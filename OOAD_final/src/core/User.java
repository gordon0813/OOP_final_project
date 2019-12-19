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
	public static boolean signup(String name,String PassWord) {
		//db.newUser(id,passWord)
		return false;
	}
	public static void login(String name,String PassWord) throws UserException {
		//todo db
		if( User.loginUser.valid==true) {
			throw new UserException("current user has not logout");
		}
		User.loginUser=new User(name,PassWord);//db.getUser(id,password)
	}
	public static void logout() {
		assert User.loginUser.valid==true;
		User.loginUser.valid=false;
	}
	
	
	private int id;
	private String name;
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
	public User(String name,String password) {
		valid=true;
		this.name=name;
		this.password=password;
		orderList=new ArrayList<Order>();
		pageMark =new ArrayList<Plan> ();
		record=new ArrayList<Search_input>();
		
	}
	public void addOrder(Order toadd) throws UserException {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		//db.addOrder(this->id,toadd);
		orderList.add(toadd);
		
	}
	public void deleteOrder(Order todelete) throws UserException {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		//db.deleteOrder(this->id,todelete)
		orderList.remove(todelete);
		
	}
	public void editOrder(Order afterEdit) throws UserException {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		//db.editOrder(this->id,afterEdit);
		orderList.remove(afterEdit);
		orderList.add(afterEdit);
		
	}
	public void addRecord(Search_input si) {
		if(!valid) {
			return ;
		}
		record.add(si);
	}
	public void addpageMark(Plan pl) throws UserException {
		if(!valid) {
			throw new UserException("User not login can't add pagemark");
		}
		pageMark.add(pl);
	}

	public String toString() {
		String re="id: "+id+"\npassword: "+password
				+"\nlogin: "+valid;
		
		return re;
	}
	public String toStringAll() {
		String re="\nid: "+id+"\npassword: "+password
				+"\nlogin: "+valid;
		for(Order i:orderList){
			re+=i.toString();
		}
		for(Search_input i:record) {
			re+="\n"+i.toString();
		}
		re+="\n";
		return re;
	}
	
}
