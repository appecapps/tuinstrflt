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
 * Persistent class for entity stored in table "clienteviaje"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="clienteviaje", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClienteviajeEntity.countAll", query="SELECT COUNT(x) FROM ClienteviajeEntity x" )
} )
public class ClienteviajeEntity implements Serializable {

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
    @Column(name="latitud", nullable=false)
    private BigDecimal latitud      ;

    @Column(name="longitud", nullable=false)
    private BigDecimal longitud     ;

    @Column(name="pasajeros", nullable=false)
    private Integer    pasajeros    ;

    @Column(name="costo", nullable=false)
    private BigDecimal costo        ;

	// "viajeid" (column "viajeId") is not defined by itself because used as FK in a link 
	// "clienteid" (column "clienteId") is not defined by itself because used as FK in a link 
	// "estadoid" (column "estadoId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="viajeId", referencedColumnName="id")
    private ViajeEntity viaje       ;

    @ManyToOne
    @JoinColumn(name="clienteId", referencedColumnName="id")
    private ClienteEntity cliente     ;

    @ManyToOne
    @JoinColumn(name="estadoId", referencedColumnName="id")
    private EstadoEntity estado      ;

    @OneToMany(mappedBy="clienteviaje", targetEntity=ClienteviajeservicioEntity.class)
    private List<ClienteviajeservicioEntity> listOfClienteviajeservicio;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ClienteviajeEntity() {
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
    //--- DATABASE MAPPING : latitud ( DECIMAL ) 
    public void setLatitud( BigDecimal latitud ) {
        this.latitud = latitud;
    }
    public BigDecimal getLatitud() {
        return this.latitud;
    }

    //--- DATABASE MAPPING : longitud ( DECIMAL ) 
    public void setLongitud( BigDecimal longitud ) {
        this.longitud = longitud;
    }
    public BigDecimal getLongitud() {
        return this.longitud;
    }

    //--- DATABASE MAPPING : pasajeros ( INT ) 
    public void setPasajeros( Integer pasajeros ) {
        this.pasajeros = pasajeros;
    }
    public Integer getPasajeros() {
        return this.pasajeros;
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
    public void setViaje( ViajeEntity viaje ) {
        this.viaje = viaje;
    }
    public ViajeEntity getViaje() {
        return this.viaje;
    }

    public void setCliente( ClienteEntity cliente ) {
        this.cliente = cliente;
    }
    public ClienteEntity getCliente() {
        return this.cliente;
    }

    public void setEstado( EstadoEntity estado ) {
        this.estado = estado;
    }
    public EstadoEntity getEstado() {
        return this.estado;
    }

    public void setListOfClienteviajeservicio( List<ClienteviajeservicioEntity> listOfClienteviajeservicio ) {
        this.listOfClienteviajeservicio = listOfClienteviajeservicio;
    }
    public List<ClienteviajeservicioEntity> getListOfClienteviajeservicio() {
        return this.listOfClienteviajeservicio;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(latitud);
        sb.append("|");
        sb.append(longitud);
        sb.append("|");
        sb.append(pasajeros);
        sb.append("|");
        sb.append(costo);
        return sb.toString(); 
    } 

}