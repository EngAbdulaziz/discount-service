// Create user
dbAdmin = db.getSiblingDB("admin");
dbAdmin.createUser({
    user: "discount-user",
    pwd: "admin",
    roles: [{role: "userAdminAnyDatabase", db: "admin"}],
    mechanisms: ["SCRAM-SHA-1"],
});

// Authenticate user
dbAdmin.auth({
    user: "discount-user",
    pwd: "admin",
    mechanisms: ["SCRAM-SHA-1"],
    digestPassword: true,
});

// Create DB and collection
db = new Mongo().getDB("discount-db");
db.createCollection("discount-collection", {capped: false});
db.getCollection("discount-collection").insertMany([
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