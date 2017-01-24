package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.Set;

import de.hrw.wi.business.Order;

/**
 * 
 * @author tk-142142
 *
 */

public interface BestellungenManagementInterface {

	/**
	 * Gibt alle Bestellungen zurück
	 * 
	 * @return Set mit allen Bestellungen
	 */
	Set<Integer> getAllOrders();

	/**
	 * Gibt den Gesamtbetrag einer Bestellung zürück
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Gesamtpreis einer Bestellung
	 */

	int getGesamtbetrag(int bestellnummer);

	/**
	 * Gibt die Gesamtdaten einer Bestellung zurück
	 * 
	 * @param bestellnummer
	 *            bestellnummer    
	 * @return gesamten Bestelldaten
	 */
	Set<Order> getGesamtBestelldaten(int bestellnummer);

	/**
	 * Gibt die gesamten Bestellungen eines Kunden zurück
	 * 
	 * @param customerid
	 *            Kunden ID
	 * @return Set vom Typ Kunden mit den gesamten Bestellungen eines Kunden
	 */
	Set<Integer> getAllKundenbestellungen(int customerid);

	/**
	 * gibt alle Bestellungen eines tages an
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return Bestellung von einem Tag
	 */

	Set<Integer> getAllBestellungenADay(String bestelldatum);

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Bestelldatum einer Bestellung
	 */
	String getBestelldatum(int bestellnummer);

	/**
	 * 
	 * @param customerid
	 *            customerid
	 * @param productid
	 *            productid
	 * @param betrag
	 *            betrag
	 * @param bestelldatum
	 *            bestelldatum
	 * @param bestellnummer
	 *            bestellnummer
	 * @param status
	 *            status
	 * @param anzahl anzahl
	 * @throws SQLException e
	 */
	void addBestellung(int bestellnummer, int betrag, String status,
			String bestelldatum, int customerid,
			int productid, int anzahl) throws SQLException;

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummerr
	 * @param kundengrund Grund für die Stornierung
	 */
	void deleteBestellung(int bestellnummer, String kundengrund);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return alle Kunden, die an einem Tag bestellt haben
	 */
	Set<Integer> getAllKundenADay(String bestelldatum);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return alle Produkte, die an einem Tag bestellt wurden
	 */
	Set<Integer> getAllProdukteADay(String bestelldatum);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @param productid
	 *            producti
	 * @return Anzahl der bestellten Produkte
	 */
	Set<Integer> getAnzahlProdukt(String bestelldatum, int productid);


	
	
}
