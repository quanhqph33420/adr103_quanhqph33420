const con = require("../config/sql");
const hash = require("../config/hash");
class user {
  async signUp(req, res) {
    const { tai_khoan, mat_khau } = req.body;
    console.log(req.body);
    res.json(req.body);
    // const mk = await hash.hashPassword(mat_khau);
    // const sql = `SELECT * FROM users WHERE tai_khoan = '${tai_khoan}'`;
    // con.query(sql, async function (err, result) {
    //   if (err) throw err;
    //   if (result.length == 0) {
    //     var sql = `INSERT INTO users (tai_khoan, mat_khau) VALUES ('${tai_khoan}', '${mk}')`;
    //     con.query(sql, function (err, result) {
    //       if (err) throw err;
    //       res.json(result.affectedRows);
    //     });
    //   } else {
    //     res.json(false);
    //   }
    // });
  }

  async signIn(req, res) {
    const { tai_khoan, mat_khau } = req.body;
    const sql = `SELECT * FROM users WHERE tai_khoan = '${tai_khoan}'`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      if (result.length == 1) {
        let compare = await hash.comparePassword(mat_khau, result[0].mat_khau);
        res.json(compare);
      } else {
        res.json(false);
      }
    });
  }

  async congTien(req, res) {
    const { tai_khoan, so_tien } = req.body;
    const sql = `SELECT * FROM users WHERE tai_khoan = '${tai_khoan}'`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      if (result.length == 1) {
        const st =
          Number.parseInt(so_tien) + Number.parseInt(result[0].so_tien);
        var sql = `UPDATE users SET so_tien = '${st}' WHERE tai_khoan = '${tai_khoan}'`;
        con.query(sql, function (err, result) {
          if (err) throw err;
          res.json(result);
        });
      } else {
        res.json(false);
      }
    });
  }

  async truTien(req, res) {
    const { tai_khoan, so_tien } = req.body;
    const sql = `SELECT * FROM users WHERE tai_khoan = '${tai_khoan}'`;
    con.query(sql, async function (err, result) {
      if (err) throw err;
      if (result.length == 1) {
        const st =
          Number.parseInt(result[0].so_tien) - Number.parseInt(so_tien);
        if (st >= 0) {
          var sql = `UPDATE users SET so_tien = '${st}' WHERE tai_khoan = '${tai_khoan}'`;
          con.query(sql, function (err, result) {
            if (err) throw err;
            res.json(result);
          });
        } else {
          res.json(false);
        }
      } else {
        res.json(false);
      }
    });
  }
}
module.exports = new user();
