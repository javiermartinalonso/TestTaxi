package com.atrium.hibernate.dao.ext;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.atrium.hibernate.Pedidos;
import com.atrium.hibernate.dao.PedidosDAO;

public class Pedido_EXT extends PedidosDAO {

	/**
	 * Consulta del contenido total (datos de cabecera,cuerpo y pie) de un
	 * pedido concreto.
	 * 
	 * @param numero_pedido
	 * @return
	 */
	public Pedidos completar_Pedido(Integer numero_pedido) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Pedidos.class);
		consulta.setFetchMode("lineaPedidos", FetchMode.JOIN);
		consulta.setFetchMode("lineaPedidos.articulos", FetchMode.JOIN);
		consulta.add(Restrictions.idEq(numero_pedido));
		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<Pedidos> lista_pedido = getHibernateTemplate().findByCriteria(
				consulta);
		Pedidos resultado_consulta = null;
		if (!lista_pedido.isEmpty()) {
			resultado_consulta = lista_pedido.get(0);
		}
		return resultado_consulta;
	}
}
