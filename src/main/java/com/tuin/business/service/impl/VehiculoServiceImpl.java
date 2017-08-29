/*
 * Created on 28 ago 2017 ( Time 17:51:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Vehiculo;
import com.tuin.bean.jpa.VehiculoEntity;
import java.util.List;
import com.tuin.business.service.VehiculoService;
import com.tuin.business.service.mapping.VehiculoServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.VehiculoPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of VehiculoService
 */
@Component
public class VehiculoServiceImpl implements VehiculoService {

	private VehiculoPersistence vehiculoPersistence;

	@Resource
	private VehiculoServiceMapper vehiculoServiceMapper;
	
	public VehiculoServiceImpl() {
		vehiculoPersistence = PersistenceServiceProvider.getService(VehiculoPersistence.class);
	}
		
	@Override
	public Vehiculo findById(Long id) {
		VehiculoEntity entity = vehiculoPersistence.load(id);
		return vehiculoServiceMapper.mapVehiculoEntityToVehiculo(entity);
	}

	@Override
	public List<Vehiculo> findAll() {
		List<VehiculoEntity> entities = vehiculoPersistence.loadAll();
		List<Vehiculo> beans = new ArrayList<Vehiculo>();
		for(VehiculoEntity entity : entities) {
			beans.add(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(entity));
		}
		return beans;
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		return update(vehiculo) ;
	}

	@Override
	public Vehiculo create(Vehiculo vehiculo) {
		if(vehiculoPersistence.load(vehiculo.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoServiceMapper.mapVehiculoToVehiculoEntity(vehiculo, vehiculoEntity);
		VehiculoEntity vehiculoEntitySaved = vehiculoPersistence.save(vehiculoEntity);
		return vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntitySaved);
	}

	@Override
	public Vehiculo update(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = vehiculoPersistence.load(vehiculo.getId());
		vehiculoServiceMapper.mapVehiculoToVehiculoEntity(vehiculo, vehiculoEntity);
		VehiculoEntity vehiculoEntitySaved = vehiculoPersistence.save(vehiculoEntity);
		return vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntitySaved);
	}

	@Override
	public void delete(Long id) {
		vehiculoPersistence.delete(id);
	}

	public VehiculoPersistence getVehiculoPersistence() {
		return vehiculoPersistence;
	}

	public void setVehiculoPersistence(VehiculoPersistence vehiculoPersistence) {
		this.vehiculoPersistence = vehiculoPersistence;
	}

	public VehiculoServiceMapper getVehiculoServiceMapper() {
		return vehiculoServiceMapper;
	}

	public void setVehiculoServiceMapper(VehiculoServiceMapper vehiculoServiceMapper) {
		this.vehiculoServiceMapper = vehiculoServiceMapper;
	}

}
