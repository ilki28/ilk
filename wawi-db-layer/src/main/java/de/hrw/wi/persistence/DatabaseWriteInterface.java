package de.hrw.wi.persistence;

import java.sql.SQLException;

/**
 * @author andriesc
 *
 */
public interface DatabaseWriteInterface {
	
	/**
	 * @param number
	 * @throws SQLException
	 */
	public void addWarehouse(int number, int maxNumberOfBins, int maxSizeOfBins) throws SQLException;
	
	/**
	 * @param articleCode
	 * @param name
	 * @param size
	 * @throws SQLException
	 */
	public void addProduct(String articleCode, String name, int size) throws SQLException;
	
	/**
	 * @param articleCode
	 * @throws SQLException
	 */
	public void deleteProduct(String articleCode) throws SQLException;
	
	/**
	 * Speichert die Belegung eines Faches ab und überschreibt dabei die vorherige Belegung.
	 * 
	 * @param numberOfWarehouse
	 * @param bin
	 * @param articleCode
	 * @param amount
	 */
	public void setStock(int numberOfWarehouse, int bin, String articleCode, int amount) throws SQLException;
	
	
	public void deleteCustomerById(int id) throws SQLException;
	
	
	public void updateIdById(int oldId, int newId);

	public void updateFirstNameById(int id, String newName);

	public void updateLastNameById(int id, String newName) ;
	
	public void updateAdressById(int id, String newAdress) ;

	public void updatePostcodeById(int id, String newPostcode) ;
	
	public void updateCountryById(int id, String newCountry);
	
	public void updateCityById(int id, String newCity);

	public void updateCountrycodeById(int id, int newCountrycode);

	public void updatePhonenumberById(int id, int newPhone);
	
	public void updateDateOfBirthById(int id, String newDate);
	
	public void updateEmailById(int id, String newMail);
	
}
