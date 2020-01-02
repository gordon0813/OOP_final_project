package core;
import java.sql.SQLException;
import java.util.ArrayList;


import databaseException.*;


public class User {
	private static User loginUser;
	static {
		loginUser=new User();
	}
	/**
	 * @return now user (may or may not login but won't be null) 
	 */
	public static User getUser() {
		return loginUser;
	}



	public static boolean signup(String name,String PassWord) throws SQLException, userExist {

		try {
			DB.getDB().addUser(name,PassWord);
		} catch (passwordIllegal e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @param name
	 * @param PassWord
	 * @throws Exception 
	 */
	public static void login(String name,String PassWord) throws Exception {
		//todo db
		if( User.loginUser.valid==true) {
			throw new UserException("current user has not logout");
		}
		User.loginUser=DB.getDB().getUser(name,PassWord);
		User.loginUser.connectALLorder();
	}
	/**
	 * make current user logout( not mean user=null is user.valid=false)
	 */
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
	
	public ArrayList<Order> getOrderList() {
		return orderList;
	}



	public ArrayList<Plan> getPageMark() {
		return pageMark;
	}



	public ArrayList<Search_input> getRecord() {
		return record;
	}



	public void addOrder(Order toadd,boolean save) throws UserException, noSuchHotel, exceedSchedule, nomoreRoom, SQLException {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		if(save) {
			DB.getDB().addOrder(toadd, name);
			}
		orderList.add(toadd);
	}
	public void deleteOrder(Order todelete) throws Exception {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		DB.getDB().deleteOrder(todelete.getId());
		orderList.remove(todelete);
		
	}
	public void editOrder(Order afterEdit) throws Exception {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		DB.getDB().editOrder(afterEdit,this.name);
		orderList.remove(afterEdit);
		orderList.add(afterEdit);
		
	}
	/**
	 * auto trigger when Using Hotel.search()
	 * will access db
	 * @param si 
	 * @throws SQLException 
	 */
	public void addRecord(Search_input si,boolean save) throws SQLException {
		if(!valid) {
			return ;
		}
		if(save) {
			if(this.record.size()!=0 && this.record.get(this.record.size()-1).equals(si))return;
			DB.getDB().addSearch(si, name);
			//db.addRecord(this.id,si);
		}
		record.add(si);
		
	}
	/**
	 * use by GUI
	 * @param pl plan that will become pageMark
	 * @throws UserException 
	 * @throws SQLException 
	 */
	public void addpageMark(Plan pl,boolean save) throws UserException, SQLException {
		if(!valid) {
			throw new UserException("User has not login can't add pagemark");
		}
		
		if(save) {
			DB.getDB().addPlan(pl, name);
		}
		pageMark.add(pl);
	}
	public void deleteMark(Plan p) throws noSuchPlan, SQLException, UserException {
		if(!valid) {
			throw new UserException("User has not login can't delete mark");
		}
		DB.getDB().deletePlan(p.getid());
		this.pageMark.remove(p);
	}
	public boolean exitOrder(Hotel ht) throws UserException {
		if(!valid) {
			throw new UserException("User has not login can't leave comment");
		}
		for(Order od:orderList) {
			if(od.getHotel().equals(ht)) {
				return true;
			}
		}
		return false;
	}
	public void editEmail(String email) throws SQLException, UserException {
		if(!valid) {
			throw new UserException("User has not login can't edit email");
		}
		DB.getDB().editUsermail(this.name, email);
	}
	public void editpassword(String userInputOldPassword ,String newpassword) throws UserException, noSuchUser, passwordWrong, userExist, passwordIllegal, SQLException {
		if(!valid) {
			throw new UserException("User has not login can't edit email");
		}
		DB.getDB().editUserpassword(this.name, userInputOldPassword, newpassword);
		
	}
	public boolean islogin() {
		return valid;
	}

	public String toString() {
		String re="\nname: "+name+"\npassword: "+password
				+"\nlogin: "+valid;
		
		return re;
	}
	public String getname() {
		return name;
	}
	public String toStringAll() {
		String re="\nname: "+name+"\npassword: "+password
				+"\nlogin: "+valid;
		re+="\n=====================User:order=========num: "+orderList.size()+" ==============\n";
		for(Order i:orderList){
			re+=i.toString();
		}
		re+="\n=====================User:Search_input====num: "+record.size()+"===================\n";
		for(Search_input i:record) {
			re+="\n"+i.toString();
		}
		re+="\n=====================User:Plan===num: "+pageMark.size()+" ====================\n";
		for(Plan i:pageMark) {
			re+="\n"+i.toString();
		}
		re+="\n";
		return re;
	}
	private void connectALLorder() {
		for(int i=0;i<this.orderList.size();i++) {
			this.orderList.get(i).resetUser(this);
		}
	}
	public void clearRecord() throws SQLException {
		DB.getDB().deleteSearch(this.name);
	}
	
}
