/*
 * Created on 28 ago 2017 ( Time 17:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Vehiculo implements Serializable {

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
    @Size( min = 1, max = 25 )
    private String placa;

    @NotNull
    @Size( min = 1, max = 45 )
    private String motor;

    @NotNull
    @Size( min = 1, max = 45 )
    private String chasis;

    @NotNull
    private Long modeloid;

    @NotNull
    private Long colorid;

    @NotNull
    private Integer pasajeros;

    @NotNull
    private Integer anio;



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
    public void setPlaca( String placa ) {
        this.placa = placa;
    }
    public String getPlaca() {
        return this.placa;
    }

    public void setMotor( String motor ) {
        this.motor = motor;
    }
    public String getMotor() {
        return this.motor;
    }

    public void setChasis( String chasis ) {
        this.chasis = chasis;
    }
    public String getChasis() {
        return this.chasis;
    }

    public void setModeloid( Long modeloid ) {
        this.modeloid = modeloid;
    }
    public Long getModeloid() {
        return this.modeloid;
    }

    public void setColorid( Long colorid ) {
        this.colorid = colorid;
    }
    public Long getColorid() {
        return this.colorid;
    }

    public void setPasajeros( Integer pasajeros ) {
        this.pasajeros = pasajeros;
    }
    public Integer getPasajeros() {
        return this.pasajeros;
    }

    public void setAnio( Integer anio ) {
        this.anio = anio;
    }
    public Integer getAnio() {
        return this.anio;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(placa);
        sb.append("|");
        sb.append(motor);
        sb.append("|");
        sb.append(chasis);
        sb.append("|");
        sb.append(modeloid);
        sb.append("|");
        sb.append(colorid);
        sb.append("|");
        sb.append(pasajeros);
        sb.append("|");
        sb.append(anio);
        return sb.toString(); 
    } 


}