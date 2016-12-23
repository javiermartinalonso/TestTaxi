package com.atrium.hibernate.modelo;

import java.util.Date;
import java.util.List;

import com.atrium.hibernate.Vencimientos;

public interface IGestion_Vencimientos {

	public abstract List<Vencimientos> consultar_Vencimientos(
			Date fecha_inicio, Date fecha_fin);

}