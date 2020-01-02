package core;

import java.sql.SQLException;

import databaseException.exceedSchedule;
import databaseException.noSuchHotel;
import databaseException.nomoreRoom;

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
	void resetUser(User us){
		this.user=us;
	}
	/**
	 * save this order to db
	 * @throws UserException user own this order has logout
	 * @throws SQLException 
	 * @throws nomoreRoom 
	 * @throws exceedSchedule 
	 * @throws noSuchHotel 
	 */
	public void confirm() throws UserException, noSuchHotel, exceedSchedule, nomoreRoom, SQLException {
		if(valid==true)return;//valid order should not be confirm again
		user.addOrder(this,true);//may have exception
		valid=true;
	}
	public void deleteSelf() throws Exception {
		if(valid==false)return;//invalid order should not be delete
		valid=false;
		user.deleteOrder(this);
	}
	public void editOrder( Plan newPlan ) throws Exception {
		if(valid==false)return;//can't edit invalid order in db (because it is not in db)
		Plan tmpsave=plan.clone();
		if(!tmpsave.canChangeTo(newPlan)) {
			throw new Exception("invalid change");
		}
		plan=newPlan;
		try {
			user.editOrder(this);
		} catch (Exception e) {
			plan=tmpsave;
			throw e;
		}//may have exception
		
	}
	
	public String toString() {
		String re ="Plan: "+plan.toString()
				+", User:"
				+user.toString()
				+", order id: "+id;
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
