package com.example.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0012H\u0016J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/data/repository/PostRepositoryImpl;", "Lcom/example/domain/repository/PostRepository;", "api", "Lcom/example/data/remote/api/PostService;", "db", "Lcom/example/data/local/PostDatabase;", "(Lcom/example/data/remote/api/PostService;Lcom/example/data/local/PostDatabase;)V", "createPost", "", "post", "Lcom/example/domain/model/CreatePost;", "(Lcom/example/domain/model/CreatePost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPostDetail", "Lcom/example/domain/model/PostDetail;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "interact", "interaction", "Lcom/example/domain/model/PostInteraction;", "(Lcom/example/domain/model/PostInteraction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
@kotlin.OptIn(markerClass = {androidx.paging.ExperimentalPagingApi.class})
public final class PostRepositoryImpl implements com.example.domain.repository.PostRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.data.remote.api.PostService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.data.local.PostDatabase db = null;
    
    @javax.inject.Inject()
    public PostRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.data.remote.api.PostService api, @org.jetbrains.annotations.NotNull()
    com.example.data.local.PostDatabase db) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPostDetail(long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.domain.model.PostDetail> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object interact(@org.jetbrains.annotations.NotNull()
    com.example.domain.model.PostInteraction interaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createPost(@org.jetbrains.annotations.NotNull()
    com.example.domain.model.CreatePost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.domain.model.PostDetail>> getPosts() {
        return null;
    }
}