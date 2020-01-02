package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.User;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;

public class GUI_user {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_user() {
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
	
		JButton search = new JButton("\u67E5\u8A62\u98EF\u5E97");
		search.setBackground(Color.BLACK);
		search.setForeground(Color.WHITE);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_search fre = new GUI_search();				
			}
		});
		search.setFont(new Font("SansSerif", Font.PLAIN, 16));
		search.setBounds(165, 10, 130, 29);
		frame.getContentPane().add(search);
		
		JButton user_data = new JButton("\u57FA\u672C\u8CC7\u6599");
		user_data.setBackground(Color.BLACK);
		user_data.setForeground(Color.WHITE);
		//user_data.setIcon(i);
		user_data.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				frame.dispose();
				GUI_user_data fre = new GUI_user_data();			
			}
		});
		user_data.setFont(new Font("SansSerif", Font.PLAIN, 16));
		user_data.setBounds(10, 10, 130, 29);
		frame.getContentPane().add(user_data);
		
		JButton finished_order = new JButton("\u5DF2\u5B8C\u6210\u8A02\u55AE");
		finished_order.setBackground(Color.BLACK);
		finished_order.setForeground(Color.WHITE);
		finished_order.setEnabled(User.getUser().islogin());
		finished_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_order_manage fre = new GUI_order_manage();
				frame.dispose();			
			}
		});
		finished_order.setFont(new Font("SansSerif", Font.PLAIN, 16));
		finished_order.setBounds(330, 10, 130, 29);
		frame.getContentPane().add(finished_order);
		
		JButton search_history = new JButton("\u67E5\u8A62\u7D00\u9304");
		search_history.setBackground(Color.BLACK);
		search_history.setForeground(Color.WHITE);
		search_history.setEnabled(User.getUser().islogin());
		search_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				GUI_record fre = new GUI_record();
				frame.dispose();				
			}
		});
		search_history.setFont(new Font("SansSerif", Font.PLAIN, 16));
		search_history.setBounds(498, 10, 130, 29);
		frame.getContentPane().add(search_history);
		
		JButton logout = new JButton("\u767B\u51FA");
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLACK);
		logout.setEnabled(User.getUser().islogin());
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				User.logout();
				GUI_login fre = new GUI_login();
			}
		});
		logout.setFont(new Font("SansSerif", Font.PLAIN, 16));
		logout.setBounds(924, 10, 130, 29);
		frame.getContentPane().add(logout);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
