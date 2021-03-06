/*
 * Created on 28 ago 2017 ( Time 17:51:03 )
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
 * Persistent class for entity stored in table "vehiculoservicio"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="vehiculoservicio", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="VehiculoservicioEntity.countAll", query="SELECT COUNT(x) FROM VehiculoservicioEntity x" )
} )
public class VehiculoservicioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
	// "vehiculoid" (column "vehiculoId") is not defined by itself because used as FK in a link 
	// "servicioid" (column "servicioId") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="vehiculoservicio", targetEntity=ClienteviajeservicioEntity.class)
    private List<ClienteviajeservicioEntity> listOfClienteviajeservicio;

    @ManyToOne
    @JoinColumn(name="servicioId", referencedColumnName="id")
    private ServicioEntity servicio    ;

    @ManyToOne
    @JoinColumn(name="vehiculoId", referencedColumnName="id")
    private VehiculoEntity vehiculo    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public VehiculoservicioEntity() {
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

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfClienteviajeservicio( List<ClienteviajeservicioEntity> listOfClienteviajeservicio ) {
        this.listOfClienteviajeservicio = listOfClienteviajeservicio;
    }
    public List<ClienteviajeservicioEntity> getListOfClienteviajeservicio() {
        return this.listOfClienteviajeservicio;
    }

    public void setServicio( ServicioEntity servicio ) {
        this.servicio = servicio;
    }
    public ServicioEntity getServicio() {
        return this.servicio;
    }

    public void setVehiculo( VehiculoEntity vehiculo ) {
        this.vehiculo = vehiculo;
    }
    public VehiculoEntity getVehiculo() {
        return this.vehiculo;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 

}
