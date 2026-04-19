package com.msd.data.di

import android.content.Context
import androidx.room.Room
import com.msd.data.local.PostDatabase
import com.msd.data.local.dao.PostDao
import com.msd.data.local.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): PostDatabase =
        Room.databaseBuilder(appContext, PostDatabase::class.java, "posts.db").build()

    @Provides
    @Singleton
    fun providePostDao(database: PostDatabase): PostDao = database.postDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: PostDatabase): RemoteKeysDao = database.remoteKeysDao()
}
