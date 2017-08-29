/*
 * Created on 28 ago 2017 ( Time 17:51:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Horario;
import com.tuin.bean.jpa.HorarioEntity;
import java.util.Date;
import java.util.List;
import com.tuin.business.service.HorarioService;
import com.tuin.business.service.mapping.HorarioServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.HorarioPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of HorarioService
 */
@Component
public class HorarioServiceImpl implements HorarioService {

	private HorarioPersistence horarioPersistence;

	@Resource
	private HorarioServiceMapper horarioServiceMapper;
	
	public HorarioServiceImpl() {
		horarioPersistence = PersistenceServiceProvider.getService(HorarioPersistence.class);
	}
		
	@Override
	public Horario findById(Long id) {
		HorarioEntity entity = horarioPersistence.load(id);
		return horarioServiceMapper.mapHorarioEntityToHorario(entity);
	}

	@Override
	public List<Horario> findAll() {
		List<HorarioEntity> entities = horarioPersistence.loadAll();
		List<Horario> beans = new ArrayList<Horario>();
		for(HorarioEntity entity : entities) {
			beans.add(horarioServiceMapper.mapHorarioEntityToHorario(entity));
		}
		return beans;
	}

	@Override
	public Horario save(Horario horario) {
		return update(horario) ;
	}

	@Override
	public Horario create(Horario horario) {
		if(horarioPersistence.load(horario.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		HorarioEntity horarioEntity = new HorarioEntity();
		horarioServiceMapper.mapHorarioToHorarioEntity(horario, horarioEntity);
		HorarioEntity horarioEntitySaved = horarioPersistence.save(horarioEntity);
		return horarioServiceMapper.mapHorarioEntityToHorario(horarioEntitySaved);
	}

	@Override
	public Horario update(Horario horario) {
		HorarioEntity horarioEntity = horarioPersistence.load(horario.getId());
		horarioServiceMapper.mapHorarioToHorarioEntity(horario, horarioEntity);
		HorarioEntity horarioEntitySaved = horarioPersistence.save(horarioEntity);
		return horarioServiceMapper.mapHorarioEntityToHorario(horarioEntitySaved);
	}

	@Override
	public void delete(Long id) {
		horarioPersistence.delete(id);
	}

	public HorarioPersistence getHorarioPersistence() {
		return horarioPersistence;
	}

	public void setHorarioPersistence(HorarioPersistence horarioPersistence) {
		this.horarioPersistence = horarioPersistence;
	}

	public HorarioServiceMapper getHorarioServiceMapper() {
		return horarioServiceMapper;
	}

	public void setHorarioServiceMapper(HorarioServiceMapper horarioServiceMapper) {
		this.horarioServiceMapper = horarioServiceMapper;
	}

}