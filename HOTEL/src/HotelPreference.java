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
	protected int userid;

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
		final private int signinSetWidth = 500, signinSetHeight = 180;
		final private Dimension signinSetCenter = new Dimension(frameWidth / 2, 524);
		private JLabel login = new JLabel("LOGIN", JLabel.CENTER);
		private JLabel back = new JLabel("BACK", JLabel.CENTER);

		// attribute of sign up
		private JPanel signup = new JPanel();
		final private int signupSetWidth = 500, signupSetHeight = 240;
		final private Dimension signupSetCenter = new Dimension(frameWidth / 2, 524);
		private JLabel signuplogin = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
		private JLabel cancel = new JLabel("CANCEL", JLabel.CENTER);

		// attribute of Hotel function Hotel list/Reserve/Inquiry
		private JPanel Hotelfunction = new JPanel();
		final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
		final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, 524);
		private JLabel hotellistText = new JLabel("HOTEL LIST", JLabel.CENTER);
		private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
		private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
		private JLabel logouthf = new JLabel("LOGOUT", JLabel.CENTER);

		// attribute of entering hotel list date, people, rooms
		private JPanel EnterHotellist = new JPanel();
		final private int enterhotellistWidth = 700, enterhotellistHeight = 300;
		final private Dimension enterhotellistCenter = new Dimension(frameWidth / 2, 500);
		private JLabel backenterhotellist = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextenterhotellist = new JLabel("NEXT", JLabel.CENTER);

		// attribute of hotel list
		private JPanel Hotellist = new JPanel();
		final private int hotellistWidth = 570, hotellistHeight = 250;
		final private Dimension hotellistCenter = new Dimension(frameWidth / 2, 524);
		private JLabel star5 = new JLabel("5-star", JLabel.CENTER);
		private JLabel star4 = new JLabel("4-star", JLabel.CENTER);
		private JLabel star3 = new JLabel("3-star", JLabel.CENTER);
		private JLabel star2 = new JLabel("2-star", JLabel.CENTER);
		private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
		private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
		private JLabel backhotellist = new JLabel("BACK", JLabel.CENTER);

		// attribute of reserve
		private JPanel Reserve = new JPanel();
		final private int reserveWidth = 620, reserveHeight = 300;
		final private Dimension reserveCenter = new Dimension(frameWidth / 2, 500);
		private JLabel backreserve = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextreserve = new JLabel("NEXT", JLabel.CENTER);

		// attribute of inquiry
		private JPanel Inquiry = new JPanel();
		final private int InquiryWidth = 600, InquiryHeight = 150;
		final private Dimension InquiryCenter = new Dimension(frameWidth / 2, 524);
		private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);

		// attribute of reservation record and modify and cancel reservation
		private JPanel MCR = new JPanel();
		final private int mcrWidth = 600, mcrHeight = 300;
		final private Dimension mcrCenter = new Dimension(frameWidth / 2, 500);
		private JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
		private JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
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
			signin.setOpaque(true);

			// enter ID
			JPanel IDPanel = new JPanel();
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			IDPanel.setBackground(new Color(176, 196, 222));

			JLabel ID = new JLabel("       ID       : ");
			ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField IDField = new TextField(15);
			IDField.setFont(new Font("Arial Black", Font.BOLD, 23));
			IDField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			IDPanel.add(ID);
			IDPanel.add(IDField);

			// enter password
			JPanel passwordPanel = new JPanel();
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));

			JLabel password = new JLabel("PASSWORD : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			JPasswordField passwordField = new JPasswordField(9);
			passwordField.setFont(new Font("Arial Black", Font.BOLD, 23));
			passwordField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			passwordPanel.add(password);
			passwordPanel.add(passwordField);

			// set 'back' and 'login' button
			JPanel buttons = new JPanel();
			login.setFont(new Font("Arial Black", Font.PLAIN, 15));
			back.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			buttons.add(back);
			buttons.add(login);

			// sign in adding
			signin.add(IDPanel);
			signin.add(passwordPanel);
			signin.add(buttons);
		}

		// sign up
		private void initSignUp() {
			signup.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			signup.setLayout(new GridLayout(4, 1));
			signup.setOpaque(true);

			// set ID
			JPanel IDPanel = new JPanel();
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			IDPanel.setBackground(new Color(176, 196, 222));

			JLabel ID = new JLabel("         ID         : ");
			ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField IDField = new TextField(15);
			IDField.setFont(new Font("Arial Black", Font.BOLD, 23));
			IDField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			IDPanel.add(ID);
			IDPanel.add(IDField);

			// set password
			JPanel passwordPanel = new JPanel();
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));

			JLabel password = new JLabel("PASSWORD   : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			JPasswordField passwordField = new JPasswordField(9);
			passwordField.setFont(new Font("Arial Black", Font.BOLD, 23));
			passwordField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			passwordPanel.add(password);
			passwordPanel.add(passwordField);

			// enter verify code
			JPanel verifycodePanel = new JPanel();
			verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			verifycodePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			verifycodePanel.setBackground(new Color(176, 196, 222));

			JLabel verifycode = new JLabel("VERIFY CODE : ");
			verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField verifycodeField = new TextField(15);
			verifycodeField.setFont(new Font("Arial Black", Font.BOLD, 23));

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

			// sign up adding
			signup.add(IDPanel);
			signup.add(passwordPanel);
			signup.add(verifycodePanel);
			signup.add(buttons);

		}

		// hotel function hotel list/reserve/inquiry
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

		// enter hotel list date/people/rooms
		private void initEnterHotellist() {
			EnterHotellist.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			EnterHotellist.setLayout(new GridLayout(5, 1));
			EnterHotellist.setOpaque(true);

			// check in date panel
			JPanel checkinPanel = new JPanel();
			checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkinPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkinPanel.setBackground(new Color(176, 196, 222));
			// enter check in date
			JLabel checkin = new JLabel("  CHECK IN DATE: ");
			checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
			// setting check in dd/mm/yyyy
			TextField checkind1 = new TextField(2);
			checkind1.setFont(new Font("Serif", Font.BOLD, 23));
			checkind1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkind1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkind2 = new JLabel("dd");
			checkind2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkinm1 = new TextField(2);
			checkinm1.setFont(new Font("Serif", Font.BOLD, 23));
			checkinm1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkinm1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkinm2 = new JLabel("mm");
			checkinm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkiny1 = new TextField(4);
			checkiny1.setFont(new Font("Serif", Font.BOLD, 23));
			checkiny1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkiny1.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkiny2 = new JLabel("yyyy");
			checkiny2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check in panel adding
			checkinPanel.add(checkin);
			checkinPanel.add(checkind1);
			checkinPanel.add(checkind2);
			checkinPanel.add(checkinm1);
			checkinPanel.add(checkinm2);
			checkinPanel.add(checkiny1);
			checkinPanel.add(checkiny2);

			// check out date panel
			JPanel checkoutPanel = new JPanel();
			checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkoutPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkoutPanel.setBackground(new Color(176, 196, 222));
			// enter check out date
			JLabel checkout = new JLabel("  CHECK OUT DATE: ");
			checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
			// setting check out dd/mm/yyyy
			TextField checkoutd1 = new TextField(2);
			checkoutd1.setFont(new Font("Serif", Font.BOLD, 23));
			checkoutd1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkoutd1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutd2 = new JLabel("dd");
			checkoutd2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkoutm1 = new TextField(2);
			checkoutm1.setFont(new Font("Serif", Font.BOLD, 23));
			checkoutm1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkoutm1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutm2 = new JLabel("mm");
			checkoutm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkouty1 = new TextField(4);
			checkouty1.setFont(new Font("Serif", Font.BOLD, 23));
			checkouty1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkouty1.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkouty2 = new JLabel("yyyy");
			checkouty2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check out panel adding
			checkoutPanel.add(checkout);
			checkoutPanel.add(checkoutd1);
			checkoutPanel.add(checkoutd2);
			checkoutPanel.add(checkoutm1);
			checkoutPanel.add(checkoutm2);
			checkoutPanel.add(checkouty1);
			checkoutPanel.add(checkouty2);

			// people panel
			JPanel peoplePanel = new JPanel();
			peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			peoplePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			peoplePanel.setBackground(new Color(176, 196, 222));
			// enter people panel
			JLabel people = new JLabel("NUMBER OF PEOPLE: ");
			people.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField peopleField = new TextField(15);
			peopleField.setFont(new Font("Serif", Font.BOLD, 23));
			peopleField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// people panel adding
			peoplePanel.add(people);
			peoplePanel.add(peopleField);

			// room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// enter room panel
			JLabel room = new JLabel("NUMBER OF ROOMS: ");
			room.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField roomField = new TextField(15);
			roomField.setFont(new Font("Serif", Font.BOLD, 23));
			roomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(room);
			roomPanel.add(roomField);

			// set 'back' and 'next' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			backenterhotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
			nextenterhotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
			buttons.add(backenterhotellist);
			buttons.add(nextenterhotellist);

			// EnterHotellist adding
			EnterHotellist.add(checkinPanel);
			EnterHotellist.add(checkoutPanel);
			EnterHotellist.add(peoplePanel);
			EnterHotellist.add(roomPanel);
			EnterHotellist.add(buttons);
		}

		// hotel list
		private void initHotellist() {
			// set font
			star5.setFont(new Font("Arial Black", Font.BOLD, 28));
			star4.setFont(new Font("Arial Black", Font.BOLD, 28));
			star3.setFont(new Font("Arial Black", Font.BOLD, 28));
			star2.setFont(new Font("Arial Black", Font.BOLD, 28));
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
			star.add(star5);
			star.add(star4);
			star.add(star3);
			star.add(star2);

			Hotellist.add(star);
			Hotellist.add(pricehighText);
			Hotellist.add(pricelowText);
			Hotellist.add(backhotellist);
		}

		// inquiry
		private void initInquiry() {
			Inquiry.setLayout(new GridLayout(2, 1, 0, 0));
			Inquiry.setOpaque(true);
			Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Inquiry.setBackground(new Color(176, 196, 222));
			// enter reserve number
			JPanel reservenumberPanel = new JPanel();
			JLabel reservenumber = new JLabel("RESERVATION NUMBER : ");
			reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField resnumField = new TextField(15);
			resnumField.setFont(new Font("Serif", Font.BOLD, 23));
			resnumField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			reservenumberPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			reservenumberPanel.setBackground(new Color(176, 196, 222));

			reservenumberPanel.add(reservenumber);
			reservenumberPanel.add(resnumField);

			// set 'back' and 'next' buttons
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

		// MCR
		private void initMCR() {
			MCR.setLayout(new GridLayout(5, 1, 0, 0));
			MCR.setOpaque(true);
			MCR.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			MCR.setBackground(new Color(176, 196, 222));
			
			// hotelID Panel
			JPanel hotelIDPanel = new JPanel();
			hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			hotelIDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			hotelIDPanel.setBackground(new Color(176, 196, 222));
			// enter hotel ID
			JLabel hotelID = new JLabel("    HotelID     : ");
			hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField hotelIDField = new TextField(15);
			hotelIDField.setFont(new Font("Serif", Font.BOLD, 23));	
			// hotel ID Panel adding
			hotelIDPanel.add(hotelID);
			hotelIDPanel.add(hotelIDField);

			// number of room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new GridLayout(1, 6));
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// single room
			JLabel singleroom = new JLabel("Single: ");
			singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField singleroomField = new TextField(2);
			singleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			singleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// double room
			JLabel doubleroom = new JLabel("Double: ");
			doubleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField doubleroomField = new TextField(2);
			doubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			doubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// quad room
			JLabel quadroom = new JLabel("Quad: ");
			quadroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField quadroomField = new TextField(2);
			quadroomField.setFont(new Font("Serif", Font.BOLD, 23));
			quadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(singleroom);
			roomPanel.add(singleroomField);
			roomPanel.add(doubleroom);
			roomPanel.add(doubleroomField);
			roomPanel.add(quadroom);
			roomPanel.add(quadroomField);

			// lodging date panel
			JPanel lodgingPanel = new JPanel();
			lodgingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			lodgingPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			lodgingPanel.setBackground(new Color(176, 196, 222));
			TextField checkind1 = new TextField(2);
			checkind1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkind2 = new JLabel("/");
			checkind2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkinm1 = new TextField(2);
			checkinm1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkinm2 = new JLabel("/");
			checkinm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkiny1 = new TextField(4);
			checkiny1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkiny2 = new JLabel("~");
			checkiny2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkoutd1 = new TextField(2);
			checkoutd1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkoutd2 = new JLabel("/");
			checkoutd2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkoutm1 = new TextField(2);
			checkoutm1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkoutm2 = new JLabel("/");
			checkoutm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkouty1 = new TextField(4);
			checkouty1.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkouty2 = new JLabel("");
			checkouty2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// lodgingPanel adding
			lodgingPanel.add(checkind1);
			lodgingPanel.add(checkind2);
			lodgingPanel.add(checkinm1);
			lodgingPanel.add(checkinm2);
			lodgingPanel.add(checkiny1);
			lodgingPanel.add(checkiny2);
			lodgingPanel.add(checkoutd1);
			lodgingPanel.add(checkoutd2);
			lodgingPanel.add(checkoutm1);
			lodgingPanel.add(checkoutm2);
			lodgingPanel.add(checkouty1);
			lodgingPanel.add(checkouty2);
			
			// 'total length of stay' and 'total price'
			JPanel staypricePanel =new JPanel();
			staypricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			staypricePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			staypricePanel.setBackground(new Color(176, 196, 222));
			JLabel stay = new JLabel("Total Nights of Stay:");
			stay.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField stayField = new TextField(2);
			stayField.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel price = new JLabel("Total Price:");
			price.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField priceField = new TextField(5);
			priceField.setFont(new Font("Serif", Font.BOLD, 23));
			// stay price Panel adding
			staypricePanel.add(stay);
			staypricePanel.add(stayField);
			staypricePanel.add(price);
			staypricePanel.add(priceField);

			// set 'back' and 'next' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.setBackground(new Color(176, 196, 222));
			modifyText.setFont(new Font("Arial Black", Font.PLAIN, 20));
			cancelText.setFont(new Font("Arial Black", Font.PLAIN, 20));
			backmcr.setFont(new Font("Arial Black", Font.PLAIN, 20));
			buttons.add(backmcr);
			buttons.add(cancelText);
			buttons.add(modifyText);

			//
			MCR.add(hotelIDPanel);
			MCR.add(roomPanel);
			MCR.add(lodgingPanel);
			MCR.add(staypricePanel);
			MCR.add(buttons);
		}

		// Reserve
		private void initReserve() {
			Reserve.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Reserve.setLayout(new GridLayout(5, 1));
			Reserve.setOpaque(true);

			// check in date panel
			JPanel checkinPanel = new JPanel();
			checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkinPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkinPanel.setBackground(new Color(176, 196, 222));
			// enter check in date
			JLabel checkin = new JLabel("  CHECK IN DATE: ");
			checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
			// setting check in dd/mm/yyyy
			TextField checkind1 = new TextField(2);
			checkind1.setFont(new Font("Serif", Font.BOLD, 23));
			checkind1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkind1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkind2 = new JLabel("dd");
			checkind2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkinm1 = new TextField(2);
			checkinm1.setFont(new Font("Serif", Font.BOLD, 23));
			checkinm1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkinm1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkinm2 = new JLabel("mm");
			checkinm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkiny1 = new TextField(4);
			checkiny1.setFont(new Font("Serif", Font.BOLD, 23));
			checkiny1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkiny1.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkiny2 = new JLabel("yyyy");
			checkiny2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check in panel adding
			checkinPanel.add(checkin);
			checkinPanel.add(checkind1);
			checkinPanel.add(checkind2);
			checkinPanel.add(checkinm1);
			checkinPanel.add(checkinm2);
			checkinPanel.add(checkiny1);
			checkinPanel.add(checkiny2);

			// check out date panel
			JPanel checkoutPanel = new JPanel();
			checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkoutPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkoutPanel.setBackground(new Color(176, 196, 222));
			// enter check out date
			JLabel checkout = new JLabel("  CHECK OUT DATE: ");
			checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
			// setting check out dd/mm/yyyy
			TextField checkoutd1 = new TextField(2);
			checkoutd1.setFont(new Font("Serif", Font.BOLD, 23));
			checkoutd1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkoutd1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutd2 = new JLabel("dd");
			checkoutd2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkoutm1 = new TextField(2);
			checkoutm1.setFont(new Font("Serif", Font.BOLD, 23));
			checkoutm1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkoutm1.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutm2 = new JLabel("mm");
			checkoutm2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			TextField checkouty1 = new TextField(4);
			checkouty1.setFont(new Font("Serif", Font.BOLD, 23));
			checkouty1.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = checkouty1.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkouty2 = new JLabel("yyyy");
			checkouty2.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check out panel adding
			checkoutPanel.add(checkout);
			checkoutPanel.add(checkoutd1);
			checkoutPanel.add(checkoutd2);
			checkoutPanel.add(checkoutm1);
			checkoutPanel.add(checkoutm2);
			checkoutPanel.add(checkouty1);
			checkoutPanel.add(checkouty2);

			// hotelID Panel
			JPanel hotelIDPanel = new JPanel();
			hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			hotelIDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			hotelIDPanel.setBackground(new Color(176, 196, 222));
			// enter hotel ID
			JLabel hotelID = new JLabel("    HotelID     : ");
			hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField hotelIDField = new TextField(15);
			hotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
			hotelIDField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = hotelIDField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			hotelIDPanel.add(hotelID);
			hotelIDPanel.add(hotelIDField);

			// number of room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new GridLayout(1, 6));
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// single room
			JLabel singleroom = new JLabel("Single: ");
			singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField singleroomField = new TextField(2);
			singleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			singleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// double room
			JLabel doubleroom = new JLabel("Double: ");
			doubleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField doubleroomField = new TextField(2);
			doubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			doubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// quad room
			JLabel quadroom = new JLabel("Quad: ");
			quadroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			TextField quadroomField = new TextField(2);
			quadroomField.setFont(new Font("Serif", Font.BOLD, 23));
			quadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(singleroom);
			roomPanel.add(singleroomField);
			roomPanel.add(doubleroom);
			roomPanel.add(doubleroomField);
			roomPanel.add(quadroom);
			roomPanel.add(quadroomField);

			// setting 'back' and 'next' buttons
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			backreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
			nextreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
			buttons.add(backreserve);
			buttons.add(nextreserve);

			// Reserve adding Panel
			Reserve.add(checkinPanel);
			Reserve.add(checkoutPanel);
			Reserve.add(hotelIDPanel);
			Reserve.add(roomPanel);
			Reserve.add(buttons);
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

			this.Hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
					hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);

			this.EnterHotellist.setBounds(enterhotellistCenter.width - (enterhotellistWidth / 2),
					enterhotellistCenter.height - (enterhotellistHeight / 2), enterhotellistWidth,
					enterhotellistHeight);

			this.Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
					hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);

			this.Reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
					reserveWidth, reserveHeight);

			this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
					InquiryWidth, InquiryHeight);

			this.MCR.setBounds(mcrCenter.width - (mcrWidth / 2), mcrCenter.height - (mcrHeight / 2), mcrWidth,
					mcrHeight);
		}

		public Menu() {
			initPanel();
			initFonts();
			initTitle();
			initSubMenu();
			initSignIn();
			initSignUp();
			initHotelfunction();
			initEnterHotellist();
			initHotellist();
			initReserve();
			initInquiry();
			initMCR();
			initLayerPane();
			// buttons in sub menu / sign in / sign up
			signinText.addMouseListener(ml);
			signupText.addMouseListener(ml);
			back.addMouseListener(ml);
			login.addMouseListener(ml);
			cancel.addMouseListener(ml);
			signuplogin.addMouseListener(ml);
			// buttons in hotel function
			hotellistText.addMouseListener(ml);
			reserveText.addMouseListener(ml);
			inquiryText.addMouseListener(ml);
			logouthf.addMouseListener(ml);
			// buttons in enter hotel list
			backenterhotellist.addMouseListener(ml);
			nextenterhotellist.addMouseListener(ml);
			star5.addMouseListener(ml);
			// buttons in hotel list
			star5.addMouseListener(ml);
			star4.addMouseListener(ml);
			star3.addMouseListener(ml);
			star2.addMouseListener(ml);
			pricehighText.addMouseListener(ml);
			pricelowText.addMouseListener(ml);
			backhotellist.addMouseListener(ml);
			// buttons in reserve
			backreserve.addMouseListener(ml);
			nextreserve.addMouseListener(ml);
			// buttons in inquiry
			backinquiry.addMouseListener(ml);
			nextinquiry.addMouseListener(ml);
			// buttons in modify and cancel reservation
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
					layeredPane.add(Hotelfunction, new Integer(2));
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
					layeredPane.add(EnterHotellist, new Integer(3));
					validate();
					repaint();
					hotellistText.setForeground(Color.black);
				} else if (e.getSource() == nextenterhotellist) {
					layeredPane.remove(EnterHotellist);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					nextenterhotellist.setForeground(Color.black);
				} else if (e.getSource() == backhotellist) {
					layeredPane.remove(Hotellist);
					layeredPane.add(EnterHotellist, new Integer(3));
					validate();
					repaint();
					backhotellist.setForeground(Color.black);
				} else if (e.getSource() == backenterhotellist || e.getSource() == backreserve
						|| e.getSource() == backinquiry || e.getSource() == backmcr) {
					layeredPane.remove(EnterHotellist);
					layeredPane.remove(Inquiry);
					layeredPane.remove(MCR);
					layeredPane.remove(Reserve);
					layeredPane.add(Hotelfunction);
					validate();
					repaint();
					backenterhotellist.setForeground(Color.black);
					backinquiry.setForeground(Color.black);
					backmcr.setForeground(Color.black);
					backreserve.setForeground(Color.black);
				} else if (e.getSource() == reserveText) {
					layeredPane.remove(Hotelfunction);
					layeredPane.add(Reserve, new Integer(3));
					validate();
					repaint();
					reserveText.setForeground(Color.black);
				}

				else if (e.getSource() == inquiryText) {
					layeredPane.remove(Hotelfunction);
					layeredPane.add(Inquiry, new Integer(3));
					validate();
					repaint();
					inquiryText.setForeground(Color.black);
				} else if (e.getSource() == nextinquiry) {
					layeredPane.remove(Inquiry);
					layeredPane.add(MCR, new Integer(3));
					validate();
					repaint();
					nextinquiry.setForeground(Color.black);
				}

			}
		};

	}

	public static void main(String[] args) {
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
	}
}
