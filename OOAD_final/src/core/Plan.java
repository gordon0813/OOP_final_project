package core;

import java.sql.SQLException;

import databaseException.exceedSchedule;
import databaseException.noSuchHotel;
import databaseException.noSuchPlan;

public class Plan {
	private int id;
	private RoomNum rn;

	private CheckInOutDate ckio;
	private Hotel ht;
	public Plan(RoomNum RN,CheckInOutDate CK,Hotel HT) {
		rn=RN;   ckio=CK  ;ht=HT;  
		id=0;
	}
	public Plan(RoomNum RN,CheckInOutDate CK,Hotel HT ,int ID) {
		rn=RN;   ckio=CK  ;ht=HT;  
		id=ID;
	}

	/**
	 * @return max extend Check In Out Date base on now room number
	 * @throws SQLException 
	 * @throws exceedSchedule 
	 * @throws noSuchHotel 
	 */
	public CheckInOutDate maxExtendDate() throws noSuchHotel, exceedSchedule, SQLException {
		return ht.maxExtendDate(rn, ckio);
	}
	public boolean check() throws noSuchHotel, exceedSchedule, SQLException {
		return (this.maxExtendDate().contain(ckio)&& this.maxExtendRoom().contain(rn));
	}
	/**
	 * @return max extend room number base on now check in out date
	 * @throws SQLException 
	 * @throws exceedSchedule 
	 * @throws noSuchHotel 
	 */
	public RoomNum maxExtendRoom() throws noSuchHotel, exceedSchedule, SQLException {
		return ht.maxExtendRoom(rn, ckio);
	}
	public long calTotalPrice() {
		long days=ckio.howManyDays();
		long onedayPrice=ht.calPriceOneDay(rn);
		assert days>0;
		assert onedayPrice>0;
		return days*onedayPrice;
	}
	/**
	 * create an order by this plan
	 * @return order
	 * @throws SQLException 
	 */
	public Order toOrder() throws SQLException {
		return new Order(clone(), false);
	}
	/**
	 * save this plan as a pagemark
	 * @throws UserException user not login
	 * @throws SQLException 
	 */
	public void Mark() throws UserException, SQLException {
		Plan mark=this.clone();
		if(mark.id!=0)return;
		mark.id=DB.getDB().currentPlanid();
		User.getUser().addpageMark(mark,true);
	}
	public void unMark() throws noSuchPlan, SQLException, UserException {
		assert(id!=0);
		User.getUser().deleteMark(this);
	}
	public Plan clone(){
		return new Plan(rn.clone(),ckio.clone(),ht);
	}
	public String toString() {
		String re="Planid: "+id
				+rn.toString()
				+", 飯店資訊:"
				+ht.toString()
				+", 日期:"
				+ckio.toString()
				+", 總價格:"
				+ht.calPriceOneDay(rn)*ckio.howManyDays()+"\n";
		return re;
	}
	public CheckInOutDate getCheckInOutDate() {
		return ckio;
	}
	public void setCheckInOutDate(CheckInOutDate ckio) {
		this.ckio = ckio;
	}
	public RoomNum getRoomNum() {
		return rn;
	}
	public void setRoomNum(RoomNum rn) {
		this.rn = rn;
	}
	public Hotel getHotel() {
		return ht;
	}
	public int  getid() {
		return this.id;
	}
	public boolean canChangeTo(Plan newplan) throws noSuchHotel, exceedSchedule, SQLException {
		assert newplan !=null;
		CheckInOutDate limitdate=this.maxExtendDate();
		assert limitdate!=null;
		RoomNum limitRn=this.maxExtendRoom();
		assert limitRn!=null;
		boolean a1= limitdate.contain(newplan.getCheckInOutDate());
		boolean a2=limitRn.contain(newplan.getRoomNum());
		return a1&&a2 ;
	}
}
