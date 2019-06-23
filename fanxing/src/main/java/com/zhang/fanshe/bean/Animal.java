package com.android.fanshe.bean;

import com.android.fanshe.interfaces.IAnimal;

public  class Animal implements IAnimal {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}