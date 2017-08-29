/*
 * Created on 28 ago 2017 ( Time 17:51:43 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Horario implements Serializable {

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
    private Date fecha;

    @NotNull
    private Date hora;

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
    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return this.fecha;
    }

    public void setHora( Date hora ) {
        this.hora = hora;
    }
    public Date getHora() {
        return this.hora;
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
        sb.append(fecha);
        sb.append("|");
        sb.append(hora);
        sb.append("|");
        sb.append(activo);
        return sb.toString(); 
    } 


}