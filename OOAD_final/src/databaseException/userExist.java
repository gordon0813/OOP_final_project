package databaseException;

public class userExist extends userException {
	public userExist(String s) {
		super ("User name \"" + s + "\" has been used!! Think another one!!");
	}
}
