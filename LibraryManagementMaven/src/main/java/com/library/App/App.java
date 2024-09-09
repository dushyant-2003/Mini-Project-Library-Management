package com.library.App;

import java.sql.SQLException;

import com.library.Constants.StringConstants;
import com.library.ui.MenuUI;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		System.out.println(StringConstants.WELCOME);
		MenuUI menuUI = new MenuUI();
		menuUI.displayLoginMenu();
	}
}
