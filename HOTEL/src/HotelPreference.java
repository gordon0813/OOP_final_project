import java.util.Timer;
import java.util.TimerTask;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class HotelPreference extends JFrame {
	final private int frameWidth = 1152, frameHeight = 720;

	// Program constructor
	public HotelPreference() {
		initFrame();
		Menu menu = new Menu();
		this.add(menu);

		this.setVisible(true);
	}

	// Initialize the frame
	private void initFrame() {
		this.setTitle("HOTEL");
		this.setSize(frameWidth, frameHeight);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	}

	// the Menu class
	class Menu extends JPanel {
		private JLayeredPane layeredPane;
		private JLabel background = new JLabel();

		// attribute of title
		private JPanel title = new JPanel();
		final private int titleWidth = 930, titleHeight = 80;
		final private Dimension titleCenter = new Dimension(frameWidth / 2, frameHeight / 4);
		private JLabel titleText = new JLabel("== TRIANGLE ==", JLabel.CENTER);

		// attribute of sub menu
		private JPanel subMenu = new JPanel();
		final private int subMenuWidth = 500, subMenuHeight = 70;
		final private Dimension subMenuCenter = new Dimension(frameWidth / 2, 524);
		private JLabel signinText = new JLabel("SIGN IN", JLabel.CENTER);
		private JLabel signupText = new JLabel("SIGN UP", JLabel.CENTER);

		// attribute of sign in
		private JPanel signin = new JPanel();
		final private int signinSetWidth = 500, signinSetHeight = 250;
		final private Dimension signinSetCenter = new Dimension(frameWidth / 2, 524);
		private JLabel login = new JLabel("LOGIN", JLabel.CENTER);
		private JLabel back = new JLabel("BACK", JLabel.CENTER);

		// attribute of sign up
		private JPanel signup = new JPanel();
		final private int signupSetWidth = 500, signupSetHeight = 250;
		final private Dimension signupSetCenter = new Dimension(frameWidth / 2, 524);
		private JLabel signuplogin = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
		private JLabel cancel = new JLabel("CANCEL", JLabel.CENTER);

		// attribute of Hotelfunction Hotel list/Reserve/Inquiry
		private JPanel Hotelfunction = new JPanel();
		final private int hrriWidth = 500, hrriHeight = 200;
		final private Dimension hrriCenter = new Dimension(frameWidth / 2, 524);
		private JLabel hotellistText = new JLabel("HOTEL LIST", JLabel.CENTER);
		private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
		private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
		private JLabel logouthf = new JLabel("LOGOUT", JLabel.CENTER);

		// attribute of hotel list
		private JPanel Hotellist = new JPanel();
		final private int hotellistWidth = 570, hotellistHeight = 250;
		final private Dimension hotellistCenter = new Dimension(frameWidth / 2, 524);
		private JLabel Text5 = new JLabel("5-star", JLabel.CENTER);
		private JLabel Text4 = new JLabel("4-star", JLabel.CENTER);
		private JLabel Text3 = new JLabel("3-star", JLabel.CENTER);
		private JLabel Text2 = new JLabel("2-star", JLabel.CENTER);
		private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
		private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
		private JLabel backhotellist = new JLabel("BACK", JLabel.CENTER);
		
		//attribute of inquiry
		private JPanel Inquiry = new JPanel();
		final private int InquiryWidth = 600, InquiryHeight = 150;
		final private Dimension InquiryCenter = new Dimension(frameWidth / 2, 524);
		private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
		
		//attribute of modify and cancel reservation
		private JPanel MCR = new JPanel();
		final private int mcrWidth = 500, mcrHeight = 200;
		final private Dimension mcrCenter = new Dimension(frameWidth / 2, 524);
		private JLabel modifyText = new JLabel("MODIFY RESERVATION", JLabel.CENTER);
		private JLabel cancelText = new JLabel("CANCEL RESERVATION", JLabel.CENTER);
		private JLabel backmcr = new JLabel("BACK", JLabel.CENTER);


		// Menu(Panel) settings
		private void initPanel() {
			setLayout(new GridLayout(1, 1));
			setOpaque(false);
		}

		private void initFonts() {
			titleText.setFont(new Font("Arial Black", Font.BOLD, 60));
			signinText.setFont(new Font("Arial Black", Font.BOLD, 30));
			signupText.setFont(new Font("Arial Black", Font.BOLD, 30));
		}

		private void initTitle() {
			title.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			title.setLayout(new GridLayout(1, 1, 0, 0));
			title.setOpaque(true);

			titleText.setForeground(new Color(65, 105, 225));
			titleText.setOpaque(true);
			titleText.setBackground(new Color(176, 196, 222));
			titleText.setBorder(new MatteBorder(5, 5, 5, 5, new Color(61, 89, 171)));

			title.add(titleText);
		}

		// sign in
		private void initSignIn() {
			signin.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			signin.setLayout(new GridLayout(3, 1));
			

			// enter ID
			JPanel IDPanel = new JPanel();
			JLabel ID = new JLabel("       ID       : ");
			ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField IDField = new TextField(15);
			IDField.setFont(new Font("Serif", Font.BOLD, 23));
			
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			IDPanel.setBackground(new Color(176, 196, 222));
			
			IDPanel.add(ID);
			IDPanel.add(IDField);
			
			// enter password
			JPanel passwordPanel = new JPanel();
			JLabel password = new JLabel("PASSWORD : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			JPasswordField passwordField = new JPasswordField(10);
			passwordField.setFont(new Font("Serif", Font.BOLD, 23));
			
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));
			
			passwordPanel.add(password);
			passwordPanel.add(passwordField);

			// set 'back' and 'login' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			login.setFont(new Font("Arial Black", Font.PLAIN, 15));
			back.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.add(back);
			buttons.add(login);
			
			signin.add(IDPanel);
			signin.add(passwordPanel);
			signin.add(buttons);
		}

		// sign up
		private void initSignUp() {
			signup.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			signup.setLayout(new GridLayout(4, 1));
			
			// set ID
			JPanel IDPanel = new JPanel();
			JLabel ID = new JLabel("         ID         : ");
			ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField IDField = new TextField(15);
			
			IDField.setFont(new Font("Serif", Font.BOLD, 23));
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			IDPanel.setBackground(new Color(176, 196, 222));
			
			IDPanel.add(ID);
			IDPanel.add(IDField);

			// set password
			JPanel passwordPanel = new JPanel();
			JLabel password = new JLabel("  PASSWORD   : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			JPasswordField passwordField = new JPasswordField(10);
			passwordField.setFont(new Font("Serif", Font.BOLD, 23));
			
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));
			
			passwordPanel.add(password);
			passwordPanel.add(passwordField);

			// enter verify code
			JPanel verifycodePanel = new JPanel();
			JLabel verifycode = new JLabel("VERIFY CODE : ");
			verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField verifycodeField = new TextField(15);
			verifycodeField.setFont(new Font("Serif", Font.BOLD, 23));
			verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			verifycodePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			verifycodePanel.setBackground(new Color(176, 196, 222));
			verifycodePanel.add(verifycode);
			verifycodePanel.add(verifycodeField);

			// set 'cancel' and 'sign up and login' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			signuplogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
			cancel.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.add(cancel);
			buttons.add(signuplogin);
			
			signup.add(IDPanel);
			signup.add(passwordPanel);
			signup.add(verifycodePanel);
			signup.add(buttons);

		}

		// hotel list/reserve/inquiry
		private void initHotelfunction() {
			hotellistText.setFont(new Font("Arial Black", Font.BOLD, 30));
			reserveText.setFont(new Font("Arial Black", Font.BOLD, 30));
			inquiryText.setFont(new Font("Arial Black", Font.BOLD, 30));
			logouthf.setFont(new Font("Arial Black", Font.BOLD, 30));

			Hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
			Hotelfunction.setOpaque(true);
			Hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Hotelfunction.setBackground(new Color(176, 196, 222));
			Hotelfunction.add(hotellistText);
			Hotelfunction.add(reserveText);
			Hotelfunction.add(inquiryText);
			Hotelfunction.add(logouthf);

		}

		// hotel list
		private void initHotellist() {
			//set font
			Text5.setFont(new Font("Arial Black", Font.BOLD, 28));
			Text4.setFont(new Font("Arial Black", Font.BOLD, 28));
			Text3.setFont(new Font("Arial Black", Font.BOLD, 28));
			Text2.setFont(new Font("Arial Black", Font.BOLD, 28));
			pricehighText.setFont(new Font("Arial Black", Font.BOLD, 28));
			pricelowText.setFont(new Font("Arial Black", Font.BOLD, 28));
			backhotellist.setFont(new Font("Arial Black", Font.BOLD, 28));
			
			Hotellist.setLayout(new GridLayout(4, 1, 0, 0));
			Hotellist.setOpaque(true);
			Hotellist.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Hotellist.setBackground(new Color(176, 196, 222));
			
			JPanel star = new JPanel();
			star.setLayout(new GridLayout(1, 4, 0, 0));
			star.setBackground(new Color(176, 196, 222));
			star.add(Text5);
			star.add(Text4);
			star.add(Text3);
			star.add(Text2);

			Hotellist.add(star);
			Hotellist.add(pricehighText);
			Hotellist.add(pricelowText);
			Hotellist.add(backhotellist);
		}
		
		//inquiry
		private void initInquiry() {
			Inquiry.setLayout(new GridLayout(2, 1, 0, 0));
			Inquiry.setOpaque(true);
			Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Inquiry.setBackground(new Color(176, 196, 222));
			//enter reserve number
			JPanel reservenumberPanel = new JPanel();
			JLabel reservenumber = new JLabel("RESERVATION NUMBER : ");
			reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField resnumField = new TextField(15);
			resnumField.setFont(new Font("Serif", Font.BOLD, 23));
			
			reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			reservenumberPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			reservenumberPanel.setBackground(new Color(176, 196, 222));
			
			reservenumberPanel.add(reservenumber);
			reservenumberPanel.add(resnumField);
			
			//set 'back' and 'next' buttons
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.setBackground(new Color(176, 196, 222));
			backinquiry.setFont(new Font("Arial Black", Font.PLAIN, 15));
			nextinquiry.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.add(backinquiry);
			buttons.add(nextinquiry);
			
			Inquiry.add(reservenumberPanel);
			Inquiry.add(buttons);
		}
		
		//MCR
		private void initMCR() {
			modifyText.setFont(new Font("Arial Black", Font.BOLD, 30));
			cancelText.setFont(new Font("Arial Black", Font.BOLD, 30));
			backmcr.setFont(new Font("Arial Black", Font.BOLD, 30));	
			
			MCR.setLayout(new GridLayout(3, 1, 0, 0));
			MCR.setOpaque(true);
			MCR.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			MCR.setBackground(new Color(176, 196, 222));
			
			MCR.add(modifyText);
			MCR.add(cancelText);
			MCR.add(backmcr);
		}
		

		// sub menu
		private void initSubMenu() {
			subMenu.setLayout(new GridLayout(1, 2, 0, 0));
			subMenu.setOpaque(true);
			subMenu.setBackground(new Color(176, 196, 222));
			subMenu.add(signinText);
			subMenu.add(signupText);
			subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		}

		private void initLayerPane() {
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

			this.background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
			this.background.setBounds(0, 0, frameWidth, frameHeight);
			layeredPane.add(background, new Integer(0));

			this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2),
					titleWidth, titleHeight);
			layeredPane.add(title, new Integer(1));

			this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
					subMenuWidth, subMenuHeight);
			layeredPane.add(subMenu, new Integer(2));

			this.add(layeredPane);

			this.signin.setBounds(signinSetCenter.width - (signinSetWidth / 2),
					signinSetCenter.height - (signinSetHeight / 2), signinSetWidth, signinSetHeight);

			this.signup.setBounds(signupSetCenter.width - (signupSetWidth / 2),
					signupSetCenter.height - (signupSetHeight / 2), signupSetWidth, signupSetHeight);

			this.Hotelfunction.setBounds(hrriCenter.width - (hrriWidth / 2), hrriCenter.height - (hrriHeight / 2), hrriWidth,
					hrriHeight);

			this.Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
					hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);

			this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2),
					InquiryCenter.height - (InquiryHeight / 2), InquiryWidth, InquiryHeight);
			
			this.MCR.setBounds(mcrCenter.width - (mcrWidth / 2),
					mcrCenter.height - (mcrHeight / 2), mcrWidth, mcrHeight);
		}

		public Menu() {
			initPanel();
			initFonts();
			initTitle();
			initSubMenu();
			initSignIn();
			initSignUp();
			initHotelfunction();
			initHotellist();
			initInquiry();
			initMCR();
			initLayerPane();
			//buttons in sub menu / sign in / sign up
			signinText.addMouseListener(ml);
			signupText.addMouseListener(ml);
			back.addMouseListener(ml);
			login.addMouseListener(ml);
			cancel.addMouseListener(ml);
			signuplogin.addMouseListener(ml);
			//buttons in hotel function
			hotellistText.addMouseListener(ml);
			reserveText.addMouseListener(ml);
			inquiryText.addMouseListener(ml);
			logouthf.addMouseListener(ml);
			//buttons in hotel list
			Text5.addMouseListener(ml);
			Text4.addMouseListener(ml);
			Text3.addMouseListener(ml);
			Text2.addMouseListener(ml);
			pricehighText.addMouseListener(ml);
			pricelowText.addMouseListener(ml);
			backhotellist.addMouseListener(ml);
			//buttons in inquiry
			backinquiry.addMouseListener(ml);
			nextinquiry.addMouseListener(ml);
			//buttons in modify and cancel reservation
			modifyText.addMouseListener(ml);
			cancelText.addMouseListener(ml);
			backmcr.addMouseListener(ml);
		};

		MouseListener ml = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				l.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel) e.getSource();
				l.setForeground(Color.black);
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == signupText) {
					layeredPane.remove(subMenu);
					layeredPane.add(signup, new Integer(2));
					validate();
					repaint();
					signupText.setForeground(Color.black);
				} else if (e.getSource() == cancel) {
					layeredPane.remove(signup);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					cancel.setForeground(Color.black);
				} else if (e.getSource() == signuplogin) {
					layeredPane.remove(signup);
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signuplogin.setForeground(Color.black);
				} else if (e.getSource() == signinText) {
					layeredPane.remove(subMenu);
					layeredPane.add(signin, new Integer(2));
					validate();
					repaint();
					signinText.setForeground(Color.black);
				} else if (e.getSource() == back) {
					layeredPane.remove(signin);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					back.setForeground(Color.black);
				} else if (e.getSource() == login) {
					layeredPane.remove(signin);
					layeredPane.add(Hotelfunction,new Integer(2));
					validate();
					repaint();
					login.setForeground(Color.black);
				} else if (e.getSource() == logouthf) {
					layeredPane.remove(Hotelfunction);
					layeredPane.remove(signin);
					layeredPane.remove(signup);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					logouthf.setForeground(Color.black);
				} else if (e.getSource() == hotellistText) {
					layeredPane.remove(Hotelfunction);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					hotellistText.setForeground(Color.black);
				} else if (e.getSource() == backhotellist || e.getSource() == backinquiry || e.getSource() == backmcr) {
					layeredPane.remove(Hotellist);
					layeredPane.remove(Inquiry);
					layeredPane.remove(MCR);
					layeredPane.add(Hotelfunction);
					validate();
					repaint();
					backhotellist.setForeground(Color.black);
					backinquiry.setForeground(Color.black);
					backmcr.setForeground(Color.black);
				} else if (e.getSource() == inquiryText) {
					layeredPane.remove(Hotelfunction);
					layeredPane.add(Inquiry, new Integer(3));
					validate();
					repaint();
					inquiryText.setForeground(Color.black);
				} 
				else if (e.getSource() == nextinquiry) {
					layeredPane.remove(Inquiry);
					layeredPane.add(MCR, new Integer(3));
					validate();
					repaint();
				}

			}
		};

	}

	public static void main(String[] args) {
		HotelPreference program = new HotelPreference();
	}
}
