CREATE DATABASE IF NOT EXISTS login
DEFAULT CHARSET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE login;

CREATE TABLE `usuario` (
  `id_usuario` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `telefone` char(11),
  `cep` char(8) NOT NULL,
  `numero_endereco` smallint unsigned NOT NULL,
  `email` varchar(50) NOT NULL,
  `hash_senha` char(128) NOT NULL,
  `tem_foto` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`),
  KEY `index_email` (`email`(10))
) ENGINE=InnoDB;

CREATE TABLE `foto_perfil` (
  `id_usuario` int unsigned NOT NULL,
  `foto` mediumblob,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_usuario_foto_perfil` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB;


delimiter $$
create trigger tr_tem_foto after insert on foto_perfil
for each row
    begin
        update usuario
        set tem_foto = 1
        where id_usuario = new.id_usuario;
    end $$
delimiter ;