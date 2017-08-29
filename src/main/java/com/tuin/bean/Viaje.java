/*
 * Created on 28 ago 2017 ( Time 17:51:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Viaje implements Serializable {

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
    private Long destinohorarioid;


    private Long chofervehiculoid;

    @NotNull
    private Byte confirmado;



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
    public void setDestinohorarioid( Long destinohorarioid ) {
        this.destinohorarioid = destinohorarioid;
    }
    public Long getDestinohorarioid() {
        return this.destinohorarioid;
    }

    public void setChofervehiculoid( Long chofervehiculoid ) {
        this.chofervehiculoid = chofervehiculoid;
    }
    public Long getChofervehiculoid() {
        return this.chofervehiculoid;
    }

    public void setConfirmado( Byte confirmado ) {
        this.confirmado = confirmado;
    }
    public Byte getConfirmado() {
        return this.confirmado;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(destinohorarioid);
        sb.append("|");
        sb.append(chofervehiculoid);
        sb.append("|");
        sb.append(confirmado);
        return sb.toString(); 
    } 


}
