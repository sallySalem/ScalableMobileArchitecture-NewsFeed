const express = require("express");
const router = express.Router();
const auth = require("../middleware/authMiddleware");
const postsData = require("../data/posts.json");

// Get all posts
router.get("/", auth, (req, res) => {
  const limit = parseInt(req.query.limit) || 10;
  const cursor = req.query.cursor ? parseInt(req.query.cursor) : null;

  let startIndex = 0;
  if (cursor) {
    const index = postsData.findIndex(p => p.id === cursor);
    startIndex = index >= 0 ? index + 1 : 0;
  }

  const slicedData = postsData.slice(startIndex, startIndex + limit);

  const lastItem = slicedData[slicedData.length - 1];
  const nextCursor = lastItem ? lastItem.id : null;

    const pagination = {
      nextCursor,
      hasMore: startIndex + limit < postsData.length,
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
