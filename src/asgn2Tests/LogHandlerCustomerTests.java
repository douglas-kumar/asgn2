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
	private static final int FIRST_INDEX = 0, NON_EXISTENT_INDEX = 1, SECOND_INDEX = 1;
	private static final double DELTA = 1e-15;
	private static final int FIRST_LINE = FIRST_INDEX;
	private LogHandler log, nonInstanciatedLog;
	private ArrayList<Customer> logFile, logFile3, nonInstanciatedLogFile, dataSet;
	private String expectedString;
	private int expectedResult;
	private double expectedCalc;
	
	@Before
	public void setUp() throws CustomerException, LogHandlerException {
		log = new LogHandler();
		logFile = new ArrayList<Customer>();
		logFile = LogHandler.populateCustomerDataset("20170101.txt");
		logFile3 = LogHandler.populateCustomerDataset("20170103.txt");
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
	public void checkEmptyTextFileIsActuallyEmpty() throws CustomerException, LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		assertTrue(dataSet.isEmpty());
	}
	
	@Test
	public void checkNonEmptyTextFileIsActuallyNotEmpty() throws CustomerException, LogHandlerException {
		assertFalse(logFile.isEmpty());
	}
	
	@Test
	public void getCustomerNameInEmptyFileWorks() throws CustomerException, LogHandlerException {
		expectedString = "John Smith";
		// Empty Text File to Insert
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedString, dataSet.get(FIRST_INDEX).getName());
	}
	
	@Test
	public void getCustomerNumInEmptyFileWorks() throws CustomerException, LogHandlerException {
		expectedString = "0447539207";
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedString, dataSet.get(FIRST_INDEX).getMobileNumber());
	}
	
	@Test
	public void getCustomerXLocationInEmptyFileWorks() throws CustomerException, LogHandlerException {
		expectedResult = 5;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedResult, dataSet.get(FIRST_INDEX).getLocationX());
	}
	
	@Test
	public void getCustomerYLocationInEmptyFileWorks() throws CustomerException, LogHandlerException {
		expectedResult = -4;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedResult, dataSet.get(FIRST_INDEX).getLocationY());
	}
	
	@Test
	public void getCustomerTypeLocationInEmptyFileWorks() throws CustomerException, LogHandlerException {
		expectedString = "Driver Delivery";
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedString, dataSet.get(FIRST_INDEX).getCustomerType());
	}
	
	@Test
	public void getCustomerDriverDistanceLocationInEmptyFileWorks() throws CustomerException, 
	LogHandlerException {
		expectedResult = 9;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DVC,5,-4,PZV,4"));
		assertEquals(expectedResult, dataSet.get(FIRST_INDEX).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void getCustomerDroneDistanceLocationInEmptyFileWorks() throws CustomerException, 
	LogHandlerException {
		expectedCalc = 6.40;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,DNC,5,-4,PZV,4"));
		assertEquals(expectedCalc, dataSet.get(FIRST_INDEX).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void getCustomerPickUpDistanceLocationInEmptyFileWorks() throws CustomerException, 
	LogHandlerException {
		expectedResult = 0;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		assertEquals(expectedResult, dataSet.get(FIRST_INDEX).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void checkResetWorksAfterInsertingValuesAfterReset() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.clear();
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		expectedResult = 2;
		assertEquals(expectedResult, dataSet.size());
	}
	
	@Test
	public void removingIndexSuccessfullyMovesIndexProperly() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("19:25:30,19:50:40,Mark Kumar,0123456789,DVC,3,2,PZL,2"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,Jarvin Smith,0987654321,PUC,0,0,PZV,1"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,George Monty,0323232323,PUC,0,0,PZN,6"));
		dataSet.remove(SECOND_INDEX);
		expectedString = "Jarvin Smith";
		assertEquals(expectedString, dataSet.get(SECOND_INDEX).getName());
	}
	
	@Test
	public void checkSameIndexIsSameCustomer() throws CustomerException, 
	LogHandlerException {
		expectedResult = 0;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		assertSame(dataSet.get(FIRST_INDEX), dataSet.get(FIRST_INDEX));
	}
	
	@Test
	public void checkSameDiffIsNotSameCustomer() throws CustomerException, 
	LogHandlerException {
		expectedResult = 0;
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		assertNotSame(dataSet.get(FIRST_INDEX), dataSet.get(SECOND_INDEX));
	}
	
	@Test
	public void checkIndexesCollectCorrectInfoAtRandom() throws CustomerException, 
	LogHandlerException {
		expectedString = "Bella Thompson";
		assertEquals(expectedString, logFile3.get(47).getName());
		expectedString = "Oliver Harris";
		assertEquals(expectedString, logFile3.get(16).getName());
		expectedResult = 12;
		assertEquals(expectedResult, logFile3.get(FIRST_INDEX).getDeliveryDistance(), DELTA);
		expectedCalc = 5.0;
		assertEquals(expectedCalc, logFile.get(SECOND_INDEX).getDeliveryDistance(), DELTA);
	}
	
	// -------------------- /Exception Testing ------------------------------
	
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
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenNoCommasToSeperateData() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:0018:20:00John Smith0447539207PUCPZV4"));
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenExtraDataSegment() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4,Fake"));
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenLessDataSegment() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV"));
	}
	
	// NEED TO CHANGE TO LOGHANDLER EXCEPTION?
	@Test(expected=IndexOutOfBoundsException.class)
	public void exceptionThrownWhenGetCustomerOutOfIndex() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,0,PZV,4"));
		dataSet.get(NON_EXISTENT_INDEX).getName();
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenFileDiffFormat() throws CustomerException, 
	LogHandlerException {
		LogHandler.populateCustomerDataset("diffFormatLog.pdf");
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenXCoordsNotIntStyle() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,Zero,0,PZV,4"));
		dataSet.get(FIRST_INDEX);
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenYCoordsNotIntStyle() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,0,Zero,PZV,4"));
		dataSet.get(FIRST_INDEX);
	}
	
	@Test(expected=LogHandlerException.class)
	public void exceptionThrownWhenXAndYCoordsNotIntStyle() throws CustomerException, 
	LogHandlerException {
		dataSet = LogHandler.populateCustomerDataset("empty.txt");
		dataSet.add(LogHandler.createCustomer("18:00:00,18:20:00,John Smith,0447539207,PUC,Zero,Zero,PZV,4"));
		dataSet.get(FIRST_INDEX);
	}
	
}
