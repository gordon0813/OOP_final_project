import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.event.*;

/**
 * This is the class of the Game class for running Monopoly game.
 * 
 * @author momo, tin, catherine, sophia
 * @version 1.0
 * @since 2017-05-31
 */
public class Game extends JPanel {
	final int frameWidth = 1152, frameHeight = 740;
	private boolean transcation=false;
	private JLayeredPane layeredPane=new JLayeredPane();;
	private int turn=1;
	private boolean next=false;// change to the turn of next player
	public static Random r;
	private int count=0;
	Player[] playerList = null;
	private int[] lacbelong=new int[20];
	// map
	final private int mapWidth = frameWidth, mapHeight = 450;
	private JLabel MAP = new JLabel();
	private Landmark[] landmarkList = new Landmark[20];
	// dice
	private JLabel ROWDICE = new JLabel();
	private JButton diceButton = new JButton();
	private JLabel rowing = new JLabel();
	private Dice d=new Dice();
	//user frame
	private PlayerInfo[] playerInfoList = new PlayerInfo[4];
	private JLabel moneytext =new JLabel();
	//user icon
	private JLabel u1 = new JLabel();
	private JLabel u2 = new JLabel();
	private JLabel u3 = new JLabel();
	private JLabel u4 = new JLabel();
	//space for user frame
	private JLabel user1 = new JLabel();
	private JLabel user2 = new JLabel();
	private JLabel user3 = new JLabel();
	private JLabel user4 = new JLabel();
	//user name
	private JLabel user1n = new JLabel();
	private JLabel user2n = new JLabel();
	private JLabel user3n = new JLabel();
	private JLabel user4n = new JLabel();
	
	private JLabel F1 = new JLabel();//Figure 1
	private JLabel F2 = new JLabel();//Figure 2
	private JLabel F3 = new JLabel();//Figure 3
	private JLabel F4 = new JLabel();//Figure 4
	//button words
	private JLabel yes = new JLabel("Yes",JLabel.CENTER);
	private JLabel no = new JLabel("No",JLabel.CENTER);
	private JLabel ok = new JLabel("OK",JLabel.CENTER);
	//user's money text
	private JLabel money1,money2,money3,money4;
	//info message
	private JLabel Msg = new JLabel();
	//Lake's info
	private JLabel toLake = new JLabel();
	//payfee's Icon
	private JLabel payfeeIcon = new JLabel();
	
	private JPanel end=new JPanel();
	private JButton endButton=new JButton();
	
	private JLabel firstText = new JLabel(" ", JLabel.CENTER);
	 private JLabel secondText = new JLabel(" ", JLabel.CENTER);
	 private JLabel thirdText = new JLabel(" ", JLabel.CENTER);
	 private JLabel fourthText = new JLabel(" ", JLabel.CENTER);

	
	private String[] Icons= {"images/playerIcon/1.png","images/playerIcon/2.png","images/playerIcon/3.png","images/playerIcon/4.png"};
	private int[][] F1pos=new int[20][2];
	private int[][] F2pos=new int[20][2];
	private int[][] F3pos=new int[20][2];
	private int[][] F4pos=new int[20][2];
	private int[] moneyList = {0, 500, -200, 300, -200, 800, 1000, 1200, 0, 2500, 2000, 800, 3000, 0, 0, 900, 1500, 0, 1200, -500};
	
