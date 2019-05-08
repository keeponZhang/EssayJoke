package com.zhang.fanshe.bean;

import com.zhang.fanshe.interfaces.IAnimal;

public  class Animal implements IAnimal {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}