package core;

public class HotelList {
	
	static HotelList onlyList=new HotelList();
	static Plan[] search(Search_input si) {
		return  null;
	}
	static Hotel getHotel(int id) {
		return null;
	}
	
	Hotel[] hotelList;
	
	private HotelList() {
		//todo db
		int hotelnum=0;//db.getHotelnum();
		hotelList=new Hotel[0];
		for (int i=0;i<hotelnum;i++) {
			hotelList[i]=null;//db.getHotel(i)
		}
	}
}
