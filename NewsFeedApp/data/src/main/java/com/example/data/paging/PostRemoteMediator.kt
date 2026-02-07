package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.local.PostDatabase
import com.example.data.local.entity.PostWithAttachments
import com.example.data.local.model.RemoteKeys
import com.example.data.mapper.toEntity
import com.example.data.remote.api.PostService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator @Inject constructor(
    private val service: PostService,
    private val database: PostDatabase
) : RemoteMediator<Int, PostWithAttachments>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostWithAttachments>
    ): MediatorResult {

        val cursor: Long? = when (loadType) {
            LoadType.REFRESH -> null

            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)

                database.remoteKeysDao()
                    .remoteKeysPostId(lastItem.post.id)
                    ?.nextCursor
            }
        }

        return try {
            val response = service.getPosts(
                limit = state.config.pageSize,
                cursor = cursor
            )

            val posts = response.posts
            val paging = response.paging
            val endOfPaginationReached = !paging.hasMore

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.postDao().deleteAll()
                }

                val keys = posts.map {
                    RemoteKeys(
                        postId = it.postId,
                        nextCursor = paging.nextCursor
                    )
                }

                val postEntities = posts.map { it.toEntity() }
                val attachmentEntities = posts.flatMap { post ->
                    post.attachments.map { attachment ->
                        attachment.toEntity(post.postId)
                    }
                }

                database.remoteKeysDao().insertAll(keys)
                database.postDao().insertAll(postEntities)
                database.attachmentDao().insertAll(attachmentEntities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
