package com.firstClass;




public class MultiplicationTable {

	void print()
	{
		for(int i=1;i<=10;i++)
		{
			System.out.printf("5 x %d = %d",i,5*i).println();
		}
	}
	void print(int n)
	{
		System.out.println("Table of " + n);
		for(int i=1;i<=10;i++)
		{
			System.out.printf("%d x %d = %d",n,i,n*i).println();
		}
	}
}
