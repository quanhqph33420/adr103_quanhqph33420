const express = require("express");
const router = express.Router();
const productController = require("../controller/product");
router.get("/getProduct", productController.getProduct);
router.post("/addProduct", productController.addProduct);
router.post("/deleteProduct", productController.deleteProductr);
router.post("/updateProduct", productController.updateProduct);
module.exports = router;
