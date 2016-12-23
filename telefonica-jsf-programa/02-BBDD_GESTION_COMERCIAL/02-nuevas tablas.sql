

CREATE TABLESPACE master
      DATAFILE 'master' size 100 M;

CREATE USER master
      IDENTIFIED BY master
         DEFAULT TABLESPACE master;

grant DBA to master;

/******************************************************************************
******** TABLA:
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/


/*******************  tablas de articulos  ********************************/

/******************************************************************************
******** TABLA:articulos
******** BD: MASTER
******** DESCRIPCION: tabla primaria de articulos
******** CLAVE PRINCIPAL: codigo_articulo
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  master.ARTICULOS 
   (	
    cod_articulo NUMBER(8,0) NOT NULL ENABLE, 
	DESCRIPCION_ARTICULO VARCHAR2(80), 
	fecha_alta date,
	fecha_baja date,
	estado_articulo char(2),
	PRECIO_UNIDAD_ARTICULO NUMBER(11,2), 
	CANTIDAD NUMBER(5),
	CONSTRAINT ARTICULOS_COD_ART_PK PRIMARY KEY (cod_articulo) ENABLE
);



/******************************************************************************
******** TABLA:proveedores articulo
******** BD: MASTER
******** DESCRIPCION: indica que proveedores sirve los articulos
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

create table proveedores_articulo(
	cod_provedoresarticulo number(8,0),
	cod_proveedor number(11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_proarticulo char(2),

	constraint prov_art_cod_PK primary key (cod_provedoresarticulo),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA:precio compra
******** BD: MASTER
******** DESCRIPCION:los distintos precios de cada articulo
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table precios_compra(
	cod_preciocompra number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_precio char(2),

	constraint precio_compra_cod_PK primary key (cod_preciocompra),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)

);


/******************************************************************************
******** TABLA:presentaciones articulo
******** BD: MASTER
******** DESCRIPCION: los formatos o presentaciones de los articulos
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table presentacion_articulo(
	cod_presentacion number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_presentacion char(2),

	constraint presentacion_cod_PK primary key (cod_presentacion),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
	
);

/******************************************************************************
******** TABLA:condiciones de pago
******** BD: MASTER
******** DESCRIPCIOn: las distintas formas de pago a cada proveedor
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table condiciones_pagoproveedor(
	cod_pagoproveedor number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_pago char(2),

	constraint precio_compra_cod_PK primary key (cod_preciocompra),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA:condiciones_entrega
******** BD: MASTER
******** DESCRIPCIOn:tipo y forma de entrega de mercancia
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table condiciones_entrega(
	cod_entrega number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_entrega char(2),

	constraint entrega_cod_PK primary key (cod_entrega),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA:descuentos articulo
******** BD: MASTER
******** DESCRIPCION: los descuentos a aplicar por articulo
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table descuentos_articulo(
	cod_descuentoarticulo number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_descuentoarticulo char(2),

	constraint descuento_arti_cod_PK primary key (cod_descuentoarticulo),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA:descuentos pedidos
******** BD: MASTER
******** DESCRIPCION: descuentos a realizar por los proveedores en nuestros pedidos
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table descuentos_pedidos(
	cod_descuentopedido number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_descuentopedido char(2),

	constraint descuento_ped_cod_PK primary key (cod_descuentopedido),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA: descuento rappel
******** BD: MASTER
******** DESCRIPCION: descuento a realizar en funcion de volumen en un periodo de tiempo
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table descuentos_rappel(
	cod_rappel number (8,0),
	cod_proveedor number (11,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_rappel char(2),

	constraint rappel_cod_PK primary key (cod_rappel),
	constraint cod_proveedor_FK FOREIGN KEY (cod_proveedor)
	references proveedores (CODIGO_PROVEEDOR),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/******************************************************************************
******** TABLA:familias articulos
******** BD: MASTER
******** DESCRIPCION: grupos de articulos por los que segmentamos los articulos
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table familias_articulos(
	cod_familia number (8,0),
	cod_articulo number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_familia char(2),

	constraint famili_cod_PK primary key (cod_familia),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo)
);

/*******************  fin tablas de articulos  ***************************/


/******************* tablas de almacen ***********************************/

