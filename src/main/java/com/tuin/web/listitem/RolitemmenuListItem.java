/*
 * Created on 28 ago 2017 ( Time 17:51:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Rolitemmenu;
import com.tuin.web.common.ListItem;

public class RolitemmenuListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public RolitemmenuListItem(Rolitemmenu rolitemmenu) {
		super();

		this.value = ""
			 + rolitemmenu.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = rolitemmenu.toString();
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
