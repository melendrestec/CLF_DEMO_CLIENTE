-- Creamos BD
create database CL3;
use CL3;

-- Creamos Tablas
create table tb_cliente
(
IdCliente int(11) NOT NULL AUTO_INCREMENT,
nombre varchar(50) DEFAULT NULL,
apellido varchar(50) DEFAULT NULL,
dni char(8) DEFAULT NULL,
edad int(11) DEFAULT NULL,
PRIMARY KEY (IdCliente)
);

-- Creamos procedure Insertar
DELIMITER $$
create procedure sp_listaCliente(edad1 int(11), edad2 int(11))
BEGIN
select * from tb_cliente where edad>=edad1 && edad<=edad2;
END$$
DELIMITER ;

-- Creamos procedure Listar
DELIMITER $$
create procedure sp_saveCliente(IN nom varchar(50),IN ape varchar(50),IN dni char(8), IN age int(11) )
BEGIN
insert into tb_cliente values(null,nom,ape,dni,age);
END$$
DELIMITER ;



-- Procedures
call sp_saveCliente('Carlos','Lazaro','65789438',27);
call sp_saveCliente('Angel','Castro','25357898',25);
call sp_saveCliente('Miguel','Pozo','12345678',30);
call sp_saveCliente('Sara','Lozoo','90876548',17);
call sp_saveCliente('Carlos','Lazaro','34256788',18);
call sp_saveCliente('aaa','aaa','34256788',18);
call sp_saveCliente('prueba','prueba','34256788',17);
call sp_saveCliente('test4','test4','34256788',19);

select * from tb_cliente;
call sp_listaCliente(20,30);