/******************************************************************************
******** TABLA:almacen
******** BD: MASTER
******** DESCRIPCION:informacion de los lugares donde se deposita la mercancia hasta su venta
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table almacen(
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_almacen char(2),

	constraint almacen_cod_PK primary key (cod_almacen)
);

/******************************************************************************
******** TABLA:minimo almacen
******** BD: MASTER
******** DESCRIPCION: cantidad minima de producto a mantener en cada almacen
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table minimo_articulo(
	cod_minimo number (8,0),
	cod_articulo number (8,0),
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_minimo char(2),

	constraint minimo_cod_PK primary key (cod_minimo),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)
);

/******************************************************************************
******** TABLA:stock
******** BD: MASTER
******** DESCRIPCION:producto disponible en cada almacen 
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table stock_almacen(
	cod_stock number (8,0),
	cod_articulo number (8,0),
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_stock char(2),

	constraint stock_cod_PK primary key (cod_stock),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)
);

/******************************************************************************
******** TABLA: tipo_almacenamiento
******** BD: MASTER
******** DESCRIPCION: cada uno de los tipos de almacenaje disponibles.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table tipo_almacenamiento(
	cod_tipoalmacenamiento number(8,0),
	fecha_alta date,
	fecha_baja date,
	estado_tipoalmacenamiento char(2),

	constraint tipo_almacenamiento_cod_PK primary key (cod_tipoalmacenamiento)
	
);


/******************************************************************************
******** TABLA:capacidad almacen
******** BD: MASTER
******** DESCRIPCION: cada uno de las posibilidades de almacenar mercancia 
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table capacidad_almacen(
	cod_capacidadalmacen number(8,0),
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_capacidadalmacen char(2),

	constraint tipo_almacenamiento_cod_PK primary key (cod_tipoalmacenamiento),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)
);

 
/******************************************************************************
******** TABLA:ruta reparto
******** BD: MASTER
******** DESCRIPCION: cada una de las rutas de reparto fijas de cada almacen
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table ruta_reparto(
	cod_rutareparto number (8,0),
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_capacidadalmacen char(2),

	constraint ruta_reparto_cod_PK primary key (cod_rutareparto),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)

);


/******************************************************************************
******** TABLA: flota
******** BD: MASTER
******** DESCRIPCION: informacion de cada uno de los aparatos/vehiculos usados en cada almacen.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table flota(
	cod_flota number(8,0),
	cod_almacen number (8,0),
	cod_rutareparto number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_capacidadalmacen char(2),

	constraint flota_cod_PK primary key (cod_flota),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen),
	constraint cod_rutareparto_FK foreign key (cod_rutareperto)
	references ruta_reparto (cod_rutareparto)
);


/******************************************************************************
******** TABLA:ubicacion almacen
******** BD: MASTER
******** DESCRIPCION: posicion fisico/logica en el almacen
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table ubicacion_almacen (
	cod_ubicacionalmacen number (8,0),
	cod_almacen number (8,0),
	fecha_alta date,
	fecha_baja date,
	estado_capacidadalmacen char(2),

	constraint ubicacion_almacen_cod_PK primary key (cod_ubicacionalmacen),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)
);


/******************* fin tablas de almacen ********************************/



/******************* tablas de proveedores ********************************/

/******************************************************************************
******** TABLA: PROVEEDORES
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: MoDIFICADO EL 21-1-2009
******************************************************************************/

CREATE TABLE  "PROVEEDORES" 
   (	"CODIGO_PROVEEDOR" NUMBER(11,0), 
	"NOMBRE_PROVEEDOR" VARCHAR2(35), 
	"CIF_PROVEEDOR" VARCHAR2(10), 
	"NOMBRE_COMERCIAL_PROVEEDOR" VARCHAR2(40), 
	"CALLE_PROVEEDOR" VARCHAR2(40), 
	"CIUDAD_PROVEEDOR" VARCHAR2(40), 
	"CP_PROVEEDOR" NUMBER(5,0), 
	"PROVINCIA_PROVEEDOR" VARCHAR2(35), 
	"TELEFONO_PROVEEDOR" NUMBER(9,0), 
	"FAX_PROVEEDOR" NUMBER(9,0), 
	"EMAIL_PROVEEDOR" VARCHAR2(40), 
	"OBSERVACIONES_PROVEEDOR" CLOB, 
	 CONSTRAINT "PROVEEDORES_COD_PROV_PK" PRIMARY KEY ("CODIGO_PROVEEDOR") ENABLE
   );

