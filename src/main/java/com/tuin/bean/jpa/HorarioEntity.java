/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.tuin.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "horario"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="horario", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="HorarioEntity.countAll", query="SELECT COUNT(x) FROM HorarioEntity x" )
} )
public class HorarioEntity implements Serializable {

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
    @Temporal(TemporalType.DATE)
    @Column(name="fecha", nullable=false)
    private Date       fecha        ;

    @Temporal(TemporalType.TIME)
    @Column(name="hora", nullable=false)
    private Date       hora         ;

    @Column(name="activo", nullable=false)
    private Byte       activo       ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="horario", targetEntity=DestinohorarioEntity.class)
    private List<DestinohorarioEntity> listOfDestinohorario;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public HorarioEntity() {
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
    //--- DATABASE MAPPING : fecha ( DATE ) 
    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return this.fecha;
    }

    //--- DATABASE MAPPING : hora ( TIME ) 
    public void setHora( Date hora ) {
        this.hora = hora;
    }
    public Date getHora() {
        return this.hora;
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
        sb.append(fecha);
        sb.append("|");
        sb.append(hora);
        sb.append("|");
        sb.append(activo);
        return sb.toString(); 
    } 

}