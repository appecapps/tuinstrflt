/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.tuin.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "menu"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="menu", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="MenuEntity.countAll", query="SELECT COUNT(x) FROM MenuEntity x" )
} )
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nombre", nullable=false, length=45)
    private String     nombre       ;

    @Column(name="orden", nullable=false, length=45)
    private String     orden        ;

    @Column(name="activo", nullable=false)
    private Byte       activo       ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="menu", targetEntity=ItemmenuEntity.class)
    private List<ItemmenuEntity> listOfItemmenu;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public MenuEntity() {
		super();
    }
    
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
    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }

    //--- DATABASE MAPPING : orden ( VARCHAR ) 
    public void setOrden( String orden ) {
        this.orden = orden;
    }
    public String getOrden() {
        return this.orden;
    }

    //--- DATABASE MAPPING : activo ( TINYINT ) 
    public void setActivo( Byte activo ) {
        this.activo = activo;
    }
    public Byte getActivo() {
        return this.activo;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfItemmenu( List<ItemmenuEntity> listOfItemmenu ) {
        this.listOfItemmenu = listOfItemmenu;
    }
    public List<ItemmenuEntity> getListOfItemmenu() {
        return this.listOfItemmenu;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(nombre);
        sb.append("|");
        sb.append(orden);
        sb.append("|");
        sb.append(activo);
        return sb.toString(); 
    } 

}
