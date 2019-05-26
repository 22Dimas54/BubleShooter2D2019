package ru.sibnsk.bubleshooter2d;

import java.io.Serializable;

public class Profile implements Serializable{
	
	public String name;
	public int rezult;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRezult() {
		return rezult;
	}
	public void setRezult(int rezult) {
		this.rezult = rezult;
	}

}
