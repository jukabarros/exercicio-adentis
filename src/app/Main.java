package app;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import app.service.OrderService;

public class Main {

	public static void main(String[] args) {

		Map<String, Integer> result = new HashMap<>();

		if (args.length == 1) {
			String[] months = args[0].split("-");
			System.out.println("**** Group: " + args[0]);
			result = filterByMonthsInterval(months[0], months[1]);
		} else if (args.length > 1) {
			System.out.println("**** All Groups");
			result = filterByDateInterval(args[0], args[1]);
		}

		if (!result.isEmpty()) {
			result.forEach((k, v) -> System.out.println(k + " : " + v));
		} else {
			System.out.println("Not found");
		}

	}

	private static Map<String, Integer> filterByMonthsInterval(String init, String end) {
		try {
			Integer initMonth = Integer.parseInt(init);
			Integer endMonth = Integer.parseInt(end);
			OrderService service = new OrderService();
			
			return service.filterOrdersBySpecificInterval(initMonth, endMonth);
		} catch (NumberFormatException e) {
			System.err.println("*** error: cannot parse string to integer. More info: "+e.getMessage());
			return null;
		}
	}

	private static Map<String, Integer> filterByDateInterval(String init, String end) {
		try {
			LocalDateTime dateInit = convertStrToDate(init);
			LocalDateTime dateEnd = convertStrToDate(end);
			OrderService service = new OrderService();
			return service.filterAllOrders(dateInit, dateEnd);

		} catch (DateTimeException e) {
			System.err.println("*** error: cannot parse string to date. More info: "+e.getMessage());
			return null;
		}
	}

	public static LocalDateTime convertStrToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return  LocalDateTime.parse(dateStr, formatter);
	}

}
