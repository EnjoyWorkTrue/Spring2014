import java.util.Comparator;

public class SortByDOB implements Comparator<Emp> {
	@Override
	public int compare(Emp e1, Emp e2) {
		if (((Integer) e1.bornYear).compareTo(e2.bornYear) == 0) {
			if (((Integer) e1.bornMonth).compareTo(e2.bornMonth) == 0) {
				if (((Integer) e1.bornDate).compareTo(e2.bornDate) == 0) {
					return ((String) e1.name).compareTo(e2.name);
				} else
					return ((Integer) e1.bornDate).compareTo(e2.bornDate);
			} else
				return ((Integer) e1.bornMonth).compareTo(e2.bornMonth);
		} else
			return ((Integer) e1.bornYear).compareTo(e2.bornYear);
	}
}
