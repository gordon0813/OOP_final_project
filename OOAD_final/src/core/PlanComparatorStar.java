package core;
import java.util.*;
public class PlanComparatorStar implements Comparator<Plan> {

	@Override
	public int compare(Plan o1, Plan o2) {
		// TODO Auto-generated method stub
		return o1.getHotel().getStar()-o2.getHotel().getStar();
		//return 0;
	}

}
