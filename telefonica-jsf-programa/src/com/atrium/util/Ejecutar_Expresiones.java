package com.atrium.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class Ejecutar_Expresiones {

	public static <T> T obtener_Objeto(String expresion, Class<T> tipo_objeto) {
		// RECUPERAMOS EL CONTEXTO GENERAL DE JSF PARA REALIZAR EL TRABAJO
		FacesContext contexto_generalJSF = FacesContext.getCurrentInstance();
		// PREPARAMOS LOS OBJETOS NECESARIOS DEL CONTEXO DE JSF
		ELContext contexto_expresionesUEL = contexto_generalJSF.getELContext();
		Application application = contexto_generalJSF.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		// PREPARAMOS UN LANZADOR DE EXPRESIONES PARA EJECUTAR LA EXPRESION
		ValueExpression lanzador_expresiones = expressionFactory.createValueExpression(contexto_expresionesUEL, expresion, tipo_objeto);
		// LANZAMOS PROGRAMATICAMENTA LA EXPRESION QUE RECUPERA EL OBJETO PEDIDO
		return (T) lanzador_expresiones.getValue(contexto_expresionesUEL);
	}

}
