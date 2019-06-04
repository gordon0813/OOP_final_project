import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Menu extends JPanel {
	private JLayeredPane layeredPane;
	private JLabel background = new JLabel();
	final int frameWidth = 1152, frameHeight = 720;

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

	// attribute of sign in error - USERID DOESN'T EXIST.
	private JPanel Signinerror = new JPanel();
	final private int signinerrorWidth = 700, signinerrorHeight = 110;
	final private Dimension signinerrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinerrorText = new JLabel("USERID DOESN'T EXIST.", JLabel.CENTER);
	private JLabel backsigninerror = new JLabel("BACK", JLabel.CENTER);
	
	// attribute of sign in error - WRONG PASSWORD.
	private JPanel Signinerror1 = new JPanel();
	final private int signinerrorWidth1 = 700, signinerrorHeight1 = 110;
	final private Dimension signinerrorCenter1 = new Dimension(frameWidth / 2, 500);
	private JLabel signinerrorText1 = new JLabel("WRONG PASSWORD.", JLabel.CENTER);
	private JLabel backsigninerror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - USERID ALREADY EXISTS.
	private JPanel Signuperror = new JPanel();
	final private int signuperrorWidth = 500, signuperrorHeight = 110;
	final private Dimension signuperrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuperrorText = new JLabel("USERID ALREADY EXISTS.", JLabel.CENTER);
	private JLabel backsignuperror = new JLabel("BACK", JLabel.CENTER);
		
	// attribute of sign up error - WRONG VERIFY CODE.
	private JPanel Signuperror1 = new JPanel();
	final private int signuperrorWidth1 = 500, signuperrorHeight1 = 110;
	final private Dimension signuperrorCenter1 = new Dimension(frameWidth / 2, 500);
	private JLabel signuperrorText1 = new JLabel("WRONG VERIFY CODE.", JLabel.CENTER);
	private JLabel backsignuperror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign in
	private JPanel Signin = new JPanel();
	final private int signinSetWidth = 500, signinSetHeight = 180;
	final private Dimension signinSetCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinlogin = new JLabel("LOGIN", JLabel.CENTER);
	private JLabel signinback = new JLabel("BACK", JLabel.CENTER);
	protected TextField signinidField = new TextField(15);
	protected TextField signinpasswordField = new TextField(15);

	// attribute of sign up
	private JPanel Signup = new JPanel();
	final private int signupSetWidth = 500, signupSetHeight = 240;
	final private Dimension signupSetCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuplogin = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
	private JLabel signupcancel = new JLabel("CANCEL", JLabel.CENTER);
	protected TextField signupidField = new TextField(13);
	protected TextField signuppasswordField = new TextField(13);
	protected TextField usercodeField = new TextField(6);
	protected JLabel verifycodeField = new JLabel("");

	// attribute of Hotel function Hotel list/Reserve/Inquiry
	private JPanel Hotelfunction = new JPanel();
	final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
	final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
	private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
	private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
	private JLabel logout = new JLabel("LOGOUT", JLabel.CENTER);

	// attribute of entering hotel list date, people, rooms
	private JPanel EnterSearch = new JPanel();
	final private int enterhotellistWidth = 700, enterhotellistHeight = 300;
	final private Dimension enterhotellistCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backenterhotellist = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextenterhotellist = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField entercheckindateField = new JTextField(10);
	protected JTextField entercheckoutdateField = new JTextField(10);
	protected TextField enterpeopleField = new TextField(15);
	protected TextField enterroomField = new TextField(15);

	// attribute of enter hotel list error - INVALID DATE!
	private JPanel Entersearcherror = new JPanel();
	final private int enterhotelerrorWidth = 300, enterhotelerrorHeight = 150;
	final private Dimension enterhotelerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel entersearcherrorText = new JLabel("INVALID DATE!", JLabel.CENTER);
	private JLabel backenterhotelerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of search hotel
	private JPanel Search = new JPanel();
	final private int searchWidth = 570, searchHeight = 250;
	final private Dimension searchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel star5 = new JLabel("5-star", JLabel.CENTER);
	private JLabel star4 = new JLabel("4-star", JLabel.CENTER);
	private JLabel star3 = new JLabel("3-star", JLabel.CENTER);
	private JLabel star2 = new JLabel("2-star", JLabel.CENTER);
	private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
	private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
	private JLabel backsearch = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve
	private JPanel Reserve = new JPanel();
	final private int reserveWidth = 620, reserveHeight = 300;
	final private Dimension reserveCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backreserve = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextreserve = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField reservecheckindateField = new JTextField(10);
	protected JTextField reservecheckoutdateField = new JTextField(10);
	protected TextField reservehotelIDField = new TextField(15);
	protected TextField reservesingleroomField = new TextField(2);
	protected TextField reservedoubleroomField = new TextField(2);
	protected TextField reservequadroomField = new TextField(2);
	protected JComboBox<Object> reservehotelid = new JComboBox<Object>();

	// attribute of inquiry
	private JPanel Inquiry = new JPanel();
	final private int InquiryWidth = 600, InquiryHeight = 150;
	final private Dimension InquiryCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
	protected TextField reservenumberField = new TextField(15);

	// attribute of MCR (reservation record and modify and cancel reservation)
	private JPanel MCR = new JPanel();
	final private int mcrWidth = 600, mcrHeight = 300;
	final private Dimension mcrCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
	private JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backmcr = new JLabel("BACK", JLabel.CENTER);
	protected TextField mcrhotelIDField = new TextField(15);
	protected TextField mcrsingleroomField = new TextField(2);
	protected TextField mcrdoubleroomField = new TextField(2);
	protected TextField mcrquadroomField = new TextField(2);
	protected JTextField mcrcheckindateField = new JTextField(10);
	protected JTextField mcrcheckoutdateField = new JTextField(10);
	protected TextField mcrstaynightField = new TextField(2);
	protected TextField mcrpriceField = new TextField(5);

	// Menu(Panel) settings
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}
	private void initFonts() {
		titleText.setFont(new Font("Chalkboard", Font.BOLD, 60));
		signinText.setFont(new Font("Arial Black", Font.BOLD, 30));
		signupText.setFont(new Font("Arial Black", Font.BOLD, 30));
	}
	private void initTitle() {
		title.setLayout(new GridLayout(1, 1, 0, 0));
		title.setOpaque(false);
		titleText.setForeground(new Color(65, 105, 225));
		titleText.setOpaque(false);
//		titleText.setBackground(new Color(176, 196, 222));
		titleText.setBorder(new EmptyBorder(5, 5, 5, 5));
		title.add(titleText);
	}
	// sign in error
	private void initSigninerror() {
		signinerrorText.setFont(new Font("Arial", Font.BOLD, 28));
		signinerrorText.setForeground(new Color(255, 0, 0));
		backsigninerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror.setOpaque(false);
		Signinerror.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signinerror.add(signinerrorText);
		Signinerror.add(backsigninerror);

	}
	// sign up error
	private void initSignuperror() {
		signuperrorText.setFont(new Font("Arial", Font.BOLD, 28));
		signuperrorText.setForeground(new Color(255, 0, 0));
		backsignuperror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror.setOpaque(false);
		Signuperror.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signuperror.add(signuperrorText);
		Signuperror.add(backsignuperror);
	}
	// sign in
	private void initSignIn() {
		Signin.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signin.setLayout(new GridLayout(3, 1));
		Signin.setOpaque(false);

		// enter ID Panel setting
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter ID
		JLabel ID = new JLabel("       ID       : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinidField.setEditable(true);
		signinidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		// ID Panel adding
		IDPanel.add(ID);
		IDPanel.add(signinidField);

		// enter password Panel setting
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter password
		JLabel password = new JLabel("PASSWORD : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinpasswordField.setEchoChar('●');
		signinpasswordField.setEditable(true);
		signinpasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		// password Panel adding
		passwordPanel.add(password);
		passwordPanel.add(signinpasswordField);

		// set 'back' and 'login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		signinlogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		signinback.setFont(new Font("Arial Black", Font.PLAIN, 15));
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(signinback);
		buttons.add(signinlogin);

		// sign in adding
		Signin.add(IDPanel);
		Signin.add(passwordPanel);
		Signin.add(buttons);
	}
	// sign up
	private void initSignUp() {
		Signup.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signup.setLayout(new GridLayout(4, 1));
		Signup.setOpaque(false);

		// set ID
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel ID = new JLabel("         ID         : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signupidField.setEditable(true);
		signupidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		IDPanel.add(ID);
		IDPanel.add(signupidField);

		// set password
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel password = new JLabel("PASSWORD   : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signuppasswordField.setEchoChar('●');
		signuppasswordField.setEditable(true);
		signuppasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		passwordPanel.add(password);
		passwordPanel.add(signuppasswordField);

		// verify code Panel
		JPanel verifycodePanel = new JPanel();
		verifycodePanel.setOpaque(false);
		verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter verify code
		JLabel verifycode = new JLabel("VERIFY CODE : ");
		verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
		usercodeField.setEditable(true);
		usercodeField.setFont(new Font("Arial Black", Font.BOLD, 23));
		usercodeField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				String s = usercodeField.getText();
				if (s.length() >= 6)
					e.consume();
			}
		});
		verifycodeField.setFont(new Font("Arial Black", Font.BOLD, 15));
		// verify code panel adding
		verifycodePanel.add(verifycode);
		verifycodePanel.add(usercodeField);
		verifycodePanel.add(verifycodeField);

		// set 'cancel' and 'sign up and login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setLayout(new GridLayout(1, 2));
		signuplogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		signupcancel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		buttons.add(signupcancel);
		buttons.add(signuplogin);

		// sign up adding
		Signup.add(IDPanel);
		Signup.add(passwordPanel);
		Signup.add(verifycodePanel);
		Signup.add(buttons);

	}
	// hotel function hotel list/reserve/inquiry
	private void initHotelfunction() {
		searchText.setFont(new Font("Arial Black", Font.BOLD, 30));
		reserveText.setFont(new Font("Arial Black", Font.BOLD, 30));
		inquiryText.setFont(new Font("Arial Black", Font.BOLD, 30));
		logout.setFont(new Font("Arial Black", Font.BOLD, 30));
		Hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
		Hotelfunction.setOpaque(false);
		Hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Hotelfunction.add(searchText);
		Hotelfunction.add(reserveText);
		Hotelfunction.add(inquiryText);
		Hotelfunction.add(logout);
	}
	// enter hotel list date/people/rooms
	private void initEnterSearch() {
		EnterSearch.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		EnterSearch.setLayout(new GridLayout(5, 1));
		EnterSearch.setOpaque(false);

		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setOpaque(false);
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckindateField.setEditable(false);
		entercheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckindateField.setBackground(new Color(255, 255, 255));
		entercheckindateField.setText("SELECT A DATE");
		entercheckindateField.setOpaque(true);
		entercheckindateField.setBounds(267, 15, 105, 40);
		entercheckindateField.setColumns(10);
		entercheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(entercheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setOpaque(false);
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter check out date
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckoutdateField.setEditable(false);
		entercheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckoutdateField.setBackground(new Color(255, 255, 255));
		entercheckoutdateField.setText("SELECT A DATE");
		entercheckoutdateField.setOpaque(true);
		entercheckoutdateField.setBounds(267, 15, 105, 40);
		entercheckoutdateField.setColumns(10);
		entercheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(entercheckoutdateField);
				
		// people panel
		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		peoplePanel.setOpaque(false);
		peoplePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
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
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
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
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backenterhotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextenterhotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backenterhotellist);
		buttons.add(nextenterhotellist);

		// EnterHotellist adding
		EnterSearch.add(checkinPanel);
		EnterSearch.add(checkoutPanel);
		EnterSearch.add(peoplePanel);
		EnterSearch.add(roomPanel);
		EnterSearch.add(buttons);
	}
	// enter hotel list error (select wrong date)
	private void initEnterSearcherror() {
		entersearcherrorText.setFont(new Font("Arial", Font.BOLD, 28));
		entersearcherrorText.setForeground(new Color(255, 0, 0));
		entersearcherrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backenterhotelerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backenterhotelerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		Entersearcherror.setLayout(new GridLayout(2, 1, 0, 0));
		Entersearcherror.setOpaque(false);
		Entersearcherror.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Entersearcherror.add(entersearcherrorText);
		Entersearcherror.add(backenterhotelerror);
	}
	// hotel list
	private void initSearch() {
		// set font
		star5.setFont(new Font("Arial Black", Font.BOLD, 28));
		star4.setFont(new Font("Arial Black", Font.BOLD, 28));
		star3.setFont(new Font("Arial Black", Font.BOLD, 28));
		star2.setFont(new Font("Arial Black", Font.BOLD, 28));
		pricehighText.setFont(new Font("Arial Black", Font.BOLD, 28));
		pricelowText.setFont(new Font("Arial Black", Font.BOLD, 28));
		backsearch.setFont(new Font("Arial Black", Font.BOLD, 28));
		Search.setLayout(new GridLayout(4, 1, 0, 0));
		Search.setOpaque(true);
		Search.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Search.setBackground(new Color(176, 196, 222));
		JPanel star = new JPanel();
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setBackground(new Color(176, 196, 222));
		star.add(star5);
		star.add(star4);
		star.add(star3);
		star.add(star2);
		Search.add(star);
		Search.add(pricehighText);
		Search.add(pricelowText);
		Search.add(backsearch);
	}
	// Reserve
	private void initReserve() {
		Reserve.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserve.setLayout(new GridLayout(5, 1));
		Reserve.setOpaque(false);

		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkinPanel.setOpaque(false);
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckindateField.setEditable(false);
		reservecheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckindateField.setBackground(new Color(255, 255, 255));
		reservecheckindateField.setText("SELECT DATE");
		reservecheckindateField.setOpaque(true);
		reservecheckindateField.setBounds(267, 15, 105, 40);
		reservecheckindateField.setColumns(10);
		reservecheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(reservecheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkoutPanel.setOpaque(false);
		// enter check out date
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckoutdateField.setEditable(false);
		reservecheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckoutdateField.setBackground(new Color(255, 255, 255));
		reservecheckoutdateField.setText("SELECT DATE");
		reservecheckoutdateField.setOpaque(true);
		reservecheckoutdateField.setBounds(267, 15, 105, 40);
		reservecheckoutdateField.setColumns(10);
		reservecheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(reservecheckoutdateField);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		hotelIDPanel.setOpaque(false);
		// select hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		Integer[] option = new Integer[1500];
		for (int i = 0; i < 1500; i++) {
			option[i] = i;
		}
		String[] option2 = new String[1500];
		for (int i = 0; i < 1500; i++) {
			option2[i] = (option[i]).toString();
		}
		reservehotelid = new JComboBox<Object>(option2);
//		reservehotelIDField.setEditable(false);
//		reservehotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
//		reservehotelIDField.addKeyListener(new KeyAdapter() {// can only enter number!
//			public void keyTyped(KeyEvent e) {
//				char keyChar = e.getKeyChar();
//				if (!(keyChar >= '0' && keyChar <= '9')) {
//					e.consume();
//				}
//				String s = reservehotelIDField.getText();
//				if (s.length() >= 4)
//					e.consume();
//			}
//		});
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(reservehotelid);

		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		roomPanel.setOpaque(false);
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
		buttons.setOpaque(false);
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
		Inquiry.setOpaque(false);
		Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

		// enter reserve number
		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

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
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
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
		MCR.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		MCR.setOpaque(false);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setOpaque(false);
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mcrhotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
		mcrhotelIDField.setEditable(false);
		// hotel ID Panel adding
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(mcrhotelIDField);

		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// single room
		JLabel singleroom = new JLabel("Single: ");
		singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mcrsingleroomField.setEditable(false);
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
		mcrdoubleroomField.setEditable(false);
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
		mcrquadroomField.setEditable(false);
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
		lodgingPanel.setOpaque(false);
		lodgingPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		mcrcheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		mcrcheckindateField.setEditable(false);
		JLabel mark = new JLabel("~");
		mark.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mcrcheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		mcrcheckoutdateField.setEditable(false);
		// lodgingPanel adding
		lodgingPanel.add(mcrcheckindateField);
		lodgingPanel.add(mark);
		lodgingPanel.add(mcrcheckoutdateField);

		// 'total length of stay' and 'total price'
		JPanel staypricePanel = new JPanel();
		staypricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		staypricePanel.setOpaque(false);
		staypricePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel stay = new JLabel("Total Nights of Stay:");
		stay.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mcrstaynightField.setFont(new Font("Serif", Font.BOLD, 23));
		mcrstaynightField.setEditable(false);
		JLabel price = new JLabel("Total Price:");
		price.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mcrpriceField.setFont(new Font("Serif", Font.BOLD, 23));
		mcrpriceField.setEditable(false);
		// stay price Panel adding
		staypricePanel.add(stay);
		staypricePanel.add(mcrstaynightField);
		staypricePanel.add(price);
		staypricePanel.add(mcrpriceField);

		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
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
	public void showMCR(int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate, int night,
			int p) {
		mcrhotelIDField.setText(Integer.toString(hid));
		mcrsingleroomField.setText(Integer.toString(sroom));
		mcrdoubleroomField.setText(Integer.toString(droom));
		mcrquadroomField.setText(Integer.toString(qroom));
		mcrcheckindateField.setText(chkindate);
		mcrcheckoutdateField.setText(chkoutdate);
		mcrstaynightField.setText(Integer.toString(night));
		mcrpriceField.setText(Integer.toString(p));
	}
	// clear all the Text
	private void clearalltext() {
		signinidField.setText(null);
		signinpasswordField.setText(null);
		signupidField.setText(null);
		signuppasswordField.setText(null);
		usercodeField.setText(null);
		entercheckindateField.setText("SELECT DATE");
		entercheckoutdateField.setText("SELECT DATE");
		enterpeopleField.setText(null);
		enterroomField.setText(null);
		mcrhotelIDField.setText(null);
		mcrsingleroomField.setText(null);
		mcrdoubleroomField.setText(null);
		mcrquadroomField.setText(null);
		mcrcheckindateField.setText(null);
		mcrcheckoutdateField.setText(null);
		mcrstaynightField.setText(null);
		mcrpriceField.setText(null);
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservehotelIDField.setText(null);
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
		Integer[] option = new Integer[1500];
		for (int i = 0; i < 1500; i++) {
			option[i] = i;
		}
		String[] option2 = new String[1500];
		for (int i = 0; i < 1500; i++) {
			option2[i] = (option[i]).toString();
		}
		reservehotelid = new JComboBox<Object>(option2);
	}
	// sub menu
	private void initSubMenu() {
		subMenu.setLayout(new GridLayout(1, 2, 0, 0));
		subMenu.setOpaque(false);
		subMenu.setBackground(null);
		subMenu.add(signinText);
		subMenu.add(signupText);
//		subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
	}
	private void initLayerPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

		this.background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
		this.background.setBounds(0, 0, frameWidth, frameHeight);
		layeredPane.add(background, new Integer(0));

		this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2), titleWidth,
				titleHeight);
		layeredPane.add(title, new Integer(1));

		this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
				subMenuWidth, subMenuHeight);
		layeredPane.add(subMenu, new Integer(2));

		this.add(layeredPane);

		this.Signin.setBounds(signinSetCenter.width - (signinSetWidth / 2),
				signinSetCenter.height - (signinSetHeight / 2), signinSetWidth, signinSetHeight);

		this.Signup.setBounds(signupSetCenter.width - (signupSetWidth / 2),
				signupSetCenter.height - (signupSetHeight / 2), signupSetWidth, signupSetHeight);

		this.Signinerror.setBounds(signinerrorCenter.width - (signinerrorWidth / 2),
				signinerrorCenter.height - (signinerrorHeight / 2), signinerrorWidth, signinerrorHeight);

		this.Signuperror.setBounds(signuperrorCenter.width - (signuperrorWidth / 2),
				signuperrorCenter.height - (signuperrorHeight / 2), signuperrorWidth, signuperrorHeight);

		this.Hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
				hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);

		this.EnterSearch.setBounds(enterhotellistCenter.width - (enterhotellistWidth / 2),
				enterhotellistCenter.height - (enterhotellistHeight / 2), enterhotellistWidth, enterhotellistHeight);

		this.Entersearcherror.setBounds(enterhotelerrorCenter.width - (enterhotelerrorWidth / 2),
				enterhotelerrorCenter.height - (enterhotelerrorHeight / 2), enterhotelerrorWidth,
				enterhotelerrorHeight);

		this.Search.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);

		this.Reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
				reserveWidth, reserveHeight);

		this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
				InquiryWidth, InquiryHeight);

		this.MCR.setBounds(mcrCenter.width - (mcrWidth / 2), mcrCenter.height - (mcrHeight / 2), mcrWidth, mcrHeight);
	}
	
	public Menu() {
		initPanel();
		initFonts();
		initTitle();
		initSubMenu();
		initSignIn();
		initSignUp();
		initHotelfunction();
		initEnterSearch();
		initSearch();
		initReserve();
		initInquiry();
		initMCR();
		initLayerPane();
		initSigninerror();
		initSignuperror();
		initEnterSearcherror();
		// buttons in sub menu / sign in / sign up
		signinText.addMouseListener(ml);
		signupText.addMouseListener(ml);
		signinback.addMouseListener(ml);
		signinlogin.addMouseListener(ml);
		signupcancel.addMouseListener(ml);
		signuplogin.addMouseListener(ml);
		// buttons of sign in sign up error
		backsigninerror.addMouseListener(ml);
		backsignuperror.addMouseListener(ml);
		// buttons in hotel function
		searchText.addMouseListener(ml);
		reserveText.addMouseListener(ml);
		inquiryText.addMouseListener(ml);
		logout.addMouseListener(ml);
		// buttons in enter hotel list
		backenterhotellist.addMouseListener(ml);
		nextenterhotellist.addMouseListener(ml);
		// buttons in enter hotel list error
		backenterhotelerror.addMouseListener(ml);
		// buttons in hotel list
		star5.addMouseListener(ml);
		star4.addMouseListener(ml);
		star3.addMouseListener(ml);
		star2.addMouseListener(ml);
		pricehighText.addMouseListener(ml);
		pricelowText.addMouseListener(ml);
		backsearch.addMouseListener(ml);
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
				verifycodeField.setText(main.getRandomString(6));
				layeredPane.add(Signup, new Integer(2));
				validate();
				repaint();
				signupText.setForeground(Color.black);
			} else if (e.getSource() == signupcancel) {
				layeredPane.remove(Signup);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				signupcancel.setForeground(Color.black);
			} else if (e.getSource() == signuplogin) {
				// get the sign up id and password and verify code
				String UserID = signupidField.getText();
				String Password = signuppasswordField.getText();
				String UserCode = usercodeField.getText(); // user enter verify code
				String VerifyCode = verifycodeField.getText(); // random verify code
				if (main.SignUpCheck(UserID, Password, UserCode)) {
					if (UserCode.equals(VerifyCode)) {
						System.out.println("hi");
						main.user = new User(UserID, Password);
						main.UserList.add(main.user);
						
						layeredPane.add(title);
						layeredPane.remove(Signup);
	//					layeredPane.add(Signuperror, new Integer(3));
						layeredPane.add(Hotelfunction, new Integer(3));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					} else {
//跳出錯誤訊息:Wrong verify code.		
//清空欄位
						verifycodeField.setText(main.getRandomString(6));
					}
				} else {
//跳出錯誤訊息:UserID already existed.
//清空三個欄位	 
					verifycodeField.setText(main.getRandomString(6));
				}
			} else if (e.getSource() == signinText) {
				layeredPane.remove(subMenu);
				layeredPane.add(Signin, new Integer(2));
				validate();
				repaint();
				signinText.setForeground(Color.black);
			} else if (e.getSource() == signinback) {
				layeredPane.remove(Signin);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				signinback.setForeground(Color.black);
			} else if (e.getSource() == signinlogin) {
				String UserID = signinidField.getText();
				String Password = signinpasswordField.getText();
				int re = main.SignInCheck(UserID, Password);
				if (re == 1) {
					layeredPane.add(title);
					layeredPane.remove(Signin);
//					layeredPane.add(Signinerror, new Integer(2));
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == 0) {
//錯誤訊息:UserID doesn't exist.
//清空欄位
				} else if (re == -1) {
//錯誤訊息:Wrong Password.
//清空欄位
				}
			} else if (e.getSource() == logout || e.getSource() == backsigninerror
					|| e.getSource() == backsignuperror) {
				layeredPane.remove(Hotelfunction);
				layeredPane.remove(Signin);
				layeredPane.remove(Signup);
				layeredPane.remove(Signinerror);
				layeredPane.remove(Signuperror);
				layeredPane.add(title);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				logout.setForeground(Color.black);
				backsigninerror.setForeground(Color.black);
				backsignuperror.setForeground(Color.black);
				// clear all the text field
				clearalltext();
			} else if (e.getSource() == searchText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				searchText.setForeground(Color.black);
			} else if (e.getSource() == nextenterhotellist) {
				// get enter check in date yyyy/mm/dd
				String CID = entercheckindateField.getText();
				// get enter check out date yyyy/mm/dd
				String COD = entercheckoutdateField.getText();
				// get enter people and room
				int People = Integer.parseInt(enterpeopleField.getText());
				int RoomNumbers = Integer.parseInt(enterroomField.getText());
//這裡看看能不能直接在選日期的時候限定好				
				if (main.CountDaysBetween(CID, COD) >= 0) {
					ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, RoomNumbers);
					layeredPane.remove(EnterSearch);
					layeredPane.add(Search, new Integer(3));
//					layeredPane.add(Entersearcherror,new Integer(3));
					validate();
					repaint();
					nextenterhotellist.setForeground(Color.black);
				} else {
//錯誤訊息:Invaid Date.
				}
			} else if (e.getSource() == backsearch) {
				layeredPane.remove(Search);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				backsearch.setForeground(Color.black);
			} else if (e.getSource() == backenterhotelerror) {
				layeredPane.remove(Entersearcherror);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				backenterhotelerror.setForeground(Color.black);
				entercheckindateField.setText(null);
				entercheckoutdateField.setText(null);
				enterpeopleField.setText(null);
				enterroomField.setText(null);
			} else if (e.getSource() == backenterhotellist || e.getSource() == backreserve
					|| e.getSource() == backinquiry || e.getSource() == backmcr) {
				layeredPane.remove(EnterSearch);
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
				//yyyy/mm/dd
				String CID = reservecheckindateField.getText();
				String COD = reservecheckoutdateField.getText();
				int HotelID = reservehotelid.getSelectedIndex();
				int sn = Integer.parseInt(reservesingleroomField.getText());
				int dn = Integer.parseInt(reservedoubleroomField.getText());
				int qn = Integer.parseInt(reservequadroomField.getText());
				if (main.BookHotel(CID, COD, HotelID, sn, dn, qn)) {
//訂房成功的output					
				} else {
//錯誤訊息:訂房失敗 房間數量不足/房間已售罄					
				}
			} else if (e.getSource() == inquiryText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				inquiryText.setForeground(Color.black);
			} else if (e.getSource() == nextinquiry) {
				// get reserve number
				int OrderID = Integer.parseInt(reservenumberField.getText());
				layeredPane.remove(Inquiry);
				showMCR(reservehotelid.getSelectedIndex(), Integer.parseInt(reservesingleroomField.getText()),
						Integer.parseInt(reservedoubleroomField.getText()),
						Integer.parseInt(reservequadroomField.getText()), reservecheckindateField.getText(),
						reservecheckoutdateField.getText(), 10, 30);
				layeredPane.add(MCR, new Integer(3));
				validate();
				repaint();
				nextinquiry.setForeground(Color.black);
			}

		}
	};
}
