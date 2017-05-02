package asgn2Tests;

import asgn2Customers.*;
import asgn2Exceptions.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	private Customer john, nonInstanciatedJohn, invalidJohn;
	
	@Before
	public void customerInstanciation() throws CustomerException {
		john = new Customer("John", "0443204514", 24, 15, "Pick Up");
	}
	
	@Test
	public void customerIsNull() {
		assertNull(nonInstanciatedJohn);
	}
	
	@Test
	public void customerIsNotNull() {
		assertNotNull(john);
	}
	
	@Test
	public void 
	
}
