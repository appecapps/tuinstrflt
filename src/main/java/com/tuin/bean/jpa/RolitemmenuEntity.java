/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.tuin.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

/**
 * Persistent class for entity stored in table "rolitemmenu"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="rolitemmenu", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="RolitemmenuEntity.countAll", query="SELECT COUNT(x) FROM RolitemmenuEntity x" )
} )
public class RolitemmenuEntity implements Serializable {

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
    @Column(name="activo", nullable=false)
    private Byte       activo       ;

	// "rolid" (column "rolId") is not defined by itself because used as FK in a link 
	// "itemmenuid" (column "itemMenuId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="itemMenuId", referencedColumnName="id")
    private ItemmenuEntity itemmenu    ;

    @ManyToOne
    @JoinColumn(name="rolId", referencedColumnName="id")
    private RolEntity rol         ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RolitemmenuEntity() {
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
    public void setItemmenu( ItemmenuEntity itemmenu ) {
        this.itemmenu = itemmenu;
    }
    public ItemmenuEntity getItemmenu() {
        return this.itemmenu;
    }

    public void setRol( RolEntity rol ) {
        this.rol = rol;
    }
    public RolEntity getRol() {
        return this.rol;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(activo);
        return sb.toString(); 
    } 

}
