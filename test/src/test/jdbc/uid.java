package test.jdbc;
import java.security.SecureRandom;
	import java.math.BigInteger;

public class uid {
	
	
	    private static final SecureRandom random = new SecureRandom();

	    public static void main(String[] args) {
	        String uniqueId = new BigInteger(16, random).toString(8);
	        System.out.println("Generated Unique ID: " + uniqueId);
	        
	}

}
