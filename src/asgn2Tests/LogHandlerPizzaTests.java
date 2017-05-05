package asgn2Tests;

import static org.junit.Assert.assertEquals;

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
}
