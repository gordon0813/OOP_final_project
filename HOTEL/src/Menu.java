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


/*
 * to do:
 * 1. Search的output 5~2 Star & Price(highest/lowest first)
 * 2. CheckInDate/CheckOutDate 日期限制
 * 3. 訂房成功＆失敗的output
 * 4. 輸入訂單編號不存在的錯誤訊息
 * 5. Inquiry的Modify跟Cancel
 */


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
	
	// attribute of sign in error - UNKNOWN ID
	private JPanel Signinerror = new JPanel();
	final private int signinerrorWidth = 700, signinerrorHeight = 110;
	final private Dimension signinerrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinerrorText = new JLabel("UNKNOWN ID.", JLabel.CENTER);
	private JLabel backsigninerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign in error - WRONG PASSWORD
	private JPanel Signinerror1 = new JPanel();
	final private int signinerror1Width = 700, signinerror1Height = 110;
	final private Dimension signinerror1Center = new Dimension(frameWidth / 2, 500);
	private JLabel signinerror1Text = new JLabel("WRONG PASSWORD.", JLabel.CENTER);
	private JLabel backsigninerror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - USERID ALREADY EXISTS.
	private JPanel Signuperror = new JPanel();
	final private int signuperrorWidth = 500, signuperrorHeight = 110;
	final private Dimension signuperrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuperrorText = new JLabel("USERID ALREADY EXISTS.", JLabel.CENTER);
	private JLabel backsignuperror = new JLabel("BACK", JLabel.CENTER);
		
	// attribute of sign up error - WRONG VERIFY CODE.
	private JPanel Signuperror1 = new JPanel();
	final private int signuperror1Width = 500, signuperror1Height = 110;
	final private Dimension signuperrorCenter1 = new Dimension(frameWidth / 2, 500);
	private JLabel signuperror1Text = new JLabel("WRONG VERIFY CODE.", JLabel.CENTER);
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

	// attribute of Hotel function Search/Reserve/Inquiry
	private JPanel Hotelfunction = new JPanel();
	final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
	final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
	private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
	private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
	private JLabel logout = new JLabel("LOGOUT", JLabel.CENTER);

	// attribute of entering Search Date, People, Rooms
	private JPanel EnterSearch = new JPanel();
	final private int entersearchWidth = 700, entersearchlistHeight = 300;
	final private Dimension entersearchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backentersearch = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextentersearch = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField entercheckindateField = new JTextField(10);
	protected JTextField entercheckoutdateField = new JTextField(10);
	protected TextField enterpeopleField = new TextField(15);
	protected TextField enterroomField = new TextField(15);

	// attribute of enter invalid date error
	private JPanel Enter_invalid_date_error = new JPanel();
	final private int enterinvaiddateerrorWidth = 300, enterinvaliddateerrorHeight = 150;
	final private Dimension enterinvaliddateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel enterinvaliddateerrorText = new JLabel("INVALID DATE!", JLabel.CENTER);
	private JLabel backenterinvaliddateerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of no matched hotel error
	private JPanel No_matched_hotel_error = new JPanel();
	final private int nomatchedhotelerrorWidth = 500, nomatchedhotelerrorHeight = 150;
	final private Dimension nomatchedhotelerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel nomatchedhotelerrorText = new JLabel("NO MATCHED HOTEL!", JLabel.CENTER);
	private JLabel backnomatchedhotelerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of Search
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

	// attribute of Reserve
	private JPanel Reserve = new JPanel();
	final private int reserveWidth = 620, reserveHeight = 300;
	final private Dimension reserveCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backreserve = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextreserve = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField reservecheckindateField = new JTextField(10);
	protected JTextField reservecheckoutdateField = new JTextField(10);
