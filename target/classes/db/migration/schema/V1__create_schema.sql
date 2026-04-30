-- Sequences
CREATE SEQUENCE IF NOT EXISTS gender_ID         START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE IF NOT EXISTS status_id         START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE IF NOT EXISTS authority_id      START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE IF NOT EXISTS user_profile      START WITH 1 INCREMENT BY 10;
CREATE SEQUENCE IF NOT EXISTS user_id           START WITH 1 INCREMENT BY 10;
CREATE SEQUENCE IF NOT EXISTS permissions_id    START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE IF NOT EXISTS customModules_id  START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE IF NOT EXISTS children_modules  START WITH 1 INCREMENT BY 5;

-- Lookup tables
CREATE TABLE IF NOT EXISTS gender (
    id     BIGINT PRIMARY KEY DEFAULT nextval('gender_ID'),
    gender VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS status (
    id     BIGINT PRIMARY KEY DEFAULT nextval('status_id'),
    status VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authority (
    id        BIGINT PRIMARY KEY DEFAULT nextval('authority_id'),
    authority VARCHAR(255) NOT NULL UNIQUE
);

-- User profile (no branch/department/designation; gender only)
CREATE TABLE IF NOT EXISTS users_profile (
    id        BIGINT PRIMARY KEY DEFAULT nextval('user_profile'),
    name      VARCHAR(25) NOT NULL,
    lastname  VARCHAR(25),
    bio       VARCHAR(280),
    gender_id BIGINT,
    CONSTRAINT fk_users_profile_gender
        FOREIGN KEY (gender_id) REFERENCES gender (id)
);

-- Users
CREATE TABLE IF NOT EXISTS users (
    id              BIGINT PRIMARY KEY DEFAULT nextval('user_id'),
    username        VARCHAR(25)  NOT NULL UNIQUE,
    password        VARCHAR(150) NOT NULL,
    email           VARCHAR(40)  NOT NULL,
    account_locked  BOOLEAN      NOT NULL DEFAULT FALSE,
    is_super_admin  BOOLEAN      NOT NULL DEFAULT FALSE,
    statusid        BIGINT,
    user_profile    BIGINT UNIQUE,
    CONSTRAINT fk_users_status
        FOREIGN KEY (statusid)     REFERENCES status (id),
    CONSTRAINT fk_users_profile
        FOREIGN KEY (user_profile) REFERENCES users_profile (id)
);

-- Custom modules (replaces designation as the permission target)
CREATE TABLE IF NOT EXISTS custom_modules (
    id             BIGINT PRIMARY KEY DEFAULT nextval('customModules_id'),
    module_name    VARCHAR(255) NOT NULL UNIQUE,
    has_child      BOOLEAN      NOT NULL,
    created_at     TIMESTAMP,
    created_by_id  BIGINT,
    CONSTRAINT fk_custom_modules_created_by
        FOREIGN KEY (created_by_id) REFERENCES users (id)
);

-- Children of custom modules
CREATE TABLE IF NOT EXISTS module_childrens (
    id          BIGINT PRIMARY KEY DEFAULT nextval('children_modules'),
    created_at  TIMESTAMP,
    has_child   BOOLEAN NOT NULL,
    parent_id   BIGINT,
    user_id_id  BIGINT,
    CONSTRAINT fk_module_childrens_parent
        FOREIGN KEY (parent_id)  REFERENCES custom_modules (id),
    CONSTRAINT fk_module_childrens_user
        FOREIGN KEY (user_id_id) REFERENCES users (id)
);

-- Module assignment (links a user to a module / child)
CREATE TABLE IF NOT EXISTS module_assign (
    id           BIGINT PRIMARY KEY DEFAULT nextval('children_modules'),
    module_id_id BIGINT,
    child_id_id  BIGINT,
    user_id_id   BIGINT,
    CONSTRAINT fk_module_assign_module
        FOREIGN KEY (module_id_id) REFERENCES custom_modules  (id),
    CONSTRAINT fk_module_assign_child
        FOREIGN KEY (child_id_id)  REFERENCES module_childrens (id),
    CONSTRAINT fk_module_assign_user
        FOREIGN KEY (user_id_id)   REFERENCES users (id)
);

-- Permissions (now scoped to a custom module via the "designation" FK column)
CREATE TABLE IF NOT EXISTS permissions (
    id              BIGINT PRIMARY KEY DEFAULT nextval('permissions_id'),
    user_profile_id BIGINT,
    designation     BIGINT,
    authority       BIGINT NOT NULL,
    CONSTRAINT fk_permissions_user_profile
        FOREIGN KEY (user_profile_id) REFERENCES users_profile  (id),
    CONSTRAINT fk_permissions_module
        FOREIGN KEY (designation)     REFERENCES custom_modules (id),
    CONSTRAINT fk_permissions_authority
        FOREIGN KEY (authority)       REFERENCES authority      (id)
);
