package com.atrium.hibernate.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.atrium.hibernate.Vencimientos;
import com.atrium.hibernate.dao.ext.VencimientosDAO_EXT;


public class Gestion_Vencimientos implements Serializable, IGestion_Vencimientos{

	private static final long serialVersionUID = 1;
	
	
	private VencimientosDAO_EXT vencimiento_DAO;

	@Override
	public List<Vencimientos> consultar_Vencimientos(Date fecha_inicio,
			Date fecha_fin) {
		List<Vencimientos> lista = vencimiento_DAO.consultar_EntreFechas(
				fecha_inicio, fecha_fin);
		return lista;
	}

	// ACCESORES PARA SPRING
	public void setVencimiento_DAO(VencimientosDAO_EXT vencimiento_DAO) {
		this.vencimiento_DAO = vencimiento_DAO;
	}

}
