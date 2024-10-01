package com.dushyant.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

	@Test
	void testCorrect() {
		PasswordValidator validator = new PasswordValidator();
		assertTrue(validator.isValidPassword("db@2003A"));
	}
	@Test
	void testWithoutUpperCase() {
		PasswordValidator validator = new PasswordValidator();
		assertFalse(validator.isValidPassword("db@2003a"));
	}
	@Test
	void testWithoutDigit() {
		PasswordValidator validator = new PasswordValidator();
		assertFalse(validator.isValidPassword("db@deofofA"));
	}
	@Test
	void testWithoutSpecialCharacter() {
		PasswordValidator validator = new PasswordValidator();
		assertFalse(validator.isValidPassword("db2003Add"));
	}
	@Test
	void testWithShortLength() {
		PasswordValidator validator = new PasswordValidator();
		assertFalse(validator.isValidPassword("Db@2003"));
	}

}