/******************* fin tablas de proveedores ********************************/



/*************** TABLAS DE CLIENTES *************************/

/******************************************************************************
******** TABLA: CLIENTES
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
   
CREATE TABLE  "CLIENTES" 
   (	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	"NOMBRE_CLIENTE" VARCHAR2(35)NOT NULL,  
	"TIPO_EMPRESA" CHAR(1), 
	"CIF_CLIENTE" VARCHAR2(10), 
	"NIF_CLIENTE" VARCHAR2(10), 
	"NOMBRE_COMERCIAL_CLIENTE" VARCHAR2(40), 
	"FECHA_ALTA_CLIENTE" DATE, 
	"FECHA_BAJA_CLIENTE" DATE, 
	"CALLE_CLIENTE" VARCHAR2(40), 
	"CIUDAD_CLIENTE" VARCHAR2(35), 
	"CP_CLIENTE" VARCHAR2(5), 
	"PROVINCIA_CLIENTE" VARCHAR2(35), 
	"TELEFONO_CLIENTE" VARCHAR2(9), 
	"FAX_CLIENTE" VARCHAR2(9), 
	"EMAIL_CLIENTE" VARCHAR2(35), 
	"CONTADO_CLIENTE" CHAR(1), 
	"CREDITO_CLIENTE" CHAR(1), 
	"CIFRA_VENTAS_CLIENTE" NUMBER(11,2), 
	"BENEFICIOS_CLIENTE" NUMBER(11,2), 
	"PREPAGO_CLIENTE" CHAR(1), 
	"CAPITAL_CLIENTE" NUMBER(11,2), 
	"INMOVILIZADO_CLIENTE" NUMBER(11,2), 
	"PASIVO_CLIENTE" NUMBER(11,2), 
	"ACTIVO_CLIENTE" NUMBER(11,2), 
	"AÑO_BALANCE_CLIENTE" DATE, 
	"LIMITE_CLIENTE" NUMBER(11,2), 
	"PENDIENTE_CLIENTE" NUMBER(11,2), 
	"RESTO_CLIENTE" NUMBER(11,2), 
	"CALLE_ENVIO_CLIENTE" VARCHAR2(40), 
	"CIUDAD_ENVIO_CLIENTE" VARCHAR2(30), 
	"CP_ENVIO_CLIENTE" VARCHAR2(5), 
	"PROVINCIA_ENVIO_CLIENTE" VARCHAR2(35), 
	"TELEFONO_ENVIO_CLIENTE" VARCHAR2(9), 
	"FAX_ENVIO_CLIENTE" VARCHAR2(9), 
	"EMAIL_ENVIO_CLIENTE" VARCHAR2(35), 
	"OBSERVACIONES_CLIENTE" CLOB, 
	"ESTADO_CLIENTE" CHAR(2) NOT NULL ENABLE, 
	 CONSTRAINT "CLIENTE_CODIGO_CLIENTE_PK" PRIMARY KEY ("CODIGO_CLIENTE") ENABLE
   );

/******************************************************************************
******** TABLA: BANCOS
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  "BANCOS" 
   (	"NOMBRE_BANCO" VARCHAR2(40), 
	"DIRECCION_BANCO" VARCHAR2(40), 
	"ENTIDAD_BANCO" NUMBER(4,0), 
	"OFICINA_BANCO" NUMBER(4,0), 
	"DC_BANCO" NUMBER(2,0), 
	"CUENTA_BANCO" NUMBER(10,0), 
	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	 CONSTRAINT "BANCOS_COD_CLI_PK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE
   );

/******************************************************************************
******** TABLA: FORMA DE PAGO
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: MODIFICADO 12-3-2008 
******** MODIFICACION: MODIFICADO 8-4-2008
******** MODIFICACION: MODIFICADO 12-5-2008
******************************************************************************/

