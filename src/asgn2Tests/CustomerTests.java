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
	private DriverDeliveryCustomer john, mark, 
			nonInstanciatedJohn, invalidJohn;
	private DroneDeliveryCustomer jill, mary, invalidJill;
	private String expectedString, notSameString;
	private int expectedResult;
	private double expectedDistance;
	
	@Before
	public void customerInstanciation() throws CustomerException {
		john = new DriverDeliveryCustomer("John", "0443514214", 4, 3);
		mark = new DriverDeliveryCustomer("Mark", "0400123456", -6, -3);
		jill = new DroneDeliveryCustomer("Jill", "0431431414", 5, 8);
		mary = new DroneDeliveryCustomer("Mary", "0453920960", -9, 0);
	}
	
	// ----------------------------------------------------
	// ---------- DriverDeliveryCustomer Testing ----------
	// ----------------------------------------------------
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
	// name.charAt(0).toUpperCase()?
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
	
	@Test
	public void getDeliveryDistanceIsCorrectWithNegativeNums() {
		expectedDistance = 9; // |0-(-6)| + |0-(-3)| = 9
		assertEquals(expectedDistance, mark.getDeliveryDistance(), DELTA);
	}
	
	// ------ Exception Testing for DriverDeliveryCustomer class -----	 	
	
	@Test(expected=CustomerException.class)
	public void invalidInstanciation() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "", 0, 0);
	}
	
	@Test(expected=CustomerException.class)
	public void invalidName() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "0443514314", 3, 5);
	}
	
	@Test(expected=CustomerException.class)
	public void invalidNumberTooShort() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("John", "04434314", 3, 5);
	}
	
	// should be able to add +61 instead of 0???
	@Test(expected=CustomerException.class)
	public void invalidNumberDoesNotStartWithZero() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "443514314", 3, 5);
	}
	
	@Test(expected=CustomerException.class)
	public void xLocationTooHigh() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("John", "0443514314", 40, 5);
	}
	
	@Test(expected=CustomerException.class)
	public void yLocationTooHigh() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("John", "0443514314", 3, 35);
	}
	
	@Test(expected=CustomerException.class)
	public void xLocationTooHighWithNegativeNums() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "0443514314", -40, 5);
	}
	
	@Test(expected=CustomerException.class)
	public void yLocationTooHighWithNegativeNums() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("", "0443514314", 3, -35);
	}
	
	@Test(expected=CustomerException.class)
	public void NameIsLongerThanTwentyCharacters() throws CustomerException {
		invalidJohn = new DriverDeliveryCustomer("JJJJoooooohhhhhnnnnnnnn", "0443514314", 3, 5);
	}
	
	// Possibly add more tests...
	
	// ---------------------------------------------------
	// ---------- DroneDeliveryCustomer Testing ----------
	// ---------------------------------------------------
	
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
	
	@Test
	public void getXLocationIsCorrectForDrone() {
		expectedResult = 5;
		assertEquals(expectedResult, jill.getLocationX());
	}
	
	@Test
	public void getYLocationIsCorrectForDrone() {
		expectedResult = 8;
		assertEquals(expectedResult, jill.getLocationY());
	}
	
	@Test
	public void getDeliveryDistanceIsCorrectForDrone() {
		expectedDistance = 9.433981132; // sqrt( ((0-5)^2) + ((0-8)^2) ) = 9.433981132
		assertEquals(expectedDistance, jill.getDeliveryDistance(), DELTA);
	}
	
	
}
