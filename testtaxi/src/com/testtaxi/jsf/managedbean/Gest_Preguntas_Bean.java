package com.testtaxi.jsf.managedbean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.event.SelectEvent;
import org.icefaces.ace.event.UnselectEvent;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.modelo.I_Preguntas;
import com.testtaxi.spring.Acceso_ApplicationContext;
import com.testtaxi.util.Acceso_Contextos;

public class Gest_Preguntas_Bean implements Serializable {

	private static final long serialVersionUID = -5475860140243042807L;
	// CONTROL DE ACTIVACION DE BOTONES
	private boolean activar_BMIL = true;
	private boolean activar_alta = true;
	
	// COLECCION PARA LA CARGA DEL COMPONENTE EN LA PAGINA
	private List<SelectItem> lista_tipo_pregunta;
	
	//COLECCION PARA LA CARGA DE LA TABLA DE PREGUNTAS
	private List<Preguntas> lista_preguntas;
	
	private Preguntas pregunta;
	private Preguntas pregunta_desconectado;
	
	// PROPIEDAD PARA GESTION DE PREGUNTAS
	public I_Preguntas f_preguntas;
	
	private String rutaFoto = "";
	
	
	// PROPIEDADES PARA GESTINAR LA RESPUESTAS CORRECTA SELECCIONADA
	private boolean check_1 = true;
	private boolean check_2 = false;
	private boolean check_3 = false;
	private boolean check_4 = false;
	
