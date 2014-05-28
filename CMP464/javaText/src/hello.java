
public class hello {
	
	private static class W{
		W() {
			System.out.println("h");
		}
		public W(String a){
			System.out.println(a);
		}

		public void W() {
			System.out.println("method clladed W");
			
		}
	}
	public  hello(String what){
		System.out.println("this is hello");
		W a = new W(what);
		a.W();
		
	}
	public static void f1(){
		System.out.println("f1");
	}
	public static void f1(String a){
		
	}

}
