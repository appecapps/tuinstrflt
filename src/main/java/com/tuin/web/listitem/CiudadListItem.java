/*
 * Created on 28 ago 2017 ( Time 17:51:38 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Ciudad;
import com.tuin.web.common.ListItem;

public class CiudadListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public CiudadListItem(Ciudad ciudad) {
		super();

		this.value = ""
			 + ciudad.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = ciudad.toString();
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
