/*
 * Created on 28 ago 2017 ( Time 17:51:40 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.math.BigDecimal;

public class Destinohorario implements Serializable {

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
    private Long horarioid;

    @NotNull
    private Long destinoid;

    @NotNull
    private BigDecimal costo;

    @NotNull
    private Integer minimopasajeros;

    @NotNull
    private Byte automatico;



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
    public void setHorarioid( Long horarioid ) {
        this.horarioid = horarioid;
    }
    public Long getHorarioid() {
        return this.horarioid;
    }

    public void setDestinoid( Long destinoid ) {
        this.destinoid = destinoid;
    }
    public Long getDestinoid() {
        return this.destinoid;
    }

    public void setCosto( BigDecimal costo ) {
        this.costo = costo;
    }
    public BigDecimal getCosto() {
        return this.costo;
    }

    public void setMinimopasajeros( Integer minimopasajeros ) {
        this.minimopasajeros = minimopasajeros;
    }
    public Integer getMinimopasajeros() {
        return this.minimopasajeros;
    }

    public void setAutomatico( Byte automatico ) {
        this.automatico = automatico;
    }
    public Byte getAutomatico() {
        return this.automatico;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(horarioid);
        sb.append("|");
        sb.append(destinoid);
        sb.append("|");
        sb.append(costo);
        sb.append("|");
        sb.append(minimopasajeros);
        sb.append("|");
        sb.append(automatico);
        return sb.toString(); 
    } 


}
