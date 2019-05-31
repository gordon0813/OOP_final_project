public class Order {
	private int ID;
	private int HotelID;
	private String UserID;
	private String CheckInDate;
	private String CheckOutDate;
	private int[] Demand;
	private int SumPrice;
	Order() {
		ID = 0;
		UserID = "";
		HotelID = 0;
		CheckInDate = "";
		CheckOutDate = "";
		Demand = new int[3];
		SumPrice = 0;
	}
	Order(int _ID, String _UserID, int _HotelID, String _CheckInDate, String _CheckOutDate, int[] _Demand) {
		ID = _ID;
		UserID = _UserID;
		HotelID = _HotelID;
		CheckInDate = _CheckInDate;
		CheckOutDate = _CheckOutDate;
		Demand = new int[3];
		for (int i = 0; i < 3; i++)
			Demand[i] = _Demand[i];
		SumPrice = Hotel.getSingleRoomPrice() * Demand[0] 
				+ Hotel.getDoubleRoomPrice() * Demand[1] 
				+ Hotel.getQuadRoomPrice() * Demand[2];
	}
	Order(Order _Order) {
		ID = _Order.ID;
		UserID = _Order.UserID;
		HotelID = _Order.HotelID;
		CheckInDate = _Order.CheckInDate;
		CheckOutDate = _Order.CheckOutDate;
		Demand = new int[_Order.Demand.length];
		for (int i = 0; i < 3; i++)
			Demand[i] = _Order.Demand[i];
		SumPrice = Hotel.getSingleRoomPrice() * Demand[0] 
				+ Hotel.getDoubleRoomPrice() * Demand[1] 
				+ Hotel.getQuadRoomPrice() * Demand[2];
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
	int[] getDemand() {
		int[] nDemand = new int[3];
		for (int i = 0; i < 3; i++) 
			nDemand[i] = Demand[i];
		return nDemand;
	}
	int getSumPrice() {
		return SumPrice;
	}
	void setDemand(int i, int x) {
		Demand[i] -= x;
	}
}
