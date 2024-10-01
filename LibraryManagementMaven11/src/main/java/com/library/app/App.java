package com.library.app;

import java.sql.SQLException;

import com.library.constants.StringConstants;
import com.library.ui.MenuUI;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		MenuUI menuUI = new MenuUI();
		menuUI.displayLoginMenu();
	}
}
