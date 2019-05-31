import java.awt.*;
import java.util.Random;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.event.*;
public class Game extends JPanel {
	final int frameWidth = 1152, frameHeight = 740;
	private JLayeredPane layeredPane;
	private int turn;
	public static Random r;
	private JPanel user1,user2,user3,user4;
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	// map
	final private int mapWidth = frameWidth, mapHeight = 450;
	private JLabel MAP = new JLabel();
	private Landmark[] landmarkList = new Landmark[20];
	
	// dice
	private JLabel ROWDICE = new JLabel();
	private JButton diceButton = new JButton();
	//user frame
	private PlayerInfo[] playerInfoList = new PlayerInfo[4];
	private JPanel msg = new Message();
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}
	
	private void initLandmarkList() {
		//					 0		  1		  2		  3		   4		5		6		7		 8		  9		   10		11		 12			13		 14			15		 16		 17		  18	   19
		String[] nameList = {"START", "操 場", "新 體", "舊 體", "醉月湖", "新 生", "計 中", "心理系", "應力所", "資工系", "電機系", "獸醫系", "我大工海", "圖書館", "健康中心", "森林系", "園藝系", "傅 鐘", "物理系", "戲劇系"};
		int[][] posList = {{180, 250}, {180, 150}, {180, 30}, {375, 30}, {500, 30}, {590, 30}, {710, 30}, {800, 30}, {880, 30}, {1000, 30}, {1000, 120}, {1000, 210}, {1000, 340}, {900, 340}, {750, 340}, {660, 340}, {505, 340}, {405, 340}, {300, 340}, {180, 340}};
		int[] moneyList = {0, 500, -200, 300, 0, 800, 1000, 1200, 0, 2500, 2000, 0, 3000, 0, 0, 900, 1500, 0, 1200, -500};
		for (int i = 0; i < landmarkList.length; i++) {
			if (i == 0) { // START
				landmarkList[i] = new Landmark(nameList[i], new Color(16 ,78 ,139), new Color(244, 255 ,255), new Color(16 ,78, 139), posList[i][0], posList[i][1]);
			} else if (i == 4 || i == 11) { // CHANCE
				landmarkList[i] = new Landmark(nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "CHANCE", posList[i][0], posList[i][1]);
			} else if (i == 8 || i == 14) { // FATE
				landmarkList[i] = new Landmark(nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "FATE", posList[i][0], posList[i][1]);				
			} else if (i == 13) { // JAIL
				landmarkList[i] = new Landmark(nameList[i], new Color(70, 64, 64), new Color(244, 255 ,255), new Color(70, 64, 64), "JAIL", posList[i][0], posList[i][1]);								
			} else if (i == 17) { // FUBELL
				landmarkList[i] = new Landmark(nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "21 rings", posList[i][0], posList[i][1]);								
			} else {
				landmarkList[i] = new Landmark(nameList[i], new Color(115, 74, 18), new Color(250, 235, 215), new Color(115, 74, 18), moneyList[i], posList[i][0], posList[i][1]);
			}
		}
	}
	
	private void initPlayerInfoList() {
		for (int i = 0; i < playerInfoList.length; i++) {
			playerInfoList[i] = new PlayerInfo();
		}
	}
	
	private void initLayerPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

		// Map
		MAP.setIcon(new ImageIcon("images/Map/mapbackground.png"));// TODO map icon path
		MAP.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
		MAP.setBounds(0, 0, mapWidth, mapHeight);
        layeredPane.add(MAP, new Integer(0));
		
        for (int i = 0; i < landmarkList.length; i++) {
        	layeredPane.add(landmarkList[i], new Integer(1));
        }
        
        // Users' info
        JPanel playerArea = new JPanel(new GridLayout(2, 2, 0, 0));
        for (int i = 0; i < playerInfoList.length; i++) {
        	playerArea.add(playerInfoList[i]);
        }

        playerArea.setBorder(new MatteBorder(5, 5, 5, 5, Color.GRAY));
        playerArea.setBounds(0, mapHeight, 700, frameHeight-mapHeight-4*5);
        layeredPane.add(playerArea, new Integer(0));
        
        // Massage's info
        msg = new Message();
        layeredPane.add(msg, new Integer(0));
        
      //dice
		this.ROWDICE.setBounds(570, 200, 64, 64);
		  try {
			  ROWDICE.add(diceButton);
			  diceButton.setIcon(new ImageIcon("images/Dice/dieRed1.png"));
			  diceButton.setBounds(570, 200, 64, 64);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		layeredPane.add(diceButton, new Integer(1));
        
        
        this.add(layeredPane);
	}
	
	public Game() {
		r = new Random();
		initPanel();
		initLandmarkList();
		initPlayerInfoList();
		initLayerPane();
		
		myEvent();
	}
	
	class Landmark extends JLabel {
		final static int width = 85, height = 75;
		private String name = null;
		private int money = 0;
		private int x = 0, y = 0;
		
		// for START
		public Landmark(String name, Color textColor, Color bgColor, Color boardColor, int x, int y) {
			this.name = new String(name);
			this.money = 0;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText.setFont(new Font("Arial Black", Font.BOLD, 14));
			nameText.setForeground(textColor);
			
			this.setLayout(new GridLayout(1, 1, 0, 0));
			this.setOpaque(true);
	        this.setBackground(bgColor);
			this.setBorder(new MatteBorder(5, 5, 5, 5, boardColor));
			this.add(nameText);
			
			this.setBounds(this.x, this.y, width, height);
		}
		
		// for special landmark
		public Landmark(String name, Color textColor, Color bgColor, Color boardColor, String text, int x, int y) {
			this.name = new String(name);
			this.money = 0;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
			nameText.setForeground(textColor);
			
			JLabel descriptText = new JLabel(text, JLabel.CENTER);
			
			this.setLayout(new GridLayout(2, 1, 0, 0));
			this.setOpaque(true);
	        this.setBackground(bgColor);
			this.setBorder(new MatteBorder(5, 5, 5, 5, boardColor));
			this.add(nameText);
			this.add(descriptText);
			
			this.setBounds(this.x, this.y, width, height);
		}
		
		// for departments
		public Landmark(String name, Color textColor, Color bgColor, Color boardColor, int money, int x, int y) {
			this.name = new String(name);
			this.money = money;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
			nameText.setForeground(textColor);
			
			JLabel descriptText;
			if (this.money >= 0) 
				descriptText = new JLabel("$" + this.money, JLabel.CENTER);
			else
				descriptText = new JLabel("-$" + (-this.money), JLabel.CENTER);
			
			this.setLayout(new GridLayout(2, 1, 0, 0));
			this.setOpaque(true);
	        this.setBackground(bgColor);
			this.setBorder(new MatteBorder(5, 5, 5, 5, boardColor));
			this.add(nameText);
			this.add(descriptText);
			
			this.setBounds(this.x, this.y, width, height);
		}
		
		public Landmark() { // default, shouldn't be used
			this.name = "No Name";
			this.money = -1;
			this.x = 0;
			this.y = 0;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
			nameText.setForeground(Color.BLACK);
			
			JLabel descriptText = new JLabel("nothing", JLabel.CENTER);

			
			this.setLayout(new GridLayout(2, 1, 0, 0));
			this.setOpaque(true);
	        this.setBackground(Color.BLACK);
			this.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
			this.add(nameText);
			this.add(descriptText);
			
			this.setBounds(this.x, this.y, width, height);
		}
	}
	
	class PlayerInfo extends JPanel {
		private Image background;
		private JLabel text = new JLabel("player");
		Player player; // share the reference with 'player' when the game is playing

		public PlayerInfo() {
			try {
				this.background = ImageIO.read(new File("images/Menu/1.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
			this.add(text);
		}
		
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(background, 0, 0, this);
	    }
	}
	
	private void myEvent(){
		diceButton.addActionListener(
				new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        //  when the button is pressed
	    	Dice d=new Dice();
		d.setDice();
		diceButton.setIcon(new ImageIcon(d.getDiceIcon()));
				
	      }
	    });
		}
	
	class Message extends JPanel {	
		private JLabel text = new JLabel("NTU Monopoly",JLabel.CENTER);
		
		public Message() {
	        text.setFont(new Font("Arial Black", Font.BOLD, 40));
	        text.setForeground(new Color(115, 74, 18));
	        this.add(text);
	        this.setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
	        this.setBounds(700, mapHeight, frameWidth-700, frameHeight-mapHeight-4*5);
	        this.setOpaque(true);
	        this.setBackground(new Color(253, 245, 230));
	        
		}
	}
	
	public static void main(String[] args) {
		final int frameWidth = 1152, frameHeight = 740;
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (Game test)");
 		f.setSize(frameWidth, frameHeight);
 		f.setResizable(false);
 		f.setLocationRelativeTo(null);
 		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
		f.add(new Game());
		f.setVisible(true);

	}

}
