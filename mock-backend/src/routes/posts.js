const express = require("express");
const router = express.Router();
const fs = require("fs");
const path = require("path");
const auth = require("../middleware/authMiddleware");
const postsFilePath = path.join(__dirname, "../data/posts.json");
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
router.get("/:postId", auth, (req, res) => {
  const postId = parseInt(req.params.postId, 10);
  const post = postsData.find(p => p.postId === postId);
  if (!post) {
    return res.status(404).json({ message: "Post not found" });
  }
  res.json({ post });
});

// Record an interaction
router.post("/interaction", auth, (req, res) => {
  const { postId, interactionType } = req.body;

  if (!postId || !interactionType) {
    return res.status(400).json({ message: "postId and interactionType are required" });
  }

  const postIndex = postsData.findIndex(p => p.postId === postId);

  if (postIndex === -1) return res.status(404).json({ message: "Post not found" });

  const post = postsData[postIndex];

  switch (interactionType) {
    case "LIKED":
      if (!post.liked) {
        post.liked = true;
        post.likedCount += 1;
      }
      break;

    case "REMOVED_LIKE":
      if (post.liked) {
        post.liked = false;
        post.likedCount = Math.max(0, post.likedCount - 1);
      }
      break;

    case "SHARED":
      post.shareCount += 1;
      break;

    default:
      return res.status(400).json({ message: "Invalid interactionType" });
  }

  fs.writeFileSync(postsFilePath, JSON.stringify(postsData, null, 2));

  return res.status(200).json({ // Use 200 OK for successful post interaction as it's update not creation. Use 201 Created for resource creation ( ex: Create new post).
    postId: post.postId,
    liked: post.liked,
    likedCount: post.likedCount,
    shareCount: post.shareCount
  });
});


module.exports = router;
