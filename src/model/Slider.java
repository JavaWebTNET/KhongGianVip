package model;

import javax.servlet.http.HttpServletRequest;

import dao.ImageDao;

public class Slider {
	private int id;
	private String title;
	private String name_image;
	public static String uploadDir = "slider";
	
	public Slider(){};
	
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

	public String getName_image() {
		return name_image;
	}

	public void setName_image(String name_image) {
		this.name_image = name_image;
	}

	public String imageLink(HttpServletRequest request) {
		return ImageDao.imageLink(request, Slider.uploadDir, this.getName_image());
	}
	
	
	
	
}
