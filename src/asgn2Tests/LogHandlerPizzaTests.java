package asgn2Tests;

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
    }
}
