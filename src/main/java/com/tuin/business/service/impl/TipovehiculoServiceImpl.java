/*
 * Created on 28 ago 2017 ( Time 17:51:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Tipovehiculo;
import com.tuin.bean.jpa.TipovehiculoEntity;
import java.util.List;
import com.tuin.business.service.TipovehiculoService;
import com.tuin.business.service.mapping.TipovehiculoServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.TipovehiculoPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of TipovehiculoService
 */
@Component
public class TipovehiculoServiceImpl implements TipovehiculoService {

	private TipovehiculoPersistence tipovehiculoPersistence;

	@Resource
	private TipovehiculoServiceMapper tipovehiculoServiceMapper;
	
	public TipovehiculoServiceImpl() {
		tipovehiculoPersistence = PersistenceServiceProvider.getService(TipovehiculoPersistence.class);
	}
		
	@Override
	public Tipovehiculo findById(Long id) {
		TipovehiculoEntity entity = tipovehiculoPersistence.load(id);
		return tipovehiculoServiceMapper.mapTipovehiculoEntityToTipovehiculo(entity);
	}

	@Override
	public List<Tipovehiculo> findAll() {
		List<TipovehiculoEntity> entities = tipovehiculoPersistence.loadAll();
		List<Tipovehiculo> beans = new ArrayList<Tipovehiculo>();
		for(TipovehiculoEntity entity : entities) {
			beans.add(tipovehiculoServiceMapper.mapTipovehiculoEntityToTipovehiculo(entity));
		}
		return beans;
	}

	@Override
	public Tipovehiculo save(Tipovehiculo tipovehiculo) {
		return update(tipovehiculo) ;
	}

	@Override
	public Tipovehiculo create(Tipovehiculo tipovehiculo) {
		if(tipovehiculoPersistence.load(tipovehiculo.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		TipovehiculoEntity tipovehiculoEntity = new TipovehiculoEntity();
		tipovehiculoServiceMapper.mapTipovehiculoToTipovehiculoEntity(tipovehiculo, tipovehiculoEntity);
		TipovehiculoEntity tipovehiculoEntitySaved = tipovehiculoPersistence.save(tipovehiculoEntity);
		return tipovehiculoServiceMapper.mapTipovehiculoEntityToTipovehiculo(tipovehiculoEntitySaved);
	}

	@Override
	public Tipovehiculo update(Tipovehiculo tipovehiculo) {
		TipovehiculoEntity tipovehiculoEntity = tipovehiculoPersistence.load(tipovehiculo.getId());
		tipovehiculoServiceMapper.mapTipovehiculoToTipovehiculoEntity(tipovehiculo, tipovehiculoEntity);
		TipovehiculoEntity tipovehiculoEntitySaved = tipovehiculoPersistence.save(tipovehiculoEntity);
		return tipovehiculoServiceMapper.mapTipovehiculoEntityToTipovehiculo(tipovehiculoEntitySaved);
	}

	@Override
	public void delete(Long id) {
		tipovehiculoPersistence.delete(id);
	}

	public TipovehiculoPersistence getTipovehiculoPersistence() {
		return tipovehiculoPersistence;
	}

	public void setTipovehiculoPersistence(TipovehiculoPersistence tipovehiculoPersistence) {
		this.tipovehiculoPersistence = tipovehiculoPersistence;
	}

	public TipovehiculoServiceMapper getTipovehiculoServiceMapper() {
		return tipovehiculoServiceMapper;
	}

	public void setTipovehiculoServiceMapper(TipovehiculoServiceMapper tipovehiculoServiceMapper) {
		this.tipovehiculoServiceMapper = tipovehiculoServiceMapper;
	}

}