CREATE TABLE  "FORMAS_PAGO" 
   (	"CODIGO_FORMA_PAGO" NUMBER(5,0), 
	"DESCRIPCION_FORMA_PAGO" VARCHAR2(40),
	ESTADO_FORMA_PAGO CHAR(2),
	NUMERO_PAGOS NUMBER(2),
	NUMERO_DIAS_ENTRE_PAGOS NUMBER(2),
	NUMERO_DIAS_PRIMER_PAGO NUMBER(3),
	FECHA_ALTA_PAGO DATE, 
	FECHA_BAJA_PAGO DATE,
	 CONSTRAINT "FORMAS_PAGO_COD_FP_PK" PRIMARY KEY ("CODIGO_FORMA_PAGO") ENABLE,
	 CONSTRAINT FORMAS_PAGO_ESTADO_CK CHECK (ESTADO_FORMA_PAGO IN ('AC','BA'))
   );


/******************************************************************************
******** TABLA: FORMA DE PAGO CLIENTE
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** AÑADIDO: AÑADIDO EL 12-3-2008
******** MODIFICACION: modificado el 20-01-2009
******************************************************************************/

create table formas_pago_cliente
	(
		CODIGO_FORMA_PAGO NUMBER(5,0),
		CODIGO_CLIENTE NUMBER(5,0),
		ESTADO_FORMA_PAGO_CLIENTE CHAR (2),
		FECHA_ALTA_PAGO_CLIENTE DATE,
		FECHA_BAJA_PAGO_CLIENTE DATE,
		clave_forma_pago_cliente number (5,0),
		 CONSTRAINT PAGO_ESTADO_CK CHECK (ESTADO_FORMA_PAGO_CLIENTE IN ('AC','BA')),
		CONSTRAINT FORMA_PC_CODIGO_CLIENTE_FK FOREIGN KEY (CODIGO_CLIENTE)
		REFERENCES CLIENTES (CODIGO_CLIENTE),
		CONSTRAINT FORMA_PC_CODIFO_FORMA_PAGO_FK FOREIGN KEY (CODIGO_FORMA_PAGO)
		REFERENCES FORMAS_PAGO (CODIGO_FORMA_PAGO),
		constraint "clave_p" primary key (clave_forma_pago_cliente) enable
	);

/******************************************************************************
******** TABLA: PERSONAS DE CONTACTO
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******************************************************************************/

CREATE TABLE  "PERSONAS_CONTACTO" 
   (	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	"NOMBRE_PERSONA_CONTACTO" VARCHAR2(35), 
	"TELEFONO_PERSONA_CONTACTO" NUMBER(9,0), 
	"EXTENSION_PERSONA_CONTACTO" NUMBER(6,0), 
	"CARGO_PERSONA_CONTACTO" VARCHAR2(35), 
	"HORARIO_PERSONA_CONTACTO" VARCHAR2(20), 
	"EMAIL_PERSONA_CONTACTO" VARCHAR2(35), 
	 CONSTRAINT "PERSONAS_CONTAC_COD_CLI_FK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE
   );

/************ FIN TABLAS DE CLIENTES *****************************/


/******************** TABLAS DE PEDIDOS  *************************/

/******************************************************************************
******** TABLA: PEDIDOS
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******************************************************************************/

CREATE TABLE  "PEDIDOS" 
   (	"NUMERO_PEDIDO" NUMBER(5,0), 
	"FECHA_PEDIDO" DATE, 
	"PORTE_PEDIDO" NUMBER(11,2), 
	"SEGURO_PEDIDO" NUMBER(11,2), 
	"OTROS_CARGOS_PEDIDO" NUMBER(11,2), 
	"TOTAL_CARGOS_PEDIDO" NUMBER(11,2), 
	"TOTAL_BRUTO_PEDIDO" NUMBER(11,2), 
	"PORCENTAJE_IVA_PEDIDO" NUMBER(2,0), 
	"IVA_PEDIDO" NUMBER(11,2), 
	"TOTAL_FACTURA_PEDIDO" NUMBER(11,2), 
	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	 CONSTRAINT "PEDIDOS_NUMERO_PEDIDO_PK" PRIMARY KEY ("NUMERO_PEDIDO") ENABLE, 
	 CONSTRAINT "PEDIDOS_COD_CLI_FK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE
   );

