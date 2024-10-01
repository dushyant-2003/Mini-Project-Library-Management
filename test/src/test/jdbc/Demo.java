package test.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

//abstract class Demo1 implements Callable{
//	
//	abstract void fun1();
//	
//	void fun2() {
//		System.out.println("Non abstract function in abstract class");
//		
//	}
//}
//
//
//class Abc extends Demo1{
//	@Override
//	void fun2() {
//		System.out.println("OVERRIDEN");
//	}
//	
//	@Override
//	void fun1() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Object call() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}

public class Demo {
	public static void main(String[] args) {
		
		List<String> s1 = Arrays.asList("hi", "hello");
	//	s1.add("tom");
		
		List<String> s2 = new ArrayList<>();
		//		List.of("Hii", "Hello", "London");
		s2.add("today");
        s2.addAll(s1);
		System.out.println(s2);

//		ExecutorService ex = Executors.newFixedThreadPool(5);
//		for(int i=0;i<5;i++) {
//			final int task = i;
//		ex.submit( () ->  reverse(task) );
//		}
//		Collectors.toList();		
	}
	
	
	private static  void reverse(int thread)  {
		try {
			System.out.println(Thread.currentThread().getName() +  " " +  thread);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}