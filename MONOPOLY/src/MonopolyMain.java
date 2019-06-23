	
import java.awt.*;

import javax.swing.*;

public class MonopolyMain extends JFrame {
	final public int frameWidth = 1151, frameHeight = 742;
	
	// Program constructor
    public MonopolyMain() {
		initFrame();
		this.setContentPane(new Menu());
        this.setVisible(true);
	}
    
    // Initialize the frame
 	private void initFrame() {
 		this.setTitle("NTU Monopoly Game");
 		this.setSize(frameWidth, frameHeight);
 		this.setResizable(false);
 		this.setLocationRelativeTo(null);
 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
 	}
 	
 	

	public static void main(String[] args) {
		new MonopolyMain();
	}
}