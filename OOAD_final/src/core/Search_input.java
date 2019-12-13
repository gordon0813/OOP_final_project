package core;

public class Search_input {
	int Lowstar;
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
		ck=ckio ;lowrn=Lr  ;address=ad;
		
	}
	
}
