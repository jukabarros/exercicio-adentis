package app;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import app.service.OrderService;

public class Main {

	public static void main(String[] args) {
		String init = "2018-01-01 00:00:00";
		String end = "2019-01-01 00:00:00";
		if(args.length > 1) {
			init = args[0];
			end = args[1];
		}
		
		try {
			LocalDateTime dateInit = convertStrToDate(init);
			LocalDateTime dateEnd = convertStrToDate(end);
			OrderService service = new OrderService();
			Map<String, Integer> result = service.filterOrders(dateInit, dateEnd);
			System.out.println("Filter OK. Result:");
			result.entrySet().forEach(map -> {
				System.out.println(map.getKey() + " : " + map.getValue() + " orders");
			});
		} catch (DateTimeException e) {
			System.err.println("*** error parsing string to date. More info: "+e.getMessage());
		}
		
	}
	
	public static LocalDateTime convertStrToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return  LocalDateTime.parse(dateStr, formatter);
	}

}
