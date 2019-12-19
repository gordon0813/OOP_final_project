package databaseException;

public class exceedSchedule extends scheduleException {
	public exceedSchedule (String s,String e) {
		super("available day is between " + s + " and " + e);
	}
}
