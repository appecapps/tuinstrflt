/*
 * Created on 28 ago 2017 ( Time 17:51:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Viaje;
import com.tuin.web.common.ListItem;

public class ViajeListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ViajeListItem(Viaje viaje) {
		super();

		this.value = ""
			 + viaje.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = viaje.toString();
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
