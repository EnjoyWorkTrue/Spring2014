import java.util.Comparator;

public class SortByName implements Comparator<Emp> {
	@Override
	public int compare(Emp e1, Emp e2) {
		return ((String) e1.name).compareTo(e2.name);
	}
}
