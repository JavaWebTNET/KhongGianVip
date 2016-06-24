package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.ImageStore;
import utils.ConnectDB;

public class ImageStoreDAO {
	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;

	public ImageStoreDAO(){}
	
	public Vector<ImageStore> AllImageStore(){
		Vector<ImageStore> vtis=new Vector<ImageStore>();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id,name_image,title from tb_imagestore";
			pre=connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				ImageStore is=new ImageStore();
				is.setId(rs.getInt("id"));
				is.setName_image(rs.getString("name_image"));
				is.setTitle(rs.getString("title"));
				vtis.add(is);
			}
			}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ImageStoreDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtis;
	}
	
	public boolean AddImageStore(ImageStore ims){
		try{
			connection=ConnectDB.ConnectData();
			String sql="insert into tb_imagestore(name_image,title) value(?,?)";
			pre=connection.prepareStatement(sql);
			pre.setString(1,ims.getName_image());
			pre.setString(2,ims.getTitle());			
			return pre.executeUpdate()>0;
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ImageStoreDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
	
	public boolean DeletemageStore(int id){
		try{
			connection=ConnectDB.ConnectData();
			String sql="delete from tb_imagestore where id=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);				
			return pre.executeUpdate()>0;
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ImageStoreDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
	
	

}
