import Database from 'better-sqlite3';
import path from 'path';
import fs from 'fs';

const DB_DIR = path.join(__dirname, '..', '..', 'data');
const DB_PATH = path.join(DB_DIR, 'posts.db');

if (!fs.existsSync(DB_DIR)) {
  fs.mkdirSync(DB_DIR, { recursive: true });
}

const db = new Database(DB_PATH);

// PRAGMA for WAL (optional but recommended for concurrency)
try {
  db.pragma('journal_mode = WAL');
} catch {
  // ignore if not supported
}

export function initDb() {
  const create = `
    CREATE TABLE IF NOT EXISTS posts (
      id TEXT PRIMARY KEY,
      title TEXT NOT NULL,
      content TEXT NOT NULL,
      author TEXT,
      created_at INTEGER NOT NULL
    );
  `;
  db.exec(create);
}

export default db;
