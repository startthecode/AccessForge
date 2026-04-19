CREATE SEQUENCE IF NOT EXISTS authority_id
    START WITH 1
    INCREMENT BY 5;

CREATE SEQUENCE IF NOT EXISTS branch_id
    START WITH 1
    INCREMENT BY 5;

CREATE SEQUENCE IF NOT EXISTS department_id
    START WITH 1
    INCREMENT BY 5;

CREATE SEQUENCE IF NOT EXISTS designation_id
    START WITH 1
    INCREMENT BY 5;

CREATE SEQUENCE IF NOT EXISTS user_profile
    START WITH 1
    INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS user_id
    START WITH 1
    INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS permissions_id
    START WITH 1
    INCREMENT BY 5;

CREATE TABLE IF NOT EXISTS authority (
    id BIGINT PRIMARY KEY DEFAULT nextval('authority_id'),
    authority VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS branch (
    id BIGINT PRIMARY KEY DEFAULT nextval('branch_id'),
    branch VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS department (
    id BIGINT PRIMARY KEY DEFAULT nextval('department_id'),
    department VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS designation (
    id BIGINT PRIMARY KEY DEFAULT nextval('designation_id'),
    designation VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usersProfile (
    id BIGINT PRIMARY KEY DEFAULT nextval('user_profile'),
    name VARCHAR(25) NOT NULL,
    lastname VARCHAR(25),
    bio VARCHAR(280) NOT NULL,
    branchID BIGINT,
    departmentID BIGINT,
    CONSTRAINT fk_users_profile_branch
        FOREIGN KEY (branchID) REFERENCES branch (id),
    CONSTRAINT fk_users_profile_department
        FOREIGN KEY (departmentID) REFERENCES department (id)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY DEFAULT nextval('user_id'),
    username VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    email VARCHAR(40) NOT NULL,
    accountLocked BOOLEAN NOT NULL DEFAULT FALSE,
    isSuperAdmin BOOLEAN NOT NULL DEFAULT FALSE,
    statusid BIGINT,
    userProfile BIGINT UNIQUE,
    CONSTRAINT fk_users_status
        FOREIGN KEY (statusid) REFERENCES status (id),
    CONSTRAINT fk_users_profile
        FOREIGN KEY (userProfile) REFERENCES usersProfile (id)
);

CREATE TABLE IF NOT EXISTS permissions (
    id BIGINT PRIMARY KEY DEFAULT nextval('permissions_id'),
    userProfileID BIGINT,
    department BIGINT,
    branch BIGINT,
    designation BIGINT,
    authority BIGINT NOT NULL,
    CONSTRAINT fk_permissions_user_profile
        FOREIGN KEY (userProfileID) REFERENCES usersProfile (id),
    CONSTRAINT fk_permissions_department
        FOREIGN KEY (department) REFERENCES department (id),
    CONSTRAINT fk_permissions_branch
        FOREIGN KEY (branch) REFERENCES branch (id),
    CONSTRAINT fk_permissions_designation
        FOREIGN KEY (designation) REFERENCES designation (id),
    CONSTRAINT fk_permissions_authority
        FOREIGN KEY (authority) REFERENCES authority (id)
);
