package com.atrium.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import com.atrium.hibernate.Roles;
import com.atrium.hibernate.Usuarios;
import com.atrium.hibernate.modelo.IGestion_Roles;
import com.atrium.hibernate.modelo.IGestion_Usuario;
import com.atrium.spring.Acceso_ApplicationContext;
import com.atrium.util.Acceso_Contextos;

public class Usuarios_Bean implements Serializable {

	// PROPIEDADES PARA EL PROCESO DE USUARIOS
	private String nombre_usuario;
	private String clave;
	private String carpeta;
	private Date fecha_alta;
	private Date fecha_baja;
	private String idioma;
	private String codigo_rol;

	// CONTROL DE ACTIVACION DE BOTONES
	private boolean activar_BM;
	private boolean activar_alta;

	// COLECCION PARA LA CARGA DEL COMPONENTE EN LA PAGINA
	private List<SelectItem> lista_roles;

	// FACHADA DEL MODELO PARA LEER LOS ROLES
	private IGestion_Roles gestion_roles;

	// PROPIEDAD PARA GESTION DE USUARIOS
	private IGestion_Usuario gestion_Usuario;

	public Usuarios_Bean() {
		gestion_roles = Acceso_ApplicationContext.getBean(IGestion_Roles.class);
		lista_roles = new ArrayList<SelectItem>();
		// VALORES POR DEFECTO EN EL FORMULARIO
		this.accion_Reiniciar();
		cargar_Rol();
	}

	// ******** TRATAMIENTO DE ACCIONES DE LOS BOTONES ************
	// RESOLUCION DEL EVENTO DE LOS BOTONES DEL FORMULARIO
	public void tratar_Accion(ActionEvent evento) {
		String boton_elegido = evento.getComponent().getId();
		String control_proceso = "";
		if (boton_elegido.equalsIgnoreCase("bot_alta")) {
			this.alta_Usuario();
		}
		if (boton_elegido.equalsIgnoreCase("bot_baja")) {
			this.baja_Usuario();
		}
		if (boton_elegido.equalsIgnoreCase("bot_consultas")) {
			this.consulta_Usuario();
		}
		if (boton_elegido.equalsIgnoreCase("bot_modificaciones")) {
			this.modificacion_Usuario();
		}
		if (boton_elegido.equalsIgnoreCase("bot_reiniciar")) {
			this.accion_Reiniciar();
		}
		if (boton_elegido.equalsIgnoreCase("bot_salir")) {
			this.accion_Salir();
		} else {
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.setVisible(true);
		}
	}

	public void cerrar_Mensaje(AjaxBehaviorEvent evento) {
		this.accion_Reiniciar();
	}

