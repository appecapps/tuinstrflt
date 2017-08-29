/*
 * Created on 28 ago 2017 ( Time 17:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Vehiculoservicio implements Serializable {

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
    private Long vehiculoid;

    @NotNull
    private Long servicioid;



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
    public void setVehiculoid( Long vehiculoid ) {
        this.vehiculoid = vehiculoid;
    }
    public Long getVehiculoid() {
        return this.vehiculoid;
    }

    public void setServicioid( Long servicioid ) {
        this.servicioid = servicioid;
    }
    public Long getServicioid() {
        return this.servicioid;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(vehiculoid);
        sb.append("|");
        sb.append(servicioid);
        return sb.toString(); 
    } 


}