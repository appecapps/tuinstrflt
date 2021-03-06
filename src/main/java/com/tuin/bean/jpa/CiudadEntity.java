/*
 * Created on 28 ago 2017 ( Time 17:50:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.tuin.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "ciudad"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="ciudad", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CiudadEntity.countAll", query="SELECT COUNT(x) FROM CiudadEntity x" )
} )
public class CiudadEntity implements Serializable {

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

    @Column(name="costo", nullable=false)
    private BigDecimal costo        ;

	// "provinciaid" (column "provinciaId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="provinciaId", referencedColumnName="id")
    private ProvinciaEntity provincia   ;

    @OneToMany(mappedBy="ciudad", targetEntity=DestinohorarioEntity.class)
    private List<DestinohorarioEntity> listOfDestinohorario;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CiudadEntity() {
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

    //--- DATABASE MAPPING : costo ( DECIMAL ) 
    public void setCosto( BigDecimal costo ) {
        this.costo = costo;
    }
    public BigDecimal getCosto() {
        return this.costo;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setProvincia( ProvinciaEntity provincia ) {
        this.provincia = provincia;
    }
    public ProvinciaEntity getProvincia() {
        return this.provincia;
    }

    public void setListOfDestinohorario( List<DestinohorarioEntity> listOfDestinohorario ) {
        this.listOfDestinohorario = listOfDestinohorario;
    }
    public List<DestinohorarioEntity> getListOfDestinohorario() {
        return this.listOfDestinohorario;
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
        sb.append("|");
        sb.append(costo);
        return sb.toString(); 
    } 

}
