CREATE TABLE role_user
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE role_user
    ADD CONSTRAINT fk_role_user_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE role_user
    ADD CONSTRAINT fk_role_user_on_user FOREIGN KEY (user_id) REFERENCES user (id);