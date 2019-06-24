
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/**
 * <h1>This is the class of the main class for running a Hotel Booking Webpage.<\h1>
 * 
 * @author momo, tim, catherine, sopia
 * @version 1.0
 * @since 2019-05-31
 */

public class databaseUtil {
	// MySQL
	static Connection connect = null;
	static Statement stmt = null;
	static ResultSet results = null;

	/**
	 * build connection to MySQL database(user:root, password:root)
	 * 
	 */
	public static void buildConnection() {
		// build connection to MySQL
		try {
			System.out.print("Connecting to MySQL...");

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
			stmt = connect.createStatement();
			stmt.execute("USE `monopoly`;");
			System.out.println("finish!");
		} catch (SQLException e) {
			System.out.println("Connect failed!");
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * read SQL Script to build tables
	 * 
	 */
	public static void initDatabase() {
		// build database
		BufferedReader fin = null;
		try {
			System.out.print("Building database...");

			fin = new BufferedReader(new FileReader("MONO.sql"));
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

	/**
	 * insert a user to table 'Users' by given User object
	 * 
	 * @param newUser new User to be insert
	 * @return True if insert successfully, False instead.
	 */
	public static boolean insertUser(User newUser) {
		try {
			User tmp = null; 
			if ((tmp = getUser(newUser.getName())) != null) {
				long newAssets = newUser.getAssets() + tmp.getAssets();
				newUser.setAssets(newAssets);
				stmt.execute("DELETE FROM totalAssets WHERE UserName=\'" + newUser.getName() + "\';");
			}
			String cmd = "INSERT INTO totalAssets"
					+ "(UserName, Assets)" 
					+ "VALUES"
					+ "(\'" + newUser.getName() + "\', " + newUser.getAssets() + ")";
			stmt.execute(cmd);
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * get the certain User by given UserID
	 * 
	 * @param UserName
	 * @return
	 */
	public static User getUser(String UserName) {
		String cmd = "SELECT * FROM totalAssets WHERE UserName=\'" + UserName + "\';";
		try {
			results = stmt.executeQuery(cmd);
			if (results.next()) {
				return new User(results.getString("UserName"), results.getLong("Assets"));
			} else { 
				System.out.println("No such User!!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * This is the main method to test databaseUtil.java
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		buildConnection();
		initDatabase();
		insertUser(new User("p1", 123));
		insertUser(new User("p1", 123));
		System.out.println(getUser("p1").getAssets());
		try {
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
