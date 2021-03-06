/*
 * Created on 28 ago 2017 ( Time 17:51:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Estado implements Serializable {

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
    @Size( min = 1, max = 45 )
    private String tabla;



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

    public void setTabla( String tabla ) {
        this.tabla = tabla;
    }
    public String getTabla() {
        return this.tabla;
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
        sb.append(tabla);
        return sb.toString(); 
    } 


}
