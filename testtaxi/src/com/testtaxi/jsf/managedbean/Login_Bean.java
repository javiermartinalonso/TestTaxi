package com.testtaxi.jsf.managedbean;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.testtaxi.crypto.Encriptador;
import com.testtaxi.hibernate.Usuarios;
import com.testtaxi.hibernate.modelo.I_Usuario;
import com.testtaxi.util.Acceso_Contextos;

public class Login_Bean implements Serializable{
	private static final long serialVersionUID = 1L;
	// PROPIEDADES DEL FORMULARIO DE LOGIN
	private String nombre_usuario;
	private String clave_usuario;
	// CONTROL DE VISIBILIDAD DEL PANEL DE LOGIN
	private boolean login_visible;

	private I_Usuario f_usuarios;
	
	
//    private static HashMap<String, Integer> severityMap = new HashMap<String, Integer>() {{
//        put("Nombre", 0);
//        put("Clave", 1);
//    }
//    private static final long serialVersionUID = 6584997908723158778L;
//    };
//    
//    
//    private static String[] severityNames = {"Info", "Warn", "Error", "Fatal"};
//	
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Login_Bean.class);

	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public Login_Bean() {
		login_visible = true;
		nombre_usuario = "";
		clave_usuario = "";
	}

	public void comprobar_Credenciales() {
		if (log.isTraceEnabled())
			log.trace("-----******* COMPROBAMOS CREDENCIALES DE USUARIO " + getNombre_usuario() + " ******------");
			
		Usuarios usuario = f_usuarios.consultar_PorClave(getNombre_usuario());
		if (usuario != null) {
			String password_encriptada = Encriptador.encriptar(getClave_usuario());

			if (password_encriptada.equals(usuario.getPassword())) {
				// CORRECTO
				//GUARDAMOS EL OBJETO USUARIO EN LA SESION
				Acceso_Contextos.getSesion().setAttribute("usuario", usuario);
				login_visible = false;
				// HACEMOS VISIBLE EL MENU EN EL MOMENTO EN QUE EL USUARIO ES CORRECTO
				Menu_Bean menu_bean = (Menu_Bean) Acceso_Contextos.getSesion().getAttribute("menu_bean");
				menu_bean.setVisible(true);
				menu_bean.crear_Menu();
			} else {
				// ERROR EN LA CLAVE				
				Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));				
				mensaje.cargar_Mensaje("Login", "Error en la clave. Asegurese que no esta apretado Mayusculas", "/resources/images/error/error-2.png", "aceptar", ""); 
			}
		} else {
			// ERROR EN EL NOMBRE
			Mensajes_Bean mensaje = ((Mensajes_Bean) Acceso_Contextos.getAtributo("mensajes_bean"));				
			mensaje.cargar_Mensaje("Login", "No existe un usuario con ese identificador", "/resources/images/error/error-2.png", "aceptar", ""); 
		}
	}
	
	
	
//    public void blurListener(AjaxBehaviorEvent event) {
//        TextEntry textEntry = (TextEntry) event.getComponent();
//        String value = textEntry.getValue().toString().trim();
//        String label = textEntry.getLabel();
//        if (value.equals("") || value.equalsIgnoreCase(label)) {
//           
//            String message;
//            if (value.equals("")) {
//                message = "  Debe introducir un " + label + ".";
//            } else {
//            	message = "  Debe introducir un " + label + ".";
//            }
//            FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), message, message);
//            FacesContext.getCurrentInstance().addMessage(textEntry.getClientId(), facesMessage);
//        }
//    }
    
    
    //TODO revisar la validacion del formulario de login
    public void blurListener(AjaxBehaviorEvent event) {
    	
		// RECOGEMOS EL COMPONENTE QUE GENERA EL EVENTO
//		UIComponent componente = event.getComponent();
//		
//		System.out.println("para");
////		componente.
    	
    	
//    	InputTextTag textEntry = (InputTextTag) event.getComponent();
//        String value = textEntry.   getValue().toString().trim();
//        String label = textEntry.getLabel();
//        if (value.equals("") || value.equalsIgnoreCase(label)) {
//            int index = severityMap.get(label);
//            String message;
//            if (value.equals("")) {
//                message = severityNames[index] + ": " + label + " missing.";
//            } else {
//                message = severityNames[index] + ": Value cannot be \"" + value + "\"";
//            }
//            FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), message, message);
//            FacesContext.getCurrentInstance().addMessage(textEntry.getId(), facesMessage);
//        }
    }

	// ACCESORES PARA JSF
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getClave_usuario() {
		return clave_usuario;
	}

	public void setClave_usuario(String clave_usuario) {
		this.clave_usuario = clave_usuario;
	}

	public boolean isLogin_visible() {
		return login_visible;
	}

	public void setLogin_visible(boolean login_visible) {
		this.login_visible = login_visible;
	}


	// ACCESORES PARA SPRING
	public void setF_usuarios(I_Usuario f_usuarios) {
		this.f_usuarios = f_usuarios;
	}
}
