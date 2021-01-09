package app.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import app.entity.Item;
import app.entity.Order;
import app.entity.Product;

/**
 * Represents the data storage.
 * In a real world, it could be a database or api
 * @author juccelino.barros
 *
 */
public class OrderRepository {
	
	private static final String NOKIA_1100 = "Nokia 1100";
	private static final String SAMSUNG_A1 = "Samsung A1";
	private static final String I_PHONE_12 = "IPhone 12";

	/**
	 * There are 4 products (cellphone) which each one represents a specific category based by creation date.
	 * Here are the category:
	 * 1-3 months
	 * 4-6 months
	 * 7-12 months
	 * > 12 months
	 * @return
	 */
	private List<Product> createProducts() {
		// 1-3 months 
		LocalDateTime recentDate = LocalDateTime.of(2018, Month.JANUARY, 29, 10, 30, 40);
		// 4-6 months
		LocalDateTime middleDate = LocalDateTime.of(2017, Month.NOVEMBER, 15, 9, 30, 40);
		// > 12 months
		LocalDateTime farawayDate = LocalDateTime.of(2013, Month.MAY, 01, 13, 30, 40);
		
		Product iphone12 = new Product(I_PHONE_12, "Smartphone", recentDate, new BigDecimal("2500.99"));
		Product samsungA1 = new Product(SAMSUNG_A1, "Smartphone", middleDate, new BigDecimal("1805.22"));
		Product nokia1100 = new Product(NOKIA_1100, "Smartphone", farawayDate, new BigDecimal("100.50"));
		
		return Arrays.asList(iphone12, samsungA1, nokia1100);
	}
	
	/**
	 * There is one item by product.
	 * @return
	 */
	private List<Item> createItens() {
		List<Product> allProducts = this.createProducts();
		List<Item> itens = new ArrayList<>();
		allProducts.forEach(prod -> {
			Item item = new Item(prod.getPrice(), new BigDecimal("50.0"), new BigDecimal("75"), 1, prod);
			itens.add(item);
		});
		return itens;
	}
	
	/**
	 * 6 orders -- Iphone 12
	 * @return orders
	 */
	private List<Order> createOrdersIphone12(Item item) {
		LocalDateTime dateOrder1 = LocalDateTime.of(2018, Month.FEBRUARY, 15, 15, 30, 40);
		Order order1 = new Order("Juccelino", "", "", new BigDecimal("2500"), dateOrder1, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder2 = LocalDateTime.of(2018, Month.MARCH, 16, 14, 30, 40);
		Order order2 = new Order("Rodrigues", "", "", new BigDecimal("2700"), dateOrder2, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder3 = LocalDateTime.of(2018, Month.MAY, 17, 13, 30, 40);
		Order order3 = new Order("Alves", "", "", new BigDecimal("2233"), dateOrder3, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder4 = LocalDateTime.of(2018, Month.JULY, 18, 19, 30, 40);
		Order order4 = new Order("de", "", "", new BigDecimal("1800.56"), dateOrder4, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder5 = LocalDateTime.of(2018, Month.SEPTEMBER, 19, 11, 30, 40);
		Order order5 = new Order("Barros", "", "", new BigDecimal("1900.12"), dateOrder5, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder6 = LocalDateTime.of(2018, Month.DECEMBER, 20, 5, 30, 40);
		Order order6 = new Order("Juka", "", "", new BigDecimal("2600"), dateOrder6, new HashSet<>(Arrays.asList(item)));
		
		return Arrays.asList(order1, order2, order3, order4, order5, order6);
				
	}
	
	/**
	 * 4 orders -- Samsung A1
	 * @return orders
	 */
	private List<Order> createOrdersSamsung(Item item) {
		LocalDateTime dateOrder1 = LocalDateTime.of(2018, Month.FEBRUARY, 15, 15, 30, 40);
		Order order1 = new Order("Will", "", "", new BigDecimal("1100"), dateOrder1, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder2 = LocalDateTime.of(2018, Month.MARCH, 16, 14, 30, 40);
		Order order2 = new Order("Bob", "", "", new BigDecimal("1300"), dateOrder2, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder3 = LocalDateTime.of(2018, Month.MAY, 17, 13, 30, 40);
		Order order3 = new Order("Francisca", "", "", new BigDecimal("1554"), dateOrder3, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder4 = LocalDateTime.of(2018, Month.JULY, 18, 19, 30, 40);
		Order order4 = new Order("Juliana", "", "", new BigDecimal("900"), dateOrder4, new HashSet<>(Arrays.asList(item)));
		
		return Arrays.asList(order1, order2, order3, order4);
				
	}
	
	
	/**
	 * 2 orders -- Nokia 1100 
	 * @return orders
	 */
	private List<Order> createOrdersNokia(Item item) {
		LocalDateTime dateOrder1 = LocalDateTime.of(2018, Month.APRIL, 15, 15, 30, 40);
		Order order1 = new Order("Mickey", "", "", new BigDecimal("200"), dateOrder1, new HashSet<>(Arrays.asList(item)));
		
		LocalDateTime dateOrder2 = LocalDateTime.of(2018, Month.OCTOBER, 16, 14, 30, 40);
		Order order2 = new Order("Donald", "", "", new BigDecimal("250"), dateOrder2, new HashSet<>(Arrays.asList(item)));
		
		return Arrays.asList(order1, order2);
	}
	
	
	/**
	 * Here is the distribution of the orders:
	 * 6 orders -- Iphone 12
	 * 4 orders -- Samsung A1
	 * 3 orders -- LG K10
	 * 2 orders -- Nokia 1100
	 * 
	 * Total: 15 orders. 
	 * @return orders
	 */
	public List<Order> loadData() {
		List<Item> itens = this.createItens();
		List<Order> allOrders = new ArrayList<>();
		
		itens.forEach(item -> {
			if (item.getProduct().getName().equals(I_PHONE_12)) {
				allOrders.addAll(this.createOrdersIphone12(item));
				
			} else if (item.getProduct().getName().equals(SAMSUNG_A1)) {
				allOrders.addAll(this.createOrdersSamsung(item));
				
			} else {
				// nokia 1100
				allOrders.addAll(this.createOrdersNokia(item));
			}
		});
		
		return allOrders;
		
	}
	
	

}
