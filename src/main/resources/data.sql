INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO authentification (email, password) VALUES ('admin@yopmail.com', '$2a$10$/InlHWrhQ81whl75svgW2OVfa73rX0Z/Hk3J.2Ow647wJWcR4eQTu');
INSERT INTO authentification (email, password) VALUES ('user@yopmail.com', '$2a$10$idrHENBOQp2im/KMrsbAgOQGjntVKk8jz67Yk9m4SbxghqOunFTUC');
INSERT INTO authentification_roles (authentification_id, role_id) VALUES (1, 1);
INSERT INTO authentification_roles (authentification_id, role_id) VALUES (2, 2);