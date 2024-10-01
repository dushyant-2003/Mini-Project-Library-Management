package consumer;



import java.util.logging.Logger;
import java.util.List;

import sorting.MySortingUtil;

public class MySortingUtilConsumer {
	
	private static Logger logger = Logger.getLogger(MySortingUtilConsumer.class.getName());
	
	public static void main(String[] args)
	{
		MySortingUtil util = new MySortingUtil();
		List<String> sorted = util.sort(List.of("A","B","D","C"));
		logger.info(sorted.toString());
		
	
	
	}
}
