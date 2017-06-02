package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
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
    private static final double DELTA = 1e-15;
    PizzaRestaurant pr;
    PizzaRestaurant pr2;

    @Before
    public void setUp() {
        pr = new PizzaRestaurant();
    }

    @Test
    public void makeRestaurantFromGoodFile() throws CustomerException, PizzaException, LogHandlerException {
        assertTrue(pr.processLog("20170102.txt"));
    }

    @Test
    public void makeRestaurantFromBadFile() throws CustomerException, PizzaException, LogHandlerException {
        assertFalse(pr.processLog("empty.txt"));
    }

    @Test
    public void checkPizzaAtIndex() throws CustomerException, PizzaException, LogHandlerException {
        pr.processLog("20170102.txt");
        Pizza pizza1 = pr.getPizzaByIndex(5);
        assertEquals("Meat Lovers", pizza1.getPizzaType());
        assertEquals(7, pizza1.getQuantity());
    }

    @Test
    public void checkNumberOfOrders() throws CustomerException, PizzaException, LogHandlerException {
        pr.processLog("20170102.txt");
        assertEquals(10, pr.getNumPizzaOrders());
    }

    @Test
    public void checkTotalProfit() throws CustomerException, PizzaException, LogHandlerException {
        pr.processLog("20170102.txt");
        assertEquals(316.5, pr.getTotalProfit(), DELTA);
    }

    @Test
    public void testReset() throws CustomerException, PizzaException, LogHandlerException {
        pr.processLog("20170103.txt");
        pr.resetDetails();
        assertEquals(0, pr.getNumPizzaOrders());
    }
}
