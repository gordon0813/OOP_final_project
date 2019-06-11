import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.event.*;
public class Game extends JPanel {
	final int frameWidth = 1152, frameHeight = 780;
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
	//user frame
	private PlayerInfo[] playerInfoList = new PlayerInfo[4];
	private JLabel msg = new Message();
	private JLabel moneytext =new JLabel();
	
	private JLabel u1 = new JLabel();
	private JLabel u2 = new JLabel();
	private JLabel u3 = new JLabel();
	private JLabel u4 = new JLabel();
	
	private JLabel user1 = new JLabel();
	private JLabel user2 = new JLabel();
	private JLabel user3 = new JLabel();
	private JLabel user4 = new JLabel();
	
	private JLabel user1n = new JLabel();
	private JLabel user2n = new JLabel();
	private JLabel user3n = new JLabel();
	private JLabel user4n = new JLabel();
	
	private JLabel F1 = new JLabel();//Figure 1
	private JLabel F2 = new JLabel();//Figure 2
	private JLabel F3 = new JLabel();//Figure 3
	private JLabel F4 = new JLabel();//Figure 4
	
	private JLabel yes = new JLabel("Yes",JLabel.CENTER);
	private JLabel no = new JLabel("No",JLabel.CENTER);
	
	private JLabel money1,money2,money3,money4;
	private JLabel Msg = new JLabel();
	private int[][] F1pos=new int[20][2];
	private int[][] F2pos=new int[20][2];
	private int[][] F3pos=new int[20][2];
	private int[][] F4pos=new int[20][2];
	private int[] moneyList = {0, 500, -200, 300, -200, 800, 1000, 1200, 0, 2500, 2000, 800, 3000, 0, 0, 900, 1500, 0, 1200, -500};
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}
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
	private void initLandmarkList() {
		//					 0		  1		  2		  3		   4		5		6		7		 8		  9		   10		11		 12			13		 14			15		 16		 17		  18	   19
		String[] nameList = {"START", "操 場", "新 體", "舊 體", "醉月湖", "新 生", "計 中", "心理系", "應力所", "資工系", "電機系", "獸醫系", "我大工海", "圖書館", "健康中心", "森林系", "園藝系", "傅 鐘", "物理系", "戲劇系"};
		int[][] posList = {{180, 250}, {180, 150}, {180, 30}, {375, 30}, {500, 30}, {600, 30}, {700, 30}, {800, 30}, {900, 30}, {1000, 30}, {1000, 120}, {1000, 210}, {1000, 340}, {900, 340}, {750, 340}, {660, 340}, {505, 340}, {405, 340}, {300, 340}, {180, 340}};
		
		for (int i = 0; i < 20; i++) {
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
	
	private void initPlayerInfoList() {
		for (int i = 0; i < playerInfoList.length; i++) {
			playerInfoList[i] = new PlayerInfo(playerList[i]);
		}
	}
	
	private void initLayerPane() {
		//layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

		// Map
		MAP.setIcon(new ImageIcon("images/Map/mapbackground.png"));// TODO map icon path
		//MAP.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
		MAP.setBounds(0, 0, mapWidth, mapHeight);
        layeredPane.add(MAP, new Integer(0));
		
        for (int i = 0; i < landmarkList.length; i++) {
        	layeredPane.add(landmarkList[i], new Integer(1));
        }
        
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
		user1n.setFont(new Font("Arial Black", Font.BOLD, 28));
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
        //layeredPane.add(moneytext, new Integer(2));
        
        
        
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
		user3n.setFont(new Font("Arial Black", Font.BOLD, 28));
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
		user2n.setFont(new Font("Arial Black", Font.BOLD, 28));
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
		user4n.setFont(new Font("Arial Black", Font.BOLD, 28));
		user4.setForeground(new Color(115, 74, 18));
		money4=new JLabel("Money : "+playerList[0].getCash(),JLabel.CENTER);
		money4.setFont(new Font("Arial Black", Font.BOLD, 17));
		user4.add(money4);
        layeredPane.add(user4, new Integer(0));
        
        //Message's info
        
       //this.Msg.setIcon(new ImageIcon("images/Menu/menuBackground.png"));// TODO map icon path
        Msg=new JLabel("NTU Monopoly",JLabel.CENTER);
        Msg.setFont(new Font("Arial Black", Font.BOLD, 40));
        Msg.setForeground(new Color(115, 74, 18));
        this.Msg.setBounds(702, mapHeight, 450-3, 270-2);//447 268
        Msg.setOpaque(true);
        Msg.setBackground(new Color(253, 245, 230));
        Msg.setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
        layeredPane.add(Msg, new Integer(0));
        
        // initFigure
 		F1.setOpaque(true);
 		F1.setBackground(new Color(205, 38, 38));
 		this.F1.setBounds(170,245,30,30);
 		//this.F1.setIcon(new ImageIcon("images/playerIcon/1.png"));
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
        
        this.add(layeredPane);
	}
	
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
		Msg.addMouseListener(ml);
		
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
	
	private void myEvent(){
		diceButton.addActionListener(
				new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        //  when the button is pressed
	    	  //JLabel rowing=new JLabel();
	    	 
	    	 Msg.setText("");
	    	  //layeredPane.remove(Msg);
	    	  Dice d=new Dice();
	    	  d.setDice();
	    	  
				diceButton.setIcon(new ImageIcon(d.getDiceIcon()));
				if(playerList[turn-1].stop) {
					playerList[turn-1].stop=false;
					Msg.setText("Studying!!!");
				}else {
					playerList[turn-1].setLocat(d.getDice());
					setFpos(turn,playerList[turn-1].getLocat());
					if(playerList[turn-1].getLocat()==2) {//new gym
						Msg.setIcon(new ImageIcon("images/chancefate/1.jpeg"));
						playerList[turn-1].setCash(moneyList[playerList[turn-1].getLocat()]);
						
					}
					else if(playerList[turn-1].getLocat()==19) {
						Msg.setIcon(new ImageIcon("images/chancefate/1.jpeg"));
						playerList[turn-1].setCash(moneyList[playerList[turn-1].getLocat()]);
					}
					//chance and fate
					else if(playerList[turn-1].getLocat()==4||playerList[turn-1].getLocat()==11||playerList[turn-1].getLocat()==8||playerList[turn-1].getLocat()==14) {
						if(playerList[turn-1].getLocat()==4) {
							Msg.setIcon(new ImageIcon("images/chancefate/1.jpeg"));
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							playerList[turn-1].setCash(-200);
						}
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
							moneytext.setText("-800");
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

					}else if(playerList[turn-1].getLocat()==13) {//Jail
						Msg.setText("");
						Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
						playerList[turn-1].stop=true;
						//TODO
					}else if(playerList[turn-1].getLocat()==17) {//Fu-bell
						Msg.setText("");
						Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
					}
					else {
						if(lacbelong[playerList[turn-1].getLocat()]==-1) {
							buyLand(moneyList[playerList[turn-1].getLocat()]);
						}else {
							if(lacbelong[playerList[turn-1].getLocat()]==turn-1){
								Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));//on your land
							}else {
								Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));//on play*'s land give the pass by fee 
								playerList[lacbelong[playerList[turn-1].getLocat()]].setCash(moneyList[playerList[turn-1].getLocat()]/2);
								playerList[turn-1].setCash(-moneyList[playerList[turn-1].getLocat()]/2);
							}
							
						}
					}
				}
			
	      }
	    });
		}
	public void buyLand(int n) {
		Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
		yes.setFont(new Font("Arial Black", Font.BOLD, 35));
		no.setFont(new Font("Arial Black", Font.BOLD, 35));
		yes.setBounds(770, 620, 100, 45);
		no.setBounds(980, 620, 100, 45);
		layeredPane.add(yes,new Integer(2));
		layeredPane.add(no,new Integer(2));
	}

	class Message extends JLabel {	
		//private JLabel text =new JLabel("NTU Monopoly",JLabel.CENTER);
		public Message() {}
			
		public void chanceNFate(int player){
			//Msg.setBounds(702, mapHeight, 450-3, 270-2);//447 268
			if(count==0) {
				layeredPane.remove(Msg);
				Msg.setIcon(new ImageIcon("images/chancefate/1.jpeg"));
				playerList[player].setCash(-500);
				layeredPane.add(Msg, new Integer(0));
			}
			else if(count==1) {
				Msg.setIcon(new ImageIcon("images/chancefate/2.jpeg"));
				playerList[player].stop=true;
			}
			else if(count==2) {
				Msg.setIcon(new ImageIcon("images/chancefate/3.jpeg"));
				playerList[player].setCash(-800);
			}
			else if(count==3) {
				Msg.setIcon(new ImageIcon("images/chancefate/4.jpeg"));
				playerList[player].setCash(-400);
			}
			else if(count==4) {
				Msg.setIcon(new ImageIcon("images/chancefate/5.jpeg"));
				playerList[player].setCash(300);
			}
			else if(count==5) {
				Msg.setIcon(new ImageIcon("images/chancefate/6.jpeg"));
				playerList[player].setCash(-500);
			}
			else if(count==6) {
				Msg.setIcon(new ImageIcon("images/chancefate/7.jpeg"));
				playerList[player].setCash(200);
			}
			else if(count==7) {
				Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
				playerList[player].setCash(300);
			}
			if(++count==8){
				count=0;
			}
	        Msg.setOpaque(true);
	        
		}
		public void jail(int player){
			playerList[player].stop=true;
			Msg.setIcon(new ImageIcon("images/chancefate/8.jpeg"));
			Msg.add(yes);
		}
		public void fubell() {
			
			
		}
		public void others() {
			
		}
	}
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
			if(e.getSource() == yes) {
				playerList[turn-1].setCash(-moneyList[playerList[turn-1].getLocat()]);
				lacbelong[playerList[turn-1].getLocat()]=turn-1;
				moneytext.setText("-"+moneyList[playerList[turn-1].getLocat()]);
				validate();
				repaint();
				layeredPane.remove(yes);
				layeredPane.remove(no);
			} else if(e.getSource() == no){
				moneytext.setText("");
				validate();
				repaint();
				layeredPane.remove(yes);
				layeredPane.remove(no);
				
			} else if(e.getSource() == Msg) {
				validate();
				repaint();
			}
			switch(turn) {
			case 1:
				money1.setText("Money : "+playerList[0].getCash());
			case 2:
				money2.setText("Money : "+playerList[1].getCash());
			case 3:
				money3.setText("Money : "+playerList[2].getCash());
			case 4:
				money4.setText("Money : "+playerList[3].getCash());
			}	
			switch(lacbelong[playerList[turn-1].getLocat()]+1) {
			case 1:
				money1.setText("Money : "+playerList[0].getCash());
			case 2:
				money2.setText("Money : "+playerList[1].getCash());
			case 3:
				money3.setText("Money : "+playerList[2].getCash());
			case 4:
				money4.setText("Money : "+playerList[3].getCash());
			}	
			
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
			Msg.setText("");
			
		}					
	};
	
	
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
