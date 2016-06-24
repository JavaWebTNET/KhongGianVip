package dao;

public class CheckNumberString {
	
	public static boolean CheckNumber(String chuoi){
		//chuoi=chuoi.replace(" ",""); nếu muốn loại bort khoảng trắng trc khi kiêmtra
		 for(int i=0;i<chuoi.length();i++){
			
			 if(!Character.isDigit( chuoi.charAt(i))){
				 return false;
			 }
		 }
		return true;
	}
	
	/*public static void main(String[] a){
		System.out.println("kiem tra "+CheckNumber("1 "));
	}*/
	
}
