package utils;

public class singleNote {
	private static singleNote instance;

	private String name;
	private int age;
	private int dob;

	private singleNote() {

	}

	public static singleNote getInstance() {
		if (instance == null) {
			instance = new singleNote();
		}
		return instance;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

}
