package asgn2Tests;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza,
 * asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. Note that
 * an instance of asgn2Pizzas.MeatLoversPizza should be used to test the
 * functionality of the asgn2Pizzas.Pizza abstract class.
 * 
 * @author Person B
 *
 */
public class PizzaTests {
    private Pizza pizza1;

    @Before
    public void setup() throws PizzaException {
        pizza1 = new MargheritaPizza(2, LocalTime.of(20, 10, 00), LocalTime.of(20, 30, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithNegativeQuantity() throws PizzaException {
        pizza1 = new MargheritaPizza(-1, LocalTime.of(19, 30, 00), LocalTime.of(19, 50, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithOverTenQuantity() throws PizzaException {
        pizza1 = new VegetarianPizza(11, LocalTime.of(19, 30, 00), LocalTime.of(19, 50, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderBeforeOpenTime() throws PizzaException {
        pizza1 = new MeatLoversPizza(2, LocalTime.of(18, 59, 00), LocalTime.of(19, 30, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderAfterCloseTime() throws PizzaException {
        pizza1 = new MargheritaPizza(1, LocalTime.of(23, 00, 01), LocalTime.of(23, 15, 30));
    }

    @Test(expected = PizzaException.class)
    public void orderWithDeliveryOverOneHour() throws PizzaException {
        pizza1 = new VegetarianPizza(3, LocalTime.of(20, 00, 00), LocalTime.of(21, 00, 01));
    }

    @Test(expected = PizzaException.class)
    public void orderWithDeliveryWithinTenMinutesOfOrder() throws PizzaException {
        pizza1 = new MeatLoversPizza(4, LocalTime.parse("20:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.of(20, 05, 00));
    }
}
