package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.icefaces.ace.event.SelectEvent;
import org.icefaces.ace.event.UnselectEvent;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.modelo.I_Tests;
import com.testtaxi.spring.Acceso_ApplicationContext;
import com.testtaxi.util.Acceso_Contextos;

public class Test_Bean implements Serializable {
	
	private static final long serialVersionUID = 4571360607939557573L;

	// CONTROL DE ACTIVACION DE BOTONES
	private boolean activar_BMIL = true;
	private boolean activar_alta = true;
//	private boolean activar_print;
	
//	private boolean test_seleccionado = false;

	// COLECCION PARA LA CARGA DEL COMPONENTE EN LA PAGINA
	private List<SelectItem> lista_tipo_test;
	
	//COLECCION PARA LA CARGA DE LA TABLA DE TESTS
	private List<Tests> lista_tests;
	
	
	private Tests test;
	private Tests test_desconectado;
	
	// PROPIEDAD PARA GESTION DE TESTS
	public I_Tests f_tests;
		
	private Mensajes_Bean mensaje;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Test_Bean.class);

	public Test_Bean() {	
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS LA GESTION DE TEST ******------");
		
		// VALORES POR DEFECTO EN EL FORMULARIO
		this.accion_Reiniciar();
		
		cargar_lista_tests();
		
		mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
	}	
	
	
	public void cargar_lista_tests() {
		//CARGAMOS LA LISTA DE TESTs
		f_tests = Acceso_ApplicationContext.getBean(I_Tests.class);
		List<Tests> lista = f_tests.consultar_todos();
				
		// CREAMOS LA COLECCION PARA LOS TEST SELECCIONABLES DE LA TABLA
		setLista_tests(new ArrayList<Tests>(0));
		// CARGAMOS LA TABLA CON LOS VENCIMIENTOS ENCONTRADOS
		lista_tests.addAll(lista);
	}	


	public void accion_Reiniciar() {
		// PROPIEDADES DEL FORMULARIO
		//CARGAMOS EL DESPLEGABLE CON LOS TIPOS DE TESTS
		cargarListaTipoTests();	
		//CARGAMOS UN TEST NUEVO DESCONECTADO
		test = new Tests();
		
		Short dificultad = 3;
		test.setDificultad(dificultad);
				
		// ACTIVACION DE BOTONES
		activar_BMIL = true;
		activar_alta = false;
	}
	

	private void cargarListaTipoTests() {
		lista_tipo_test = new ArrayList<SelectItem>();
		
		SelectItem tipo_test;
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("PSICOTECNICO");
		tipo_test.setLabel("PSICOTECNICO");
		tipo_test.setValue("PSICOTECNICO");
		lista_tipo_test.add(tipo_test);
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("PLANOS");
		tipo_test.setLabel("PLANOS");
		tipo_test.setValue("PLANOS");
		lista_tipo_test.add(tipo_test);
				
		tipo_test = new SelectItem();
		tipo_test.setDescription("LEGISLACION");
		tipo_test.setLabel("LEGISLACION");
		tipo_test.setValue("LEGISLACION");
		lista_tipo_test.add(tipo_test);
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("TARIFAS");
		tipo_test.setLabel("TARIFAS");
		tipo_test.setValue("TARIFAS");
		lista_tipo_test.add(tipo_test);
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("ITINERARIOS");
		tipo_test.setLabel("ITINERARIOS");
		tipo_test.setValue("ITINERARIOS");
		lista_tipo_test.add(tipo_test);
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("PUNTOS DE INTERES");
		tipo_test.setLabel("PUNTOS DE INTERES");
		tipo_test.setValue("PUNTOS DE INTERES");
		lista_tipo_test.add(tipo_test);
		
		tipo_test = new SelectItem();
		tipo_test.setDescription("EXAMEN");
		tipo_test.setLabel("EXAMEN");
		tipo_test.setValue("EXAMEN");
		lista_tipo_test.add(tipo_test);
	}
	
	
	public void tratar_Seleccion(SelectEvent evento) {
//		System.out.println("El usuario seleccionado es .."
//				+ ((Usuarios) evento.getObject()).getUsername());
		
//		if (test_seleccionado)
//		{
//			accion_Reiniciar();
//			test_seleccionado = false;
//		}
//		else
//		{
//			test_seleccionado = true;
			test.setId(((Tests) evento.getObject()).getId());
			consulta_test();			
//		}
		
	}
	
	public void tratar_Deseleccion(UnselectEvent evento) {
//		System.out.println("La fila que se deselecciona es .."
//				+ evento.getRow());
		accion_Reiniciar();
//		test_seleccionado = false;
	}
	
	
	
	
	// ******** TRATAMIENTO DE ACCIONES DE LOS BOTONES ************
	// RESOLUCION DEL EVENTO DE LOS BOTONES DEL FORMULARIO
	public void tratar_Accion(ActionEvent evento) {
		String boton_elegido = evento.getComponent().getId();

		if (boton_elegido.equalsIgnoreCase("bot_alta")) {
			this.alta_test();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_baja")) {
			this.baja_test();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_consultas")) {
			this.consulta_test();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_modificaciones")) {
			this.modificacion_test();
		}else
//		if (boton_elegido.equalsIgnoreCase("bot_imprimir")) {
//			this.imprimir_test();
//		}else
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
	
	
	
	
	// ***** PROCESOS CRUD TESTS
	public void alta_test() {
		Tests test_nuevo = new Tests();
		// CARGAMOS LAS PROPIEDADES DEL TEST
		
		//TODO ESTAS TRES PROPIEDADES NO LAS CONTENPLAMOS ACTUALMENTE
		test_nuevo.setDificultad(test.getDificultad());
//		test_nuevo.setId(id);	
		
		test_nuevo.setDescripcion(test.getDescripcion());
		test_nuevo.setDuracion(test.getDuracion());
		test_nuevo.setNumPreguntas(test.getNumPreguntas());
//		test_nuevo.setPreguntases(preguntases);
		test_nuevo.setPuntosAprobado(test.getPuntosAprobado());
		test_nuevo.setTipo(test.getTipo());		
		
		
			
		try {
			f_tests.alta_Test(test_nuevo);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_tests();
			mensaje.cargar_Mensaje("Alta Correcta", "El test se dio de alta de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR			
			mensaje.cargar_Mensaje("Alta ERROR", "Error en el alta deL test: ", "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void baja_test() {
//		Usuarios usuario_baja = new Usuarios();
//		// CARGAMOS LAS PROPIEDADES DEL USUARIO
//		usuario_baja.setNombre(user.getUsername());

//		String user_name = user.getUsername();
		
		try {

			f_tests.baja_Test(test); 
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_tests();
			mensaje.cargar_Mensaje("Baja correcta", "El test se dio de baja de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Baja ERROR", "Error en la baja del test", "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void consulta_test() {
		
		
		try {
			
			//OBTENGO EL TEST		
			test = f_tests.consultar_PorClave(test.getId());
			String str_mensaje = "";
			
//			String test_descripcion = test.getDescripcion();
//			
//			//El usuario no ha introducido correctamente un test a buscar
//			if (test_descripcion.equals(""))
//			{	
//				str_mensaje = "Debe introducir un identificador de usuario.";
//			}
//			//No hay coincidencia en BBDD
//			else if (test==null)
//			{
//				str_mensaje = "El test no tiene coincidencias en la base de datos.";	
//			}
//			//HAY COINCIDENCIA EN BBDD
//			else
//			{
				activar_BMIL = false;
				activar_alta = true;
//			}
			
			// TRATAMIENTO MENSAJE
//			if (!str_mensaje.equals(""))
//			{
//				mensaje.cargar_Mensaje("Consulta ERROR", str_mensaje, "/resources/images/operacion_incorrecta.png","Aceptar", "");
//				accion_Reiniciar();
//			}
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Consulta ERROR", "No hay coincidencias en la base de datos. Quizas otro usuario ha dado de baja el registro mientras estabas consultando.", "/resources/images/operacion_incorrecta.png","Aceptar", "");
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//RECARGO LA LISTA DE TEST
			cargar_lista_tests();
		}
	}

	public void modificacion_test() {
		Tests test_modificacion = new Tests();
		// CARGAMOS LAS PROPIEDADES DEL TEST
		test_modificacion.setDescripcion(test.getDescripcion());;
		test_modificacion.setDificultad(test.getDificultad());
		test_modificacion.setDuracion(test.getDuracion());
		test_modificacion.setId(test.getId());		
		test_modificacion.setNumPreguntas(test.getNumPreguntas());
		test_modificacion.setPreguntases(test.getPreguntases());
		test_modificacion.setPuntosAprobado(test.getPuntosAprobado());
		test_modificacion.setTipo(test.getTipo());
		
		try {
			f_tests.modificacion_Test(test_modificacion);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_tests();
			mensaje.cargar_Mensaje("Modificacion INFO", "El test se modifico de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Modificacion ERROR", "Error en modificacion del test", "/resources/images/operacion_incorrecta.png","Aceptar", "");
			
			activar_BMIL = false;
		}
	}

	
	
	public void accion_Salir() {
		Menu_Bean menu = (Menu_Bean) Acceso_Contextos.getAtributo("menu_bean");
		menu.setPagina_elegida("/xhtml/fondo.xhtml");
		
//		menu.accion_salir();
	}
	
	
	
	//ACCESORES PARA JSF
	public List<SelectItem> getLista_tipo_test() {
		return lista_tipo_test;
	}


	public void setLista_tipo_test(List<SelectItem> lista_tipo_test) {
		this.lista_tipo_test = lista_tipo_test;
	}


	public List<Tests> getLista_tests() {
		return lista_tests;
	}


	public void setLista_tests(List<Tests> lista_tests) {
		this.lista_tests = lista_tests;
	}


	public Tests getTest() {
		return test;
	}


	public void setTest(Tests test) {
		this.test = test;
	}


	public Tests getTest_desconectado() {
		return test_desconectado;
	}


	public void setTest_desconectado(Tests test_desconectado) {
		this.test_desconectado = test_desconectado;
	}


	public Mensajes_Bean getMensaje() {
		return mensaje;
	}


	public void setMensaje(Mensajes_Bean mensaje) {
		this.mensaje = mensaje;
	}

		
	public boolean isActivar_alta() {
		return activar_alta;
	}


	public void setActivar_alta(boolean activar_alta) {
		this.activar_alta = activar_alta;
	}
	
	
	public boolean isActivar_BMIL() {
		return activar_BMIL;
	}


	public void setActivar_BMIL(boolean activar_BMIL) {
		this.activar_BMIL = activar_BMIL;
	}


	// ACCESORES PARA SPRING
	public void setF_tests(I_Tests f_tests) {
		this.f_tests = f_tests;
	}

}
