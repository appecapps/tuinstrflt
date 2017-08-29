/*
 * Created on 28 ago 2017 ( Time 17:51:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Modelo;
import com.tuin.web.common.ListItem;

public class ModeloListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ModeloListItem(Modelo modelo) {
		super();

		this.value = ""
			 + modelo.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = modelo.toString();
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