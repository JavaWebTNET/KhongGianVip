package model;

public class News {
	private int id;
	private String title;
	private String content;
	private String time_news;
	public News(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime_news() {
		return time_news;
	}
	public void setTime_news(String time_news) {
		this.time_news = time_news;
	}
	
	public String subDetail() {
		String plainDetail =content.replaceAll("\\<.*?\\>", "");
		String[] arrDetail = plainDetail.split(" ");
		String subDetail = "";
		int i = 1;
		for(String word: arrDetail) {
			if(i>20) break;
			subDetail = subDetail + " " + word;
			i++;
		}
		return subDetail;
	}
	
	
}


