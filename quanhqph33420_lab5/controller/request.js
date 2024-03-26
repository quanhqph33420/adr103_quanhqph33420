const con = require("../config/sql");
class request {
  async sendRequest(req, res) {
    const { tai_khoan, so_tien, thong_tin1, thong_tin2 } = req.body;
    var sql = `INSERT INTO requests (tai_khoan, so_tien, thong_tin1, thong_tin2) VALUES ('${tai_khoan}', '${so_tien}', '${thong_tin1}', '${thong_tin2}')`;
    con.query(sql, function (err, result) {
      if (err) throw err;
      res.json(result.affectedRows);
    });
  }
  async updateRequest(req, res) {
    const { tai_khoan } = req.body;
    const sql = `SELECT * FROM requests WHERE tai_khoan = '${tai_khoan}' AND trang_thai = '1'`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      console.log(result[0].id);
      if (result.length > 0) {
        var sql = `UPDATE requests SET trang_thai = '0' WHERE id = '${result[0].id}'`;
        con.query(sql, function (err, result) {
          if (err) throw err;
          res.json(result.affectedRows);
        });
      } else {
        res.json(false);
      }
    });
  }
  async getUser(req, res) {
    const sql = `SELECT * FROM users`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      res.send(result);
    });
  }
  async getRequest(req, res) {
    const sql = `SELECT * FROM requests`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      res.send(result);
    });
  }
}
module.exports = new request();
