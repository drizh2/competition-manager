INSERT INTO users (id, username, password)
VALUES (0, 'admin', '$2a$08$6JTn.PBEXeMQG1cTeMZnSO0Bim6NA9Q8YwNw5D2kVU8ceFnGpPeje');

INSERT INTO user_role (user_id, roles)
VALUES (0, 'ROLE_ADMIN'), (0, 'ROLE_USER');