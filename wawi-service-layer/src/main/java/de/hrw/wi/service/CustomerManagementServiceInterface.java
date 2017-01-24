package de.hrw.wi.service;

import java.util.Set;

import de.hrw.wi.business.*;

public interface CustomerManagementServiceInterface {
	
	
	
	/**
	 * Gibt alle Kontaktdaten der vorhandenen Kunden zur�ck.
	 * @return Set mit allen Kontaktdaten
	 */
	
	public Set<String> getAllCustomerData();
	
	/**
	 * Gibt Telefonnummer der Kunden Zur�ck anhand der ID.
	 * @param ID der Kunden
	 * @return Integer mit Telefonnummer
	 */
	
	public int getPhoneNrById(int id);
	
	/**
	 * Gibt Emailadresse der Kunden zur�ck anhand der ID.
	 * @param ID der Kunden
	 * @return String mit EMailadresse
	 */

	public String getMailById(int id);
	
	/**
	 * Gibt Adressdaten der Kunden zur�ck anhand der ID.
	 * @param ID der Kunden
	 * @return Set mit allen Kontaktdaten
	 */
	
	public String getAddressById(int id);
	
	

}
