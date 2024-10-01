package com.dushyant.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {
	
	List<String> todos = Arrays.asList("AWS","Azure","DevOps");
	
	@Test
	void test() {
		boolean test = todos.contains("AS");
//		assertEquals(true,todos); no
		
		assertTrue(test);
		// assertFalse()
		// assertNull
		// assertNotNull
		// assertArrayEquals(new int[] {1,2},new int[] {3,4})
		
	}

}
