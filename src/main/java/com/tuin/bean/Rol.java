/*
 * Created on 28 ago 2017 ( Time 17:51:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    @Size( min = 1, max = 45 )
    private String nombre;

    @NotNull
    private Byte activo;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setActivo( Byte activo ) {
        this.activo = activo;
    }
    public Byte getActivo() {
        return this.activo;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(nombre);
        sb.append("|");
        sb.append(activo);
        return sb.toString(); 
    } 


}