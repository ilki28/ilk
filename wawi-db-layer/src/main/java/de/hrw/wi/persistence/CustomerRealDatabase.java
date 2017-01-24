package de.hrw.wi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author tc-132000
 *
 */

public class CustomerRealDatabase implements CustomerReadInterface, CustomerWriteInterface {
	
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
				if (c != null) {
					c.close();
				}
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
				if (c != null) {
					c.close();
				}
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
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private String getString(String sql) {
		try {
			ResultSet result = executeQuery(sql);
			if (result.next()) {
				return result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * AlleKundenIds
	 * @return Ein Integer Set
	 */
	public HashSet<Integer> getAllCustomerId() {
		return new HashSet<Integer>(getResultAsInt("SELECT id FROM CUSTOMER"));
	}

	/**
	 * AlleKundenVornamen
	 * @return Ein String Set
	 */
	
	public HashSet<String> getAllCustomerFname() {
		return new HashSet<String>(getResultAsString("SELECT fname FROM CUSTOMER"));
	}
	
	/**
	 * AlleKundenNachnamen
	 * @return Ein String Set
	 */

	public HashSet<String> getAllCustomerLname() {
		return new HashSet<String>(getResultAsString("SELECT lname FROM CUSTOMER"));
	}
	
	/**
	 * AlleKundenAdressen
	 * @return Ein String Set
	 */

	public HashSet<String> getAllCustomerAdress() {
		return new HashSet<String>(getResultAsString("SELECT adress FROM CUSTOMER"));
	}
	
	/**
	 * AlleKundenTelefonnummern
	 * @return Ein String Set
	 */

	public HashSet<String> getAllCustomerPhonenumber() {
		return new HashSet<String>(getResultAsString("SELECT phonenumber FROM CUSTOMER"));
	}
	
	/**
	 * AlleKundenAdressen
	 * @return Ein String Set
	 */

	public HashSet<String> getAllCustomerDateOfBirth() {
		return new HashSet<String>(getResultAsString("SELECT dateofbirth FROM CUSTOMER"));
	}
	
	/**
	 * AlleKundenMails
	 * @return Ein String Set
	 */

	public HashSet<String> getAllCustomerMail() {
		return new HashSet<String>(getResultAsString("SELECT email FROM CUSTOMER"));
	}
	

	/**
	 * Löscht alle privaten Kundendaten
	 * @param id des Kunden
	 * @throws PersistenceException 
	 */
	
	public void deleteCustomerById(int id) throws PersistenceException {
		
		String fname = "";
		String lname = "";
		String adress = "";
		String phonenumber = "";
		String dateofbirth = "";
		String email = "";
		 
		
		try {
			executeUpdate("UPDATE CUSTOMER SET id=" + id + ",  fname=\'" + fname + "\',    "
					+ "lname=\'" + lname + "\' , adress=\'" + adress + "   \', phonenumber=\'" 
					+ phonenumber + "    \', dateofbirth=\'" + dateofbirth + " \', email=\'" 
					+ email + " \' WHERE id = " + id);

		} catch (SQLException e) {
			throw new PersistenceException("Customer   could   not be deleted.");
		} 

	}
	
	/**
	 * Fügt einen Kunden hinzu
	 * @param id des Kunden
	 * @param fname Vorname des Kunden
	 * @param lname Nachname des Kunden
	 * @param adress Adresse des Kunden
	 * @param phonenumber Telefonnummer des Kunden
	 * @param dateofbirth Geburtstag des Kunden
	 * @param email Mailadresse des Kunden
	 * @throws PersistenceException 
	 */
	 
	public void addCustomer(int id, String fname, String lname, String adress, 
			String phonenumber, String dateofbirth, String email) throws PersistenceException {
		try {
			String s = "INSERT INTO CUSTOMER VALUES(" + id + ", \'" 
		    + fname + "\',       \'" + lname + "\',   \'" 
		    + adress + "\',  \'" + phonenumber + "\',\'" + dateofbirth + "\', \'" + email + "\')";
			int res = executeUpdate(s);
			
			if (res == 0) {
				throw new PersistenceException("Customer       could not be added.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be added.", e);
		}

	}
	
	


	/**
	 * Update Id 
	 * @param oldId oldId
	 * @param newId newId
	 * @throws PersistenceException 
	 */
	public void updateIdById(int oldId, int newId) {
		try {
			executeUpdate("UPDATE CUSTOMER SET  id= \'" + newId 
					+ "\'WHERE  id=\'" + oldId + "   				   \'");

			int res = executeUpdate("UPDATE CUSTOMER SET id= \'"
			+ newId + " \' WHERE id=\'" + oldId + "  "
					+ "   			 \'");

			if (res == 0) {
				throw new PersistenceException("Customer     could not  be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer  could  not be    updated. 	");
		}
	}
	/**
	 * Update Vorname
	 * @param id id
	 * @param newName name
	 * @throws PersistenceException 
	 */
	public void updateFirstNameById(int id, String newName) {
		try {
			executeUpdate("UPDATE CUSTOMER SET fname=    \'"
					+ newName + " \'WHERE id=  \'" + id + " 			 \'");

			int res = executeUpdate("UPDATE CUSTOMER  SET fname=   \'" + newName
					+ "    \'WHERE id=     \'" + id + "   \'");

			if (res == 0) {
				throw new PersistenceException("Customer could  not   be  updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer  could  not be	 updated.");
		}

	}
	/**
	 * Update Nachname
	 * @param id des Kunden 
	 * @param newName name
	 * @throws PersistenceException 
	 */
	public void updateLastNameById(int id, String newName) {

		try {
			executeUpdate("UPDATE CUSTOMER SET lname=  \'" + newName 
					+ "  	 \'WHERE id=      \'" + id + "             \'");

			int res = executeUpdate("UPDATE CUSTOMER SET lname=   \'"
			+ newName + "     \'WHERE id=  \'" + id + " 		     \'");

			if (res == 0) {
				throw new PersistenceException("Customer  could  not	 be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer  could  not be  updated.");
		}

	}

	/**
	 * Update Adresse
	 * @param id des Kunden
	 * @param newAdress adresse
	 * @throws PersistenceException 
	 */
	public void updateAdressById(int id, String newAdress) {

		try {
			executeUpdate("UPDATE CUSTOMER  SET adress=  \'"
		        + newAdress + "   \'WHERE id=\'" + id + "      			 \'");

			int res = executeUpdate("UPDATE CUSTOMER SET adress=  \'" 
			+ newAdress + "      \'WHERE id=         \'" + id + "        \'");

			if (res == 0) { 
				throw new PersistenceException("Customer  could  not be    updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer  could  not be updated  .");
		}

	}
	
	/**
	 * Update Postleitzahl
	 * @param id des Kunden
	 * @param newPostcode post
	 * @throws PersistenceException 
	 */

	public void updatePostcodeById(int id, String newPostcode) {

		try {
			executeUpdate("UPDATE CUSTOMER SET postcode=     \'"
		        + newPostcode + "     \'WHERE id=         \'" + id + "            \'");

			int res = executeUpdate("UPDATE CUSTOMER SET postcode=  \'"
			    + newPostcode + " \'WHERE id=     \'" + id + "     					  \'");

			if (res == 0) {
				throw new PersistenceException("Customer  could not  be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could   not be updated.");
		}

	}
	/**
	 * Update Land
	 * @param id des Kunden
	 * @param newCountry country
	 * @throws PersistenceException 
	 */

	public void updateCountryById(int id, String newCountry) {

		try {
			executeUpdate("UPDATE CUSTOMER SET country= \'" 
		        + newCountry + "  \'WHERE id=     \'" + id + "       \'");

			int res = executeUpdate("UPDATE CUSTOMER SET country=  \'"
			    + newCountry + "   \'WHERE id=      \'" + id + "      	    \'");

			if (res == 0) {
				throw new PersistenceException(" 	Customer  could  not be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could  not  be updated.");
		}

	}

	/**
	 *Update stadt
	 * @param id des Kunden
	 * @param newCity city
	 * @throws PersistenceException 
	 */
	public void updateCityById(int id, String newCity) {

		try {
			executeUpdate("UPDATE CUSTOMER SET city=         \'" 
		        + newCity + "  \'WHERE id=      \'" + id + "          \'");

			int res = executeUpdate("UPDATE CUSTOMER SET city= \'"
			    + newCity + "  \'WHERE id=   	\'" + id + "    			\'");

			if (res == 0) {
				throw new PersistenceException("Customer  could  not be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not be  updated.");
		}

	}

	/**
	 * Update Countrycode
	 * @param id des Kunden
	 * @param newCountrycode code
	 * @throws PersistenceException 
	 */
	public void updateCountrycodeById(int id, int newCountrycode) {

		try {
			executeUpdate("UPDATE CUSTOMER SET countrycode=        \'" 
		        + newCountrycode + "  \'WHERE id=  	 \'" + id + "  	   \'");

			int res = executeUpdate(
					"UPDATE CUSTOMER SET countrycode=\'"
			    + newCountrycode + "  \'WHERE id=    \'" + id + "    			"
			    		+ "\'");

			if (res == 0) {
				throw new PersistenceException("Customer   could not be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not  be  updated.");
		}

	}
	/**
	 * Update Telefonnummer
	 * @param id des Kunden
	 * @param newPhone phone
	 * @throws PersistenceException 
	 */

	public void updatePhonenumberById(int id, String newPhone) {

		try {
			executeUpdate("UPDATE CUSTOMER SET phonenumber=              \'"
		        + newPhone + "   \'WHERE id=    \'" + id + "    	 \'");

			int res = executeUpdate("UPDATE CUSTOMER SET phonenumber=     \'" 
			    + newPhone + "    \'WHERE id=    \'" + id + "      \'");

			if (res == 0) {
				throw new PersistenceException("Customer  	could   not be updated.");
			} 
		} catch (SQLException e) {
			throw new PersistenceException("Customer could  not   be updated.");
		}

	}

	/**
	 * Update Geburtsdatum
	 * @param id des Kunden
	 * @param newDate date
	 * @throws PersistenceException 
	 */
	public void updateDateOfBirthById(int id, String newDate) {

		try {
			executeUpdate("UPDATE CUSTOMER SET dateofbirth=            \'" 
		        + newDate + "  \'WHERE id=   \'" + id + "     					\'");

			int res = executeUpdate("UPDATE CUSTOMER SET dateofbirth=   \'" 
				+ newDate + "   \'WHERE id=   \'" + id + "     \'");

			if (res == 0) {
				throw new PersistenceException("Customer  could   not be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could  not be updated.");
		}

	}

	/**
	 * Update Email
	 * @param id des Kunden
	 * @param newMail mail
	 * @throws PersistenceException 
	 */
	public void updateEmailById(int id, String newMail) {

		try {
			executeUpdate("UPDATE CUSTOMER SET email=                      \'" 
		        + newMail + "   \'WHERE id=     \'" + id + "         \'");

			int res = executeUpdate("UPDATE CUSTOMER SET email=\'" 
				+ newMail + "	\'WHERE id=		\'" + id + "		  \'");

			if (res == 0) {
				throw new PersistenceException("Customer  could not be updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Customer could not  be updated.");
		}

		

		
		
	}

	@Override
	public int getCustomerId(String email) {
		return getInt("SELECT id FROM CUSTOMER WHERE email=\'" + email + "  \'");
	}

	@Override
	public String getCustomerFname1(int id) {
		return getString("SELECT fname FROM CUSTOMER WHERE id=" + id);	
	}

	@Override
	public String getCustomeLname(int id) {
		return getString("SELECT lname FROM CUSTOMER WHERE id=" + id);	
		
	}

	@Override
	public String getCustomerAdress(int id) {
		return getString("SELECT adress FROM CUSTOMER WHERE id=" + id);	
	}

	@Override
	public String getCustomerPhonenumber(int id) {
		return getString("SELECT phonenumber FROM CUSTOMER WHERE id=" + id);	
		
	}

	@Override
	public String getCustomerDateOfBirth(int id) {
		return getString("SELECT dateofbirth FROM CUSTOMER WHERE id=" + id);	
	}

	@Override
	public String getCustomerMail(int id) {
		return getString("SELECT email FROM CUSTOMER WHERE id=" + id);
		
	}

}

