package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.AttachmentDao
import com.example.data.local.dao.PostDao
import com.example.data.local.dao.RemoteKeysDao
import com.example.data.local.entity.AttachmentEntity
import com.example.data.local.entity.PostEntity
import com.example.data.local.model.RemoteKeys

@Database(entities = [PostEntity::class, RemoteKeys::class, AttachmentEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun attachmentDao(): AttachmentDao
}
