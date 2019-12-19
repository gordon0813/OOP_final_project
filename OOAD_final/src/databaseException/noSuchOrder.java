package databaseException;

public class noSuchOrder extends orderException {
	public noSuchOrder (long id) {
		super ("can not find such order id = " + id);
	}
}
