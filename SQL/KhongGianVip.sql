create database KhongGianVipDaTa
use KhongGianVipData

create table kgvinformation(
id int(2) auto_increment primary key,
name_res nvarchar(250),
add_res text,
tell varchar(20),
email varchar(100),
logo varchar(50),
gioithieu text,
tuyendung text,

);

create table tb_imagestore(
id int(2) auto_increment primary key,
name_image nvarchar(250),
title varchar(200)
);

create table tb_slider(
id int(3) auto_increment primary key,
title nvarchar(200),
image_slider varchar(100)
);



create table tb_news(
id int(3) auto_increment primary key,
title varchar(250),
content text,
time_news varchar(50)
);

create table tb_danhmuc(
id int(3) auto_increment primary key,
title varchar(200),
sp_id int(3),
content_danhmuc text
);

create table tb_menu(
id_menu int(3) auto_increment primary key,
title_menu varchar(200),
sp_id_menu int(3),
type_menu int(1),
content_menu text
);

create table tb_product(
id_pr int(3) auto_increment primary key,
sp_pr int(3),
image_pr varchar(100),
price varchar(100),
title_pr varchar(200),
content_menu text
);

alter table kgvinformation add  tuyendung text