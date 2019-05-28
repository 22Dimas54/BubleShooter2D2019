package ru.sibnsk.bubleshooter2d;

import java.io.Serializable;

public class Profile implements Serializable,Comparable<Profile>{
	
	//FIELDS
	public String name;
	public Integer rezult;
	
	//CONSTRUCTOR
	
	//FUNCTIONS	
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
	@Override
	public int compareTo(Profile o) {
		int result2 = o.rezult.compareTo(this.rezult);
		return result2;
	}

}
