package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.testtaxi.dto.Respuesta;
import com.testtaxi.dto.TipoTest;
import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.modelo.I_Preguntas;
import com.testtaxi.spring.Acceso_ApplicationContext;

public class ExamenGenerico_Bean implements Serializable, Comparator<Respuesta>{
	private static final long serialVersionUID = 1L;
	//OBJETOS TEST
	// TEST EN CURSO
	private Tests test_actual = null;
	
	// INFORMACION DE CADA TEST
	private Tests test_psicotecnico = null;
	private Tests test_planos = null;
	private Tests test_legislacion = null;
	private Tests test_tarifas = null;
	private Tests test_itinerarios = null;
	private Tests test_puntos_interes = null;	
	
	//PREGUNTAS DE CADA TEST
	private Set<Preguntas> preguntas_psicotecnico = new HashSet<Preguntas>(0);
	private Set<Preguntas> preguntas_planos = new HashSet<Preguntas>(0);
	private Set<Preguntas> preguntas_legislacion = new HashSet<Preguntas>(0);
	private Set<Preguntas> preguntas_tarifas = new HashSet<Preguntas>(0);
	private Set<Preguntas> preguntas_itinerarios = new HashSet<Preguntas>(0);
	private Set<Preguntas> preguntas_puntos_interes = new HashSet<Preguntas>(0);
	
	//LISTA DE RESPUESTAS CORRECTAS DEL EXAMEN
	private List<String> lista_respuestas_correctas_psicotecnico = null;	
	private List<String> lista_respuestas_correctas_planos = null;
	private List<String> lista_respuestas_correctas_legislacion = null;
	private List<String> lista_respuestas_correctas_tarifas = null;
	private List<String> lista_respuestas_correctas_itinerarios = null;
	private List<String> lista_respuestas_correctas_puntos_interes = null;
	
	//LISTA RESPUESTAS DEL EXAMEN
	private List<Respuesta> lista_respuestas_psicotecnico = null;
	private List<Respuesta> lista_respuestas_planos = null;
	private List<Respuesta> lista_respuestas_legislacion = null;
	private List<Respuesta> lista_respuestas_tarifas = null;
	private List<Respuesta> lista_respuestas_itinerarios = null;
	private List<Respuesta> lista_respuestas_puntos_interes = null;
	
	//INFO DEL RESULTADO DEL EXAMEN
	//COMENTARIO RESULTADO TEST
	private String comentario_resultado_psicotecnico;
	private String comentario_resultado_planos;
	private String comentario_resultado_legislacion;
	private String comentario_resultado_tarifas;
	private String comentario_resultado_itinerarios;
	private String comentario_resultado_puntos_interes;
	
	//PUNTUACION DEL TEST
	private Double puntuacion_psicotecnico = 0.00;
	private Double puntuacion_planos = 0.00;
	private Double puntuacion_legislacion = 0.00;
	private Double puntuacion_tarifas = 0.00;
	private Double puntuacion_itinerarios = 0.00;
	private Double puntuacion_puntos_interes = 0.00;
	
	//RESULTADO DEL TEST
	private String resultado_psicotecnico = "SUSPENDIDO";
	private String resultado_planos = "SUSPENDIDO";
	private String resultado_legislacion = "SUSPENDIDO";
	private String resultado_tarifas = "SUSPENDIDO";
	private String resultado_itinerarios = "SUSPENDIDO";
	private String resultado_puntos_interes = "SUSPENDIDO";
	
	//RESULTADO DEL EXAMEN	
	private String resultado_examen= "SUSPENDIDO";
		
	//PARA CONTROLAR LA LISTA RESPUESTAS DEL TEST ACTUAL
	private List<Respuesta> lista_respuestas_actual = null;
	
	// PARA CONTROLAR LA PREGUNTA EN CURSO
	private Respuesta respuesta_actual = null;
	private int num_pregunta_visor = 0;
	

	// CONTROL DE ACTIVACION DE BOTONES
	private String texto_boton = null;
	private boolean activar_anterior = true;
	private boolean activar_siguiente = true;
	
	// CONTROL DEL TIEMPO TOTAL DEL EXAMEN
	//FECHA COMIENZO EXAMEN
	private String fecha_comienzo = null;
	private Date date_comienzo = null;
	//FECHA FINALIZACION EXAMEN
	private String fecha_finalizacion = null;	
	private Date date_fin = null;
	//TIEMPO TOTAL REALIZACION TEST
	private String tiempo_realizacion = null; 
	
	
	//DURACION DE TEST EN CURSO
	private String duracion = null;
	//DURACION SIGUIENTE TEST EN CURSO
	private String duracion_siguiente = null;
	
	
	//CONTROL VISOR NUMERO DE PREGUNTAS
	private Integer num_preguntas = 0;
	//TODO falta gestionar por ajax que cuando se seleccione una pregunta se actualice el visor de navegacion de preguntas
	//TODO faltaria también añadir una navegación directa a la pregunta concreta e indicar cuales está y cuales no contestadas.
	
	
	//RUTA DE LAS IMAGENES EN EL SERVIDOR
	private static final String FILE_PATH = "/resources/images/test/";
//	private static final String FILE_PATH_RESULTADO = "/resources/images/";
	
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(ExamenGenerico_Bean.class);

