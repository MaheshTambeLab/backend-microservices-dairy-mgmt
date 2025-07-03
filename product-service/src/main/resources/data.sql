-- Insert main categories
INSERT INTO categories (id, name, description)
VALUES (1, 'Yummy', 'Delicious dairy products'),
       (2, 'Delicious', 'Tasty dairy treats'),
       (3, 'Healthy', 'Healthy dairy options');

-- Insert subcategory for Yummy
INSERT INTO categories (id, name, description, parent_id)
VALUES (4, 'Shrikhand', 'Traditional Indian sweetened yogurt', 1);

-- Insert products for Shrikhand subcategory
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      subcategory_id, created_at, updated_at)
VALUES (1, 'Amrakhand', 'Mango flavored shrikhand', 199.00, 10, 100, 0, 1, 1, 4, NOW(), NOW()),
       (2, 'Badam Pista', 'Almond and pistachio shrikhand', 249.00, 5, 80, 0, 1, 1, 4, NOW(), NOW()),
       (3, 'Kesar Eliachi', 'Saffron and cardamom shrikhand', 229.00, 8, 90, 0, 1, 1, 4, NOW(), NOW());

-- Insert other Yummy products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      created_at, updated_at)
VALUES (4, 'Paneer', 'Fresh cottage cheese', 300.00, 5, 200, 0, 1, 1, NOW(), NOW()),
       (5, 'Butter', 'Fresh white butter', 500.00, 5, 150, 0, 1, 1, NOW(), NOW()),
       (6, 'Cheese', 'Processed cheese', 350.00, 8, 120, 0, 1, 1, NOW(), NOW());

-- Insert Delicious category products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      created_at, updated_at)
VALUES (7, 'Ghee', 'Clarified butter', 800.00, 5, 80, 0, 1, 2, NOW(), NOW()),
       (8, 'Dahi', 'Fresh curd', 60.00, 0, 300, 0, 1, 2, NOW(), NOW()),
       (9, 'Lassi', 'Sweet yogurt drink', 40.00, 0, 250, 0, 1, 2, NOW(), NOW()),
       (10, 'Chhas', 'Buttermilk', 30.00, 0, 200, 0, 1, 2, NOW(), NOW()),
       (11, 'Flavoured Milk', 'Various flavored milk', 30.00, 0, 180, 0, 1, 2, NOW(), NOW());

-- Insert Healthy category products
INSERT INTO products (id, name, description, price, discount, weekly_quantity, used_quantity, vendor_id, category_id,
                      created_at, updated_at)
VALUES (12, 'Milk', 'Fresh cow milk', 50.00, 0, 500, 0, 1, 3, NOW(), NOW()),
       (13, 'Milk Powder', 'Dairy milk powder', 300.00, 5, 100, 0, 1, 3, NOW(), NOW()),
       (14, 'Fruit Pulp', 'Mixed fruit pulp', 150.00, 5, 120, 0, 1, 3, NOW(), NOW()),
       (15, 'Tetra Pack', 'Long life milk', 60.00, 0, 200, 0, 1, 3, NOW(), NOW());

-- Update sequence values to avoid conflicts with future inserts
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));