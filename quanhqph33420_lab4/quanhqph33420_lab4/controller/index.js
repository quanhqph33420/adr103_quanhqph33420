class index {
  index(req, res) {
    res.send("<h1>Running</h1>");
  }
}
module.exports = new index();
