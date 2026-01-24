package com.example.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0001H\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&\u00a8\u0006\u0012\u00c0\u0006\u0003"}, d2 = {"Lcom/example/domain/repository/PostRepository;", "", "getPostDetail", "Lcom/example/domain/model/PostDetail;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createPost", "", "post", "Lcom/example/domain/model/CreatePost;", "(Lcom/example/domain/model/CreatePost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interact", "request", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/domain/model/PaginatedPosts;", "domain"})
public abstract interface PostRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPostDetail(long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.domain.model.PostDetail> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPost(@org.jetbrains.annotations.NotNull()
    com.example.domain.model.CreatePost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object interact(@org.jetbrains.annotations.NotNull()
    java.lang.Object request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.domain.model.PaginatedPosts> getPosts();
}