package com.firstClass;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListRunner {
	public static void main(String[] args)
	{
		ArrayList<Integer> array = new ArrayList<Integer>(5);
//		System.out.println(array.size());
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		
//		System.out.println(array.size());
		array.add(6);
//		System.out.println(array.size());
		ArrayList<Integer> array2 = new ArrayList<Integer>(5);
		array2.add(100);
		array2.add(200);
		
		array.addAll(2,array2);
		for(Integer n:array)
		{
			System.out.println(n);
		}
		
		
	}
	 
}
