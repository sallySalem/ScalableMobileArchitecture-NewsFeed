package com.example.newsfeedapp.ui

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class PostEvent {
    object PostCreated : PostEvent()
}

@Singleton
class PostEventBus @Inject constructor() {
    private val _events = MutableSharedFlow<PostEvent>()
    val events = _events.asSharedFlow()

    suspend fun post(event: PostEvent) {
        _events.emit(event)
    }
}
