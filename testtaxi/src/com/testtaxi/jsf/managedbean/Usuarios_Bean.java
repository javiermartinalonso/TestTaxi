package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.icefaces.ace.event.SelectEvent;
import org.icefaces.ace.event.UnselectEvent;

import com.testtaxi.crypto.Encriptador;
import com.testtaxi.hibernate.Usuarios;
import com.testtaxi.hibernate.modelo.I_Usuario;
import com.testtaxi.spring.Acceso_ApplicationContext;
import com.testtaxi.util.Acceso_Contextos;


public class Usuarios_Bean implements Serializable {

	private static final long serialVersionUID = -5188389835761859524L;
	
	// CONTROL DE ACTIVACION DE BOTONES
	private boolean activar_BMIL = true;
	private boolean activar_alta = true;
	
	//CONTROL DE LAS FECHAS A PARTIR DE LA FECHA ACTUAL
	private Date fecha_actual = null;

	// COLECCION PARA LA CARGA DEL COMPONENTE EN LA PAGINA
	private List<SelectItem> lista_roles;
	private List<SelectItem> lista_activo;
	
	//COLECCION PARA LA CARGA DE LA TABLA DE USUARIOS
	private List<Usuarios> lista_usuarios;
	
	private Usuarios user;
	private Usuarios user_desconectado;
	
	// PROPIEDAD PARA GESTION DE USUARIOS
	public I_Usuario f_usuarios;
	
