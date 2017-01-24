package de.hrw.wi.persistence;

import java.sql.SQLException;

/**
 * 
 * @author sh-131982
 *
 */
 
public interface CustomerWriteInterface {
	 
	/**
	 * 
	 * @param id id
	 * @throws SQLException sqlException
	 */
		
	void deleteCustomerById(int id) throws SQLException;
	
	/**
	 * Fügt einen Kunden hinzu
	 * @param id des Kunden
	 * @param fname Vorname des Kunden
	 * @param lname Nachname des Kunden
	 * @param adress Adresse des Kunden
	 * @param phonenumber Telefonnummer des Kunden
	 * @param dateofbirth Geburtstag des Kunden
	 * @param email Mailadresse des Kunden
	 * @throws SQLException 
	 */
	
	void addCustomer(int id, String fname, String lname, String adress, 
			String phonenumber, String dateofbirth, String email) throws SQLException;
		
	/**
	 * 
	 * @param oldId alte ID
	 * @param newId neue ID
	 */
		
	void updateIdById(int oldId, int newId);
		/**
		 * 
		 * @param id id
		 * @param newName neuer FName
		 */

    void updateFirstNameById(int id, String newName);
		/**
		 * 
		 * @param id id
		 * @param newName neuer LName
		 */

	void updateLastNameById(int id, String newName);
		/**
		 * 
		 * @param id id
		 * @param newAdress neue Adresse
		 */
		
	void updateAdressById(int id, String newAdress);
		/**
		 * 
		 * @param id id
		 * @param newPostcode neue Postleitzahl
		 */

	void updatePostcodeById(int id, String newPostcode);
		/**
		 * 
		 * @param id id
		 * @param newCountry neues Land
		 */
		
	void updateCountryById(int id, String newCountry);
		/**
		 * 
		 * @param id id
		 * @param newCity neue Stadt
		 */
		
	void updateCityById(int id, String newCity);
		
		/**
		 * 
		 * @param id id
		 * @param newPhone neue Nummer
		 */

    void updatePhonenumberById(int id, String newPhone);
		/**
		 * 
		 * @param id id
		 * @param newDate neues Datum
		 */
		
	void updateDateOfBirthById(int id, String newDate);
		/**
		 * 
		 * @param id id
		 * @param newMail neue Mail
		 */
		
    void updateEmailById(int id, String newMail);
		

}



