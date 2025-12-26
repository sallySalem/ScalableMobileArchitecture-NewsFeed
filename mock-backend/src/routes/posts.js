const express = require("express");
const router = express.Router();
const auth = require("../middleware/authMiddleware");
const postsData = require("../data/posts.json");

// Get all posts
router.get("/", auth, (req, res) => {
  const limit = Number(req.query.limit) || 10;

  const cursor =
    req.query.cursor !== undefined && !Number.isNaN(Number(req.query.cursor))
      ? Number(req.query.cursor)
      : null;

  let startIndex = 0;

  if (cursor !== null) {
    const index = postsData.findIndex(p => p.postId === cursor);
    startIndex = index >= 0 ? index + 1 : 0;
  }

  const slicedData = postsData.slice(startIndex, startIndex + limit);

  const hasMore = startIndex + slicedData.length < postsData.length;

  const nextCursor =
    hasMore && slicedData.length > 0
      ? slicedData[slicedData.length - 1].postId
      : null;


  const pagination = {
    nextCursor,
    hasMore
  };

  res.json({
    posts: slicedData,
    paging: pagination,
  });
});

// Get a single post by ID
router.get("/:id", auth, (req, res) => {
  const id = parseInt(req.params.id, 10);
  const post = postsData.find(p => p.postId === id);
  if (!post) {
    return res.status(404).json({ message: "Post not found" });
  }
  res.json({ post });
});

module.exports = router;
