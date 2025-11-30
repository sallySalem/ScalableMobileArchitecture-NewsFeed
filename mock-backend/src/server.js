const express = require("express");
const bodyParser = require("body-parser");

const authRoutes = require("./routes/auth");
const postsRoutes = require("./routes/posts");

const app = express();
app.use(bodyParser.json());

app.use("/api/auth", authRoutes);
app.use("/api/posts", postsRoutes);

app.listen(3000, () => console.log("✅ Mock API running at http://localhost:3000"));
