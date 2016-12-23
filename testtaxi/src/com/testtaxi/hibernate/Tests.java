package com.testtaxi.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Tests entity. @author MyEclipse Persistence Tools
 */

public class Tests  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tipo;
     private String descripcion;
     private Short duracion;
     private Short dificultad;
     private Short numPreguntas;
     private Short puntosAprobado;
     private Set preguntases = new HashSet(0);


    // Constructors

    /** default constructor */
    public Tests() {
    }

	/** minimal constructor */
    public Tests(String tipo, String descripcion, Short duracion, Short dificultad, Short numPreguntas, Short puntosAprobado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.numPreguntas = numPreguntas;
        this.puntosAprobado = puntosAprobado;
    }
    
    /** full constructor */
    public Tests(String tipo, String descripcion, Short duracion, Short dificultad, Short numPreguntas, Short puntosAprobado, Set preguntases) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.numPreguntas = numPreguntas;
        this.puntosAprobado = puntosAprobado;
        this.preguntases = preguntases;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getDuracion() {
        return this.duracion;
    }
    
    public void setDuracion(Short duracion) {
        this.duracion = duracion;
    }

    public Short getDificultad() {
        return this.dificultad;
    }
    
    public void setDificultad(Short dificultad) {
        this.dificultad = dificultad;
    }

    public Short getNumPreguntas() {
        return this.numPreguntas;
    }
    
    public void setNumPreguntas(Short numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

    public Short getPuntosAprobado() {
        return this.puntosAprobado;
    }
    
    public void setPuntosAprobado(Short puntosAprobado) {
        this.puntosAprobado = puntosAprobado;
    }

    public Set getPreguntases() {
        return this.preguntases;
    }
    
    public void setPreguntases(Set preguntases) {
        this.preguntases = preguntases;
    }
   








}