package core;

public class RoomNum {
	int singleNum;
	
	int doubleNum;
	
	int quadNum;
	
	/**
	 * @param s singleNum
	 * @param d doubleNum
	 * @param q quadNum
	 */
	public RoomNum(int s,int d,int q) {
		assert s>=0;
		assert d>=0;
		assert q>=0;
		singleNum=s;
		doubleNum=d;
		quadNum =q;
	}
	public int totalPeople() {
		return singleNum+2*doubleNum+4*quadNum;
	}
	public RoomNum clone()throws CloneNotSupportedException{
		return new RoomNum(singleNum,doubleNum,quadNum);
	}
	public int getSingleNum() {
		return singleNum;
	}
	public void setSingleNum(int singleNum) {
		assert singleNum>=0;
		this.singleNum = singleNum;
	}
	
	public int getDoubleNum() {
		return doubleNum;
	}
	public void setDoubleNum(int doubleNum) {
		assert doubleNum>=0;
		this.doubleNum = doubleNum;
	}
	public int getQuadNum() {
		return quadNum;
	}
	public void setQuadNum(int quadNum) {
		assert quadNum>=0;
		this.quadNum = quadNum;
	}
}
