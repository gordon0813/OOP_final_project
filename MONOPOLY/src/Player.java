/**
 * This is the class of the Player class for setting and getting Player info and position.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */
public class Player {
	private String name; // player's name
	private int cash; //the money the player hold
	private Integer[] estate; //the estate the player hold
	private int estateValue; //the total value of the estate the player hold
	private int account; //cash+estateValue
	private int locat; //the location the player is at
	public boolean stop; //if the player is stop in the turn: true = go to jail (library)
	
	/**
	 * constructor of Player
	 * 
	 * @param Name player's name
	 */
	public Player(String Name) {
		name = Name;
		cash = 5000;
		this.estate = new Integer[20];
		for(int i = 0; i < 20; i++) estate[i] = -1;
		estateValue = 0;
		account = 0;
		locat = 0;
		stop = false;
	}
	
	/**
	 * default constructor of Player
	 * 
	 */
	public Player() {
		name = "no name";
		cash = 5000;
		this.estate = new Integer[20];
		for(int i = 0; i < 20; i++) estate[i] = -1;
		estateValue = 0;
		account = 0;
		locat = 0;
		stop = false;
	}
	
	/**
	 * This method is set player
	 *
	 * @param  Name the name of the player
	 */
	public void setName(String Name) {
		this.name = Name;
	}
	
	/**
	 * This method is set player's name
	 *
	 * @return name player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method is to get location
	 *
	 * @return locat the loction
	 */
	public int getLocat() {
		return locat;
	}
	/**
	 * This method is set player's location
	 *
	 * @param  dice the number= of the dice
	 * 
	 */
	public void setLocat(int dice) {
		//decided by dice;
		int pre_loc = locat;
		locat = (pre_loc + dice)%20;
		if(pre_loc > locat && locat != 0)//the player plays a round, get 1500,but if the player stops at 0 get nothing
			cash += 500;
		if(locat == 13)//if the player stops at library(estate[13]), stop for a round
			stop = true;
		
	}
	/**
	 * This method is to get account
	 *
	 * @return account the account
	 */
	public int getAccount() {
		return this.account;
	}
	/**
	 * This method is set player's account
	 */
	public void setAccount() {
		this.account = this.cash + this.estateValue;
	}
	/**
	 * This method is set player's account
	 * @param money the money that have to add or minus
	 */
	public void setCash(int money) {
		cash+=money;
	}
	/**
	 * This method is to get player's cash
	 *
	 * @return account the account
	 */
	public int getCash() {
		return cash;
	}
	
}