package asgn2Tests;

import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

import asgn2Exceptions.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Person A
 */
public class RestaurantCustomerTests {
	private static final double DELTA = 1e-15;
	private static final int FIRST_LINE = 0, EMPTY_LOG_LINE = 4, 
			NEGATIVE_LINE_NUM = -1;
	private static final String FIRST_LOG_FILE = "20170101.txt";
	private PizzaRestaurant pr, nonInstanciatedPR;
	private int expectedResult;
	private String expectedString;
	private double expectedCalc;
	
	@Before
	public void setUp() {
		pr = new PizzaRestaurant();
	}
	
	// Key: PR = Pizza Restaurant
	
	@Test
	public void checkInstanciatedPRIsNotNull() {
		assertNotNull(pr);
	}
	
	@Test
	public void checkNonInstanciatedPRIsNull() {
		assertNull(nonInstanciatedPR);
	}
	
	@Test
	public void readsFileProperly() throws CustomerException, 
	PizzaException, LogHandlerException {
		assertTrue(pr.processLog(FIRST_LOG_FILE));
	}
	
	@Test
	public void testNumOfOrdersIsZeroWithoutProcessingLog() {
		expectedResult = 0;
		assertEquals(expectedResult, pr.getNumCustomerOrders());
	}
	
	@Test
	public void checkCorrectNumOfOrdersAfterProcessingLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedResult = 3;
		assertEquals(expectedResult, pr.getNumCustomerOrders());
	}
	
	@Test
	public void readsCustomerNameProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedString = "Casey Jones";
		assertEquals(expectedString, pr.getCustomerByIndex(FIRST_LINE).getName());
	}
	
	@Test
	public void readsCustomerNumProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedString = "0123456789";
		assertEquals(expectedString, pr.getCustomerByIndex(FIRST_LINE).getMobileNumber());
	}
	
	@Test
	public void readsCustomerTypeProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedString = "Driver Delivery";
		assertEquals(expectedString, pr.getCustomerByIndex(FIRST_LINE).getCustomerType());
	}
	
	@Test
	public void readsCustomerXLocationProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedResult = 5;
		assertEquals(expectedResult, pr.getCustomerByIndex(FIRST_LINE).getLocationX());
	}
	
	@Test
	public void readsCustomerYLocationProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedResult = 5;
		assertEquals(expectedResult, pr.getCustomerByIndex(FIRST_LINE).getLocationY());
	}
	
	@Test
	public void readsCustomerDistanceLocationProperlyAfterProcessLog() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		expectedCalc = 10.0;
		assertEquals(expectedCalc, pr.getCustomerByIndex(FIRST_LINE).getDeliveryDistance(), 
				DELTA);
	}
	
	@Test
	public void LogFileProperlyResetsNumOfOrders() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		pr.resetDetails();
		expectedResult = 0;
		assertEquals(expectedResult, pr.getNumCustomerOrders());
	}
	
	@Test
	public void checkCustomerAtSameIndexIsSameCustomer() throws CustomerException, 
	PizzaException, LogHandlerException {
		// TO DO:
		pr.processLog(FIRST_LOG_FILE);
		assertSame(pr.getCustomerByIndex(FIRST_LINE), 
				pr.getCustomerByIndex(FIRST_LINE));
	}
	
	
	@Test
	public void getsCorrectTotalDeliveryDist() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		// First Line TD = 10.0, Second Line TD = 5.0, Third Line = 0
		expectedCalc = 15.0; // 10.0 + 5.0 + 0 = 15.0
		assertEquals(expectedCalc, pr.getTotalDeliveryDistance(), DELTA);
	}
	
	// ------------------ Exception Testing --------------------------
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenInvalidFile() throws LogHandlerException, 
	CustomerException, PizzaException {
		pr.processLog("Invalid.txt");
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenTextFileNotInLogFolder() throws LogHandlerException, 
	CustomerException, PizzaException {
		pr.processLog("story.txt"); // On my Desktop Directory
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenCustomerIndexTooHigh() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		pr.getCustomerByIndex(EMPTY_LOG_LINE);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenCustomerIndexNegative() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		pr.getCustomerByIndex(NEGATIVE_LINE_NUM);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenGetCustomerAfterReset() throws CustomerException, 
	PizzaException, LogHandlerException {
		pr.processLog(FIRST_LOG_FILE);
		pr.resetDetails();
		expectedResult = 0;
		pr.getCustomerByIndex(FIRST_LINE);
	} 
	
}
