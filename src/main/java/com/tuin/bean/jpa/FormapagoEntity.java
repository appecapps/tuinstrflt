/*
 * Created on 28 ago 2017 ( Time 17:50:59 )
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
 * Persistent class for entity stored in table "formapago"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="formapago", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="FormapagoEntity.countAll", query="SELECT COUNT(x) FROM FormapagoEntity x" )
} )
public class FormapagoEntity implements Serializable {

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

    @Column(name="activo", nullable=false)
    private Byte       activo       ;

	// "tipodocumentoid" (column "tipoDocumentoId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="formapago", targetEntity=FormapagodocumentoEntity.class)
    private List<FormapagodocumentoEntity> listOfFormapagodocumento;

    @ManyToOne
    @JoinColumn(name="tipoDocumentoId", referencedColumnName="id")
    private TipodocumentoEntity tipodocumento;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public FormapagoEntity() {
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
    public void setListOfFormapagodocumento( List<FormapagodocumentoEntity> listOfFormapagodocumento ) {
        this.listOfFormapagodocumento = listOfFormapagodocumento;
    }
    public List<FormapagodocumentoEntity> getListOfFormapagodocumento() {
        return this.listOfFormapagodocumento;
    }

    public void setTipodocumento( TipodocumentoEntity tipodocumento ) {
        this.tipodocumento = tipodocumento;
    }
    public TipodocumentoEntity getTipodocumento() {
        return this.tipodocumento;
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
        sb.append(activo);
        return sb.toString(); 
    } 

}
