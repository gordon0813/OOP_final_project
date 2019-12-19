package mumi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.Hotel;
import core.Room;

public class readJSON {
	public static ArrayList<Hotel> Loader() throws IOException, ParseException {
		Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("database/HotelList.json"), "BIG5"));
		int ch = 0;
		StringBuffer sb = new StringBuffer();
		while ((ch = reader.read()) != -1) {
			sb.append((char) ch);
		}

		reader.close();
		String jsonString = sb.toString();
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONArray array = new JSONArray();
		array.add(obj);
		
		// abstract each element from each hotel and record them in an Arraylist, which contains all hotel
		for (int i = 0; i < ((ArrayList) array.get(0)).size(); i++) {
			JSONObject jo = (JSONObject) ((ArrayList) array.get(0)).get(i);
			JSONArray a = (JSONArray) jo.get("Rooms");
			JSONObject m = (JSONObject) a.get(0);
			Room s = new Room((int) (long) (Long) m.get("Number"),
							(int) (long) (Long) m.get("RoomPrice"));
			JSONObject m1 = (JSONObject) a.get(1);
			Room d = new Room((int) (long) (Long) m1.get("Number"),
					(int) (long) (Long) m1.get("RoomPrice"));
			JSONObject m2 = (JSONObject) a.get(2);
			Room q = new Room((int) (long) (Long) m2.get("Number"),
					(int) (long) (Long) m2.get("RoomPrice"));

			Hotel newhotel = new Hotel((int) (long) (Long) jo.get("HotelID"), (int) (long) (Long) jo.get("HotelStar"),
					(String) jo.get("Locality") + (String) jo.get("Street-Address"), s, d, q);
			hotelList.add(newhotel);
				}
		return hotelList;
	}
}
