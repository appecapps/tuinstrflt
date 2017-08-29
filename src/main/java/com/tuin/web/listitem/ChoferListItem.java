/*
 * Created on 28 ago 2017 ( Time 17:51:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Chofer;
import com.tuin.web.common.ListItem;

public class ChoferListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ChoferListItem(Chofer chofer) {
		super();

		this.value = ""
			 + chofer.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = chofer.toString();
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