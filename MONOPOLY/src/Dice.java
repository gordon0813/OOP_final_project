import java.util.concurrent.TimeUnit;

public class Dice {
	private int diceNum;
	public String[] diceIcon= {"images/Dice/dieRed1.png","images/Dice/dieRed2.png","images/Dice/dieRed3.png","images/Dice/dieRed4.png",
			"images/Dice/dieRed5.png","images/Dice/dieRed6.png"};
	public Dice(){
		diceNum=1;
		//diceIcon="images/Dice/dieRed1.png";
	}
	public int setDice() {
		diceNum = Game.r.nextInt(6)+1;
		//System.out.println(diceNum);
		return diceNum;
	}
	public int getDice() {
		return diceNum;
	}
	public String getDiceIcon(){
		return diceIcon[diceNum-1];
	}
}
