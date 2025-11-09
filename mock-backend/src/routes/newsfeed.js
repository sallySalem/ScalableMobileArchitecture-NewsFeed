const express = require("express");
const router = express.Router();
const auth = require("../middleware/authMiddleware");

router.get("/", auth, (req, res) => {
  res.json([
    { id: 1, title: "News Item A", content: "Content for news item A" },
    { id: 2, title: "News Item B", content: "Content for news item B" }
  ]);
});

module.exports = router;
