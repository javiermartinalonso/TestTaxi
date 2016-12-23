package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.component.submenu.Submenu;
import org.icefaces.ace.model.DefaultMenuModel;
import org.icefaces.ace.model.MenuModel;

import com.testtaxi.dto.TipoTest;
import com.testtaxi.hibernate.Usuarios;
import com.testtaxi.util.Acceso_Contextos;

public class Menu_Bean implements ActionListener, Serializable {
	private static final long serialVersionUID = 1L;

	// CONTROL DE VISIBILIDAD DE MENU Y CUERPO DE LA APLICACION
	private boolean visible = false;
	// PROPIEDADES PARA INYECCION DE SPRING
//	private IGestion_Usuario gestion_servicio;

	// OBJETOS PARA CREAR LAS OPCIONES DE MENU Y CONTROLAR LA PAGINA A MOSTRAR
	private List<MenuItem> opciones_menu = null;
	private String pagina_elegida = null;

	// COLECCION PARA IDIOMATIZAR LAS OPCIONES DE MENU
//	private List<Tareas_Idioma> lista_tareas_traducidas;
//	private List<Tareas> lista_tareas;

	// PROPIEDAD PARA LA GENERACION DINAMICA DEL MENU CON LOS COMPONENTES ACE
	private MenuModel menu_dinamicoace = null;
	
	//Usuario para obtener su información de la sesión
	Usuarios usuario=null;
	
	
	private String tipoTestelegido=null;

	public Menu_Bean() {
		visible = false;
		pagina_elegida = "/xhtml/fondo.xhtml";
	}

	/**
	 * Proceso inicial de reconocimiento de usuario y la posterior consulta de
	 * sus tareas. Dicho proceso solo se hace una vez por usuario.<br/>
	 * A continuacion se carga el menu en otro metodo.
	 */
	public void crear_Menu() {
		// COGER EL USUARIO DE LA SESION
		usuario = (Usuarios) Acceso_Contextos.getSesion().getAttribute("usuario");
		// REALIZAR LA CONSULTA DE LAS TAREAS
		if (usuario != null) {
//			lista_tareas = Acceso_ApplicationContext.getBean(IGestion_Usuario.class).consultar_Tareas(usuario.getNombreUsuario());
			// CREAR EL CONTENIDO DEL MENU
			this.cargar_Menu();
		}
	}

