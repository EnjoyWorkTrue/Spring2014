public class MainClass {
	public static void main(String[] args) {
		SingletonFactory singleton = SingletonFactory.getInstance();
		DAO dao = new DAO(10);
		System.out.println(dao + " " + DAO.count);
		DAO dao1 = new DAO(20);
		System.out.println(dao1 + " " + DAO.count);
		DAO dao2 = new DAO(30);
		System.out.println(dao2 + " " + DAO.count);
		System.out.println(dao + " " + DAO.count);
		
	}
}
