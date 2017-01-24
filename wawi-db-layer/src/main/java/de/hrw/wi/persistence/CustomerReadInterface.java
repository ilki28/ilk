package de.hrw.wi.persistence;

/**
 * 
 * @author ic-132249
 *
 */
public interface CustomerReadInterface {

	/**
	 * Gibt Kundenid zurück
	 * 
	 * @param email
	 *            email
	 * @return Kundenid
	 */
	int getCustomerId(String email);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Vorname
	 */
	String getCustomerFname1(int id);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Lastname
	 */
	String getCustomeLname(int id);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Kundenadresse
	 */
	String getCustomerAdress(int id);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Kundentelefonnummer
	 */

	String getCustomerPhonenumber(int id);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Kundengeburtsdatum
	 */
	String getCustomerDateOfBirth(int id);

	/**
	 * 
	 * @param id
	 *            id
	 * @return Mailadresse eines Kunden
	 */
	String getCustomerMail(int id);

}

