DROP PROCEDURE IF EXISTS sp_insert_cli_usu;

DELIMITER ;;
CREATE PROCEDURE sp_insert_cli_usu(

p_log_usuario varchar(255),
p_pass_usuario varchar(255),
p_estado int,
p_cod_tipo_usuario int,
p_fec_registro datetime,
p_fec_modificacion datetime,
p_cod_usuario_registro int,
p_nom_cliente varchar(255),
p_ape_paterno varchar(100),
p_ape_materno varchar(100),
p_cod_tipo_documento int,
p_num_documento varchar(255),
p_cod_tipo_genero int,
p_cod_ubigeo char(9),
p_direccion varchar(300),
p_cel_1 varchar(50),
p_cel_2 varchar(50),
p_cuenta_facebook varchar(255),
p_cuenta_gmail varchar(255),
p_fecha_nacimiento date,
p_cod_tipo_canal_contacto int,
p_fec_modificacion_cli datetime,
p_fec_registro_cli datetime,
p_cod_usuario_registro_cli int,
p_estado_cli int
)
begin
declare p_des_name varchar(255);
declare out_id int;
DECLARE exit handler for sqlexception
  BEGIN
    -- ERROR
  ROLLBACK;
  set out_id = 0; 
END;

DECLARE exit handler for sqlwarning
 BEGIN
    -- WARNING
 ROLLBACK;
 set out_id = 0; 
END;


start transaction ;

	set p_des_name = concat(p_nom_cliente,' ',p_ape_paterno,' ',p_ape_materno);

insert into tb_usuario (des_usuario,log_usuario ,
pass_usuario ,
estado ,
cod_tipo_usuario ,
fec_registro ,
fec_modificacion ,
cod_usuario_registro )

values (p_des_name,p_log_usuario ,
p_pass_usuario ,
p_estado ,
p_cod_tipo_usuario ,
p_fec_registro ,
p_fec_modificacion ,
p_cod_usuario_registro);

 set out_id = LAST_INSERT_ID(); 

insert into tb_cliente  (
NOM_CLIENTE,
APE_PATERNO,
APE_MATERNO,
COD_TIPO_DOCUMENTO,
NUM_DOCUMENTO,
COD_TIPO_GENERO,
COD_UBIGEO,
DIRECCION,
CEL_1,
CEL_2,
COD_USUARIO,
CUENTA_FACEBOOK,
CUENTA_GMAIL,
FEC_NACIMIENTO,
COD_TIPO_CANAL_CONTACTO,
FEC_MODIFICACION,
FEC_REGISTRO,
COD_USUARIO_REGISTRO,
ESTADO
)
values

(
p_nom_cliente,
p_ape_paterno,
p_ape_materno,
p_cod_tipo_documento,
p_num_documento,
p_cod_tipo_genero,
p_cod_ubigeo,
p_direccion,
p_cel_1,
p_cel_2,
LAST_INSERT_ID(),
p_cuenta_facebook,
p_cuenta_gmail,
p_fecha_nacimiento,
p_cod_tipo_canal_contacto,
p_fec_modificacion_cli,
p_fec_registro_cli,
p_cod_usuario_registro_cli,
p_estado);

        COMMIT;
        
        select out_id;

END ;;
DELIMITER ;



/*SEGUNDO PROCEDURE */

DROP PROCEDURE IF EXISTS sp_insert_solicitud;


DELIMITER ;;
CREATE PROCEDURE sp_insert_solicitud(
									p_COD_CLIENTE	int,
									p_CORDENADAS_REGISTRO	varchar(300),
									p_CORDENADAS_UBICACION	varchar(300),
									p_COD_TIPO_AVERIA	int,
									p_COD_TIPO_PRIORIDAD	int,
									p_NOMBRE	varchar(300),
									p_EMAIL	varchar(300),
									p_TELEFONO	varchar(300),
									p_DESCRIPCION	varchar(300),
									p_ESTADO	int,
									p_PRECIO_PRESUPUESTO	decimal(18,2),
									p_PRECIO_FINAL	decimal(18,2),
									p_COD_TIPO_REGISTRO	int,
									p_FEC_REGISTRO	datetime,
									p_FEC_MODIFICACION	datetime,
									p_COD_USUARIO_REGISTRO	int,
									p_COD_UBIGEO	char(9),
									p_DIRECCION	varchar(300),
									p_COD_OFICIO	int
									)
begin

declare out_id int;


start transaction ;


insert into tb_solicitud_trabajo ( COD_CLIENTE,
CORDENADAS_REGISTRO,
CORDENADAS_UBICACION,
COD_TIPO_AVERIA,
COD_TIPO_PRIORIDAD,
NOMBRE,
EMAIL,
TELEFONO,
TITULO,
DESCRIPCION,
ESTADO,
PRECIO_PRESUPUESTO,
PRECIO_FINAL,
COD_TIPO_REGISTRO,
FEC_REGISTRO,
FEC_MODIFICACION,
COD_USUARIO_REGISTRO,
COD_UBIGEO,
DIRECCION,
FOTO,
COD_OFICIO )

values (
p_COD_CLIENTE,
p_CORDENADAS_REGISTRO,
p_CORDENADAS_UBICACION,
p_COD_TIPO_AVERIA,
p_COD_TIPO_PRIORIDAD,
p_NOMBRE,
p_EMAIL,
p_TELEFONO,
'-',
p_DESCRIPCION,
p_ESTADO,
p_PRECIO_PRESUPUESTO,
p_PRECIO_FINAL,
p_COD_TIPO_REGISTRO,
p_FEC_REGISTRO,
p_FEC_MODIFICACION,
p_COD_USUARIO_REGISTRO,
p_COD_UBIGEO,
p_DIRECCION,
null,
p_COD_OFICIO);

 set out_id = LAST_INSERT_ID(); 

        COMMIT;
        
        select out_id;

end ;;
DELIMITER ;
