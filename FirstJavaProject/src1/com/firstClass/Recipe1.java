package com.firstClass;

public class Recipe1 extends AbstractRecipe{

	@Override
	void getReady() {
		// TODO Auto-generated method stub
		System.out.println("Get the raw materials");
		System.out.println("Get the utensils");
		System.out.println("Turn on the microwave");
	}

	@Override
	void doTheDish() {
		// TODO Auto-generated method stub
		System.out.println("Do the dish");
		
	}

	@Override
	void cleanUp() {
		// TODO Auto-generated method stub
		System.out.println("Clean the mess");
		System.out.println("Turn off the microwave");
	}
	
	
}
