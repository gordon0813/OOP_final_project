package core;

public class Order {
	private boolean valid;
	private long id;


	private User user;
	private Plan plan;
	public Order(Plan p,boolean val) {
		//todo db
		plan=p;   valid=val; 
		id=0;//db.getlastid()
		user=User.getUser();
	}
	public void confirm() throws UserException {
		if(valid==true)return;
		if(!plan.check()) {
			return ;//exception
		}
		valid=true;
		user.addOrder(this);
	}
	public void deleteSelf() throws UserException {
		valid=false;
		user.deleteOrder(this);
	}
	public void editOrder( Plan newPlan ) throws UserException {
		if(valid==false)return;//exception
		plan=newPlan;
		user.editOrder(this);
	}
	public String toString() {
		String re ="===============plan:\n"
				+plan.toString()
				+"\n================User:\n"
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
	
}
