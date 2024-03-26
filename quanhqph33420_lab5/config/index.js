const express = require("express");
const cors = require("cors");
require("../config/sql");
const userRouter = require("../router/user");
const sendRouter = require("../router/request");

const app = express();
app.use(express.json());
app.use(cors());

app.get("/", (_, res) => res.send("Hello"));
app.use("/login", userRouter);
app.use("/send", sendRouter);
module.exports = app;
