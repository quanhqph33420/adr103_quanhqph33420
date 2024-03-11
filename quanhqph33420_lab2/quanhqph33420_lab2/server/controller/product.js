const productModel = require("../model/product");
class product {
  async getProduct(req, res) {
    await productModel
      .find({})
      .then((result) => {
        res.render("index", { products: result });
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
module.exports = new product();
