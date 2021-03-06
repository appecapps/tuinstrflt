/*
 * Created on 28 ago 2017 ( Time 17:51:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Itemmenu;
import com.tuin.bean.jpa.ItemmenuEntity;
import com.tuin.bean.jpa.MenuEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ItemmenuServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ItemmenuServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ItemmenuEntity' to 'Itemmenu'
	 * @param itemmenuEntity
	 */
	public Itemmenu mapItemmenuEntityToItemmenu(ItemmenuEntity itemmenuEntity) {
		if(itemmenuEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Itemmenu itemmenu = map(itemmenuEntity, Itemmenu.class);

		//--- Link mapping ( link to Menu )
		if(itemmenuEntity.getMenu() != null) {
			itemmenu.setMenuid(itemmenuEntity.getMenu().getId());
		}
		return itemmenu;
	}
	
	/**
	 * Mapping from 'Itemmenu' to 'ItemmenuEntity'
	 * @param itemmenu
	 * @param itemmenuEntity
	 */
	public void mapItemmenuToItemmenuEntity(Itemmenu itemmenu, ItemmenuEntity itemmenuEntity) {
		if(itemmenu == null) {
			return;
		}

		//--- Generic mapping 
		map(itemmenu, itemmenuEntity);

		//--- Link mapping ( link : itemmenu )
		if( hasLinkToMenu(itemmenu) ) {
			MenuEntity menu1 = new MenuEntity();
			menu1.setId( itemmenu.getMenuid() );
			itemmenuEntity.setMenu( menu1 );
		} else {
			itemmenuEntity.setMenu( null );
		}

	}
	
	/**
	 * Verify that Menu id is valid.
	 * @param Menu Menu
	 * @return boolean
	 */
	private boolean hasLinkToMenu(Itemmenu itemmenu) {
		if(itemmenu.getMenuid() != null) {
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