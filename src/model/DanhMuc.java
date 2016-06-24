package model;

public class DanhMuc {
	/*create table tb_danhmuc(
			id int(3) auto_increment primary key,
			title varchar(200),
			sp_id int(3),
			content_danhmuc text
			);*/
	private int id_dm;
	private String title ;
	private int sp_id;
	private String content_dm;
	
	public DanhMuc(){}

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
	
	
	
	

	
	
}
