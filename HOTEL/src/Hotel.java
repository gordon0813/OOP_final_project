
public class Hotel {
	private int ID;
	private int Star;
	private String Locality;
	private String Address;
	public class Room {
		private String Type;
		private int Price;
		private int Number;
		private boolean[] DateIsOccupied;
		private Room() {
			Type = "Single";
			Price = 0;
			Number = 0;
			DateIsOccupied = new boolean[190];
		}
		private Room(String _Type, int _Price, int _Number) {
			Type = _Type;
			Price = _Price;
			Number = _Number;
			DateIsOccupied = new boolean[190];
		}	
	}
	private class Order {
		private int ID;
		private int CheckInDate;
		private int CheckOutDate;
		private Room[] Rooms;
		private int SumPrice;
		private Order() {
			ID = 0;
			CheckInDate = 0;
			CheckOutDate = 0;
			Rooms = new Room[1];
			SumPrice = 0;
		}
		private Order(int _ID, int _CheckInDate, int _CheckOutDate, Room[] _Rooms) {
			ID = _ID;
			CheckInDate = _CheckInDate;
			CheckOutDate = _CheckOutDate;
			for (int i = 0; i < _Rooms.length; i++)
				Rooms[i] = _Rooms[i];
			SumPrice = 0;
			for (int i = 0; i < Rooms.length; i++)
				SumPrice += Rooms[i].Price;
		}
	}
	
	public Hotel() {
		ID = 0;
		Star = 0;
		Locality = "";
		Address = "";
	}
	public Hotel(int _ID, int _Star, String _Locality, String _Address) {
		ID = _ID;
		Star = _Star;
		Locality = _Locality;
		Address = _Address;
	}
}
