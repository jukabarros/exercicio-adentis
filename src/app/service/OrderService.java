package app.service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import app.entity.Order;
import app.repository.OrderRepository;

public class OrderService {
	
	private static final String _12_MONTHS = ">12 months";
	private static final String _7_12_MONTHS = "7-12 months";
	private static final String _4_6_MONTHS = "4-6 months";
	private static final String _1_3_MONTHS = "1-3 months";

	private OrderRepository repository;
	
	private List<Order> allOrders;

	public OrderService() {
		repository = new OrderRepository();
		allOrders = repository.loadData();
	}
	
	/**
	 * Filter orders by all groups
	 * @param dateInit
	 * @param dateEnd
	 * @return
	 */
	public Map<String, Integer> filterAllOrders(LocalDateTime dateInit, LocalDateTime dateEnd) {
		Map<String, Integer> result = createResultDefault();
		
		// filter by interval specified in parameters
		List<Order> ordersFilteredByDate = allOrders.stream()
				.filter(order -> order.getDateOrder().isAfter(dateInit) && order.getDateOrder().isBefore(dateEnd))
				.collect(Collectors.toList());
		
		if (!ordersFilteredByDate.isEmpty()) {

			ordersFilteredByDate.stream().forEach(order -> order.getItens().forEach(item -> {
				long monthsBTWCreationAndOrder = ChronoUnit.MONTHS.between(
					     YearMonth.from(item.getProduct().getCreationDate()), 
					     YearMonth.from(order.getDateOrder()));
				
				if (monthsBTWCreationAndOrder < 4) {
					result.put(_1_3_MONTHS, result.get(_1_3_MONTHS) + 1);
				} else if (monthsBTWCreationAndOrder >= 4 && monthsBTWCreationAndOrder <= 6) {
					result.put(_4_6_MONTHS, result.get(_4_6_MONTHS) + 1);
				} else if (monthsBTWCreationAndOrder > 6 && monthsBTWCreationAndOrder <= 12) {
					result.put(_7_12_MONTHS, result.get(_7_12_MONTHS) + 1);
				} else {
					// > 12
					result.put(_12_MONTHS, result.get(_12_MONTHS) + 1);
				}
			}));
		}
		
		return result;
	}
	
	/**
	 * It is called when the parameter is a period of months.
	 * Example:
	 * 1-3 (between 1 and 3 months)
	 * @param monthInit
	 * @param monthEnd
	 * @return result
	 */
	public Map<String, Integer> filterOrdersBySpecificInterval(Integer monthInit, Integer monthEnd) {
		Map<String, Integer> result = new HashMap<>();
		
		Long numberOfOrders = allOrders.stream().flatMap(order -> order.getItens()
				.stream().filter(item -> 
						ChronoUnit.MONTHS.between(
								YearMonth.from(item.getProduct().getCreationDate()), 
								YearMonth.from(order.getDateOrder())) >= monthInit 
					     && 
					     ChronoUnit.MONTHS.between(
							     YearMonth.from(item.getProduct().getCreationDate()), 
							     YearMonth.from(order.getDateOrder())) <= monthEnd)).count();
		
		result.put(monthInit+"-" + monthEnd + " months", numberOfOrders.intValue());
		
		return result;
	}
	
	private Map<String, Integer>  createResultDefault() {
		Map<String, Integer> result = new LinkedHashMap<>();
		result.put(_1_3_MONTHS, 0);
		result.put(_4_6_MONTHS, 0);
		result.put(_7_12_MONTHS, 0);
		result.put(_12_MONTHS, 0);
		return result;
	}

}
