# Mock Backend

Mock REST API using Express.js + Token-based Authentication for a News Feed application.

## Features

- **Token Authentication**: Bearer token validation for protected endpoints
- **Posts API**: Retrieve all posts or fetch a single post by ID
- **User Authentication**: Login endpoint to generate mock tokens
- **Mock Images**: Uses [**Picsum Photos**](https://picsum.photos) for realistic image URLs
  - Global CDN ensures fast loading
  - Fixed ID images are cached for better performance
  - Lightweight JPEG format

## Installation & Setup

```bash
npm install
npm run dev
```

The server will start on `http://localhost:3000`

## API Endpoints

### 1. Authentication

**Login** - Get a mock authentication token:

```bash
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "test", "password": "1234"}'
```

**Response:**
```json
{
  "token": "dGVzdC1tb2NrdG9rZW4="
}
```

### 2. Get All Posts

**Get all posts** (requires authentication):

```bash
curl -X GET http://localhost:3000/api/posts \
  -H "Authorization: Bearer dGVzdC1tb2NrdG9rZW4="
```

### 3. Get Single Post by ID

**Get a specific post** (requires authentication):

```bash
curl -X GET http://localhost:3000/api/posts/0 \
  -H "Authorization: Bearer dGVzdC1tb2NrdG9rZW4="
```

Replace `0` with the post ID (0-9 available)

## Mock Data

- **10 posts** with realistic social media content
- Each post includes:
  - Post ID
  - Content/text
  - Author info (name, avatar)
  - Created timestamp
  - Like count and share count
  - Image attachments

## Default Test Credentials

- **Username:** `test`
- **Password:** `1234`
- **Mock Token:** `dGVzdC1tb2NrdG9rZW4=` (base64 encoded: "test-mocktoken")