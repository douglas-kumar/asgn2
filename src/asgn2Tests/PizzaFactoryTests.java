package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B - Douglas
 * 
 */
public class PizzaFactoryTests {

    Pizza pizza1;

    @Test(expected = PizzaException.class)
    public void getPizzaUsingBadCode() throws PizzaException {
        pizza1 = PizzaFactory.getPizza("PZN", 2, LocalTime.parse("21:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.parse("21:30:00", DateTimeFormatter.ISO_TIME));
    }

    @Test
    public void getMargheritaPizza() throws PizzaException {
        pizza1 = PizzaFactory.getPizza("PZM", 1, LocalTime.parse("21:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.parse("21:30:00", DateTimeFormatter.ISO_TIME));
        assertEquals("Margherita", pizza1.getPizzaType());
    }

    @Test
    public void getVegetarianPizza() throws PizzaException {
        pizza1 = PizzaFactory.getPizza("PZV", 1, LocalTime.parse("21:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.parse("21:30:00", DateTimeFormatter.ISO_TIME));
        assertEquals("Vegetarian", pizza1.getPizzaType());
    }

    @Test
    public void getMeatloversPizza() throws PizzaException {
        pizza1 = PizzaFactory.getPizza("PZL", 1, LocalTime.parse("21:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.parse("21:30:00", DateTimeFormatter.ISO_TIME));
        assertEquals("Meat Lovers", pizza1.getPizzaType());
    }
}
