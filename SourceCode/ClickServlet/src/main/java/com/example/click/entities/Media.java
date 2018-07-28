package com.example.click.entities;

import java.io.Serializable;

public class Media implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int status;
	
	public Media(int id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
