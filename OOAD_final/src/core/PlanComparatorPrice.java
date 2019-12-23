package core;

import java.util.Comparator;

public class PlanComparatorPrice implements Comparator<Plan>{

	@Override
	public int compare(Plan o1, Plan o2) {
		// TODO Auto-generated method stub
		return (int) (o1.calTotalPrice() -o2.calTotalPrice());
	}

}
