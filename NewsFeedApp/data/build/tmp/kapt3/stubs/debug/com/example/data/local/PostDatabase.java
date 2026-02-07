package com.example.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/example/data/local/PostDatabase;", "Landroidx/room/RoomDatabase;", "()V", "attachmentDao", "Lcom/example/data/local/dao/AttachmentDao;", "postDao", "Lcom/example/data/local/dao/PostDao;", "remoteKeysDao", "Lcom/example/data/local/dao/RemoteKeysDao;", "data_debug"})
@androidx.room.Database(entities = {com.example.data.local.entity.PostEntity.class, com.example.data.local.model.RemoteKeys.class, com.example.data.local.entity.AttachmentEntity.class}, version = 1)
public abstract class PostDatabase extends androidx.room.RoomDatabase {
    
    public PostDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.data.local.dao.PostDao postDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.data.local.dao.RemoteKeysDao remoteKeysDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.data.local.dao.AttachmentDao attachmentDao();
}