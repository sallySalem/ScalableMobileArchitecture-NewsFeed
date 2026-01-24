export interface Post {
  id?: string;           // uuid v4
  title: string;
  content: string;
  author?: string;
  created_at?: number;   // epoch ms
}
