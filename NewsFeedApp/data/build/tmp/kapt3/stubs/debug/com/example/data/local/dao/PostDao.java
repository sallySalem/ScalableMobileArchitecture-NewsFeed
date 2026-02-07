package com.example.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000bH\'J\u001c\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/example/data/local/dao/PostDao;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPost", "Lcom/example/data/local/entity/PostWithAttachments;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "Landroidx/paging/PagingSource;", "", "insertAll", "posts", "", "Lcom/example/data/local/entity/PostEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
@androidx.room.Dao()
public abstract interface PostDao {
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM posts")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.paging.PagingSource<java.lang.Integer, com.example.data.local.entity.PostWithAttachments> getPosts();
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM posts WHERE id = :postId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPost(long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.local.entity.PostWithAttachments> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.data.local.entity.PostEntity> posts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}