import java.util.Timer;
import java.util.TimerTask;
	
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.MatteBorder;
	
public class MonopolyMain extends JFrame {
	final private int frameWidth = 1152, frameHeight = 720;
	
	// Program constructor
    public MonopolyMain() {
		initFrame();
		Menu menu = new Menu();
		this.add(menu);
		
		
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
	// start game
 	private void startGame() {
 		this.remove(menu);
		Game game =new Game();
		this.add(game);
 	}
 	
	// the Game class
 	class Game extends JPanel{
 		private JLayeredPane layeredPane;
 		private JPanel map=new JPanel();
 		private JLabel MAP = new JLabel();
 		
 		final private int mapWidth = frameWidth, mapHeight = 500;
 		final private Dimension mapCenter = new Dimension(frameWidth/2, mapHeight/2);
 		private void initLayerPane() {
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(mapWidth, mapHeight));
	        
			this.MAP.setIcon(new ImageIcon("images/Menu/menuBackground.png"));// TODO map icon path
			this.MAP.setBounds(0, 0, mapWidth, mapHeight);
	        layeredPane.add(MAP, new Integer(0));
	     
	        this.add(layeredPane);
		}
 		public Game() {
 			initLayerPane();
 		}
 		
 	}
 	// the Menu class
 	class Menu extends JPanel {
 		private JLayeredPane layeredPane;
 		private JLabel background = new JLabel();
 		
 		// attribute of title
 		private JPanel title = new JPanel();
 		final private int titleWidth = 930, titleHeight = 80;
 		final private Dimension titleCenter = new Dimension(frameWidth/2, frameHeight/4);
 		private JLabel titleText = new JLabel("== NTU MONOPOLY ==", JLabel.CENTER);
		
 		// attribute of sub menu		
 		private JPanel subMenu = new JPanel();
		final private int subMenuWidth = 384, subMenuHeight = 300;
 		final private Dimension subMenuCenter = new Dimension(frameWidth/2, 524);
 		private JLabel startText = new JLabel("START", JLabel.CENTER);
		private JLabel credits = new JLabel("View Credits", JLabel.CENTER);
		
		//attribute of credits
		private JPanel credit = new JPanel();
		private JTextArea creditText = new JTextArea(20,60);
		private JLabel creditButt = new JLabel("BACK");
		
		// attribute of game setting
		private JPanel gameSet = new JPanel();
		final private int gameSetWidth = 500, gameSetHeight = 250;
		final private Dimension gameSetCenter = new Dimension(frameWidth/2, 524);
		private JLabel start = new JLabel("START GAME", JLabel.CENTER);
		private JLabel back = new JLabel("BACK", JLabel.CENTER);
		
		// Menu(Panel) settings
 		private void initPanel() {
			setLayout(new GridLayout(1, 1));
			setOpaque(false);
		}
		
		private void initFonts() {
			titleText.setFont(new Font("Arial Black", Font.BOLD, 60));
			startText.setFont(new Font("Arial Black", Font.BOLD, 30));
			credits.setFont(new Font("Arial Black", Font.BOLD, 30));
		}
		
		private void initTitle() {
			title.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			title.setLayout(new GridLayout(1, 1, 0, 0));
			title.setOpaque(true);

			titleText.setForeground(new Color(115, 74, 18));
			titleText.setOpaque(true);
			titleText.setBackground(new Color(250, 235, 215));
			titleText.setBorder(new MatteBorder(5, 5, 5, 5, new Color(61, 89, 171)));
			
			title.add(titleText);
		}
		
		// credit setting (TODO Optional)
		private void initCredit() {
			credit.setLayout(new GridLayout(2, 1, 60, 10));
			credit.setOpaque(true);
			credit.setBackground(new Color(178, 247, 178));
			creditText.append("This game have been created by:\nRichard Walton\n");
			creditText.append("Programs used: \n Adobe Photoshop\n MultiMedia Fusion 2\n Audacity\n");
			creditText.append("Thanks also to various people supporting the game\n");
			creditText.setEditable(false);
			credit.add(creditText);
			credit.add(creditButt);
		}
		
		// game setting 
		private void initGameSet() {
			gameSet.setLayout(new GridLayout(5, 1));
			for (int i = 0; i < 4; i++) {
				JPanel inputPanel = new JPanel();
				int playerNumber = i + 1;
				JLabel player = new JLabel("Player " + playerNumber + " : ");
				TextField inputField = new TextField(15);
				inputField.setFont(new Font("Serif", Font.BOLD, 23));
				inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				inputPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
				inputPanel.add(player);
				inputPanel.add(inputField);
				inputPanel.setBackground(new Color(245, 222, 179));
				gameSet.add(inputPanel);
			}
			
			// set 'back' and 'start' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.setBackground(new Color(245, 222, 179));
			buttons.add(back);
//			buttons.add(new Panel());
			buttons.add(start);
			gameSet.add(buttons);
		}
		
		private void initSubMenu() {
			initCredit();
			subMenu.setLayout(new GridLayout(2, 1, 0, 0));
			subMenu.setOpaque(true);
			subMenu.setBackground(new Color(245, 222, 179));
			subMenu.add(startText);
			subMenu.add(credits);
			subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		}
		
		private void initLayerPane() {
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
	        
			this.background.setIcon(new ImageIcon("images/Menu/menuBackground.png"));
			this.background.setBounds(0, 0, frameWidth, frameHeight);
	        layeredPane.add(background, new Integer(0));
	        
	        this.title.setBounds(titleCenter.width-(titleWidth/2), titleCenter.height-(titleHeight/2), titleWidth, titleHeight);
	        layeredPane.add(title, new Integer(1));
	        
	        this.subMenu.setBounds(subMenuCenter.width-(subMenuWidth/2), subMenuCenter.height-(subMenuHeight/2), subMenuWidth, subMenuHeight);
	        layeredPane.add(subMenu, new Integer(2));
	        
	        this.gameSet.setBounds(gameSetCenter.width-(gameSetWidth/2), gameSetCenter.height-(gameSetHeight/2), gameSetWidth, gameSetHeight);
	        
	        this.add(layeredPane);
		}
		
		public Menu() {
			initPanel();
	        initFonts();
			initTitle();
			initSubMenu();
			initGameSet();
			initLayerPane();
			
			startText.addMouseListener(ml);
			credits.addMouseListener(ml);
			creditButt.addMouseListener(ml);
			back.addMouseListener(ml);
			start.addMouseListener(ml);
		};
		MouseListener ml = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel)e.getSource();
				l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				l.setForeground(Color.red);
			}
		
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel)e.getSource();
				l.setForeground(Color.black);
			}
			
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == credits){ // TODO optional
					layeredPane.remove(subMenu);
					layeredPane.add(credit);
					validate();
					repaint();
					credits.setForeground(Color.black);
				} else if(e.getSource() == creditButt) {
					layeredPane.remove(credit);
					layeredPane.add(subMenu);
					validate();
					repaint();
					creditButt.setForeground(Color.black);
				} else if(e.getSource() == startText) {
					layeredPane.remove(subMenu);
					layeredPane.add(gameSet, new Integer(2));
					validate();
					repaint();
					startText.setForeground(Color.black);
				} else if(e.getSource() == back) {
					layeredPane.remove(gameSet);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					back.setForeground(Color.black);
				} else if(e.getSource()==start) {
					System.exit(0);
				}
			}					
		};
	
	}
	
	
	public static void main(String[] args) {
		MonopolyMain program = new MonopolyMain();
	}
}
