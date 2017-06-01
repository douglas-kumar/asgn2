package asgn2Tests;

import asgn2Restaurant.LogHandler;

import asgn2Exceptions.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A - Calum
 */
public class LogHandlerCustomerTests {
	private static final double DELTA = 1e-15;
	private static final int FIRST_LINE = 0;
	private LogHandler log, nonInstanciatedLog;
	private ArrayList<Customer> logFile, nonInstanciatedLogFile;
	private String expectedString;
	private int expectedResult;
	private double expectedCalc;
	
	@Before
	public void setUp() throws CustomerException, LogHandlerException {
		log = new LogHandler();
		logFile = new ArrayList<Customer>();
		logFile = LogHandler.populateCustomerDataset("20170101.txt");
	}
	
	@Test
	public void checkNotNullLogHandlerObject() {
		assertNotNull(log);
	}
	
	@Test
	public void checkCustomerListIsNotNull() {
		assertNotNull(logFile);
	}
	
	@Test
	public void checkNonInstanciatedLogHandlerObjectIsNull() {
		assertNull(nonInstanciatedLog);
	}
	
	@Test
	public void checkNonInstanciatedCustomerListObjectIsNull() {
		assertNull(nonInstanciatedLogFile);
	}
	
	@Test
	public void readsFileSuccessfully() throws CustomerException, LogHandlerException {
		LogHandler.populateCustomerDataset("20170101.txt");
	}
	
	@Test
	public void readsCustomerNameCorrectlyFromFile() {
		expectedString = "Casey Jones";
		assertEquals(expectedString, logFile.get(FIRST_LINE).getName());
	}
	
	@Test
	public void readsCustomerNumCorrectlyFromFile() {
		expectedString = "0123456789";
		assertEquals(expectedString, logFile.get(FIRST_LINE).getMobileNumber());
	}
	
	@Test
	public void readsCustomerTypeCorrectlyFromFile() {
		expectedString = "Driver Delivery";
		assertEquals(expectedString, logFile.get(FIRST_LINE).getCustomerType());
	}
	
	@Test
	public void readsCustomerXLocationCorrectlyFromFile() {
		expectedResult = 5;
		assertEquals(expectedResult, logFile.get(FIRST_LINE).getLocationX());
	}
	
	@Test
	public void readsCustomerYLocationCorrectlyFromFile() {
		expectedCalc = 10.0;
		assertEquals(expectedCalc, logFile.get(FIRST_LINE).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void checkSizeOfFileIsCorrect() {
		expectedResult = 3;
		assertEquals(expectedResult, logFile.size());
	}
	
	@Test
	public void clearFunctionActuallyClearsAllLinesInLogFile() {
		logFile.clear();
		expectedResult = 0;
		assertEquals(expectedResult, logFile.size());
	}
	
	@Test
	public void createCustomerInEmptyFileWorks() throws CustomerException, LogHandlerException {
		// Empty Text File to Insert
		LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4");
		LogHandler.populateCustomerDataset("empty.txt");
	}
	
	// -------------------- Exception Testing ------------------------------
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenCreateCustomerWithEmptyData() throws CustomerException, LogHandlerException {
		LogHandler.createCustomer("");
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenPopulateEmptyData() throws CustomerException, LogHandlerException {
		LogHandler.populateCustomerDataset("");
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenFileIsNotInLogDirectory() throws CustomerException, LogHandlerException {
		// file on my Desktop
		LogHandler.populateCustomerDataset("story.txt");
	}
	
	
	
}
