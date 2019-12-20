package core;

public class Room {
	private int roomsize;
	private long roomprice;
	
	/**
	 * @param s roomsize
	 * @param p roomprice
	 * @category create room
	 */
	public Room(int s,long p) {
		assert s>0;
		assert p>=-1;
		roomsize=s;
		roomprice=p;
		
	}
	public int getRoomsize() {
		return roomsize;
	}
	public long getRoomprice() {
		return this.roomprice;
	}
	/**
	 * @return roomprice / roomsize
	 */
	public float avgPricePerPerson() {
		return (float)roomprice/roomsize;
	}
	public String toString() {
		return "size:"+roomsize+" price:"+roomprice;
	}
}
