package com.testtaxi.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Preguntas entity. @author MyEclipse Persistence Tools
 */

public class Preguntas  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String textoPregunta;
     private String puntuacion;
     private String indicacionesPregunta;
     private String foto;
     private Short dificultad;
     private String idRespuestaCorrecta;
     private Short numRespuestas;
     private String respuesta1;
     private String respuesta2;
     private String respuesta3;
     private String respuesta4;
     private String respuesta5;
     private String respuesta6;
     private String comentario;
     private String tipoPregunta;
     private Set testses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Preguntas() {
    }

	/** minimal constructor */
    public Preguntas(String textoPregunta, Short dificultad, String idRespuestaCorrecta, Short numRespuestas, String respuesta1, String respuesta2, String respuesta3, String respuesta4) {
        this.textoPregunta = textoPregunta;
        this.dificultad = dificultad;
        this.idRespuestaCorrecta = idRespuestaCorrecta;
        this.numRespuestas = numRespuestas;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
    }
    
    /** full constructor */
    public Preguntas(String textoPregunta, String puntuacion, String indicacionesPregunta, String foto, Short dificultad, String idRespuestaCorrecta, Short numRespuestas, String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuesta5, String respuesta6, String comentario, String tipoPregunta, Set testses) {
        this.textoPregunta = textoPregunta;
        this.puntuacion = puntuacion;
        this.indicacionesPregunta = indicacionesPregunta;
        this.foto = foto;
        this.dificultad = dificultad;
        this.idRespuestaCorrecta = idRespuestaCorrecta;
        this.numRespuestas = numRespuestas;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.respuesta5 = respuesta5;
        this.respuesta6 = respuesta6;
        this.comentario = comentario;
        this.tipoPregunta = tipoPregunta;
        this.testses = testses;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoPregunta() {
        return this.textoPregunta;
    }
    
    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public String getPuntuacion() {
        return this.puntuacion;
    }
    
    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getIndicacionesPregunta() {
        return this.indicacionesPregunta;
    }
    
    public void setIndicacionesPregunta(String indicacionesPregunta) {
        this.indicacionesPregunta = indicacionesPregunta;
    }

    public String getFoto() {
        return this.foto;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Short getDificultad() {
        return this.dificultad;
    }
    
    public void setDificultad(Short dificultad) {
        this.dificultad = dificultad;
    }

    public String getIdRespuestaCorrecta() {
        return this.idRespuestaCorrecta;
    }
    
    public void setIdRespuestaCorrecta(String idRespuestaCorrecta) {
        this.idRespuestaCorrecta = idRespuestaCorrecta;
    }

    public Short getNumRespuestas() {
        return this.numRespuestas;
    }
    
    public void setNumRespuestas(Short numRespuestas) {
        this.numRespuestas = numRespuestas;
    }

    public String getRespuesta1() {
        return this.respuesta1;
    }
    
    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return this.respuesta2;
    }
    
    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return this.respuesta3;
    }
    
    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return this.respuesta4;
    }
    
    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public String getRespuesta5() {
        return this.respuesta5;
    }
    
    public void setRespuesta5(String respuesta5) {
        this.respuesta5 = respuesta5;
    }

    public String getRespuesta6() {
        return this.respuesta6;
    }
    
    public void setRespuesta6(String respuesta6) {
        this.respuesta6 = respuesta6;
    }

    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoPregunta() {
        return this.tipoPregunta;
    }
    
    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Set getTestses() {
        return this.testses;
    }
    
    public void setTestses(Set testses) {
        this.testses = testses;
    }
   








}