	// ***** PROCESOS CRUD USUARIOS
	public void alta_Usuario() {
		Usuarios usuario = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
		usuario.setNombreUsuario(this.getNombre_usuario());
		usuario.setCarpetaDocumentacion(this.getCarpeta());
		usuario.setPassword(this.getClave());
		usuario.setFechaAlta(this.getFecha_alta());
		usuario.setFechaBaja(this.getFecha_baja());
		usuario.setIdioma(this.getIdioma());
		usuario.setRoles(new Roles(new Byte(this.getCodigo_rol())));
		try {
			gestion_Usuario.alta_Usuario(usuario);
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("El usuario se dio de alta de forma correcta");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuariocorrecta.png");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("Error en el alta de usuario");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuarioincorrecta.png");
		}
	}

	public void baja_Usuario() {
		Usuarios usuario = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
		usuario.setNombreUsuario(this.getNombre_usuario());
		try {
			gestion_Usuario.baja_Usuario(usuario);
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("El usuario se dio de baja de forma correcta");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuariocorrecta.png");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("Error en la baja de usuario");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuarioincorrecta.png");
		}
	}

	public void consulta_Usuario() {
		try {
			Usuarios usuario_nuevo = gestion_Usuario
					.consultar_UsuarioConRol(getNombre_usuario());
			setCarpeta(usuario_nuevo.getCarpetaDocumentacion());
			setFecha_alta(usuario_nuevo.getFechaAlta());
			setFecha_baja(usuario_nuevo.getFechaBaja());
			setIdioma(usuario_nuevo.getIdioma());
			setNombre_usuario(usuario_nuevo.getNombreUsuario());
			setClave(usuario_nuevo.getPassword());
			if (usuario_nuevo.getRoles() != null) {
				setCodigo_rol(usuario_nuevo.getRoles().getCodigoRol()
						.toString());
			}
			activar_BM = false;
			activar_alta = true;
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("No hay usuarios con ese nombre");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuarioincorrecta.png");
		}
	}

	public void modificacion_Usuario() {
		Usuarios usuario = new Usuarios();
		// CARGAMOS LAS PROPIEDADES DEL USUARIO
		usuario.setNombreUsuario(this.getNombre_usuario());
		usuario.setCarpetaDocumentacion(this.getCarpeta());
		usuario.setPassword(this.getClave());
		usuario.setFechaAlta(this.getFecha_alta());
		usuario.setFechaBaja(this.getFecha_baja());
		usuario.setIdioma(this.getIdioma());
		usuario.setRoles(new Roles(new Byte(this.getCodigo_rol())));
		try {
			gestion_Usuario.modificacion_Usuario(usuario);
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("El usuario modifico de forma correcta");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuariocorrecta.png");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Mensaje("Error en modificacion de usuario");
			((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"))
					.cargar_Icono("/imagenes/alta_usuarioincorrecta.png");
			activar_BM = true;
		}
	}

	public void accion_Salir() {
		((Menu_DinamicoBean) Acceso_Contextos.getAtributo("menu_bean"))
				.setPagina_elegida("/xhtml/inicio.xhtml");
	}

	public void accion_Reiniciar() {
		// PROPIEDADES DEL FORMULARIO
		setCarpeta("");
		setFecha_alta(new Date());
		setFecha_baja(null);
		setIdioma("es");
		setNombre_usuario("");
		setClave("");
		// ACTIVACION DE BOTONES
		activar_BM = true;
		activar_alta = false;
	}

	/**
	 * Cargamos el contenido de la lista de roles
	 */
	private void cargar_Rol() {
		lista_roles.clear();
		List<Roles> roles = gestion_roles.consultar_Todos();
		SelectItem rol_lista;
		// CASO DE NO ESTAR DEFINIDOS NINGUN ROL
		if (roles.isEmpty()) {
			rol_lista = new SelectItem();
			rol_lista.setDescription("No hay disponibles roles");
			rol_lista.setValue("0");
			lista_roles.add(rol_lista);
		} else {
			// CARGA DE LOS ROLES OBTENIDOS DE LA BASE DE DATOS
			for (Roles rol : roles) {
				rol_lista = new SelectItem();
				rol_lista.setLabel(rol.getDescripcionRol());
				rol_lista.setValue(rol.getCodigoRol());
				lista_roles.add(rol_lista);
			}
		}
	}

	// ACCESORES PARA JSF
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isActivar_BM() {
		return activar_BM;
	}

	public void setActivar_BM(boolean activar_BM) {
		this.activar_BM = activar_BM;
	}

	public List<SelectItem> getLista_roles() {
		return lista_roles;
	}

	public void setLista_roles(List<SelectItem> lista_roles) {
		this.lista_roles = lista_roles;
	}

	public String getCodigo_rol() {
		return codigo_rol;
	}

	public void setCodigo_rol(String codigo_rol) {
		this.codigo_rol = codigo_rol;
	}

	public boolean isActivar_alta() {
		return activar_alta;
	}

	public void setActivar_alta(boolean activar_alta) {
		this.activar_alta = activar_alta;
	}

	// ACCESORES PARA SPRING
	public void setGestion_Usuario(IGestion_Usuario gestion_Usuario) {
		this.gestion_Usuario = gestion_Usuario;
	}

}
