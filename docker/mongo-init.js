// var db = connect("mongodb://admin:14292@127.0.0.1:27017/admin");
var db = connect("mongodb://localhost:27017");

db =
  db.getSiblingDB(
    "seriesdevmongo"
  ); /* 'use' statement doesn't support here to switch db */

db.createUser(
    {
        user: "usr-series",
        pwd: "pwd-series",
        roles: [
            {
                role: "readWrite",
                db: "seriesdevmongo"
            }
        ]
    }
);