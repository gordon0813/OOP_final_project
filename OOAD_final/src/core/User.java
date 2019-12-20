package core;
import java.util.ArrayList;

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
	public static boolean signup(String name,String PassWord) {
		//db.newUser(id,passWord)
		return false;
	}
	/**
	 * @param name
	 * @param PassWord
	 * @throws UserException
	 */
	public static void login(String name,String PassWord) throws UserException {
		//todo db
		if( User.loginUser.valid==true) {
			throw new UserException("current user has not logout");
		}
		User.loginUser=new User(name,PassWord);//db.getUser(id,password)
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
	public void addOrder(Order toadd,boolean save) throws UserException {
		if(!valid) {
			throw new UserException("User who own this order not login");
		}
		if(save) {
			//DB.getDB().addOrder(this->id,toadd);
			}
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
	/**
	 * auto trigger when Using Hotel.search()
	 * will access db
	 * @param si 
	 */
	public void addRecord(Search_input si,boolean save) {
		if(!valid) {
			return ;
		}
		record.add(si);
		if(save) {
			//db.addRecord(this.id,si);
		}
		
	}
	/**
	 * use by GUI
	 * @param pl plan that will become pageMark
	 * @throws UserException 
	 */
	public void addpageMark(Plan pl,boolean save) throws UserException {
		if(!valid) {
			throw new UserException("User has not login can't add pagemark");
		}
		pageMark.add(pl);
		if(save) {
		
		}
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
	public boolean islogin() {
		return valid;
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
