CREATE TABLE IF NOT EXISTS category(
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS product(
    id integer not null primary key,
    description varchar(255),
    name varchar(255),
    available_quantity double precision not null,
    price numeric(38,2),
    category_id integer
        constraint foreign_key references category
);


CREATE SEQUENCE IF NOT EXISTS category_seq increment by 1;
CREATE SEQUENCE IF NOT EXISTS product_seq increment by 1;
ALTER SEQUENCE category_seq RESTART WITH 1;
ALTER SEQUENCE product_seq RESTART WITH 1;
ALTER SEQUENCE category_seq INCREMENT BY 1;
ALTER SEQUENCE product_seq INCREMENT BY 1;