//	protected TextField reservehotelIDField = new TextField(15);
	protected TextField reservesingleroomField = new TextField(2);
	protected TextField reservedoubleroomField = new TextField(2);
	protected TextField reservequadroomField = new TextField(2);
	protected JComboBox<Object> reservehotelid = new JComboBox<Object>();

	// attribute of reserve error (sold out)
	private JPanel Soldout = new JPanel();
	final private int soldoutWidth = 700, soldoutHeight = 150;
	final private Dimension soldoutCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel soldoutText = new JLabel("Sorry, NO VACANT SUITES!", JLabel.CENTER);
	private JLabel backsoldout = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve invalid date error
	private JPanel Reserve_invalid_date_error = new JPanel();
	final private int reserveinvaiddateerrorWidth = 300, reserveinvaliddateerrorHeight = 150;
	final private Dimension reserveinvaliddateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel reserveinvaliddateerrorText = new JLabel("INVALID DATE!", JLabel.CENTER);
	private JLabel backreserveinvaliddateerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve success
	private JPanel Reserve_success = new JPanel();
	final private int reservesuccessWidth = 600, reservesuccessHeight = 75;
	final private Dimension reservesuccessCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	protected TextField successreservenumberField = new TextField(20);

	// attribute of inquiry
	private JPanel Inquiry = new JPanel();
	final private int InquiryWidth = 600, InquiryHeight = 150;
	final private Dimension InquiryCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
	protected TextField reservenumberField = new TextField(15);

	// attribute of wrong reservation number
	private JPanel Wrong_reservation_number = new JPanel();
	final private int wrongreservationnumberWidth = 700, wrongreservationnumberHeight = 150;
	final private Dimension wrongreservationnumberCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel wrongreservationnumberText = new JLabel("WRONG RESERVATION NUMBER!", JLabel.CENTER);
	private JLabel backwrongreservationnumber = new JLabel("BACK", JLabel.CENTER);

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
	private void initTitle() {
		titleText.setFont(new Font("Chalkboard", Font.BOLD, 60));
		signinText.setFont(new Font("Arial Black", Font.BOLD, 30));
		signupText.setFont(new Font("Arial Black", Font.BOLD, 30));

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
	// sign in error1
	private void initSigninerror1() {
		signinerror1Text.setFont(new Font("Arial", Font.BOLD, 28));
		signinerror1Text.setForeground(new Color(255, 0, 0));
		backsigninerror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror1.setOpaque(false);
		Signinerror1.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signinerror1.add(signinerror1Text);
		Signinerror1.add(backsigninerror1);

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
	private void initSignuperror1() {
		signuperror1Text.setFont(new Font("Arial", Font.BOLD, 28));
		signuperror1Text.setForeground(new Color(255, 0, 0));
		backsignuperror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror1.setOpaque(false);
		Signuperror1.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signuperror1.add(signuperror1Text);
		Signuperror1.add(backsignuperror);
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
		backentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backentersearch);
		buttons.add(nextentersearch);

		// EnterHotellist adding
		EnterSearch.add(checkinPanel);
		EnterSearch.add(checkoutPanel);
		EnterSearch.add(peoplePanel);
		EnterSearch.add(roomPanel);
		EnterSearch.add(buttons);
	}
	// enter hotel list error (select wrong date)
	private void initEnterinvaliddateerror() {
		enterinvaliddateerrorText.setFont(new Font("Arial", Font.BOLD, 28));
		enterinvaliddateerrorText.setForeground(new Color(255, 0, 0));
		enterinvaliddateerrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backenterinvaliddateerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backenterinvaliddateerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		Enter_invalid_date_error.setLayout(new GridLayout(2, 1, 0, 0));
		Enter_invalid_date_error.setOpaque(false);
		Enter_invalid_date_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Enter_invalid_date_error.add(enterinvaliddateerrorText);
		Enter_invalid_date_error.add(backenterinvaliddateerror);
	}
	// search hotel
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
		Search.setOpaque(false);
		Search.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		JPanel star = new JPanel();
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star.add(star5);
		star.add(star4);
		star.add(star3);
		star.add(star2);
		Search.add(star);
		Search.add(pricehighText);
		Search.add(pricelowText);
		Search.add(backsearch);
	}
	// search hotel error (No matched Hotel)
	private void initNomatchedhotelerror() {
		nomatchedhotelerrorText.setFont(new Font("Arial", Font.BOLD, 28));
		nomatchedhotelerrorText.setForeground(new Color(255, 0, 0));
		nomatchedhotelerrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backnomatchedhotelerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backnomatchedhotelerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		No_matched_hotel_error.setLayout(new GridLayout(2, 1, 0, 0));
		No_matched_hotel_error.setOpaque(false);
		No_matched_hotel_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		No_matched_hotel_error.add(nomatchedhotelerrorText);
		No_matched_hotel_error.add(backnomatchedhotelerror);
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
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++) {
			option[i] = i.toString();
		}
		reservehotelid = new JComboBox<Object>(option);
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
	// Reserve success
	private void initReservesuccess() {
		Reserve_success.setLayout(new GridLayout(1, 1, 0, 0));
		Reserve_success.setOpaque(false);
		Reserve_success.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		JLabel reservenumber = new JLabel("SUCCEED! RESERVATION NUMBER : ");
		reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 17));
		successreservenumberField.setFont(new Font("Serif", Font.BOLD, 17));
		reservenumberPanel.add(reservenumber);
		reservenumberPanel.add(successreservenumberField);

		Reserve_success.add(reservenumberPanel);
	}
	// Reserve invalid date error
	private void initReserveinvaliddateerror() {
		reserveinvaliddateerrorText.setFont(new Font("Arial", Font.BOLD, 28));
		reserveinvaliddateerrorText.setForeground(new Color(255, 0, 0));
		reserveinvaliddateerrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backreserveinvaliddateerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backreserveinvaliddateerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		Reserve_invalid_date_error.setLayout(new GridLayout(2, 1, 0, 0));
		Reserve_invalid_date_error.setOpaque(false);
		Reserve_invalid_date_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserve_invalid_date_error.add(reserveinvaliddateerrorText);
		Reserve_invalid_date_error.add(backreserveinvaliddateerror);
	}
	// sold out
	private void initSoldout() {
		soldoutText.setFont(new Font("Arial", Font.BOLD, 28));
		soldoutText.setForeground(new Color(255, 0, 0));
		soldoutText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backsoldout.setFont(new Font("Arial Black", Font.BOLD, 28));
		backsoldout.setBorder(new EmptyBorder(20, 40, 20, 40));
		Soldout.setLayout(new GridLayout(2, 1, 0, 0));
		Soldout.setOpaque(false);
		Soldout.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Soldout.add(soldoutText);
		Soldout.add(backsoldout);
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
		backinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		nextinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		buttons.add(backinquiry);
		buttons.add(nextinquiry);

		// inquiry adding
		Inquiry.add(reservenumberPanel);
		Inquiry.add(buttons);
	}
	// wrong reservation number
	private void initWrongreservationnumber() {
		wrongreservationnumberText.setFont(new Font("Arial", Font.BOLD, 28));
		wrongreservationnumberText.setForeground(new Color(255, 0, 0));
		wrongreservationnumberText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backwrongreservationnumber.setFont(new Font("Arial Black", Font.BOLD, 28));
		backwrongreservationnumber.setBorder(new EmptyBorder(20, 40, 20, 40));
		Wrong_reservation_number.setLayout(new GridLayout(2, 1, 0, 0));
		Wrong_reservation_number.setOpaque(false);
		Wrong_reservation_number.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Wrong_reservation_number.add(wrongreservationnumberText);
		Wrong_reservation_number.add(backwrongreservationnumber);
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
	// show reserve success
	public void showreservesuccess(int OrderID, int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate, int night,
			int p) {
		successreservenumberField.setText(Integer.toString(OrderID));
		layeredPane.add(Reserve_success, new Integer(3));
		showMCR(hid, sroom, droom, qroom, chkindate, chkoutdate, night, p);
		layeredPane.add(MCR, new Integer(3));
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
	}
	// clear all the Text when logout
	public void clearalltext() {
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
//		reservehotelIDField.setText(null);
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++) {
			option[i] = i.toString();
		}
		reservehotelid = new JComboBox<Object>(option);
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

		this.Signinerror1.setBounds(signinerror1Center.width - (signinerror1Width / 2),
				signinerror1Center.height - (signinerror1Height / 2), signinerror1Width, signinerror1Height);

		this.Signuperror.setBounds(signuperrorCenter.width - (signuperrorWidth / 2),
				signuperrorCenter.height - (signuperrorHeight / 2), signuperrorWidth, signuperrorHeight);
		
		this.Signuperror1.setBounds(signuperrorCenter1.width - (signuperror1Width / 2),
				signuperrorCenter1.height - (signuperror1Height / 2), signuperror1Width, signuperror1Height);

		this.Hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
				hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);

		this.EnterSearch.setBounds(entersearchCenter.width - (entersearchWidth / 2),
				entersearchCenter.height - (entersearchlistHeight / 2), entersearchWidth, entersearchlistHeight);

		this.Enter_invalid_date_error.setBounds(enterinvaliddateerrorCenter.width - (enterinvaiddateerrorWidth / 2),
				enterinvaliddateerrorCenter.height - (enterinvaliddateerrorHeight / 2), enterinvaiddateerrorWidth,
				enterinvaliddateerrorHeight);

		this.No_matched_hotel_error.setBounds(nomatchedhotelerrorCenter.width - (nomatchedhotelerrorWidth / 2),
				nomatchedhotelerrorCenter.height - (nomatchedhotelerrorHeight / 2), nomatchedhotelerrorWidth,
				nomatchedhotelerrorHeight);

		this.Search.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);

		this.Reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
				reserveWidth, reserveHeight);

		this.Reserve_invalid_date_error.setBounds(
				reserveinvaliddateerrorCenter.width - (reserveinvaiddateerrorWidth / 2),
				reserveinvaliddateerrorCenter.height - (reserveinvaliddateerrorHeight / 2), reserveinvaiddateerrorWidth,
				reserveinvaliddateerrorHeight);

		this.Reserve_success.setBounds(reservesuccessCenter.width - (reservesuccessWidth / 2),
				reservesuccessCenter.height - (reservesuccessHeight / 2), reservesuccessWidth, reservesuccessHeight);

		this.Soldout.setBounds(soldoutCenter.width - (soldoutWidth / 2), soldoutCenter.height - (soldoutHeight / 2),
				soldoutWidth, soldoutHeight);

		this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
				InquiryWidth, InquiryHeight);

		this.Wrong_reservation_number.setBounds(wrongreservationnumberCenter.width - (wrongreservationnumberWidth / 2),
				wrongreservationnumberCenter.height - (wrongreservationnumberHeight / 2), wrongreservationnumberWidth,
				wrongreservationnumberHeight);

		this.MCR.setBounds(mcrCenter.width - (mcrWidth / 2), mcrCenter.height - (mcrHeight / 2), mcrWidth, mcrHeight);
	}
	public Menu() {
		initPanel();
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
		initSigninerror1();
		initSignuperror();
		initSignuperror1();
		initEnterinvaliddateerror();
		initNomatchedhotelerror();
		initWrongreservationnumber();
		initSoldout();
		initReserveinvaliddateerror();
		initReservesuccess();
		// buttons in sub menu / sign in / sign up
		signinText.addMouseListener(ml);
		signupText.addMouseListener(ml);
		signinback.addMouseListener(ml);
		signinlogin.addMouseListener(ml);
		signupcancel.addMouseListener(ml);
		signuplogin.addMouseListener(ml);
		// buttons of sign in sign up error
		backsigninerror.addMouseListener(ml);
		backsigninerror1.addMouseListener(ml);
		backsignuperror.addMouseListener(ml);
		// buttons in hotel function
		searchText.addMouseListener(ml);
		reserveText.addMouseListener(ml);
		inquiryText.addMouseListener(ml);
		logout.addMouseListener(ml);
		// buttons in enter hotel list
		backentersearch.addMouseListener(ml);
		nextentersearch.addMouseListener(ml);
		// buttons in enter invalid date error
		backenterinvaliddateerror.addMouseListener(ml);
		// buttons in no matched hotel error
		backnomatchedhotelerror.addMouseListener(ml);
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
		// buttons in sold out
		backsoldout.addMouseListener(ml);
		// buttons in reserve invalid date
		backreserveinvaliddateerror.addMouseListener(ml);
		// buttons in inquiry
		backinquiry.addMouseListener(ml);
		nextinquiry.addMouseListener(ml);
		// buttons in wrong reservation number
		backwrongreservationnumber.addMouseListener(ml);
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
				String UserID = signupidField.getText();
				String Password = signuppasswordField.getText();
				String UserCode = usercodeField.getText(); // user enter verify code
				String VerifyCode = verifycodeField.getText(); // random verify code
				if (main.SignUpCheck(UserID, Password, UserCode)) {
					
					if (UserCode.equals(VerifyCode)) {
						//Create a new User
						main.user = new User(UserID, Password);
						main.UserList.add(main.user);
						
						layeredPane.remove(Signup);
						layeredPane.add(title);
						layeredPane.add(Hotelfunction, new Integer(3));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					} else {//Wrong verify code.	
						System.out.println("hihi");
						layeredPane.remove(Signup);
						layeredPane.add(Signuperror1, new Integer(3));
						signinidField.setText("");
						signinpasswordField.setText("");
						verifycodeField.setText(main.getRandomString(6));
						signuplogin.setForeground(Color.black);
						validate();
						repaint();
					}
				} else {//UserID already existed.
					layeredPane.remove(Signup);
					layeredPane.add(Signuperror, new Integer(3));
					signupidField.setText("");
					signuppasswordField.setText("");
					verifycodeField.setText(main.getRandomString(6));
					signuplogin.setForeground(Color.black);
					validate();
					repaint();

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
					layeredPane.remove(Signin);
					layeredPane.add(title);
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == 0) {
					//UserID doesn't exist.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
				} else if (re == -1) {
					//Wrong Password.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror1, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
				}
			} else if (e.getSource() == logout || e.getSource() == backsigninerror || e.getSource() == backsignuperror) { 
//這裡怪怪的 signin/signup error應該不用跑到最首頁吧？
				layeredPane.remove(Hotelfunction);
				layeredPane.remove(Signin);
				layeredPane.remove(Signup);
				layeredPane.remove(Signinerror);
				layeredPane.remove(Signinerror1);
				layeredPane.remove(Signuperror);
				layeredPane.remove(Signuperror1);
				layeredPane.add(title);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				logout.setForeground(Color.black);
				backsigninerror.setForeground(Color.black);
				backsigninerror1.setForeground(Color.black);
				backsignuperror.setForeground(Color.black);
				// clear all the text field
				clearalltext();
			} else if (e.getSource() == searchText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				searchText.setForeground(Color.black);
			} else if (e.getSource() == nextentersearch) {
				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
//這裡看看能不能直接在選日期的時候限定好				
				if (main.CountDaysBetween(CID, COD) >= 0) {
					ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					if (AHR.size() > 0) {
						layeredPane.remove(EnterSearch);
						layeredPane.add(Search, new Integer(3));
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					} else {
						//no matched hotel
						layeredPane.remove(EnterSearch);
						layeredPane.add(No_matched_hotel_error, new Integer(3));
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					}
				} else {//Invaid Date
					
				}
			} else if (e.getSource() == backsearch) {
				layeredPane.remove(Search);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				backsearch.setForeground(Color.black);
			} else if (e.getSource() == backenterinvaliddateerror || e.getSource() == backnomatchedhotelerror) {
				layeredPane.remove(Enter_invalid_date_error);
				layeredPane.remove(No_matched_hotel_error);
				layeredPane.add(EnterSearch, new Integer(3));
				entercheckindateField.setText("SELECT DATE");
				entercheckoutdateField.setText("SELECT DATE");
				enterpeopleField.setText(null);
				enterroomField.setText(null);
				validate();
				repaint();
				backenterinvaliddateerror.setForeground(Color.black);
				backnomatchedhotelerror.setForeground(Color.black);
			} else if (e.getSource() == backentersearch || e.getSource() == backreserve || e.getSource() == backinquiry
					|| e.getSource() == backmcr) {
				layeredPane.remove(EnterSearch);
				layeredPane.remove(Inquiry);
				layeredPane.remove(MCR);
				layeredPane.remove(Reserve);
				layeredPane.remove(Reserve_success);
				layeredPane.add(Hotelfunction);
				validate();
				repaint();
				backentersearch.setForeground(Color.black);
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
				String CID = reservecheckindateField.getText();//yyyy/mm/dd
				String COD = reservecheckoutdateField.getText();
				int HotelID = reservehotelid.getSelectedIndex();
				int sn = Integer.parseInt(reservesingleroomField.getText());
				int dn = Integer.parseInt(reservedoubleroomField.getText());
				int qn = Integer.parseInt(reservequadroomField.getText());
				Order order = main.BookHotel(CID, COD, HotelID, sn, dn, qn);
				if (order != null) {
					//訂房成功
                    layeredPane.remove(Reserve);
				    showreservesuccess(order.getID(), order.getHotelID(), order.getsn(), order.getdn(), order.getqn(), order.getCheckInDate(), order.getCheckOutDate(), 
				    		(int)main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()) ,order.getSumPrice());
			       	validate();
				    repaint();
				    nextreserve.setForeground(Color.black);
                } else {
                	//訂房失敗 房間數量不足/房間已售罄		
                	layeredPane.remove(Reserve);
                	layeredPane.add(Soldout, new Integer(3)); 
            	    validate();
				    repaint();
				    nextreserve.setForeground(Color.black);
		        }	
			} else if (e.getSource() == backsoldout || e.getSource() == backreserveinvaliddateerror) {
				layeredPane.remove(Soldout);
				layeredPane.remove(Reserve_invalid_date_error);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				backsoldout.setForeground(Color.black);
			} else if (e.getSource() == inquiryText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				inquiryText.setForeground(Color.black);
			} else if (e.getSource() == nextinquiry) {
				layeredPane.remove(Inquiry);
				// get reserve number
				int OrderID = Integer.parseInt(reservenumberField.getText());
				
				if (main.CheckOrder(OrderID) != null) {//unsolved bug : NPE
					Order order = main.CheckOrder(OrderID);
					System.out.println("here" + order != null);
					layeredPane.remove(Inquiry);
					showMCR(order.getHotelID(), order.getsn(), order.getdn(), order.getqn(), 
							order.getCheckInDate(), order.getCheckOutDate(), (int)main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
							order.getSumPrice());
					
					layeredPane.add(MCR, new Integer(3));
					validate();
					repaint();
					nextinquiry.setForeground(Color.black);
				} else {//不存在此訂單代號
     				layeredPane.add(Wrong_reservation_number, new Integer(3));
     				validate();
					repaint();
					reservenumberField.setText("");
					nextinquiry.setForeground(Color.black);
                }
			} else if (e.getSource() == backwrongreservationnumber) {
				layeredPane.remove(Wrong_reservation_number);
				reservenumberField.setText(null);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				backwrongreservationnumber.setForeground(Color.black);
			}

		}
	};
}