/******************************************************************************
******** TABLA: LINEA DE PEDIDOS
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: MoDIFICADO EL 21-1-2009
******************************************************************************/

CREATE TABLE  "LINEA_PEDIDO"
   (	
	codigo_linea_pedido number(10,0),
	"CODIGO_ARTICULO" NUMBER(5,0), 
	"NUMERO_PEDIDO" NUMBER(5,0), 
	"PRECIO_UNIDAD_ARTICULO" NUMBER(11,2), 
	"NUMERO_UNIDADES_ARTICULO" NUMBER(5,0), 
	"PORCENTAJE_DESCUENTO" NUMBER(4,2), 
	 CONSTRAINT "LINEA_PEDIDO_NUM_PED_FK" FOREIGN KEY ("NUMERO_PEDIDO")
	  REFERENCES  "PEDIDOS" ("NUMERO_PEDIDO") ENABLE, 
	 CONSTRAINT "LINEA_PEDIDO_COD_ART_FK" FOREIGN KEY ("CODIGO_ARTICULO")
	  REFERENCES  "ARTICULOS" ("CODIGO_ARTICULO") ENABLE,
	CONSTRAINT "codigo_linea_pedido_PK" PRIMARY KEY (codigo_linea_pedido)
   );


/******************** FIN TABLAS PEDIDOS *****************************/


/******************* tablas de vencimientos ******************************/

/******************************************************************************
******** TABLA:vencimientos
******** BD: MASTER
******** DESCRIPCION: prevision de pagos a realizar por los clientes y por cada pedido.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: 22-01-2009
******************************************************************************/

CREATE TABLE  "VENCIMIENTOS" 
   (	
	codigo_vencimiento number(10,0),
	"FECHA_VENCIMIENTO" DATE, 
	"CANTIDAD_VENCIMIENTO" NUMBER(11,2), 
	"NUMERO_PEDIDO" NUMBER(5,0), 
	"FECHA_COBRO_VENCIMIENTO" DATE, 
	"NOMBRE_BANCO" VARCHAR2(40), 
	"ESTADO_VENCIMIENTO" CHAR(1),
 	CONSTRAINT codigo_vencimiento_PK PRIMARY KEY (codigo_vencimiento) ENABLE,
	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	 CONSTRAINT "VENCIMIENTOS_COD_CLI_PK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE
   );


/******************************************************************************
******** TABLA: pago de clientes
******** BD: MASTER
******** DESCRIPCION: registro de los pagos de cada cliente.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: 22-01-2009
******************************************************************************/
CREATE TABLE  "PAGO_CLIENTES"(	
	codigo_pago_cliente number (10,0),
	"CODIGO_FORMA_PAGO" NUMBER(5,0), 
	"CODIGO_CLIENTE" NUMBER(5,0),
 	
	
	 CONSTRAINT codigo_pago_cliente_PK PRIMARY KEY (codigo_pago_cliente) ENABLE,
	 CONSTRAINT "PAGO_CLIENTES_COD_CLI_FK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE, 
	 CONSTRAINT "PAGO_CLIENTES_COD_FP_FK" FOREIGN KEY ("CODIGO_FORMA_PAGO")
	  REFERENCES  "FORMAS_PAGO" ("CODIGO_FORMA_PAGO") ENABLE
   );


/******************* tablas de vencimientos ******************************/


/******************* TABLAS sistema logeado *****************
******************************************************************************
******** TABLA:
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: 12-01-2009
******************************************************************************/

CREATE TABLE  "USUARIOS" 
   (	"NOMBRE_USUARIO" VARCHAR2(20), 
	"PASSWORD" VARCHAR2(10), 
	"CODIGO_ROL" NUMBER(2,0),
	fecha_alta date,
	fecha_baja date,
	carpeta_documentacion varchar2(250),
	idioma char(2),

	 CONSTRAINT "NOMBRE_USUARIO_PK" PRIMARY KEY ("NOMBRE_USUARIO") ENABLE, 
	 CONSTRAINT "CODIGO_ROL_USUARIOS_FK" FOREIGN KEY ("CODIGO_ROL")
	  REFERENCES  "ROLES" ("CODIGO_ROL") ENABLE	 

   );


