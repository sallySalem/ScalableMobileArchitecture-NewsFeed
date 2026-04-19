package com.msd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msd.data.local.dao.AttachmentDao
import com.msd.data.local.dao.PostDao
import com.msd.data.local.dao.RemoteKeysDao
import com.msd.data.local.entity.AttachmentEntity
import com.msd.data.local.entity.PostEntity
import com.msd.data.local.model.RemoteKeys

@Database(entities = [PostEntity::class, RemoteKeys::class, AttachmentEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun attachmentDao(): AttachmentDao
}
