package core;

public class Hotel {
	private Room roomsingle;

	private Room roomdouble;

	private Room roomquad;

	private int id;
	private int star;
	private String address;
	public Hotel(int ID,int STAR ,String ADDRESS,Room r1,Room r2,Room r4) {
		assert r1.getRoomsize()==1;
		assert r2.getRoomsize()==2;
		assert r4.getRoomsize()==4;
		assert STAR >0;
		assert ADDRESS !=null;
		assert ADDRESS !="";
		assert ID>=0;
		roomsingle=r1;   roomdouble=r2;  roomquad=r4;
		id=ID;   star=STAR;   address=ADDRESS;
	}
	public long calPriceOneDay(RoomNum rn) {	
		return rn.getSingleNum()*this.roomsingle.getRoomprice()
				+rn.getDoubleNum()*this.roomdouble.getRoomprice()
				+rn.getQuadNum()*this.roomquad.getRoomprice();
	}
	public String[] loadcomments() {
		//todo db
		String[] re=null;//db.loadcomments(this->ID)  //static  
		return re;
	}
	/**
	 * @param rn number of  rooms
	 * @param ck now Check In Out Date
	 * @return max extend Check In Out Date
	 */
	public CheckInOutDate maxExtendDate(RoomNum rn,CheckInOutDate ck) {
		//todo db
		CheckInOutDate re=null;//db.maxExtendDate(this->ID,rn,ck)
		return re;
	}
	/**
	 * @param rn number of  rooms
	 * @param ck now Check In Out Date
	 * @return max extend room number
	 */
	public RoomNum maxExtendRoom(RoomNum rn,CheckInOutDate ck) {
		//todo db
		RoomNum re=null;//db.maxExtendRoom(ID,rn,ck)
		return re;
	}
	public Room getRoomsingle() {
		return roomsingle;
	}
	public Room getRoomdouble() {
		return roomdouble;
	}
	public Room getRoomquad() {
		return roomquad;
	}
}
