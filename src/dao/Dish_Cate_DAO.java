package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectDB;

public class Dish_Cate_DAO {	
		private PreparedStatement pre;
		private Connection connection;
		private ResultSet rs;
		public Dish_Cate_DAO(){}
		
		public boolean AddDish_Cate(int id_dish,int id_cate){
			try{				
				connection=ConnectDB.ConnectData();
				String  sql="insert into tb_cate_dish(id_dish,id_category) value(?,?)";
				pre=connection.prepareStatement(sql);
				pre.setInt(1,id_dish);
				pre.setInt(2,id_cate);			
				return pre.executeUpdate()>0;			
			}catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(Dish_Cate_DAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
			return false;
		}
		
		public boolean Delete_Dish_Cate(int id_dish,int id_cate){
			try{				
				connection=ConnectDB.ConnectData();
				String  sql="delete from tb_cate_dish where id_category=? and id_dish=?";
				pre=connection.prepareStatement(sql);
				pre.setInt(2,id_dish);
				pre.setInt(1,id_cate);						
				return pre.executeUpdate()>0;			
			}catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(DishDAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
			return false;
		}
	

		public boolean CheckExist_Dish_Cate(int id_dish,int id_cate){
			try {
				connection = ConnectDB.ConnectData();
				String sql = "select id_dish from tb_cate_dish where id_dish=? and id_category=?";
				pre=connection.prepareStatement(sql);
				pre.setInt(1,id_dish);
				pre.setInt(2, id_cate);
				return pre.executeQuery().next();
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(Dish_Cate_DAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
			return false;
		}
		public static void main(String[] a){
			Dish_Cate_DAO jj=new Dish_Cate_DAO();
			boolean hh=jj.CheckExist_Dish_Cate(15,19);
			System.out.println("in ra kq= "+hh);
		}
}
