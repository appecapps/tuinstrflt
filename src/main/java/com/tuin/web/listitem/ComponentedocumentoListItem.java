/*
 * Created on 28 ago 2017 ( Time 17:51:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Componentedocumento;
import com.tuin.web.common.ListItem;

public class ComponentedocumentoListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ComponentedocumentoListItem(Componentedocumento componentedocumento) {
		super();

		this.value = ""
			 + componentedocumento.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = componentedocumento.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}