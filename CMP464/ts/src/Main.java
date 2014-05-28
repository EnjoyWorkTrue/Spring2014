import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Emp emp1 = new Emp("a2", 2000, 12, 5);
		Emp emp2 = new Emp("a1", 2000, 12, 5);
		Emp emp3 = new Emp("a2", 1992, 7, 3);
		Emp emp4 = new Emp("a3", 1983, 7, 5);
		Emp emp5 = new Emp("a4", 1907, 10, 5);
		Emp emp6 = new Emp("a5", 1922, 12, 22);

		ArrayList<Emp> empList = new ArrayList<Emp>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp4);
		empList.add(emp5);
		empList.add(emp6);
		
		if (!empList.contains(emp1))
			empList.add(emp1);
		
		System.err.println(emp2.equals(emp1));
		System.out.println(empList);
		
		Set<Emp> empSet = new TreeSet(new SortByName());
		empSet.add(emp1);
		empSet.add(emp2);
		empSet.add(emp3);
		empSet.add(emp4);
		empSet.add(emp5);
		empSet.add(emp6);
		
		for (Emp emp : empSet)
			System.out.println(emp);
	}
}
