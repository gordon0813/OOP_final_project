public class Order {
	private int ID;
	private int HotelID;
	private String UserID;
	private String CheckInDate;
	private String CheckOutDate;
	private int sn, dn, qn;
	private int SumPrice;
	Order() {
		ID = -1;
		UserID = "";
		HotelID = 0;
		CheckInDate = "";
		CheckOutDate = "";
		sn = dn = qn = 0;
		SumPrice = 0;
	}
	Order(int _ID, String _UserID, int _HotelID, String _CheckInDate, String _CheckOutDate, int _sn, int _dn, int _qn) {
		ID = _ID;
		UserID = _UserID;
		HotelID = _HotelID;
		CheckInDate = _CheckInDate;
		CheckOutDate = _CheckOutDate;
		sn = _sn; dn = _dn; qn = _qn;
		SumPrice = Hotel.getSingleRoomPrice() * sn 
				+ Hotel.getDoubleRoomPrice() * dn 
				+ Hotel.getQuadRoomPrice() * qn;
	}
	Order(Order _Order) {
		ID = _Order.ID;
		UserID = _Order.UserID;
		HotelID = _Order.HotelID;
		CheckInDate = _Order.CheckInDate;
		CheckOutDate = _Order.CheckOutDate;
		sn = _Order.sn; dn = _Order.dn; qn = _Order.qn;
		SumPrice = Hotel.getSingleRoomPrice() * sn 
				+ Hotel.getDoubleRoomPrice() * dn 
				+ Hotel.getQuadRoomPrice() * qn;
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
		return sn;
	}
	int getdn() {
		return dn;
	}
	int getqn() {
		return qn;
	}
	int getSumPrice() {
		return SumPrice;
	}
	void setsn(int x) {
		sn -= x;
	}
	void setdn(int x) {
		dn -= x;
	}
	void setqn(int x) {
		qn -= x;
	}
}
