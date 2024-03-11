const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const dotenv = require("dotenv");
const http = require("http");

const app = express();
app.use(cors());
app.use(express.json());
app.set("view engine", "ejs");
dotenv.config();

const server = http.createServer(app);

const port = process.env.PORT || 5000;
const url_mongodb = process.env.URL_MONGODB;

const routerDefault = require("./router/index");
const routerProduct = require("./router/product");

mongoose
  .connect(url_mongodb)
  .then((result) => {
    console.log("Connected");
  })
  .catch((err) => {
    console.log(err);
  });

app.use("/", routerDefault);
app.use("/product", routerProduct);

server.listen(port, () => {
  console.log("Server running at " + port);
});
