import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dice extends JPanel {
	final private int x = 570, y = 200, width = 64, height = 64;
	private JButton diceButton = new JButton();
	private int number;
	private ImageIcon[] iconList = new ImageIcon[7]; // 0 should not be used, instead, use 1~6 
	private Random r;
	
	private void initIconList() {
		iconList[0] = null;
		for (int i = 1; i <= 6; i++) {
			iconList[i] = new ImageIcon("images/Dice/dieRed"+(i+1)+".png");
		}
	}
	
	public Dice(){
		initIconList();
		this.number = 1;
	}
	
	public int setDice() {
		number = r.nextInt(6)+1;
		System.out.println(number);
		return number;
	}
	
}
