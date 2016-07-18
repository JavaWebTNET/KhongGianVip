package model;

public class Service {

	private int id_sv;
	private String title_sv;
	private String content_sv;
	
	public Service(){}

	public int getId_sv() {
		return id_sv;
	}

	public void setId_sv(int id_sv) {
		this.id_sv = id_sv;
	}

	public String getTitle_sv() {
		return title_sv;
	}

	public void setTitle_sv(String title_sv) {
		this.title_sv = title_sv;
	}

	public String getContent_sv() {
		return content_sv;
	}

	public void setContent_sv(String content_sv) {
		this.content_sv = content_sv;
	}
	
	public String subDetail() {
		String plainDetail =content_sv.replaceAll("\\<.*?\\>", "");
		String[] arrDetail = plainDetail.split(" ");
		String subDetail = "";
		int i = 1;
		for(String word: arrDetail) {
			if(i>40) break;
			subDetail = subDetail + " " + word;
			i++;
		}
		return subDetail;
	}
}
