INSERT INTO servicebox.roles(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO servicebox.roles(id, name) VALUES (2, 'ROLE_EMPLOYEE');
INSERT INTO servicebox.roles(id, name) VALUES (3, 'ROLE_CLIENT');
--
INSERT INTO servicebox.users (id, username, password, enabled) VALUES (1, 'test', '{bcrypt}$2a$12$yglrSabJuXl072PDpLCDXeieEnGrvc25d.48g6Focb7Z7/CLWFpP2', true);
--
INSERT INTO servicebox.users_roles (user_id, role_id) VALUES (1, 2);