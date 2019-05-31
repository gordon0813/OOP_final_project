import java.util.ArrayList;

public class User {
	private String UserID;
	private String Password;
	private ArrayList<Order> Orders;
	private int Num;
	public User() {
		UserID = "";
		Password = "";
		Num = 0;
	}
	public User(String _UserID, String _Password) {
		UserID = _UserID;
		Password = _Password;
		Num = 0;
	}
	public String getUserID() {
		return UserID;
	}
	public String getPassword() {
		return Password;
	}
	public ArrayList<Order> getOrders() {
		return Orders;
	}
	public int getNum() {
		return Num;
	}
	public boolean equals(String _UserID) {
		return UserID == _UserID;
	}
	public int getnextOrderID() {
		return Orders.size();
	}
	public void newOrder(Order _Order) {
		Orders.add(_Order);
	}
}
