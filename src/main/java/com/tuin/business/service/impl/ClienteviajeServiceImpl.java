/*
 * Created on 28 ago 2017 ( Time 17:51:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Clienteviaje;
import com.tuin.bean.jpa.ClienteviajeEntity;
import java.math.BigDecimal;
import java.util.List;
import com.tuin.business.service.ClienteviajeService;
import com.tuin.business.service.mapping.ClienteviajeServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.ClienteviajePersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of ClienteviajeService
 */
@Component
public class ClienteviajeServiceImpl implements ClienteviajeService {

	private ClienteviajePersistence clienteviajePersistence;

	@Resource
	private ClienteviajeServiceMapper clienteviajeServiceMapper;
	
	public ClienteviajeServiceImpl() {
		clienteviajePersistence = PersistenceServiceProvider.getService(ClienteviajePersistence.class);
	}
		
	@Override
	public Clienteviaje findById(Long id) {
		ClienteviajeEntity entity = clienteviajePersistence.load(id);
		return clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(entity);
	}

	@Override
	public List<Clienteviaje> findAll() {
		List<ClienteviajeEntity> entities = clienteviajePersistence.loadAll();
		List<Clienteviaje> beans = new ArrayList<Clienteviaje>();
		for(ClienteviajeEntity entity : entities) {
			beans.add(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(entity));
		}
		return beans;
	}

	@Override
	public Clienteviaje save(Clienteviaje clienteviaje) {
		return update(clienteviaje) ;
	}

	@Override
	public Clienteviaje create(Clienteviaje clienteviaje) {
		if(clienteviajePersistence.load(clienteviaje.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		ClienteviajeEntity clienteviajeEntity = new ClienteviajeEntity();
		clienteviajeServiceMapper.mapClienteviajeToClienteviajeEntity(clienteviaje, clienteviajeEntity);
		ClienteviajeEntity clienteviajeEntitySaved = clienteviajePersistence.save(clienteviajeEntity);
		return clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntitySaved);
	}

	@Override
	public Clienteviaje update(Clienteviaje clienteviaje) {
		ClienteviajeEntity clienteviajeEntity = clienteviajePersistence.load(clienteviaje.getId());
		clienteviajeServiceMapper.mapClienteviajeToClienteviajeEntity(clienteviaje, clienteviajeEntity);
		ClienteviajeEntity clienteviajeEntitySaved = clienteviajePersistence.save(clienteviajeEntity);
		return clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntitySaved);
	}

	@Override
	public void delete(Long id) {
		clienteviajePersistence.delete(id);
	}

	public ClienteviajePersistence getClienteviajePersistence() {
		return clienteviajePersistence;
	}

	public void setClienteviajePersistence(ClienteviajePersistence clienteviajePersistence) {
		this.clienteviajePersistence = clienteviajePersistence;
	}

	public ClienteviajeServiceMapper getClienteviajeServiceMapper() {
		return clienteviajeServiceMapper;
	}

	public void setClienteviajeServiceMapper(ClienteviajeServiceMapper clienteviajeServiceMapper) {
		this.clienteviajeServiceMapper = clienteviajeServiceMapper;
	}

}
