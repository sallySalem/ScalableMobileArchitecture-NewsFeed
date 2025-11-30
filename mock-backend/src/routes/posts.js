const express = require("express");
const router = express.Router();
const auth = require("../middleware/authMiddleware");
const posts = require("../data/posts.json");

router.get("/", auth, (req, res) => {
  res.json(posts);
});

router.get("/:id", auth, (req, res) => {
  const id = parseInt(req.params.id, 10);
  const post = posts.find(p => p.postId === id);
  if (!post) {
    return res.status(404).json({ message: "Post not found" });
  }
  res.json({ post });
});

module.exports = router;
