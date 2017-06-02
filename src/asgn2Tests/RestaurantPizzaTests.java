package asgn2Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in
 * the asgn2Restaurant.PizzaRestaurant class as well as processLog and
 * resetDetails.
 * 
 * @author Person B - Douglas
 *
 */
public class RestaurantPizzaTests {
    PizzaRestaurant pr;

    @Before
    public void setUp() {
        pr = new PizzaRestaurant();
    }

    @Test
    public void makeRestaurantFromGoodFile() throws CustomerException, PizzaException, LogHandlerException {
        assertTrue(pr.processLog("20170102.txt"));
    }
}
