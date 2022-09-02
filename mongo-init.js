dbAdmin = db.getSiblingDB("admin");
dbAdmin.createUser({
    user: "discount-user",
    pwd: "admin",
    roles: [{role: "userAdminAnyDatabase", db: "admin"}],
    mechanisms: ["SCRAM-SHA-1"],
});

dbAdmin.auth({
    user: "discount-user",
    pwd: "admin",
    mechanisms: ["SCRAM-SHA-1"],
    digestPassword: true,
});

db = new Mongo().getDB("discount-db");
db.createCollection("discount", {capped: false});
db.getCollection("discount").insertMany([
    {
        _id: 1,
        discount_uuid: "9e887dd4-b389-413f-9073-b07a51a7b203",
        discount_type: "Store employee discount",
        discount_percentage: 30
    },
    {
        _id: 2,
        discount_uuid: "47b549e9-46c9-4c9f-83ae-6a68a825433d",
        discount_type: "Store affiliate discount",
        discount_percentage: 10
    },
    {
        _id: 3,
        discount_uuid: "9af1f994-2361-4841-8721-c0cec7dc2ff9",
        discount_type: "Customer for over 2 years discount",
        discount_percentage: 5
    }
]);

db.createCollection("item", {capped: false});
db.getCollection("item").insertMany([
    {
        _id: 1,
        item_uuid: "34ccd357-04f0-4029-89a6-f37b48e216e3",
        item_description: "iPhone 13 Pro",
        is_grocery: false
    },
    {
        _id: 2,
        item_uuid: "44295f91-1b2a-4172-9f24-2b0eef64eb42",
        item_description: "Milk - 1L",
        is_grocery: true
    },
    {
        _id: 3,
        item_uuid: "ac4bd470-44c2-498e-aa69-b612afeed753",
        item_description: "Sofa",
        is_grocery: false
    }
]);