-- Seed data for initial setup

-- Insert gender data
INSERT INTO gender (gender) VALUES ('MALE');
INSERT INTO gender (gender) VALUES ('FEMALE');
INSERT INTO gender (gender) VALUES ('TRANSGENDER');

-- Insert status data
INSERT INTO status (status) VALUES ('ACTIVE');
INSERT INTO status (status) VALUES ('INACTIVE');
INSERT INTO status (status) VALUES ('PENDING');
INSERT INTO status (status) VALUES ('DRAFT');

-- Insert authority data
INSERT INTO authority (authority) VALUES ('CREATE');
INSERT INTO authority (authority) VALUES ('READ');
INSERT INTO authority (authority) VALUES ('DELETE');
INSERT INTO authority (authority) VALUES ('UPDATE');
INSERT INTO authority (authority) VALUES ('ALL');
INSERT INTO authority (authority) VALUES ('ALL_WITH_REVIEW');
