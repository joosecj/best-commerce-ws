create table tb_category_product (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_category_store (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_order (id uuid not null, sale_date TIMESTAMP WITHOUT TIME ZONE, client_id uuid, primary key (id));
create table tb_order_product (price float(53), quantity integer, tax integer, product_id uuid not null, order_id uuid not null, primary key (order_id, product_id));
create table tb_permission (id uuid not null, description varchar(255) not null, primary key (id));
create table tb_product (id uuid not null, description TEXT not null, img_url varchar(255) not null, name varchar(20) not null, price float(53) not null, store_id uuid, primary key (id));
create table tb_product_category (product_id uuid not null, category_id uuid not null, primary key (product_id, category_id));
create table tb_store (id uuid not null, name varchar(20) not null, category_store_id uuid, primary key (id));
create table tb_user (id uuid not null, account_non_expired boolean, account_non_locked boolean, birth_date date not null, credentials_non_expired boolean, email varchar(255), enabled boolean, name varchar(255) not null, password varchar(255), phone varchar(255) not null, primary key (id));
create table tb_user_permission (id_user uuid not null, id_permission uuid not null);
alter table if exists tb_category_product add constraint UK_bvikuhlr3h1wuspwg9ghb9bta unique (description);
alter table if exists tb_category_product add constraint UK_e8onfpcs6m91x1l0qpi5sqrk6 unique (name);
alter table if exists tb_category_store add constraint UK_5suwgketqmmxkvv03kjyimenf unique (description);
alter table if exists tb_category_store add constraint UK_sfuwp61vb12m7quoskiceaxyt unique (name);
alter table if exists tb_permission add constraint UK_9pk4xmv8pe5908e3kw0m09coe unique (description);
alter table if exists tb_product add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);
alter table if exists tb_store add constraint UK_939tehc5nrgiv9jlilbj8a91j unique (name);
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table if exists tb_order add constraint FKi0x0rv7d65vsceuy33km9567n foreign key (client_id) references tb_user;
alter table if exists tb_order_product add constraint FKsu03ywlcvyqg5y78qey2q25lc foreign key (product_id) references tb_product;
alter table if exists tb_order_product add constraint FK40anaevs16kmc2tbh7wc511fq foreign key (order_id) references tb_order;
alter table if exists tb_product add constraint FKkdj7nfjpppdti98b4qj33odkm foreign key (store_id) references tb_store;
alter table if exists tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product;
alter table if exists tb_user_permission add constraint FKqsxlawmvr4dhvmhyhjmjjj3rt foreign key (id_permission) references tb_permission;
alter table if exists tb_user_permission add constraint FKt719rbex1tr7l6pwqytxmsutv foreign key (id_user) references tb_user;
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3601', 'Celular', 'celular geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3602', 'Tech', 'tech geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3603', 'Informatica', 'info geral', 0);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b01', 'Tecnologia', 'tech geral', 1);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b02', 'Informatica', 'informatica geral', 1);
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b03', 'TECH APP', '6519d20f-e027-4df1-bcf4-e09a34e14b01');
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b04', 'COMP TEC', '6519d20f-e027-4df1-bcf4-e09a34e14b02');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', 'PC Gamer', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 1700.0,  'https://imgs.casasbahia.com.br/1500726908/1xg.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b04');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', 'iPhone', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 5000.0,  'https://imgs.ponto.com.br/55048758/1g.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b03');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3603');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3601');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3602');
INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', 'Antonio Carlos', 'antonio@gmail.com', '966666666', '1987-12-13', 'a908c92848c92c7aab42ea2cabddad6d024f20d3d784121175a86d765b4696e7c08dbe0beb374239');
INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3611', 'Maria Clara', 'maria@gmail.com', '966666666', '1993-09-13', 'b880839d60e9793b7c888d7121007529b6dad53284c1a34897b81f23871d4a535179ba7378ee8a97');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08',TIMESTAMP WITH TIME ZONE '2022-07-29T15:50:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', TIMESTAMP WITH TIME ZONE '2022-08-03T14:20:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 2, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 3, 5000.5, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 4, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 1, 5000.5, 5);
create table tb_category_product (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_category_store (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_order (id uuid not null, sale_date TIMESTAMP WITHOUT TIME ZONE, client_id uuid, primary key (id));
create table tb_order_product (price float(53), quantity integer, tax integer, product_id uuid not null, order_id uuid not null, primary key (order_id, product_id));
create table tb_permission (id uuid not null, description varchar(255) not null, primary key (id));
create table tb_product (id uuid not null, description TEXT not null, img_url varchar(255) not null, name varchar(20) not null, price float(53) not null, store_id uuid, primary key (id));
create table tb_product_category (product_id uuid not null, category_id uuid not null, primary key (product_id, category_id));
create table tb_store (id uuid not null, name varchar(20) not null, category_store_id uuid, primary key (id));
create table tb_user (id uuid not null, account_non_expired boolean, account_non_locked boolean, birth_date date not null, credentials_non_expired boolean, email varchar(255), enabled boolean, name varchar(255) not null, password varchar(255), phone varchar(255) not null, primary key (id));
create table tb_user_permission (id_user uuid not null, id_permission uuid not null);
alter table if exists tb_category_product add constraint UK_bvikuhlr3h1wuspwg9ghb9bta unique (description);
alter table if exists tb_category_product add constraint UK_e8onfpcs6m91x1l0qpi5sqrk6 unique (name);
alter table if exists tb_category_store add constraint UK_5suwgketqmmxkvv03kjyimenf unique (description);
alter table if exists tb_category_store add constraint UK_sfuwp61vb12m7quoskiceaxyt unique (name);
alter table if exists tb_permission add constraint UK_9pk4xmv8pe5908e3kw0m09coe unique (description);
alter table if exists tb_product add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);
alter table if exists tb_store add constraint UK_939tehc5nrgiv9jlilbj8a91j unique (name);
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table if exists tb_order add constraint FKi0x0rv7d65vsceuy33km9567n foreign key (client_id) references tb_user;
alter table if exists tb_order_product add constraint FKsu03ywlcvyqg5y78qey2q25lc foreign key (product_id) references tb_product;
alter table if exists tb_order_product add constraint FK40anaevs16kmc2tbh7wc511fq foreign key (order_id) references tb_order;
alter table if exists tb_product add constraint FKkdj7nfjpppdti98b4qj33odkm foreign key (store_id) references tb_store;
alter table if exists tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product;
alter table if exists tb_user_permission add constraint FKqsxlawmvr4dhvmhyhjmjjj3rt foreign key (id_permission) references tb_permission;
alter table if exists tb_user_permission add constraint FKt719rbex1tr7l6pwqytxmsutv foreign key (id_user) references tb_user;
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3601', 'Celular', 'celular geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3602', 'Tech', 'tech geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3603', 'Informatica', 'info geral', 0);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b01', 'Tecnologia', 'tech geral', 1);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b02', 'Informatica', 'informatica geral', 1);
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b03', 'TECH APP', '6519d20f-e027-4df1-bcf4-e09a34e14b01');
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b04', 'COMP TEC', '6519d20f-e027-4df1-bcf4-e09a34e14b02');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', 'PC Gamer', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 1700.0,  'https://imgs.casasbahia.com.br/1500726908/1xg.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b04');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', 'iPhone', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 5000.0,  'https://imgs.ponto.com.br/55048758/1g.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b03');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3603');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3601');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3602');
INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', 'Antonio Carlos', 'antonio@gmail.com', '966666666', '1987-12-13', 'a908c92848c92c7aab42ea2cabddad6d024f20d3d784121175a86d765b4696e7c08dbe0beb374239');
INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3611', 'Maria Clara', 'maria@gmail.com', '966666666', '1993-09-13', 'b880839d60e9793b7c888d7121007529b6dad53284c1a34897b81f23871d4a535179ba7378ee8a97');
INSERT INTO tb_permission (id, description) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3640', 'ADMIN');
INSERT INTO tb_permission (id, description) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3641', 'CLIENT');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3640');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3641');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08',TIMESTAMP WITH TIME ZONE '2022-07-29T15:50:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', TIMESTAMP WITH TIME ZONE '2022-08-03T14:20:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 2, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 3, 5000.5, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 4, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 1, 5000.5, 5);
create table tb_category_product (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_category_store (id uuid not null, description varchar(20) not null, name varchar(20) not null, type smallint not null, primary key (id));
create table tb_order (id uuid not null, sale_date TIMESTAMP WITHOUT TIME ZONE, client_id uuid, primary key (id));
create table tb_order_product (price float(53), quantity integer, tax integer, product_id uuid not null, order_id uuid not null, primary key (order_id, product_id));
create table tb_permission (id uuid not null, description varchar(255) not null, primary key (id));
create table tb_product (id uuid not null, description TEXT not null, img_url varchar(255) not null, name varchar(20) not null, price float(53) not null, store_id uuid, primary key (id));
create table tb_product_category (product_id uuid not null, category_id uuid not null, primary key (product_id, category_id));
create table tb_store (id uuid not null, name varchar(20) not null, category_store_id uuid, primary key (id));
create table tb_user (id uuid not null, account_non_expired boolean, account_non_locked boolean, birth_date date not null, credentials_non_expired boolean, email varchar(255), enabled boolean, name varchar(255) not null, password varchar(255), phone varchar(255) not null, primary key (id));
create table tb_user_permission (id_user uuid not null, id_permission uuid not null);
alter table if exists tb_category_product add constraint UK_bvikuhlr3h1wuspwg9ghb9bta unique (description);
alter table if exists tb_category_product add constraint UK_e8onfpcs6m91x1l0qpi5sqrk6 unique (name);
alter table if exists tb_category_store add constraint UK_5suwgketqmmxkvv03kjyimenf unique (description);
alter table if exists tb_category_store add constraint UK_sfuwp61vb12m7quoskiceaxyt unique (name);
alter table if exists tb_permission add constraint UK_9pk4xmv8pe5908e3kw0m09coe unique (description);
alter table if exists tb_product add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);
alter table if exists tb_store add constraint UK_939tehc5nrgiv9jlilbj8a91j unique (name);
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table if exists tb_order add constraint FKi0x0rv7d65vsceuy33km9567n foreign key (client_id) references tb_user;
alter table if exists tb_order_product add constraint FKsu03ywlcvyqg5y78qey2q25lc foreign key (product_id) references tb_product;
alter table if exists tb_order_product add constraint FK40anaevs16kmc2tbh7wc511fq foreign key (order_id) references tb_order;
alter table if exists tb_product add constraint FKkdj7nfjpppdti98b4qj33odkm foreign key (store_id) references tb_store;
alter table if exists tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product;
alter table if exists tb_user_permission add constraint FKqsxlawmvr4dhvmhyhjmjjj3rt foreign key (id_permission) references tb_permission;
alter table if exists tb_user_permission add constraint FKt719rbex1tr7l6pwqytxmsutv foreign key (id_user) references tb_user;
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3601', 'Celular', 'celular geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3602', 'Tech', 'tech geral', 0);
INSERT INTO tb_category_product(id, name, description, type) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3603', 'Informatica', 'info geral', 0);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b01', 'Tecnologia', 'tech geral', 1);
INSERT INTO tb_category_store(id, name, description, type) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b02', 'Informatica', 'informatica geral', 1);
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b03', 'TECH APP', '6519d20f-e027-4df1-bcf4-e09a34e14b01');
INSERT INTO tb_store(id, name, category_store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b04', 'COMP TEC', '6519d20f-e027-4df1-bcf4-e09a34e14b02');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', 'PC Gamer', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 1700.0,  'https://imgs.casasbahia.com.br/1500726908/1xg.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b04');
INSERT INTO tb_product(id, name, description, price, img_url, store_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', 'iPhone', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua', 5000.0,  'https://imgs.ponto.com.br/55048758/1g.jpg', '6519d20f-e027-4df1-bcf4-e09a34e14b03');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b05', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3603');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3601');
INSERT INTO tb_product_category (product_id, category_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b06', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3602');
INSERT INTO tb_user (id, name, email, phone, birth_date, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', 'Antonio Carlos', 'antonio@gmail.com', '966666666', '1987-12-13','a908c92848c92c7aab42ea2cabddad6d024f20d3d784121175a86d765b4696e7c08dbe0beb374239', 1, 1, 1, 1);
INSERT INTO tb_user (id, name, email, phone, birth_date, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3611', 'Maria Clara', 'maria@gmail.com', '966666666', '1993-09-13', 'b880839d60e9793b7c888d7121007529b6dad53284c1a34897b81f23871d4a535179ba7378ee8a97',  1, 1, 1, 1);
INSERT INTO tb_permission (id, description) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3640', 'ADMIN');
INSERT INTO tb_permission (id, description) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3641', 'CLIENT');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3640');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3641');
INSERT INTO tb_user_permission (id_user, id_permission) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3611', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08',TIMESTAMP WITH TIME ZONE '2022-07-29T15:50:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', TIMESTAMP WITH TIME ZONE '2022-08-03T14:20:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 2, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 3, 5000.5, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 4, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 1, 5000.5, 5);