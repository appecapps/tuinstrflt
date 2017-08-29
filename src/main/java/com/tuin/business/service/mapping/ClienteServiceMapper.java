/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Cliente;
import com.tuin.bean.jpa.ClienteEntity;
import com.tuin.bean.jpa.EntidadEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ClienteServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ClienteServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ClienteEntity' to 'Cliente'
	 * @param clienteEntity
	 */
	public Cliente mapClienteEntityToCliente(ClienteEntity clienteEntity) {
		if(clienteEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Cliente cliente = map(clienteEntity, Cliente.class);

		//--- Link mapping ( link to Entidad )
		if(clienteEntity.getEntidad() != null) {
			cliente.setEntidadid(clienteEntity.getEntidad().getId());
		}
		return cliente;
	}
	
	/**
	 * Mapping from 'Cliente' to 'ClienteEntity'
	 * @param cliente
	 * @param clienteEntity
	 */
	public void mapClienteToClienteEntity(Cliente cliente, ClienteEntity clienteEntity) {
		if(cliente == null) {
			return;
		}

		//--- Generic mapping 
		map(cliente, clienteEntity);

		//--- Link mapping ( link : cliente )
		if( hasLinkToEntidad(cliente) ) {
			EntidadEntity entidad1 = new EntidadEntity();
			entidad1.setId( cliente.getEntidadid() );
			clienteEntity.setEntidad( entidad1 );
		} else {
			clienteEntity.setEntidad( null );
		}

	}
	
	/**
	 * Verify that Entidad id is valid.
	 * @param Entidad Entidad
	 * @return boolean
	 */
	private boolean hasLinkToEntidad(Cliente cliente) {
		if(cliente.getEntidadid() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}