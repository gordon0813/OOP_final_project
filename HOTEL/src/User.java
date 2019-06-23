import java.util.ArrayList;

public class User {
	private String UserID;
	private String Password;
	//private ArrayList<Order> Orders;
	public User() {
		UserID = "";
		Password = "";
		//Orders = new ArrayList<Order>();
	}
	public User(String _UserID, String _Password) {
		UserID = _UserID;
		Password = _Password;
		//Orders = new ArrayList<Order>();
	}
	public String getUserID() {
		return UserID;
	}
	public String getPassword() {
		return Password;
	}
	/*public ArrayList<Order> getOrders() {
		return Orders;
	}*/
	public boolean equals(String _UserID) {
		return UserID == _UserID;
	}
	/*public int getnextOrderID() {
		return Orders.size();
	}
	public void newOrder(Order _Order) {
		Orders.add(_Order);
	}*/
}
