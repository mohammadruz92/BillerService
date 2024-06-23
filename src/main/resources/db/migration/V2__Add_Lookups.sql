INSERT INTO biller.roles (id, name) VALUES(1, 'ROLE_ADMIN');
INSERT INTO biller.roles (id, name) VALUES(2, 'ROLE_MODERATOR');
INSERT INTO biller.roles (id, name) VALUES(3, 'ROLE_USER');

INSERT INTO biller.users (id, username, email, password) VALUES(1, 'SuperAdmin', 'superadminbiller@blom.com', '$2a$10$jLLwra1R2MMR2tXTMqyD0Onrj2jrNdZH4wmeSD4zXRR0iNQqHsmbW');
-- password is "SsPIJBjHf1S5SIo"
INSERT INTO biller.user_roles (role_id, user_id) VALUES(1, 1);
INSERT INTO biller.user_roles (role_id, user_id) VALUES(2, 1);