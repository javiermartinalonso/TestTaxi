package com.atrium.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.component.submenu.Submenu;
import org.icefaces.ace.model.DefaultMenuModel;
import org.icefaces.ace.model.MenuModel;

import com.atrium.hibernate.Tareas;
import com.atrium.hibernate.Usuarios;
import com.atrium.hibernate.ext.Tareas_Idioma;

import com.atrium.hibernate.modelo.IGestion_Usuario;
import com.atrium.spring.Acceso_ApplicationContext;
import com.atrium.util.Acceso_Contextos;

public class Menu_DinamicoBean implements Comparator<Tareas_Idioma>,
		ActionListener, Serializable {
	private static final long serialVersionUID = 1L;

	// CONTROL DE VISIBILIDAD DE MENU Y CUERPO DE LA APLICACION
	private boolean visible;
	// PROPIEDADES PARA INYECCION DE SPRING
	private IGestion_Usuario gestion_servicio;

	// OBJETOS PARA CREAR LAS OPCIONES DE MENU Y CONTROLAR LA PAGINA A MOSTRAR
	private List<MenuItem> opciones_menu;
	private String pagina_elegida;

	// COLECCION PARA IDIOMATIZAR LAS OPCIONES DE MENU
	private List<Tareas_Idioma> lista_tareas_traducidas;
	private List<Tareas> lista_tareas;

	// PROPIEDAD PARA LA GENERACION DINAMICA DEL MENU CON LOS COMPONENTES ACE
	private MenuModel menu_dinamicoace;

	public Menu_DinamicoBean() {
		visible = false;
		pagina_elegida = "/xhtml/inicio.xhtml";
	}

	/**
	 * Proceso inicial de reconocimiento de usuario y la posterior consulta de
	 * sus tareas. Dicho proceso solo se hace una vez por usuario.<br/>
	 * A continuacion se carga el menu en otro metodo.
	 */
	public void crear_Menu() {
		// COGER EL USUARIO DE LA SESION
		Usuarios usuario = (Usuarios) Acceso_Contextos.getSesion()
				.getAttribute("usuario");
		// REALIZAR LA CONSULTA DE LAS TAREAS
		if (usuario != null) {
			lista_tareas = Acceso_ApplicationContext.getBean(
					IGestion_Usuario.class).consultar_Tareas(
					usuario.getNombreUsuario());
			// CREAR EL CONTENIDO DEL MENU
			this.cargar_Menu();
		}
	}

	/**
	 * Proceso de creacion del contenido del menu.
	 */
	public void cargar_Menu() {
		// PREPARAMOS LOS OBJETOS DEL PROCESO
		lista_tareas_traducidas = new ArrayList<Tareas_Idioma>();
		opciones_menu = new ArrayList<MenuItem>();
		// RESOLVEMOS LA IDIOMATIZACION DEL MENU
		String idioma_elegido = ((Idioma_Bean) Acceso_Contextos.getSesion()
				.getAttribute("idioma_bean")).getIdioma_elegido();
		ResourceBundle rb = ResourceBundle.getBundle(idioma_elegido);
		// CARGAMOS LA TRADUCCION DE LAS TAREAS
		Tareas_Idioma nueva_tarea_traducida;
		for (Tareas tarea : lista_tareas) {
			nueva_tarea_traducida = new Tareas_Idioma();
			nueva_tarea_traducida.setCodigoTarea(tarea.getCodigoTarea());
			nueva_tarea_traducida.setDescripcionTarea(tarea
					.getDescripcionTarea());
			nueva_tarea_traducida.setVinculo(tarea.getVinculo());
			nueva_tarea_traducida.setTraduccion(rb.getString("menu.opcion."
					+ tarea.getDescripcionTarea().replace(" ", "")));
			lista_tareas_traducidas.add(nueva_tarea_traducida);
		}
		// ORDENAMOS LA COLECCION POR LA TRADUCCION DEL TEXTO
		Collections.sort(lista_tareas_traducidas, this);
		Acceso_Contextos.getSesion().setAttribute("lista_tareas_traducidas",
				lista_tareas);
		// OPCION COMPONENTES ACE
		menu_dinamicoace = new DefaultMenuModel();
		Submenu listados = new Submenu();
		listados.setId("m_listados");
		listados.setLabel(rb.getString("menu.submenu.listados"));
		Submenu administracion = new Submenu();
		administracion.setId("m_administracion");
		administracion.setLabel(rb.getString("menu.submenu.administracion"));
		Submenu negocio = new Submenu();
		negocio.setId("m_negocio");
		negocio.setLabel(rb.getString("menu.submenu.negocio"));
		menu_dinamicoace.addSubmenu(administracion);
		menu_dinamicoace.addSubmenu(listados);
		menu_dinamicoace.addSubmenu(negocio);
		MenuItem opcion_menu;
		for (Tareas_Idioma tarea : lista_tareas_traducidas) {
			opcion_menu = new MenuItem();
			// texto menu
			opcion_menu.setValue(tarea.getTraduccion());
			// metodo_accion.
			opcion_menu.addActionListener(this);
			// id del componente
			opcion_menu.setId(tarea.getDescripcionTarea().replace(" ", ""));
			opcion_menu.getAttributes().put("ruta_pagina",
					tarea.getVinculo().replace(".jsp", ".xhtml"));
			// añadimos a la coleccion la nueva opcion de menu
			boolean otros = true;
			if (tarea.getVinculo().startsWith("listado")) {
				listados.getChildren().add(opcion_menu);
				otros = false;
			}
			if (tarea.getVinculo().startsWith("administra")
					|| tarea.getVinculo().startsWith("historic")) {
				administracion.getChildren().add(opcion_menu);
				otros = false;
			}
			if (otros) {
				negocio.getChildren().add(opcion_menu);
			}
		}
	}

	/**
	 * Proceso de evento de menu para resolver la pagina a mostrar segun la
	 * eleccion del usuario en el menu.
	 */
	public void processAction(ActionEvent evento)
			throws AbortProcessingException {
		String opcion = "/xhtml/"
				+ evento.getComponent().getAttributes().get("ruta_pagina");
//		System.out.println(opcion);
		setPagina_elegida(opcion);
	}

	/**
	 * Ordenacion del menu.
	 */
	public int compare(Tareas_Idioma tarea1, Tareas_Idioma tarea2) {
		int comparacion = 0;
		comparacion = tarea1.getTraduccion().compareTo(tarea2.getTraduccion());
		return comparacion;
	}

	// ACCESORES PARA SPRING
	public List<MenuItem> getOpciones_menu() {
		return opciones_menu;
	}

	public void setOpciones_menu(List<MenuItem> opcionesMenu) {
		opciones_menu = opcionesMenu;
	}

	public String getPagina_elegida() {
		return pagina_elegida;
	}

	public void setPagina_elegida(String paginaElegida) {
		pagina_elegida = paginaElegida;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public MenuModel getMenu_dinamicoace() {
		return menu_dinamicoace;
	}

	public void setMenu_dinamicoace(MenuModel menu_dinamicoace) {
		this.menu_dinamicoace = menu_dinamicoace;
	}

}
