package com.example.posts_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.PostDetail
import com.example.domain.usecase.GetPostsUseCase
import com.example.domain.event.PostEvent
import com.example.domain.event.PostEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postEventBus: PostEventBus
) : ViewModel() {

    private val refreshTrigger = MutableStateFlow(0)

    val posts: Flow<PagingData<PostDetail>> = refreshTrigger.flatMapLatest {
        getPostsUseCase()
    }.cachedIn(viewModelScope)

    init {
        postEventBus.events
            .onEach { event ->
                if (event is PostEvent.PostCreated) {
                    // Add a delay to allow the backend to process the new post and avoid race conditions.
                    delay(1000)
                    refresh()
                }
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        refreshTrigger.value++
    }
}
