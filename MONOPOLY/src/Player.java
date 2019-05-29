//package Player;

public class Player {
	private String name; // player's name
	private int cash; //the money the player hold
	private Integer[] estate; //the estate the player hold
	private int estateValue; //the total value of the estate the player hold
	private int account; //cash+estateValue
	private int locat; //the location the player is at
	private boolean stop; //if the player is stop in the turn: true = go to jail (library)
	
	public Player(String Name) {
		name = Name;
		cash = 20000;
		this.estate = new Integer[20];
		for(int i = 0; i < 20; i++) estate[i] = -1;
		estateValue = 0;
		account = 0;
		locat = 0;
		stop = false;
	}
	
	public Player() {
		name = "no name";
		cash = 20000;
		this.estate = new Integer[20];
		for(int i = 0; i < 20; i++) estate[i] = -1;
		estateValue = 0;
		account = 0;
		locat = 0;
		stop = false;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public int getLocat() {
		return locat;
	}
	
	public void setLocat() {
		//decided by dice;
		if(stop) {
			stop = false;
		}
		else{
			int pre_loc = locat;
			locat = (pre_loc + (int)Math.random()*6 + 1)%20;
			if(pre_loc > locat && locat != 0)//the player plays a round, get 1500,but if the player stops at 0 get nothing
				cash += 1500;
			if(locat == 13)//if the player stops at library(estate[13]), stop for a round
				stop = true;
		}
	}
	public int getAccount() {
		return this.account;
	}
	public void setAccount() {
		this.account = this.cash + this.estateValue;
	}
	public int getEstateValue() {
		return this.estateValue;
	}
	public void setEstateValue() {

	}
	
	
}
