BEGIN;

drop table if exists users cascade;
create table users (id bigserial primary key , name varchar(50));
insert into users (name)
values
    ('Dima'),
    ('Alena'),
    ('Karina'),
    ('Julia');

drop table if exists products cascade;
create table products (id bigserial primary key , title varchar(50), cost double precision);
insert into products (title, cost)
values
    ('tomato', 50),
    ('potato', 25),
    ('car', 1000000),
    ('tv', 1000);

drop table if exists orders cascade;
create table orders (id bigserial primary key ,
                     cost double precision, amount int,
                     user_id bigint,
                     product_id bigint,
                     order_date date,
                     foreign key (user_id) references users (id),
                     foreign key (product_id) references products (id));
insert into orders (cost, amount, user_id, product_id, order_date)
values
    (45, 5, 2, 1, CURRENT_DATE),
    (23, 1, 2, 2, CURRENT_DATE);

COMMIT;