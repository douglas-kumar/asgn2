package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Pizza objects in
 * the asgn2Restaurant.LogHander class.
 * 
 * @author Person B - Douglas
 * 
 */
public class LogHandlerPizzaTests {
    ArrayList<Pizza> pizzas;
    Pizza pizza1;
    Pizza pizza2;

    @Test
    public void readFromLog1() throws PizzaException, LogHandlerException {
        pizzas = LogHandler.populatePizzaDataset("20170101.txt");
        assertEquals("Vegetarian", pizzas.get(0).getPizzaType());
        assertEquals(2, pizzas.get(0).getQuantity());
        assertEquals("Margherita", pizzas.get(1).getPizzaType());
        assertEquals(1, pizzas.get(1).getQuantity());
        assertEquals("Meat Lovers", pizzas.get(2).getPizzaType());
        assertEquals(3, pizzas.get(2).getQuantity());
    }

    @Test
    public void readFromLog2() throws PizzaException, LogHandlerException {
        pizzas = LogHandler.populatePizzaDataset("20170102.txt");
        assertEquals("Meat Lovers", pizzas.get(1).getPizzaType());
        assertEquals(9, pizzas.get(1).getQuantity());
        assertEquals("Vegetarian", pizzas.get(4).getPizzaType());
        assertEquals(7, pizzas.get(4).getQuantity());
        assertEquals("Meat Lovers", pizzas.get(7).getPizzaType());
        assertEquals(4, pizzas.get(7).getQuantity());
    }

    @Test
    public void readFromLog3() throws PizzaException, LogHandlerException {
        pizzas = LogHandler.populatePizzaDataset("20170103.txt");
        assertEquals("Meat Lovers", pizzas.get(24).getPizzaType());
        assertEquals(8, pizzas.get(24).getQuantity());
        assertEquals("Meat Lovers", pizzas.get(49).getPizzaType());
        assertEquals(4, pizzas.get(49).getQuantity());
        assertEquals("Vegetarian", pizzas.get(74).getPizzaType());
        assertEquals(5, pizzas.get(74).getQuantity());
    }

    @Test
    public void readFromEmptyLog() throws PizzaException, LogHandlerException {
        pizzas = LogHandler.populatePizzaDataset("empty.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void makePizzaWithBadDataLine() throws PizzaException, LogHandlerException {
        pizza1 = LogHandler.createPizza("19:10:00,19:40:00,Joe Dolce,DVC,3,6,PZV,3");
    }

    @Test(expected = LogHandlerException.class)
    public void makePizzaWithBadOrderTime() throws PizzaException, LogHandlerException {
        pizza1 = LogHandler.createPizza("-20:00:00,20:30:00,Jim Hall,0456876543,DNC,7,8,PZL,2");
    }

    @Test
    public void makeAPizza() throws PizzaException, LogHandlerException {
        pizza1 = LogHandler.createPizza("19:10:00,19:40:00,Joe Dolce,0428654767,DVC,3,6,PZV,3");
        assertEquals("Vegetarian", pizza1.getPizzaType());
        assertEquals(3, pizza1.getQuantity());
    }

    @Test
    public void compareEqualPizzas() throws PizzaException, LogHandlerException {
        pizza1 = LogHandler.createPizza("20:05:00,20:30:00,Ryan King,0484323901,DNC,2,1,PZL,7");
        pizza2 = LogHandler.createPizza("20:05:00,20:30:00,Ryan King,0484323901,DNC,2,1,PZL,7");
        assertTrue(pizza1.equals(pizza2));
    }
}
