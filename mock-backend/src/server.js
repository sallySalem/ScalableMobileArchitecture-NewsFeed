const express = require("express");
const bodyParser = require("body-parser");

const authRoutes = require("./routes/auth");
const newsfeedRoutes = require("./routes/newsfeed");

const app = express();
app.use(bodyParser.json());

app.use("/api/auth", authRoutes);
app.use("/api/newsfeed", newsfeedRoutes);

app.listen(3000, () => console.log("✅ Mock API running at http://localhost:3000"));
