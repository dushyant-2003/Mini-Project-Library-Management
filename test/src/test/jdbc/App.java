package test.jdbc;

import java.io.Console;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}
interface in {
	String name = "interface name";
}
public class App {
	
	// Java Program to illustrate memory leaks

		public static void main(String[] args) 
		{
			String name = in.name;
			System.out.println(name);
		}
	

//	public static void main(String[] args) throws ClassNotFoundException, SQLException
//	{
//		// step1 register the driver
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		
//		
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","mysql");
//		
//		// insert query
//		String insertSQL = "INSERT INTO student (rollno, name, city) VALUES (?, ?,?)";
//
//		try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
//		    // Set the values for the placeholders
//		    preparedStatement.setInt(1, 5);         // id
//		    preparedStatement.setString(2, "Pulkit"); // name
//		    preparedStatement.setString(3, "UK"); // city
//		    
//
//		    // Execute the insert operation
//		    int rowsAffected = preparedStatement.executeUpdate();
//		    System.out.println("Rows inserted: " + rowsAffected);
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
//		
//		
//		
//		String query = "select * from student";
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println("Students: ");
//            System.out.println("+------------+--------------------+----------+------------+");
//            System.out.println("| Student Rollno | Name               | City");
//            System.out.println("+------------+--------------------+----------+------------+");
//            while(resultSet.next()){
//                int id = resultSet.getInt("rollno");
//                String name = resultSet.getString("name");
//                String city = resultSet.getString("city");
//                System.out.printf("| %-10s | %-18s | %-8s |\n", id, name, city);
//                System.out.println("+------------+--------------------+----------+------------+");
//            }
//            
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//		
//		System.out.println("Connection created");
//		Integer in = 5;
//		System.out.println(Integer.valueOf(in));
//		System.out.println(in.hashCode());
//		String str1 = "abc";
//		String str2 ="abcd";
//		StringBuilder s1 = new StringBuilder("abc");
//		StringBuilder s2 = new StringBuilder("abc");
//		System.out.println(s1.hashCode());
//		System.out.println(s2.hashCode());
//		BigDecimal bd1 = new BigDecimal("0");
//		BigDecimal bd2 = new BigDecimal("0.00");
//
//		// Using == operator (incorrect)
////		if (bd1 == bd2) {
////		    System.out.println("Values are equal"); // This will not print
////		} else {
////		    System.out.println("Values are not equal"); // This will print
////		}
////
////		// Using .equals() method (incorrect)
////		if (bd1.equals(bd2)) {
////		    System.out.println("Values are equal"); // This will not print
////		} else {
////		    System.out.println("Values are not equal"); // This will print
////		}
////
////		// Using compareTo() method (correct)
////		if (bd1.compareTo(bd2) == 0) {
////		    System.out.println("Values are equal"); // This will print
////		} else {
////		    System.out.println("Values are not equal"); // This will not print
////		}
//		Animal animal = null;            // Declaration of a reference of type Animal
//               // Assigning a Dog object to the animal reference
//        animal.makeSound();
//
//		Date today = new Date();
//		abc obj = new abc();
//		obj.fun();
		
//	}
}
