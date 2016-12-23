package com.testtaxi.hibernate;

import java.util.Date;


/**
 * Usuarios entity.
 */

public class Usuarios  implements java.io.Serializable {


    // Fields    

     private String username;
     private String password;
     private String rol;
     private String nombre;
     private String apellido1;
     private String apellido2;
     private String email;
     private String telefono;
     private String activo;
     private Date fechaAlta;
     private Date fechaVencimiento;


    // Constructors

    /** default constructor */
    public Usuarios() {
    }

	/** minimal constructor */
    public Usuarios(String username, String password, String rol, String nombre, String apellido1, String activo, Date fechaAlta, Date fechaVencimiento) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    /** full constructor */
    public Usuarios(String username, String password, String rol, String nombre, String apellido1, String apellido2, String email, String telefono, String activo, Date fechaAlta, Date fechaVencimiento) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaVencimiento = fechaVencimiento;
    }

   
    // Property accessors

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return this.apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getActivo() {
        return this.activo;
    }
    
    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }
    
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}