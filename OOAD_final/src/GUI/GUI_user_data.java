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
import javax.swing.JPasswordField;
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

public class GUI_user_data {

	private JFrame frame;
	private JTextField email;
	private JTextField password;
	public User usr;
	private JTextField passwordnew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_user_data window = new GUI_user_data();
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
	public GUI_user_data() {
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
					if(!email.getText().isEmpty())
						User.getUser().editEmail(email.getText());
					User.getUser().editpassword(password.getText(), passwordnew.getText());
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
		confirm.setBounds(534, 338, 146, 29);
		confirm.setBackground(Color.BLACK);
        confirm.setForeground(Color.WHITE);
		frame.getContentPane().add(confirm);

		JLabel lblEmailoptional = new JLabel("Email (Optional)");
		lblEmailoptional.setForeground(Color.WHITE);
		lblEmailoptional.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblEmailoptional.setBounds(304, 161, 118, 20);
		frame.getContentPane().add(lblEmailoptional, BorderLayout.NORTH);

		email = new JTextField();
		email.setFont(new Font("新細明體", Font.PLAIN, 16));
		email.setBounds(431, 155, 249, 33);
		frame.getContentPane().add(email);
		email.setColumns(10);

		JLabel label_1 = new JLabel("\u820A\u5BC6\u78BC");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		label_1.setBounds(364, 220, 78, 20);
		frame.getContentPane().add(label_1);

		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(431, 214, 249, 33);
		frame.getContentPane().add(password);
		
		passwordnew = new JTextField();
		passwordnew.setFont(new Font("新細明體", Font.PLAIN, 16));
		passwordnew.setColumns(10);
		passwordnew.setBounds(431, 275, 249, 33);
		frame.getContentPane().add(passwordnew);
		
		JLabel label = new JLabel("\u65B0\u5BC6\u78BC");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("新細明體", Font.PLAIN, 16));
		label.setBounds(364, 281, 78, 20);
		frame.getContentPane().add(label);
		
		JButton cancel = new JButton("\u53D6\u6D88");
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
					GUI_user fre = new GUI_user();
					frame.dispose();			
			}
		});
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cancel.setBackground(Color.BLACK);
		cancel.setBounds(378, 338, 146, 29);
		frame.getContentPane().add(cancel);

	}
}