	private boolean correccion_visible;
	private boolean examen_visible;
	private boolean barra_visible;
	

	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public ExamenGenerico_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS EL EXAMEN GENERICO ******------");
						
		correccion_visible = false;
		examen_visible = false;
		barra_visible = true;
		
		//cargarDatos();
	}

		
	public void cargarDatos() {	
		
		//OBTENEMOS LONG DE INICIO
		date_comienzo = new Date();
		fecha_comienzo = get_fecha_actual();
		    
		//MONTAMOS LAS DISTINTAS SECCIONES DEL EXAMEN		
		I_Preguntas f_preguntas = Acceso_ApplicationContext.getBean(I_Preguntas.class);
	
		// CONJUNTO DE PREGUNTAS ALEATORIAS PARA MONTAR CADA EXAMEN
		preguntas_psicotecnico = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(50, "PSICOTECNICO"));
		preguntas_planos = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(6, "PLANOS"));
		preguntas_legislacion = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(15, "LEGISLACION"));
		preguntas_tarifas = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(30, "TARIFAS"));
		preguntas_itinerarios = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(12, "ITINERARIOS"));
		preguntas_puntos_interes = new HashSet<Preguntas>(f_preguntas.consultar_ListaPreguntasAleatoriaPorTipo(12, "PUNTOS DE INTERES"));
		
		//LISTA DE RESPUESTAS DE CADA TEST QUE FORMA EL EXAMEN
		lista_respuestas_psicotecnico = getListaRespuestas(preguntas_psicotecnico, true);
		lista_respuestas_planos = getListaRespuestas(preguntas_planos, true);
		lista_respuestas_legislacion = getListaRespuestas(preguntas_legislacion, true);
		lista_respuestas_tarifas = getListaRespuestas(preguntas_tarifas, true);
		lista_respuestas_itinerarios = getListaRespuestas(preguntas_itinerarios, true);
		lista_respuestas_puntos_interes = getListaRespuestas(preguntas_puntos_interes, true);
		
		//CONJUNTO DE RESPUESTAS CORRECTAS A CADA UNA DE LAS PREGUNTAS ANTERIORES
		lista_respuestas_correctas_psicotecnico = doListaRespuestasCorrectas(preguntas_psicotecnico);
		lista_respuestas_correctas_planos = doListaRespuestasCorrectas(preguntas_planos);
		lista_respuestas_correctas_legislacion = doListaRespuestasCorrectas(preguntas_legislacion);
		lista_respuestas_correctas_tarifas = doListaRespuestasCorrectas(preguntas_tarifas);
		lista_respuestas_correctas_itinerarios = doListaRespuestasCorrectas(preguntas_itinerarios);
		lista_respuestas_correctas_puntos_interes = doListaRespuestasCorrectas(preguntas_puntos_interes);
		
		
		//MONTO UN TEST DE TIPO PSICOTECNICO
		Short duracion = 20;
		Short dificultad = 3;
		Short numPreguntas = 50;
		Short puntosAprobado = 35;
		test_psicotecnico = new Tests("PSICOTECNICO", "PSICOTECNICO", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_psicotecnico);
		      
		//MONTO UN TEST DE TIPO PLANOS		
		duracion = 15;
		dificultad = 3;
		numPreguntas = 6;
		puntosAprobado = 4;
		test_planos = new Tests("PLANOS", "PLANOS", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_planos);

		//MONTO UN TEST DE TIPO LEGISLACION
		duracion = 15;
		dificultad = 3;
		numPreguntas = 15;
		puntosAprobado = 7;
		test_legislacion = new Tests("LEGISLACION", "LEGISLACION", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_legislacion);

		//MONTO UN TEST DE TIPO TARIFAS
		duracion = 30;
		dificultad = 3;
		numPreguntas = 30;
		puntosAprobado = 18;
		test_tarifas = new Tests("TARIFAS", "TARIFAS", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_tarifas);
		
		//MONTO UN TEST DE TIPO ITINERARIOS
		duracion = 15;
		dificultad = 3;
		numPreguntas = 12;
		puntosAprobado = 6;
		test_itinerarios = new Tests("ITINERARIOS", "ITINERARIOS", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_itinerarios); 
				
		//MONTO UN TEST DE TIPO PUNTOS DE INTERES
		duracion = 10;
		dificultad = 3;
		numPreguntas = 12;
		puntosAprobado = 6;
		test_puntos_interes = new Tests("PUNTOS DE INTERES", "PUNTOS DE INTERES", duracion, dificultad, numPreguntas, puntosAprobado, preguntas_puntos_interes); 
		
		//	PINTAMOS EL TEST PSICOTECNICO
		cargarTest(TipoTest.STR_PSICOTECNICO);
				
		
		//TODO falta el control de mostrar algo cargando la pagina antes de mostrarse
		barra_visible = false;
		correccion_visible = false;
		examen_visible = true;
	}
	
	
	/**
	 * Carga la informacion del tipo de test pasado por argumento
	 * @param tipo
	 */
	private void cargarTest(String tipo) 
	{				
		if (tipo.equals(TipoTest.STR_PSICOTECNICO))
		{
			test_actual=test_psicotecnico;
			duracion_siguiente = "15";
			pintaTestPantalla(test_psicotecnico, "terminar PSICOTECNICO y pasar a PLANOS", lista_respuestas_psicotecnico);
		}else			
		if (tipo.equals(TipoTest.STR_PLANOS))
		{
			test_actual=test_planos;
			duracion_siguiente = "15";
			pintaTestPantalla(test_planos, "terminar PLANOS y pasar a LEGISLACION", lista_respuestas_planos);
//			refreshPage();
		}else				
		if (tipo.equals(TipoTest.STR_LEGISLACION))
		{
			test_actual=test_legislacion;
			duracion_siguiente = "30";
			pintaTestPantalla(test_legislacion, "terminar LEGISLACION y pasar a TARIFAS", lista_respuestas_legislacion);
//			refreshPage();
		}else				
		if (tipo.equals(TipoTest.STR_TARIFAS))
		{
			test_actual=test_tarifas;
			duracion_siguiente = "15";
			pintaTestPantalla(test_tarifas, "terminar TARIFAS y pasar a ITINERARIOS", lista_respuestas_tarifas);
//			refreshPage();
		}else				
		if (tipo.equals(TipoTest.STR_ITINERARIOS))
		{
			test_actual=test_itinerarios;
			duracion_siguiente = "10";
			pintaTestPantalla(test_itinerarios, "terminar ITINERARIOS y pasar a PUNTOS DE INTERES", lista_respuestas_itinerarios);
//			refreshPage();
		}else				
		if (tipo.equals(TipoTest.STR_PUNTOS_DE_INTERES))
		{
			test_actual=test_puntos_interes;
			duracion_siguiente = "99";
			pintaTestPantalla(test_puntos_interes, "terminar PUNTOS DE INTERES y ver resultado del examen", lista_respuestas_puntos_interes);
//			refreshPage();
		}
	}

	
	/**
	 * Actualiza los datos del test para pintarlo por pantalla 
	 * @param test
	 * @param boton
	 * @param lista_respuestas
	 */
	private void pintaTestPantalla(Tests test, String boton, List<Respuesta> lista_respuestas)
	{
		//ESTABLECEMOS EL TEST ACTUAL
		texto_boton = boton;
		lista_respuestas_actual = lista_respuestas;
		//DURACION DEL TEST
		duracion=test.getDuracion().toString();// + " minutos";
		//NUMERO DE PREGUNTAS
		num_preguntas = test.getPreguntases().size();
		
		//DIBUJO EN EL VISOR DEL EXAMEN LA PRIMERA PREGUNTA
		num_pregunta_visor=0;
		this.respuesta_actual = lista_respuestas_actual.get(num_pregunta_visor);
		this.respuesta_actual.setIdRespuestaCorrecta("x");				
		activar_anterior = true;
		activar_siguiente = false;
	}

	/**
	 * Monta una lista de Strings con la respuesta (a, b, c, d) correcta para cada pregunta.
	 * @param set_preguntas conjunto de preguntas.
	 * @return
	 */
	private ArrayList<String> doListaRespuestasCorrectas(Set<Preguntas> set_preguntas) {
		
		//MONTO LAS LISTAS DE RESPUESTAS CORRECTAS Y RESETEO LA RESPUESTA DEL USUARIO
		ArrayList<String> lista_respuestas_correctas = null;
		
		//SI TENEMOS PREGUNTAS
		if(set_preguntas!=null)
		{
			List<Respuesta> listaRespuestas = getListaRespuestas(set_preguntas, false);
			
			//ORDENAMOS LA LISTA DE RESPUESTAS
//			Collections.sort(listaRespuestas, this);
					
			lista_respuestas_correctas = new ArrayList<String>();
			
			for (int i = 0; i < listaRespuestas.size(); i++) {
				//MONTO LA LISTA DE RESPUESTAS CORRECTAS	
				Respuesta respuesta_aux = listaRespuestas.get(i);		
				lista_respuestas_correctas.add(respuesta_aux.getIdRespuestaCorrecta());
			}		
		}
		
		return lista_respuestas_correctas;
	}


	/**
	 * Dado un conjunto de preguntas las transforma para devolver una lista de objetos
	 * que se puedan representar por pantalla. devuelve una lista con las respuestas del test
	 * @param set_preguntas
	 * @return
	 */
	private List<Respuesta> getListaRespuestas(Set<Preguntas> set_preguntas, boolean reset_respuesta_correcta) {
		//MONTO LA LISTA DE PREGUNTAS
		List<Respuesta> lista_respuestas = null;
		
		Iterator <Preguntas> iterator = set_preguntas.iterator();
		while(iterator.hasNext()) {
			
			if (lista_respuestas==null)
			{
				lista_respuestas = new ArrayList<Respuesta>();
			}
			
			//OBTENEMOS LA SIGUIENTE PREGUNTA
			Preguntas pregunta = (Preguntas) iterator.next();
									
			Respuesta respuesta = new Respuesta(pregunta);
			//GENERAMOS LOS PARÁMETROS DE LA RESPUESTA
			String foto = respuesta.getFoto();
			
			if ((foto==null)||(foto.equals("")))
			{
				respuesta.setDisplay("none");
			}
			else
			{
				respuesta.setDisplay("block");
				respuesta.setFoto(FILE_PATH.concat(foto));
			}
			
			
			//RESETEO LA RESPUESTA DEL USUARIO
			if (reset_respuesta_correcta)
			{
				respuesta.setIdRespuestaCorrecta("x");
			}

			
			//AÑADO LA RESPUESTA
			lista_respuestas.add(respuesta);
		}
		
		return lista_respuestas;
	}


	
	/*
	public void respuesta_seleccionada(AjaxBehaviorEvent evento)  {
		String seleccionado = evento.getComponent().getId();
		
//		Integer pos = Integer.parseInt(seleccionado);
		this.respuesta_actual = lista_respuestas.get(num_pregunta_visor);		
	}
	*/
	
	/**
	 * metodo de control para ir directamente a una pregunta del test en curso
	 * TODO no esta correctamente realizado, ahora no se usa
	 */
	public void respuesta_seleccionada(){
//	    String numPreguntatxt = (String)event.getComponent().getAttributes().get("num_pregunta_visor");
//	    String numPreguntatxt = FacesContext.getExternalContext().getRequestParameterMap().get("num_pregunta_visor");
//	    num_pregunta_visor = Integer.parseInt(numPreguntatxt);
//		response.setHeader("Cache-Control", "no-cache");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", 0);
		this.respuesta_actual = lista_respuestas_actual.get(num_pregunta_visor);		
	}


