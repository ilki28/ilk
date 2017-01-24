package de.hrw.wi.persistence;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ha-131985
 *
 */

public interface BestellungReadInterface {

	/**
	 * 
	 * @return alle Bestellungen
	 */
	HashSet<Integer> getAllBestellungen();

	/**
	 * 
	 * @return Datum
	 */
	HashSet<String> getAllBestelldatum();

	/**
	 * 
	 * @return gesamtpreis einer Bestellung
	 */
	HashSet<Integer> getAllGesamtpreis();

	/**
	 * 
	 * @return alle Bestellnummern
	 */
	HashSet<Integer> getAllBestellnummer();

	/**
	 * 
	 * @return Status
	 */
	HashSet<String> getAllStatus();

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Betrag einer Bestellung
	 */
	int getBetrag(int bestellnummer);

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Bestelldatum einer Bestellung
	 */
	String getBestelldatum(int bestellnummer);

	/**
	 * 
	 * @param customerId
	 *            customerId
	 * @return Bestellungen eines Kunden
	 */
	Set<Integer> getBestellnummer(int customerId);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return Bestellungen an einem Tag
	 */
	Set<Integer> getBestellnummerForADay(String bestelldatum);

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Kunden ID einer Bestellung
	 */
	int getCustomerId(int bestellnummer);

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Produkte einer Bestellung
	 */
	int getProductId(int bestellnummer);

	/**
	 * 
	 * @param bestellnummer
	 *            bestellnummer
	 * @return Status einer Bestellung
	 */
	String getStatus(int bestellnummer);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return alle Kunden, die an einem, Tag bestellt haben
	 */
	Set<Integer> getAllKundenADay(String bestelldatum);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @return produkte an einem Tag
	 */
	Set<Integer> getAllProdukteADay(String bestelldatum);

	/**
	 * 
	 * @param bestelldatum
	 *            bestelldatum
	 * @param productid
	 *            productid
	 * @return anzahl der Produkte
	 */
	Set<Integer> getAnzahlProdukt(String bestelldatum, int productid);
	
	
	/**
	 * 
	 * @param productid productid
	 * @return Anzahl der Produkte
	 */
	int getAnzahl(int productid);

}

