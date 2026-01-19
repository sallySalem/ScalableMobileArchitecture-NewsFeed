function authMiddleware(req, res, next) {
  const authHeader = req.headers.authorization;

  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    return res.status(401).json({ message: "No token provided" });
  }

  const token = authHeader.split(" ")[1];

  if (!token || !Buffer.from(token, "base64").toString().includes("-mocktoken")) {
    return res.status(403).json({ message: "Invalid token" });
  }

   // Mock logged-in user
  req.user = {
    id: 101,
    name: "Mock User",
    avatarUrl: "https://i.pravatar.cc/150?img=40"
  };
  
  next();
}

module.exports = authMiddleware;
