package com.example.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = withContext(dispatcher) { call() }

        if (response.isSuccessful) {
            ApiResult.Success(response.body() as T)
        } else {
            ApiResult.Error(
                HttpException(response)
            )
        }
    } catch (t: Throwable) {
        ApiResult.Error(t)
    }
}
