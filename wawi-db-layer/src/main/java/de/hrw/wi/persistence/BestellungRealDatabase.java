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
 * 
 * @author tc-132000
 *
 */
public class BestellungRealDatabase implements BestellungReadInterface, BestellungWriteInterface {

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

	@Override
	public void addBestellung(int bestellnummer, int betrag, String status, 
			String bestelldatum, int customerid,
			int productid, int anzahl) throws SQLException {
		
		String productIdStr = String.valueOf(productid); // Articlecode
		

		
		int number = getInt("SELECT number FROM STOCK WHERE articleCode = \'" 
				+ productIdStr +  " \'  ");
		
		int bin = getInt("SELECT bin FROM STOCK WHERE articleCode = \'" 
				+ productIdStr +  " \' ");
		
		int amount = getInt("SELECT amount FROM STOCK WHERE articleCode = \'" 
				+ productIdStr +  " \'   ");
		
		bin = bin - 1;

		try {
			executeUpdate("UPDATE STOCK  SET number=" + number + ", bin= " + bin 
					+ ", articleCode =\'" + productIdStr + "\', "
					+ "amount=" + amount
					+ " WHERE articleCode =   \'" + productIdStr + "			\'");
			
			
			int res = executeUpdate("INSERT INTO ORDERS VALUES(" + bestellnummer
					+ "          ,     " + betrag
					+ "     , \'" + status + "\'   ,    \'" + bestelldatum 
					+ "\'            ,   " + customerid
					+ "             ,   " + productid + ", " + anzahl + "   )   ");
			if (res == 0) {
				throw new PersistenceException(" Bestellung could not be added.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Bestellung could not be added.", e);
		}
	}

	@Override
	public void stornoBestellung(int bestellnummer, String kundengrund) throws SQLException {

		
		int productid = getInt("SELECT productid FROM ORDERS WHERE bestellnummer = " 
		+ bestellnummer);
		
		
		
		
		executeUpdate("UPDATE STOCK  SET bin = bin+1  WHERE articleCode =\'" + productid + "\'");
		

		try {
			int res = executeUpdate("UPDATE ORDERS  SET status=\'storniert wegen: " + kundengrund 
					+ "\' WHERE bestellnummer =" 
				    + bestellnummer);

		} catch (SQLException e) {
			throw new PersistenceException("Bestellung could not be deleted.");
		}

	}

	@Override
	public void updateStatusByBestellnummer(int bestellnummer, String newStatus) {
		try {
			int res = executeUpdate(
					"UPDATE ORDERS  SET status=   \'" + newStatus
					+ "                     \'WHERE bestellnummer=     \'"
							+ bestellnummer + "                        \'");

			if (res == 0) {
				throw new PersistenceException("                    Orders could" 
			+ "  not   be  updated.");
			}
		} catch (SQLException e) {
			throw new PersistenceException("    Orders  could  not be	 updated.");
		}

	}

	@Override
	public HashSet<Integer> getAllBestellungen() {
		return new HashSet<Integer>(getResultAsInt("  SELECT bestellnummer FROM ORDERS"));

	}

	@Override
	public HashSet<String> getAllBestelldatum() {
		return new HashSet<String>(getResultAsString("SELECT bestelldatum FROM ORDERS"));
	}

	@Override
	public HashSet<Integer> getAllGesamtpreis() {
		return new HashSet<Integer>(getResultAsInt("SELECT betrag FROM ORDERS"));
	}

	@Override
	public HashSet<Integer> getAllBestellnummer() {
		return new HashSet<Integer>(getResultAsInt("SELECT bestellnummer FROM ORDERS WHERE "
				+ "status='neu'"));
	}

	@Override
	public HashSet<String> getAllStatus() {
		return new HashSet<String>(getResultAsString("SELECT status FROM ORDERS"));
	}

	@Override
	public int getBetrag(int bestellnummer) {
		return getInt("SELECT betrag FROM ORDERS WHERE bestellnummer=\'"
	+ bestellnummer + "                 \'");
	}

	@Override
	public String getBestelldatum(int bestellnummer) {
		return getString("SELECT bestelldatum FROM ORDERS WHERE bestellnummer=\'" 
	+ bestellnummer + "          \'");

	}

	@Override
	public Set<Integer> getBestellnummer(int customerId) {
		return new HashSet<Integer>(
				getResultAsInt("SELECT bestellnummer FROM ORDERS WHERE customerid=" 
		+ customerId + "                  "));
	}

	@Override
	public Set<Integer> getBestellnummerForADay(String bestelldatum) {
		return new HashSet<Integer>(
				getResultAsInt("SELECT bestellnummer FROM ORDERS WHERE bestelldatum=\'" 
		+ bestelldatum + " \'"));
	}

	@Override
	public int getCustomerId(int bestellnummer) {
		return getInt("SELECT customerid FROM ORDERS WHERE bestellnummer=\'" 
	+ bestellnummer + "   \'");
	}

	@Override
	public int getProductId(int bestellnummer) {
		return getInt("SELECT productid FROM ORDERS WHERE bestellnummer=\'"
	+ bestellnummer + "         \'");
	}

	@Override
	public String getStatus(int bestellnummer) {
		return getString("SELECT status FROM ORDERS WHERE bestellnummer=\'" 
	+ bestellnummer + "                            \'");
	}

	@Override
	public Set<Integer> getAllKundenADay(String bestelldatum) {

		return new HashSet<Integer>(
				getResultAsInt("SELECT customerid FROM ORDERS WHERE bestelldatum=\'" 
		+ bestelldatum + "                              \'"));
	}

	@Override
	public Set<Integer> getAllProdukteADay(String bestelldatum) {		
		int productid = getInt("SELECT productid FROM ORDERS WHERE bestelldatum = " + bestelldatum);
		return new HashSet<Integer>(
				getResultAsInt("SELECT productid FROM ORDERS WHERE bestelldatum=\'" 
		+ bestelldatum + "     \'"));
	}

	@Override
	public Set<Integer> getAnzahlProdukt(String bestelldatum, int productid) {
		String productIdStr = String.valueOf(productid);
		
		return new HashSet<Integer>(
				getResultAsInt("SELECT size FROM PRODUCTS WHERE articleCode=\'" 
		+  productIdStr + "  \'"));
	}

	@Override
	public int getAnzahl(int productid) {
		return getInt("SELECT anzahl FROM ORDERS WHERE productid=\'"
				+ productid + "    \'");
	}



}

