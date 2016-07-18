package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import model.Category;
import utils.ConnectDB;

public class CategoryDAO {
	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;

	public CategoryDAO() {
	}

	/*get all resourse in table tb_category*/
	public Vector<Category> AllCategory() {
		Vector<Category> vtct = new Vector<Category>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_category,title,sp_id,content_category from tb_category where status=1";
			pre = connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				Category cate=new Category();
				cate.setId_dm(rs.getInt("id_category"));
				cate.setTitle(rs.getString("title"));
				cate.setSp_id(rs.getInt("sp_id"));
				cate.setContent_dm(rs.getString("content_category"));
				vtct.add(cate);
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}
	/*get all category have sp_id =0 and its child level one,get we make menu left*/
	public HashMap<Integer,ArrayList<Category>> Category_Two_Level() {
		HashMap<Integer,ArrayList<Category>> hmAllDM=new HashMap<Integer,ArrayList<Category>>();	
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select * from tb_category where (sp_id=0 or sp_id in (select id_category from tb_category where sp_id=0)) && status=1 order by id_category";
			pre = connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				Category cate=new Category();
				cate.setId_dm(rs.getInt("id_category"));
				cate.setTitle(rs.getString("title"));
				cate.setSp_id(rs.getInt("sp_id"));
				cate.setContent_dm(rs.getString("content_category"));
				if(hmAllDM.containsKey(rs.getInt("sp_id"))){
					ArrayList<Category> arrcate=hmAllDM.get(rs.getInt("sp_id"));
					arrcate.add(cate);
					hmAllDM.remove(rs.getInt("sp_id"));
					hmAllDM.put(rs.getInt("sp_id"), arrcate);
				}
				else{
					ArrayList<Category> arrcateexist=new ArrayList<Category>();
					arrcateexist.add(cate);
					hmAllDM.put(rs.getInt("sp_id"), arrcateexist);
				}
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return hmAllDM;
	}
	/*get all resourse in table tb_category*/
	public Vector<Category> Category(int sp_id) {
		Vector<Category> vtct = new Vector<Category>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_category,title,sp_id,content_category from tb_category where sp_id=? and status=1";
			pre = connection.prepareStatement(sql);
			pre.setInt(1,sp_id);
			rs=pre.executeQuery();
			while(rs.next()){
				Category cate=new Category();
				cate.setId_dm(rs.getInt("id_category"));
				cate.setTitle(rs.getString("title"));
				cate.setSp_id(rs.getInt("sp_id"));
				cate.setContent_dm(rs.getString("content_category"));
				vtct.add(cate);
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}
	
	
	/*get all resourse follow sp_id*/
	
	public Vector<Category> AllCategorySp_id(int sp_id){
		Vector<Category> vtctsp=new Vector<Category>();
		try{
			connection=ConnectDB.ConnectData();
			String sql = "select id_category,title,sp_id,content_category from tb_category where sp_id=? and status=1";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,sp_id);
			rs=pre.executeQuery();
			while(rs.next()){
				Category cate=new Category();
				cate.setId_dm(rs.getInt("id_category"));
				cate.setTitle(rs.getString("title"));
				cate.setSp_id(rs.getInt("sp_id"));
				cate.setContent_dm(rs.getString("content_category"));
				vtctsp.add(cate);
			}
			
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtctsp;
		
		
	}	
	
	
	
	public String Name_Category(int id_cate){
		try{
			connection=ConnectDB.ConnectData();
			String sql = "select title from tb_category where id_category=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id_cate);
			rs=pre.executeQuery();
			if(rs.next()){
				return rs.getString("title");
			}			
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return "";
			
	}		
	
	public String Name_Category_Parent(int id_cate){
		try{
			connection=ConnectDB.ConnectData();
			String sql = "select title from tb_category where id_category in (select sp_id from tb_category where id_category=?)";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id_cate);
			rs=pre.executeQuery();
			if(rs.next()){
				return rs.getString("title");
			}			
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return "";
			
	}		
	// get content category parent follow sp_id child
	public String Content_Category_Parent(int id_cate){
		try{
			connection=ConnectDB.ConnectData();
			String sql = "select content_category from tb_category where id_category in (select sp_id from tb_category where id_category=?)";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id_cate);
			rs=pre.executeQuery();
			if(rs.next()){
				return rs.getString("content_category");
			}			
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return "";
			
	}		
	public boolean AddCategory(Category cate) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "insert into tb_category(title,sp_id,content_category) values(?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1,cate.getTitle());
			pre.setInt(2,cate.getSp_id());
			pre.setString(3,cate.getContent_dm());				
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	public boolean UpdateCategory(Category cate) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update  tb_category set title=?,sp_id=?,content_category=? where id_category=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1,cate.getTitle());
			pre.setInt(2,cate.getSp_id());
			pre.setString(3,cate.getContent_dm());	
			pre.setInt(4,cate.getId_dm());
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	public boolean DeleteCategory(int id) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update  tb_category set status=0 where id_category=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean CheckExist_Category(int id){
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select *  from tb_category where id_category=? and status=1";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public boolean CheckExist_Category_Level_Two(int id){
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_category  from tb_category where id_category=? and sp_id in(select id_category from tb_category where sp_id=0) and status=1";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean CheckExist_Category_Child(int id){
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_category  from tb_category where sp_id=? and status=1";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(CategoryDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}


	public static void main(String[] a){
		CategoryDAO cate= new CategoryDAO();
		HashMap<Integer,ArrayList<Category>> hmAllDMs=cate.Category_Two_Level();
		for(Category item:hmAllDMs.get(0)){
			
		System.out.println("ket qua la  "+item.getId_dm()+" ----  "+item.getSp_id()+"  -----   "+item.getTitle());
		
		if(hmAllDMs.containsKey(item.getId_dm())){
			for(Category itemlv2:hmAllDMs.get(item.getId_dm())){
				
				System.out.println("ket qua la  "+itemlv2.getId_dm()+" ----  "+itemlv2.getSp_id()+"  -----   "+itemlv2.getTitle());
		}
		}
		
	}
}
}
