package com.example.domain.usecase;

import com.example.domain.repository.PostRepository;
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
    "KotlinInternalInJava"
})
public final class CreatePostUseCase_Factory implements Factory<CreatePostUseCase> {
  private final Provider<PostRepository> repositoryProvider;

  public CreatePostUseCase_Factory(Provider<PostRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CreatePostUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static CreatePostUseCase_Factory create(Provider<PostRepository> repositoryProvider) {
    return new CreatePostUseCase_Factory(repositoryProvider);
  }

  public static CreatePostUseCase newInstance(PostRepository repository) {
    return new CreatePostUseCase(repository);
  }
}
