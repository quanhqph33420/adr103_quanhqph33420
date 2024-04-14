const express = require("express");
const mongoose = require("mongoose");

const app = express();
app.use(express.json());

mongoose
  .connect("mongodb://127.0.0.1:27017/")
  .then(() => {
    console.log("Server connected");
  })
  .catch((err) => {
    console.log(err);
  });

const schema = new mongoose.Schema({
  masach_ph33420: { type: String, required: true },
  tieude_ph33420: { type: String, required: true },
  tacgia_ph33420: { type: String, required: true },
  namxuatban_ph33420: { type: Number, required: true },
  sotrang_ph33420: { type: Number, required: true },
  theloai_ph33420: String,
});

const model = mongoose.model("test", schema);

app.get("/", async (req, res) => {
  await model
    .find({})
    .then((result) => {
      res.send(result);
    })
    .catch((err) => {
      console.log(err);
    });
});

app.get("/:id", async (req, res) => {
  const id = req.params.id;
  await model
    .findById(id)
    .then((result) => {
      res.send(result);
    })
    .catch((err) => {
      console.log(err);
    });
});

app.post("/", async (req, res) => {
  const item = req.body;
  console.log(req.body);
  await model
    .insertMany({ ...item })
    .then((result) => {
      res.json(result.length == 1 ? 1 : 0);
    })
    .catch((err) => {
      console.log(err);
    });
});

app.put("/:id", async (req, res) => {
  const id = req.params.id;
  const item = req.body;
  await model
    .findByIdAndUpdate(id, { $set: { ...item } }, { new: true })
    .then((result) => {
      res.json(result ? 1 : 0);
    })
    .catch((err) => {
      console.log(err);
    });
});

app.delete("/:id", async (req, res) => {
  const id = req.params.id;
  console.log(id);
  await model
    .findByIdAndDelete(id)
    .then((result) => {
      res.json(result ? 1 : 0);
    })
    .catch((err) => {
      console.log(err);
    });
});

app.listen(3000, () => {
  console.log("Server running");
});
