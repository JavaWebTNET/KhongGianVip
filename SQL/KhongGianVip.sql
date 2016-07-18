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

create table tb_category(
id_category int(3) auto_increment primary key,
title varchar(200),
sp_id int(3),
content_category text
);


create table tb_dish(
id_dish int(3) auto_increment primary key,
image_dish varchar(100),
price varchar(100),
name_dish varchar(200),
content_menu text,
status_dish int(1) default '1'
);

create table tb_cate_dish(
id_dish int(3),
id_category int(3),
primary key(id_dish,id_category)
);

create table tb_sarvice(
id_sv int(3) auto_increment primary key,
title_sv varchar(255),
content_sv text
);

update tb_dish set status_dish=0 where id_dish=?
