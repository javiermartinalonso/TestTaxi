package com.testtaxi.jsf.managedbean.sinuso;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.testtaxi.jsf.managedbean.Login_Bean;
import com.testtaxi.jsf.managedbean.Mensajes_Bean;
import com.testtaxi.jsf.managedbean.Menu_Bean;
import com.testtaxi.jsf.managedbean.TestGenerico_Bean;
import com.testtaxi.util.Acceso_Contextos;

/**
 * @author JAVI
 *
 */
public class Salir_Bean implements Serializable{
	private static final long serialVersionUID = 1L;

	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(TestGenerico_Bean.class);

	private String salir = "/resources/images/salir/exit_3.png";
	
	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public Salir_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* Salir ******------");
						
		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
		

		
		
//		mensaje.cargar_Mensaje("Salir", "El usuario ".concat(usuario_nuevo.getUsername()).concat(" se dio de alta de forma correcta"), "/resources/images/operacion_correcta.png","Aceptar", "");
		mensaje.cargar_Mensaje("Salir", "¿Realmente deseas salir?", "/resources/images/salir/exit_3.png", "Desconectar", "#{salir_bean.aceptar}", "Cancelar", "#{salir_bean.cancelar}"); 
//		       cargar_Mensaje(String cabecera, String mensaje, String icono, String texto_boton_aceptar, String metodo_js_aceptar, String texto_boton_cancelar, String metodo_js_cancelar) 
	}	
	
	
	
	
	// ******** TRATAMIENTO DE ACCIONES DE LOS BOTONES ************
	// RESOLUCION DEL EVENTO DE LOS BOTONES DEL FORMULARIO
	public void aceptar(ActionEvent evento) {
		
//		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
//		mensaje.cargar_Mensaje("Salir", "¿Realmente deseas salir?", "/resources/images/salir/exit_3.png", "Desconectar", "#{salir_bean.aceptar}", "Cancelar", "#{salir_bean.cancelar}"); 

		
		//CERRAR SESION
		Acceso_Contextos.getSesion().invalidate();
		
		//OCULTAMOS LA APLICACION Y MOSTRAMOS EL LOGIN DE NUEVO
		Menu_Bean aplicacion = ((Menu_Bean) Acceso_Contextos.getAtributo("menu_bean"));
		aplicacion.setVisible(false);
		
		Login_Bean login = ((Login_Bean) Acceso_Contextos.getAtributo("login_bean"));
		login.setLogin_visible(true);	
		
	}
	
	public void exit(ActionEvent evento) {
		
		Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));
		mensaje.cargar_Mensaje("Salir", "¿Realmente deseas salir?", "/resources/images/salir/exit_3.png", "Desconectar", "#{salir_bean.aceptar}", "Cancelar", "#{salir_bean.cancelar}"); 
	}
	
	public void cancelar(ActionEvent evento) {
		System.out.println("QUE HAGO???");
	}




	public String getSalir() {
		return salir;
	}




	public void setSalir(String salir) {
		this.salir = salir;
	}
	
	
	
	
}
