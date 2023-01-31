db.getCollection("Orders").find({});

db.getCollection("Orders").createIndex({
    "idempotencyKey": 1});