package com.example.data.di;

import com.example.data.local.PostDatabase;
import com.example.data.local.dao.PostDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvidePostDaoFactory implements Factory<PostDao> {
  private final Provider<PostDatabase> databaseProvider;

  public DatabaseModule_ProvidePostDaoFactory(Provider<PostDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PostDao get() {
    return providePostDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePostDaoFactory create(
      Provider<PostDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePostDaoFactory(databaseProvider);
  }

  public static PostDao providePostDao(PostDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePostDao(database));
  }
}
