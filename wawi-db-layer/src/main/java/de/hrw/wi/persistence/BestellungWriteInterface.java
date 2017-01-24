package de.hrw.wi.persistence;

import java.sql.SQLException;

/**
 * 
 * @author mu-142141
 *
 */
public interface BestellungWriteInterface {

/**
 * 
 * @param bestellnummer bestellnummer
 * @param betrag betrag
 * @param status status
 * @param bestelldatum bestelldatum
 * @param customerid customerid
 * @param productid productid
 * @param anzahl anzahl
 * @throws SQLException exception
 */
	void addBestellung(int bestellnummer, int betrag,
			String status, String bestelldatum, int customerid, int productid, int anzahl)
			throws SQLException;

/**
 * 
 * @param bestellnummer bestellnummer
 * @param kundengrund Der Grund für die Stornierung
 * @throws SQLException e
 */
	void stornoBestellung(int bestellnummer, String kundengrund) throws SQLException;


/**  
 * 
 * @param bestellnummer bestellnummer
 * @param newStatus neuer Status
 */
	void updateStatusByBestellnummer(int bestellnummer, String newStatus);


}

