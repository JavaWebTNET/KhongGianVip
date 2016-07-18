package model;

import javax.servlet.http.HttpServletRequest;

import dao.ImageDao;

public class Dish {

	private int id_dish;
	private String image_dish;
	private String price;
	private String name_dish;
	private String content_dish;
	private int tasty;
	public int getTasty() {
		return tasty;
	}

	public void setTasty(int tasty) {
		this.tasty = tasty;
	}

	public String getContent_dish() {
		return content_dish;
	}

	public void setContent_dish(String content_dish) {
		this.content_dish = content_dish;
	}

	private String status_dish;
	public static String uploadDir = "dish";
	
	public Dish(){}

	public int getId_dish() {
		return id_dish;
	}

	public void setId_dish(int id_dish) {
		this.id_dish = id_dish;
	}

	public String getImage_dish() {
		return image_dish;
	}

	public void setImage_dish(String image_dish) {
		this.image_dish = image_dish;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName_dish() {
		return name_dish;
	}

	public void setName_dish(String name_dish) {
		this.name_dish = name_dish;
	}

	

	public String getStatus_dish() {
		return status_dish;
	}

	public void setStatus_dish(String status_dish) {
		this.status_dish = status_dish;
	}
	public String subContent() {
		String subDetail = "";
		if(content_dish!=null){
		String plainDetail =content_dish.replaceAll("\\<.*?\\>", "");
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
	
	
	public String imageLink(HttpServletRequest request) {
		return ImageDao.imageLink(request, Dish.uploadDir, this.getImage_dish());
	}
	
}
