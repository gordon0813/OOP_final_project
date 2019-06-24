import java.util.concurrent.TimeUnit;
/**
 * This is the class of the Dice class for setting Dice and icon of the dice.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */
public class Dice {
	private int diceNum;
	public String[] diceIcon= {"images/Dice/dieRed1.png","images/Dice/dieRed2.png","images/Dice/dieRed3.png","images/Dice/dieRed4.png",
			"images/Dice/dieRed5.png","images/Dice/dieRed6.png"};
	/**
	 * constructor of Dice
	 * 
	 */
	public Dice(){
		diceNum=1;
		//diceIcon="images/Dice/dieRed1.png";
	}
	/**
	 * This method is set Dice number
	 * 
	 * @return diceNum the number of the dice
	 */
	public int setDice() {
		diceNum = Game.r.nextInt(6)+1;
		//System.out.println(diceNum);
		return diceNum;
	}
	/**
	 * This method is get Dice number
	 * 
	 * @return diceNum the number of the dice
	 */
	public int getDice() {
		return diceNum;
	}
	/**
	 * This method is get Dice number's icon
	 * 
	 * @return diceIcon the number of the dice's icon
	 */
	public String getDiceIcon(){
		return diceIcon[diceNum-1];
	}
}