/*
 * Created on 28 ago 2017 ( Time 17:51:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Destinohorario;
import com.tuin.bean.jpa.DestinohorarioEntity;
import com.tuin.bean.jpa.CiudadEntity;
import com.tuin.bean.jpa.HorarioEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class DestinohorarioServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public DestinohorarioServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'DestinohorarioEntity' to 'Destinohorario'
	 * @param destinohorarioEntity
	 */
	public Destinohorario mapDestinohorarioEntityToDestinohorario(DestinohorarioEntity destinohorarioEntity) {
		if(destinohorarioEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Destinohorario destinohorario = map(destinohorarioEntity, Destinohorario.class);

		//--- Link mapping ( link to Ciudad )
		if(destinohorarioEntity.getCiudad() != null) {
			destinohorario.setDestinoid(destinohorarioEntity.getCiudad().getId());
		}
		//--- Link mapping ( link to Horario )
		if(destinohorarioEntity.getHorario() != null) {
			destinohorario.setHorarioid(destinohorarioEntity.getHorario().getId());
		}
		return destinohorario;
	}
	
	/**
	 * Mapping from 'Destinohorario' to 'DestinohorarioEntity'
	 * @param destinohorario
	 * @param destinohorarioEntity
	 */
	public void mapDestinohorarioToDestinohorarioEntity(Destinohorario destinohorario, DestinohorarioEntity destinohorarioEntity) {
		if(destinohorario == null) {
			return;
		}

		//--- Generic mapping 
		map(destinohorario, destinohorarioEntity);

		//--- Link mapping ( link : destinohorario )
		if( hasLinkToCiudad(destinohorario) ) {
			CiudadEntity ciudad1 = new CiudadEntity();
			ciudad1.setId( destinohorario.getDestinoid() );
			destinohorarioEntity.setCiudad( ciudad1 );
		} else {
			destinohorarioEntity.setCiudad( null );
		}

		//--- Link mapping ( link : destinohorario )
		if( hasLinkToHorario(destinohorario) ) {
			HorarioEntity horario2 = new HorarioEntity();
			horario2.setId( destinohorario.getHorarioid() );
			destinohorarioEntity.setHorario( horario2 );
		} else {
			destinohorarioEntity.setHorario( null );
		}

	}
	
	/**
	 * Verify that Ciudad id is valid.
	 * @param Ciudad Ciudad
	 * @return boolean
	 */
	private boolean hasLinkToCiudad(Destinohorario destinohorario) {
		if(destinohorario.getDestinoid() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Horario id is valid.
	 * @param Horario Horario
	 * @return boolean
	 */
	private boolean hasLinkToHorario(Destinohorario destinohorario) {
		if(destinohorario.getHorarioid() != null) {
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