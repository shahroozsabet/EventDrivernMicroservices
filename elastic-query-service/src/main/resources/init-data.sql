CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";


INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('f02e896e-f43d-4b54-b132-b1e0d6f02d62', 'app_user', 'Standard', 'User');
INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('1ad7dbbf-a621-4810-84eb-122a4e81ac6c', 'app_admin', 'Admin', 'User');
INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('3014f36f-028c-4b85-8b6a-c5c253b1d2d5', 'app_super_user', 'Super', 'User');


insert into documents(id, document_id)
values ('c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 1);
insert into documents(id, document_id)
values ('f2b2d644-3a08-4acb-ae07-20569f6f2a01', 2);
insert into documents(id, document_id)
values ('90573d2b-9a5d-409e-bbb6-b94189709a19', 3);

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), 'f02e896e-f43d-4b54-b132-b1e0d6f02d62', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '1ad7dbbf-a621-4810-84eb-122a4e81ac6c', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '1ad7dbbf-a621-4810-84eb-122a4e81ac6c', 'f2b2d644-3a08-4acb-ae07-20569f6f2a01', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '1ad7dbbf-a621-4810-84eb-122a4e81ac6c', '90573d2b-9a5d-409e-bbb6-b94189709a19', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '3014f36f-028c-4b85-8b6a-c5c253b1d2d5', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');


