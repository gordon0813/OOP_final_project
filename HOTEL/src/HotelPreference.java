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
		private JLabel titleText = new JLabel("== HOTEL ==", JLabel.CENTER);

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
		private JLabel signinlogin = new JLabel("LOGIN", JLabel.CENTER);
		private JLabel signinback = new JLabel("BACK", JLabel.CENTER);
		protected TextField signinidField = new TextField(15);
		protected JPasswordField signinpasswordField = new JPasswordField(9);

		// attribute of sign up
		private JPanel signup = new JPanel();
		final private int signupSetWidth = 500, signupSetHeight = 240;
		final private Dimension signupSetCenter = new Dimension(frameWidth / 2, 524);
		private JLabel signuplogin = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
		private JLabel signupcancel = new JLabel("CANCEL", JLabel.CENTER);
		protected TextField signupidField = new TextField(15);
		protected JPasswordField signuppasswordField = new JPasswordField(9);
		protected TextField verifycodeField = new TextField(15);

		// attribute of Hotel function Hotel list/Reserve/Inquiry
		private JPanel Hotelfunction = new JPanel();
		final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
		final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, 524);
		private JLabel hotellistText = new JLabel("HOTEL LIST", JLabel.CENTER);
		private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
		private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
		private JLabel logout = new JLabel("LOGOUT", JLabel.CENTER);

		// attribute of entering hotel list date, people, rooms
		private JPanel EnterHotellist = new JPanel();
		final private int enterhotellistWidth = 700, enterhotellistHeight = 300;
		final private Dimension enterhotellistCenter = new Dimension(frameWidth / 2, 500);
		private JLabel backenterhotellist = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextenterhotellist = new JLabel("NEXT", JLabel.CENTER);
		protected TextField entercheckindayField = new TextField(2);
		protected TextField entercheckinmonthField = new TextField(2);
		protected TextField entercheckinyearField = new TextField(4);
		protected TextField entercheckoutdayField = new TextField(2);
		protected TextField entercheckoutmonthField = new TextField(2);
		protected TextField entercheckoutyearField = new TextField(4);
		protected TextField enterpeopleField = new TextField(15);
		protected TextField enterroomField = new TextField(15);

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
		protected TextField reservecheckindayField = new TextField(2);
		protected TextField reservecheckinmonthField = new TextField(2);
		protected TextField reservecheckinyearField = new TextField(4);
		protected TextField reservecheckoutdayField = new TextField(2);
		protected TextField reservecheckoutmonthField = new TextField(2);
		protected TextField reservecheckoutyearField = new TextField(4);
		protected TextField reservehotelIDField = new TextField(15);
		protected TextField reservesingleroomField = new TextField(2);
		protected TextField reservedoubleroomField = new TextField(2);
		protected TextField reservequadroomField = new TextField(2);

		// attribute of inquiry
		private JPanel Inquiry = new JPanel();
		final private int InquiryWidth = 600, InquiryHeight = 150;
		final private Dimension InquiryCenter = new Dimension(frameWidth / 2, 524);
		private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
		private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
		protected TextField reservenumberField = new TextField(15);

		// attribute of MCR (reservation record and modify and cancel reservation)
		private JPanel MCR = new JPanel();
		final private int mcrWidth = 600, mcrHeight = 300;
		final private Dimension mcrCenter = new Dimension(frameWidth / 2, 500);
		private JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
		private JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
		private JLabel backmcr = new JLabel("BACK", JLabel.CENTER);
		protected TextField mcrhotelIDField = new TextField(15);
		protected TextField mcrsingleroomField = new TextField(2);
		protected TextField mcrdoubleroomField = new TextField(2);
		protected TextField mcrquadroomField = new TextField(2);
		protected TextField mcrcheckinday = new TextField(2);
		protected TextField mcrcheckinmonth = new TextField(2);
		protected TextField mcrcheckinyear = new TextField(4);
		protected TextField mcrcheckoutday = new TextField(2);
		protected TextField mcrcheckoutmonth = new TextField(2);
		protected TextField mcrcheckoutyear = new TextField(4);
		protected TextField mcrstaynightField = new TextField(2);
		protected TextField mcrpriceField = new TextField(5);

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

			// enter ID Panel setting
			JPanel IDPanel = new JPanel();
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			IDPanel.setBackground(new Color(176, 196, 222));
			// enter ID
			JLabel ID = new JLabel("       ID       : ");
			ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			signinidField.setEditable(true);
			signinidField.setFont(new Font("Arial Black", Font.BOLD, 23));
			signinidField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// ID Panel adding
			IDPanel.add(ID);
			IDPanel.add(signinidField);

			// enter password Panel setting
			JPanel passwordPanel = new JPanel();
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));
			// enter password
			JLabel password = new JLabel("PASSWORD : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			signinpasswordField.setEditable(true);
			signinpasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
			signinpasswordField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// password Panel adding
			passwordPanel.add(password);
			passwordPanel.add(signinpasswordField);

			// set 'back' and 'login' button
			JPanel buttons = new JPanel();
			signinlogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
			signinback.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			buttons.add(signinback);
			buttons.add(signinlogin);

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
			signupidField.setEditable(true);
			signupidField.setFont(new Font("Arial Black", Font.BOLD, 23));
			signupidField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			IDPanel.add(ID);
			IDPanel.add(signupidField);

			// set password
			JPanel passwordPanel = new JPanel();
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			passwordPanel.setBackground(new Color(176, 196, 222));

			JLabel password = new JLabel("PASSWORD   : ");
			password.setFont(new Font("Arial Black", Font.PLAIN, 20));
			signuppasswordField.setEditable(true);
			signuppasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
			signuppasswordField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});

			passwordPanel.add(password);
			passwordPanel.add(signuppasswordField);

			// verify code Panel
			JPanel verifycodePanel = new JPanel();
			verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			verifycodePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			verifycodePanel.setBackground(new Color(176, 196, 222));
			// enter verify code
			JLabel verifycode = new JLabel("VERIFY CODE : ");
			verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
			verifycodeField.setEditable(true);
			verifycodeField.setFont(new Font("Arial Black", Font.BOLD, 23));
			verifycodeField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// verify code panel adding
			verifycodePanel.add(verifycode);
			verifycodePanel.add(verifycodeField);

			// set 'cancel' and 'sign up and login' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(176, 196, 222));
			signuplogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
			signupcancel.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.add(signupcancel);
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
			logout.setFont(new Font("Arial Black", Font.BOLD, 30));

			Hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
			Hotelfunction.setOpaque(true);
			Hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Hotelfunction.setBackground(new Color(176, 196, 222));
			Hotelfunction.add(hotellistText);
			Hotelfunction.add(reserveText);
			Hotelfunction.add(inquiryText);
			Hotelfunction.add(logout);

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

			entercheckindayField.setEditable(true);
			entercheckindayField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckindayField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckindayField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkindd = new JLabel("dd");
			checkindd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			entercheckinmonthField.setEditable(true);
			entercheckinmonthField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckinmonthField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckinmonthField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkinmm = new JLabel("mm");
			checkinmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			entercheckinyearField.setEditable(true);
			entercheckinyearField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckinyearField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckinyearField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkinyyyy = new JLabel("yyyy");
			checkinyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check in panel adding
			checkinPanel.add(checkin);
			checkinPanel.add(entercheckindayField);
			checkinPanel.add(checkindd);
			checkinPanel.add(entercheckinmonthField);
			checkinPanel.add(checkinmm);
			checkinPanel.add(entercheckinyearField);
			checkinPanel.add(checkinyyyy);

			// check out date panel
			JPanel checkoutPanel = new JPanel();
			checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkoutPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkoutPanel.setBackground(new Color(176, 196, 222));
			// enter check out date
			JLabel checkout = new JLabel("  CHECK OUT DATE: ");
			checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
			// setting check out dd/mm/yyyy
			entercheckoutdayField.setEditable(true);
			entercheckoutdayField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckoutdayField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckoutdayField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutdd = new JLabel("dd");
			checkoutdd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			entercheckoutmonthField.setEditable(true);
			entercheckoutmonthField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckoutmonthField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckoutmonthField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutmm = new JLabel("mm");
			checkoutmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			entercheckoutyearField.setEditable(true);
			entercheckoutyearField.setFont(new Font("Serif", Font.BOLD, 23));
			entercheckoutyearField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = entercheckoutyearField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkoutyyyy = new JLabel("yyyy");
			checkoutyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check out panel adding
			checkoutPanel.add(checkout);
			checkoutPanel.add(entercheckoutdayField);
			checkoutPanel.add(checkoutdd);
			checkoutPanel.add(entercheckoutmonthField);
			checkoutPanel.add(checkoutmm);
			checkoutPanel.add(entercheckoutyearField);
			checkoutPanel.add(checkoutyyyy);

			// people panel
			JPanel peoplePanel = new JPanel();
			peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			peoplePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			peoplePanel.setBackground(new Color(176, 196, 222));
			// enter people panel
			JLabel people = new JLabel("NUMBER OF PEOPLE: ");
			people.setFont(new Font("Arial Black", Font.PLAIN, 20));
			enterpeopleField.setEditable(true);
			enterpeopleField.setFont(new Font("Serif", Font.BOLD, 23));
			enterpeopleField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// people panel adding
			peoplePanel.add(people);
			peoplePanel.add(enterpeopleField);

			// room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// enter room panel
			JLabel room = new JLabel("NUMBER OF ROOMS: ");
			room.setFont(new Font("Arial Black", Font.PLAIN, 20));
			enterroomField.setEditable(true);
			enterroomField.setFont(new Font("Serif", Font.BOLD, 23));
			enterroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(room);
			roomPanel.add(enterroomField);

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
			// enter check in date dd/mm/yyyy
			JLabel checkin = new JLabel("  CHECK IN DATE: ");
			checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
			reservecheckindayField.setEditable(true);
			reservecheckindayField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckindayField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckindayField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkindd = new JLabel("dd");
			checkindd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			reservecheckinmonthField.setEditable(true);
			reservecheckinmonthField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckinmonthField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckinmonthField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkinmm = new JLabel("mm");
			checkinmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			reservecheckinyearField.setEditable(true);
			reservecheckinyearField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckinyearField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckinyearField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkinyyyy = new JLabel("yyyy");
			checkinyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check in panel adding
			checkinPanel.add(checkin);
			checkinPanel.add(reservecheckindayField);
			checkinPanel.add(checkindd);
			checkinPanel.add(reservecheckinmonthField);
			checkinPanel.add(checkinmm);
			checkinPanel.add(reservecheckinyearField);
			checkinPanel.add(checkinyyyy);

			// check out date panel
			JPanel checkoutPanel = new JPanel();
			checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			checkoutPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			checkoutPanel.setBackground(new Color(176, 196, 222));
			// enter check out date dd/mm/yyyy
			JLabel checkout = new JLabel("  CHECK OUT DATE: ");
			checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
			reservecheckoutdayField.setEditable(true);
			reservecheckoutdayField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckoutdayField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckoutdayField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutdd = new JLabel("dd");
			checkoutdd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			reservecheckoutmonthField.setEditable(true);
			reservecheckoutmonthField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckoutmonthField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckoutmonthField.getText();
					if (s.length() >= 2)
						e.consume();
				}
			});
			JLabel checkoutmm = new JLabel("mm");
			checkoutmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			reservecheckoutyearField.setEditable(true);
			reservecheckoutyearField.setFont(new Font("Serif", Font.BOLD, 23));
			reservecheckoutyearField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservecheckoutyearField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			JLabel checkoutyyyy = new JLabel("yyyy");
			checkoutyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// check out panel adding
			checkoutPanel.add(checkout);
			checkoutPanel.add(reservecheckoutdayField);
			checkoutPanel.add(checkoutdd);
			checkoutPanel.add(reservecheckoutmonthField);
			checkoutPanel.add(checkoutmm);
			checkoutPanel.add(reservecheckoutyearField);
			checkoutPanel.add(checkoutyyyy);

			// hotelID Panel
			JPanel hotelIDPanel = new JPanel();
			hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			hotelIDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			hotelIDPanel.setBackground(new Color(176, 196, 222));
			// enter hotel ID
			JLabel hotelID = new JLabel("    HotelID     : ");
			hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
			reservehotelIDField.setEditable(true);
			reservehotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
			reservehotelIDField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
					String s = reservehotelIDField.getText();
					if (s.length() >= 4)
						e.consume();
				}
			});
			hotelIDPanel.add(hotelID);
			hotelIDPanel.add(reservehotelIDField);

			// number of room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new GridLayout(1, 6));
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// single room
			JLabel singleroom = new JLabel("Single: ");
			singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			reservesingleroomField.setEditable(true);
			reservesingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			reservesingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
			reservedoubleroomField.setEditable(true);
			reservedoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			reservedoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
			reservequadroomField.setEditable(true);
			reservequadroomField.setFont(new Font("Serif", Font.BOLD, 23));
			reservequadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(singleroom);
			roomPanel.add(reservesingleroomField);
			roomPanel.add(doubleroom);
			roomPanel.add(reservedoubleroomField);
			roomPanel.add(quadroom);
			roomPanel.add(reservequadroomField);

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

		// inquiry
		private void initInquiry() {
			Inquiry.setLayout(new GridLayout(2, 1, 0, 0));
			Inquiry.setOpaque(true);
			Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
			Inquiry.setBackground(new Color(176, 196, 222));

			// enter reserve number
			JPanel reservenumberPanel = new JPanel();
			reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			reservenumberPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			reservenumberPanel.setBackground(new Color(176, 196, 222));
			JLabel reservenumber = new JLabel("RESERVATION NUMBER : ");
			reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
			reservenumberField.setFont(new Font("Serif", Font.BOLD, 23));
			reservenumberField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// reservenumberPanel adding
			reservenumberPanel.add(reservenumber);
			reservenumberPanel.add(reservenumberField);

			// set 'back' and 'next' buttons
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.setBackground(new Color(176, 196, 222));
			backinquiry.setFont(new Font("Arial Black", Font.PLAIN, 15));
			nextinquiry.setFont(new Font("Arial Black", Font.PLAIN, 15));
			buttons.add(backinquiry);
			buttons.add(nextinquiry);

			// inquiry adding
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
			mcrhotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
			// hotel ID Panel adding
			hotelIDPanel.add(hotelID);
			hotelIDPanel.add(mcrhotelIDField);

			// number of room panel
			JPanel roomPanel = new JPanel();
			roomPanel.setLayout(new GridLayout(1, 6));
			roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			roomPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			roomPanel.setBackground(new Color(176, 196, 222));
			// single room
			JLabel singleroom = new JLabel("Single: ");
			singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
			mcrsingleroomField.setEditable(true);
			mcrsingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			mcrsingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
			mcrdoubleroomField.setEditable(true);
			mcrdoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
			mcrdoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
			mcrquadroomField.setEditable(true);
			mcrquadroomField.setFont(new Font("Serif", Font.BOLD, 23));
			mcrquadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
			// room panel adding
			roomPanel.add(singleroom);
			roomPanel.add(mcrsingleroomField);
			roomPanel.add(doubleroom);
			roomPanel.add(mcrdoubleroomField);
			roomPanel.add(quadroom);
			roomPanel.add(mcrquadroomField);

			// lodging date panel
			JPanel lodgingPanel = new JPanel();
			lodgingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			lodgingPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			lodgingPanel.setBackground(new Color(176, 196, 222));
			mcrcheckinday.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkindd = new JLabel("/");
			checkindd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			mcrcheckinmonth.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkinmm = new JLabel("/");
			checkinmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			mcrcheckinyear.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkinyyyy = new JLabel("~");
			checkinyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			mcrcheckoutday.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkoutdd = new JLabel("/");
			checkoutdd.setFont(new Font("Arial Black", Font.PLAIN, 15));
			mcrcheckoutmonth.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkoutmm = new JLabel("/");
			checkoutmm.setFont(new Font("Arial Black", Font.PLAIN, 15));
			mcrcheckoutyear.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel checkoutyyyy = new JLabel("");
			checkoutyyyy.setFont(new Font("Arial Black", Font.PLAIN, 15));
			// lodgingPanel adding
			lodgingPanel.add(mcrcheckinday);
			lodgingPanel.add(checkindd);
			lodgingPanel.add(mcrcheckinmonth);
			lodgingPanel.add(checkinmm);
			lodgingPanel.add(mcrcheckinyear);
			lodgingPanel.add(checkinyyyy);
			lodgingPanel.add(mcrcheckoutday);
			lodgingPanel.add(checkoutdd);
			lodgingPanel.add(mcrcheckoutmonth);
			lodgingPanel.add(checkoutmm);
			lodgingPanel.add(mcrcheckoutyear);
			lodgingPanel.add(checkoutyyyy);

			// 'total length of stay' and 'total price'
			JPanel staypricePanel = new JPanel();
			staypricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			staypricePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(176, 196, 222)));
			staypricePanel.setBackground(new Color(176, 196, 222));
			JLabel stay = new JLabel("Total Nights of Stay:");
			stay.setFont(new Font("Arial Black", Font.PLAIN, 20));
			mcrstaynightField.setFont(new Font("Serif", Font.BOLD, 23));
			JLabel price = new JLabel("Total Price:");
			price.setFont(new Font("Arial Black", Font.PLAIN, 20));
			mcrpriceField.setFont(new Font("Serif", Font.BOLD, 23));
			// stay price Panel adding
			staypricePanel.add(stay);
			staypricePanel.add(mcrstaynightField);
			staypricePanel.add(price);
			staypricePanel.add(mcrpriceField);

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

			// MCR adding
			MCR.add(hotelIDPanel);
			MCR.add(roomPanel);
			MCR.add(lodgingPanel);
			MCR.add(staypricePanel);
			MCR.add(buttons);
		}

		// show the reserve information
		private void showMCR(int chkind, int chkinm, int chkiny, int chkoutd, int chkoutm, int chkouty, int h,
				int sroom, int droom, int qroom, int n, int p) {
			mcrcheckinday.setText(Integer.toString(chkind));
			mcrcheckinmonth.setText(Integer.toString(chkinm));
			mcrcheckinyear.setText(Integer.toString(chkiny));
			mcrcheckoutday.setText(Integer.toString(chkoutd));
			mcrcheckoutmonth.setText(Integer.toString(chkoutm));
			mcrcheckoutyear.setText(Integer.toString(chkouty));
			mcrhotelIDField.setText(Integer.toString(h));
			mcrsingleroomField.setText(Integer.toString(sroom));
			mcrdoubleroomField.setText(Integer.toString(droom));
			mcrquadroomField.setText(Integer.toString(qroom));
			mcrstaynightField.setText(Integer.toString(n));
			mcrpriceField.setText(Integer.toString(p));
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
			signinback.addMouseListener(ml);
			signinlogin.addMouseListener(ml);
			signupcancel.addMouseListener(ml);
			signuplogin.addMouseListener(ml);
			// buttons in hotel function
			hotellistText.addMouseListener(ml);
			reserveText.addMouseListener(ml);
			inquiryText.addMouseListener(ml);
			logout.addMouseListener(ml);
			// buttons in enter hotel list
			backenterhotellist.addMouseListener(ml);
			nextenterhotellist.addMouseListener(ml);
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
				} else if (e.getSource() == signupcancel) {
					layeredPane.remove(signup);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					signupcancel.setForeground(Color.black);
				} else if (e.getSource() == signuplogin) {
					layeredPane.remove(signup);
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signuplogin.setForeground(Color.black);
					// get the sign up id and password and verify code
					Integer.parseInt(signupidField.getText());
					Integer.parseInt(new String(signuppasswordField.getPassword()));
					Integer.parseInt(verifycodeField.getText());
				} else if (e.getSource() == signinText) {
					layeredPane.remove(subMenu);
					layeredPane.add(signin, new Integer(2));
					validate();
					repaint();
					signinText.setForeground(Color.black);
				} else if (e.getSource() == signinback) {
					layeredPane.remove(signin);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					signinback.setForeground(Color.black);
				} else if (e.getSource() == signinlogin) {
					layeredPane.remove(signin);
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
					// get the sign in id and password
					Integer.parseInt(signinidField.getText());
					Integer.parseInt(new String(signinpasswordField.getPassword()));
				} else if (e.getSource() == logout) {
					layeredPane.remove(Hotelfunction);
					layeredPane.remove(signin);
					layeredPane.remove(signup);
					layeredPane.add(subMenu, new Integer(2));
					validate();
					repaint();
					logout.setForeground(Color.black);
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
					// get enter check in day month year
					Integer.parseInt(entercheckindayField.getText());
					Integer.parseInt(entercheckinmonthField.getText());
					Integer.parseInt(entercheckinyearField.getText());
					// get enter check out day month year
					Integer.parseInt(entercheckoutdayField.getText());
					Integer.parseInt(entercheckoutmonthField.getText());
					Integer.parseInt(entercheckoutyearField.getText());
					// get enter people and room
					Integer.parseInt(enterpeopleField.getText());
					Integer.parseInt(enterroomField.getText());
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
				} else if (e.getSource() == nextreserve) {
					// get reserve check in day month year
					Integer.parseInt(reservecheckindayField.getText());
					Integer.parseInt(reservecheckinmonthField.getText());
					Integer.parseInt(reservecheckinyearField.getText());
					// get reserve check out day month year
					Integer.parseInt(reservecheckoutdayField.getText());
					Integer.parseInt(reservecheckoutmonthField.getText());
					Integer.parseInt(reservecheckoutyearField.getText());
					// get reserve HOTEL ID
					Integer.parseInt(reservehotelIDField.getText());
					// get reserve number of room
					Integer.parseInt(reservesingleroomField.getText());
					Integer.parseInt(reservedoubleroomField.getText());
					Integer.parseInt(reservequadroomField.getText());
				} else if (e.getSource() == inquiryText) {
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
					// get reserve number
					Integer.parseInt(reservenumberField.getText());
				}

			}
		};

	}

	public static void main(String[] args) {
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
	}
}
