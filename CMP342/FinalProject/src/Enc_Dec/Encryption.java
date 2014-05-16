package Enc_Dec;

public class Encryption {
	private String text;
	private String key;
	private static String Charlist = "abcdefghijklmnopqrstuvwxyz ";

	public Encryption(String text) {
		this.text = text.toLowerCase();
	}

	public boolean isEncryptable() {
		for (int i = 0; i < text.length(); i++) {

			String test = "" + text.charAt(i);
			if (!Charlist.contains(test)) {
				return false;
			}
		}
		return true;
	}
	public String getEncryptionKey(){
		String key="";
		for(int i=0;i < text.length(); i++){
			key = key + Charlist.charAt((int)(Math.random()*27));
		}
		this.key = key;
		return key;
	}
	public String getEncrypted(){
		String enc="";
		for(int i=0;i<text.length();i++){
			int num = Charlist.indexOf(text.charAt(i))
					+ Charlist.indexOf(key.charAt(i));
			num = num%27;
			enc = enc + Charlist.charAt(num);
		}
		return enc;
	}

}
