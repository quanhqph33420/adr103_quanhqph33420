const productModel = require("../model/product");
class product {
  async getProduct(req, res) {
    await productModel
      .find({})
      .then((result) => {
        res.send(result);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  async addProduct(req, res) {
    const name = req.body.name;
    const type = req.body.type;
    await productModel
      .insertMany({ name: name, type: type })
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  async updateProduct(req, res) {
    const id = req.body.id;
    const name = req.body.name;
    const type = req.body.type;
    await productModel
      .findByIdAndUpdate(id, {
        $set: {
          name: name,
          type: type,
        },
      })
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  async deleteProductr(req, res) {
    const id = req.body.id;
    await productModel
      .findByIdAndDelete(id)
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
module.exports = new product();
