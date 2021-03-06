package core;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
public class CheckInOutDate implements Cloneable{
	LocalDate checkin;
	
	LocalDate checkout;
	
	public CheckInOutDate(LocalDate from,LocalDate to) {
		assert ChronoUnit.DAYS.between(from, to)>0;
		checkin=from;
		checkout=to;
	}
	/**
	 * @return days between check in date and check out date
	 */
	public long howManyDays() {
		long days=ChronoUnit.DAYS.between(checkin, checkout);
		assert days>0;
		return days;
	}
	/**
	 * @param ckio
	 * @return this check in out date contain input or not 
	 */
	public boolean contain(CheckInOutDate ckio) {
		
		return (ckio.checkin.compareTo(checkin)>=0 &&  ckio.checkout.compareTo(checkout)<=0 ) ;
	}
	public boolean equals(CheckInOutDate ckio) {
		return ckio.checkin.isEqual(this.checkin) && ckio.checkout.isEqual(this.checkout);
	}
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "checkin: "+checkin.format(formatter)+"\ncheckout: "+checkout.format(formatter)
		+"\ndays between: "+howManyDays();
		
	}
	public CheckInOutDate clone(){
		return new CheckInOutDate(checkin,checkout);
	}
	
	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	
}
