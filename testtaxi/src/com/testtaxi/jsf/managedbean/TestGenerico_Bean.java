package com.testtaxi.jsf.managedbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.testtaxi.dto.Respuesta;
import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.modelo.I_Tests;
import com.testtaxi.spring.Acceso_ApplicationContext;

public class TestGenerico_Bean implements Serializable, Comparator<Respuesta>{
	private static final long serialVersionUID = 1L;
	//OBJETO TEST
	private Tests test = null;
	//TIPO TEST
	private String descripcion = null;
	//FECHA COMIENZO TEST
	private String fecha_comienzo = null;
	private Date date_comienzo = null;
	//FECHA FINALIZACION TEST
	private String fecha_finalizacion = null;	
	private Date date_fin = null;
	//TIEMPO TOTAL REALIZACION TEST
	private String tiempo_realizacion = null; 
	//DURACION DE TEST
	private String duracion = null;
	//NUMERO DE PREGUNTAS
	private String numero_preguntas = "0 (0%)";
	private Integer num_preguntas = 0;
	//PORCENTAJE
	private String porcentaje = null;
	//COMENTARIO RESULTADO TEST
	private String comentario_resultado;
	//RUTA DE LAS IMAGENES EN EL SERVIDOR
	private static final String FILE_PATH = "/resources/images/test/";
	private static final String FILE_PATH_RESULTADO = "/resources/images/";
	//LISTA DE PREGUNTAS QUE COMPONEN EL TEST
	private List<Preguntas> lista_preguntas = null;	
	//LISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_correctas = null;	
	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_1_5 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_6_10 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_11_15 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_16_20 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_21_25 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_26_30 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_31_35 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_36_40 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_41_45 = null;	
	//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
	private List<String> lista_respuestas_46_50 = null;	
	
	//PUNTUACION DEL TEST
	private Double puntuacion = 0.00;
	//PUNTUACION NECESARIA PARA APROBAR
	private Double puntuacionAprobar;
	//RESULTADO DEL TEST
	private String resultado = "SUSPENDIDO";
	//LISTA RESPUESTAS
	private List<Respuesta> lista_respuestas = null;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(TestGenerico_Bean.class);

	private boolean correccion_visible;
	
	
	private boolean test_visible;
	
	private boolean barra_visible;
	
	
	private Integer id_test;
	
	private String id;
	

	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public TestGenerico_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS EL TEST GENERICO ******------");
				
		
		
		correccion_visible = false;
		test_visible = false;
		barra_visible = true;
		
//		cargarDatos();
		
//		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
//		mensaje.setMetodo_aceptar_js("countDown(0, 5, 0);countUp(0, 0, 0)");
//		mensaje.cargar_Mensaje("Comenzar test");
//		mensaje.cargar_Icono("/resources/images/info/info-7.png");
//		mensaje.setCabecera("Comenzar Test");
//		mensaje.setTexto_boton_aceptar("Comenzar Test");
//		mensaje.setVisible(true);
		

//		mensaje.cargar_Mensaje("Comenzar test", "Comenzar test", "/resources/images/info/info-7.png", "Comenzar Test", "countDown(0, 5, 0);countUp(0, 0, 0)");
		
		
		
//		cargarDatos();
	}

	
	
