package core;

public class Search_input {
	int Lowstar;
	public int getLowstar() {
		return Lowstar;
	}
	public int getHighstar() {
		return highstar;
	}
	public int getHighprice() {
		return highprice;
	}
	public int getLowprice() {
		return lowprice;
	}
	public int getNumofpeople() {
		return numofpeople;
	}
	public CheckInOutDate getCk() {
		return ck;
	}
	public RoomNum getLowrn() {
		return lowrn;
	}
	public String getAddress() {
		return address;
	}
	int highstar;
	int highprice;
	int lowprice;
	int numofpeople;
	CheckInOutDate ck;
	RoomNum lowrn;
	String address;
	/**
	 * @param hs highstar  include itself
	 * @param ls Lowstar   include itself
	 * @param hp highprice
	 * @param lp lowprice
	 * @param np numofpeople
	 * @param ckio CheckInOutDate
	 * @param Lr Low room number for each room size
	 * @param address substr of hotel address 
	 */
	public Search_input(int hs,int ls,int hp,int lp,int np,CheckInOutDate ckio,RoomNum Lr,String ad) {
		assert ls>0;
		assert hs>=ls;
		assert lp>=0;
		assert hp>lp;
		assert np>0;
		//exception Lr.totalpeople<np
		
		
		Lowstar=ls; highstar=hs;  highprice=hp; lowprice=lp; numofpeople=np;
		ck=ckio ;lowrn=Lr  ;address=ad.replace("ел", "");
		
	}
	public String toString() {
		String re="Lowstar: "+Lowstar
				+" highstar"+highstar
				+" highprice:"+highprice
				+"  lowprice:"+lowprice
				+" numofpeople"+numofpeople;
		return re;
	}
	public boolean equals(Search_input si) {
		
		return (si.numofpeople==numofpeople&&
				si.Lowstar==this.Lowstar&&
				si.highstar==this.highstar&&
				si.lowrn.equals(this.lowrn)&&
				si.address.equals(this.address)&&
				si.numofpeople==this.numofpeople&&
				si.highprice==this.highprice&&
				si.lowprice==this.lowprice&&
				si.ck==this.ck
				);
		
	}
	
}
