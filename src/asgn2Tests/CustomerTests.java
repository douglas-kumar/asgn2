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
	private static final double DELTA = 1e-15;
	private DriverDeliveryCustomer john, 
			nonInstanciatedJohn, invalidJohn;
	private DroneDeliveryCustomer jill, invalidJill;
	private String expectedString, notSameString;
	private int expectedResult;
	private double expectedDistance;
	
	@Before
	public void customerInstanciation() throws CustomerException {
		john = new DriverDeliveryCustomer("John", "0443514214", 4, 3);
		jill = new DroneDeliveryCustomer("Jill", "0431431414", 4, 3);
	}
	
	// ---------- DriverDeliveryCustomer Testing ----------
	
	@Test
	public void instanciatedCustomerIsNotNull() {
		assertNotNull(john);
	}
	
	@Test
	public void nonInstanciatedCustomerIsNull() {
		assertNull(nonInstanciatedJohn);
	}
	
	@Test
	public void getFirstNameIsWorking() {
		expectedString = "John";
		assertEquals(expectedString, john.getName());
	}
	
	// Check to make sure it is okay to enter lower-case characters?
	// name.substring(0).toUpperCase()?
	@Test
	public void getFirstNameIsCaseSensitive() {
		notSameString = "john";
		assertNotEquals("john", john.getName());
	}
	
	@Test
	public void getMobileNumberIsWorking() {
		expectedString = "0443514214";
		assertEquals(expectedString, john.getMobileNumber());
	}
	
	@Test
	public void getXLocationIsWorking() {
		expectedResult = 4;
		assertEquals(expectedResult, john.getLocationX());
	}
	
	@Test
	public void getYLocationIsWorking() {
		expectedResult = 3;
		assertEquals(expectedResult, john.getLocationY());
	}
	
	@Test
	public void getCustomerTypeIsWorking() {
		expectedString = "DVC";
		assertEquals(expectedString, john.getCustomerType());
	}
	
	@Test
	public void getDeliveryDistanceIsCorrect() {
		expectedDistance = 7;
		assertEquals(expectedDistance, john.getDeliveryDistance(), DELTA);
	}
	
	// ------ Exception Testing for DriverDeliveryCustomer class -----
	 	
	/*
	@Test(expected=CustomerException.class)
	public void invalidInstanciation() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "", 0, 0);
	}
	*/
	
	// ---------- DroneDeliveryCustomer Testing ----------
	
	@Test
	public void checkDroneCustomerIsNotNull() {
		assertNotNull(jill);
	}
	
	@Test
	public void checkNonInstanciatedCustomerIsNull() {
		assertNull(invalidJill);
	}
	
	@Test
	public void getCustomerNameIsCorrectForDrone() {
		expectedString = "Jill";
		assertEquals(expectedString, jill.getName());
	}
	
	@Test
	public void getMobileNumberIsCorrectForDrone() {
		expectedString = "0431431414";
		assertEquals(expectedString, jill.getMobileNumber());
	}
	
}
