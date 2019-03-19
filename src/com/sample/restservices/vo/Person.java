package com.sample.restservices.vo;

public class Person {

	int id;
	String prsName;
	String cityName;

	public Person() {
		super();
	}

	public Person(int i, String prsName, String cityName) {
		super();
		this.id = i;
		this.prsName = prsName;
		this.cityName = cityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrsName() {
		return prsName;
	}

	public void setPrsName(String prsName) {
		this.prsName = prsName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
