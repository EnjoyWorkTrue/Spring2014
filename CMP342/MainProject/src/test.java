import java.util.ArrayList;


public class test {

	public static void main(String[] args) {
		int[] a = {1,2,3,4};
		for(int x:a){
			System.out.println(x);
		}
		System.out.println("hello");
		
		ArrayList<String> b = new ArrayList<String>();
		b.add("hello");
		b.add("hello1");
		
		for(String x:b){
			System.out.println(x);
		}
		//aSystem.out.println("??");
	}

}
