import java.awt.*;

import javax.swing.*;
/**
 * This is the class of the main class for running Monopoly frame and initial monopoly.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */
public class MonopolyMain extends JFrame {
	final public int frameWidth = 1151, frameHeight = 742;
	
	// Program constructor
    public MonopolyMain() {
		initFrame();
		this.setContentPane(new Menu());
        this.setVisible(true);
	}
    /**
	 * This method is to Initialize the frame
	 *
	 */
    // Initialize the frame
 	private void initFrame() {
 		this.setTitle("NTU Monopoly Game");
 		this.setSize(frameWidth, frameHeight);
 		this.setResizable(false);
 		this.setLocationRelativeTo(null);
 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
 	}
 	
 	
 	/**
	 * This is the main function
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		databaseUtil.buildConnection();
		new MonopolyMain();
	}
}