	/**
	 * This method is to initialize panel.
	 *
	 */
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}
	
	/**
	 * This method set Figure's position.
	 * 
	 * @param player player's number
	 * @param player player's Figure's position
	 */
	private void setFpos(int player,int Fpos) {//TODO
		int x,y;
		if(player==1) {
			x=F1pos[Fpos][0];
			y=F1pos[Fpos][1];
			F1.setBounds(x,y,30,30);
		}else if(player==2) {
			x=F2pos[Fpos][0];
			y=F2pos[Fpos][1];
			F2.setBounds(x,y,30,30);
		}else if(player==3) {
			x=F3pos[Fpos][0];
			y=F3pos[Fpos][1];
			F3.setBounds(x,y,30,30);
		}else if(player==4) {
			x=F4pos[Fpos][0];
			y=F4pos[Fpos][1];
			F4.setBounds(x,y,30,30);
		}
	}
	
	/**
	 * This method Check whether the User can be signed in.
	 * 
	 */
	private void initLandmarkList() {
		//					 0		  1		  2		  3		   4		5		6		7		 8		  9		   10		11		 12			13		 14			15		 16		 17		  18	   19
		String[] nameList = {"START", "操 場", "新 體", "舊 體", "醉月湖", "新 生", "計 中", "心理系", "應力所", "資工系", "電機系", "獸醫系", "我大工海", "圖書館", "健康中心", "森林系", "園藝系", "傅 鐘", "物理系", "戲劇系"};
		String[] LinkList = {"https://www.ntu.edu.tw/",
				"https://pe.ntu.edu.tw/",
				"https://pe.ntu.edu.tw/",
				"https://ntusportscenter.ntu.edu.tw/",
				"https://www.ntu.edu.tw/12scence/02.html",
				"https://zh.wikipedia.org/wiki/%E5%9C%8B%E7%AB%8B%E8%87%BA%E7%81%A3%E5%A4%A7%E5%AD%B8%E6%96%B0%E7%94%9F%E6%95%99%E5%AD%B8%E9%A4%A8",
				"https://www.cc.ntu.edu.tw/",
				"https://www.psy.ntu.edu.tw/",
				"https://www.iam.ntu.edu.tw/zh/",
				"https://www.csie.ntu.edu.tw/",
				"https://www.ee.ntu.edu.tw/",
				"https://www.vm.ntu.edu.tw/DVM/",
				"http://www.esoe.ntu.edu.tw/",
				"https://www.lib.ntu.edu.tw/",
				"http://shmc.osa.ntu.edu.tw/",
				"https://www.fo.ntu.edu.tw/",
				"http://www.hort.ntu.edu.tw/web/index/index.jsp",
				"https://zh.wikipedia.org/wiki/%E5%82%85%E9%90%98_(%E5%9C%8B%E7%AB%8B%E8%87%BA%E7%81%A3%E5%A4%A7%E5%AD%B8)",
				"https://www.phys.ntu.edu.tw/",
				"http://homepage.ntu.edu.tw/~theatre/admission-02.htm"};
		int[][] posList = {{180, 250}, {180, 150}, {180, 30}, {375, 30}, {500, 30}, {600, 30}, {700, 30}, {800, 30}, {900, 30}, {1000, 30}, {1000, 120}, {1000, 210}, {1000, 340}, {900, 340}, {750, 340}, {660, 340}, {505, 340}, {405, 340}, {300, 340}, {180, 340}};
		
		for (int i = 0; i < 20; i++) {
			if (i == 0) { // START
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(16 ,78 ,139), new Color(244, 255 ,255), new Color(16 ,78, 139), posList[i][0], posList[i][1]);
			} else if (i == 4 || i == 11) { // CHANCE
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "CHANCE", posList[i][0], posList[i][1]);
			} else if (i == 8 || i == 14) { // FATE
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "FATE", posList[i][0], posList[i][1]);				
			} else if (i == 13) { // JAI
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(70, 64, 64), new Color(244, 255 ,255), new Color(70, 64, 64), "JAIL", posList[i][0], posList[i][1]);								
			} else if (i == 17) { // FUBELL
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "Rings", posList[i][0], posList[i][1]);								
			} else {
				landmarkList[i] = new Landmark(LinkList[i],nameList[i], new Color(115, 74, 18), new Color(250, 235, 215), new Color(115, 74, 18), moneyList[i], posList[i][0], posList[i][1]);
			}
			F1pos[i][0]=posList[i][0]-10;
			F1pos[i][1]=posList[i][1]-5;
			
			F2pos[i][0]=posList[i][0]+65;
			F2pos[i][1]=posList[i][1]-5;
			
			F3pos[i][0]=posList[i][0]-10;
			F3pos[i][1]=posList[i][1]+50;
			
			F4pos[i][0]=posList[i][0]+65;
			F4pos[i][1]=posList[i][1]+50;
			
			lacbelong[i]=-1;
		}
		
	}
	
	/**
	 * This method initailize player's name.
	 */
	private void initPlayerInfoList() {
		for (int i = 0; i < playerInfoList.length; i++) {
			playerInfoList[i] = new PlayerInfo(playerList[i]);
		}
	}
	
	/**
	 * This method initialize the layerPane.
	 * 
	 */
	private void initLayerPane() {
		
		//layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
		payfeeIcon.setBounds(1020, 495, 100, 100);
		// Map
		MAP.setIcon(new ImageIcon("images/Map/mapbackground.png"));// TODO map icon path
		//MAP.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
		MAP.setBounds(0, 0, mapWidth, mapHeight);
        layeredPane.add(MAP, new Integer(0));
		
        for (int i = 0; i < landmarkList.length; i++) {
        	layeredPane.add(landmarkList[i], new Integer(1));
        }
                
        ok.setFont(new Font("Arial Black", Font.BOLD, 35));
		ok.setBounds(875, 620, 100, 45);
      	//dice
		
		try {
			diceButton.setOpaque(true);
			diceButton.setIcon(new ImageIcon("images/Dice/dieRed1.png"));
			diceButton.setBounds(570, 200, 64, 64);
		} catch (Exception ex) {
		  System.out.println(ex);
		}
		layeredPane.add(diceButton, new Integer(1));
		
		//users' info
		user1.setOpaque(true);
		this.user1.setLayout(new GridLayout(2, 2, 0, 0));
		u1.setOpaque(true);
		this.u1.setBounds(30,465,100,100);
		this.u1.setBackground(new Color(255, 250,240));
		this.u1.setIcon(new ImageIcon("images/playerIcon/1.png"));
		layeredPane.add(u1, new Integer(1));
		
        this.user1.setBackground(new Color(255, 250,240));// TODO map icon path
		this.user1.setBounds(0, mapHeight, 350-1, 135-2);//442
		this.user1.setBorder(new MatteBorder(5, 5, 5, 5, new Color(205, 51, 51)));
		user1n=new JLabel(playerList[0].getName(),JLabel.CENTER);
		user1n.setFont(new Font("Dialog", Font.BOLD, 28));
		user1.add(new JLabel("",JLabel.CENTER));
		user1.add(user1n);
		user1.add(new JLabel("",JLabel.CENTER));
		money1=new JLabel("Money : "+playerList[0].getCash(),JLabel.CENTER);
		money1.setFont(new Font("Arial Black", Font.BOLD, 17));
		user1.add(money1);
        layeredPane.add(user1, new Integer(0));
        
        moneytext=new JLabel("-500",JLabel.CENTER);
        moneytext.setFont(new Font("Arial Black", Font.BOLD, 40));
        moneytext.setForeground(Color.red);
        moneytext.setBounds(210,495,100,50);//u1
        //moneytext.setBounds(570,495,100,50);//u2
        //moneytext.setBounds(210,630,100,50);//u3
        //moneytext.setBounds(570,630,100,50);//u4        
        
        user3.setOpaque(true);
        this.user3.setLayout(new GridLayout(2, 2, 0, 0));
		u3.setOpaque(true);
		this.u3.setBounds(30,600,100,100);
		this.u3.setBackground(new Color(255, 250,240));
		this.u3.setIcon(new ImageIcon("images/playerIcon/3.png"));
		layeredPane.add(u3, new Integer(1));
        this.user3.setBackground(new Color(255, 250,240));
        this.user3.setLayout(new GridLayout(2, 1, 0, 0));
		this.user3.setBounds(0, 585, 350-1, 135-2);//585
		user3n=new JLabel(playerList[2].getName(),JLabel.CENTER);
		user3.add(new JLabel("",JLabel.CENTER));
		user3.add(user3n);
		user3.add(new JLabel("",JLabel.CENTER));
		user3n.setFont(new Font("Dialog", Font.BOLD, 28));
		user3.setForeground(new Color(115, 74, 18));
		money3=new JLabel("Money : "+playerList[0].getCash(),JLabel.CENTER);
		money3.setFont(new Font("Arial Black", Font.BOLD, 17));
		user3.add(money3);
        layeredPane.add(user3, new Integer(0));
        
        user2.setOpaque(true);
        this.user2.setLayout(new GridLayout(2, 2, 0, 0));
		u2.setOpaque(true);
		this.u2.setBounds(380,465,100,100);
		this.u2.setBackground(new Color(255, 250,240));
		this.u2.setIcon(new ImageIcon("images/playerIcon/2.png"));
		layeredPane.add(u2, new Integer(1));
        this.user2.setBackground(new Color(255, 250,240));//this.user2.setIcon(new ImageIcon("images/Menu/menuBackground.png"));// TODO map icon path
		this.user2.setBounds(350,mapHeight, 350-1, 135-2);
		user2n=new JLabel(playerList[1].getName(),JLabel.CENTER);
		user2.add(new JLabel("",JLabel.CENTER));
		user2.add(user2n);
		user2.add(new JLabel("",JLabel.CENTER));
		user2n.setFont(new Font("Dialog", Font.BOLD, 28));
		user2.setForeground(new Color(115, 74, 18));
		money2=new JLabel("Money : "+playerList[0].getCash(),JLabel.CENTER);
		money2.setFont(new Font("Arial Black", Font.BOLD, 17));
		user2.add(money2);
        layeredPane.add(user2, new Integer(0));
        
        user4.setOpaque(true);
        this.user4.setLayout(new GridLayout(2, 2, 0, 0));
		u4.setOpaque(true);
		this.u4.setBounds(380,600,100,100);
		this.u4.setBackground(new Color(255, 250,240));
		this.u4.setIcon(new ImageIcon("images/playerIcon/4.png"));
		layeredPane.add(u4, new Integer(1));
        this.user4.setBackground(new Color(255, 250,240));
		this.user4.setBounds(350, 585, 350-1, 135-2);
		user4n=new JLabel(playerList[3].getName(),JLabel.CENTER);
		user4.add(new JLabel("",JLabel.CENTER));
		user4.add(user4n);
		user4.add(new JLabel("",JLabel.CENTER));
		user4n.setFont(new Font("Dialog", Font.BOLD, 28));
		user4.setForeground(new Color(115, 74, 18));
		money4=new JLabel("Money : "+playerList[0].getCash(),JLabel.CENTER);
		money4.setFont(new Font("Arial Black", Font.BOLD, 17));
		user4.add(money4);
        layeredPane.add(user4, new Integer(0));
        
        //Message's info
        
        Msg=new JLabel();
        Msg.setIcon(new ImageIcon("music/1000.png"));
        Msg.setFont(new Font("Arial Black", Font.BOLD, 40));
        Msg.setForeground(new Color(115, 74, 18));
        this.Msg.setBounds(702, mapHeight, 450-3, 270-2);//447 268
        Msg.setOpaque(true);
        Msg.setBackground(new Color(253, 245, 230));
        Msg.setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
        layeredPane.add(Msg, new Integer(1));
        
        // initFigure
 		F1.setOpaque(true);
 		F1.setBackground(new Color(205, 38, 38));
 		this.F1.setBounds(170,245,30,30);
 		layeredPane.add(F1, new Integer(2));
 		
 		F2.setOpaque(true);
 		this.F2.setBounds(245,245,30,30);
 		F2.setBackground(new Color(0 ,100, 80));
 		layeredPane.add(F2, new Integer(2));
 		
 		F3.setOpaque(true);
 		this.F3.setBounds(170,300,30,30);
 		F3.setBackground(new Color(24 ,116 ,205));
 		layeredPane.add(F3, new Integer(2));
 		
 		F4.setOpaque(true);
 		this.F4.setBounds(245,300,30,30);
 		F4.setBackground(new Color(255, 185, 15));
 		layeredPane.add(F4, new Integer(2));
        
 		end.setBounds(380, 150, 550, 300);
 		end.setLayout(new GridLayout(4, 1, 0 ,0));
 		end.setBackground(new Color(232, 232, 232, 100));
 		firstText.setFont(new Font("Dialog", Font.BOLD, 40));
 		firstText.setForeground(new Color(125, 38, 205));
 		secondText.setFont(new Font("Dialog", Font.BOLD, 40));
 		secondText.setForeground(new Color(125, 38, 205));
 		thirdText.setFont(new Font("Dialog", Font.BOLD, 40));
 		thirdText.setForeground(new Color(125, 38, 205));
 		fourthText.setFont(new Font("Dialog", Font.BOLD, 40));
 		fourthText.setForeground(new Color(125, 38, 205));
 		end.add(firstText);
 		end.add(secondText);
 		end.add(thirdText);
 		end.add(fourthText);
 		  
 		try {
 			endButton.setOpaque(false);
 	 		endButton.setIcon(new ImageIcon("images/Map/red_spuare.png"));
 	 		endButton.setLayout(new GridLayout(1, 1, 0, 0));	
 	 		endButton.setBounds(frameWidth/30, 400, 30, 30);
 		} catch (Exception ex) {
		    System.out.println(ex);
		}
 		layeredPane.add(endButton, new Integer(2));
        this.add(layeredPane);
	}
	
	/**
	 * This method set Figure's position.
	 * 
	 * @param playerList player's list
	 * 
	 */
	public Game(Player[] playerList) {
		r = new Random();
		this.playerList = playerList;
		initPanel();
		initLandmarkList();
		initPlayerInfoList();
		initLayerPane();
		myEvent();
		 yes.addMouseListener(ml);
  		 no.addMouseListener(ml); 
  		 ok.addMouseListener(ml);
  		 endButton.addMouseListener(ml);
  		 diceButton.addMouseListener(ml);
	}
	
	/**
	 * This is the class of the Landmark class for setting landmarks.
	 * 
	 * @author momo, tin, catherine, sophia
	 * @version 1.0
	 * @since 2017-05-31
	 */
	class Landmark extends JLabel {
		final static int width = 85, height = 75;
		private String name = null;
		private int money = 0;
		private int x = 0, y = 0;
		
		// for START
		public Landmark(String link,String name, Color textColor, Color bgColor, Color boardColor, int x, int y) {
			this.name = new String(name);
			this.money = 0;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText=new LinkLabel(name,link);
			nameText.setHorizontalAlignment(SwingConstants.CENTER);
			nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
			nameText.setForeground(textColor);
			this.setLayout(new GridLayout(1, 1, 0, 0));
			this.setOpaque(true);
	        this.setBackground(bgColor);
			this.setBorder(new MatteBorder(5, 5, 5, 5, boardColor));
			this.add(nameText);
			this.setBounds(this.x, this.y, width, height);
			
		}
		
		/**
		 * This method set special Landmark.
		 * 
		 * @param link the link of the land
		 * @param name the name of the land
		 * @param textColor the text color of the land
		 * @param bgColor the background color of the land
		 * @param boardColor the board color of the land
		 * @param x,y the coordinate
		 */
		public Landmark(String link,String name, Color textColor, Color bgColor, Color boardColor, String text, int x, int y) {
			this.name = new String(name);
			this.money = 0;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText=new LinkLabel(name,link);
			nameText.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		/**
		 * This method set department  Landmark.
		 * 
		 * @param link the link of the land
		 * @param name the name of the land
		 * @param textColor the text color of the land
		 * @param bgColor the background color of the land
		 * @param boardColor the board color of the land
		 * @param x,y the coordinate
		 */
		public Landmark(String link,String name, Color textColor, Color bgColor, Color boardColor, int money, int x, int y) {
			this.name = new String(name);
			this.money = money;
			this.x = x;
			this.y = y;
			
			JLabel nameText = new JLabel(this.name, JLabel.CENTER);
			nameText=new LinkLabel(name,link);
			nameText.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		/**
		 * This method default Landmark.
		 *
		 */
		public Landmark() {
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

		public void setBorder(Color color) {
			this.setBorder(color);
		}
	}
	
	/**
	 * This is the class of the PlayerInfo class for initial player's basic info.
	 * 
	 * @author momo, tin, catherine, sophia
	 * @version 1.0
	 * @since 2017-05-31
	 */
	class PlayerInfo extends JPanel {
		private Image background;
		Player player; // share the reference with 'player' when the game is playing
		private JLabel nameText = null;
		public PlayerInfo(Player player) {
			this.player = player;
			try {
				this.background = ImageIO.read(new File("images/playerIcon/1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
			this.nameText = new JLabel(player.getName());
			this.add(nameText);
		}
	
		
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(background, 0, 0, this);
	    }
	}
	
	private void updateDatabase() {
		for (int i = 0; i < 4; i++) {
			databaseUtil.insertUser(new User(playerList[i].getName(), playerList[i].getCash()));
		}
	}
	
	/**
	 * This method to action when button pressed.
	 *
	 */
	private void myEvent(){
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println(playerList[0].getCash());
			    System.out.println(playerList[1].getCash());
			    System.out.println(playerList[2].getCash());
			    System.out.println(playerList[3].getCash());
			    updateDatabase();
			    firstText.setText(playerList[0].getName()+"  "+playerList[0].getCash());
			    secondText.setText(playerList[1].getName()+"  "+playerList[1].getCash());
			    thirdText.setText(playerList[2].getName()+"  "+playerList[2].getCash());
			    fourthText.setText(playerList[3].getName()+"  "+playerList[3].getCash());
			    layeredPane.add(end, new Integer(3));
			    validate();
			    repaint();
			    AudioPlayer.player.stop(Menu.themeAS);
			    try {
			    	FileInputStream fileau = new FileInputStream("music/endgame.wav");
			    	AudioStream as = new AudioStream(fileau);
			    	AudioPlayer.player.start(as);
			    } catch (Exception a) {
			    	a.printStackTrace();
			    }
			}
			   
		});
		
		
		diceButton.addActionListener(
				new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        //  when the button is pressed
	    	  d.setDice();
	    	  try {
				     FileInputStream fileau = new FileInputStream("music/rolldice.wav" );
				     AudioStream as = new AudioStream(fileau);
				     AudioPlayer.player.start(as);
				    }catch (Exception a){
				     a.printStackTrace();
				    }
	    	  	layeredPane.remove(diceButton);
	    	  	rowing.setOpaque(true);
	    	  	rowing.setBounds(570, 200, 64, 64);
				layeredPane.add(rowing, new Integer(1));
	    	    rowing.setIcon(new ImageIcon(d.getDiceIcon()));
	    	    System.out.println(playerList[turn-1].getLocat()+" befoe");
	    	    System.out.println(turn);
	    	    System.out.println("T or F : "+playerList[turn-1].stop);
				if(playerList[turn-1].stop) {//studying
					playerList[turn-1].stop=false;
					Msg.setIcon(new ImageIcon("images/messege/study.jpg"));
					layeredPane.add(ok,new Integer(2));
				}else {
					playerList[turn-1].setLocat(d.getDice());
					setFpos(turn,playerList[turn-1].getLocat());
					System.out.println(playerList[turn-1].getLocat()+" after");
					if(playerList[turn-1].getLocat()==2) {//new gym
						Msg.setIcon(new ImageIcon("images/messege/newgym.jpeg"));
						playerList[turn-1].setCash(moneyList[playerList[turn-1].getLocat()]);
						layeredPane.add(ok,new Integer(2));
					}else if(playerList[turn-1].getLocat()==0) {
						Msg.setIcon(new ImageIcon("music/1000.png"));
						layeredPane.add(ok,new Integer(2));
					}
					else if(playerList[turn-1].getLocat()==19) {
						Msg.setIcon(new ImageIcon("images/messege/drama.jpeg"));
						playerList[turn-1].setCash(moneyList[playerList[turn-1].getLocat()]);
						layeredPane.add(ok,new Integer(2));
					}
					//chance and fate
					else if(playerList[turn-1].getLocat()==4||playerList[turn-1].getLocat()==8||playerList[turn-1].getLocat()==14) {
						if(playerList[turn-1].getLocat()==4) {
							toLake.setBounds(380,150,447,200);
							toLake.setIcon(new ImageIcon("images/messege/lake.jpeg"));
							playerList[turn-1].setCash(-200);
							layeredPane.add(toLake,new Integer(4));
						}
						System.out.println(count+" a");
						if(count==0) {
							Msg.setIcon(new ImageIcon("images/chancefate/1.jpeg"));
							playerList[turn-1].setCash(-500);
						}
						else if(count==1) {
							Msg.setIcon(new ImageIcon("images/chancefate/2.jpeg"));
							playerList[turn-1].stop=true;
						}
						else if(count==2) {
							Msg.setIcon(new ImageIcon("images/chancefate/3.jpeg"));
							//moneytext.setText("-800");
							playerList[turn-1].setCash(-800);
						}
						else if(count==3) {
							Msg.setIcon(new ImageIcon("images/chancefate/4.jpeg"));
							playerList[turn-1].setCash(-400);
						}
						else if(count==4) {
							Msg.setIcon(new ImageIcon("images/chancefate/5.jpeg"));
							playerList[turn-1].setCash(300);
						}
						else if(count==5) {
							Msg.setIcon(new ImageIcon("images/chancefate/6.jpeg"));
							playerList[turn-1].setCash(-500);
						}
						else if(count==6) {
							Msg.setIcon(new ImageIcon("images/chancefate/7.jpeg"));
							playerList[turn-1].setCash(200);
						}
						else if(count==7) {
							Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
							playerList[turn-1].setCash(300);
						}
						if(++count==8){
							count=0;
						}
						layeredPane.add(ok,new Integer(2));

					}else if(playerList[turn-1].getLocat()==13) {//Jail
						Msg.setIcon(new ImageIcon("images/messege/lib.jpeg"));
						playerList[turn-1].stop=true;
						layeredPane.add(ok,new Integer(2));
					}else if(playerList[turn-1].getLocat()==17) {//Fu-bell
						Msg.setIcon(new ImageIcon("images/messege/fubell.jpeg"));
						layeredPane.add(ok,new Integer(2));
					}else if(playerList[turn-1].getLocat()==11) {
						Msg.setIcon(new ImageIcon("images/messege/vm.jpeg"));
						moneytext.setText("+800");
						playerList[turn-1].setCash(800);
						layeredPane.add(ok,new Integer(2));
					}
					else {
						if(lacbelong[playerList[turn-1].getLocat()]==-1) {
							buyLand(turn,playerList[turn-1].getLocat());
						}else {
							if(lacbelong[playerList[turn-1].getLocat()]==turn-1){
								Msg.setIcon(new ImageIcon("images/messege/welcomehome.jpg"));//on your land
							}else {
								Msg.setIcon(new ImageIcon("images/messege/payfee.jpg"));//on play*'s land give the pass by fee 
								payfeeIcon.setIcon(new ImageIcon(Icons[lacbelong[playerList[turn-1].getLocat()]]));
								layeredPane.add(payfeeIcon,new Integer(2));
								playerList[lacbelong[playerList[turn-1].getLocat()]].setCash(moneyList[playerList[turn-1].getLocat()]/2);
								playerList[turn-1].setCash(-moneyList[playerList[turn-1].getLocat()]/2);
								transcation=true;
							}
							ok.setFont(new Font("Arial Black", Font.BOLD, 35));
							ok.setBounds(875, 620, 100, 45);
							layeredPane.add(ok,new Integer(2));
						}
					}
				}
			
	      }
	    });
	}
	
	/**
	 * This method is to process buying a landmark.
	 * 
	 * @param player the number of the player
	 * @param locate the location of the player is at 
	 */
	public void buyLand(int player,int locate) {
		switch(locate) {
		case 1:
			Msg.setIcon(new ImageIcon("images/messege/playground.jpeg"));
			break;
		case 3:
			Msg.setIcon(new ImageIcon("images/messege/oldgym.jpeg"));
			break;
		case 5:
			Msg.setIcon(new ImageIcon("images/messege/fmbuilding.jpeg"));
			break;
		case 6:
			Msg.setIcon(new ImageIcon("images/messege/computercenter.jpeg"));
			break;
		case 7:
			Msg.setIcon(new ImageIcon("images/messege/psy.jpeg"));
			break;
		case 9:
			Msg.setIcon(new ImageIcon("images/messege/csie.jpeg"));
			break;
		case 10:
			Msg.setIcon(new ImageIcon("images/messege/ee.jpeg"));
			break;
		case 12:
			Msg.setIcon(new ImageIcon("images/messege/esoe.jpeg"));
			break;
		case 15:
			Msg.setIcon(new ImageIcon("images/messege/forest.jpeg"));
			break;
		case 16:
			Msg.setIcon(new ImageIcon("images/messege/horticulture.jpeg"));
			break;
		case 18:
			Msg.setIcon(new ImageIcon("images/messege/physics.jpeg"));
			break;
		}
		if(playerList[turn-1].getCash()-moneyList[locate]>0) {
			yes.setFont(new Font("Arial Black", Font.BOLD, 35));
			yes.setBounds(770, 620, 100, 45);
			layeredPane.add(yes,new Integer(2));
		}	
		no.setFont(new Font("Arial Black", Font.BOLD, 35));
		no.setBounds(980, 620, 100, 45);
		layeredPane.add(no,new Integer(2));
	}
	
	MouseListener ml = new MouseAdapter() {
		/**
		 * This method is to show when mouse entered
		 * 
		 * @param e event
		 */
		public void mouseEntered(MouseEvent e) {
				if(e.getSource() == yes) {
					JLabel l = (JLabel)e.getSource();
					l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					l.setForeground(Color.red);
				} else if(e.getSource() == no){
					JLabel l = (JLabel)e.getSource();
					l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					l.setForeground(Color.red);
				}  else if(e.getSource() == ok){
					JLabel l = (JLabel)e.getSource();
					l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					l.setForeground(Color.red);
				} else if(e.getSource() ==diceButton) {
					try {
					     FileInputStream fileau = new FileInputStream("music/click3.wav" );
					     AudioStream as = new AudioStream(fileau);
					     AudioPlayer.player.start(as);
					    }catch (Exception a){
					     a.printStackTrace();
					    }
				}
		}
		
		/**
		 * This method is to show when mouse exited
		 * 
		 * @param e event
		 */
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == yes) {
				JLabel l = (JLabel)e.getSource();
				l.setForeground(Color.black);
			} else if(e.getSource() == no){
				JLabel l = (JLabel)e.getSource();
				l.setForeground(Color.black);
			} else if(e.getSource() == ok){
				JLabel l = (JLabel)e.getSource();
				l.setForeground(Color.black);
			} 
		}
		
		/**
		 * This method is to show when mouse clicked
		 * 
		 * @param e event
		 */
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == yes) {
				playerList[turn-1].setCash(-moneyList[playerList[turn-1].getLocat()]);
				lacbelong[playerList[turn-1].getLocat()]=turn-1;
				moneytext.setText("-"+moneyList[playerList[turn-1].getLocat()]);
				validate();
				repaint();
				layeredPane.remove(yes);
				layeredPane.remove(no);
				yes.setForeground(Color.black);
				switch(turn) {
				case 1:
					landmarkList[playerList[turn-1].getLocat()].setBorder(new MatteBorder(5, 5, 5, 5,new Color(205,38,38)));
					break;
				case 2:
					landmarkList[playerList[turn-1].getLocat()].setBorder(new MatteBorder(5, 5, 5, 5,new Color(0,100,80)));
					break;
				case 3:
					landmarkList[playerList[turn-1].getLocat()].setBorder(new MatteBorder(5, 5, 5, 5,new Color(24,116,205)));
					break;
				case 4:
					landmarkList[playerList[turn-1].getLocat()].setBorder(new MatteBorder(5, 5, 5, 5,new Color(255,185,15)));
					break;
				}
				//validate();
				repaint();
				try {
				     FileInputStream fileau = new FileInputStream("music/casher.wav" );
				     AudioStream as = new AudioStream(fileau);
				     AudioPlayer.player.start(as);
				    }catch (Exception a){
				     a.printStackTrace();
				    }
			} else if(e.getSource() == no){
				try {
				     FileInputStream fileau = new FileInputStream("music/click3.wav" );
				     AudioStream as = new AudioStream(fileau);
				     AudioPlayer.player.start(as);
				    }catch (Exception a){
				     a.printStackTrace();
				    }
				moneytext.setText("");
				validate();
				repaint();
				no.setForeground(Color.black);
				layeredPane.remove(yes);
				layeredPane.remove(no);
			} else if(e.getSource() == ok) {
				validate();
				repaint();
				if(playerList[turn-1].getLocat()==4) {
					layeredPane.remove(toLake);
				}
				layeredPane.remove(ok);
				layeredPane.remove(payfeeIcon);
				if(transcation) {
					try {
					     FileInputStream fileau = new FileInputStream("music/casher.wav" );
					     AudioStream as = new AudioStream(fileau);
					     AudioPlayer.player.start(as);
					    }catch (Exception a){
					     a.printStackTrace();
					    }
					transcation=false;
				}else {
					try {
					     FileInputStream fileau = new FileInputStream("music/click3.wav" );
					     AudioStream as = new AudioStream(fileau);
					     AudioPlayer.player.start(as);
					    }catch (Exception a){
					     a.printStackTrace();
					    }
					ok.setForeground(Color.black);
				}
			}
			money1.setText("Money : "+playerList[0].getCash());
			money2.setText("Money : "+playerList[1].getCash());		
			money3.setText("Money : "+playerList[2].getCash());	
			money4.setText("Money : "+playerList[3].getCash());

			if(++turn==5) turn=1;
			switch(turn) {
			case 1:
				user4.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 250,240)));
				user1.setBorder(new MatteBorder(5, 5, 5, 5, new Color(205, 51, 51)));
				break;
			case 2:
				user1.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 250,240)));
				user2.setBorder(new MatteBorder(5, 5, 5, 5, new Color(205, 51, 51)));
				break;
			case 3:
				user2.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 250,240)));
				user3.setBorder(new MatteBorder(5, 5, 5, 5, new Color(205, 51, 51)));
				break;
			case 4:
				user3.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 250,240)));
				user4.setBorder(new MatteBorder(5, 5, 5, 5, new Color(205, 51, 51)));
				break;
			}
			Msg.setIcon(new ImageIcon("music/1000.png"));
			layeredPane.remove(rowing);
			layeredPane.add(diceButton,new Integer(2));
    	    diceButton.setIcon(new ImageIcon(d.getDiceIcon()));
		}					
	};
	
	/**
	 * This the main function
	 */
	public static void main(String[] args) {
		final int frameWidth = 1152, frameHeight = 740;
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (Game test)");
 		f.setSize(frameWidth, frameHeight);
 		f.setResizable(false);
 		f.setLocationRelativeTo(null);
 		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        
        Player[] list = new Player[4];
        for (int i = 0; i < 4; i++) {
        	list[i] = new Player("Player"+(i+1));
        }
        
		f.add(new Game(list));
		f.setVisible(true);
		
	}

}
