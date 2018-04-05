package com.ump;

import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.getProperty("webapp.root");
		ResourceBundle rb = ResourceBundle.getBundle("config/messages/message");
		String name = rb.getString("name");
		System.out.println(name);
	}
}
