package com.firstClass;

public class CustomerRunner {
	public static void main(String[] args)
	{
		Address address1 = new Address("Janta Colony","Jaipur","302004");
		Customer c1 = new Customer("Dushyant",address1);
		System.out.println(c1);
				
	}
}
