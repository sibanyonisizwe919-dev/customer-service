-- Test data for the customer table in customer_db.
--
-- Run from a terminal:
--   mysql -u <user> -p customer_db < src/main/resources/sql/seed_customers.sql
-- Or just paste the statements into the mysql client / Workbench.

USE customer_db;

START TRANSACTION;

INSERT INTO customer (name, surname, contact, email, address) VALUES
    ('Ada',     'Lovelace',   '+44 20 7946 0123', 'ada@example.com',     '221B Baker Street, London'),
    ('Alan',    'Turing',     '+44 20 7946 0456', 'alan@example.com',    '5 Bletchley Park, Milton Keynes'),
    ('Grace',   'Hopper',     '+1 203 555 0142',  'grace@example.com',   '308 Whitman St, New York'),
    ('Linus',   'Torvalds',   '+1 650 555 0199',  'linus@example.com',   '1 Infinity Loop, Cupertino'),
    ('Guido',   'van Rossum', '+1 650 555 0177',  'guido@example.com',   '1600 Amphitheatre Pkwy, Mountain View'),
    ('Kent',    'Beck',       '+1 801 555 0123',  'kent@example.com',    '100 Main St, Salt Lake City'),
    ('Dennis',  'Ritchie',    '+1 908 555 0144',  'dennis@example.com',  '600 Mountain Ave, Murray Hill'),
    ('Barbara', 'Liskov',     '+1 617 555 0123',  'barbara@example.com', '77 Massachusetts Ave, Cambridge'),
    ('Niklaus', 'Wirth',      '+41 44 555 0123', 'niklaus@example.com', 'ETH Zentrum, Zurich'),
    ('Edsger',  'Dijkstra',   '+31 30 555 0123',  'edsger@example.com',  '3584 BB Utrecht, Netherlands');

COMMIT;

-- Verify the insert:
SELECT id, name, surname, contact, email, address FROM customer ORDER BY id;
