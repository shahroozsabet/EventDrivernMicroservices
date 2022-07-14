CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";


INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('7e1d106c-ad92-4f81-a68e-439d410839fa', 'app_user', 'Standard', 'User');
INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('a837e0c3-7750-454a-95c4-23e2f60d3d3f', 'app_admin', 'Admin', 'User');
INSERT INTO public.users(id, username, firstname, lastname)
VALUES ('91cafb70-889f-45d6-8cd7-3af9ffc26f87', 'app_super_user', 'Super', 'User');


insert into documents(id, document_id)
values ('c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 1);
insert into documents(id, document_id)
values ('f2b2d644-3a08-4acb-ae07-20569f6f2a01', 2);
insert into documents(id, document_id)
values ('90573d2b-9a5d-409e-bbb6-b94189709a19', 3);

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '7e1d106c-ad92-4f81-a68e-439d410839fa', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), 'a837e0c3-7750-454a-95c4-23e2f60d3d3f', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), 'a837e0c3-7750-454a-95c4-23e2f60d3d3f', 'f2b2d644-3a08-4acb-ae07-20569f6f2a01', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), 'a837e0c3-7750-454a-95c4-23e2f60d3d3f', '90573d2b-9a5d-409e-bbb6-b94189709a19', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '91cafb70-889f-45d6-8cd7-3af9ffc26f87', 'c1df7d01-4bd7-40b6-86da-7e2ffabf37f7', 'READ');


