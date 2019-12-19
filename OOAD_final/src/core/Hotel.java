package core;

import java.util.ArrayList;

public class Hotel {
	private static Hotel[] hotelList;
	static {
		int hotelnum=0;//db.getHotelnum();
		hotelList=new Hotel[hotelnum];
		for (int i=0;i<hotelnum;i++) {
			hotelList[i]=null;//db.getHotel(i)
		}
	}
	public static Plan[] search(Search_input si) {
		User.getUser().addRecord(si);
		return null;
	}
	public static Hotel getHotel(int ID) {
		return null;
	}
	 static ArrayList<RoomNum> roomset(int numOfPeople,RoomNum minRoomNum){
		ArrayList<RoomNum> numarr=new ArrayList<RoomNum>();
		RoomNum tmp;
		int leftAfterq=0;
		int leftAfterd=0;
		for (int i=0;i<=numOfPeople/4;i++) {
			leftAfterq=numOfPeople-4*i;
			for(int j=0;j<=leftAfterq/4;j++) {
				leftAfterd=leftAfterq-2*j;
				tmp=new RoomNum(leftAfterd, j, i);
				if(tmp.contain(minRoomNum))numarr.add(tmp);
			}		
		}
		return numarr;
	}
	
	private Room roomsingle;

	private Room roomdouble;

	private Room roomquad;

	private int id;

	private int star;
	
	private String address;

	/**
	 * @param ID
	 * @param STAR
	 * @param ADDRESS
	 * @param r1
	 * @param r2
	 * @param r4
	 */
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
	public void addcomments(String comment) throws UserException {
		if(User.getUser().exitOrder(this)){
			return ;//db.addComment(this,User.getUser,comment)
		}else {
			throw new UserException("User must book this hotel before leave a comment");
		}
		
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
	public String toString() {
		String re="ID: "+id+"\nStar: "+star+"\naddress: "+address+"\n"
				+this.roomsingle.toString()+"\n"
		        +this.roomdouble.toString()+"\n"
		        +this.roomquad.toString();
		return re;
	}
	public boolean equals(Hotel ht) {
		return this.id==ht.id;
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
	public int getId() {
		return id;
	}
	public int getStar() {
		return star;
	}
	public String getAddress() {
		return address;
	}

	
}