/******************************************************************************
******** TABLA:
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  "ROLES" 
   (	"CODIGO_ROL" NUMBER(2,0), 
	"DESCRIPCION_ROL" VARCHAR2(100), 
	 CONSTRAINT "CODIGO_ROL_PK" PRIMARY KEY ("CODIGO_ROL") ENABLE
   );

/******************************************************************************
******** TABLA:
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: 
******************************************************************************/
CREATE TABLE  "TAREAS" 
   (	"CODIGO_TAREA" NUMBER(2,0), 
	"DESCRIPCION_TAREA" VARCHAR2(2000), 
	"VINCULO" VARCHAR2(50), 
	 CONSTRAINT "CODIGO_TAREA_PK" PRIMARY KEY ("CODIGO_TAREA") ENABLE
   );



/******************* fin TABLAS sistema logeado *****************/

/******************* tablas de cruze ***************************************  /

/******************************************************************************
******** TABLA:articulos stock
******** BD: MASTER
******** DESCRIPCION:cruze entre articulos y almacen
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
create table articulos_almacen(
	cod_articulo number (8,0),
	cod_stock number (8,0),
	
	constraint articulostock_PK primary key (cod_articulo,cod_stock),
	constraint cod_articulo_FK foreign key (cod_articulo)
	references articulos (cod_articulo),
	constraint cod_almacen_FK foreign key (cod_almacen)
	references almacen (cod_almacen)
);

/******************************************************************************
******** TABLA: proveedores  articulos 
******** BD: MASTER
******** DESCRIPCION: cruze entre articulos y proveedores
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  "PROVEEDORES_ARTICULOS" 
   (	"CODIGO_PROVEEDOR" NUMBER(11,0), 
	"CODIGO_ARTICULO" NUMBER(20,0),

	constraint proveedor_articulo_PK primary key (codigo_proveedor,codigo_articulo), 
	 CONSTRAINT "PROV_ART_COD_PROV_FK" FOREIGN KEY ("CODIGO_PROVEEDOR")
	  REFERENCES  "PROVEEDORES" ("CODIGO_PROVEEDOR") ENABLE, 
	 CONSTRAINT "PROV_ART_COD_ART_FK" FOREIGN KEY ("CODIGO_ARTICULO")
	  REFERENCES  "ARTICULOS" ("CODIGO_ARTICULO") ENABLE
   );

/******************************************************************************
******** TABLA: roles tareas 
******** BD: MASTER
******** DESCRIPCION: cruze de roles y tareas
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  "ROLES_TAREAS"(
	"CODIGO_ROL" NUMBER(2,0), 
	"CODIGO_TAREA" NUMBER(2,0), 

	 CONSTRAINT "ROLES_TAREAS_PK" PRIMARY KEY ("CODIGO_ROL", "CODIGO_TAREA") ENABLE, 
	 CONSTRAINT "CODIGO_ROL_FK" FOREIGN KEY ("CODIGO_ROL")
	  REFERENCES  "ROLES" ("CODIGO_ROL") ENABLE, 
	 CONSTRAINT "CODIGO_TAREA_FK" FOREIGN KEY ("CODIGO_TAREA")
	  REFERENCES  "TAREAS" ("CODIGO_TAREA") ENABLE
   );

/******************************************************************************
******** TABLA: usuarios clientes
******** BD: MASTER
******** DESCRIPCION: cruze de usuarios y clientes, indicando que usuarios son clientes.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: añadido 7-4-2008
******************************************************************************/

CREATE TABLE USUARIOS_CLIENTES(
	"NOMBRE_USUARIO" VARCHAR2(20) NOT NULL ENABLE, 
	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE,
	
	constraint nombre_cliente_PK primary key (nombre_usuario,codigo_cliente),
 	CONSTRAINT "CODIGO_CLIENTE_FK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE,
	CONSTRAINT "NOMBRE_USUARIO_FK" FOREIGN KEY ("NOMBRE_USUARIO")
	  REFERENCES  "USUARIOS" ("NOMBRE_USUARIO") ENABLE

	);


/******************* fin tablas de cruze ***************************************/



