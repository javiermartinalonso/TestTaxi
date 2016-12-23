package com.atrium.hibernate;



/**
 * LineaPedido entity. @author MyEclipse Persistence Tools
 */

public class LineaPedido  implements java.io.Serializable {


    // Fields    

     private Long codigoLineaPedido;
     private Pedidos pedidos;
     private Articulos articulos;
     private Double precioUnidadArticulo;
     private Integer numeroUnidadesArticulo;
     private Double porcentajeDescuento;


    // Constructors

    /** default constructor */
    public LineaPedido() {
    }

	/** minimal constructor */
    public LineaPedido(Long codigoLineaPedido) {
        this.codigoLineaPedido = codigoLineaPedido;
    }
    
    /** full constructor */
    public LineaPedido(Long codigoLineaPedido, Pedidos pedidos, Articulos articulos, Double precioUnidadArticulo, Integer numeroUnidadesArticulo, Double porcentajeDescuento) {
        this.codigoLineaPedido = codigoLineaPedido;
        this.pedidos = pedidos;
        this.articulos = articulos;
        this.precioUnidadArticulo = precioUnidadArticulo;
        this.numeroUnidadesArticulo = numeroUnidadesArticulo;
        this.porcentajeDescuento = porcentajeDescuento;
    }

   
    // Property accessors

    public Long getCodigoLineaPedido() {
        return this.codigoLineaPedido;
    }
    
    public void setCodigoLineaPedido(Long codigoLineaPedido) {
        this.codigoLineaPedido = codigoLineaPedido;
    }

    public Pedidos getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Articulos getArticulos() {
        return this.articulos;
    }
    
    public void setArticulos(Articulos articulos) {
        this.articulos = articulos;
    }

    public Double getPrecioUnidadArticulo() {
        return this.precioUnidadArticulo;
    }
    
    public void setPrecioUnidadArticulo(Double precioUnidadArticulo) {
        this.precioUnidadArticulo = precioUnidadArticulo;
    }

    public Integer getNumeroUnidadesArticulo() {
        return this.numeroUnidadesArticulo;
    }
    
    public void setNumeroUnidadesArticulo(Integer numeroUnidadesArticulo) {
        this.numeroUnidadesArticulo = numeroUnidadesArticulo;
    }

    public Double getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }
    
    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
   








}