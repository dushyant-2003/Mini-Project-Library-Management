package com.firstClass;

public abstract class AbstractRecipe {
	//prepare
	//recipe
	//cleanup
	
	public void execute()
	{
		getReady();
		doTheDish();
		cleanUp();
	}
	abstract void getReady();
	abstract void doTheDish();
	abstract void cleanUp();
	
}
