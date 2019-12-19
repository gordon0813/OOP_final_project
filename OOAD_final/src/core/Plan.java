package core;


public class Plan {
	private RoomNum rn;

	private CheckInOutDate ckio;
	private Hotel ht;
	public Plan(RoomNum RN,CheckInOutDate CK,Hotel HT) {
		rn=RN;   ckio=CK  ;ht=HT;
	}

	/**
	 * @return max extend Check In Out Date base on now room number
	 */
	public CheckInOutDate maxExtendDate() {
		return ht.maxExtendDate(rn, ckio);
	}
	public boolean check() {
		return (this.maxExtendDate().contain(ckio)&& this.maxExtendRoom().contain(rn));
	}
	/**
	 * @return max extend room number base on now check in out date
	 */
	public RoomNum maxExtendRoom() {
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
	 */
	public Order toOrder() {
		return new Order(clone(), false);
	}
	/**
	 * save this plan as a pagemark
	 * @throws UserException user not login
	 */
	public void Mark() throws UserException {
		User.getUser().addpageMark(this.clone());
	}
	public Plan clone(){
		return new Plan(rn.clone(),ckio.clone(),ht);
	}
	public String toString() {
		String re="=========Room num:\n"
				+rn.toString()
				+"\n=========Hotel info:\n"
				+ht.toString()
				+"\n========check in out info:\n"
				+ckio.toString()
				+"\n========total price:\n"
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
}
