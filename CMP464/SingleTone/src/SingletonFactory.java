
public class SingletonFactory {
	private static SingletonFactory instance;
	private SingletonFactory() {
	}
	
	public static SingletonFactory getInstance(){
		if (instance == null)
			instance = new SingletonFactory();
		return instance;
	}
}
