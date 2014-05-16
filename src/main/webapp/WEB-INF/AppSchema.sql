DROP DATABASE IF EXISTS geapp;
CREATE DATABASE geapp;
USE geapp;


-- =====================================================================================================================
-- Tables
-- =====================================================================================================================

CREATE TABLE account (
  id                     INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username               VARCHAR(50)  NOT NULL,
  password               VARCHAR(100) NOT NULL,
  first_name             VARCHAR(50)  NOT NULL,
  last_name              VARCHAR(50)  NOT NULL,
  email                  VARCHAR(50)  NOT NULL,
  expiration_date        DATETIME     NOT NULL,
  credential_non_expired BOOLEAN      NOT NULL,
  account_non_locked     BOOLEAN      NOT NULL,
  enabled                BOOLEAN      NOT NULL,
  UNIQUE INDEX `idx_account_username` (username)
)
  ENGINE = InnoDb;

CREATE TABLE authority (
  id   SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)       NOT NULL
)
  ENGINE = InnoDb;

CREATE TABLE permission (
  id   SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)       NOT NULL
)
  ENGINE = InnoDb;

CREATE TABLE account_authority (
  id           INT UNSIGNED      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id   INT UNSIGNED      NOT NULL,
  authority_id SMALLINT UNSIGNED NOT NULL,
  FOREIGN KEY (account_id) REFERENCES account (id),
  FOREIGN KEY (authority_id) REFERENCES authority (id),
  UNIQUE INDEX idx_account_authority_id (account_id, authority_id)
)
  ENGINE = InnoDb;

CREATE TABLE authority_permission (
  id            SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  authority_id  SMALLINT UNSIGNED NOT NULL,
  permission_id SMALLINT UNSIGNED NOT NULL,
  FOREIGN KEY (authority_id) REFERENCES authority (id),
  FOREIGN KEY (permission_id) REFERENCES permission (id),
  UNIQUE INDEX idx_authority_permission_id (authority_id, permission_id)
)
  ENGINE = InnoDb;

CREATE TABLE account_attempts (
  account_id   INT UNSIGNED NOT NULL PRIMARY KEY,
  attempts INT UNSIGNED NOT NULL,
  lastModified datetime NOT NULL,
  FOREIGN KEY (account_id) REFERENCES account (id)
) ENGINE = InnoDb;

-- =====================================================================================================================
-- Procedures
-- =====================================================================================================================

DELIMITER //

CREATE PROCEDURE createPermission($name VARCHAR(50))
  BEGIN
    INSERT INTO permission (name) VALUES ($name);
  END //

CREATE PROCEDURE createAuthority($name VARCHAR(50), OUT $id SMALLINT)
  BEGIN
    INSERT INTO `authority` (name) VALUES ($name);
    SET $id := last_insert_id();
  END //

CREATE PROCEDURE `authorityHasPermission`($authority_id SMALLINT, $perm_name VARCHAR(50))
  BEGIN
    DECLARE _perm_id INT;
    SELECT
      id
    FROM permission
    WHERE name = $perm_name
    INTO _perm_id;
    INSERT INTO authority_permission (authority_id, permission_id) VALUES ($authority_id, _perm_id);
  END //

CREATE PROCEDURE createAccount($name      VARCHAR(50), $password VARCHAR(100), $first_name VARCHAR(50),
                               $last_name VARCHAR(50), $email VARCHAR(50), $expiration_date DATE,
  OUT                          $id        INT)
  BEGIN
    INSERT INTO account (username, password, first_name, last_name, email, expiration_date, credential_non_expired, account_non_locked, enabled)
    VALUES ($name, $password, $first_name, $last_name, $email, $expiration_date, 1, 1, 1);
    SET $id := last_insert_id();
  END //

CREATE PROCEDURE accountHasAuthority($account_id INT, $authority_id SMALLINT)
  BEGIN
    INSERT INTO account_authority (account_id, authority_id) VALUES ($account_id, $authority_id);
  END //

DELIMITER ;