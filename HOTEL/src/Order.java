import java.util.ArrayList;

public class Order {
	private int ID;
	private int HotelID;
	private String UserID;
	private String CheckInDate;
	private String CheckOutDate;
	private ArrayList<Integer> Snum, Dnum, Qnum;
	private int SumPrice;
	Order() {
		ID = -1;
		UserID = "";
		HotelID = 0;
		CheckInDate = "";
		CheckOutDate = "";
		SumPrice = 0;
		Snum = Dnum = Qnum = null;
	}
	Order(int _ID, String _UserID, int _HotelID, String _CheckInDate, String _CheckOutDate, ArrayList<Integer> _Snum, ArrayList<Integer> _Dnum, ArrayList<Integer> _Qnum) {
		ID = _ID;
		UserID = _UserID;
		HotelID = _HotelID;
		CheckInDate = _CheckInDate;
		CheckOutDate = _CheckOutDate;
		Snum = new ArrayList<Integer> (); Snum.addAll(_Snum);
		Dnum = new ArrayList<Integer> (); Dnum.addAll(_Dnum);
		Qnum = new ArrayList<Integer> (); Qnum.addAll(_Qnum);
		SumPrice = Hotel.getSingleRoomPrice() * Snum.size()
				+ Hotel.getDoubleRoomPrice() * Dnum.size() 
				+ Hotel.getQuadRoomPrice() * Qnum.size();
	}
	Order(Order _Order) {
		ID = _Order.ID;
		UserID = _Order.UserID;
		HotelID = _Order.HotelID;
		CheckInDate = _Order.CheckInDate;
		CheckOutDate = _Order.CheckOutDate;
		Snum = new ArrayList<Integer> (); Snum.addAll(_Order.Snum);
		Dnum = new ArrayList<Integer> (); Dnum.addAll(_Order.Dnum);
		Qnum = new ArrayList<Integer> (); Qnum.addAll(_Order.Qnum);
		SumPrice = Hotel.getSingleRoomPrice() * Snum.size()
				+ Hotel.getDoubleRoomPrice() * Dnum.size() 
				+ Hotel.getQuadRoomPrice() * Qnum.size();
	}
	int getID() {
		return ID;
	}
	String getUserID() {
		return UserID;
	}
	int getHotelID() {
		return HotelID;
	}
	String getCheckInDate() {
		return CheckInDate;
	}
	String getCheckOutDate() {
		return CheckOutDate;
	}
	int getsn() {
		return Snum.size();
	}
	int getdn() {
		return Dnum.size();
	}
	int getqn() {
		return Qnum.size();
	}
	int getSumPrice() {
		return SumPrice;
	}
	
}
