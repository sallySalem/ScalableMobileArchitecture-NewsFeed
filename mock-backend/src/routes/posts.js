const express = require("express");
const router = express.Router();
const auth = require("../middleware/authMiddleware");
const postsData = require("../data/posts.json");

// Get all posts
router.get("/", auth, (req, res) => {
    const page = parseInt(req.query.page) || 1;
  const limit = parseInt(req.query.limit) || 10;

  const startIndex = (page - 1) * limit;
  const endIndex = startIndex + limit;

  const pagedPosts = postsData.slice(startIndex, endIndex);
  
  const pagination = {
    page,
    limit,
    totalItems: postsData.length,
    totalPages: Math.ceil(postsData.length / limit),
  };

  res.json({
    paging: pagination,
    posts: pagedPosts,
  });
});


// Get a single post by ID
router.get("/:id", auth, (req, res) => {
  const id = parseInt(req.params.id, 10);
  const post = posts.find(p => p.postId === id);
  if (!post) {
    return res.status(404).json({ message: "Post not found" });
  }
  res.json({ post });
});

module.exports = router;
