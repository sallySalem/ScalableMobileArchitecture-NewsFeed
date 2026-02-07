package com.example.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.data.local.entity.AttachmentEntity;
import com.example.data.local.entity.PostEntity;
import com.example.data.local.entity.PostWithAttachments;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PostDao_Impl implements PostDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PostEntity> __insertionAdapterOfPostEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PostDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPostEntity = new EntityInsertionAdapter<PostEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `posts` (`id`,`content`,`authorId`,`authorName`,`authorAvatarUrl`,`createdAt`,`liked`,`likedCount`,`shareCount`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        statement.bindLong(3, entity.getAuthorId());
        if (entity.getAuthorName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAuthorName());
        }
        if (entity.getAuthorAvatarUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAuthorAvatarUrl());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCreatedAt());
        }
        final int _tmp = entity.getLiked() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getLikedCount());
        statement.bindLong(9, entity.getShareCount());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM posts";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<PostEntity> posts,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPostEntity.insert(posts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public PagingSource<Integer, PostWithAttachments> getPosts() {
    final String _sql = "SELECT * FROM posts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new LimitOffsetPagingSource<PostWithAttachments>(_statement, __db, "attachments", "posts") {
      @Override
      @NonNull
      protected List<PostWithAttachments> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
        final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(cursor, "authorId");
        final int _cursorIndexOfAuthorName = CursorUtil.getColumnIndexOrThrow(cursor, "authorName");
        final int _cursorIndexOfAuthorAvatarUrl = CursorUtil.getColumnIndexOrThrow(cursor, "authorAvatarUrl");
        final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "createdAt");
        final int _cursorIndexOfLiked = CursorUtil.getColumnIndexOrThrow(cursor, "liked");
        final int _cursorIndexOfLikedCount = CursorUtil.getColumnIndexOrThrow(cursor, "likedCount");
        final int _cursorIndexOfShareCount = CursorUtil.getColumnIndexOrThrow(cursor, "shareCount");
        final LongSparseArray<ArrayList<AttachmentEntity>> _collectionAttachments = new LongSparseArray<ArrayList<AttachmentEntity>>();
        while (cursor.moveToNext()) {
          final long _tmpKey;
          _tmpKey = cursor.getLong(_cursorIndexOfId);
          if (!_collectionAttachments.containsKey(_tmpKey)) {
            _collectionAttachments.put(_tmpKey, new ArrayList<AttachmentEntity>());
          }
        }
        cursor.moveToPosition(-1);
        __fetchRelationshipattachmentsAscomExampleDataLocalEntityAttachmentEntity(_collectionAttachments);
        final List<PostWithAttachments> _result = new ArrayList<PostWithAttachments>(cursor.getCount());
        while (cursor.moveToNext()) {
          final PostWithAttachments _item;
          final PostEntity _tmpPost;
          final long _tmpId;
          _tmpId = cursor.getLong(_cursorIndexOfId);
          final String _tmpContent;
          if (cursor.isNull(_cursorIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = cursor.getString(_cursorIndexOfContent);
          }
          final long _tmpAuthorId;
          _tmpAuthorId = cursor.getLong(_cursorIndexOfAuthorId);
          final String _tmpAuthorName;
          if (cursor.isNull(_cursorIndexOfAuthorName)) {
            _tmpAuthorName = null;
          } else {
            _tmpAuthorName = cursor.getString(_cursorIndexOfAuthorName);
          }
          final String _tmpAuthorAvatarUrl;
          if (cursor.isNull(_cursorIndexOfAuthorAvatarUrl)) {
            _tmpAuthorAvatarUrl = null;
          } else {
            _tmpAuthorAvatarUrl = cursor.getString(_cursorIndexOfAuthorAvatarUrl);
          }
          final String _tmpCreatedAt;
          if (cursor.isNull(_cursorIndexOfCreatedAt)) {
            _tmpCreatedAt = null;
          } else {
            _tmpCreatedAt = cursor.getString(_cursorIndexOfCreatedAt);
          }
          final boolean _tmpLiked;
          final int _tmp;
          _tmp = cursor.getInt(_cursorIndexOfLiked);
          _tmpLiked = _tmp != 0;
          final int _tmpLikedCount;
          _tmpLikedCount = cursor.getInt(_cursorIndexOfLikedCount);
          final int _tmpShareCount;
          _tmpShareCount = cursor.getInt(_cursorIndexOfShareCount);
          _tmpPost = new PostEntity(_tmpId,_tmpContent,_tmpAuthorId,_tmpAuthorName,_tmpAuthorAvatarUrl,_tmpCreatedAt,_tmpLiked,_tmpLikedCount,_tmpShareCount);
          final ArrayList<AttachmentEntity> _tmpAttachmentsCollection;
          final long _tmpKey_1;
          _tmpKey_1 = cursor.getLong(_cursorIndexOfId);
          _tmpAttachmentsCollection = _collectionAttachments.get(_tmpKey_1);
          _item = new PostWithAttachments(_tmpPost,_tmpAttachmentsCollection);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public Object getPost(final long postId,
      final Continuation<? super PostWithAttachments> $completion) {
    final String _sql = "SELECT * FROM posts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, postId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<PostWithAttachments>() {
      @Override
      @Nullable
      public PostWithAttachments call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
            final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
            final int _cursorIndexOfAuthorName = CursorUtil.getColumnIndexOrThrow(_cursor, "authorName");
            final int _cursorIndexOfAuthorAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "authorAvatarUrl");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfLiked = CursorUtil.getColumnIndexOrThrow(_cursor, "liked");
            final int _cursorIndexOfLikedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "likedCount");
            final int _cursorIndexOfShareCount = CursorUtil.getColumnIndexOrThrow(_cursor, "shareCount");
            final LongSparseArray<ArrayList<AttachmentEntity>> _collectionAttachments = new LongSparseArray<ArrayList<AttachmentEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionAttachments.containsKey(_tmpKey)) {
                _collectionAttachments.put(_tmpKey, new ArrayList<AttachmentEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipattachmentsAscomExampleDataLocalEntityAttachmentEntity(_collectionAttachments);
            final PostWithAttachments _result;
            if (_cursor.moveToFirst()) {
              final PostEntity _tmpPost;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpContent;
              if (_cursor.isNull(_cursorIndexOfContent)) {
                _tmpContent = null;
              } else {
                _tmpContent = _cursor.getString(_cursorIndexOfContent);
              }
              final long _tmpAuthorId;
              _tmpAuthorId = _cursor.getLong(_cursorIndexOfAuthorId);
              final String _tmpAuthorName;
              if (_cursor.isNull(_cursorIndexOfAuthorName)) {
                _tmpAuthorName = null;
              } else {
                _tmpAuthorName = _cursor.getString(_cursorIndexOfAuthorName);
              }
              final String _tmpAuthorAvatarUrl;
              if (_cursor.isNull(_cursorIndexOfAuthorAvatarUrl)) {
                _tmpAuthorAvatarUrl = null;
              } else {
                _tmpAuthorAvatarUrl = _cursor.getString(_cursorIndexOfAuthorAvatarUrl);
              }
              final String _tmpCreatedAt;
              if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
                _tmpCreatedAt = null;
              } else {
                _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
              }
              final boolean _tmpLiked;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfLiked);
              _tmpLiked = _tmp != 0;
              final int _tmpLikedCount;
              _tmpLikedCount = _cursor.getInt(_cursorIndexOfLikedCount);
              final int _tmpShareCount;
              _tmpShareCount = _cursor.getInt(_cursorIndexOfShareCount);
              _tmpPost = new PostEntity(_tmpId,_tmpContent,_tmpAuthorId,_tmpAuthorName,_tmpAuthorAvatarUrl,_tmpCreatedAt,_tmpLiked,_tmpLikedCount,_tmpShareCount);
              final ArrayList<AttachmentEntity> _tmpAttachmentsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpAttachmentsCollection = _collectionAttachments.get(_tmpKey_1);
              _result = new PostWithAttachments(_tmpPost,_tmpAttachmentsCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipattachmentsAscomExampleDataLocalEntityAttachmentEntity(
      @NonNull final LongSparseArray<ArrayList<AttachmentEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipattachmentsAscomExampleDataLocalEntityAttachmentEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`postId`,`type`,`contentUrl`,`previewImageUrl`,`caption` FROM `attachments` WHERE `postId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "postId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfPostId = 1;
      final int _cursorIndexOfType = 2;
      final int _cursorIndexOfContentUrl = 3;
      final int _cursorIndexOfPreviewImageUrl = 4;
      final int _cursorIndexOfCaption = 5;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<AttachmentEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final AttachmentEntity _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final long _tmpPostId;
          _tmpPostId = _cursor.getLong(_cursorIndexOfPostId);
          final String _tmpType;
          if (_cursor.isNull(_cursorIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _cursor.getString(_cursorIndexOfType);
          }
          final String _tmpContentUrl;
          if (_cursor.isNull(_cursorIndexOfContentUrl)) {
            _tmpContentUrl = null;
          } else {
            _tmpContentUrl = _cursor.getString(_cursorIndexOfContentUrl);
          }
          final String _tmpPreviewImageUrl;
          if (_cursor.isNull(_cursorIndexOfPreviewImageUrl)) {
            _tmpPreviewImageUrl = null;
          } else {
            _tmpPreviewImageUrl = _cursor.getString(_cursorIndexOfPreviewImageUrl);
          }
          final String _tmpCaption;
          if (_cursor.isNull(_cursorIndexOfCaption)) {
            _tmpCaption = null;
          } else {
            _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
          }
          _item_1 = new AttachmentEntity(_tmpId,_tmpPostId,_tmpType,_tmpContentUrl,_tmpPreviewImageUrl,_tmpCaption);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
