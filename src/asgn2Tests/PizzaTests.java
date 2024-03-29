package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza,
 * asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. Note that
 * an instance of asgn2Pizzas.MeatLoversPizza should be used to test the
 * functionality of the asgn2Pizzas.Pizza abstract class.
 * 
 * @author Person B - Douglas
 *
 */
public class PizzaTests {
    private static final double DELTA = 1e-15;
    private Pizza pizza1;
    private Pizza pizza2;

    @Before
    public void setup() throws PizzaException {
        pizza1 = new MargheritaPizza(2, LocalTime.of(20, 10, 00), LocalTime.of(20, 30, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithNegativeQuantity() throws PizzaException {
        pizza1 = new MargheritaPizza(-1, LocalTime.of(19, 30, 00), LocalTime.of(19, 50, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithNegativeQuantity2() throws PizzaException {
        pizza1 = new MeatLoversPizza(-24, LocalTime.of(19, 20, 00), LocalTime.of(19, 43, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithOverTenQuantity() throws PizzaException {
        pizza1 = new VegetarianPizza(11, LocalTime.of(19, 30, 00), LocalTime.of(19, 50, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithOverTenQuantity2() throws PizzaException {
        pizza1 = new VegetarianPizza(18, LocalTime.of(19, 30, 00), LocalTime.of(19, 50, 00));
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

    @Test
    public void orderWithDeliveryOnOneHour() throws PizzaException {
        pizza1 = new MargheritaPizza(5, LocalTime.of(20, 00, 00), LocalTime.of(21, 00, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithDeliveryWithinTenMinutesOfOrder() throws PizzaException {
        pizza1 = new MeatLoversPizza(4, LocalTime.parse("20:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.of(20, 05, 00));
    }

    @Test(expected = PizzaException.class)
    public void orderWithDeliveryBeforeOrderTime() throws PizzaException {
        pizza1 = new MeatLoversPizza(6, LocalTime.parse("20:00:00", DateTimeFormatter.ISO_TIME),
                LocalTime.of(19, 59, 00));
    }

    @Test
    public void getCostPerMargheritaPizza() {
        assertEquals(1.5, pizza1.getCostPerPizza(), DELTA);
    }

    @Test
    public void getCostPerVegetarianPizza() throws PizzaException {
        pizza1 = new VegetarianPizza(3, LocalTime.of(20, 00, 00), LocalTime.of(20, 18, 00));
        assertEquals(5.5, pizza1.getCostPerPizza(), DELTA);
    }

    @Test
    public void getCostPerMeatLoversPizza() throws PizzaException {
        pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 00, 00), LocalTime.of(20, 15, 00));
        assertEquals(5.0, pizza1.getCostPerPizza(), DELTA);
    }

    @Test
    public void getMargheritaPrice() {
        assertEquals(8.0, pizza1.getPricePerPizza(), DELTA);
    }

    @Test
    public void getVegetarianPrice() throws PizzaException {
        pizza1 = new VegetarianPizza(3, LocalTime.of(20, 00, 00), LocalTime.of(20, 20, 00));
        assertEquals(10.0, pizza1.getPricePerPizza(), DELTA);
    }

    @Test
    public void getMeatLoversPrice() throws PizzaException {
        pizza1 = new MeatLoversPizza(3, LocalTime.of(20, 00, 00), LocalTime.of(20, 25, 00));
        assertEquals(12.0, pizza1.getPricePerPizza(), DELTA);
    }

    @Test
    public void getOrderCost1() {
        assertEquals(3.0, pizza1.getOrderCost(), DELTA);
    }

    @Test
    public void getOrderCost2() throws PizzaException {
        pizza1 = new VegetarianPizza(9, LocalTime.of(20, 00, 00), LocalTime.of(20, 40, 00));
        assertEquals(49.5, pizza1.getOrderCost(), DELTA);
    }

    @Test
    public void getOrderCost3() throws PizzaException {
        pizza1 = new MeatLoversPizza(5, LocalTime.of(20, 00, 00), LocalTime.of(20, 32, 00));
        assertEquals(25.0, pizza1.getOrderCost(), DELTA);
    }

    @Test
    public void getOrderPrice1() {
        assertEquals(16.0, pizza1.getOrderPrice(), DELTA);
    }

    @Test
    public void getOrderPrice2() throws PizzaException {
        pizza1 = new VegetarianPizza(8, LocalTime.of(19, 15, 00), LocalTime.of(20, 05, 00));
        assertEquals(80.0, pizza1.getOrderPrice(), DELTA);
    }

    @Test
    public void getOrderPrice3() throws PizzaException {
        pizza1 = new MeatLoversPizza(7, LocalTime.of(19, 15, 00), LocalTime.of(19, 35, 00));
        assertEquals(84.0, pizza1.getOrderPrice(), DELTA);
    }

    @Test
    public void getOrderProfit1() {
        assertEquals(13.0, pizza1.getOrderProfit(), DELTA);
    }

    @Test
    public void getOrderProfit2() throws PizzaException {
        pizza1 = new VegetarianPizza(4, LocalTime.of(19, 12, 00), LocalTime.of(19, 30, 00));
        assertEquals(18.0, pizza1.getOrderProfit(), DELTA);
    }

    @Test
    public void getOrderProfit3() throws PizzaException {
        pizza1 = new MeatLoversPizza(6, LocalTime.of(19, 10, 00), LocalTime.of(19, 40, 00));
        assertEquals(42.0, pizza1.getOrderProfit(), DELTA);
    }

    @Test
    public void checkMargheritaToppings() {
        assertTrue(pizza1.containsTopping(PizzaTopping.TOMATO));
        assertTrue(pizza1.containsTopping(PizzaTopping.CHEESE));
    }

    @Test
    public void checkVegetarianToppings() throws PizzaException {
        pizza1 = new VegetarianPizza(1, LocalTime.of(19, 14, 00), LocalTime.of(19, 33, 00));
        assertTrue(pizza1.containsTopping(PizzaTopping.TOMATO));
        assertTrue(pizza1.containsTopping(PizzaTopping.CHEESE));
        assertTrue(pizza1.containsTopping(PizzaTopping.EGGPLANT));
        assertTrue(pizza1.containsTopping(PizzaTopping.MUSHROOM));
        assertTrue(pizza1.containsTopping(PizzaTopping.CAPSICUM));
    }

    @Test
    public void checkMeatLoversToppings() throws PizzaException {
        pizza1 = new MeatLoversPizza(9, LocalTime.of(19, 16, 30), LocalTime.of(19, 48, 14));
        assertTrue(pizza1.containsTopping(PizzaTopping.TOMATO));
        assertTrue(pizza1.containsTopping(PizzaTopping.CHEESE));
        assertTrue(pizza1.containsTopping(PizzaTopping.BACON));
        assertTrue(pizza1.containsTopping(PizzaTopping.PEPPERONI));
        assertTrue(pizza1.containsTopping(PizzaTopping.SALAMI));
    }

    @Test
    public void checkQuantity() {
        assertEquals(2, pizza1.getQuantity());
    }

    @Test
    public void checkQuantity2() throws PizzaException {
        pizza1 = new VegetarianPizza(5, LocalTime.of(19, 05, 00), LocalTime.of(19, 30, 00));
        assertEquals(5, pizza1.getQuantity());
    }

    @Test
    public void checkQuantity3() throws PizzaException {
        pizza1 = new MeatLoversPizza(9, LocalTime.of(21, 45, 00), LocalTime.of(22, 8, 00));
        assertEquals(9, pizza1.getQuantity());
    }

    @Test
    public void getMargheritaType() {
        assertEquals("Margherita", pizza1.getPizzaType());
    }

    @Test
    public void getVegetarianType() throws PizzaException {
        pizza1 = new VegetarianPizza(5, LocalTime.of(19, 05, 00), LocalTime.of(19, 30, 00));
        assertEquals("Vegetarian", pizza1.getPizzaType());
    }

    @Test
    public void getMeatLoversType() throws PizzaException {
        pizza1 = new MeatLoversPizza(9, LocalTime.of(21, 45, 00), LocalTime.of(22, 8, 00));
        assertEquals("Meat Lovers", pizza1.getPizzaType());
    }

    @Test
    public void checkIdenticalOrder() throws PizzaException {
        pizza1 = new MeatLoversPizza(4, LocalTime.of(22, 00, 00), LocalTime.of(22, 20, 00));
        pizza2 = new MeatLoversPizza(4, LocalTime.of(22, 00, 00), LocalTime.of(22, 20, 00));
        assertTrue(pizza1.equals(pizza2));
    }

    @Test
    public void checkDifferentOrders() throws PizzaException {
        pizza1 = new VegetarianPizza(2, LocalTime.of(20, 00, 00), LocalTime.of(20, 30, 00));
        pizza2 = new VegetarianPizza(1, LocalTime.of(20, 00, 00), LocalTime.of(20, 30, 00));
        assertFalse(pizza1.equals(pizza2));
    }

    @Test
    public void checkDifferentTypesNotEqual() throws PizzaException {
        pizza1 = new VegetarianPizza(2, LocalTime.of(20, 00, 00), LocalTime.of(20, 30, 00));
        pizza2 = new MargheritaPizza(2, LocalTime.of(20, 00, 00), LocalTime.of(20, 30, 00));
        assertFalse(pizza1.equals(pizza2));
    }
}
