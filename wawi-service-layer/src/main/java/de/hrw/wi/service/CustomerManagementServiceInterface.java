package de.hrw.wi.service;


import java.sql.SQLException;

import de.hrw.wi.business.Customer;


/**
 *  
 * 
 *
 */
public interface CustomerManagementServiceInterface {

	/**
	 * Gibt alle Kontaktdaten eines Kunden zurück
	 * 
	 * @param id
	 *            id
	 * @return Set mit allen Kontaktdaten
	 */

	Customer getAllCustomerDataById(int id);
	
 
	/**
	 * Gibt Telefonnummer eines Kunden anhand der ID zurück.
	 * 
	 * @param id
	 *            id
	 * @return Integer mit Telefonnummer
	 */

	String getCustomerPhoneById(int id);


	/**
	 * Gibt die Emailadresse eines Kunden anhand der ID zurück.
	 * @param id id 
	 * @return String mit EMailadresse
	 */

	String getCustomerMailById(int id);

	/**
	 * Gibt Adressdaten der Kunden zurück anhand der ID.
	 * @param id id 
	 * @return Set mit allen Kontaktdaten
	 */

	String getCustomerAdressById(int id);
	
	
	/**
	 * Löscht den Kunden anhand seiner ID
	 * @param id id
	 * @throws SQLException 
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
	 * Nachname des Kunden wird anhand Id geändert
	 * @param id id
	 * @param newName name
	 */
	void updateCustomerLname(int id, String newName);
	
	/**
	 * Adresse des Kunden wird anhand Id geändert
	 * @param id id
	 * @param newAdress adress
	 */
	void updateCustomerAdress(int id, String newAdress);
	
	/**
	 * Telefonnummer des Kunden wird anhand Id geändert
	 * @param id id
	 * @param newPhone phone
	 */
	void updateCustomerPhone(int id, String newPhone);
	
	/**
	 * Email Adresse des Kunden wird anhand Id geändert
	 * @param id id
	 * @param newMail mail
	 */
	void updateCustomerEmail(int id, String newMail);
	
	
	
	
	
	
	

}
