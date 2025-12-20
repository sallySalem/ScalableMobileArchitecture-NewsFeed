package com.example.newsfeedapp.data.remote

suspend fun <T> safeApiCall(call: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(call())
    } catch (t: Throwable) {
        ApiResult.Error(t)
    }
}

