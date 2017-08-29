/*
 * Created on 28 ago 2017 ( Time 17:51:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Marca;
import com.tuin.bean.jpa.MarcaEntity;
import java.util.List;
import com.tuin.business.service.MarcaService;
import com.tuin.business.service.mapping.MarcaServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.MarcaPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of MarcaService
 */
@Component
public class MarcaServiceImpl implements MarcaService {

	private MarcaPersistence marcaPersistence;

	@Resource
	private MarcaServiceMapper marcaServiceMapper;
	
	public MarcaServiceImpl() {
		marcaPersistence = PersistenceServiceProvider.getService(MarcaPersistence.class);
	}
		
	@Override
	public Marca findById(Long id) {
		MarcaEntity entity = marcaPersistence.load(id);
		return marcaServiceMapper.mapMarcaEntityToMarca(entity);
	}

	@Override
	public List<Marca> findAll() {
		List<MarcaEntity> entities = marcaPersistence.loadAll();
		List<Marca> beans = new ArrayList<Marca>();
		for(MarcaEntity entity : entities) {
			beans.add(marcaServiceMapper.mapMarcaEntityToMarca(entity));
		}
		return beans;
	}

	@Override
	public Marca save(Marca marca) {
		return update(marca) ;
	}

	@Override
	public Marca create(Marca marca) {
		if(marcaPersistence.load(marca.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		MarcaEntity marcaEntity = new MarcaEntity();
		marcaServiceMapper.mapMarcaToMarcaEntity(marca, marcaEntity);
		MarcaEntity marcaEntitySaved = marcaPersistence.save(marcaEntity);
		return marcaServiceMapper.mapMarcaEntityToMarca(marcaEntitySaved);
	}

	@Override
	public Marca update(Marca marca) {
		MarcaEntity marcaEntity = marcaPersistence.load(marca.getId());
		marcaServiceMapper.mapMarcaToMarcaEntity(marca, marcaEntity);
		MarcaEntity marcaEntitySaved = marcaPersistence.save(marcaEntity);
		return marcaServiceMapper.mapMarcaEntityToMarca(marcaEntitySaved);
	}

	@Override
	public void delete(Long id) {
		marcaPersistence.delete(id);
	}

	public MarcaPersistence getMarcaPersistence() {
		return marcaPersistence;
	}

	public void setMarcaPersistence(MarcaPersistence marcaPersistence) {
		this.marcaPersistence = marcaPersistence;
	}

	public MarcaServiceMapper getMarcaServiceMapper() {
		return marcaServiceMapper;
	}

	public void setMarcaServiceMapper(MarcaServiceMapper marcaServiceMapper) {
		this.marcaServiceMapper = marcaServiceMapper;
	}

}