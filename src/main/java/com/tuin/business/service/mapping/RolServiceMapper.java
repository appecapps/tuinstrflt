/*
 * Created on 28 ago 2017 ( Time 17:51:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Rol;
import com.tuin.bean.jpa.RolEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class RolServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public RolServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'RolEntity' to 'Rol'
	 * @param rolEntity
	 */
	public Rol mapRolEntityToRol(RolEntity rolEntity) {
		if(rolEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Rol rol = map(rolEntity, Rol.class);

		return rol;
	}
	
	/**
	 * Mapping from 'Rol' to 'RolEntity'
	 * @param rol
	 * @param rolEntity
	 */
	public void mapRolToRolEntity(Rol rol, RolEntity rolEntity) {
		if(rol == null) {
			return;
		}

		//--- Generic mapping 
		map(rol, rolEntity);

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