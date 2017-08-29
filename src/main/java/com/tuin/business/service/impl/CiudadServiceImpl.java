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

import com.tuin.bean.Ciudad;
import com.tuin.bean.jpa.CiudadEntity;
import java.math.BigDecimal;
import java.util.List;
import com.tuin.business.service.CiudadService;
import com.tuin.business.service.mapping.CiudadServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.CiudadPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of CiudadService
 */
@Component
public class CiudadServiceImpl implements CiudadService {

	private CiudadPersistence ciudadPersistence;

	@Resource
	private CiudadServiceMapper ciudadServiceMapper;
	
	public CiudadServiceImpl() {
		ciudadPersistence = PersistenceServiceProvider.getService(CiudadPersistence.class);
	}
		
	@Override
	public Ciudad findById(Long id) {
		CiudadEntity entity = ciudadPersistence.load(id);
		return ciudadServiceMapper.mapCiudadEntityToCiudad(entity);
	}

	@Override
	public List<Ciudad> findAll() {
		List<CiudadEntity> entities = ciudadPersistence.loadAll();
		List<Ciudad> beans = new ArrayList<Ciudad>();
		for(CiudadEntity entity : entities) {
			beans.add(ciudadServiceMapper.mapCiudadEntityToCiudad(entity));
		}
		return beans;
	}

	@Override
	public Ciudad save(Ciudad ciudad) {
		return update(ciudad) ;
	}

	@Override
	public Ciudad create(Ciudad ciudad) {
		if(ciudadPersistence.load(ciudad.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		CiudadEntity ciudadEntity = new CiudadEntity();
		ciudadServiceMapper.mapCiudadToCiudadEntity(ciudad, ciudadEntity);
		CiudadEntity ciudadEntitySaved = ciudadPersistence.save(ciudadEntity);
		return ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntitySaved);
	}

	@Override
	public Ciudad update(Ciudad ciudad) {
		CiudadEntity ciudadEntity = ciudadPersistence.load(ciudad.getId());
		ciudadServiceMapper.mapCiudadToCiudadEntity(ciudad, ciudadEntity);
		CiudadEntity ciudadEntitySaved = ciudadPersistence.save(ciudadEntity);
		return ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntitySaved);
	}

	@Override
	public void delete(Long id) {
		ciudadPersistence.delete(id);
	}

	public CiudadPersistence getCiudadPersistence() {
		return ciudadPersistence;
	}

	public void setCiudadPersistence(CiudadPersistence ciudadPersistence) {
		this.ciudadPersistence = ciudadPersistence;
	}

	public CiudadServiceMapper getCiudadServiceMapper() {
		return ciudadServiceMapper;
	}

	public void setCiudadServiceMapper(CiudadServiceMapper ciudadServiceMapper) {
		this.ciudadServiceMapper = ciudadServiceMapper;
	}

}