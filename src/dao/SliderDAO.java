package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Slider;
import utils.ConnectDB;

public class SliderDAO {
	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;

	public SliderDAO() {
	}

	// select all resource in table tb_slider
	public Vector<Slider> AllSlider() {
		Vector<Slider> vtsl = new Vector<Slider>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id,title,image_slider from tb_slider";
			pre = connection.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				Slider sl = new Slider();
				sl.setId(rs.getInt("id"));
				sl.setName_image(rs.getString("image_slider"));
				sl.setTitle(rs.getString("title"));
				vtsl.add(sl);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(SliderDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtsl;
	}
	
	public Slider SliderFollowId(int id) {
		Slider sl = new Slider();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id,title,image_slider from tb_slider where id=?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1,id);
			rs = pre.executeQuery();
			if (rs.next()) {
				
				sl.setId(rs.getInt("id"));
				sl.setName_image(rs.getString("image_slider"));
				sl.setTitle(rs.getString("title"));
				return sl;
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(SliderDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return sl;
	}

	public boolean AddSlider(Slider sl) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "insert into tb_slider(title,image_slider) value(?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1, sl.getTitle());
			pre.setString(2, sl.getName_image());
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(SliderDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean UpdateSlider(Slider sl) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update tb_slider set title=?,image_slider=? where id=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1, sl.getTitle());
			pre.setString(2, sl.getName_image());
			pre.setInt(3,sl.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(SliderDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public boolean DeleteSlider(int id){
		try{
			connection=ConnectDB.ConnectData();
			String sql="delete from tb_slider where id=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);				
			return pre.executeUpdate()>0;
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(SliderDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
	
}
