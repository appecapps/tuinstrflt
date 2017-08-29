/*
 * Created on 28 ago 2017 ( Time 17:51:40 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Destinohorario;
import com.tuin.web.common.ListItem;

public class DestinohorarioListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public DestinohorarioListItem(Destinohorario destinohorario) {
		super();

		this.value = ""
			 + destinohorario.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = destinohorario.toString();
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
