-- Create permissions

call createPermission('PERM_CREATE_ACCOUNTS');
call createPermission('PERM_READ_ACCOUNTS');
call createPermission('PERM_UPDATE_ACCOUNTS');
call createPermission('PERM_DELETE_ACCOUNTS');
call createPermission('PERM_ADMIN_ACCOUNTS');

-- Create authorities

call createAuthority('AUTHORITY_USER', @authority_user);
call authorityHasPermission(@authority_user, 'PERM_READ_ACCOUNTS');

call createAuthority('AUTHORITY_ADMIN', @authority_admin);
call authorityHasPermission(@authority_admin, 'PERM_UPDATE_ACCOUNTS');
call authorityHasPermission(@authority_admin, 'PERM_DELETE_ACCOUNTS');
call authorityHasPermission(@authority_admin, 'PERM_ADMIN_ACCOUNTS');

call createAuthority('AUTHORITY_GUEST', @authority_guest);

-- Create accounts

call createAccount('paula', '$2a$12$hpLb3X.eR0S3uznzQb1uWetDql.k4RjOqH/XLPAEhIx/DHNWae.1q', 'Paula', 'Cazares', 'paula@example.com', @paula);
call accountHasAuthority(@paula, @authority_user);

call createAccount('daniel', '$2a$12$i/V9YY9vik3TdJuEn3q0r.nOLvmA1I8CDZkColtzH.hu.Ws1CpMjC', 'Daniel', 'Cazares', 'daniel@example.com', @daniel);
call accountHasAuthority(@daniel, @authority_guest);

call createAccount('julia', '$2a$12$azNYZq3pRh52G2BNEroKiefqi8msJcgHOE5vbeo7pUVY93ZtJUV2m', 'Julia', 'Cazares', 'julia@example.com', @julia);
call accountHasAuthority(@julia, @authority_user);
call accountHasAuthority(@julia, @authority_guest);

call createAccount('juan', '$2a$12$1geczus0gx0vdNIA5N98kuHsVVC6lH1ppGYZAVCEOn.r1MzbkcXre', 'Juan', 'Cazares', 'juan@example.com', @juan);
call accountHasAuthority(@juan, @authority_user);
call accountHasAuthority(@juan, @authority_admin);