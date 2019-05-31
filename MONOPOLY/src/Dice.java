public class Dice {
	private int diceNum;
	private String diceIcon;
	public Dice(){
		diceNum=1;
		diceIcon="images/Dice/dieRed1.png";
	}
	public int setDice() {
		diceNum = Game.r.nextInt(6)+1;
		System.out.println(diceNum);
		return diceNum;
	}
	public String getDiceIcon() {
		switch (diceNum) {
		case 1:
			diceIcon= "images/Dice/dieRed1.png";
			break;
		case 2:
			diceIcon= "images/Dice/dieRed2.png";
			break;
		case 3:
			diceIcon= "images/Dice/dieRed3.png";
			break;
		case 4:
			diceIcon= "images/Dice/dieRed4.png";
			break;
		case 5:
			diceIcon= "images/Dice/dieRed5.png";
			break;
		case 6:
			diceIcon= "images/Dice/dieRed6.png";
			break;
		
		}
		return diceIcon;
	}
}

