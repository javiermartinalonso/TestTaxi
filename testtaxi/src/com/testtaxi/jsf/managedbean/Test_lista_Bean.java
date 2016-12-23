package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.testtaxi.dto.Respuesta;
import com.testtaxi.dto.TipoTest;
import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.modelo.I_Tests;
import com.testtaxi.spring.Acceso_ApplicationContext;
import com.testtaxi.util.Acceso_Contextos;

public class Test_lista_Bean implements Serializable, Comparator<Tests> {

	private static final long serialVersionUID = 7435024357107000082L;

	//TIPO INTEGER LISTA TEST
	private String tipo_lista = null;

	//TITULO LISTA TEST
	private String titulo_lista = null;
	
	//DESCRIPCION LISTA TEST
	private String descripcion_lista = null;
	

	//LISTA TESTS
	private List<Tests> lista_test = null;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Test_lista_Bean.class);



	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public Test_lista_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS EL TEST GENERICO ******------");
				
		cargarDatos();
		
//		FacesContext context = FacesContext.getCurrentInstance();
//		String viewId = context.getViewRoot().getViewId();
//		ViewHandler handler = context.getApplication().getViewHandler();
//		UIViewRoot root = handler.createView(context, viewId);
//		root.setViewId(viewId);
//		context.setViewRoot(root);
		
//		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));	
//		mensaje.cargar_Mensaje("Comenzar test", "Comenzar test", "/resources/images/info/info-7.png", "Comenzar Test", "countDown(0, 5, 0);countUp(0, 0, 0)");
	}

	
	public void cargarDatos() {			    
		//OBTENEMOS LA LISTA DE TESTS DEL TIPO SELECCIONADO
//		ApplicationContext contexto = new ClassPathXmlApplicationContext("/com/testtaxi/spring/modelo.xml");
		I_Tests f_tests = Acceso_ApplicationContext.getBean(I_Tests.class);
		//TODO acceder con la clase estatica para evitar crear objetos nuevos
//		IGestion_Preguntas gestionPreguntas = Acceso_ApplicationContext.getBean(IGestion_Preguntas.class);
	
//		
//		I_Tests f_tests2 = Acceso_ApplicationContext.getBean(I_Tests.class);
//		
		
		
//		tipo_lista = 1;
		
		
		Menu_Bean menu = ((Menu_Bean) Acceso_Contextos.getAtributo("menu_bean"));
		tipo_lista = menu.getTipoTestelegido();
//		lista_test = new Test_lista_Bean();
		lista_test = f_tests.consultar_PorTipo(tipo_lista);
		
		//ORDENAMOS LA LISTA DE TESTS
		Collections.sort(lista_test, this);
//		
//		
//		List<Tests>lista_test2 = f_tests2.consultar_PorTipo(tipo_lista);
//		
//		
		
		
		
		//DESCRIPCION DEL TEST
		descripcion_lista="";
			
		//titulo_lista=tipo_lista;
		
		// OBTENEMOS EL TIPO DE TEST
		if (tipo_lista.equals(TipoTest.STR_PSICOTECNICO))
		{
			titulo_lista = TipoTest.STR_PSICOTECNICO;

			descripcion_lista = "cabecera_psicotecnico";
		}else			
		if (tipo_lista.equals(TipoTest.STR_PLANOS))
		{
			titulo_lista = TipoTest.STR_PLANOS;
			
			descripcion_lista = "cabecera_planos";
		}else				
		if (tipo_lista.equals(TipoTest.STR_LEGISLACION))
		{
			titulo_lista = TipoTest.STR_LEGISLACION;
			
			descripcion_lista = "cabecera_legislacion";
		}else				
		if (tipo_lista.equals(TipoTest.STR_TARIFAS))
		{
			titulo_lista = TipoTest.STR_TARIFAS;
			
			descripcion_lista = "cabecera_tarifas";
		}else				
		if (tipo_lista.equals(TipoTest.STR_ITINERARIOS))
		{
			titulo_lista = TipoTest.STR_ITINERARIOS;
			
			descripcion_lista = "cabecera_itinerarios";
		}else				
		if (tipo_lista.equals(TipoTest.STR_PUNTOS_DE_INTERES))
		{
			titulo_lista = TipoTest.STR_PUNTOS_DE_INTERES;
			
			descripcion_lista = "cabecera_puntos_interes";
		}else				
		if (tipo_lista.equals(TipoTest.STR_EXAMEN))
		{
			titulo_lista = TipoTest.STR_EXAMEN;
			
			descripcion_lista = "cabecera_examen";
		}
		
		/*
		switch (tipo_lista) {
		case TipoTest.PSICOTECNICO:
			titulo_lista = TipoTest.STR_PSICOTECNICO;

			descripcion_lista = "cabecera_psicotecnico";
			
		break;
		case TipoTest.PLANOS:
			titulo_lista = TipoTest.STR_PLANOS;
			
			descripcion_lista = "cabecera_planos";			
		break;
		case TipoTest.LEGISLACION:
			titulo_lista = TipoTest.STR_LEGISLACION;
			
			descripcion_lista = "cabecera_legislacion";
		break;
		case TipoTest.TARIFAS:
			titulo_lista = TipoTest.STR_TARIFAS;
			
			descripcion_lista = "cabecera_tarifas";
		break;
		case TipoTest.ITINERARIOS:
			titulo_lista = TipoTest.STR_ITINERARIOS;
			
			descripcion_lista = "cabecera_itinerarios";
		break;
		case TipoTest.PUNTOS_DE_INTERES:
			titulo_lista = TipoTest.STR_PUNTOS_DE_INTERES;
			
			descripcion_lista = "cabecera_puntos_interes";
		break;		
		case TipoTest.EXAMEN:
			titulo_lista = TipoTest.STR_EXAMEN;
			
			descripcion_lista = "cabecera_examen";
		break;	
		default:
			
		break;
		}
		*/
	}

	
	
	public String accion(){
//		TestGenerico_Bean test = ((TestGenerico_Bean) Acceso_Contextos.getAtributo("test_generico"));
//		test.cargarDatos();
//		test.setVisible(true);
		//MUESTRO LA PAGINA DE RESULTADOS
		return "/xhtml/test/test.xhtml";
	}

	
	/**
	 * Ordenacion de los tests.
	 */
	public int compare(Tests test1, Tests test2) {
		int comparacion = 0;
		comparacion = test1.getDescripcion().compareTo(test2.getDescripcion());
		return comparacion;
	}
	
	// ACCESORES PARA JSF
	public String getTitulo_lista() {
		return titulo_lista;
	}


	public void setTitulo_lista(String titulo_lista) {
		this.titulo_lista = titulo_lista;
	}


	public String getDescripcion_lista() {
		return descripcion_lista;
	}


	public void setDescripcion_lista(String descripcion_lista) {
		this.descripcion_lista = descripcion_lista;
	}


	public List<Tests> getLista_test() {
		return lista_test;
	}


	public void setLista_test(List<Tests> lista_test) {
		this.lista_test = lista_test;
	}


	public String getTipo_lista() {
		return tipo_lista;
	}


	public void setTipo_lista(String tipo_lista) {
		this.tipo_lista = tipo_lista;
	}
}
