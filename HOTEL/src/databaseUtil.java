import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class databaseUtil {
	// MySQL
	static Connection connect = null;
	static Statement stmt = null;
	static ResultSet rs = null;
    	
	public static void buildConnection() {
		// build connection to MySQL
    	try {
    		System.out.print("Connecting to MySQL...");
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		connect = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=123456798Abcd");
    		stmt = connect.createStatement();
    		
    		System.out.println("finish!");
    	} catch (Exception e) {
    		e.getStackTrace();
    	} 
	}
	public static void initDatabase() {
		// build database
    	BufferedReader fin = null;
    	try {
			System.out.print("Building database...");

			fin = new BufferedReader(new FileReader("hotel.sql"));		
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
	public static String readJSONFile(String filename) {
	    String result = null;
	    try {
	    	File jsonfile = new File(filename);
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(jsonfile), "big5"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        result = sb.toString();
	        br.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public static void buildTable() {
    	// parse json
    	// JSON parser object to parse jsonStr
    	JSONParser jsonParser = new JSONParser();
        String jsonStr = readJSONFile("HotelList.json");
        JSONArray hotelList = null;
        try {
        	System.out.print("Parsing JSON...");
        	
            hotelList = (JSONArray)jsonParser.parse(jsonStr);
            
            System.out.println("finish!");
    	} catch (Exception e) {
        	e.printStackTrace();
        }
       	
        // build RoomList table
    	try {
	       	System.out.print("Building RoomList table...");
	       	
	       	for (Object h : hotelList) {
				JSONObject hotel = (JSONObject)h;
				JSONArray roomList = (JSONArray)hotel.get("Rooms");
				for (Object r : roomList) {
					JSONObject room = (JSONObject)r;
					if (room.get("Number").toString() == "0")
						continue;
					String cmd = "INSERT INTO RoomList" + 
								 "(HotelID, HotelStar, StreetAddress, Locality, RoomType, RoomPrice, Number)" + 
								 "VALUES" + 
								 "(" + 
								 	hotel.get("HotelID").toString() + ", " + 
								 	hotel.get("HotelStar").toString() + " , " + 
								 	"\"" + hotel.get("Street-Address").toString() + "\"" + ", " + 
								    "\"" + hotel.get("Locality").toString() + "\"" + ", " + 
								 	"\"" + room.get("RoomType").toString() + "\"" + ", " + 
								 	room.get("RoomPrice").toString() + ", " + 
								 	room.get("Number").toString() +
								 ");";
					try {
						stmt.execute(cmd);
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			}
	       	
			System.out.println("finish!");
    	} catch (Exception e) {
    		e.getStackTrace();
    		System.exit(4);
    	}
    }
	
	public static void buildDatabase() {
    	buildConnection();	
    	initDatabase();
    	buildTable();
    }
    
    public static void main(String[] args) { 
    	buildDatabase();
    	try {
			if (connect != null)
				connect.close();
      	} catch (Exception e) {
        	 e.printStackTrace();
      	}

    }
}
