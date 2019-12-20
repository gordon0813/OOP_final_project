package databaseException;

public class noSuchUser extends userException {
	public noSuchUser(String id) {
		super("no such user : " + id);
	}
}
