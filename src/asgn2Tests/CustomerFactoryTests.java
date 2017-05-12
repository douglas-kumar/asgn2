package asgn2Tests;

import asgn2Customers.*;
import asgn2Exceptions.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	  private static final String PICKUP_CUSTOMER = "PUC", 
			  DRIVER_DELIVERY = "DVC", DRONE_DELIVERY = "DNC";
	private static CustomerFactory customerSet, nonInstanciatedCustomerSet;
	private DriverDeliveryCustomer john;
	private String expectedString;
	
	@Before
	public void instanciateCustomerFactory() throws CustomerException {
		customerSet = new CustomerFactory();
		john = new DriverDeliveryCustomer("John", "0443514214", 4, 3);
	}
	
	@Test
	public void nonInstanciatedCFIsNull() {
		assertNull(nonInstanciatedCustomerSet);
	}
	
	@Test
	public void instanciatedCFIsNotNull() {
		assertNotNull(customerSet);
	}
	
	// Unsure?
	@Test
	public void staticCFIsNotNull() throws CustomerException {
		assertNotNull(CustomerFactory.getCustomer(DRIVER_DELIVERY, "John", "0443514214", 4, 3));
	}
	
	// Unsure?
	@Test
	public void getsNameCorrectly() throws CustomerException {
		expectedString = "Mark";
		assertEquals("not same name", expectedString, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getName());
	}
	
	// ----------- Exception Testing -------------
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenInvalidCustomerCode() throws CustomerException {
		CustomerFactory.getCustomer("WER", "John", "0443514214", 4, 3);
	}
	
}
