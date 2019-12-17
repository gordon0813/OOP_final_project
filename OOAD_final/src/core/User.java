package core;

import java.util.ArrayList;

public class User {
	private static User loginUser;
	static {
		loginUser=new User();
	}
	public static User getUser() {
		return loginUser;
	}
	public static boolean signup(int ID,String PassWord) {
		return false;
	}
	public static void login(int ID,String PassWord) {
		//todo db
		assert User.loginUser.valid==false;//expection
		User.loginUser=new User();//db.getUser(id,password)
	}
	public static void logout() {
		assert User.loginUser.valid==true;
		User.loginUser.valid=false;
	}
	
	
	private int id;
	private String password;
	private boolean valid;
	private ArrayList<Order> orderList;
	private ArrayList<Plan> pageMark;
	private ArrayList<Search_input> record;
	public User() {
		valid=false;
		id=-1;
		password="default";
		
	}
	public void addOrder(Order toadd) {
		if(!valid)return;//exception
		orderList.add(toadd);
		//db.addOrder(this->id,toadd);
	}
	public void deleteOrder(Order todelete) {
		if(!valid)return;
		orderList.remove(todelete);
		//db.deleteOrder(this->id,todelete)
	}
	public void editOrder(Order afterEdit) {
		if(!valid)return;
		orderList.remove(afterEdit);
		orderList.add(afterEdit);
		//db.editOrder(this->id,aftrEdit);
	}
	public void addRecord(Search_input si) {
		if(!valid)return;
		record.add(si);
	}
	public void addpageMark(Plan pl) {
		if(!valid)return;
		pageMark.add(pl);
	}

	public String toString() {
		String re="id: "+id+"\npassword: "+password
				+"\nlogin: "+valid;
		return re;
	}
	
}