//	private void refreshPage() {
//		//		REFRESCAR LA PAGINA
//				FacesContext context = FacesContext.getCurrentInstance();
//				String viewId = context.getViewRoot().getViewId();
//				ViewHandler handler = context.getApplication().getViewHandler();
//				UIViewRoot root = handler.createView(context, viewId);
//				root.setViewId(viewId);
//				context.setViewRoot(root);
//	}
	
	
	/**
	 * Controla el como pintar por pantalla la pregunta anterior
	 */
	public void respuesta_siguiente()  {
		num_pregunta_visor++;
		
		if (num_pregunta_visor==lista_respuestas_actual.size()-1)
		{
			activar_siguiente = true;
		}
		activar_anterior = false;
		
		this.respuesta_actual = lista_respuestas_actual.get(num_pregunta_visor);		
	}
	
	
	
	/**
	 * Controla el como pintar por pantalla la pregunta siguiente
	 */
	public void respuesta_anterior()  {
		num_pregunta_visor--;
		if (num_pregunta_visor==0)
		{
			activar_anterior = true;
		}
		activar_siguiente = false;
		
		this.respuesta_actual = lista_respuestas_actual.get(num_pregunta_visor);		
	}
	
	
	
	/**
	 * METODO PARA CONTROLAR CON EL BOTÓN EL TEST EN CURSO DEL EXAMEN
	 */
	public void siguienteTest()
	{
		String tipoTest = test_actual.getTipo();
				
		if (tipoTest.equals(TipoTest.STR_PSICOTECNICO))
		{
			cargarTest(TipoTest.STR_PLANOS);
		}else	
		if (tipoTest.equals(TipoTest.STR_PLANOS))
		{
			cargarTest(TipoTest.STR_LEGISLACION);
		}else				
		if (tipoTest.equals(TipoTest.STR_LEGISLACION))
		{
			cargarTest(TipoTest.STR_TARIFAS);
		}else				
		if (tipoTest.equals(TipoTest.STR_TARIFAS))
		{
			cargarTest(TipoTest.STR_ITINERARIOS);
		}else				
		if (tipoTest.equals(TipoTest.STR_ITINERARIOS))
		{
			cargarTest(TipoTest.STR_PUNTOS_DE_INTERES);
		}else				
		if (tipoTest.equals(TipoTest.STR_PUNTOS_DE_INTERES))
		{
			//TERMINAR EXAMEN
			//CALCULAR RESULTADO Y PINTAR LA PANTALLA
			calcular_resultado_examen();
		}
	}
	
	
	/**
	 * Monta un String con la fecha actual del sistema
	 * @return
	 */
	private String get_fecha_actual() {
		Calendar calendario=Calendar.getInstance();
		Date fecha=calendario.getTime();
		calendario.setTime(fecha);		
		DateFormat formato= new SimpleDateFormat("EEEEEEEEE',' dd 'de' MMMMMMMMMM 'de' y',' HH:mm:ss");
		return formato.format(fecha);
	}
	
	
	 /**
	  * DEVUELVE LA PAGINA DE RESULTADO DEL TEST Y CALCULA LA PUNTUACION, RESPUESTAS CONTESTADAS POR EL USUARIO
	  * RESPUESTA CORRECTA, ETC
	  * @return
	  */
	public void calcular_resultado_examen() 
	{
		/**
		 * La Puntuación Neta se calcula de acuerdo con el siguiente baremo
		 * 		Respuestas Correctas - Suman 1 Punto
		 * 		Respuestas Incorrectas - Restan 0,1 Punto
		 * 		Preguntas sin responder - No suman ni restan Puntos
		 */
		
		calcularTiempoResolucion();		
		//OBTENEMOS FECHA DE FIN
		fecha_finalizacion = get_fecha_actual();	
				
		//INFO DEL RESULTADO DEL EXAMEN
		
		//PUNTUACION DE CADA TEST
		puntuacion_psicotecnico = getResultadoTest(lista_respuestas_correctas_psicotecnico, lista_respuestas_psicotecnico);
		puntuacion_planos = getResultadoTest(lista_respuestas_correctas_planos, lista_respuestas_planos);
		puntuacion_legislacion = getResultadoTest(lista_respuestas_correctas_legislacion, lista_respuestas_legislacion);
		puntuacion_tarifas = getResultadoTest(lista_respuestas_correctas_tarifas, lista_respuestas_tarifas);
		puntuacion_itinerarios = getResultadoTest(lista_respuestas_correctas_itinerarios, lista_respuestas_itinerarios);
		puntuacion_puntos_interes = getResultadoTest(lista_respuestas_correctas_puntos_interes, lista_respuestas_puntos_interes);
		
		// PUNTOS PARA APROBAR CADA TEST
		Double puntuacionAprobar_psicotecnico = new Double (test_psicotecnico.getPuntosAprobado());
		Double puntuacionAprobar_planos = new Double (test_planos.getPuntosAprobado());
		Double puntuacionAprobar_legislacion = new Double (test_legislacion.getPuntosAprobado());
		Double puntuacionAprobar_tarifas = new Double (test_tarifas.getPuntosAprobado());
		Double puntuacionAprobar_itinerarios = new Double (test_itinerarios.getPuntosAprobado());
		Double puntuacionAprobar_puntos_interes = new Double (test_puntos_interes.getPuntosAprobado());	
		
		//CALCULO EL RESULTADO DE CADA TEST
		resultado_psicotecnico = getResultadoTest(puntuacionAprobar_psicotecnico, puntuacion_psicotecnico);
		resultado_planos = getResultadoTest(puntuacionAprobar_planos, puntuacion_planos);
		resultado_legislacion = getResultadoTest(puntuacionAprobar_legislacion, puntuacion_legislacion);
		resultado_tarifas = getResultadoTest(puntuacionAprobar_tarifas, puntuacion_tarifas);
		resultado_itinerarios = getResultadoTest(puntuacionAprobar_itinerarios, puntuacion_itinerarios);
		resultado_puntos_interes = getResultadoTest(puntuacionAprobar_puntos_interes, puntuacion_puntos_interes);
		
		//RESULTADO DEL EXAMEN	
		if (resultado_psicotecnico.equals("SUSPENDIDO") || resultado_planos.equals("SUSPENDIDO") || resultado_legislacion.equals("SUSPENDIDO")
			|| resultado_tarifas.equals("SUSPENDIDO") || resultado_itinerarios.equals("SUSPENDIDO") || resultado_puntos_interes.equals("SUSPENDIDO"))
		{
			resultado_examen= "SUSPENDIDO";
		}
		else
		{
			resultado_examen= "APROBADO";
		}

	
		//COMENTARIO RESULTADO TEST INDIVIDUALMENTE
		comentario_resultado_psicotecnico = getComentarioResultado(puntuacionAprobar_psicotecnico, puntuacion_psicotecnico);
		comentario_resultado_planos= getComentarioResultado(puntuacionAprobar_planos, puntuacion_planos);
		comentario_resultado_legislacion= getComentarioResultado(puntuacionAprobar_legislacion, puntuacion_legislacion);
		comentario_resultado_tarifas= getComentarioResultado(puntuacionAprobar_tarifas, puntuacion_tarifas);
		comentario_resultado_itinerarios = getComentarioResultado(puntuacionAprobar_itinerarios, puntuacion_itinerarios); 
		comentario_resultado_puntos_interes= getComentarioResultado(puntuacionAprobar_puntos_interes, puntuacion_puntos_interes);
		
		//MUESTRO LA PAGINA DE RESULTADOS
		barra_visible = false;
		correccion_visible = true;
		examen_visible = false;
//		return "/xhtml/test/correccion.xhtml";
	}


	
	/**
	 * Devuelve un comentario teniendo en cuenta la puntuación para aprobar y la obtenida
	 * @param puntuacionAprobar
	 * @param puntuacion_aux
	 * @return
	 */
	private String getComentarioResultado(Double puntuacionAprobar, Double puntuacion_aux) {
		String comentario_resultado ="";
		
		if (puntuacionAprobar.intValue() == num_preguntas.intValue())
		{
			comentario_resultado = "Enhorabuena!!! Tienes dominado este módulo de preguntas.";
		}
		else if (puntuacionAprobar.intValue()<=puntuacion_aux.intValue())
		{
			comentario_resultado = "Enhorabuena, pero aún puedes mejorar el resultado de este módulo de preguntas. Puedes practicar más.";
		}
		else
		{
			comentario_resultado = "Animo, seguro que puedes mejorar el resultado. Debes practicar más realizando este módulo de preguntas.";
		}
		
		return comentario_resultado;
	}


	/**
	 * Devuelve el resultado objetivo teniendo en cuenta la puntuación para aprobar y la obtenida
	 * @param puntuacionNecesarioAprobar
	 * @param puntuacionObtenida
	 * @return
	 */
	private String getResultadoTest(Double puntuacionNecesarioAprobar, Double puntuacionObtenida) {
		
		String resultado="SUSPENDIDO";
		
		if (puntuacionNecesarioAprobar<=puntuacionObtenida)
		{
			resultado = "APROBADO";
		}
		
		return resultado;
	}
	
	
	/**
	 * Devuelve la puntuación obtenida en el test.
	 * @param lista_respuestas_correctas
	 * @param lista_respuestas_test_actual
	 * @return
	 */
	private Double getResultadoTest(List<String> lista_respuestas_correctas, List<Respuesta> lista_respuestas_test_actual)
	{
		Double puntuacion = 0.00;
		
		Integer puntuacion_aux = 0;
		Double respuestasIncorrectas = 0.00 ;
		
		for (int i = 0; i < lista_respuestas_correctas.size(); i++) 
		{
			
			// OBTENGO LA RESPUESTA ACTUAL EN ESTUDIO, Y LA RESPUESTA CORRECTA
			Respuesta respuesta = lista_respuestas_test_actual.get(i);
			
			String respuestaEnCurso = "x";
			if ((respuesta.getIdRespuestaCorrecta()!=null)&&(!respuesta.getIdRespuestaCorrecta().equals("")))
			{
				respuestaEnCurso = respuesta.getIdRespuestaCorrecta();				
			}

			String respuestaCorrecta = lista_respuestas_correctas.get(i);
			
			
			// CALCULO LA PUNTUACIÓN DE LA PREGUNTA			
			//SI NO SE CONTESTA NO VARIA PUNTUACION
			if (!respuestaEnCurso.equals("x"))
			{
				//SI ES UN ACIERTO SUMA
				if (respuestaCorrecta.equals(respuestaEnCurso)){
					puntuacion_aux++;				
				}
				//SI ES UN FALLO LO ANOTAMOS
				else
				{										
					respuestasIncorrectas++;
				}
			}
			//SI NO CONTESTA NINGUNA
			else
			{
				//NO SUMA NI RESTA
			}
		}
		
		//RESTO LAS INCORRECTAS A LA PUNTUACION GENERAL
		respuestasIncorrectas = respuestasIncorrectas * 0.1;
		puntuacion = puntuacion_aux -respuestasIncorrectas;
		
		return puntuacion;
	}
		
	
	/**
	 * Actualiza la variable global con el tiempo de finalizacion del examen
	 */
	private void calcularTiempoResolucion() {
		//OBTENEMOS LONG DE FIN
		date_fin = new Date();
				
		Long tiempoTranscurrido = date_fin.getTime() - date_comienzo.getTime();
		
		//		1000 milisegundos = 1 segundo.
		//		60 segundos = 1 minuto.
		//		60 minutos= 1 hora.
		//		24 horas = 1 día.
						
		Long segundos = tiempoTranscurrido / 1000 % 60;
		Long minutos  = tiempoTranscurrido / (60*1000) % 60;
		Long horas    = tiempoTranscurrido / (60*60*1000) % 24;
		
		StringBuffer tiempo;
		
		if (horas == 0)
		{
			tiempo = new StringBuffer();
		}
		else
		{
			tiempo = new StringBuffer(horas.toString());
			tiempo.append(" horas ");
		}
				
		if (minutos < 10)
		{
			tiempo.append(" 0").append(minutos.toString());
		}
		else
		{
			tiempo.append(minutos.toString());
		}
		
		tiempo.append(" minutos ");
		
		if (segundos < 10)
		{
			tiempo.append("0").append(segundos.toString());
		}
		else
		{
			tiempo.append(segundos.toString());	
		}
		
		tiempo.append(" segundos");
		
		setTiempo_realizacion(tiempo.toString());
	}
				
	
	/**
	 * Ordenacion de las respuestas.
	 */
	public int compare(Respuesta respuesta1, Respuesta respuesta2) {
		int comparacion = 0;
		comparacion = respuesta1.getId().compareTo(respuesta2.getId());
		return comparacion;
	}
	
	// ACCESORES PARA JSF
	public String getFecha_comienzo() {
		return fecha_comienzo;
	}

	public void setFecha_comienzo(String fecha_comienzo) {
		this.fecha_comienzo = fecha_comienzo;
	}

	public String getFecha_finalizacion() {
		return fecha_finalizacion;
	}

	public void setFecha_finalizacion(String fecha_finalizacion) {
		this.fecha_finalizacion = fecha_finalizacion;
	}

	public String getTiempo_realizacion() {
		return tiempo_realizacion;
	}

	public void setTiempo_realizacion(String tiempo_realizacion) {
		this.tiempo_realizacion = tiempo_realizacion;
	}

	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	public boolean isCorreccion_visible() {
		return correccion_visible;
	}


	public void setCorreccion_visible(boolean correccion_visible) {
		this.correccion_visible = correccion_visible;
	}


	public boolean isExamen_visible() {
		return examen_visible;
	}


	public void setExamen_visible(boolean examen_visible) {
		this.examen_visible = examen_visible;
	}


	public boolean isBarra_visible() {
		return barra_visible;
	}


	public void setBarra_visible(boolean barra_visible) {
		this.barra_visible = barra_visible;
	}


	public Respuesta getRespuesta_actual() {
		return respuesta_actual;
	}


	public void setRespuesta_actual(Respuesta respuesta_actual) {
		this.respuesta_actual = respuesta_actual;
	}


	public String getTexto_boton() {
		return texto_boton;
	}


	public void setTexto_boton(String texto_boton) {
		this.texto_boton = texto_boton;
	}


	public int getNum_pregunta_visor() {
		return num_pregunta_visor;
	}


	public void setNum_pregunta_visor(int num_pregunta_visor) {
		this.num_pregunta_visor = num_pregunta_visor;
	}


	public boolean isActivar_anterior() {
		return activar_anterior;
	}


	public void setActivar_anterior(boolean activar_anterior) {
		this.activar_anterior = activar_anterior;
	}


	public boolean isActivar_siguiente() {
		return activar_siguiente;
	}


	public void setActivar_siguiente(boolean activar_siguiente) {
		this.activar_siguiente = activar_siguiente;
	}


	public String getDuracion_siguiente() {
		return duracion_siguiente;
	}


	public void setDuracion_siguiente(String duracion_siguiente) {
		this.duracion_siguiente = duracion_siguiente;
	}


	public Tests getTest_actual() {
		return test_actual;
	}


	public void setTest_actual(Tests test_actual) {
		this.test_actual = test_actual;
	}


	public String getComentario_resultado_psicotecnico() {
		return comentario_resultado_psicotecnico;
	}


	public void setComentario_resultado_psicotecnico(
			String comentario_resultado_psicotecnico) {
		this.comentario_resultado_psicotecnico = comentario_resultado_psicotecnico;
	}


	public String getComentario_resultado_planos() {
		return comentario_resultado_planos;
	}


	public void setComentario_resultado_planos(String comentario_resultado_planos) {
		this.comentario_resultado_planos = comentario_resultado_planos;
	}


	public String getComentario_resultado_legislacion() {
		return comentario_resultado_legislacion;
	}


	public void setComentario_resultado_legislacion(
			String comentario_resultado_legislacion) {
		this.comentario_resultado_legislacion = comentario_resultado_legislacion;
	}


	public String getComentario_resultado_tarifas() {
		return comentario_resultado_tarifas;
	}


	public void setComentario_resultado_tarifas(String comentario_resultado_tarifas) {
		this.comentario_resultado_tarifas = comentario_resultado_tarifas;
	}


	public String getComentario_resultado_itinerarios() {
		return comentario_resultado_itinerarios;
	}


	public void setComentario_resultado_itinerarios(
			String comentario_resultado_itinerarios) {
		this.comentario_resultado_itinerarios = comentario_resultado_itinerarios;
	}


	public String getComentario_resultado_puntos_interes() {
		return comentario_resultado_puntos_interes;
	}


	public void setComentario_resultado_puntos_interes(
			String comentario_resultado_puntos_interes) {
		this.comentario_resultado_puntos_interes = comentario_resultado_puntos_interes;
	}


	public Double getPuntuacion_psicotecnico() {
		return puntuacion_psicotecnico;
	}


	public void setPuntuacion_psicotecnico(Double puntuacion_psicotecnico) {
		this.puntuacion_psicotecnico = puntuacion_psicotecnico;
	}


	public Double getPuntuacion_planos() {
		return puntuacion_planos;
	}


	public void setPuntuacion_planos(Double puntuacion_planos) {
		this.puntuacion_planos = puntuacion_planos;
	}


	public Double getPuntuacion_legislacion() {
		return puntuacion_legislacion;
	}


	public void setPuntuacion_legislacion(Double puntuacion_legislacion) {
		this.puntuacion_legislacion = puntuacion_legislacion;
	}


	public Double getPuntuacion_tarifas() {
		return puntuacion_tarifas;
	}


	public void setPuntuacion_tarifas(Double puntuacion_tarifas) {
		this.puntuacion_tarifas = puntuacion_tarifas;
	}


	public Double getPuntuacion_itinerarios() {
		return puntuacion_itinerarios;
	}


	public void setPuntuacion_itinerarios(Double puntuacion_itinerarios) {
		this.puntuacion_itinerarios = puntuacion_itinerarios;
	}


	public Double getPuntuacion_puntos_interes() {
		return puntuacion_puntos_interes;
	}


	public void setPuntuacion_puntos_interes(Double puntuacion_puntos_interes) {
		this.puntuacion_puntos_interes = puntuacion_puntos_interes;
	}


	public String getResultado_psicotecnico() {
		return resultado_psicotecnico;
	}


	public void setResultado_psicotecnico(String resultado_psicotecnico) {
		this.resultado_psicotecnico = resultado_psicotecnico;
	}


	public String getResultado_planos() {
		return resultado_planos;
	}


	public void setResultado_planos(String resultado_planos) {
		this.resultado_planos = resultado_planos;
	}


	public String getResultado_legislacion() {
		return resultado_legislacion;
	}


	public void setResultado_legislacion(String resultado_legislacion) {
		this.resultado_legislacion = resultado_legislacion;
	}


	public String getResultado_tarifas() {
		return resultado_tarifas;
	}


	public void setResultado_tarifas(String resultado_tarifas) {
		this.resultado_tarifas = resultado_tarifas;
	}


	public String getResultado_itinerarios() {
		return resultado_itinerarios;
	}


	public void setResultado_itinerarios(String resultado_itinerarios) {
		this.resultado_itinerarios = resultado_itinerarios;
	}


	public String getResultado_puntos_interes() {
		return resultado_puntos_interes;
	}


	public void setResultado_puntos_interes(String resultado_puntos_interes) {
		this.resultado_puntos_interes = resultado_puntos_interes;
	}


	public String getResultado_examen() {
		return resultado_examen;
	}


	public void setResultado_examen(String resultado_examen) {
		this.resultado_examen = resultado_examen;
	}


	public List<Respuesta> getLista_respuestas_actual() {
		return lista_respuestas_actual;
	}


	public void setLista_respuestas_actual(List<Respuesta> lista_respuestas_actual) {
		this.lista_respuestas_actual = lista_respuestas_actual;
	}


	public Date getDate_comienzo() {
		return date_comienzo;
	}


	public void setDate_comienzo(Date date_comienzo) {
		this.date_comienzo = date_comienzo;
	}


	public Date getDate_fin() {
		return date_fin;
	}


	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}


	public Tests getTest_psicotecnico() {
		return test_psicotecnico;
	}


	public void setTest_psicotecnico(Tests test_psicotecnico) {
		this.test_psicotecnico = test_psicotecnico;
	}


	public Tests getTest_planos() {
		return test_planos;
	}


	public void setTest_planos(Tests test_planos) {
		this.test_planos = test_planos;
	}


	public Tests getTest_legislacion() {
		return test_legislacion;
	}


	public void setTest_legislacion(Tests test_legislacion) {
		this.test_legislacion = test_legislacion;
	}


	public Tests getTest_tarifas() {
		return test_tarifas;
	}


	public void setTest_tarifas(Tests test_tarifas) {
		this.test_tarifas = test_tarifas;
	}


	public Tests getTest_itinerarios() {
		return test_itinerarios;
	}


	public void setTest_itinerarios(Tests test_itinerarios) {
		this.test_itinerarios = test_itinerarios;
	}


	public Tests getTest_puntos_interes() {
		return test_puntos_interes;
	}


	public void setTest_puntos_interes(Tests test_puntos_interes) {
		this.test_puntos_interes = test_puntos_interes;
	}			
}
