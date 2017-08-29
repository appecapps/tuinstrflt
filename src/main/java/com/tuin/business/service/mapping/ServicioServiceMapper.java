/*
 * Created on 28 ago 2017 ( Time 17:51:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Servicio;
import com.tuin.bean.jpa.ServicioEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ServicioServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ServicioServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ServicioEntity' to 'Servicio'
	 * @param servicioEntity
	 */
	public Servicio mapServicioEntityToServicio(ServicioEntity servicioEntity) {
		if(servicioEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Servicio servicio = map(servicioEntity, Servicio.class);

		return servicio;
	}
	
	/**
	 * Mapping from 'Servicio' to 'ServicioEntity'
	 * @param servicio
	 * @param servicioEntity
	 */
	public void mapServicioToServicioEntity(Servicio servicio, ServicioEntity servicioEntity) {
		if(servicio == null) {
			return;
		}

		//--- Generic mapping 
		map(servicio, servicioEntity);

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