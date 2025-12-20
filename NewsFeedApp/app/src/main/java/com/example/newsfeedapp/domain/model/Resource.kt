package com.example.newsfeedapp.domain.model

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable, val message: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

