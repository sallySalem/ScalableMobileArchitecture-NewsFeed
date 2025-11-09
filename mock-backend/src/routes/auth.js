const express = require("express");
const router = express.Router();
const users = require("../data/users.json");

// Fake token generator
function generateToken(username) {
  return Buffer.from(username + "-mocktoken").toString("base64");
}

router.post("/login", (req, res) => {
  const { username, password } = req.body;

  const user = users.find(
    (u) => u.username === username && u.password === password
  );

  if (!user) {
    return res.status(401).json({ message: "Invalid credentials" });
  }

  const token = generateToken(username);
  res.json({ token });
});

module.exports = router;
