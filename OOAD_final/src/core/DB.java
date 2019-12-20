package core;

import java.sql.SQLException;

import mumi.mumiLite;

public class DB {
	private static mumiLite db;
	static {
		try {
			db=new mumiLite();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static mumiLite getDB() {
		return db;
	}
}
