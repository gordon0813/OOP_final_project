package core;

import java.util.ArrayList;

public class User {
	private static User loginUser;
	public static User getUser() {
		return loginUser;
	}
	public static void login(int ID,String PassWord) {
		
	}
	public static void logout() {
		
	}
	private int id;
	private String password;
	private boolean valid;
	private ArrayList<Order> orderList;
	private ArrayList<Plan> pageMark;
	private ArrayList<Search_input> record;
	private User(int ID,String PassWord) {
		
	}
	
}
