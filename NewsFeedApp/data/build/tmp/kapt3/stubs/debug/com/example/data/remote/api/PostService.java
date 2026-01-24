package com.example.data.remote.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u00020\u000e2\b\b\u0003\u0010\u000f\u001a\u00020\u00102\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/data/remote/api/PostService;", "", "createPost", "Lretrofit2/Response;", "", "request", "Lcom/example/data/remote/dto/CreatePostRequest;", "(Lcom/example/data/remote/dto/CreatePostRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPostDetail", "Lcom/example/data/remote/dto/PostDetailApiResponse;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "Lcom/example/data/remote/dto/PostListApiResponse;", "limit", "", "cursor", "(ILjava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interactWithPost", "Lcom/example/data/remote/dto/PostInteractionRequest;", "(Lcom/example/data/remote/dto/PostInteractionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface PostService {
    
    @retrofit2.http.GET(value = "posts/{postId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPostDetail(@retrofit2.http.Path(value = "postId")
    long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.data.remote.dto.PostDetailApiResponse>> $completion);
    
    @retrofit2.http.GET(value = "posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPosts(@retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "cursor")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer cursor, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.remote.dto.PostListApiResponse> $completion);
    
    @retrofit2.http.POST(value = "posts/interaction")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object interactWithPost(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.data.remote.dto.PostInteractionRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.POST(value = "posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPost(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.data.remote.dto.CreatePostRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}