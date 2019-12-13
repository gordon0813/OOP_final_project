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
		user=null;//User.getuser()
	}
}
