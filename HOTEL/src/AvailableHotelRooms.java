
public class AvailableHotelRooms {
	private int HotelID;
	private int HotelStar;
	private int[] AvailableRoomsNumber;

	public AvailableHotelRooms() {
		HotelID = 0;
		HotelStar = 0;
		AvailableRoomsNumber = new int[3];
	}
	public AvailableHotelRooms(int _HotelID, int _HotelStar, int[] _AvailableRoomsNumber) {
		HotelID = _HotelID;
		HotelStar = _HotelStar;
		AvailableRoomsNumber = new int[3];
		for (int i = 0; i < 3; i++)
			AvailableRoomsNumber[i] = _AvailableRoomsNumber[i];
	}
	public void setAvailabelRoomsNumber(int i, int x) {
		AvailableRoomsNumber[i] = x;
	}
}
