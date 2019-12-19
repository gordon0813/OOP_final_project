package databaseException;

public class noSuchPlan extends planException {

	public noSuchPlan(int s) {
		super("can not find such plan id = " + s);
	}
	
}
