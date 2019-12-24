package core;

import java.sql.SQLException;

public class Order {
	private boolean valid;
	private long id;


	private User user;
	private Plan plan;
	public Order(Plan p,boolean val) throws SQLException {
		//todo db
		plan=p;   valid=val; 
		id=DB.getDB().currentOrderid();
		user=User.getUser();
	}
	public Order(Plan p,boolean val,long ID) throws SQLException {
		//todo db
		plan=p;   valid=val; 
		id=ID;
		user=User.getUser();
	}
	
	/**
	 * save this order to db
	 * @throws UserException user own this order has logout
	 */
	public void confirm() throws UserException {
		if(valid==true)return;//valid order should not be confirm again
		user.addOrder(this,true);//may have exception
		valid=true;
	}
	public void deleteSelf() throws UserException {
		if(valid==false)return;//invalid order should not be delete
		valid=false;
		user.deleteOrder(this);
	}
	public void editOrder( Plan newPlan ) throws UserException {
		if(valid==false)return;//can't edit invalid order in db (because it is not in db)
		
		user.editOrder(this);//may have exception
		
		plan=newPlan;
	}
	public String toString() {
		String re ="\n=======================order:\n"
				+"===============plan:\n"
				+plan.toString()
				+"\n=============User:\n"
				+user.toString()
				+"\norder id: "+id;
		return re;
	}
	public boolean equals(Order other) {
		if(this.id==other.id)return true;
		return false;
	}
	public long getId() {
		return id;
	}
	public Plan getPlan() {
		return plan.clone();
	}
	public Hotel getHotel() {
		return plan.getHotel();
	}
	
}
