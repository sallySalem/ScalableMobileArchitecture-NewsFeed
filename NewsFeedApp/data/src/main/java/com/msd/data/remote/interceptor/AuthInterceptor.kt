package com.msd.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        // Hardcoded token – as will skip the login in that example.
        private const val MOCK_TOKEN = "dGVzdC1tb2NrdG9rZW4="
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $MOCK_TOKEN")
            .build()

        return chain.proceed(newRequest)
    }
}