package practiceSingleTone;

import java.util.ArrayList;
import java.util.Set;

public class ARRRRY {

	public static void main(String[] args) {
		ArrayList<String> s = new ArrayList<String>();
		s.add("hi");
		s.add("joe");
		s.add("aaa");
		s.add("bbb");
		String delete = null;
		for(String a : s){
			System.out.println(a);
			if(a.equals("hi")){
				delete = a;
			}
		}
		s.remove(delete);
		System.out.println("___");
		for(String a:s){
			System.out.println(a);
		}
		
	
	}
}