	/**
	 * Proceso de creacion del contenido del menu.
	 */
	public void cargar_Menu() {
		// RESOLVEMOS LA IDIOMATIZACION DEL MENU
		String idioma_elegido = ((Idioma_Bean) Acceso_Contextos.getSesion().getAttribute("idioma_bean")).getIdioma_elegido();
		ResourceBundle rb = ResourceBundle.getBundle(idioma_elegido);
		
		
		// PREPARAMOS LOS OBJETOS DEL PROCESO
//		lista_tareas_traducidas = new ArrayList<Tareas_Idioma>();
		opciones_menu = new ArrayList<MenuItem>();

		// CARGAMOS LA TRADUCCION DE LAS TAREAS
//		Tareas_Idioma nueva_tarea_traducida;
//		for (Tareas tarea : lista_tareas) {
//			nueva_tarea_traducida = new Tareas_Idioma();
//			nueva_tarea_traducida.setCodigoTarea(tarea.getCodigoTarea());
//			nueva_tarea_traducida.setDescripcionTarea(tarea
//					.getDescripcionTarea());
//			nueva_tarea_traducida.setVinculo(tarea.getVinculo());
//			nueva_tarea_traducida.setTraduccion(rb.getString("menu.opcion."
//					+ tarea.getDescripcionTarea().replace(" ", "")));
//			lista_tareas_traducidas.add(nueva_tarea_traducida);
//		}
		// ORDENAMOS LA COLECCION POR LA TRADUCCION DEL TEXTO
//		Collections.sort(lista_tareas_traducidas, this);
//		Acceso_Contextos.getSesion().setAttribute("lista_tareas_traducidas",lista_tareas);
		
		// OPCION COMPONENTES ACE
		//MENU DE OPCIONES
		menu_dinamicoace = new DefaultMenuModel();
		
		
		//MENU DESPLEGABLE ADMINISTRACION
		Submenu administracion = new Submenu();
		administracion.setId("submenu_administracion");
		administracion.setLabel(rb.getString("menu.submenu.administracion"));
		
		
		//  ******* ELEMENTOS SUBMENU ADMINISTRACION *******
		//GESTION_CRUD_USUARIOS
		MenuItem opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("Gestion_Usuarios");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("gestion_usuarios");
		// vinculo
//		opcion_menu.getAttributes().put("ruta_pagina", "administracion/administracion.xhtml");
		opcion_menu.getAttributes().put("ruta_pagina", "usuarios/usuarios_gestion.xhtml");		

		//AÑADO LA OPCION	
		administracion.getChildren().add(opcion_menu);
		
		//GESTION_CRUD_TESTS
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("Gestion_Tests");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("gestion_tests");
		// vinculo
//		if (usuario.getUsername().equals("admin"))
			opcion_menu.getAttributes().put("ruta_pagina", "administracion/administracion.xhtml");		
//			opcion_menu.getAttributes().put("ruta_pagina", "test/test_gestion.xhtml");
//		else		
//			opcion_menu.getAttributes().put("ruta_pagina", "sin_uso/test_gestion.xhtml");
		
		//AÑADO LA OPCION	
		administracion.getChildren().add(opcion_menu);
		//  ******* ELEMENTOS SUBMENU ADMINISTRACION *******		
		
		
			
				
		//MENU_ITEM TESTS	
//		// texto menu
//		MenuItem opcion_menu_test = new MenuItem();
//		opcion_menu_test.setValue("Lista_Tests");
//		// metodo_accion.
//		opcion_menu_test.addActionListener(this);
//		// id del componente
//		opcion_menu_test.setId("lista_tests");
//		// vinculo
//		opcion_menu_test.getAttributes().put("ruta_pagina", "test/test_generico.xhtml");
////		opcion_menu_test.setUrl("javascript:cambioPagina('test/test_comp.xhtml');countDown(0, 5, 0);countUp(0, 0, 0)");
////		opcion_menu_test.setOnclick("countDown(0, 5, 0);countUp(0, 0, 0)");
////		opcion_menu_test.getAttributes().put("ruta_pagina", "lista_tests.xhtml");
			

		
		
		
		
		
		//MENU DESPLEGABLE TEST
		Submenu sub_menu_test = new Submenu();
		sub_menu_test.setId("submenu_test");
		sub_menu_test.setLabel("Lista de test disponibles");
		
		
		//  ******* ELEMENTOS SUBMENU TEST *******
		//PSICOTECNICO
		opcion_menu = new MenuItem();
		
		
		
//		opcion_menu.
		
		
		
		// texto menu
		opcion_menu.setValue(TipoTest.STR_PSICOTECNICO);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_PSICOTECNICO);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		//PLANOS
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue(TipoTest.STR_PLANOS);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_PLANOS);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		//LEGISLACION
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue(TipoTest.STR_LEGISLACION);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_LEGISLACION);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		//TARIFAS
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue(TipoTest.STR_TARIFAS);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_TARIFAS);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		//ITINERARIOS
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue(TipoTest.STR_ITINERARIOS);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_ITINERARIOS);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		//PUNTOS_DE_INTERES
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue(TipoTest.STR_PUNTOS_DE_INTERES);
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("PUNTOS_DE_INTERES");
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "test/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		
		

		
		//EXAMEN
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("SIMULADOR EXAMEN");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId(TipoTest.STR_EXAMEN);
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "examen/lista.xhtml");
		//AÑADO LA OPCION	
		sub_menu_test.getChildren().add(opcion_menu);
		//  ******* FIN ELEMENTOS SUBMENU TEST *******

		
		
		//MENU DESPLEGABLE AREA_PRIVADA
		Submenu area_privada = new Submenu();
		area_privada.setId("submenu_area_privada");
//		area_privada.setLabel(rb.getString("menu.submenu.area_privada"));
		area_privada.setLabel("Area Privada");
		
		//  ******* ELEMENTOS SUBMENU AREA_PRIVADA *******
		//DATOS PERSONALES
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("Datos_Personales");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("datos_personales");
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "area_personal/area_personal.xhtml");
		//AÑADO LA OPCION	
		area_privada.getChildren().add(opcion_menu);
		
		//ESTADISTICAS PERSONALES
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("Estadisticas");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("estadisticas");
		// vinculo
		opcion_menu.getAttributes().put("ruta_pagina", "estadisticas/estadisticas.xhtml");
		//AÑADO LA OPCION	
		area_privada.getChildren().add(opcion_menu);
		//  ******* ELEMENTOS SUBMENU AREA_PRIVADA *******
				
				
		
		
		
		//SALIR DESCONECTAR
		opcion_menu = new MenuItem();
		// texto menu
		opcion_menu.setValue("Salir / Desconectar");
		// metodo_accion.
		opcion_menu.addActionListener(this);
		// id del componente
		opcion_menu.setId("Salir");
		// vinculo
