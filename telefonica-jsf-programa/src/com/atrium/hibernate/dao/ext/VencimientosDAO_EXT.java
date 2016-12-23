package com.atrium.hibernate.dao.ext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.atrium.hibernate.Vencimientos;
import com.atrium.hibernate.dao.VencimientosDAO;


public class VencimientosDAO_EXT extends VencimientosDAO {

	/**
	 * Proceso de consulta de vencimientos entre fechas. Se resuelve la relacion
	 * entre vencimientos,clientes y pedido.
	 * 
	 * @param fecha_inicio
	 * @param fecha_fin
	 * @return
	 */
	public List<Vencimientos> consultar_EntreFechas(Date fecha_inicio,
			Date fecha_fin) {
		List<Vencimientos> lista_venci;
		DetachedCriteria consulta = DetachedCriteria
				.forClass(Vencimientos.class);
		consulta.setFetchMode("clientes", FetchMode.JOIN);
		consulta.setFetchMode("pedido", FetchMode.JOIN);
		consulta.add(Restrictions.between("fechaVencimiento", fecha_inicio,
				fecha_fin));
		lista_venci = getHibernateTemplate().findByCriteria(consulta);
		return lista_venci;
	}

}
