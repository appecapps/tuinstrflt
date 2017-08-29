/*
 * Created on 28 ago 2017 ( Time 17:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.listitem;

import com.tuin.bean.Tipodocumento;
import com.tuin.web.common.ListItem;

public class TipodocumentoListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public TipodocumentoListItem(Tipodocumento tipodocumento) {
		super();

		this.value = ""
			 + tipodocumento.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = tipodocumento.toString();
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
