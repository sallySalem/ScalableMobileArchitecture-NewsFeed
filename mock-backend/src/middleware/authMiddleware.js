function authMiddleware(req, res, next) {
  const authHeader = req.headers.authorization;

  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    return res.status(401).json({ message: "No token provided" });
  }

  const token = authHeader.split(" ")[1];

  // For mock: just check if token includes "-mocktoken"
  if (!token || !Buffer.from(token, "base64").toString().includes("-mocktoken")) {
    return res.status(403).json({ message: "Invalid token" });
  }

  next();
}

module.exports = authMiddleware;
