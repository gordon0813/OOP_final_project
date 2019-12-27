package core;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseException.exceedSchedule;
import databaseException.noSuchHotel;

/**
 * read only class
 * @author gordon
 *
 */
public class Hotel {
	private static Hotel[] hotelList;
	static {
		int hotelnum=1500;
		hotelList=new Hotel[hotelnum];
		for (int i=0;i<hotelnum;i++) {
			try {
				hotelList[i]=DB.getDB().getHotel(i);
			} catch (noSuchHotel | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Plan[] search(Search_input si) throws SQLException {
		assert si!=null;
		User.getUser().addRecord(si,true);
		ArrayList<RoomNum> matchroomset=roomset(si.numofpeople,si.lowrn);
		System.out.println(matchroomset.size());
		ArrayList<Hotel> matchHotel=matchHotel(si);
		System.out.println(matchHotel.size());
		ArrayList<Plan> matchPlan=new ArrayList<Plan>();
		RoomNum zerorn=new RoomNum(0,0,0);
		//for(RoomNum r:matchroomset)System.out.println(r);
		
 		for(Hotel ht:matchHotel) {
			boolean needacessdb=false;
			ArrayList<RoomNum> matchPriceRnset =new ArrayList<RoomNum>(10);
			for(RoomNum rn:matchroomset) {
				long price=ht.calPriceOneDay(rn);
				if(price>si.lowprice && price<si.highprice) {
					matchPriceRnset.add(rn);
				}
			}
			if(matchPriceRnset.size()!=0) {	
				RoomNum maxrn;
				try {
					maxrn = ht.maxExtendRoom(zerorn, si.ck);
					
					for (RoomNum mrn:matchPriceRnset) {
						if(maxrn.contain(mrn)) {
							matchPlan.add(new Plan(mrn.clone(), si.ck.clone(), ht));
						}
					}
				} catch (noSuchHotel e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (exceedSchedule e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
 		Plan[] p = new Plan[0];
		return  matchPlan.toArray(p);
	}
	public static Hotel getHotel(int ID) {
		return hotelList[ID];
	}
	static ArrayList<Hotel> matchHotel(Search_input si){
		ArrayList<Hotel> re=new ArrayList<Hotel>();
		for(Hotel i:hotelList) {
			if(i.star>=si.Lowstar && i.star<=si.highstar) {
				re.add(i);
			}
		}
		ArrayList<Hotel> renew=new ArrayList<Hotel>();
		if(si.address!=null && !si.address.isEmpty()) {
			
			for(int i=0;i<re.size();i++) {
				if(re.get(i).address.contains(si.address)) {
					System.out.println(si.address+" "+re.get(i).address);
					renew.add(re.get(i));
				}
			}
		}
		return renew;
		
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
	public String[] loadcomments() throws noSuchHotel, SQLException {
		//todo db
		ArrayList<String> re=DB.getDB().loadComments(id);  //static  
		String[] tmp=new String[0];
		return re.toArray(tmp);
	}
	/**
	 * @param comment
	 * @throws UserException  be handle as error page
	 * @throws noSuchHotel this is bug need to be fix
	 * @throws SQLException this is bug need to be fix
	 */
	public void addcomments(String comment) throws UserException, noSuchHotel, SQLException {
		if(User.getUser().exitOrder(this)){
			DB.getDB().addComment(id, comment+" /"+User.getUser().getname());
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
	public String googlemapURL() {
		return "https://www.google.com.tw/maps/place/"+address;
	}
	/**
	 * @param rn number of  rooms
	 * @param ck now Check In Out Date
	 * @return max extend room number
	 * @throws SQLException
	 * @throws exceedSchedule
	 * @throws noSuchHotel
	 */
	public RoomNum maxExtendRoom(RoomNum rn,CheckInOutDate ck) throws noSuchHotel, exceedSchedule, SQLException {
		//todo db
		RoomNum re=DB.getDB().extendRoom(this.id,rn,ck);
		return re;
	}
	public String toString() {
		String re="ID: "+id+" Star: "+star+" Address: "+address+" "
				+this.roomsingle.toString()+" "
		        +this.roomdouble.toString()+" "
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
