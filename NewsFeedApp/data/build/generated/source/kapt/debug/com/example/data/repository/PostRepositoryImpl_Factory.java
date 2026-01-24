package com.example.data.repository;

import com.example.data.remote.api.PostService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class PostRepositoryImpl_Factory implements Factory<PostRepositoryImpl> {
  private final Provider<PostService> apiProvider;

  public PostRepositoryImpl_Factory(Provider<PostService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public PostRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static PostRepositoryImpl_Factory create(Provider<PostService> apiProvider) {
    return new PostRepositoryImpl_Factory(apiProvider);
  }

  public static PostRepositoryImpl newInstance(PostService api) {
    return new PostRepositoryImpl(api);
  }
}
