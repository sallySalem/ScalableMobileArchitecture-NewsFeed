package com.example.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.entity.PostEntity
import com.example.data.local.entity.PostWithAttachments

@Dao
interface PostDao {
    @Transaction
    @Query("SELECT * FROM posts")
    fun getPosts(): PagingSource<Int, PostWithAttachments>

    @Transaction
    @Query("SELECT * FROM posts WHERE id = :postId")
    suspend fun getPost(postId: Long): PostWithAttachments?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}
