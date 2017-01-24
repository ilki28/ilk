/**
 * 
 */
package de.hrw.wi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author andriesc
 *
 */
public class RealDatabase implements DatabaseReadInterface,
		DatabaseWriteInterface {

	private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
	private final String user = "sa";
	private final String password = "";

	private ResultSet executeQuery(String sql) throws SQLException {
		Connection c = null;
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			ResultSet rs = c.createStatement().executeQuery(sql);
			c.commit();
			return rs;
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int executeUpdate(String sql) throws SQLException {
		Connection c = null;
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			int result = c.createStatement().executeUpdate(sql);
			c.commit();
			return result;
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private List<String> getResultAsString(String sql) {
		List<String> list = new ArrayList<String>();
		try {
			ResultSet result = executeQuery(sql);
			while (result.next())
				list.add(result.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<Integer> getResultAsInt(String sql) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			ResultSet result = executeQuery(sql);
			while (result.next())
				list.add(result.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private int getInt(String sql) {
		try {
			ResultSet result = executeQuery(sql);
			if (result.next())
				return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private String getString(String sql) {
		try {
			ResultSet result = executeQuery(sql);
			if (result.next())
				return result.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Set<Integer> getAllWarehouses() {
		return new HashSet<Integer>(
				getResultAsInt("SELECT number FROM WAREHOUSES"));
	}

	public int getMaxAmountOfBins(int numberOfWarehouse) {
		return getInt("SELECT maxBin FROM WAREHOUSES WHERE number ="
				+ numberOfWarehouse);
	}

	public int getMaxSizeOfBins(int numberOfWarehouse) {
		return getInt("SELECT maxSize FROM WAREHOUSES WHERE number ="
				+ numberOfWarehouse);
	}

	public String getArticleCodeForBin(int numberOfWarehouse, int numberOfBin) {
		return getString("SELECT articleCode FROM STOCK WHERE number="
				+ numberOfWarehouse + " AND bin=" + numberOfBin);
	}

	public int getAmountForBin(int numberOfWarehouse, int numberOfBin) {
		return getInt("SELECT amount FROM STOCK WHERE number="
				+ numberOfWarehouse + " AND bin=" + numberOfBin);
	}

	public Set<String> getAllProducts() {
		return new HashSet<String>(
				getResultAsString("SELECT articleCode FROM PRODUCTS"));
	}

	public String getNameOfProduct(String articleCode) {
		return getString("SELECT name FROM PRODUCTS WHERE articleCode=\'"
				+ articleCode + "\'");
	}

	public int getSizeOfProduct(String articleCode) {
		return getInt("SELECT size FROM PRODUCTS WHERE articleCode=\'"
				+ articleCode + "\'");
	}

	public void addProduct(String articleCode, String name, int size)
			throws PersistenceException {

		if (articleCode.length() != 13)
			throw new PersistenceException(
					"Article code does not have 13 digits.");

		try {
			String s = "INSERT INTO PRODUCTS VALUES(\'" + articleCode
					+ "\', \'" + name + "\', " + size + ")";
			int res = executeUpdate(s);
			if (res == 0)
				throw new PersistenceException("Product could not be added.");
		} catch (SQLException e) {
			throw new PersistenceException("Product could not be added.", e);
		}
	}

	public void deleteProduct(String articleCode) throws PersistenceException {
		try {
			executeUpdate("DELETE FROM STOCK WHERE articleCode=\'"
					+ articleCode + "\'");
			int res = executeUpdate("DELETE FROM PRODUCTS WHERE articleCode=\'"
					+ articleCode + "\'");
			if (res == 0)
				throw new PersistenceException("Product could not be deleted.");
		} catch (SQLException e) {
			throw new PersistenceException("Product could not be deleted.");
		}
	}

	public void setStock(int numberOfWarehouse, int bin, String articleCode,
			int amount) throws PersistenceException {
		HashSet<Integer> warehouses = new HashSet<Integer>(getAllWarehouses());

		// Nur abspeichern, wenn es das Lager auch gibt
		if (warehouses.contains(numberOfWarehouse)) {
			HashSet<String> products = new HashSet<String>(getAllProducts());

			// Nur abspeichern, wenn es das Produkt bereits in der Datenbank
			// gibt
			if (products.contains(articleCode)) {
				int maxSize = getMaxSizeOfBins(numberOfWarehouse);

				// Lagerplatz leer?
				if (getArticleCodeForBin(numberOfWarehouse, bin) == null) {
					// Nur abspeichern, wenn die gewünschte Menge auch paßt
					if (maxSize >= amount * getSizeOfProduct(articleCode)) {
						try {
							executeUpdate("INSERT INTO STOCK VALUES("
									+ numberOfWarehouse + ", " + bin + ", "
									+ "\'" + articleCode + "\', " + amount
									+ ")");
						} catch (SQLException e) {
							throw new PersistenceException(
									"Stock could not be set.", e);
						}
					} else
						throw new PersistenceException("Bin is too small.");
				} else {
					// Lagerplatz nicht leer
					// Es sind keine zwei verschiedenen Produkte pro Platz
					// möglich,
					// liegt bereits das richtige Produkt auf dem Platz?
					String exArtCode = getArticleCodeForBin(numberOfWarehouse,
							bin);
					if (exArtCode.equals(articleCode)) {
						if (maxSize >= amount * getSizeOfProduct(articleCode)) {
							// Genügend Platz, dann überschreiben
							try {
								executeUpdate("UPDATE STOCK SET("
										+ numberOfWarehouse + ", " + bin + ", "
										+ "\'" + articleCode + "\', " + amount
										+ ")");
							} catch (SQLException e) {
								throw new PersistenceException(
										"Stock could not be set.", e);
							}
						} else
							throw new PersistenceException("Bin is too small.");
					} else
						throw new PersistenceException(
								"Bin is already taken for different product.");
				}
			} else
				throw new PersistenceException("Product does not exist.");
		} else
			throw new PersistenceException("Warehouse does not exist.");

	}

	public void addWarehouse(int number, int maxNumberOfBins, int maxSizeOfBins)
			throws PersistenceException {
		if (number >= 0 && maxNumberOfBins >= 1 && maxSizeOfBins >= 1) {
			try {
				int res = executeUpdate("INSERT INTO WAREHOUSES VALUES("
						+ number + ", " + maxNumberOfBins + ", "
						+ maxSizeOfBins + ")");
				if (res == 0)
					throw new PersistenceException(
							"Warehouse could not be added.");
			} catch (SQLException e) {
				throw new PersistenceException("Warehouse could not be added.",
						e);
			}
		} else
			throw new PersistenceException("Warehouse could not be added.");
	}
	
	
	
	public Set<String> getAllCustomer() {
		return new HashSet<String>(
				getResultAsString("SELECT * FROM CUSTOMER"));
	}
	
	public int getPhoneNrOfCustomer(int id) {
		return getInt("SELECT phonenumber FROM CUSTOMER WHERE id=\'"
				+ id + "\'");
	}
	
	public String getMailOfCustomer(int id) {
		return getString("SELECT email FROM CUSTOMER WHERE id=\'"
				+ id + "\'");
	}
	
	public String getAdressOfCustomer(int id) {
		return getString("SELECT adress FROM CUSTOMER WHERE id=\'"
				+ id + "\'");
	}
	
	
	
	
	public void deleteCustomerById(int id) throws PersistenceException {
		try {
			executeUpdate("DELETE FROM CUSTOMER WHERE id=\'"
					+ id + "\'");
			int res = executeUpdate("DELETE FROM CUSTOMER WHERE id=\'"
					+ id + "\'");
			if (res == 0)
				throw new PersistenceException("Customer could not be deleted.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be deleted.");
		}
	}
	
	public void updateIdById(int oldId, int newId){
		try {
			executeUpdate("UPDATE CUSTOMER SET id=\'" + newId +"\'WHERE id=\'" + oldId + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET id=\'" + newId +"\'WHERE id=\'" + oldId + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
	}
	
	public void updateFirstNameById(int id, String newName){
		try {
			executeUpdate("UPDATE CUSTOMER SET fname=\'" + newName +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET fname=\'" + newName +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}

	public void updateLastNameById(int id, String newName) {
		
		try {
			executeUpdate("UPDATE CUSTOMER SET lname=\'" + newName +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET lname=\'" + newName +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}
	
	public void updateAdressById(int id, String newAdress) {
		
		try {
			executeUpdate("UPDATE CUSTOMER SET adress=\'" + newAdress +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET adress=\'" + newAdress +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}

	public void updatePostcodeById(int id, String newPostcode) {
		
		try {
			executeUpdate("UPDATE CUSTOMER SET postcode=\'" + newPostcode +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET postcode=\'" + newPostcode +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}
	
	public void updateCountryById(int id, String newCountry){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET country=\'" + newCountry +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET country=\'" + newCountry +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}
	
	public void updateCityById(int id, String newCity){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET city=\'" + newCity +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET city=\'" + newCity +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}

	public void updateCountrycodeById(int id, int newCountrycode){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET countrycode=\'" + newCountrycode +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET countrycode=\'" + newCountrycode +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}

	public void updatePhonenumberById(int id, int newPhone){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET phonenumber=\'" + newPhone +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET phonenumber=\'" + newPhone +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}
	
	public void updateDateOfBirthById(int id, String newDate){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET dateofbirth=\'" + newDate +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET dateofbirth=\'" + newDate +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}
	
	public void updateEmailById(int id, String newMail){
		
		try {
			executeUpdate("UPDATE CUSTOMER SET email=\'" + newMail +"\'WHERE id=\'" + id + "\'");
			
			int res = executeUpdate("UPDATE CUSTOMER SET email=\'" + newMail +"\'WHERE id=\'" + id + "\'");
			
			if (res == 0)
				throw new PersistenceException("Customer could not be updated.");
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be updated.");
		}
		
	}

}
