package GUI;

import core.*;
import databaseException.noSuchHotel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class GUI_hotelpage {
	// test data
	LocalDate d1 = LocalDate.of(2019, 12, 13);
	LocalDate d2 = LocalDate.of(2019, 12, 17);
	private RoomNum rmnm = new RoomNum(3, 3, 3);
	private CheckInOutDate ck = new CheckInOutDate(d1, d2);
	private Hotel htl = new Hotel(2, 3, "road 1", new Room(1, 1000), new Room(2, 200), new Room(4, 30));
	private Plan test_plan = new Plan(rmnm, ck, htl);

	private static JFrame frame;

	public static JFrame getFrame() {
		return frame;
	}

	private JTable table;
	private Plan pln = GUI_search.getChosen_plan();
	private static Order ordr;
	private JTable hotel_info;

	public static Order getOrdr() {
		return ordr;
	}

	public static void setOrdr(Order ordr) {
		GUI_hotelpage.ordr = ordr;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @throws noSuchHotel
	 */
	public GUI_hotelpage() throws noSuchHotel, SQLException {
		if (GUI_record.isFrom_record()) {
			pln = GUI_record.getChosen_plan();
			GUI_record.setFrom_record(false);
		} else if (GUI_order_manage.isFrom_record()) {
			ordr = GUI_order_manage.getChosen_order();
			pln = ordr.getPlan();
			GUI_order_manage.setFrom_record(false);
		}

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 * @throws noSuchHotel
	 */
	private void initialize() throws noSuchHotel, SQLException {
		frame = new JFrame();
		try {
			final BufferedImage backgroundImage = ImageIO.read(new File("images/8.png"));
			frame.setContentPane(new JPanel(new BorderLayout()) {
				@Override
				public void paintComponent(Graphics g) {
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setForeground(Color.WHITE);
		roomnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		roomnum.setBounds(577, 15, 62, 27);
		frame.getContentPane().add(roomnum);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBackground(Color.BLACK);
		spinner_1.setValue(pln.getRoomNum().getSingleNum());
		spinner_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_1.setBounds(634, 12, 85, 33);
		frame.getContentPane().add(spinner_1);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBackground(Color.BLACK);
		spinner_4.setValue(pln.getRoomNum().getQuadNum());
		spinner_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_4.setBounds(941, 10, 85, 33);
		frame.getContentPane().add(spinner_4);

		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_7.setBounds(731, 16, 63, 27);
		frame.getContentPane().add(label_7);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBackground(Color.BLACK);
		spinner_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_2.setValue(pln.getRoomNum().getDoubleNum());
		spinner_2.setBounds(782, 12, 85, 33);
		frame.getContentPane().add(spinner_2);

		JLabel label_9 = new JLabel("\u56DB\u4EBA\u623F");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_9.setBounds(879, 16, 54, 27);
		frame.getContentPane().add(label_9);

		JLabel hyperlink = new JLabel("veiw on google map");
		hyperlink.setForeground(Color.WHITE);
		hyperlink.setFont(new Font("SansSerif", Font.PLAIN, 16));
		hyperlink.setBounds(700, 230, 150, 27);
		hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(hyperlink);
		hyperlink.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(pln.getHotel().googlemapURL()));

				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

		});

		String[] head = { "評論" };
		String[] place = pln.getHotel().loadcomments();
		String[][] ph = { {} };
		if (place != null) {
			ph = new String[place.length][1];
			for (int i = 0; i < place.length; i++) {
				ph[i][0] = place[i];
			}
		} else {
			ph = new String[1][1];
			ph[0][0] = "no comment";
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 280, 835, 340);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);

		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.setBackground(Color.BLACK);
		confirm.setForeground(Color.WHITE);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomNum rn = new RoomNum((Integer) spinner_1.getValue(), (Integer) spinner_2.getValue(),
						(Integer) spinner_4.getValue());
				pln.setRoomNum(rn);
				try {
					ordr = pln.toOrder();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GUI_order_confirm fre = new GUI_order_confirm();
				frame.dispose();
				if(GUI_search.getFrame().isActive())
					GUI_search.getFrame().dispose();
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(896, 613, 130, 29);
		frame.getContentPane().add(confirm);

		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.setBackground(Color.BLACK);
		button_user.setForeground(Color.WHITE);
		button_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_user fre = new GUI_user();
				frame.dispose();
			}
		});
		button_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button_user.setBounds(896, 572, 130, 29);
		frame.getContentPane().add(button_user);

		JLabel label = new JLabel("\u7E3D\u50F9:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(577, 92, 62, 27);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel(((Long) pln.calTotalPrice()).toString());
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(623, 92, 144, 27);
		frame.getContentPane().add(lblNewLabel);
		Plan pclone = pln;

		JTextArea cmtarea = new JTextArea();
		cmtarea.setForeground(Color.WHITE);
		cmtarea.setBackground(Color.BLACK);
		cmtarea.setText("\u8A55\u8AD6...");
		cmtarea.setBounds(10, 140, 520, 120);
		cmtarea.setBorder(new LineBorder(Color.WHITE, 3));
		frame.getContentPane().add(cmtarea);

		JButton comment = new JButton("\u8A55\u8AD6");
		comment.setBackground(Color.BLACK);
		comment.setForeground(Color.WHITE);
		comment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					pln.getHotel().addcomments(cmtarea.getText());
					String[] place = pln.getHotel().loadcomments();
					String[][] ph = { {} };
					if (place != null) {
						ph = new String[place.length][1];
						for (int i = 0; i < place.length; i++) {
							ph[i][0] = place[i];
						}
					} else {
						ph = new String[1][1];
						ph[0][0] = "no comment";
					}
					table.setModel(new DefaultTableModel(ph, head));
				} catch (UserException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (noSuchHotel e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		comment.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comment.setBounds(550, 230, 130, 29);
		frame.getContentPane().add(comment, BorderLayout.SOUTH);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 15, 520, 110);
		frame.getContentPane().add(scrollPane_2);

		String[][] inf = { { "飯店號", ((Integer) pln.getHotel().getId()).toString() },
				{ "星級", ((Integer) pln.getHotel().getStar()).toString() }, { "地址", pln.getHotel().getAddress() },
				{ "原房數", pln.getRoomNum().toString() }, { "原總價", ((Long) pln.calTotalPrice()).toString() } };
		String[] infhead = { "飯店資訊", "" };
		hotel_info = new JTable(new DefaultTableModel(inf, infhead));
		hotel_info.setRowSelectionAllowed(false);
		hotel_info.setForeground(Color.WHITE);
		hotel_info.setBackground(Color.BLACK);
		scrollPane_2.setViewportView(hotel_info);
		hotel_info.setPreferredScrollableViewportSize(new Dimension(63, 63));


		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
