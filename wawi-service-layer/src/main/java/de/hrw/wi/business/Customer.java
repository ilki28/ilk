package de.hrw.wi.business;

/**
 * 
 * Kundenklasse Klasse mit Kundeneigenschaften
 *
 */ 

public class Customer {

	private int id;
	private String fname;
	private String lname;
	private String adress;
	private String phonenumber;
	private String dateofbirth;
	private String email;

	/**
	 * 
	 * @param fname
	 *            Vorname des Kunden
	 * @param lname
	 *            Nachname des Kunden
	 * @param adress
	 *            Adresse des Kunden
	 * @param phonenumber
	 *            Telefon des Kunden
	 * @param dateofbirth
	 *            Geburtstag des Kunden
	 * @param email
	 *            Email des Kunden
	 */
	public Customer(String fname, String lname, String adress, 
			String phonenumber, String dateofbirth, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.adress = adress;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.email = email;

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber
	 *            the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the dateofbirth
	 */
	public String getDateofbirth() {
		return dateofbirth;
	}

	/**
	 * @param dateofbirth
	 *            the dateofbirth to set
	 */
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
