package com.atrium.managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.icefaces.ace.component.datatable.DataTable;
import org.icefaces.ace.event.DateSelectEvent;
import org.icefaces.ace.event.ExpansionChangeEvent;
import org.icefaces.ace.model.table.RowState;

import com.atrium.hibernate.LineaPedido;
import com.atrium.hibernate.Pedidos;
import com.atrium.hibernate.Vencimientos;
import com.atrium.hibernate.modelo.IGestion_Pedido;
import com.atrium.hibernate.modelo.IGestion_Vencimientos;
import com.atrium.spring.Acceso_ApplicationContext;
import com.atrium.util.Ejecutar_Expresiones;

public class Vencimientos_Bean {
	// PROPIEDADES DE PROCESO
	// PROPIEDADES PARA LA PAGINA JSF
	// coleccion de vencimientos a mostrar en el datatable
	private List<Vencimientos> lista_vencimientos;

	// PROPIEDADES PROCESO DE BUSQUEDA DE VENCIMIENTOS
	private Date primer_diames;
	private Date ultimo_dia;

	// PROPIEDADES PARA LOS PROCESOS DE MODELO
	private IGestion_Vencimientos gestion_vencimientos;
	private IGestion_Pedido gestion_pedido;

	// PROPIEDAD PARA MOSTRAR EL PEDIDO SELECCIONADO
	private Vencimientos vencimiento_seleccionado;
	private Pedidos pedido_completado;

	// COLECCION DE LINEAS DE PEDIDO A MOSTRAR
	private List<LineaPedido> lista_lineas;

	// PROPIEDADES PARA EL FILTRADO DINAMICO
	private String opcion_filtrado;
	private String opcion_elegida;

	/**
	 * Damos los valores iniciales a las fechas con el primer y el ultimo dia
	 * del mes de la fecha en curso.
	 */
	public Vencimientos_Bean() {
		// POR DEFECTO MOSTRAMOS LOS VENCIMIENTOS DEL MES EN CURSO
		GregorianCalendar hoy = new GregorianCalendar();
		// PRIMER DIA DEL MES
		hoy.set(Calendar.DAY_OF_MONTH, 1);
		// INICIO DEL DIA
		hoy.set(Calendar.HOUR, 0);
		hoy.set(Calendar.MINUTE, 0);
		hoy.set(Calendar.SECOND, 0);
		setPrimer_diames(hoy.getTime());

		// ULTIMO DIA DEL MES
		hoy.set(Calendar.DAY_OF_MONTH, hoy.getMaximum(Calendar.DAY_OF_MONTH));
		// FIN DEL DIA
		hoy.set(Calendar.HOUR, 23);
		hoy.set(Calendar.MINUTE, 59);
		hoy.set(Calendar.SECOND, 59);
		setUltimo_dia(hoy.getTime());

		gestion_vencimientos = Acceso_ApplicationContext
				.getBean(IGestion_Vencimientos.class);

		this.inicio_Bean();
	}

	/**
	 * Proceso de consulta de los vencimientos entre dos fechas dadas.<br/>
	 * Las fechas corresponden a los valores de las propiedades de clase
	 * primerdia_mes y ultimodia_mes.
	 */
	public void inicio_Bean() {
		// CONSULTAMOS LOS VENCIMIENTOS DEL MES
		List<Vencimientos> lista_vencidos = gestion_vencimientos
				.consultar_Vencimientos(primer_diames, ultimo_dia);
		// CREAMOS LA COLECCION PARA LOS VENCIMIENTOS SELECCIONABLES DE LA TABLA
		setLista_vencimientos(new ArrayList<Vencimientos>(0));
		// CARGAMOS LA TABLA CON LOS VENCIMIENTOS ENCONTRADOS
		lista_vencimientos.addAll(lista_vencidos);
	}

	// ********** ZONA DE EVENTOS ****************
	/**
	 * Evento de cambio de fecha. A partir de este evento modificamos la fecha
	 * inicial o final de busqueda y se recarga el contenido del datatable.
	 * 
	 * @param evento
	 */
	public void cambiar_Fecha(DateSelectEvent evento) {
		// CAMBIAMOS LA FECHA ELEGIDA POR EL USUARIO
		if (evento.getComponent().getId().equals("fecha_inicial")) {
			setPrimer_diames((Date) evento.getDate());
		} else {
			setUltimo_dia((Date) evento.getDate());
		}
		// RECARGAMOS LA COLECCION DE DATOS A MOSTRAR EN EL DATATABLE
		this.inicio_Bean();
	}

