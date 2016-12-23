package com.atrium.hibernate.modelo;

import com.atrium.hibernate.Pedidos;
import com.atrium.hibernate.dao.ext.Pedido_EXT;

public class Gestion_Pedido implements IGestion_Pedido {

	private Pedido_EXT pedido_dao;

	@Override
	public Pedidos completar_Pedido(Pedidos pedido) {
		return pedido_dao.completar_Pedido(pedido.getNumeroPedido());
	}

	// ACCESORES PARA SPRING
	public void setPedido_dao(Pedido_EXT pedido_dao) {
		this.pedido_dao = pedido_dao;
	}

}
