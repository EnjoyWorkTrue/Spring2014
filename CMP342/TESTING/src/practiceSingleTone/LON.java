package practiceSingleTone;

public class LON {
	private static LON instance = null;
	public String[] s;
	private LON(){
	}
	public static LON getInstance(){
		if(instance==null)
			instance = new LON();
		return instance;
	}
	public void add(String s){
		this.s = s.split(",");
	}
	public void print() {
		for(int i = 0; i<s.length; i++){
			System.out.println(s[i]);
		}
		
	}

}
