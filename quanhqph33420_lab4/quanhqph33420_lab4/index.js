const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const dotenv = require("dotenv");
const http = require("http");
const multer = require("multer");
const nodemailer = require("nodemailer");

const app = express();
app.use(cors());
app.use(express.json());
dotenv.config();

const server = http.createServer(app);

const port = process.env.PORT || 5000;
const url_mongodb = process.env.URL_MONGODB;
const routerProduct = require("./router/product");

mongoose
  .connect(url_mongodb)
  .then((result) => {
    console.log("Connected");
  })
  .catch((err) => {
    console.log(err);
  });

app.get("/", (req, res) => {
  res.send("Welcome");
});

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, "upload");
  },

  filename: (req, file, cb) => {
    cb(null, file.originalname);
  },
});
const upload = multer({ storage });

app.get("/upload", (req, res) => {
  res.sendFile(__dirname + "/upload.html");
});

app.post("/upload", upload.single("image"), (req, res) => {
  res.send("upload anh thanh cong");
});

app.use("/product", routerProduct);

app.post("/sendMail", (req, res) => {
  var transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
      user: "vuongq303@gmail.com",
      pass: "ikpr kqwo kgzi musz",
    },
  });

  var mailOptions = {
    from: "vuongq303@gmail.com",
    to: "aduc16624@gmail.com",
    subject: "Sending Email using Node.js",
    text: "That was easy!",
  };

  transporter.sendMail(mailOptions, function (error, info) {
    if (error) {
      console.log(error);
    } else {
      console.log("Email sent: " + info.response);
    }
  });
});

server.listen(port, () => {
  console.log("Server running at " + port);
});
