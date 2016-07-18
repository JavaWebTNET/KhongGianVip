package model;

public class Category {
	private int id_dm;
	private String title ;
	private int sp_id;
	private String content_dm;
	
	public Category(){}

	public int getId_dm() {
		return id_dm;
	}

	public void setId_dm(int id_dm) {
		this.id_dm = id_dm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public String getContent_dm() {
		return content_dm;
	}

	public void setContent_dm(String content_dm) {
		this.content_dm = content_dm;
	}
	
	public String content_Shortened() {
		String subDetail = "";
		if(content_dm!=null){
		String plainDetail =content_dm.replaceAll("\\<.*?\\>", "");
		String[] arrDetail = plainDetail.split(" ");		
		int i = 1;
		for(String word: arrDetail) {
			if(i>20) break;
			subDetail = subDetail + " " + word;
			i++;
		}
		}
		return subDetail;
	}
	
	
	
	

	
	
}
