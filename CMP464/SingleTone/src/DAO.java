public class DAO {
	public static int count;
	private int age;

	public DAO(int age) {
		super();
		this.age = age;
		count++;
	}

	@Override
	public String toString() {
		return "DAO [age=" + age + "]";
	}

}
