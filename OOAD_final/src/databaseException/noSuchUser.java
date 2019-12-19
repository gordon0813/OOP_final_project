package databaseException;

public class noSuchUser extends userException {
	public noSuchUser(int id) {
		super("no such user id = " + id);
	}
}