	/**
	 * Opcion para el rowselector.
	 * 
	 * @param evento
	 */
	public void recuperar_Pedido(ExpansionChangeEvent evento) {
		// RECOGEMOS DEL MODELO EL VENCIMIENTO SELECCIONADO
		vencimiento_seleccionado = (Vencimientos) evento.getRowData();
		// OBTENEMOS LA INFORMACION COMPLETA DEL PEDIDO A PARTIR DEL VENCIMIENTO
		// SELECCIONADO
		pedido_completado = gestion_pedido
				.completar_Pedido(vencimiento_seleccionado.getPedido());
		// HAGO VISIBLE LAS LINEAS DE PEDIDO
		if (lista_lineas == null) {
			lista_lineas = new ArrayList<LineaPedido>();
		} else {
			lista_lineas.clear();
		}
		lista_lineas.addAll(pedido_completado.getLineaPedidos());
		// RECOGEMOS EL COMPONENTE QUE GENERA EL EVENTO
		UIComponent componente = evento.getComponent();
		// BUSCAMOS LA TABLA (OBJETO DATATABLE) A PARTIR DEL COMPONENTE QUE
		// GENERA EL EVENTO
		DataTable tabla_vencimientos = null;
		UIComponent nodo_anterior = componente;
		// VAMOS RECORRIENDO A LA INVERSA EL ARBOL DE NODOS PARA LLEGAR A LA
		// TABLA
		while (nodo_anterior != null) {
			if (nodo_anterior instanceof DataTable) {
				tabla_vencimientos = ((DataTable) nodo_anterior);
				break;
			} else {
				nodo_anterior = nodo_anterior.getParent();
			}
		}
		// UNA VEZ TENEMOS LA TABLA RECOGEMOS LA FILA SELECCIONADA
		Integer fila_seleccionada = tabla_vencimientos.getRowIndex();
		int numero_filas = tabla_vencimientos.getRowCount();
		// RECORREMOS TODAS LAS FILAS CERRANDO EL EXPANSIONTOGLE MENOS EN LA
		// SELECCIONADA
		for (int fila = 0; fila < numero_filas; fila++) {
			if (fila == fila_seleccionada) {
				// COLOCAMOS COMO FILA ACTIVA LA TRATADA POR EL FOR
				tabla_vencimientos.setRowIndex(fila);
				// LANZAMOS PROGRAMATICAMENTA LA EXPRESION QUE RECUPERA EL
				// ROWSTATE
				RowState interruptor = Ejecutar_Expresiones.obtener_Objeto("#{rowState}", RowState.class);
				// LO ABRIMOS
				interruptor.setExpanded(true);
			} else {
				// COLOCAMOS COMO FILA ACTIVA LA TRATADA POR EL FOR
				tabla_vencimientos.setRowIndex(fila);
				// LANZAMOS PROGRAMATICAMENTA LA EXPRESION QUE RECUPERA EL
				// ROWSTATE
				RowState interruptor = Ejecutar_Expresiones.obtener_Objeto("#{rowState}", RowState.class);
				// LO CERRAMOS
				interruptor.setExpanded(false);
			}
		}
	}

	// ACCESORES PARA JSF
	public List<Vencimientos> getLista_vencimientos() {
		return lista_vencimientos;
	}

	public void setLista_vencimientos(List<Vencimientos> lista_vencimientos) {
		this.lista_vencimientos = lista_vencimientos;
	}

	public Date getPrimer_diames() {
		return primer_diames;
	}

	public void setPrimer_diames(Date primer_diames) {
		this.primer_diames = primer_diames;
	}

	public Date getUltimo_dia() {
		return ultimo_dia;
	}

	public void setUltimo_dia(Date ultimo_dia) {
		this.ultimo_dia = ultimo_dia;
	}

	public Vencimientos getVencimiento_seleccionado() {
		return vencimiento_seleccionado;
	}

	public void setVencimiento_seleccionado(
			Vencimientos vencimiento_seleccionado) {
		this.vencimiento_seleccionado = vencimiento_seleccionado;
	}

	public Pedidos getPedido_completado() {
		return pedido_completado;
	}

	public void setPedido_completado(Pedidos pedido_completado) {
		this.pedido_completado = pedido_completado;
	}

	public String getOpcion_filtrado() {
		return opcion_filtrado;
	}

	public void setOpcion_filtrado(String opcion_filtrado) {
		this.opcion_filtrado = opcion_filtrado;
	}

	public String getOpcion_elegida() {
		return opcion_elegida;
	}

	public void setOpcion_elegida(String opcion_elegida) {
		this.opcion_elegida = opcion_elegida;
	}

	public List<LineaPedido> getLista_lineas() {
		return lista_lineas;
	}

	public void setLista_lineas(List<LineaPedido> lista_lineas) {
		this.lista_lineas = lista_lineas;
	}

	// ACCESORES PARA SPRING
	public void setGestion_vencimientos(
			IGestion_Vencimientos gestion_vencimientos) {
		this.gestion_vencimientos = gestion_vencimientos;
	}

	public void setGestion_pedido(IGestion_Pedido gestion_pedido) {
		this.gestion_pedido = gestion_pedido;
	}

}
