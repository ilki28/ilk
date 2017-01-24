package de.hrw.wi.business;

import java.lang.reflect.Constructor;

public class Customer {

	private int id ;
	private String fname;
	private String lname;
	private String adress;
	private String postcode;
	private String country;
	private String city ;
	private int countrycode;
	private int phonenumber;
	private String dateofbirth;
	private String email;
	
	
	public Customer(String fname, String lname, String adress, String postcode, String country, String city,
			int countrycode, int phonenumber, String dateofbirth, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.adress = adress;
		this.postcode = postcode;
		this.country = country;
		this.city = city;
		this.countrycode = countrycode;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.email = email;
		
		}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getCountrycode() {
		return countrycode;
	}


	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}


	public int getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
