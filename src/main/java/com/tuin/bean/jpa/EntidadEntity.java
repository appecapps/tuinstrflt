/*
 * Created on 28 ago 2017 ( Time 17:50:58 )
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
 * Persistent class for entity stored in table "entidad"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="entidad", catalog="app" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EntidadEntity.countAll", query="SELECT COUNT(x) FROM EntidadEntity x" )
} )
public class EntidadEntity implements Serializable {

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
    @Column(name="identificacion", nullable=false, length=20)
    private String     identificacion ;

    @Column(name="nombre", nullable=false, length=200)
    private String     nombre       ;

    @Column(name="apellido", length=200)
    private String     apellido     ;

    @Column(name="correo", length=45)
    private String     correo       ;

    @Column(name="password", length=45)
    private String     password     ;

    @Column(name="activo", nullable=false)
    private Byte       activo       ;

    @Column(name="web")
    private Byte       web          ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="entidad", targetEntity=ClienteEntity.class)
    private List<ClienteEntity> listOfCliente;

    @OneToMany(mappedBy="entidad", targetEntity=EntidadrolEntity.class)
    private List<EntidadrolEntity> listOfEntidadrol;

    @OneToMany(mappedBy="entidad", targetEntity=ChoferEntity.class)
    private List<ChoferEntity> listOfChofer;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EntidadEntity() {
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
    //--- DATABASE MAPPING : identificacion ( VARCHAR ) 
    public void setIdentificacion( String identificacion ) {
        this.identificacion = identificacion;
    }
    public String getIdentificacion() {
        return this.identificacion;
    }

    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }

    //--- DATABASE MAPPING : apellido ( VARCHAR ) 
    public void setApellido( String apellido ) {
        this.apellido = apellido;
    }
    public String getApellido() {
        return this.apellido;
    }

    //--- DATABASE MAPPING : correo ( VARCHAR ) 
    public void setCorreo( String correo ) {
        this.correo = correo;
    }
    public String getCorreo() {
        return this.correo;
    }

    //--- DATABASE MAPPING : password ( VARCHAR ) 
    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    //--- DATABASE MAPPING : activo ( TINYINT ) 
    public void setActivo( Byte activo ) {
        this.activo = activo;
    }
    public Byte getActivo() {
        return this.activo;
    }

    //--- DATABASE MAPPING : web ( TINYINT ) 
    public void setWeb( Byte web ) {
        this.web = web;
    }
    public Byte getWeb() {
        return this.web;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfCliente( List<ClienteEntity> listOfCliente ) {
        this.listOfCliente = listOfCliente;
    }
    public List<ClienteEntity> getListOfCliente() {
        return this.listOfCliente;
    }

    public void setListOfEntidadrol( List<EntidadrolEntity> listOfEntidadrol ) {
        this.listOfEntidadrol = listOfEntidadrol;
    }
    public List<EntidadrolEntity> getListOfEntidadrol() {
        return this.listOfEntidadrol;
    }

    public void setListOfChofer( List<ChoferEntity> listOfChofer ) {
        this.listOfChofer = listOfChofer;
    }
    public List<ChoferEntity> getListOfChofer() {
        return this.listOfChofer;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(identificacion);
        sb.append("|");
        sb.append(nombre);
        sb.append("|");
        sb.append(apellido);
        sb.append("|");
        sb.append(correo);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(activo);
        sb.append("|");
        sb.append(web);
        return sb.toString(); 
    } 

}