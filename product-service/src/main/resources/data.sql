-- Insert main categories
INSERT INTO categories (id, name, description, parent_id)
VALUES (1, 'Yummy', 'Delicious dairy products', NULL),
       (2, 'Delicious', 'Tasty dairy treats', NULL),
       (3, 'Healthy', 'Healthy dairy options', NULL);

-- Insert subcategory for Yummy
INSERT INTO categories (id, name, description, parent_id)
VALUES (4, 'Shrikhand', 'Traditional Indian sweetened yogurt', 1);

-- Insert products for Shrikhand subcategory
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      subcategory_id, created_at, updated_at)
VALUES (1, 'Amrakhand', 'Mango flavored shrikhand', 199.00, 10.00, 100, 0, 1, 1, 4, NOW(), NOW()),
       (2, 'Badam Pista', 'Almond and pistachio shrikhand', 249.00, 5.00, 80, 0, 1, 1, 4, NOW(), NOW()),
       (3, 'Kesar Eliachi', 'Saffron and cardamom shrikhand', 229.00, 8.00, 90, 0, 1, 1, 4, NOW(), NOW());

-- Insert other Yummy products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      subcategory_id, created_at, updated_at)
VALUES (4, 'Paneer', 'Fresh cottage cheese', 300.00, 5.00, 200, 0, 1, 1, NULL, NOW(), NOW()),
       (5, 'Butter', 'Fresh white butter', 500.00, 5.00, 150, 0, 1, 1, NULL, NOW(), NOW()),
       (6, 'Cheese', 'Processed cheese', 350.00, 8.00, 120, 0, 1, 1, NULL, NOW(), NOW());

-- Insert Delicious category products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      subcategory_id, created_at, updated_at)
VALUES (7, 'Ghee', 'Clarified butter', 800.00, 5.00, 80, 0, 1, 2, NULL, NOW(), NOW()),
       (8, 'Dahi', 'Fresh curd', 60.00, 0.00, 300, 0, 1, 2, NULL, NOW(), NOW()),
       (9, 'Lassi', 'Sweet yogurt drink', 40.00, 0.00, 250, 0, 1, 2, NULL, NOW(), NOW()),
       (10, 'Chhas', 'Buttermilk', 30.00, 0.00, 200, 0, 1, 2, NULL, NOW(), NOW()),
       (11, 'Flavoured Milk', 'Various flavored milk', 30.00, 0.00, 180, 0, 1, 2, NULL, NOW(), NOW());

-- Insert Healthy category products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      subcategory_id, created_at, updated_at)
VALUES (12, 'Milk', 'Fresh cow milk', 50.00, 0.00, 500, 0, 1, 3, NULL, NOW(), NOW()),
       (13, 'Milk Powder', 'Dairy milk powder', 300.00, 5.00, 100, 0, 1, 3, NULL, NOW(), NOW()),
       (14, 'Fruit Pulp', 'Mixed fruit pulp', 150.00, 5.00, 120, 0, 1, 3, NULL, NOW(), NOW()),
       (15, 'Tetra Pack', 'Long life milk', 60.00, 0.00, 200, 0, 1, 3, NULL, NOW(), NOW());

-- Update sequence values to avoid conflicts with future inserts
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));