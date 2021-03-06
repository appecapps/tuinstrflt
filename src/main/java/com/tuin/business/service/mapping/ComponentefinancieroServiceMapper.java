/*
 * Created on 28 ago 2017 ( Time 17:51:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Componentefinanciero;
import com.tuin.bean.jpa.ComponentefinancieroEntity;
import com.tuin.bean.jpa.TipodocumentoEntity;
import com.tuin.bean.jpa.TipocomponentefinancieroEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ComponentefinancieroServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ComponentefinancieroServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ComponentefinancieroEntity' to 'Componentefinanciero'
	 * @param componentefinancieroEntity
	 */
	public Componentefinanciero mapComponentefinancieroEntityToComponentefinanciero(ComponentefinancieroEntity componentefinancieroEntity) {
		if(componentefinancieroEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Componentefinanciero componentefinanciero = map(componentefinancieroEntity, Componentefinanciero.class);

		//--- Link mapping ( link to Tipodocumento )
		if(componentefinancieroEntity.getTipodocumento() != null) {
			componentefinanciero.setTipodocumentoid(componentefinancieroEntity.getTipodocumento().getId());
		}
		//--- Link mapping ( link to Tipocomponentefinanciero )
		if(componentefinancieroEntity.getTipocomponentefinanciero() != null) {
			componentefinanciero.setTipocomponentefinancieroid(componentefinancieroEntity.getTipocomponentefinanciero().getId());
		}
		return componentefinanciero;
	}
	
	/**
	 * Mapping from 'Componentefinanciero' to 'ComponentefinancieroEntity'
	 * @param componentefinanciero
	 * @param componentefinancieroEntity
	 */
	public void mapComponentefinancieroToComponentefinancieroEntity(Componentefinanciero componentefinanciero, ComponentefinancieroEntity componentefinancieroEntity) {
		if(componentefinanciero == null) {
			return;
		}

		//--- Generic mapping 
		map(componentefinanciero, componentefinancieroEntity);

		//--- Link mapping ( link : componentefinanciero )
		if( hasLinkToTipodocumento(componentefinanciero) ) {
			TipodocumentoEntity tipodocumento1 = new TipodocumentoEntity();
			tipodocumento1.setId( componentefinanciero.getTipodocumentoid() );
			componentefinancieroEntity.setTipodocumento( tipodocumento1 );
		} else {
			componentefinancieroEntity.setTipodocumento( null );
		}

		//--- Link mapping ( link : componentefinanciero )
		if( hasLinkToTipocomponentefinanciero(componentefinanciero) ) {
			TipocomponentefinancieroEntity tipocomponentefinanciero2 = new TipocomponentefinancieroEntity();
			tipocomponentefinanciero2.setId( componentefinanciero.getTipocomponentefinancieroid() );
			componentefinancieroEntity.setTipocomponentefinanciero( tipocomponentefinanciero2 );
		} else {
			componentefinancieroEntity.setTipocomponentefinanciero( null );
		}

	}
	
	/**
	 * Verify that Tipodocumento id is valid.
	 * @param Tipodocumento Tipodocumento
	 * @return boolean
	 */
	private boolean hasLinkToTipodocumento(Componentefinanciero componentefinanciero) {
		if(componentefinanciero.getTipodocumentoid() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Tipocomponentefinanciero id is valid.
	 * @param Tipocomponentefinanciero Tipocomponentefinanciero
	 * @return boolean
	 */
	private boolean hasLinkToTipocomponentefinanciero(Componentefinanciero componentefinanciero) {
		if(componentefinanciero.getTipocomponentefinancieroid() != null) {
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