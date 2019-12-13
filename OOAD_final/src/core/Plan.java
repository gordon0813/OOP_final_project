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
	public Plan clone()throws CloneNotSupportedException{
		return new Plan(rn.clone(),ckio.clone(),ht);
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
