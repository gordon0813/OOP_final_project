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
		this.setTitle("TRIANGLE");
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
		final private int subMenuWidth = 384, subMenuHeight = 300;
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
		private JTextArea creditText = new JTextArea(20, 60);
		private JLabel creditButt = new JLabel("BACK");

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

			titleText.setForeground(new Color(115, 74, 18));
			titleText.setOpaque(true);
			titleText.setBackground(new Color(250, 235, 215));
			titleText.setBorder(new MatteBorder(5, 5, 5, 5, new Color(61, 89, 171)));

			title.add(titleText);
		}

		// sign in 
		private void initSignIn() {
			signin.setLayout(new GridLayout(3, 1));
			
			JPanel IDPanel = new JPanel();
			JLabel ID = new JLabel("   ID         : ");
			TextField IDField = new TextField(15);
			IDField.setFont(new Font("Serif", Font.BOLD, 23));
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
			IDPanel.add(ID);
			IDPanel.add(IDField);
			ID.setBackground(new Color(245, 222, 179));
			signin.add(IDPanel);

			JPanel passwordPanel = new JPanel();
			JLabel password = new JLabel("PASSWORD : ");
			TextField passwordField = new TextField(15);
			passwordField.setFont(new Font("Serif", Font.BOLD, 23));
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
			passwordPanel.add(password);
			passwordPanel.add(passwordField);
			password.setBackground(new Color(245, 222, 179));
			signin.add(passwordPanel);
			

			// set 'back' and 'login' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.setBackground(new Color(245, 222, 179));
			buttons.add(back);
//			buttons.add(new Panel());
			buttons.add(login);
			signin.add(buttons);
		}

		// sign up
		private void initSignUp() {
			signup.setLayout(new GridLayout(4, 1));
			
			JPanel IDPanel = new JPanel();
			JLabel ID = new JLabel("   ID         : ");
			TextField IDField = new TextField(15);
			IDField.setFont(new Font("Serif", Font.BOLD, 23));
			IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			IDPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
			IDPanel.add(ID);
			IDPanel.add(IDField);
			ID.setBackground(new Color(245, 222, 179));
			signup.add(IDPanel);

			JPanel passwordPanel = new JPanel();
			JLabel password = new JLabel("PASSWORD : ");
			TextField passwordField = new TextField(15);
			passwordField.setFont(new Font("Serif", Font.BOLD, 23));
			passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			passwordPanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
			passwordPanel.add(password);
			passwordPanel.add(passwordField);
			password.setBackground(new Color(245, 222, 179));
			signup.add(passwordPanel);
			
			JPanel verifycodePanel = new JPanel();
			JLabel verifycode = new JLabel("VERIFYCODE : ");
			TextField verifycodeField = new TextField(15);
			verifycodeField.setFont(new Font("Serif", Font.BOLD, 23));
			verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			verifycodePanel.setBorder(new MatteBorder(20, 40, 20, 40, new Color(245, 222, 179)));
			verifycodePanel.add(verifycode);
			verifycodePanel.add(verifycodeField);
			verifycode.setBackground(new Color(245, 222, 179));
			signup.add(verifycodePanel);

			// set 'cancel' and 'sign up and login' button
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(1, 2));
			buttons.setBackground(new Color(245, 222, 179));
			buttons.add(cancel);
//			buttons.add(new Panel());
			buttons.add(signuplogin);
			signup.add(buttons);

		}

		private void initSubMenu() {
			// initCredit();
			subMenu.setLayout(new GridLayout(2, 1, 0, 0));
			subMenu.setOpaque(true);
			subMenu.setBackground(new Color(245, 222, 179));
			subMenu.add(signinText);
			subMenu.add(signupText);
			subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		}

		private void initLayerPane() {
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

			this.background.setIcon(new ImageIcon("images/Menu/menuBackground.png"));
			this.background.setBounds(0, 0, frameWidth, frameHeight);
			layeredPane.add(background, new Integer(0));

			this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2),
					titleWidth, titleHeight);
			layeredPane.add(title, new Integer(1));

			this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
					subMenuWidth, subMenuHeight);
			layeredPane.add(subMenu, new Integer(2));

			this.signin.setBounds(signinSetCenter.width - (signinSetWidth / 2),
					signinSetCenter.height - (signinSetHeight / 2), signinSetWidth, signinSetHeight);

			this.signup.setBounds(signupSetCenter.width - (signupSetWidth / 2),
					signupSetCenter.height - (signupSetHeight / 2), signupSetWidth, signupSetHeight);

			this.add(layeredPane);
		}

		public Menu() {
			initPanel();
			initFonts();
			initTitle();
			initSubMenu();
			initSignIn();
			initSignUp();
			initLayerPane();

			signinText.addMouseListener(ml);
			signupText.addMouseListener(ml);
			creditButt.addMouseListener(ml);
			back.addMouseListener(ml);
			login.addMouseListener(ml);
			cancel.addMouseListener(ml);
			signuplogin.addMouseListener(ml);

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
				if (e.getSource() == signupText) { // TODO optional
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
					System.exit(0);
				}

				if (e.getSource() == signinText) {
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
					System.exit(0);
				}
			}
		};

	}

	public static void main(String[] args) {
		HotelPreference program = new HotelPreference();
	}
}