/******************* TABLAS PAISES/PROVINCIAS/MUNICIPIOS *****************

******************************************************************************
******** TABLA: paises
******** BD: MASTER
******** DESCRIPCION: codificacion ISO de los paises del mundo.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:  AÑADIDO 1-6-2008 
******************************************************************************/
CREATE TABLE  pais  (
   codigo_pais  number(11,0) NOT NULL,
   PAIs_ISONUM  number(6,0),
   PAIs_ISO2  char(2),
   PAIs_ISO3  char(3),
   PAIs_NOMBRE  varchar2(80),
  constraint pais_codigo_pais_PK PRIMARY KEY  ( codigo_pais )
);
/******************************************************************************
******** TABLA: provincias
******** BD: MASTER
******** DESCRIPCION: provincias de españa.
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/

CREATE TABLE  provincias  (
   codigo_provincia  number(2,0) NOT NULL ,
   provincia  varchar2(255) NOT NULL,
  constraint provincias_codigo_provincia_PK primary key (codigo_provincia)
);

/******************************************************************************
******** TABLA: municipios
******** BD: MASTER
******** DESCRIPCION: municipios de españa
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION:
******************************************************************************/
CREATE TABLE  municipios (
   codigo_municipio  number(11,0) NOT NULL,
   provincia number(2,0) NOT NULL,
   municipio  varchar2(255) NOT NULL,
  constraint municipios_PK PRIMARY KEY (codigo_municipio,provincia)
);

/*********FIN TABLAS DE PAISES/PROVINCIAS/MUNICIPIOS****************/

/********* tabla calles/codigos postales

******************************************************************************
******** TABLA: calles
******** BD: MASTER
******** DESCRIPCION: informacion de los codigos postales por calles y ciudades de españa
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: AÑADIDO 1-1-2011. MODIFICADO 7-12-2011
******************************************************************************/
create table calles (
	codigo_calle number (10,0),
	nombre_calle varchar2 (100),
	codigo_postal char(5),
	zona_codigo_postal varchar2 (100),
	codigo_municipio number(11,0),
	codigo_provincia number(2,0),

	constraint calles_codigo_calle_PK primary key (codigo_calle),
	CONSTRAINT "municipio_FK" FOREIGN KEY ("codigo_municipio")
	  REFERENCES  "municipios" ("codigo_municipio"),
	CONSTRAINT "provincia_FK" FOREIGN KEY ("codigo_provincia")
	  REFERENCES  "provincias" ("codigo_provincia") 
) ;

/********* tabla REGISTRO DE ACCESOS A LA APLICACION
******************************************************************************
******** TABLA:
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: AÑADIDO 2-12-2011.
******** MODIFICACION: 22-12-2011
******************************************************************************/
CREATE TABLE REGISTRO_USUARIOS(
	codigo_registro number(10,0),
	codigo_usuario varchar2(20) not null,
	fecha_entrada date not null,
	fecha_salida date,
	duracion varchar2(50),
	ficheros_subidos number(3,0),
	informacion_subida number (15,2),

	constraint registro_codigo_registro_PK primary key (codigo_registro)
);



/********** SECUENCIADORES PARA LA BASE DE DATOS  *******************/
/********* AÑADIDO EL 21-1-2009 ************************************/
/********* modificado el 26-11-2010 *********************************/
/********* modificado el 27-07-2011 *********************************/
/********* modificado el 2-12-2011 *********************************/

create sequence codigo_registro;
create sequence num_linea_pedido;
create sequence codigo_cliente start with 9;
create sequence numero_pedido;
create sequence pagos_clientes;
create sequence numero_vencimiento; 
create sequence codigo_articulo;
create sequence codigo_proveedores;
create sequence codigo_calle;
create sequence codigo_forma_pago_cliente;

create sequence cod_proveedoresartioculo;
create sequence cod_preciocompra;
create sequence cod_presentacion;
create sequence cod_pagoproveedores;
create sequence cod_entrega;
create sequence cod_descuentoarticulo;
create sequence cod_descuentopedido;
create sequence cod_rappel;
create sequence cod_familia;
create sequence cod_minimo;
create sequence cod_stockalmacen;
create sequence cod_tipoalmacenamiento;
create sequence cod_capacidadalmacen;
create sequence cod_rutareparto;
create sequence cod_flota;
create sequence cod_ubicacionalmacen;

