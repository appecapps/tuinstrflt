/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Chofervehiculo;
import com.tuin.bean.jpa.ChofervehiculoEntity;
import java.util.List;
import com.tuin.business.service.ChofervehiculoService;
import com.tuin.business.service.mapping.ChofervehiculoServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.ChofervehiculoPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of ChofervehiculoService
 */
@Component
public class ChofervehiculoServiceImpl implements ChofervehiculoService {

	private ChofervehiculoPersistence chofervehiculoPersistence;

	@Resource
	private ChofervehiculoServiceMapper chofervehiculoServiceMapper;
	
	public ChofervehiculoServiceImpl() {
		chofervehiculoPersistence = PersistenceServiceProvider.getService(ChofervehiculoPersistence.class);
	}
		
	@Override
	public Chofervehiculo findById(Long id) {
		ChofervehiculoEntity entity = chofervehiculoPersistence.load(id);
		return chofervehiculoServiceMapper.mapChofervehiculoEntityToChofervehiculo(entity);
	}

	@Override
	public List<Chofervehiculo> findAll() {
		List<ChofervehiculoEntity> entities = chofervehiculoPersistence.loadAll();
		List<Chofervehiculo> beans = new ArrayList<Chofervehiculo>();
		for(ChofervehiculoEntity entity : entities) {
			beans.add(chofervehiculoServiceMapper.mapChofervehiculoEntityToChofervehiculo(entity));
		}
		return beans;
	}

	@Override
	public Chofervehiculo save(Chofervehiculo chofervehiculo) {
		return update(chofervehiculo) ;
	}

	@Override
	public Chofervehiculo create(Chofervehiculo chofervehiculo) {
		if(chofervehiculoPersistence.load(chofervehiculo.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		ChofervehiculoEntity chofervehiculoEntity = new ChofervehiculoEntity();
		chofervehiculoServiceMapper.mapChofervehiculoToChofervehiculoEntity(chofervehiculo, chofervehiculoEntity);
		ChofervehiculoEntity chofervehiculoEntitySaved = chofervehiculoPersistence.save(chofervehiculoEntity);
		return chofervehiculoServiceMapper.mapChofervehiculoEntityToChofervehiculo(chofervehiculoEntitySaved);
	}

	@Override
	public Chofervehiculo update(Chofervehiculo chofervehiculo) {
		ChofervehiculoEntity chofervehiculoEntity = chofervehiculoPersistence.load(chofervehiculo.getId());
		chofervehiculoServiceMapper.mapChofervehiculoToChofervehiculoEntity(chofervehiculo, chofervehiculoEntity);
		ChofervehiculoEntity chofervehiculoEntitySaved = chofervehiculoPersistence.save(chofervehiculoEntity);
		return chofervehiculoServiceMapper.mapChofervehiculoEntityToChofervehiculo(chofervehiculoEntitySaved);
	}

	@Override
	public void delete(Long id) {
		chofervehiculoPersistence.delete(id);
	}

	public ChofervehiculoPersistence getChofervehiculoPersistence() {
		return chofervehiculoPersistence;
	}

	public void setChofervehiculoPersistence(ChofervehiculoPersistence chofervehiculoPersistence) {
		this.chofervehiculoPersistence = chofervehiculoPersistence;
	}

	public ChofervehiculoServiceMapper getChofervehiculoServiceMapper() {
		return chofervehiculoServiceMapper;
	}

	public void setChofervehiculoServiceMapper(ChofervehiculoServiceMapper chofervehiculoServiceMapper) {
		this.chofervehiculoServiceMapper = chofervehiculoServiceMapper;
	}

}