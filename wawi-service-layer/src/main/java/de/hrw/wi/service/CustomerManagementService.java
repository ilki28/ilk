package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import de.hrw.wi.business.Customer;
import de.hrw.wi.persistence.DatabaseReadInterface;
import de.hrw.wi.persistence.DatabaseWriteInterface;

public class CustomerManagementService implements CustomerManagementServiceInterface{

	private DatabaseReadInterface dbRead;
	private DatabaseWriteInterface dbWrite;

	public CustomerManagementService(DatabaseReadInterface dbRead, DatabaseWriteInterface dbWrite) {
		this.dbRead = dbRead;
		this.dbWrite = dbWrite;
	}

	/**
	 * @return Gibt eine String Liste mit allen Kundendaten von der Datenbank zurück.
	 */
	
	@Override
	public Set<String> getAllCustomerData() {
		return dbRead.getAllCustomer();
	}
	
	/**
	 * @param ID Nummer
	 * @return Gibt Telefonnummer des jeweiligen Kunden mit der zugehörigen ID zurück.
	 */

	@Override
	public int getPhoneNrById(int id) {
	
		return dbRead.getPhoneNrOfCustomer(id);
	}
	
	/**
	 * @param ID Nummer
	 * @return Gibt Mailadresse des jeweiligen Kunden mit der zugehörigen ID zurück.
	 */

	@Override
	public String getMailById(int id) {
		return dbRead.getMailOfCustomer(id);
	}
	/**
	 * @param ID Nummer
	 * @return Gibt Adresse des jeweiligen Kunden mit der zugehörigen ID zurück.
	 */

	@Override
	public String getAddressById(int id) {
		return dbRead.getAdressOfCustomer(id);
	}

	
	public void deleteCustomerById(int id) throws SQLException{
		dbWrite.deleteCustomerById(id);
	}
	
	public void updateIdById(int id) {
		
		int newId = dbRead.getAllCustomer().size()+1;
		dbWrite.updateIdById(id, newId);

	}

	public void updateFirstNameById(int id) {
		
		String newName = "Horst";
		dbWrite.updateFirstNameById(id, newName);

	}

	public void updateLastNameById(int id) {
		
		String newName = "Müller";
		dbWrite.updateLastNameById(id, newName);

	}

	public void updateAdressById(int id) {
		
		String newAdress = "Lollipopstraße 25";
		dbWrite.updateAdressById(id, newAdress);

	}

	public void updatePostcodeById(int id) {
		
		String newPostcode = "74561";
		dbWrite.updatePostcodeById(id, newPostcode);

	}

	public void updateCountryById(int id) {
		
		String newCountry = "Beispielinien";
		dbWrite.updateCountryById(id, newCountry);

	}

	public void updateCityById(int id) {
		
		String newCity = "Misalia";
		dbWrite.updateCityById(id, newCity);

	}

	public void updateCountrycodeById(int id) {
		
		int newCountrycode = 0023;
		dbWrite.updateCountrycodeById(id, newCountrycode);

	}

	public void updatePhonenumberById(int id) {
		
		int newPhone = 542118548;
		dbWrite.updatePhonenumberById(id, newPhone);

	}

	public void updateDateOfBirthById(int id) {
		
		String newDate = "07-12-1907";
		dbWrite.updateDateOfBirthById(id, newDate);

	}

	public void updateEmailById(int id) {
		
		String newMail = "misalname@misalia.ms";
		dbWrite.updateEmailById(id, newMail);

	}

}
