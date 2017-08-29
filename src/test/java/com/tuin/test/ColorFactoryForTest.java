package com.tuin.test;

import com.tuin.bean.Color;

public class ColorFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Color newColor() {

		Long id = mockValues.nextLong();

		Color color = new Color();
		color.setId(id);
		return color;
	}
	
}
