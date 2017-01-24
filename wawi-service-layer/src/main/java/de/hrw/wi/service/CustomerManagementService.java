package de.hrw.wi.service;

import java.sql.SQLException;

import de.hrw.wi.business.Customer;
import de.hrw.wi.persistence.CustomerReadInterface;
import de.hrw.wi.persistence.CustomerWriteInterface;

/** 
 * 
 * @author ic-132249
 *
 */
public class CustomerManagementService implements CustomerManagementServiceInterface {

	private CustomerReadInterface dbRead;
	private CustomerWriteInterface dbWrite;
	
/**  
 * 
 * @param dbRead DBRead
 * @param dbWrite DBWrite
 */
	public CustomerManagementService(CustomerReadInterface dbRead, CustomerWriteInterface dbWrite) {
		this.dbRead = dbRead;
		this.dbWrite = dbWrite;
	}

    @Override
    public Customer getAllCustomerDataById(int id) {
	    Customer customer = new Customer(dbRead.getCustomerFname1(id),
		 	  dbRead.getCustomeLname(id), dbRead.getCustomerAdress(id),
			  dbRead.getCustomerPhonenumber(id), dbRead.getCustomerDateOfBirth(id),
			  dbRead.getCustomerMail(id));
	
	    return customer;
    }



    @Override
    public String getCustomerPhoneById(int id) {
	    return dbRead.getCustomerPhonenumber(id);
    	
	}

    @Override
    public String getCustomerMailById(int id) {
	
    	return dbRead.getCustomerMail(id);
    }

    @Override
    public String getCustomerAdressById(int id) {
	
    	return dbRead.getCustomerAdress(id);
    }

    @Override
    public void deleteCustomerById(int id) throws SQLException {
    	dbWrite.deleteCustomerById(id);
	
    }
    
    @Override
    public void addCustomer(int id, String fname, String lname, String adress, 
    		String phonenumber, String dateofbirth, String email) throws SQLException {
    	dbWrite.addCustomer(id, fname, lname, adress, phonenumber, dateofbirth, email);
    }

    @Override
    public void updateCustomerLname(int id, String newName) {
	    dbWrite.updateLastNameById(id, newName);
	
    }

    @Override
    public void updateCustomerAdress(int id, String newAdress) {
	    dbWrite.updateAdressById(id, newAdress);
	
    }

    @Override
    public void updateCustomerPhone(int id, String newPhone) {
	    dbWrite.updatePhonenumberById(id, newPhone);
	
    }

    @Override
    public void updateCustomerEmail(int id, String newMail) {
        dbWrite.updateEmailById(id, newMail);
    
	
    }
	

	

}