//	public void listener(ActionEvent event){
//		String id = (String)event.getComponent().getAttributes().get("id");
//		Id_test = new Integer(id);
//	}
	
	
	
	public void cargarDatos() {	
		
		//OBTENEMOS LONG DE INICIO
		date_comienzo = new Date();
		
		fecha_comienzo = get_fecha_actual();
		    
		//OBTENEMOS EL TEST
//		ApplicationContext contexto = new ClassPathXmlApplicationContext("/com/testtaxi/spring/modelo.xml");
//		I_Tests f_tests = contexto.getBean(I_Tests.class);
		//acceder con la clase estatica para evitar crear objetos nuevos
		I_Tests f_tests = Acceso_ApplicationContext.getBean(I_Tests.class);
	
		
//		Map<String tring="tring"> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        String idEmpleado = params.get("id");
//        System.out.println("Eliminando empleado"+idEmpleado);
		id_test = new Integer(id);
		test = f_tests.consultar_TestConPreguntas(id_test);
		
		//DESCRIPCION DEL TEST
		descripcion=test.getDescripcion();
		
		//DURACION DEL TEST
		duracion=test.getDuracion().toString();// + " minutos";
				
		//MONTO PUNTUACION PARA APROBAR
		puntuacionAprobar = new Double (test.getPuntosAprobado());
		
		//OBTENEMOS LA LISTA DE PREGUNTAS DEL TEST
		Set<Preguntas> set_preguntas = test.getPreguntases();
		
		//NUMERO DE PREGUNTAS
		num_preguntas = set_preguntas.size();
		numero_preguntas = num_preguntas.toString();
		
		//OBTENEMOS EL TIPO DE TEST
//		switch (test.getTipo().intValue()) {
//		case PSICOTECNICO:
//			tipoTest = STR_PSICOTECNICO;
//		break;
//		case PLANOS:
//			tipoTest = STR_PLANOS;
//		break;
//		case LEGISLACION:
//			tipoTest = STR_LEGISLACION;
//		break;
//		case TARIFAS:
//			tipoTest = STR_TARIFAS;
//		break;
//		case ITINERARIOS:
//			tipoTest = STR_ITINERARIOS;
//		break;
//		case PUNTOS_DE_INTERES:
//			tipoTest = STR_PUNTOS_DE_INTERES;
//		break;		
//		default:
//		break;
//		}
		
				
		//SI TENEMOS PREGUNTAS
		if(set_preguntas!=null)
		{
			//MONTO LA LISTA DE PREGUNTAS
			lista_respuestas = new ArrayList<Respuesta>();
			
			Iterator <Preguntas> iterator = set_preguntas.iterator();
			while(iterator.hasNext()) {
				//OBTENEMOS LA SIGUIENTE PREGUNTA
				Preguntas pregunta = (Preguntas) iterator.next();
								
				Respuesta respuesta = new Respuesta(pregunta);
				//GENERAMOS LOS PARAMETROS DE LA RESPUESTA
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
				
				//ANIADO LA RESPUESTA
				lista_respuestas.add(respuesta);
			}
			
			//ORDENAMOS LA LISTA DE RESPUESTAS
			Collections.sort(lista_respuestas, this);
			
			//MONTO LAS LISTAS DE RESPUESTAS CORRECTAS Y RESETEO LA RESPUESTA DEL USUARIO
			lista_respuestas_correctas = new ArrayList<String>();

			for (int i = 0; i < lista_respuestas.size(); i++) {
				//MONTO LA LISTA DE RESPUESTAS CORRECTAS	
				Respuesta respuesta_aux = lista_respuestas.get(i);		
				lista_respuestas_correctas.add(respuesta_aux.getIdRespuestaCorrecta());
				
				//RESETEO LA RESPUESTA DEL USUARIO
				respuesta_aux.setIdRespuestaCorrecta("x");
			}
			
			
			//SUBLISTA DE RESPUESTAS CORRECTAS DEL TEST
			lista_respuestas_1_5 = null;	
			lista_respuestas_6_10 = null;	
			lista_respuestas_11_15 = null;				
			lista_respuestas_16_20 = null;				
			lista_respuestas_21_25 = null;				
			lista_respuestas_26_30 = null;				
			lista_respuestas_31_35 = null;				
			lista_respuestas_36_40 = null;				
			lista_respuestas_41_45 = null;				
			lista_respuestas_46_50 = null;	
			
			if (lista_respuestas_correctas!=null)
			{
				int num_respuestas = lista_respuestas_correctas.size();
				int modulo_num_respuestas= num_respuestas % 5;
								
				if (num_respuestas >= 0)
				{
					lista_respuestas_1_5 = lista_respuestas_correctas.subList(0, (num_respuestas >= 5) ? 5 : modulo_num_respuestas);	
				}
				if (num_respuestas >= 5)
				{
					lista_respuestas_6_10 = lista_respuestas_correctas.subList(5, (num_respuestas >= 10) ? 10 : 5+modulo_num_respuestas);
				}
				if (num_respuestas >= 10)
				{
					lista_respuestas_11_15 = lista_respuestas_correctas.subList(10, (num_respuestas >= 15) ? 15 : 10+modulo_num_respuestas);
				}
				if (num_respuestas >= 15)
				{
					lista_respuestas_16_20 = lista_respuestas_correctas.subList(15, (num_respuestas >= 20) ? 20 : 15+modulo_num_respuestas);
				}
				if (num_respuestas >= 20)
				{					
					lista_respuestas_21_25 = lista_respuestas_correctas.subList(20, (num_respuestas >= 25) ? 25 : 20+modulo_num_respuestas);
				}
				if (num_respuestas >= 25)
				{					
					lista_respuestas_26_30 = lista_respuestas_correctas.subList(25, (num_respuestas >= 30) ? 30 : 25+modulo_num_respuestas);
				}
				if (num_respuestas >= 30)
				{
					lista_respuestas_31_35 = lista_respuestas_correctas.subList(30, (num_respuestas >= 35) ? 35 : 30+modulo_num_respuestas);
				}
				if (num_respuestas >= 35)
				{
					lista_respuestas_36_40 = lista_respuestas_correctas.subList(35, (num_respuestas >= 40) ? 40 : 35+modulo_num_respuestas);
				}
				if (num_respuestas >= 40)
				{					
					lista_respuestas_41_45 = lista_respuestas_correctas.subList(40, (num_respuestas >= 45) ? 45 : 40+modulo_num_respuestas);
				}
				if (num_respuestas >= 45)
				{
					lista_respuestas_46_50 = lista_respuestas_correctas.subList(45, (num_respuestas >= 50) ? 50 : 45+modulo_num_respuestas);
				}
			}
							
		}
		
		barra_visible = false;
		correccion_visible = false;
		test_visible = true;
	}


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
	public void calcular_resultado_test() {

		/**
		 * La Puntuacion Neta se calcula de acuerdo con el siguiente baremo
		 * 		Respuestas Correctas - Suman 1 Punto
		 * 		Respuestas Incorrectas - Restan 0,1 Punto
		 * 		Preguntas sin responder - No suman ni restan Puntos
		 */
		
		
		calcularTiempoResolucion();		
		
		//OBTENEMOS FECHA DE FIN
		fecha_finalizacion = get_fecha_actual();
		
		Integer puntuacion_aux = 0;
		Double respuestasIncorrectas = 0.00 ;
		
		for (int i = 0; i < lista_respuestas_correctas.size(); i++) {
			
			Respuesta respuesta = lista_respuestas.get(i);
			
			String respuestaEnCurso = "x";
			if ((respuesta.getIdRespuestaCorrecta()!=null)&&(!respuesta.getIdRespuestaCorrecta().equals("")))
			{
				respuestaEnCurso = respuesta.getIdRespuestaCorrecta();				
			}
			else{
				respuesta.setIdRespuestaCorrecta("x");
			}
				
			String respuestaCorrecta = lista_respuestas_correctas.get(i);
			
			
			//MARCO LA RESPUESTA CORRECTA
			if (respuestaCorrecta.equals("a"))
			{
				respuesta.setEstilo_respuesta_1("respuesta_correcta");				
			}
			else if (respuestaCorrecta.equals("b"))
			{
				respuesta.setEstilo_respuesta_2("respuesta_correcta");
			}
			else if (respuestaCorrecta.equals("c"))	
			{
				respuesta.setEstilo_respuesta_3("respuesta_correcta");
			}
			else if (respuestaCorrecta.equals("d"))	
			{
				respuesta.setEstilo_respuesta_4("respuesta_correcta");
			}				
				
				/*
				
			switch (respuestaCorrecta) {
			case 1:
				respuesta.setEstilo_respuesta_1("respuesta_correcta");
			break;
			case 2:
				respuesta.setEstilo_respuesta_2("respuesta_correcta");
			break;
			case 3:
				respuesta.setEstilo_respuesta_3("respuesta_correcta");
			break;
			case 4:
				respuesta.setEstilo_respuesta_4("respuesta_correcta");
			break;	
			}
			*/
			
			String indicacionesPregunta = null;
			//SI NO SE CONTESTA NO VARIA PUNTUACION
			if (!respuestaEnCurso.equals("x"))
			{
				//SI ES UN ACIERTO SUMA
				if (respuestaCorrecta.equals(respuestaEnCurso)){
					puntuacion_aux++;				
					
					//NOTIFICO AL USUARIO SU EXITO
					respuesta.setImagen_respuesta(FILE_PATH_RESULTADO.concat("acierto.png"));
					indicacionesPregunta = "ACIERTO";
					respuesta.setIndicacionesPregunta(indicacionesPregunta);
					respuesta.setResultado_pregunta("Obtienes: " + respuesta.getPuntuacion() + " puntos");
				}
				//SI ES UN FALLO LO ANOTAMOS
				else
				{
					//NOTIFICO AL USUARIO SU FRACASO
					respuesta.setImagen_respuesta(FILE_PATH_RESULTADO.concat("fallo.png"));
					indicacionesPregunta = "FALLO";
					respuesta.setIndicacionesPregunta(indicacionesPregunta);
					respuesta.setResultado_pregunta("Obtienes: -0,1 puntos");
					
					
					
					if (respuestaEnCurso.equals("a"))
					{
						respuesta.setEstilo_respuesta_1("respuesta_incorrecta");				
					}
					else if (respuestaEnCurso.equals("b"))
					{
						respuesta.setEstilo_respuesta_2("respuesta_incorrecta");
					}
					else if (respuestaEnCurso.equals("c"))	
					{
						respuesta.setEstilo_respuesta_3("respuesta_incorrecta");
					}
					else if (respuestaEnCurso.equals("d"))	
					{
						respuesta.setEstilo_respuesta_4("respuesta_incorrecta");
					}	
					
					/*
					switch (respuestaEnCurso) {
					case 1:
						respuesta.setEstilo_respuesta_1("respuesta_incorrecta");
					break;
					case 2:
						respuesta.setEstilo_respuesta_2("respuesta_incorrecta");
					break;
					case 3:
						respuesta.setEstilo_respuesta_3("respuesta_incorrecta");
					break;
					case 4:
						respuesta.setEstilo_respuesta_4("respuesta_incorrecta");
					break;						
					}
					*/
					
					respuestasIncorrectas++;
				}
			}
			//SI NO CONTESTA NINGUNA
			else
			{
				//NOTIFICO AL USUARIO SU FRACASO
				respuesta.setImagen_respuesta(FILE_PATH_RESULTADO.concat("fallo.png"));
				indicacionesPregunta = "NO CONTESTADA.";
				respuesta.setIndicacionesPregunta(indicacionesPregunta);
				respuesta.setResultado_pregunta("Obtienes: 0 puntos");
				respuesta.setComentario("");
			}
		}
		
		
		//CALCULO EL PORCENTAJE
		Double d_puntuacion = puntuacion_aux.doubleValue();
		Double d_numPreguntas = num_preguntas.doubleValue();
		Double percent =  d_puntuacion/ d_numPreguntas;
		percent = percent * 100;
		
		porcentaje = percent.toString().concat("%");
		
		//RESTO LAS INCORRECTAS A LA PUNTUACION GENERAL
		respuestasIncorrectas = respuestasIncorrectas * 0.1;
		puntuacion = puntuacion_aux -respuestasIncorrectas;
				
		

		//ACTUALIZO EL RESULTADO DEL TEST
		if (puntuacionAprobar<=puntuacion)
		{
			resultado = "APROBADO";
		}
		
		if (puntuacionAprobar.intValue() == num_preguntas.intValue())
		{
			comentario_resultado = "Enhorabuena has superado totalmente el test.";
		}
		else if (puntuacionAprobar.intValue()<=puntuacion_aux.intValue())
		{
			comentario_resultado = "Enhorabuena, pero aun puedes mejorar el resultado.";
		}
		else
		{
			comentario_resultado = "Animo, seguro que puedes mejorar el resultado.";
		}

		//MUESTRO LA PAGINA DE RESULTADOS
		barra_visible = false;
		correccion_visible = true;
		test_visible = false;
//		return "/xhtml/test/correccion.xhtml";
	}


	private void calcularTiempoResolucion() {
		//OBTENEMOS LONG DE FIN
		date_fin = new Date();
				
		Long tiempoTranscurrido = date_fin.getTime() - date_comienzo.getTime();
		
		//		1000 milisegundos = 1 segundo.
		//		60 segundos = 1 minuto.
		//		60 minutos= 1 hora.
		//		24 horas = 1 dia.
						
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
	public Tests getTest() {
		return test;
	}
	
	public void setTest(Tests test) {
		this.test = test;
	}

	public List<Preguntas> getLista_preguntas() {
		return lista_preguntas;
	}

	public void setLista_preguntas(List<Preguntas> lista_preguntas) {
		this.lista_preguntas = lista_preguntas;
	}


	public List<String> getLista_respuestas_correctas() {
		return lista_respuestas_correctas;
	}

	public void setLista_respuestas_correctas(List<String> lista_respuestas_correctas) {
		this.lista_respuestas_correctas = lista_respuestas_correctas;
	}
	
	public List<String> getLista_respuestas_1_5() {
		return lista_respuestas_1_5;
	}

	public void setLista_respuestas_1_5(List<String> lista_respuestas_1_5) {
		this.lista_respuestas_1_5 = lista_respuestas_1_5;
	}

	public List<String> getLista_respuestas_6_10() {
		return lista_respuestas_6_10;
	}

	public void setLista_respuestas_6_10(List<String> lista_respuestas_6_10) {
		this.lista_respuestas_6_10 = lista_respuestas_6_10;
	}

	public List<String> getLista_respuestas_11_15() {
		return lista_respuestas_11_15;
	}

	public void setLista_respuestas_11_15(List<String> lista_respuestas_11_15) {
		this.lista_respuestas_11_15 = lista_respuestas_11_15;
	}

	public List<String> getLista_respuestas_16_20() {
		return lista_respuestas_16_20;
	}

	public void setLista_respuestas_16_20(List<String> lista_respuestas_16_20) {
		this.lista_respuestas_16_20 = lista_respuestas_16_20;
	}

	public List<String> getLista_respuestas_21_25() {
		return lista_respuestas_21_25;
	}

	public void setLista_respuestas_21_25(List<String> lista_respuestas_21_25) {
		this.lista_respuestas_21_25 = lista_respuestas_21_25;
	}

	public List<String> getLista_respuestas_26_30() {
		return lista_respuestas_26_30;
	}

	public void setLista_respuestas_26_30(List<String> lista_respuestas_26_30) {
		this.lista_respuestas_26_30 = lista_respuestas_26_30;
	}

	public List<String> getLista_respuestas_31_35() {
		return lista_respuestas_31_35;
	}

	public void setLista_respuestas_31_35(List<String> lista_respuestas_31_35) {
		this.lista_respuestas_31_35 = lista_respuestas_31_35;
	}

	public List<String> getLista_respuestas_36_40() {
		return lista_respuestas_36_40;
	}

	public void setLista_respuestas_36_40(List<String> lista_respuestas_36_40) {
		this.lista_respuestas_36_40 = lista_respuestas_36_40;
	}

	public List<String> getLista_respuestas_41_45() {
		return lista_respuestas_41_45;
	}

	public void setLista_respuestas_41_45(List<String> lista_respuestas_41_45) {
		this.lista_respuestas_41_45 = lista_respuestas_41_45;
	}

	public List<String> getLista_respuestas_46_50() {
		return lista_respuestas_46_50;
	}

	public void setLista_respuestas_46_50(List<String> lista_respuestas_46_50) {
		this.lista_respuestas_46_50 = lista_respuestas_46_50;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Double getPuntuacionAprobar() {
		return puntuacionAprobar;
	}

	public void setPuntuacionAprobar(Double puntuacionAprobar) {
		this.puntuacionAprobar = puntuacionAprobar;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<Respuesta> getLista_respuestas() {
		return lista_respuestas;
	}

	public void setLista_respuestas(List<Respuesta> lista_respuestas) {
		this.lista_respuestas = lista_respuestas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


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

	public String getNumero_preguntas() {
		return numero_preguntas;
	}

	public void setNumero_preguntas(String numero_preguntas) {
		this.numero_preguntas = numero_preguntas;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getComentario_resultado() {
		return comentario_resultado;
	}

	public void setComentario_resultado(String comentario_resultado) {
		this.comentario_resultado = comentario_resultado;
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


	public boolean isTest_visible() {
		return test_visible;
	}


	public void setTest_visible(boolean test_visible) {
		this.test_visible = test_visible;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public boolean isBarra_visible() {
		return barra_visible;
	}



	public void setBarra_visible(boolean barra_visible) {
		this.barra_visible = barra_visible;
	}			
	
	
	
	
	
}
