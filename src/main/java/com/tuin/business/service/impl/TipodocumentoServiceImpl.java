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

import com.tuin.bean.Tipodocumento;
import com.tuin.bean.jpa.TipodocumentoEntity;
import java.util.List;
import com.tuin.business.service.TipodocumentoService;
import com.tuin.business.service.mapping.TipodocumentoServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.TipodocumentoPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of TipodocumentoService
 */
@Component
public class TipodocumentoServiceImpl implements TipodocumentoService {

	private TipodocumentoPersistence tipodocumentoPersistence;

	@Resource
	private TipodocumentoServiceMapper tipodocumentoServiceMapper;
	
	public TipodocumentoServiceImpl() {
		tipodocumentoPersistence = PersistenceServiceProvider.getService(TipodocumentoPersistence.class);
	}
		
	@Override
	public Tipodocumento findById(Long id) {
		TipodocumentoEntity entity = tipodocumentoPersistence.load(id);
		return tipodocumentoServiceMapper.mapTipodocumentoEntityToTipodocumento(entity);
	}

	@Override
	public List<Tipodocumento> findAll() {
		List<TipodocumentoEntity> entities = tipodocumentoPersistence.loadAll();
		List<Tipodocumento> beans = new ArrayList<Tipodocumento>();
		for(TipodocumentoEntity entity : entities) {
			beans.add(tipodocumentoServiceMapper.mapTipodocumentoEntityToTipodocumento(entity));
		}
		return beans;
	}

	@Override
	public Tipodocumento save(Tipodocumento tipodocumento) {
		return update(tipodocumento) ;
	}

	@Override
	public Tipodocumento create(Tipodocumento tipodocumento) {
		if(tipodocumentoPersistence.load(tipodocumento.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		TipodocumentoEntity tipodocumentoEntity = new TipodocumentoEntity();
		tipodocumentoServiceMapper.mapTipodocumentoToTipodocumentoEntity(tipodocumento, tipodocumentoEntity);
		TipodocumentoEntity tipodocumentoEntitySaved = tipodocumentoPersistence.save(tipodocumentoEntity);
		return tipodocumentoServiceMapper.mapTipodocumentoEntityToTipodocumento(tipodocumentoEntitySaved);
	}

	@Override
	public Tipodocumento update(Tipodocumento tipodocumento) {
		TipodocumentoEntity tipodocumentoEntity = tipodocumentoPersistence.load(tipodocumento.getId());
		tipodocumentoServiceMapper.mapTipodocumentoToTipodocumentoEntity(tipodocumento, tipodocumentoEntity);
		TipodocumentoEntity tipodocumentoEntitySaved = tipodocumentoPersistence.save(tipodocumentoEntity);
		return tipodocumentoServiceMapper.mapTipodocumentoEntityToTipodocumento(tipodocumentoEntitySaved);
	}

	@Override
	public void delete(Long id) {
		tipodocumentoPersistence.delete(id);
	}

	public TipodocumentoPersistence getTipodocumentoPersistence() {
		return tipodocumentoPersistence;
	}

	public void setTipodocumentoPersistence(TipodocumentoPersistence tipodocumentoPersistence) {
		this.tipodocumentoPersistence = tipodocumentoPersistence;
	}

	public TipodocumentoServiceMapper getTipodocumentoServiceMapper() {
		return tipodocumentoServiceMapper;
	}

	public void setTipodocumentoServiceMapper(TipodocumentoServiceMapper tipodocumentoServiceMapper) {
		this.tipodocumentoServiceMapper = tipodocumentoServiceMapper;
	}

}
