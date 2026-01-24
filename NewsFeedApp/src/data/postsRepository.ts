import db, { initDb } from './database';
import { Post } from '../models/post';
import { v4 as uuidv4 } from 'uuid';

// Ensure DB/table created when repository is imported
initDb();

export function savePost(input: Omit<Post, 'id' | 'created_at'> & Partial<Pick<Post, 'id' | 'created_at'>>) : Post {
  const now = Date.now();
  const id = input.id ?? uuidv4();
  const created_at = input.created_at ?? now;

  const stmt = db.prepare(`
    INSERT OR REPLACE INTO posts (id, title, content, author, created_at)
    VALUES (@id, @title, @content, @author, @created_at)
  `);

  stmt.run({
    id,
    title: input.title,
    content: input.content,
    author: input.author ?? null,
    created_at
  });

  return { id, title: input.title, content: input.content, author: input.author, created_at };
}

export function getAllPosts(): Post[] {
  const stmt = db.prepare(`SELECT id, title, content, author, created_at FROM posts ORDER BY created_at DESC`);
  const rows = stmt.all();
  return rows.map((r: any) => ({ id: r.id, title: r.title, content: r.content, author: r.author, created_at: r.created_at }));
}

export function getPostById(id: string): Post | null {
  const stmt = db.prepare(`SELECT id, title, content, author, created_at FROM posts WHERE id = ?`);
  const row = stmt.get(id);
  if (!row) return null;
  return { id: row.id, title: row.title, content: row.content, author: row.author, created_at: row.created_at };
}

export function updatePost(id: string, fields: Partial<Omit<Post, 'id' | 'created_at'>>): Post | null {
  const existing = getPostById(id);
  if (!existing) return null;
  const updated = {
    title: fields.title ?? existing.title,
    content: fields.content ?? existing.content,
    author: fields.author ?? existing.author
  };
  const stmt = db.prepare(`
    UPDATE posts SET title = @title, content = @content, author = @author WHERE id = @id
  `);
  stmt.run({ ...updated, id });
  return getPostById(id);
}

export function deletePost(id: string): boolean {
  const stmt = db.prepare(`DELETE FROM posts WHERE id = ?`);
  const info = stmt.run(id);
  return info.changes > 0;
}
