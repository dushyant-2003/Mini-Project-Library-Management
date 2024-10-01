package com.library.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.library.constants.StringConstants;

public class InputIndex {
	private static Scanner scanner = new Scanner(System.in);
	public static int inputIndex(int limit) {
		int index =0;
		boolean validateIndex = false;
		while (!validateIndex) {
			try {
				index  = scanner.nextInt();
				if (UserValidator.isValidIndex(index , limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INDEX);
				scanner.next();
			}
		}
		return index ;
	}
}
