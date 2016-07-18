package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Service;
import utils.ConnectDB;

public class ServiceDAO {
	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;
	
	public Vector<Service> All_Service(){
		Vector<Service> vtservice=new Vector<Service>();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id_sv,title_sv,content_sv from tb_service order by id_sv desc";
			pre=connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				Service sv=new Service();
				sv.setId_sv(rs.getInt("id_sv"));
				sv.setTitle_sv(rs.getString("title_sv"));
				sv.setContent_sv(rs.getString("content_sv"));
				vtservice.add(sv);
			}
			return vtservice;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtservice;
	}
	
	public Service Get_Service_ID(int id){
		Service sv=new Service();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id_sv,title_sv,content_sv from tb_service where id_sv=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			rs=pre.executeQuery();
			if(rs.next()){			
				sv.setId_sv(rs.getInt("id_sv"));
				sv.setTitle_sv(rs.getString("title_sv"));
				sv.setContent_sv(rs.getString("content_sv"));				
			}
			return sv;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return sv;
	}
	
	
	public boolean Update_Service(Service sv){
		try{
			connection=ConnectDB.ConnectData();
			String sql="update tb_service set title_sv=?,content_sv=? where id_sv=?";
			pre=connection.prepareStatement(sql);
			pre.setString(1,sv.getTitle_sv());
			pre.setString(2,sv.getContent_sv());
			pre.setInt(3,sv.getId_sv());
			return pre.executeUpdate()>0;		
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
	
	public boolean CheckExist_Service(int id){
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_sv  from tb_service where id_sv=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public boolean Add_Service(Service sv){
		try{
			connection=ConnectDB.ConnectData();
			String sql="insert into tb_service(title_sv,content_sv) value(?,?)";
			pre=connection.prepareStatement(sql);
			pre.setString(1,sv.getTitle_sv());
			pre.setString(2,sv.getContent_sv());
			return pre.executeUpdate()>0;		
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
	

	public boolean Delete_Service(int id_sv){
		try{
			connection=ConnectDB.ConnectData();
			String sql="delete from tb_service where id_sv=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id_sv);			
			return pre.executeUpdate()>0;	
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(ServiceDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}
}
