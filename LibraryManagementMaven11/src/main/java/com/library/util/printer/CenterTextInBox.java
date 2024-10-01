package com.library.util.printer;

public class CenterTextInBox {
	public static String centerTextInBox(String BOX_BORDER, String text) {
		int boxWidth = BOX_BORDER.length();
		int textLength = text.length();
		int padding = (boxWidth - textLength) / 2;
 
		
		StringBuilder centeredText = new StringBuilder();
		centeredText.append(" ".repeat(padding));
		centeredText.append(text);
		centeredText.append(" ".repeat(padding));
 
		while (centeredText.length() < boxWidth) {
			centeredText.append(" ");
		}
 
		return centeredText.toString();
	}
}
