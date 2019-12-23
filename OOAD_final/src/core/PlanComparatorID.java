package core;

import java.util.Comparator;

public class PlanComparatorID implements Comparator<Plan>{

	@Override
	public int compare(Plan o1, Plan o2) {
		// TODO Auto-generated method stub
		return (o1.getHotel().getId() -o2.getHotel().getId());
	}

}
