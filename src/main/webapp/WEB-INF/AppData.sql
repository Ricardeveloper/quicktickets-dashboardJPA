-- Create permissions

CALL createPermission('PERM_CREATE_ACCOUNTS');
CALL createPermission('PERM_READ_ACCOUNTS');
CALL createPermission('PERM_UPDATE_ACCOUNTS');
CALL createPermission('PERM_DELETE_ACCOUNTS');
CALL createPermission('PERM_ADMIN_ACCOUNTS');

-- Create authorities

CALL createAuthority('AUTHORITY_USER', @authority_user);
CALL authorityHasPermission(@authority_user, 'PERM_READ_ACCOUNTS');

CALL createAuthority('AUTHORITY_ADMIN', @authority_admin);
CALL authorityHasPermission(@authority_admin, 'PERM_UPDATE_ACCOUNTS');
CALL authorityHasPermission(@authority_admin, 'PERM_DELETE_ACCOUNTS');
CALL authorityHasPermission(@authority_admin, 'PERM_ADMIN_ACCOUNTS');

CALL createAuthority('AUTHORITY_GUEST', @authority_guest);

-- Create accounts

CALL createAccount('paula', '$2a$12$hpLb3X.eR0S3uznzQb1uWetDql.k4RjOqH/XLPAEhIx/DHNWae.1q', 'Paula', 'Cazares',
                   'paula@example.com', '2015-06-18 10:34:09', @paula);
CALL accountHasAuthority(@paula, @authority_user);

CALL createAccount('daniel', '$2a$12$i/V9YY9vik3TdJuEn3q0r.nOLvmA1I8CDZkColtzH.hu.Ws1CpMjC', 'Daniel', 'Cazares',
                   'daniel@example.com', '2015-06-18 10:34:09', @daniel);
CALL accountHasAuthority(@daniel, @authority_guest);

CALL createAccount('julia', '$2a$12$azNYZq3pRh52G2BNEroKiefqi8msJcgHOE5vbeo7pUVY93ZtJUV2m', 'Julia', 'Cazares',
                   'julia@example.com', '2015-06-18 10:34:09', @julia);
CALL accountHasAuthority(@julia, @authority_user);
CALL accountHasAuthority(@julia, @authority_guest);

CALL createAccount('juan', '$2a$12$1geczus0gx0vdNIA5N98kuHsVVC6lH1ppGYZAVCEOn.r1MzbkcXre', 'Juan', 'Cazares',
                   'juan@example.com', '2015-06-18 10:34:09', @juan);
CALL accountHasAuthority(@juan, @authority_user);
CALL accountHasAuthority(@juan, @authority_admin);

INSERT INTO `account_attempts` (`account_id`, `attempts`, `lastModified`) VALUES ('2', '1', '2014-05-15 00:00:00');