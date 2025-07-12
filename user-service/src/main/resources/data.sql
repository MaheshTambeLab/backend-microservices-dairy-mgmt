-- Insert base users (customers and vendors)
-- Password is 'password' encrypted with BCrypt
INSERT INTO users (user_id, name, email, phone, address, password, user_type)
VALUES
    -- Customers
    ('550e8400-e29b-41d4-a716-446655440001', 'John Doe', 'john.doe@example.com', '9876543210', '123 Main St, City', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'CUSTOMER'),
    ('550e8400-e29b-41d4-a716-446655440002', 'Jane Smith', 'jane.smith@example.com', '9876543211', '456 Oak Ave, Town', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'CUSTOMER'),

    -- Vendors
    ('650e8400-e29b-41d4-a716-446655440001', 'Fresh Dairy Farms', 'fresh.dairy@example.com', '9876543212', '789 Farm Rd, Country', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'VENDOR'),
    ('650e8400-e29b-41d4-a716-446655440002', 'Happy Cow Dairy', 'happy.cow@example.com', '9876543213', '321 Pasture Ln, Village', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'VENDOR');

-- Insert customer-specific data
INSERT INTO customers (user_id)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001'),
    ('550e8400-e29b-41d4-a716-446655440002');

-- Insert vendor-specific data
INSERT INTO vendors (user_id, farm_name, farm_address, bank_account_number, ifsc_code, gst_number)
VALUES
    ('650e8400-e29b-41d4-a716-446655440001', 'Fresh Dairy Farms', '789 Farm Rd, Country', '12345678901234', 'HDFC0001234', '22AAAAA0000A1Z5'),
    ('650e8400-e29b-41d4-a716-446655440002', 'Happy Cow Dairy', '321 Pasture Ln, Village', '56789012345678', 'ICIC0005678', '33BBBBB0000B2Z5');

-- Insert product references for vendors
-- Assuming these product IDs match the ones in product-service
INSERT INTO vendor_product_ids (vendor_user_id, product_ids)
VALUES
    ('650e8400-e29b-41d4-a716-446655440001', 1), -- Amrakhand
    ('650e8400-e29b-41d4-a716-446655440001', 2), -- Badam Pista
    ('650e8400-e29b-41d4-a716-446655440001', 3), -- Kesar Eliachi
    ('650e8400-e29b-41d4-a716-446655440001', 4), -- Paneer
    ('650e8400-e29b-41d4-a716-446655440002', 7), -- Ghee
    ('650e8400-e29b-41d4-a716-446655440002', 8), -- Dahi
    ('650e8400-e29b-41d4-a716-446655440002', 9); -- Lassi

-- Insert subscriptions for customers
-- Note: Dates are in YYYY-MM-DD format
INSERT INTO subscriptions (subscription_id, customer_id, product_id, quantity, frequency, start_date, next_delivery_date, is_active)
VALUES
    ('sub-001', '550e8400-e29b-41d4-a716-446655440001', 1, 2, 'WEEKLY', '2025-07-01', '2025-07-15', true),  -- Weekly Amrakhand
    ('sub-002', '550e8400-e29b-41d4-a716-446655440001', 4, 1, 'DAILY', '2025-07-10', '2025-07-13', true),   -- Daily Paneer
    ('sub-003', '550e8400-e29b-41d4-a716-446655440002', 7, 1, 'WEEKLY', '2025-06-15', '2025-07-16', true),  -- Weekly Ghee
    ('sub-004', '550e8400-e29b-41d4-a716-446655440002', 8, 2, 'DAILY', '2025-07-05', '2025-07-13', true);  -- Daily Dahi
