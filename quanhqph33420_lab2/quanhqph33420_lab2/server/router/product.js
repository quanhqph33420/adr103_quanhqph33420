const express = require("express");
const router = express.Router();
const productController = require("../controller/product");
router.get("/getProduct", productController.getProduct);
module.exports = router;