	private Mensajes_Bean mensaje;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Usuarios_Bean.class);

	public Usuarios_Bean() {	
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS LA GESTION DE USUARIOS ******------");
		
		// VALORES POR DEFECTO EN EL FORMULARIO
		this.accion_Reiniciar();
		
		cargar_lista_usuarios();
		
		mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
	}

	public void cargar_lista_usuarios() {
		//CARGAMOS LA LISTA DE USUARIOS
		
		f_usuarios = Acceso_ApplicationContext.getBean(I_Usuario.class);
		List<Usuarios> lista = f_usuarios.consultar_todos();
				
		// CREAMOS LA COLECCION PARA LOS USUARIOS SELECCIONABLES DE LA TABLA
		setLista_usuarios(new ArrayList<Usuarios>(0));
		// CARGAMOS LA TABLA CON LOS VENCIMIENTOS ENCONTRADOS
		lista_usuarios.addAll(lista);
	}

	private void cargarListaRoles() {
		lista_roles = new ArrayList<SelectItem>();
		
		SelectItem rol_lista;
		
		rol_lista = new SelectItem();
		rol_lista.setDescription("Alumno");
		rol_lista.setLabel("Alumno");
		rol_lista.setValue("Alumno");
		lista_roles.add(rol_lista);
		
		rol_lista = new SelectItem();
		rol_lista.setDescription("Administrador");
		rol_lista.setLabel("Administrador");
		rol_lista.setValue("Administrador");
		lista_roles.add(rol_lista);
	}

	
	private void cargarListaActivo() {
		lista_activo = new ArrayList<SelectItem>();
		
		SelectItem activo_lista;
		activo_lista = new SelectItem();
		activo_lista.setDescription("Activado");
		activo_lista.setLabel("Activado");
		activo_lista.setValue("true");
		lista_activo.add(activo_lista);
		
		activo_lista = new SelectItem();
		activo_lista.setDescription("Desactivado");
		activo_lista.setLabel("Desactivado");
		activo_lista.setValue("false");
		lista_activo.add(activo_lista);
	}
	
	
	public void accion_Reiniciar() {
		// PROPIEDADES DEL FORMULARIO
		//CARGAMOS EL DESPLEGABLE CON LOS ROLES
		cargarListaRoles();	
		cargarListaActivo();
		//CARGAMOS UN USUARIO NUEVO DESCONECTADO
		user = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
//		usuario_nuevo.setActivo(true);
//		usuario_nuevo.setApellido1(user.getApellido1());
//		usuario_nuevo.setApellido2(user.getApellido2());
//		usuario_nuevo.setEmail(user.getEmail());		
		user.setNombre(null);
		user.setUsername(null);
//		usuario_nuevo.setPassword(user.getPassword());
//		usuario_nuevo.setRol(user.getRol());
//		usuario_nuevo.setTelefono(user.getTelefono());
//		usuario_nuevo.setUsername(user.getUsername());
		fecha_actual = get_fecha_actual();
		user.setFechaAlta(fecha_actual);
		user.setFechaVencimiento(get_fecha_vencimiento());

		
		// ACTIVACION DE BOTONES
		activar_BMIL = true;
		activar_alta = false;
	}
	
	
	public void tratar_Seleccion(SelectEvent evento) {
//		System.out.println("El usuario seleccionado es .."
//				+ ((Usuarios) evento.getObject()).getUsername());
		
		
		user.setUsername(((Usuarios) evento.getObject()).getUsername());
		consulta_Usuario();
	}

	

	
	public void tratar_Deseleccion(UnselectEvent evento) {
//		System.out.println("La fila que se deselecciona es .."
//				+ evento.getRow());
		accion_Reiniciar();
	}
	
	
	
	// ******** TRATAMIENTO DE ACCIONES DE LOS BOTONES ************
	// RESOLUCION DEL EVENTO DE LOS BOTONES DEL FORMULARIO
	public void tratar_Accion(ActionEvent evento) {
		String boton_elegido = evento.getComponent().getId();

		if (boton_elegido.equalsIgnoreCase("bot_alta")) {
			this.alta_Usuario();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_baja")) {
			this.baja_Usuario();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_consultas")) {
			this.consulta_Usuario();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_modificaciones")) {
			this.modificacion_Usuario();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_reiniciar")) {
			this.accion_Reiniciar();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_salir")) {
			this.accion_Salir();
		} 
//		else {
//			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean")).setVisible(true);
//		}
	}

	public void cerrar_Mensaje(AjaxBehaviorEvent evento) {
		this.accion_Reiniciar();
	}

	

	
	
	private Date get_fecha_actual() {
		Calendar calendario=Calendar.getInstance();
//		SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyyy"); 
		Date fecha=calendario.getTime();		
//		calendario.setTime(fecha);
		
//		String str_fecha = dt.format(fecha);
		
//		try 
//		{
//			fecha = dt.parse(str_fecha);
//		}
//		catch (ParseException e) {
//			e.printStackTrace();
//			log.error("ERROR: obteniendo la fecha actual del sistema.", e);
//		} 
		return fecha;
	}
	
	
	private Date get_fecha_vencimiento() {
		Calendar calendario=Calendar.getInstance();
//		SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyyy"); v
		
		calendario.add(Calendar.MONTH, 3);
		Date fecha=calendario.getTime();		
		
		//		calendario.setTime(fecha);
		
//		String str_fecha = dt.format(fecha);
		
//		try 
//		{
//			fecha = dt.parse(str_fecha);
//		}
//		catch (ParseException e) {
//			e.printStackTrace();
//			log.error("ERROR: obteniendo la fecha actual del sistema.", e);
//		} 
		return fecha;
	}	
	
	// ***** PROCESOS CRUD USUARIOS
	public void alta_Usuario() {
		Usuarios usuario_nuevo = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
		
		//TODO ESTAS TRES PROPIEDADES NO LAS CONTENPLAMOS ACTUALMENTE
		usuario_nuevo.setActivo("Activo");;
		usuario_nuevo.setFechaAlta(get_fecha_actual());
		usuario_nuevo.setFechaVencimiento(get_fecha_actual());
		
		usuario_nuevo.setApellido1(user.getApellido1());
		usuario_nuevo.setApellido2(user.getApellido2());
		usuario_nuevo.setEmail(user.getEmail());
		usuario_nuevo.setNombre(user.getNombre());
		String password = Encriptador.encriptar(user.getPassword());
		usuario_nuevo.setPassword(password);
		usuario_nuevo.setRol(user.getRol());
		usuario_nuevo.setTelefono(user.getTelefono());
		usuario_nuevo.setUsername(user.getUsername());

		
		try {
			f_usuarios.alta_Usuario(usuario_nuevo);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_usuarios();
			mensaje.cargar_Mensaje("Alta Correcta", "El usuario ".concat(usuario_nuevo.getUsername()).concat(" se dio de alta de forma correcta"), "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR			
			mensaje.cargar_Mensaje("Alta ERROR", "Error en el alta de usuario: ".concat(usuario_nuevo.getUsername()), "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void baja_Usuario() {
//		Usuarios usuario_baja = new Usuarios();
//		// CARGAMOS LAS PROPIEDADES DEL USUARIO
//		usuario_baja.setNombre(user.getUsername());

		String user_name = user.getUsername();
		
		try {

			f_usuarios.baja_Usuario(user);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_usuarios();
			mensaje.cargar_Mensaje("Baja correcta", "El usuario ".concat(user_name).concat(" se dio de baja de forma correcta"), "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Baja ERROR", "Error en la baja de usuario: ".concat(user_name), "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	
	//TODO revisar boton de listar????
	public void consulta_Usuario() {
		String user_name_aux = user.getUsername();
		
		try {
			
			//OBTENGO EL USUARIO
//			f_usuarios = Acceso_ApplicationContext.getBean(I_Usuario.class);
//			user = f_usuarios.consultar_PorClave("javi");
			
			
			user = f_usuarios.consultar_PorClave(user.getUsername());
			
			//PARA EVITAR PROBLEMAS AL MODIFICAR UN USUARIO, NECESITO OBTENER SU CLAVE SIN ENCRIPTAR.
			user.setPassword(Encriptador.desencriptar(user.getPassword()));
			
			String str_mensaje = "";
			
			//El usuario no ha introducido correctamente un usuario a buscar
			if (user_name_aux.equals(""))
			{	
				str_mensaje = "Debe introducir un identificador de usuario.";
			}
			//No hay coincidencia en BBDD
			else if (user==null)
			{
				str_mensaje = "El usuario: " + user_name_aux + " no esta dado de alta.";	
			}
			//HAY COINCIDENCIA EN BBDD
			else
			{
				activar_BMIL = false;
				activar_alta = true;
			}
			
			// TRATAMIENTO MENSAJE
			if (!str_mensaje.equals(""))
			{
				mensaje.cargar_Mensaje("Consulta ERROR", str_mensaje, "/resources/images/operacion_incorrecta.png","Aceptar", "");
				accion_Reiniciar();
			}
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Consulta ERROR", "No hay usuarios con ese identificador: ".concat(user_name_aux), "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void modificacion_Usuario() {
		Usuarios usuario_modificacion = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
		usuario_modificacion.setActivo("Activo");
		usuario_modificacion.setApellido1(user.getApellido1());
		usuario_modificacion.setApellido2(user.getApellido2());
		usuario_modificacion.setEmail(user.getEmail());		
		usuario_modificacion.setFechaAlta(user.getFechaAlta());
		usuario_modificacion.setFechaVencimiento(user.getFechaVencimiento());
		usuario_modificacion.setNombre(user.getNombre());
		String password = Encriptador.encriptar(user.getPassword());
		usuario_modificacion.setPassword(password);
		usuario_modificacion.setRol(user.getRol());
		usuario_modificacion.setTelefono(user.getTelefono());
		usuario_modificacion.setUsername(user.getUsername());
		
		String user_name = user.getUsername();
		try {
			f_usuarios.modificacion_Usuario(usuario_modificacion);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_usuarios();
			mensaje.cargar_Mensaje("Modificacion INFO", "El usuario ".concat(user_name).concat(" se modifico de forma correcta"), "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Modificacion ERROR", "Error en modificacion de usuario: ".concat(user_name), "/resources/images/operacion_incorrecta.png","Aceptar", "");
			
			activar_BMIL = true;
		}
	}

	public void accion_Salir() {
		Menu_Bean menu = (Menu_Bean) Acceso_Contextos.getAtributo("menu_bean");
		menu.setPagina_elegida("/xhtml/fondo.xhtml");
		
//		menu.accion_salir();
	}

	
	//ACCESORES PARA JSF
	public boolean isActivar_BMIL() {
		return activar_BMIL;
	}


	public void setActivar_BMIL(boolean activar_BMIL) {
		this.activar_BMIL = activar_BMIL;
	}

	public List<SelectItem> getLista_roles() {
		return lista_roles;
	}

	public void setLista_roles(List<SelectItem> lista_roles) {
		this.lista_roles = lista_roles;
	}

	
	public List<SelectItem> getLista_activo() {
		return lista_activo;
	}

	public void setLista_activo(List<SelectItem> lista_activo) {
		this.lista_activo = lista_activo;
	}

	public boolean isActivar_alta() {
		return activar_alta;
	}

	public void setActivar_alta(boolean activar_alta) {
		this.activar_alta = activar_alta;
	}	
	
	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}
	
	public Usuarios getUser_desconectado() {
		return user_desconectado;
	}

	public void setUser_desconectado(Usuarios user_desconectado) {
		this.user_desconectado = user_desconectado;
	}
	
	public List<Usuarios> getLista_usuarios() {
		return lista_usuarios;
	}

	public void setLista_usuarios(List<Usuarios> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}

	public Date getFecha_actual() {
		return fecha_actual;
	}

	public void setFecha_actual(Date fecha_actual) {
		this.fecha_actual = fecha_actual;
	}

	// ACCESORES PARA SPRING
	public void setF_usuarios(I_Usuario f_usuarios) {
		this.f_usuarios = f_usuarios;
	}
}
