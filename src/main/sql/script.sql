create database if not exists login
default charset utf8mb4
default collate utf8mb4_general_ci;

use login;

create table usuario(
    id_usuario int unsigned primary key auto_increment,
    nome varchar(50) not null,
    telefone char(11),
    cep char(8) not null,
    numero_endereco smallint unsigned not null,
    email varchar(50) not null unique,
    hash_senha char(64) not null
) engine innodb;

delimiter $$
create trigger tr_tem_foto after insert on foto_perfil
for each row
    begin
        update usuario
        set tem_foto = 1
        where id_usuario = new.id_usuario;
    end $$
delimiter ;