package com.example.newsfeedapp.di

import com.example.newsfeedapp.data.repository.PostRepositoryImpl
import com.example.newsfeedapp.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        default: DefaultDispatcherProvider
    ): DispatcherProvider

    // Use of @Binds instead of @Provides for DispatcherProvider.
//    companion object {
//        @Provides
//        @Singleton
//        fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
//    }
}
