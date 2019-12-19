package databaseException;

public class noSuchHotel extends hotelException {

	public noSuchHotel(int id) {
		super("no such hotel id = " + id);
	}

}
