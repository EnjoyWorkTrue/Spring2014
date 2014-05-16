package Enc_Dec;

public class Decryption {
	private String key;
	private String encV;
	private static String charList = "abcdefghijklmnopqrstuvwxyz ";
	public Decryption(String key,String encV){
		this.key = key;
		this.encV = encV;
	}
	public boolean isDecryptable(){
		return key.length() == encV.length();
	}
	public String getDecrypted(){
		String value = "";
		for(int i =0; i<key.length();i++){
			int num = charList.indexOf(encV.charAt(i))
					- charList.indexOf(key.charAt(i));
			if(num<0){
				num = 27+num;
			}
			value = value + charList.charAt(num);
		}
		
		
		return value;
	}

}
