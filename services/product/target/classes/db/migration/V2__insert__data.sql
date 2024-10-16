-- Inserción de datos de prueba en la tabla 'category'
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics devices and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Books from different genres and authors', 'Books'),
    (nextval('category_seq'), 'Clothes for men and women', 'Clothing'),
    (nextval('category_seq'), 'Furniture for home and office', 'Furniture');

-- Inserción de datos de prueba en la tabla 'product'
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 100, 399.99, 1),
    (nextval('product_seq'), 'Laptop with Intel i7 processor and 16GB RAM', 'Laptop', 50, 999.99, 1),
    (nextval('product_seq'), 'Fiction book by J.K. Rowling', 'Fiction Book', 200, 19.99, 2),
    (nextval('product_seq'), 'Historical non-fiction book', 'History Book', 120, 24.99, 2),
    (nextval('product_seq'), 'Men''s casual shirt', 'Casual Shirt', 150, 29.99, 3),
    (nextval('product_seq'), 'Women''s jeans', 'Jeans', 80, 49.99, 3),
    (nextval('product_seq'), 'Ergonomic office chair', 'Office Chair', 40, 199.99, 4),
    (nextval('product_seq'), 'Wooden dining table for 4', 'Dining Table', 10, 299.99, 4);
