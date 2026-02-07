package com.example.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.data.local.dao.AttachmentDao;
import com.example.data.local.dao.AttachmentDao_Impl;
import com.example.data.local.dao.PostDao;
import com.example.data.local.dao.PostDao_Impl;
import com.example.data.local.dao.RemoteKeysDao;
import com.example.data.local.dao.RemoteKeysDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PostDatabase_Impl extends PostDatabase {
  private volatile PostDao _postDao;

  private volatile RemoteKeysDao _remoteKeysDao;

  private volatile AttachmentDao _attachmentDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `posts` (`id` INTEGER NOT NULL, `content` TEXT NOT NULL, `authorId` INTEGER NOT NULL, `authorName` TEXT NOT NULL, `authorAvatarUrl` TEXT, `createdAt` TEXT NOT NULL, `liked` INTEGER NOT NULL, `likedCount` INTEGER NOT NULL, `shareCount` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `remote_keys` (`postId` INTEGER NOT NULL, `nextCursor` INTEGER, PRIMARY KEY(`postId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`id` INTEGER NOT NULL, `postId` INTEGER NOT NULL, `type` TEXT NOT NULL, `contentUrl` TEXT NOT NULL, `previewImageUrl` TEXT, `caption` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`postId`) REFERENCES `posts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '89d1f76cf029f3de5e1f621ec25b6c02')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `posts`");
        db.execSQL("DROP TABLE IF EXISTS `remote_keys`");
        db.execSQL("DROP TABLE IF EXISTS `attachments`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPosts = new HashMap<String, TableInfo.Column>(9);
        _columnsPosts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("authorId", new TableInfo.Column("authorId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("authorName", new TableInfo.Column("authorName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("authorAvatarUrl", new TableInfo.Column("authorAvatarUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("liked", new TableInfo.Column("liked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("likedCount", new TableInfo.Column("likedCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPosts.put("shareCount", new TableInfo.Column("shareCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPosts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPosts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPosts = new TableInfo("posts", _columnsPosts, _foreignKeysPosts, _indicesPosts);
        final TableInfo _existingPosts = TableInfo.read(db, "posts");
        if (!_infoPosts.equals(_existingPosts)) {
          return new RoomOpenHelper.ValidationResult(false, "posts(com.example.data.local.entity.PostEntity).\n"
                  + " Expected:\n" + _infoPosts + "\n"
                  + " Found:\n" + _existingPosts);
        }
        final HashMap<String, TableInfo.Column> _columnsRemoteKeys = new HashMap<String, TableInfo.Column>(2);
        _columnsRemoteKeys.put("postId", new TableInfo.Column("postId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRemoteKeys.put("nextCursor", new TableInfo.Column("nextCursor", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRemoteKeys = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRemoteKeys = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRemoteKeys = new TableInfo("remote_keys", _columnsRemoteKeys, _foreignKeysRemoteKeys, _indicesRemoteKeys);
        final TableInfo _existingRemoteKeys = TableInfo.read(db, "remote_keys");
        if (!_infoRemoteKeys.equals(_existingRemoteKeys)) {
          return new RoomOpenHelper.ValidationResult(false, "remote_keys(com.example.data.local.model.RemoteKeys).\n"
                  + " Expected:\n" + _infoRemoteKeys + "\n"
                  + " Found:\n" + _existingRemoteKeys);
        }
        final HashMap<String, TableInfo.Column> _columnsAttachments = new HashMap<String, TableInfo.Column>(6);
        _columnsAttachments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("postId", new TableInfo.Column("postId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("contentUrl", new TableInfo.Column("contentUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("previewImageUrl", new TableInfo.Column("previewImageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("caption", new TableInfo.Column("caption", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttachments = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAttachments.add(new TableInfo.ForeignKey("posts", "CASCADE", "NO ACTION", Arrays.asList("postId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAttachments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttachments = new TableInfo("attachments", _columnsAttachments, _foreignKeysAttachments, _indicesAttachments);
        final TableInfo _existingAttachments = TableInfo.read(db, "attachments");
        if (!_infoAttachments.equals(_existingAttachments)) {
          return new RoomOpenHelper.ValidationResult(false, "attachments(com.example.data.local.entity.AttachmentEntity).\n"
                  + " Expected:\n" + _infoAttachments + "\n"
                  + " Found:\n" + _existingAttachments);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "89d1f76cf029f3de5e1f621ec25b6c02", "94f932f743fc39e63145dfbea0a11f9a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "posts","remote_keys","attachments");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `posts`");
      _db.execSQL("DELETE FROM `remote_keys`");
      _db.execSQL("DELETE FROM `attachments`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PostDao.class, PostDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RemoteKeysDao.class, RemoteKeysDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttachmentDao.class, AttachmentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PostDao postDao() {
    if (_postDao != null) {
      return _postDao;
    } else {
      synchronized(this) {
        if(_postDao == null) {
          _postDao = new PostDao_Impl(this);
        }
        return _postDao;
      }
    }
  }

  @Override
  public RemoteKeysDao remoteKeysDao() {
    if (_remoteKeysDao != null) {
      return _remoteKeysDao;
    } else {
      synchronized(this) {
        if(_remoteKeysDao == null) {
          _remoteKeysDao = new RemoteKeysDao_Impl(this);
        }
        return _remoteKeysDao;
      }
    }
  }

  @Override
  public AttachmentDao attachmentDao() {
    if (_attachmentDao != null) {
      return _attachmentDao;
    } else {
      synchronized(this) {
        if(_attachmentDao == null) {
          _attachmentDao = new AttachmentDao_Impl(this);
        }
        return _attachmentDao;
      }
    }
  }
}