	private Mensajes_Bean mensaje;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Gest_Preguntas_Bean.class);

	public Gest_Preguntas_Bean() {	
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS LA GESTION DE PREGUNTA ******------");
		
		// VALORES POR DEFECTO EN EL FORMULARIO
		this.accion_Reiniciar();
		
		cargar_lista_preguntas();
		
		mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
	}	
	
	
	public void cargar_lista_preguntas() {
		//CARGAMOS LA LISTA DE PREGUNTAs
		f_preguntas = Acceso_ApplicationContext.getBean(I_Preguntas.class);
		List<Preguntas> lista = f_preguntas.consultar_todas();
				
		// CREAMOS LA COLECCION PARA LAS PREGUNTAS SELECCIONABLES DE LA TABLA
		setLista_preguntas(new ArrayList<Preguntas>(0));
		// CARGAMOS LA TABLA CON LAS PREGUNTAS ENCONTRADAS
		lista_preguntas.addAll(lista);
	}	


	public void accion_Reiniciar() {
		// PROPIEDADES DEL FORMULARIO
		//CARGAMOS EL DESPLEGABLE CON LOS TIPOS DE PREGUNTAS
		cargarListaTipoPreguntas();	
		//CARGAMOS UN TEST NUEVO DESCONECTADO
		pregunta = new Preguntas();
		
		Short dificultad = 3;
		pregunta.setDificultad(dificultad);
				
		// ACTIVACION DE BOTONES
		activar_BMIL = true;
		activar_alta = false;
		
		// RESETEO LOS CHECKBOSES
		reset_checkBox();
	}


	private void reset_checkBox() {
		setCheck_1(true);
		setCheck_2(false);
		setCheck_3(false);
		setCheck_4(false);
	}
	

	private void cargarListaTipoPreguntas() {
		lista_tipo_pregunta = new ArrayList<SelectItem>();
		
		SelectItem tipo_pregunta;
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("PSICOTECNICO");
		tipo_pregunta.setLabel("PSICOTECNICO");
		tipo_pregunta.setValue("PSICOTECNICO");
		lista_tipo_pregunta.add(tipo_pregunta);
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("PLANOS");
		tipo_pregunta.setLabel("PLANOS");
		tipo_pregunta.setValue("PLANOS");
		lista_tipo_pregunta.add(tipo_pregunta);
				
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("LEGISLACION");
		tipo_pregunta.setLabel("LEGISLACION");
		tipo_pregunta.setValue("LEGISLACION");
		lista_tipo_pregunta.add(tipo_pregunta);
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("TARIFAS");
		tipo_pregunta.setLabel("TARIFAS");
		tipo_pregunta.setValue("TARIFAS");
		lista_tipo_pregunta.add(tipo_pregunta);
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("ITINERARIOS");
		tipo_pregunta.setLabel("ITINERARIOS");
		tipo_pregunta.setValue("ITINERARIOS");
		lista_tipo_pregunta.add(tipo_pregunta);
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("PUNTOS DE INTERES");
		tipo_pregunta.setLabel("PUNTOS DE INTERES");
		tipo_pregunta.setValue("PUNTOS DE INTERES");
		lista_tipo_pregunta.add(tipo_pregunta);
		
		tipo_pregunta = new SelectItem();
		tipo_pregunta.setDescription("EXAMEN");
		tipo_pregunta.setLabel("EXAMEN");
		tipo_pregunta.setValue("EXAMEN");
		lista_tipo_pregunta.add(tipo_pregunta);
	}
	
	
	public void tratar_Seleccion(SelectEvent evento) {
			pregunta.setId(((Preguntas) evento.getObject()).getId());
			consulta_pregunta();			
	}
	
	
	public void tratar_Deseleccion(UnselectEvent evento) {
		accion_Reiniciar();
	}
	
	
	public void respuesta_seleccionada(AjaxBehaviorEvent evento)  {
		String seleccionado = evento.getComponent().getId();
		
		repintarChecks(seleccionado);			
	}


	private void repintarChecks(String seleccionado) {
		if ((seleccionado.equals("checkBox1"))||(seleccionado.equals("a")))
		{
			pregunta.setIdRespuestaCorrecta("a");
			setCheck_1(true);
			setCheck_2(false);
			setCheck_3(false);
			setCheck_4(false);
		}
		else if ((seleccionado.equals("checkBox2"))||(seleccionado.equals("b")))
		{
			pregunta.setIdRespuestaCorrecta("b");
			setCheck_1(false);
			setCheck_2(true);
			setCheck_3(false);
			setCheck_4(false);
		}
		else if ((seleccionado.equals("checkBox3"))||(seleccionado.equals("c")))
		{
			pregunta.setIdRespuestaCorrecta("c");
			setCheck_1(false);
			setCheck_2(false);
			setCheck_3(true);
			setCheck_4(false);
		}	
		else if ((seleccionado.equals("checkBox4"))||(seleccionado.equals("d")))
		{
			pregunta.setIdRespuestaCorrecta("d");
			setCheck_1(false);
			setCheck_2(false);
			setCheck_3(false);
			setCheck_4(true);
		}
	}
	
	

	
	public void subir_fichero(FileEntryEvent e)
    {
        FileEntry fe = (FileEntry)e.getComponent();
        FileEntryResults results = fe.getResults();
        File parent = null;

        //RECORREMOS LOS FICHEROS ELEGIDOS
        for (FileEntryResults.FileInfo i : results.getFiles()) 
        {
            if (i.isSaved()) {
                File file = i.getFile();
                if (file != null) {
                    parent = file.getParentFile();
                }
                
                //TODO RESOLVER RUTA DEL FICHERO PARA ALMACENARLO EN BBDD
                //SESION_ID + /NOMBRE FICHERO
                int pos_ruta = file.getPath().indexOf(file.separator + "test" + file.separator) + 6;
				int tam_total = file.getPath().length();
				rutaFoto = file.getPath().substring(pos_ruta, tam_total);
            } else {
                // TRATAMIENTO ERROR			
    			mensaje.cargar_Mensaje("Alta ERROR", "EL FICHERO NO SE HA SALVADO PORQUE: " + i.getStatus().getFacesMessage(FacesContext.getCurrentInstance(), fe, i).getSummary(), "/resources/images/operacion_incorrecta.png","Aceptar", "");
            }
        }

        if (parent != null) {
            long dirSize = 0;
            int fileCount = 0;
            for (File file : parent.listFiles()) {
                fileCount++;
                dirSize += file.length();
            }
        }
    }
	
	
	// ******** TRATAMIENTO DE ACCIONES DE LOS BOTONES ************
	// RESOLUCION DEL EVENTO DE LOS BOTONES DEL FORMULARIO
	public void tratar_Accion(ActionEvent evento) {
		String boton_elegido = evento.getComponent().getId();

		if (boton_elegido.equalsIgnoreCase("bot_alta")) {
			this.alta_pregunta();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_baja")) {
			this.baja_pregunta();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_consultas")) {
			this.consulta_pregunta();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_modificaciones")) {
			this.modificacion_pregunta();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_reiniciar")) {
			this.accion_Reiniciar();
		}else
		if (boton_elegido.equalsIgnoreCase("bot_salir")) {
			this.accion_Salir();
		} 
	}
	
	
	// ***** PROCESOS CRUD PREGUNTAS
	public void alta_pregunta() {
		Preguntas pregunta_nuevo = new Preguntas();
		// CARGAMOS LAS PROPIEDADES DEL TEST
			    
	    pregunta_nuevo.setComentario(pregunta.getComentario()); 
		pregunta_nuevo.setDificultad(pregunta.getDificultad());
//		pregunta_nuevo.setFoto(pregunta.getFoto());
		
		pregunta_nuevo.setFoto(rutaFoto);
		
		
//		pregunta_nuevo.setId(pregunta.getId());
		pregunta_nuevo.setIdRespuestaCorrecta(pregunta.getIdRespuestaCorrecta());
		pregunta_nuevo.setIndicacionesPregunta(pregunta.getIndicacionesPregunta());
		
		
		
//		pregunta_nuevo.setNumRespuestas(pregunta.getNumRespuestas());
		Short numpreguntas = 4;
		pregunta_nuevo.setNumRespuestas(numpreguntas);
		
		
		pregunta_nuevo.setPuntuacion(pregunta.getPuntuacion());
		pregunta_nuevo.setRespuesta1(pregunta.getRespuesta1());
		pregunta_nuevo.setRespuesta2(pregunta.getRespuesta2());
		pregunta_nuevo.setRespuesta3(pregunta.getRespuesta3());
		pregunta_nuevo.setRespuesta4(pregunta.getRespuesta4());
		pregunta_nuevo.setRespuesta5(pregunta.getRespuesta5());
		pregunta_nuevo.setRespuesta6(pregunta.getRespuesta6());
//		pregunta_nuevo.setTestses(pregunta.getTestses());
		pregunta_nuevo.setTextoPregunta(pregunta.getTextoPregunta());
		pregunta_nuevo.setTipoPregunta(pregunta.getTipoPregunta());
					
		try {
			f_preguntas.alta_Pregunta(pregunta_nuevo);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_preguntas();
			mensaje.cargar_Mensaje("Alta Correcta", "La pregunta se dio de alta de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR			
			mensaje.cargar_Mensaje("Alta ERROR", "Error en el alta de la pregunta: ", "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void baja_pregunta() {		
		try {

			f_preguntas.baja_Pregunta(pregunta); 
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_preguntas();
			mensaje.cargar_Mensaje("Baja correcta", "La pregunta se dio de baja de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Baja ERROR", "Error en la baja de la pregunta", "/resources/images/operacion_incorrecta.png","Aceptar", "");
		}
	}

	public void consulta_pregunta() {
		try {
			
			//OBTENGO EL TEST		
			pregunta = f_preguntas.consultar_PorClave(pregunta.getId());
			
			activar_BMIL = false;
			activar_alta = true;
			
			String respuestaSeleccionada = pregunta.getIdRespuestaCorrecta();
			
			repintarChecks(respuestaSeleccionada);
			

		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Consulta ERROR", "No hay coincidencias en la base de datos. Quizas otro usuario ha dado de baja el registro mientras estabas consultando.", "/resources/images/operacion_incorrecta.png","Aceptar", "");
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//RECARGO LA LISTA DE TEST
			cargar_lista_preguntas();
		}
	}

	public void modificacion_pregunta() {
		Preguntas pregunta_modificacion = new Preguntas();

		pregunta_modificacion.setId(pregunta.getId());
	    pregunta_modificacion.setComentario(pregunta.getComentario()); 
		pregunta_modificacion.setDificultad(pregunta.getDificultad());
		pregunta_modificacion.setFoto(pregunta.getFoto());
//		pregunta_modificacion.setId(pregunta.getId());
		pregunta_modificacion.setIdRespuestaCorrecta(pregunta.getIdRespuestaCorrecta());
		pregunta_modificacion.setIndicacionesPregunta(pregunta.getIndicacionesPregunta());
		pregunta_modificacion.setNumRespuestas(pregunta.getNumRespuestas());
		pregunta_modificacion.setPuntuacion(pregunta.getPuntuacion());
		pregunta_modificacion.setRespuesta1(pregunta.getRespuesta1());
		pregunta_modificacion.setRespuesta2(pregunta.getRespuesta2());
		pregunta_modificacion.setRespuesta3(pregunta.getRespuesta3());
		pregunta_modificacion.setRespuesta4(pregunta.getRespuesta4());
		pregunta_modificacion.setRespuesta5(pregunta.getRespuesta5());
		pregunta_modificacion.setRespuesta6(pregunta.getRespuesta6());
//		pregunta_modificacion.setTestses(pregunta.getTestses());
		pregunta_modificacion.setTextoPregunta(pregunta.getTextoPregunta());
		pregunta_modificacion.setTipoPregunta(pregunta.getTipoPregunta());
				
		try {
			f_preguntas.modificacion_Pregunta(pregunta_modificacion);
			//LIMPIAMOS EL FORMULARIO
			accion_Reiniciar();
			//ACTUALIZAMOS LA TABLA
			cargar_lista_preguntas();
			mensaje.cargar_Mensaje("Modificacion INFO", "La pregunta se modifico de forma correcta", "/resources/images/operacion_correcta.png","Aceptar", "");
		} catch (Exception e) {
			// TRATAMIENTO ERROR
			mensaje.cargar_Mensaje("Modificacion ERROR", "Error en modificacion de la pregunta", "/resources/images/operacion_incorrecta.png","Aceptar", "");
			
			activar_BMIL = false;
		}
	}
	
	
	public void accion_Salir() {
		Menu_Bean menu = (Menu_Bean) Acceso_Contextos.getAtributo("menu_bean");
		menu.setPagina_elegida("/xhtml/fondo.xhtml");
		
//		menu.accion_salir();
	}
	
	
	
	//ACCESORES PARA JSF
	public List<SelectItem> getLista_tipo_pregunta() {
		return lista_tipo_pregunta;
	}


	public void setLista_tipo_pregunta(List<SelectItem> lista_tipo_pregunta) {
		this.lista_tipo_pregunta = lista_tipo_pregunta;
	}


	public List<Preguntas> getLista_preguntas() {
		return lista_preguntas;
	}


	public void setLista_preguntas(List<Preguntas> lista_preguntas) {
		this.lista_preguntas = lista_preguntas;
	}


	public Preguntas getPregunta() {
		return pregunta;
	}


	public void setPregunta(Preguntas pregunta) {
		this.pregunta = pregunta;
	}


	public Preguntas getPregunta_desconectado() {
		return pregunta_desconectado;
	}


	public void setPregunta_desconectado(Preguntas pregunta_desconectado) {
		this.pregunta_desconectado = pregunta_desconectado;
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


	public boolean isCheck_1() {
		return check_1;
	}


	public void setCheck_1(boolean check_1) {
		this.check_1 = check_1;
	}


	public boolean isCheck_2() {
		return check_2;
	}


	public void setCheck_2(boolean check_2) {
		this.check_2 = check_2;
	}


	public boolean isCheck_3() {
		return check_3;
	}


	public void setCheck_3(boolean check_3) {
		this.check_3 = check_3;
	}


	public boolean isCheck_4() {
		return check_4;
	}


	public void setCheck_4(boolean check_4) {
		this.check_4 = check_4;
	}


	// ACCESORES PARA SPRING
	public void setF_preguntas(I_Preguntas f_preguntas) {
		this.f_preguntas = f_preguntas;
	}

}
