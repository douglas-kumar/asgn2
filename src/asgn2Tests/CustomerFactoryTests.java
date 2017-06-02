package asgn2Tests;

import asgn2Customers.*;
import asgn2Exceptions.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A - Calum
 *
 */
public class CustomerFactoryTests {
	private static final double DELTA = 1e-15;
	private static final String PICKUP_CUSTOMER = "PUC", 
			DRIVER_DELIVERY = "DVC", DRONE_DELIVERY = "DNC";
	private static int expectedNum;
	private static CustomerFactory customerSet, nonInstanciatedCustomerSet;
	private DriverDeliveryCustomer john;
	private static String expectedString;

	@Before
	public void instanciateCustomerFactory() throws CustomerException {
		customerSet = new CustomerFactory();
		john = new DriverDeliveryCustomer("John", "0443514214", 4, 3);
	}

	// Key: CF = CustomerFactory

	@Test
	public void nonInstanciatedCFIsNull() {
		assertNull(nonInstanciatedCustomerSet);
	}

	@Test
	public void instanciatedCFIsNotNull() {
		assertNotNull(customerSet);
	}

	@Test
	public void staticCFIsNotNull() throws CustomerException {
		assertNotNull(CustomerFactory.getCustomer(DRIVER_DELIVERY, "John", "0443514214", 4, 3));
	}

	@Test
	public void getsCustomerTypeDriverDeliveryCorrectly() throws CustomerException {
		expectedString = "Driver Delivery";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getCustomerType());
	}

	@Test
	public void getsCustomerTypeDroneDeliveryCorrectly() throws CustomerException {
		expectedString = "Drone Delivery";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DNC", "Mark", "0447539207", 3, 4).getCustomerType());
	}

	@Test
	public void getsCustomerTypePickUpCorrectly() throws CustomerException {
		expectedString = "Pick Up";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, 0).getCustomerType());
	}

	@Test
	public void getsNameCorrectly() throws CustomerException {
		expectedString = "Mark";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getName());
	}

	@Test
	public void getsMobileNumCorrectly() throws CustomerException {
		expectedString = "0447539207";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getMobileNumber());
	}

	@Test
	public void getsXLocationCorrectly() throws CustomerException {
		expectedNum = 3;
		assertEquals(expectedNum, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationX());
	}

	@Test
	public void getsYLocationCorrectly() throws CustomerException {
		expectedNum = 4;
		assertEquals(expectedNum, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationY());
	}
	
	@Test
	public void TwoDiffCustomersWithSameNameAreProperlySame() throws CustomerException {
		assertSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getName(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getName());
	}
	
	@Test
	public void TwoDiffCustomersWithSameMobAreProperlySame() throws CustomerException {
		assertSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getMobileNumber(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getMobileNumber());
	}
	
	@Test
	public void TwoDiffCustomersWithSameXLocationAreProperlySame() throws CustomerException {
		assertSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationX(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationX());
	}
	
	@Test
	public void TwoDiffCustomersWithSameYLocationAreProperlySame() throws CustomerException {
		assertSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationY(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getLocationY());
	}
	
	@Test
	public void TwoDiffCustomersWithSameTypeAreProperlySame() throws CustomerException {
		assertSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getCustomerType(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getCustomerType());
	}
	
	@Test
	public void TwoDiffCustomerTypeAreNotSameObjDNCAndDVC() throws CustomerException {
		assertNotSame(CustomerFactory.getCustomer("DNC", "Mark", "0447539207", 3, 4).getCustomerType(), 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getCustomerType());
	}
	
	@Test
	public void TwoDiffCustomerTypeAreNotSameObjDNCAndPUC() throws CustomerException {
		assertNotSame(CustomerFactory.getCustomer("DNC", "Mark", "0447539207", 3, 4).getCustomerType(), 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, 0).getCustomerType());
	}
	
	@Test
	public void TwoDiffCustomerTypeAreNotSameObjDVCAndPUC() throws CustomerException {
		assertNotSame(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, 4).getCustomerType(), 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, 0).getCustomerType());
	}
	
	@Test
	public void TwoDiffCustomerOfSameTypeDVCHaveSameDelivDist() throws CustomerException {
		assertEquals(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, -4).getDeliveryDistance(),
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", -3, -4).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfSameTypeDNCHaveSameDelivDist() throws CustomerException {
		assertEquals(CustomerFactory.getCustomer("DNC", "Mark", "0447539207", 3, -4).getDeliveryDistance(),
				CustomerFactory.getCustomer("DNC", "Mark", "0447539207", -3, -4).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfSameTypePUCHaveSameDelivDist() throws CustomerException {
		assertEquals(CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, -0).getDeliveryDistance(),
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", -0, -0).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfDiffTypePUCAndDVCHaveDiffDelivDist() throws CustomerException {
		assertNotEquals(CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, -0).getDeliveryDistance(),
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", -0, 4).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfDiffTypeDNCAndDVCHaveDiffDelivDist() throws CustomerException {
		assertNotEquals(CustomerFactory.getCustomer("DNC", "Mark", "0447539207", -5, 4).getDeliveryDistance(),
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", -5, 4).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfSameTypeDVCHaveDiffDelivDist() throws CustomerException {
		assertNotEquals(CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 3, -4).getDeliveryDistance(),
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", -5, 4).getDeliveryDistance(), DELTA);
	}
	
	@Test
	public void TwoDiffCustomerOfSameTypeDNCHaveDiffDelivDist() throws CustomerException {
		assertNotEquals(CustomerFactory.getCustomer("DNC", "Mark", "0447539207", -5, 2).getDeliveryDistance(),
				CustomerFactory.getCustomer("DNC", "Mark", "0447539207", -5, 4).getDeliveryDistance(), DELTA);
	}

	// ----------- Exception Testing -------------

	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenInvalidCustomerCode() throws CustomerException {
		CustomerFactory.getCustomer("WER", "John", "0443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenEmptyName() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "", "0443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenWhiteSpaceName() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "  ", "0443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenNameTooLong() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "Johhhhhhnnnnnnnnnnnnn", "0443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenNameTooLongAndWhiteSpace() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "                        ", "0443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenMobNumDoesNotStartWithZero() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "4443514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenMobNumLessThanTenDigits() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "043514214", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenMobNumMoreThanTenDigits() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "04351421444", 4, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXLocationIsTooHigh() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "0435142144", 11, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXLocationIsTooLow() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "0435142144", -11, 3);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenYLocationIsTooHigh() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "0435142144", -1, 11);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenYLocationIsTooLow() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "John", "0435142144", 3, -11);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXLocationNotZeroIfPickUp() throws CustomerException {
		expectedString = "Pick Up";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 4, 0).getCustomerType());
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenYLocationNotZeroIfPickUp() throws CustomerException {
		expectedString = "Pick Up";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 0, -4).getCustomerType());
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXAndYLocationNotZeroIfPickUp() throws CustomerException {
		expectedString = "Pick Up";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("PUC", "Mark", "0447539207", 4, -4).getCustomerType());
	}


	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXAndYLocationIsZeroIfDroneDelivery() throws CustomerException {
		expectedString = "Drone Delivery";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DVC", "Mark", "0447539207", 0, 0).getCustomerType());
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionThrownWhenXAndYLocationIsZeroIfDriverDelivery() throws CustomerException {
		expectedString = "Driver Delivery";
		assertEquals(expectedString, 
				CustomerFactory.getCustomer("DNC", "Mark", "0447539207", 0, 0).getCustomerType());
	}

}
