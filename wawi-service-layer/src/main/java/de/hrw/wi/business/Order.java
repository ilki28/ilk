package de.hrw.wi.business;

/**
 * 
 * @author ic-132249
 *
 */
public class Order {

	private int bestellnummer;
	private int betrag;
	private String status;
	private String bestelldatum;
	private int customerid;
	private int productid;
	private int anzahl;

	/**
	 * 
	 * @param bestellnummer
	 *            Die Bestellnummer
	 * @param betrag
	 *            Gesamtbetrag der Bestellung
	 * @param status
	 *            Ob Bestellung Aktiv oder Inaktiv ist
	 * @param bestelldatum
	 *            Datum der Bestellung
	 * @param customerid
	 *            kunden ID
	 * @param productid
	 *            produkt ID
	 * @param anzahl
	 *            anzahl
	 */
	public Order(int bestellnummer, int betrag, String status,
			String bestelldatum, int customerid, int productid,
			int anzahl) {
		super();
		this.bestellnummer = bestellnummer;
		this.betrag = betrag;
		this.status = status;
		this.bestelldatum = bestelldatum;
		this.customerid = customerid;
		this.productid = productid;
		this.anzahl = anzahl;
	}
/**
 * 
 * @return Anzahl der Produkte
 */
	public int getAnzahl() {
		return anzahl;
	}

	/**
	 * 
	 * @param anzahl anzahl
	 */
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	/**
	 * @return the bestellnummer
	 */
	public int getBestellnummer() {
		return bestellnummer;
	}

	/**
	 * @param bestellnummer
	 *            the bestellnummer to set
	 */
	public void setBestellnummer(int bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	/**
	 * @return the betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * @param betrag
	 *            the betrag to set
	 */
	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the bestelldatum
	 */
	public String getBestelldatum() {
		return bestelldatum;
	}

	/**
	 * @param bestelldatum
	 *            the bestelldatum to set
	 */
	public void setBestelldatum(String bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid
	 *            the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}

	/**
	 * @param productid
	 *            the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}

}