//		opcion_menu.getAttributes().put("ruta_pagina", "mensajes/mensajes.xhtml");
		
		opcion_menu.getAttributes().put("ruta_pagina", "fondo.xhtml");
		//AÑADO LA OPCION	
		area_privada.getChildren().add(opcion_menu);
		//  ******* ELEMENTOS SUBMENU AREA_PRIVADA *******
		
		
		
		//AÑADO LAS OPCIONES AL MENU DINÁMICO
		if(usuario.getRol().equals("Administrador"))
		{
			menu_dinamicoace.addSubmenu(administracion);	
		}

		menu_dinamicoace.addMenuItem(sub_menu_test);
		menu_dinamicoace.addSubmenu(area_privada);
	}

	/**
	 * Proceso de evento de menu para resolver la pagina a mostrar segun la
	 * eleccion del usuario en el menu.
	 */
	public void processAction(ActionEvent evento) throws AbortProcessingException {
		String boton_elegido = evento.getComponent().getId();
		
		if (boton_elegido.equals("Salir"))
		{
			accion_salir();
		}
		else
		{		

			Test_lista_Bean lista = ((Test_lista_Bean) Acceso_Contextos.getAtributo("test_lista_bean"));
			
			if (lista!=null)
			{
				lista.cargarDatos();
			}
			
			if (boton_elegido.equals(TipoTest.STR_PSICOTECNICO))
			{
				tipoTestelegido=TipoTest.STR_PSICOTECNICO;
			}else			
			if (boton_elegido.equals(TipoTest.STR_PLANOS))
			{
				tipoTestelegido=TipoTest.STR_PLANOS;
			}else				
			if (boton_elegido.equals(TipoTest.STR_LEGISLACION))
			{
				tipoTestelegido=TipoTest.STR_LEGISLACION;
			}else				
			if (boton_elegido.equals(TipoTest.STR_TARIFAS))
			{
				tipoTestelegido=TipoTest.STR_TARIFAS;
			}else				
			if (boton_elegido.equals(TipoTest.STR_ITINERARIOS))
			{
				tipoTestelegido=TipoTest.STR_ITINERARIOS;
			}else				
			if (boton_elegido.equals("PUNTOS_DE_INTERES"))
			{
				tipoTestelegido=TipoTest.STR_PUNTOS_DE_INTERES;
			}else				
			if (boton_elegido.equals(TipoTest.STR_EXAMEN))
			{
				tipoTestelegido=TipoTest.STR_EXAMEN;
			}
					
			
			String opcion = "/xhtml/" + evento.getComponent().getAttributes().get("ruta_pagina");
			setPagina_elegida(opcion);				
			
			FacesContext context = FacesContext.getCurrentInstance();
			String viewId = context.getViewRoot().getViewId();
			ViewHandler handler = context.getApplication().getViewHandler();
			UIViewRoot root = handler.createView(context, viewId);
			root.setViewId(viewId);
			context.setViewRoot(root);

		}
	}

//	/**
//	 * Ordenacion del menu.
//	 */
//	public int compare(Tareas_Idioma tarea1, Tareas_Idioma tarea2) {
//		int comparacion = 0;
//		comparacion = tarea1.getTraduccion().compareTo(tarea2.getTraduccion());
//		return comparacion;
//	}

	
	
	
	
	
	public void accion_salir() {
		
		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
		
		mensaje.setAccion("exit");
		
		mensaje.cargar_Mensaje("Salir", "¿Realmente deseas salir?", "/resources/images/salir/exit_3.png", "Desconectar", "", "Cancelar", ""); 
	
		
//		setPagina_elegida("/xhtml/fondo.xhtml");
	}
	
	
	public void accion_area_personal() {
		setPagina_elegida("/xhtml/area_personal/area_personal.xhtml");
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

	public String getTipoTestelegido() {
		return tipoTestelegido;
	}

	public void setTipoTestelegido(String tipoTestelegido) {
		this.tipoTestelegido = tipoTestelegido;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	

}
