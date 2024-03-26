const express = require("express");
const router = express.Router();
const controller = require("../controller/request");
router.post("/sendRequest", controller.sendRequest);
router.post("/updateRequest", controller.updateRequest);
router.get("/getUser", controller.getUser);
router.get("/getRequest", controller.getRequest);
module.exports = router;
