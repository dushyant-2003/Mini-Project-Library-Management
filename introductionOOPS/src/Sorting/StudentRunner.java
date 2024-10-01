package Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> students = List.of(new Student(1,"Ranga"),new Student(100,"Adam"),new Student(2,"Eve"));
		List<Student> studentsAl = new ArrayList<>(students);
		Collections.sort(studentsAl);
		System.out.println(studentsAl);
		
	
	}

}
