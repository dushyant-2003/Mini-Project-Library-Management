package com.dushyant.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {

	@Test
	void calculateSum_ThreeMemberArray() {
		MyMath math = new MyMath();
		assertEquals(math.calculateSum(new int[] {1,2,3}),6);
	}
	@Test
	void calculateSum_ZeroMemberArray() {
		MyMath math = new MyMath();
		assertEquals(0,math.calculateSum(new int[] {}));
	}
	
}
