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
 * Persistent class for entity stored in table "chofer"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="chofer", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ChoferEntity.countAll", query="SELECT COUNT(x) FROM ChoferEntity x" )
} )
public class ChoferEntity implements Serializable {

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
    @Column(name="prioridad", nullable=false)
    private Integer    prioridad    ;

    @Column(name="tipoLicencia", length=1)
    private String     tipolicencia ;

    @Column(name="latitud")
    private BigDecimal latitud      ;

    @Column(name="logitud")
    private BigDecimal logitud      ;

	// "entidadid" (column "entidadId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="entidadId", referencedColumnName="id")
    private EntidadEntity entidad     ;

    @OneToMany(mappedBy="chofer", targetEntity=ChofervehiculoEntity.class)
    private List<ChofervehiculoEntity> listOfChofervehiculo;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ChoferEntity() {
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
    //--- DATABASE MAPPING : prioridad ( INT ) 
    public void setPrioridad( Integer prioridad ) {
        this.prioridad = prioridad;
    }
    public Integer getPrioridad() {
        return this.prioridad;
    }

    //--- DATABASE MAPPING : tipoLicencia ( VARCHAR ) 
    public void setTipolicencia( String tipolicencia ) {
        this.tipolicencia = tipolicencia;
    }
    public String getTipolicencia() {
        return this.tipolicencia;
    }

    //--- DATABASE MAPPING : latitud ( DECIMAL ) 
    public void setLatitud( BigDecimal latitud ) {
        this.latitud = latitud;
    }
    public BigDecimal getLatitud() {
        return this.latitud;
    }

    //--- DATABASE MAPPING : logitud ( DECIMAL ) 
    public void setLogitud( BigDecimal logitud ) {
        this.logitud = logitud;
    }
    public BigDecimal getLogitud() {
        return this.logitud;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setEntidad( EntidadEntity entidad ) {
        this.entidad = entidad;
    }
    public EntidadEntity getEntidad() {
        return this.entidad;
    }

    public void setListOfChofervehiculo( List<ChofervehiculoEntity> listOfChofervehiculo ) {
        this.listOfChofervehiculo = listOfChofervehiculo;
    }
    public List<ChofervehiculoEntity> getListOfChofervehiculo() {
        return this.listOfChofervehiculo;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(prioridad);
        sb.append("|");
        sb.append(tipolicencia);
        sb.append("|");
        sb.append(latitud);
        sb.append("|");
        sb.append(logitud);
        return sb.toString(); 
    } 

}
