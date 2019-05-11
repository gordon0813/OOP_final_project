import java.util.Timer;
import java.util.TimerTask;
	
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
	
public class GUI_test extends JFrame {
	
	// Program constructor
    public GUI_test() {
		initFrame();
		Menu menu = new Menu();
		this.add(menu);
		this.setVisible(true);
	}
    
    // Initialize the frame
 	private void initFrame() {
 		this.setTitle("NTU Monopoly Game");
 		this.setSize(1152, 720);
 		this.setResizable(false);
 		this.setLocation(0, 0);
 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
 	}    
	
 	// the Menu class
 	class Menu extends JPanel{
 		private JLayeredPane layeredPane;
 		private JLabel background = new JLabel();
 		
		private JPanel subMenu = new JPanel();
		
		private JLabel title = new JLabel("== NTU MONOPOLY ==", JLabel.CENTER);
		private JLabel startText = new JLabel("Start a new game", JLabel.CENTER);
		private JLabel credits = new JLabel("View Credits", JLabel.CENTER);
		
		//credits
		private JPanel credit = new JPanel();
		private JTextArea creditText = new JTextArea(20,60);
		private JLabel creditButt = new JLabel("BACK");

		//Start a game
		private JPanel game = new JPanel();
		private JTextField name1 = new JTextField(15);
		private JTextField name2 = new JTextField(15);
		private String[] limits = {"100","75","50", "25"};
		private JComboBox box = new JComboBox(limits);
		private JLabel start = new JLabel("START GAME", JLabel.CENTER);
		private JLabel back = new JLabel("BACK", JLabel.CENTER);
		
		// Panel settings
		private void initPanel() {
			setLayout(new GridLayout(1, 1));
			setOpaque(false);
			setBackground(new Color(178,247,178));
		}
		
		private void initFonts() {
			title.setFont(new Font("Arial Black", Font.BOLD, 60));
			startText.setFont(new Font("Arial Black", Font.BOLD, 18));
			credits.setFont(new Font("Arial Black", Font.BOLD, 18));
		}
		
		private void initTitle() {
			title.setForeground(new Color(94, 38, 18));
			title.setOpaque(false);
//			title.setBackground(Color.red);
//			title.setBorder(new MatteBorder(5,5,5,5,Color.white));
		}
		
		private void initSubMenu() {
			// credit setting
			credit.setLayout(new GridLayout(2, 1, 60, 10));
			credit.setOpaque(true);
			credit.setBackground(new Color(178, 247, 178));
			creditText.append("This game have been created all by:\nRichard Walton\n");
			creditText.append("Programs used: \n Adobe Photoshop\n MultiMedia Fusion 2\n Audacity\n");
			creditText.append("Thanks also to various people supporting the game\n");
			creditText.setEditable(false);
			credit.add(creditText);
			credit.add(creditButt);
			
			subMenu.setLayout(new GridLayout(2, 1, 10, 10));
			subMenu.setOpaque(true);
			subMenu.setBackground(new Color(210, 180, 140));
			subMenu.add(startText);
			subMenu.add(credits);
			subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		}
		
		public Menu() {
			initPanel();
	        initFonts();
			initTitle();
			initSubMenu();
			
			this.background.setIcon(new ImageIcon("images/Menu/menu_background1.png"));
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(1152, 720));
	        layeredPane.add(background, new Integer(0));
	        layeredPane.add(title, new Integer(1));
	        layeredPane.add(subMenu, new Integer(2));
	        this.add(layeredPane);
	        
	        this.background.setBounds( 0, 0, 1152, 720);
	        this.title.setBounds( 0, 0, 1152, 360);
	        this.subMenu.setBounds(360, 384, 384, 720);
	        				
		};
	
	
	}
	
	
	
	public static void main(String[] args) {
		GUI_test program = new GUI_test();
	}
}
