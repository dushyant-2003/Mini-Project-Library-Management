package com.firstClass;

public class Customer {
	private String name;
	private Address homeAddress;
	private Address workAddress;
	public Customer(String name, Address homeAddress) {
		this.name = name;
		this.homeAddress = homeAddress;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", homeAddress=" + homeAddress + ", workAddress=" + workAddress + "]";
	}
	
	
	
}
