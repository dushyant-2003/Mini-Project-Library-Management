package test.jdbc;

interface A {
	
	public default void fun() {
		System.out.println("Interface A");
	}

}

interface B {
	//public  void fun();
	public default void fun1() {
		System.out.println("Interface A");
	}
}

public class abc implements A,B{

//	@Override
//	public void fun() {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void fun() {
//		// TODO Auto-generated method stub
//		A.super.fun();
//	}

	
}