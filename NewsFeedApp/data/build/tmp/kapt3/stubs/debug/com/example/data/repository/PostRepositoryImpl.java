package com.example.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/data/repository/PostRepositoryImpl;", "Lcom/example/domain/repository/PostRepository;", "api", "Lcom/example/data/remote/api/PostService;", "(Lcom/example/data/remote/api/PostService;)V", "createPost", "", "post", "Lcom/example/domain/model/CreatePost;", "(Lcom/example/domain/model/CreatePost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPostDetail", "Lcom/example/domain/model/PostDetail;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "interact", "interaction", "Lcom/example/domain/model/PostInteraction;", "(Lcom/example/domain/model/PostInteraction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class PostRepositoryImpl implements com.example.domain.repository.PostRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.data.remote.api.PostService api = null;
    
    @javax.inject.Inject()
    public PostRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.data.remote.api.PostService api) {
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