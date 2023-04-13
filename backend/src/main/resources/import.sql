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

INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3610', 'Antonio Carlos', 'antonio@gmail.com', '966666666', '1987-12-13', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (id, name, email, phone, birth_date, password) VALUES ('8ddb099e-e17d-4dbb-9fc5-917b3b5f3611', 'Maria Clara', 'maria@gmail.com', '966666666', '1993-09-13', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');


INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08',TIMESTAMP WITH TIME ZONE '2022-07-29T15:50:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3611');
INSERT INTO tb_order (id, sale_date, client_id) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', TIMESTAMP WITH TIME ZONE '2022-08-03T14:20:00Z', '8ddb099e-e17d-4dbb-9fc5-917b3b5f3610');

INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 2, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b07', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 3, 5000.5, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b08', '6519d20f-e027-4df1-bcf4-e09a34e14b05', 4, 1700.0, 5);
INSERT INTO tb_order_product (order_id, product_id, quantity, price, tax) VALUES ('6519d20f-e027-4df1-bcf4-e09a34e14b09', '6519d20f-e027-4df1-bcf4-e09a34e14b06', 1, 5000.5, 5);