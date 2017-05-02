package asgn2Tests;

import asgn2Customers.Customer;

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
	// TO DO
	Customer test1;
	
	@Before
	public void customerInstanciation() {
		test1 = new Customer();
	}
}
