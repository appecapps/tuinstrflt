/*
 * Created on 28 ago 2017 ( Time 17:51:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Color;
import com.tuin.bean.jpa.ColorEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ColorServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ColorServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ColorEntity' to 'Color'
	 * @param colorEntity
	 */
	public Color mapColorEntityToColor(ColorEntity colorEntity) {
		if(colorEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Color color = map(colorEntity, Color.class);

		return color;
	}
	
	/**
	 * Mapping from 'Color' to 'ColorEntity'
	 * @param color
	 * @param colorEntity
	 */
	public void mapColorToColorEntity(Color color, ColorEntity colorEntity) {
		if(color == null) {
			return;
		}

		//--- Generic mapping 
		map(color, colorEntity);

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