package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import core.User;
import core.UserException;
import databaseException.userExist;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Graphics;

public class GUI_login {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	public User usr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_login window = new GUI_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public GUI_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		try {
		    final BufferedImage backgroundImage = ImageIO.read(new File("images/8.png"));
		    frame.setContentPane(new JPanel(new BorderLayout()) {
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    });
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
		
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					User.login(username.getText(), password.getText());
					GUI_user fre = new GUI_user();
					frame.dispose();
				} catch (UserException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(485, 279, 110, 29);
		confirm.setBackground(Color.BLACK);
        confirm.setForeground(Color.WHITE);
		frame.getContentPane().add(confirm);

		JLabel label = new JLabel("\u7528\u6236\u540D");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("新細明體", Font.PLAIN, 16));
		label.setBounds(365, 165, 78, 20);
		frame.getContentPane().add(label);

		username = new JTextField();
		username.setFont(new Font("新細明體", Font.PLAIN, 16));
		username.setBounds(432, 155, 249, 33);
		frame.getContentPane().add(username);
		username.setColumns(10);

		JLabel label_1 = new JLabel("\u5BC6\u78BC");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		label_1.setBounds(365, 220, 78, 20);
		frame.getContentPane().add(label_1);

		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(432, 214, 249, 33);
		frame.getContentPane().add(password);

		JButton register = new JButton("\u8A3B\u518A");
		register.setBackground(Color.BLACK);
		register.setForeground(Color.WHITE);
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					try {
						boolean register = User.signup(username.getText(), password.getText());
					} catch (userExist e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		register.setFont(new Font("SansSerif", Font.PLAIN, 16));
		register.setBounds(365, 279, 110, 29);
		frame.getContentPane().add(register);

		JButton guest = new JButton("\u8A2A\u5BA2\u767B\u5165");
		guest.setForeground(Color.WHITE);
		guest.setBackground(Color.BLACK);
		guest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_search fre = new GUI_search();
				frame.dispose();
			}
		});
		guest.setFont(new Font("SansSerif", Font.PLAIN, 16));
		guest.setBounds(605, 279, 110, 29);
		frame.getContentPane().add(guest);

	}

}
