import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.JFrame;

/**
 * This is the class of Menu in Monopoly game.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */
public class Menu extends JPanel {
	// menu
	private JLayeredPane layeredPane;
	private JLabel background = new JLabel();
	final int frameWidth = 1152, frameHeight = 740;

	// title
	private JPanel title = new JPanel();
	final private int titleWidth = 1000, titleHeight = 120;
	final private Dimension titleCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel titleText = new JLabel("== NTU MONOPOLY ==", JLabel.CENTER);
	// sub menu
	private JPanel subMenu = new JPanel();
	final private int subMenuWidth = 384, subMenuHeight = 150;
	final private Dimension subMenuCenter = new Dimension(frameWidth / 2, 524);
	private JLabel startText = new JLabel("START", JLabel.CENTER);
	private JLabel credits = new JLabel("View Credits", JLabel.CENTER);

	// credits
	private JPanel credit = new JPanel();
	private JTextArea creditText = new JTextArea(20, 60);
	private JLabel creditButt = new JLabel("BACK");

	// game setting
	private JPanel gameSet = new JPanel();
	private JTextField[] textList = new JTextField[4];
	final private int gameSetWidth = 500, gameSetHeight = 250;
	final private Dimension gameSetCenter = new Dimension(frameWidth / 2, 524);
	private JLabel start = new JLabel("START GAME", JLabel.CENTER);
	private JLabel back = new JLabel("BACK", JLabel.CENTER);

	// music
	public static FileInputStream enterplayerFileinput;
	public static AudioStream enterplayerAS;
	public static FileInputStream themeFileinput;
	public static AudioStream themeAS;

	/**
	 * Menu(Panel) settings
	 * 
	 */
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}
	
	/**
	 * initialize fonts
	 * 
	 */
	private void initFonts() {
		titleText.setFont(new Font("Arial Black", Font.BOLD, 60));
		startText.setFont(new Font("Arial Black", Font.BOLD, 30));
		credits.setFont(new Font("Arial Black", Font.BOLD, 30));
	}

	/**
	 * initialize title
	 * 
	 */
	private void initTitle() {
		title.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		title.setLayout(new GridLayout(1, 1, 0, 0));
		title.setOpaque(false);
		// title.setIcon(new ImageIcon(""))

		titleText.setForeground(Color.black);
		titleText.setOpaque(false);
		// titleText.setBackground(new Color(250, 235, 215));
		titleText.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		title.add(titleText);
	}

	/**
	 * initialize game setting
	 * 
	 */
	private void initGameSet() {
		gameSet.setLayout(new GridLayout(5, 1));
		gameSet.setOpaque(false);
		gameSet.setBorder(new MatteBorder(7, 7, 7, 7, Color.white));
		for (int i = 0; i < 4; i++) {
			JPanel inputPanel = new JPanel();
			int playerNumber = i + 1;
			JLabel player = new JLabel("Player " + playerNumber + " : ");
			player.setFont(new Font("Arial Black", Font.PLAIN, 18));
			textList[i] = new JTextField(15);
			textList[i].setFont(new Font("Serif", Font.BOLD, 23));
			textList[i].setBackground(new Color(232, 232, 232, 100));
			inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			inputPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
			inputPanel.add(player);
			inputPanel.add(textList[i]);
			inputPanel.setOpaque(false);
			gameSet.add(inputPanel);
		}

		// set 'back' and 'start' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		back.setFont(new Font("Arial Black", Font.PLAIN, 16));
		start.setFont(new Font("Arial Black", Font.PLAIN, 16));
		buttons.add(back);
		buttons.add(start);
		gameSet.add(buttons);
	}

	/**
	 * initialize sub menu
	 * 
	 */
	private void initSubMenu() {
		subMenu.setLayout(new GridLayout(1, 1, 0, 0));
		subMenu.setOpaque(false);
		subMenu.add(startText);
		subMenu.setBorder(new MatteBorder(10, 10, 10, 10, Color.white));
	}

	/**
	 * initialize layerPane
	 * 
	 */
	private void initLayerPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.background.setIcon(new ImageIcon("images/Menu/menuBackground.png"));
		this.background.setBounds(0, 0, frameWidth, frameHeight);
		layeredPane.add(background, new Integer(0));
		this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2), titleWidth,
				titleHeight);
		layeredPane.add(title, new Integer(1));
		this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
				subMenuWidth, subMenuHeight);
		layeredPane.add(subMenu, new Integer(2));
		this.gameSet.setBounds(gameSetCenter.width - (gameSetWidth / 2), gameSetCenter.height - (gameSetHeight / 2),
				gameSetWidth, gameSetHeight);

		this.add(layeredPane);
	}

	/**
	 * constructor of Menu class
	 * 
	 */
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
		/**
		 * This method is to show when mouse entered
		 * 
		 * @param e event
		 */
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			l.setForeground(Color.red);
		}
		
		/**
		 * This method is to show when mouse exited
		 * 
		 * @param e event
		 */
		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setForeground(Color.black);
		}
		
		/**
		 * This method is to show when mouse clicked
		 * 
		 * @param e event
		 */
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == credits) { // TODO optional
				layeredPane.remove(subMenu);
				layeredPane.add(credit);
				validate();
				repaint();
				credits.setForeground(Color.black);
			} else if (e.getSource() == creditButt) {
				layeredPane.remove(credit);
				layeredPane.add(subMenu);
				validate();
				repaint();
				creditButt.setForeground(Color.black);
			} else if (e.getSource() == startText) {
				layeredPane.remove(subMenu);
				layeredPane.add(gameSet, new Integer(2));
				validate();
				repaint();
				startText.setForeground(Color.black);
				try {
					enterplayerFileinput = new FileInputStream("music/enterplayer.wav");
					enterplayerAS = new AudioStream(enterplayerFileinput);
					AudioPlayer.player.start(enterplayerAS);
				} catch (Exception a) {
					a.printStackTrace();
				}
			} else if (e.getSource() == back) {
				layeredPane.remove(gameSet);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				back.setForeground(Color.black);
			} else if (e.getSource() == start) {
				AudioPlayer.player.stop(enterplayerAS);
				Player[] playerList = new Player[4];
				for (int i = 0; i < 4; i++) {
					playerList[i] = new Player(Menu.this.textList[i].getText());
					// System.out.println(playerList[i].getName());
				}
				try {
					themeFileinput = new FileInputStream("music/theme.wav");
					themeAS = new AudioStream(themeFileinput);
					AudioPlayer.player.start(themeAS);
				} catch (Exception a) {
					a.printStackTrace();
				}
				Menu.this.setVisible(false);
				JFrame root = (JFrame) SwingUtilities.getRoot(Menu.this);
				root.setContentPane(new Game(playerList));

			}
		}
	};

	/**
	 * main method to test menu.java
	 *
	 * @param args not used.
	 */
	public static void main(String[] args) {
		final int frameWidth = 1152, frameHeight = 720;
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (Menu test)");
		f.setSize(frameWidth, frameHeight);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.add(new Menu());
		f.setVisible(true);
	}

}