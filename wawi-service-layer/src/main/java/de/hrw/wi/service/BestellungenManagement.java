package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import de.hrw.wi.business.Order;
import de.hrw.wi.persistence.BestellungReadInterface;
import de.hrw.wi.persistence.BestellungWriteInterface;

/**
 * 
 * @author tc-132000
 *
 */

public class BestellungenManagement implements BestellungenManagementInterface {

	private BestellungReadInterface dbRead;
	private BestellungWriteInterface dbWrite;

	/**
	 * 
	 * @param dbRead
	 *            dbRead
	 * @param dbWrite
	 *            dbWrite
	 */
	public BestellungenManagement(BestellungReadInterface dbRead,
			BestellungWriteInterface dbWrite) {
		this.dbRead = dbRead;
		this.dbWrite = dbWrite;
	}

	@Override
	public Set<Integer> getAllOrders() {  

		return dbRead.getAllBestellnummer();
	}

	@Override
	public int getGesamtbetrag(int bestellnummer) {
		int betrag = dbRead.getBetrag(bestellnummer);
	    return betrag;
	}

	@Override
	public Set<Order> getGesamtBestelldaten(int bestellnummer) {
		
		Set<Order> bestelldaten = new HashSet<Order>();
		Order order = new Order(bestellnummer, dbRead.getBetrag(bestellnummer),
				dbRead.getStatus(bestellnummer),
				dbRead.getBestelldatum(bestellnummer),
				dbRead.getCustomerId(bestellnummer),
				dbRead.getProductId(bestellnummer),
				dbRead.getAnzahl(bestellnummer));
		bestelldaten.add(order);

		return bestelldaten;
	}

	@Override
	public Set<Integer> getAllKundenbestellungen(int customerid) {
		return dbRead.getBestellnummer(customerid);
	}

	@Override
	public Set<Integer> getAllBestellungenADay(String bestelldatum) {
		return dbRead.getBestellnummerForADay(bestelldatum);
	}

	@Override
	public String getBestelldatum(int bestellnummer) {
		return dbRead.getBestelldatum(bestellnummer);
	}

	@Override
	public void addBestellung(int bestellnummer, int betrag, String status, 
			String bestelldatum, int customerid,
			int productid, int anzahl) throws SQLException {
	
	    dbWrite.addBestellung(bestellnummer, betrag, status,
	        		bestelldatum, customerid, productid, anzahl);
    }
	

	@Override
	public void deleteBestellung(int bestellnummer, String kundengrund) {

		try {
			dbWrite.stornoBestellung(bestellnummer, kundengrund);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public Set<Integer> getAllKundenADay(String bestelldatum) {
		return dbRead.getAllKundenADay(bestelldatum);
	}

	@Override
	public Set<Integer> getAllProdukteADay(String bestelldatum) {
		return dbRead.getAllProdukteADay(bestelldatum);
	}

	@Override
	public Set<Integer> getAnzahlProdukt(String bestelldatum, int productid) {
		return dbRead.getAnzahlProdukt(bestelldatum, productid);
	}



}
