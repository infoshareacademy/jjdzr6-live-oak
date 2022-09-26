INSERT INTO servicebox.role(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO servicebox.role(id, name) VALUES (2, 'ROLE_EMPLOYEE');
INSERT INTO servicebox.role(id, name) VALUES (3, 'ROLE_CLIENT');
--
INSERT INTO servicebox.user (id, username, password, enabled) VALUES (1, 'test', '{bcrypt}$2a$12$yglrSabJuXl072PDpLCDXeieEnGrvc25d.48g6Focb7Z7/CLWFpP2', true);
--
INSERT INTO servicebox.user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO servicebox.address (city, flat_number, house_number, street, zip_code) VALUES ('Poznań', null, '10', 'Poznańska', '60-685');

INSERT INTO servicebox.client (notifications, email, name, nip, phone, address_id, user_id) VALUES (DEFAULT, 'test@wp.pl', 'Jan', null, '603654848', 1, null);

INSERT INTO servicebox.vehicle (id, engine_capacity, make, mileage, model, plate_number, production_year, vin, client_id) VALUES (1, 2, 'Opel', 85000, 'Astra', 'PO12345', 2010, 'DO94KEW04RT', 1);

INSERT INTO servicebox.vehicle (id, engine_capacity, make, mileage, model, plate_number, production_year, vin, client_id) VALUES (2, 1.2, 'Fiat', null, 'Bravo', 'WD456765', null, null, 1);

INSERT INTO servicebox.service_order (created_at, description, finished_at, max_cost, new_parts, order_number, state, repair_card_id, vehicle_id) VALUES (DEFAULT, 'Wymiana oleju', null, 200, DEFAULT, '1/2022', 'CREATED', null, 2);