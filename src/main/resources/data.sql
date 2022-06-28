INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO users (username, password, email)
    VALUES ('admin', '$2a$10$LNYZhv4c3OywYPXHZXd.iOIjWi4XfDipg4tG65uv7wbIelftWq.ie', 'admin@admin.org');  --passwd: admin
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);