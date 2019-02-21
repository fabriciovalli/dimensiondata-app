package com.dimensiondata.fabriciovalli.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Server
{
	public Server(){}

	public Server(int id, String name ){
		this.id = id;
		this.name = name;
	}


	@Id
	private int id;

	private String name;

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

	@Override
	public String toString() {
		return "Server [ID: " + id + " - NAME: " + name + "]";
	}
}