
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Calendar;

import org.json.simple.*;
import org.json.simple.parser.*;

/*
 * Remember to call functions 'buildConnection()' and 'initDatabase()' 
 * 										at the beginning of your program.
 */
public class databaseUtil {
	// MySQL
	static Connection connect = null;
	static Statement stmt = null;
	static ResultSet results = null;

	// build connection to MySQL database(user:root, password:root)
	public static void buildConnection() {
		// build connection to MySQL
		try {
			System.out.print("Connecting to MySQL...");

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
			stmt = connect.createStatement();

			System.out.println("finish!");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// read SQL Script to build tables
	public static void initDatabase() {
		// build database
		BufferedReader fin = null;
		try {
			System.out.print("Building database...");

			fin = new BufferedReader(new FileReader("buildTable.sql"));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = fin.readLine()) != null) {
				sb.append(line + "\n");
			}
			fin.close();

			String[] cmds = sb.toString().split(";");
			for (int i = 0; i < cmds.length; i++) {
				if (!cmds[i].trim().equals("")) {
					stmt.execute(cmds[i]);
				}
			}

			System.out.println("finish!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert a user to table 'Users' by given User object
	public static boolean insertUser(User newUser) {
		String cmd = "INSERT INTO Users"
						+ "(UID, password)" 
						+ "VALUES"
						+ "(" + newUser.getUserID() + ", " + newUser.getPassword() + ")";
		
		try {
			stmt.execute(cmd);
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	// get the certain User by given UserID
	public static User getUser(String UID) {
		String cmd = "SELECT * FROM Users WHERE UID=" + UID;
		try {
			results = stmt.executeQuery(cmd);
			if (results.next()) {
				return new User(results.getString("UID"), results.getString("password"));
			} else {
				System.out.println("No such User!!");
				return null;//return new User();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new User();
	}
	
	// change the format from yyyy/mm/dd to yyyy-mm-dd
	private static String DateFormat(String origin) {
		return origin.replace('/', '-');
	}
	// insert a Order to table 'Orders' by given Order object
	public static boolean insertOrder(Order newOrder) {
		String cmd = "INSERT INTO Orders"
<<<<<<< HEAD
						+ "(OrderID, UID, HotelID, SingleRoom, DoubleRoom, QuadRoom, CheckIn, CheckOut)" 
						+ "VALUES("
						+ newOrder.getID() + ", " 
						+ "\"" + newOrder.getUserID() + "\"" + ", "
						+ newOrder.getHotelID() + ", "
						+ newOrder.getSnum().size() + ", "
						+ newOrder.getDnum().size() + ", "
						+ newOrder.getQnum().size() + ", "
						+ "\'" + DateFormat(newOrder.getCheckInDate()) + "\'" + ", "
						+ "\'" + DateFormat(newOrder.getCheckOutDate()) + "\'" + ");";
=======
					+ "(OrderID, UID, HotelID, SingleRoom, DoubleRoom, QuadRoom, CheckIn, CheckOut)" 
					+ "VALUES("
					+ newOrder.getID() + ", " 
					+ "\"" + newOrder.getUserID() + "\"" + ", "
					+ newOrder.getHotelID() + ", "
					+ newOrder.getsn() + ", "
					+ newOrder.getdn() + ", "
					+ newOrder.getqn() + ", "
					+ "\'" + DateFormat(newOrder.getCheckInDate()) + "\'" + ", "
					+ "\'" + DateFormat(newOrder.getCheckOutDate()) + "\'" + ");";
>>>>>>> 36a4136b4c5f01fbb813b3e8b33a0ed9b1f63dc2
		try {
			if (getOrderByOrderID(newOrder.getID()).getID() != -1) {
				stmt.execute("DELETE FROM Orders WHERE OrderID=" + newOrder.getID());
			}
			stmt.execute(cmd);
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	// get the certain OrderID by given OrderID
	public static Order getOrderByOrderID(int OrderID) {
		String cmd = "SELECT * FROM Orders WHERE OrderID=" + OrderID;
		try {
			results = stmt.executeQuery(cmd);
			
			if (results.next()) {
				return new Order(results.getInt("OrderID"), 
								 results.getString("UID"), 
								 results.getInt("HotelID"), 
								 results.getDate("CheckIn").toString().replace('-', '/'),
								 results.getDate("CheckOut").toString().replace('-', '/'),
								 results.getInt("SingleRoom"), 
								 results.getInt("DoubleRoom"), 
								 results.getInt("QuadRoom"));
			} else {
				System.out.println("No such Order!!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// get the certain OrderID by given UserID
	public static Order[] getOrderByUserID(String UID) {
		String cmd = "SELECT * FROM Orders WHERE UID=" + UID;
		System.out.println(cmd);

		try {
			int len;
			results = stmt.executeQuery(cmd);
			results.last();
			len = results.getRow();
			results.first();
			
			if (len == 0) {
				System.out.println("No such Order!!");
				return null;
			}
			
			Order[] retList = new Order[len];
			int index = 0;
			do {
				retList[index++] = new Order(results.getInt("OrderID"), 
										 results.getString("UID"), 
										 results.getInt("HotelID"), 
										 results.getDate("CheckIn").toString().replace('-', '/'),
										 results.getDate("CheckOut").toString().replace('-', '/'),
										 results.getInt("SingleRoom"), 
										 results.getInt("DoubleRoom"), 
										 results.getInt("QuadRoom"));
			} while(results.next());

			return retList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// get the certain OrderID by given HotelID
	public static Order[] getOrderByHotelID(int HotelID) {
		String cmd = "SELECT * FROM Orders WHERE HotelID=" + HotelID;
		try {
			int len;
			results = stmt.executeQuery(cmd);
			results.last();
			len = results.getRow();
			results.first();
			
			if (len == 0) {
				System.out.println("No such Order!!");
				return null;
			}
			
			Order[] retList = new Order[len];
			int index = 0;
			do {
				retList[index++] = new Order(results.getInt("OrderID"), 
										 results.getString("UID"), 
										 results.getInt("HotelID"), 
										 results.getDate("CheckIn").toString().replace('-', '/'),
										 results.getDate("CheckOut").toString().replace('-', '/'),
										 results.getInt("SingleRoom"), 
										 results.getInt("DoubleRoom"), 
										 results.getInt("QuadRoom"));
			} while(results.next());
			return retList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
		
	public static void main(String[] args) {
		buildConnection();
		initDatabase();
		
		try {
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
