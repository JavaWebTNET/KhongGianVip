package model;

import javax.servlet.http.HttpServletRequest;

import dao.ImageDao;

public class Information {

	private int id;
	private String name_res;
	private String add_res;
	private String tell;
	private String email;
	private String logo;
	private String gioithieu;
	private String tuyendung;
	private String lienhe;
	
	public Information() {
	}

	
	public String getLienhe() {
		return lienhe;
	}

	public void setLienhe(String lienhe) {
		this.lienhe = lienhe;
	}

	public String getTuyendung() {
		return tuyendung;
	}

	public void setTuyendung(String tuyendung) {
		this.tuyendung = tuyendung;
	}

	public String getGioithieu() {
		return gioithieu;
	}

	public void setGioithieu(String gioithieu) {
		this.gioithieu = gioithieu;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	
	public static String uploadDir = "information";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_res() {
		return name_res;
	}

	public void setName_res(String name_res) {
		this.name_res = name_res;
	}

	public String getAdd_res() {
		return add_res;
	}

	public void setAdd_res(String add_res) {
		this.add_res = add_res;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static String getUploadDir() {
		return uploadDir;
	}

	public static void setUploadDir(String uploadDir) {
		Information.uploadDir = uploadDir;
	}

	public String imageLink(HttpServletRequest request) {
		return ImageDao.imageLink(request, Information.uploadDir, this.getLogo());
	}

}
