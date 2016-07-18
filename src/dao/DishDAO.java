package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Dish;
import utils.ConnectDB;

public class DishDAO {

	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;
	public static int limit=12;

	public DishDAO() {
	}

	public Vector<Dish> AllDish() {
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_dish,image_dish,price,name_dish,content_menu,tasty from tb_dish where status_dish=1";
			pre = connection.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				dish.setContent_dish(rs.getString("content_menu"));
				dish.setTasty(rs.getInt("tasty"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}

	public Vector<Dish> AllFollow_Category(int id_category) {
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select ds.id_dish,image_dish,price,name_dish,status_dish,id_category from tb_dish ds inner join tb_cate_dish cd "
					+ "on ds.id_dish=cd.id_dish and cd.id_category=? order by cd.id desc";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id_category);
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}

	public Vector<Dish> Seach_Dish_FollowName(String name_dish) {
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_dish,image_dish,price,name_dish,content_menu,tasty from tb_dish where name_dish like ? and status_dish=1";
			pre = connection.prepareStatement(sql);
			pre.setString(1, '%' + name_dish + '%');
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				dish.setContent_dish(rs.getString("content_menu"));
				dish.setTasty(rs.getInt("tasty"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}
	public Vector<Dish> All_Dish_Follow_Tasty() {
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql ="select id_dish,image_dish,price,name_dish from tb_dish where status_dish=1 and image_dish not in('null') and tasty=1"; 
			pre = connection.prepareStatement(sql);			
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}

	/* get all dish in table tb_dish have image is not null.this is page home */
	public Vector<Dish> All_Dish_Follow_Image() {
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql ="select id_dish,image_dish,price,name_dish from tb_dish where status_dish=1 and image_dish not in('null') limit ? offset ?"; 
			pre = connection.prepareStatement(sql);
			pre.setInt(1,limit);
			pre.setInt(2,0);
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}
	
	/* get all dish in table  from number_records  get limit records*/
	public Vector<Dish> Dish_Follow_Limit(int number_pages ) {
		if(number_pages==0){
			return All_Dish_Follow_Image();
		}
		Vector<Dish> vtct = new Vector<Dish>();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_dish,image_dish,price,name_dish from tb_dish where status_dish=1 limit ? offset ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1,limit);
			pre.setInt(2, number_pages>0?(number_pages-1)*limit:number_pages*limit);
			rs = pre.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				vtct.add(dish);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return vtct;
	}

	
	/*return number records in table dish*/
	public int Number_Records() {	
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select count(id_dish) as number_records from tb_dish where status_dish=1";
			pre = connection.prepareStatement(sql);
			rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("number_records");
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return 0;
	}

	
	
	
	
	public boolean AddDish(Dish dish) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "insert into tb_dish(image_dish,price,name_dish,content_menu) value(?,?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(1, dish.getImage_dish());
			pre.setString(2, dish.getPrice());
			pre.setString(3, dish.getName_dish());
			pre.setString(4, dish.getContent_dish());
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public boolean UpdateDish(Dish dish) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update tb_dish set image_dish=?,price=?,name_dish=?,content_menu=? where id_dish=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1, dish.getImage_dish());
			pre.setString(2, dish.getPrice());
			pre.setString(3, dish.getName_dish());
			pre.setString(4, dish.getContent_dish());
			pre.setInt(5, dish.getId_dish());
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean UpdateDish_CheckedTasty(int id) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update tb_dish set tasty=? where id_dish=?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1,1);
			pre.setInt(2,id);			
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean UpdateDish_UnCheckedTasty(int id) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update tb_dish set tasty=? where id_dish=?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1,0);
			pre.setInt(2,id);			
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	

	// set status in table Dish =0 have id_dish=id and delete row have
	// id_dish=id
	public boolean DeleteDish(int id) {
		PreparedStatement predelete;
		PreparedStatement preupdate;

		try {
			connection = ConnectDB.ConnectData();
			String sqlupdate = "update tb_dish set status_dish=0 where id_dish=?";
			String sqldelete = "delete from tb_cate_dish where id_dish=?";
			connection.setAutoCommit(false);
			predelete = connection.prepareStatement(sqldelete);
			preupdate = connection.prepareStatement(sqlupdate);
			predelete.setInt(1, id);
			preupdate.setInt(1, id);
			preupdate.executeUpdate();
			predelete.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				System.out.println(DishDAO.class.getName());
				e.printStackTrace();
			}
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println(DishDAO.class.getName());
				e.printStackTrace();
			}
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	// Add dish into table tb_dish and Add (id_cate,id_dish into table
	// tb_dish_cate
	public boolean AddDish_Cate(Dish dish, int id_cate) {
		PreparedStatement preadddish;
		PreparedStatement preuadd_dish_cate;
		try {
			connection = ConnectDB.ConnectData();
			String sql = "insert into tb_dish(image_dish,price,name_dish,content_menu) value(?,?,?,?)";
			String sql2 = "insert into tb_cate_dish(id_dish,id_category) value((select max(id_dish) from tb_dish),?)";
			connection.setAutoCommit(false);
			preadddish = connection.prepareStatement(sql);
			preadddish.setString(1, dish.getImage_dish());
			preadddish.setString(2, dish.getPrice());
			preadddish.setString(3, dish.getName_dish());
			preadddish.setString(4, dish.getContent_dish());
			preuadd_dish_cate = connection.prepareStatement(sql2);
			preuadd_dish_cate.setInt(1, id_cate);
			preadddish.executeUpdate();
			preuadd_dish_cate.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				System.out.println(DishDAO.class.getName());
				e.printStackTrace();
			}
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println(DishDAO.class.getName());
				e.printStackTrace();
			}
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public Dish GetDishId(int id) {
		Dish dish = new Dish();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_dish,image_dish,price,name_dish,content_menu from tb_dish where id_dish=?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (rs.next()) {
				dish.setId_dish(rs.getInt("id_dish"));
				dish.setImage_dish(rs.getString("image_dish"));
				dish.setPrice(rs.getString("price"));
				dish.setName_dish(rs.getString("name_dish"));
				dish.setContent_dish(rs.getString("content_menu"));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return dish;
	}

	public boolean CheckExist_Dish(int id) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select id_dish  from tb_dish where id_dish=? and status_dish=1";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public boolean CheckExist_Dish_Name(String name_dish) {
		try {
			connection = ConnectDB.ConnectData();
			String name = name_dish.trim();
			while (name.indexOf("  ") != -1) {
				name = name.replaceAll("  ", " ");
			}
			String sql = "select id_dish  from tb_dish where name_dish=? and status_dish=1";
			pre = connection.prepareStatement(sql);
			pre.setString(1, name);
			return pre.executeQuery().next();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}

	public int GetIdFollowName(String name_dish) {
		try {
			connection = ConnectDB.ConnectData();
			String name = name_dish.trim();
			while (name.indexOf("  ") != -1) {
				name = name.replaceAll("  ", " ");
			}
			String sql = "select id_dish  from tb_dish where name_dish=? and status_dish=1";
			pre = connection.prepareStatement(sql);
			pre.setString(1, name);
			rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_dish");
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(DishDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return -1;
	}

	/*
	 * public static void main(String[] args) { DishDAO jj=new DishDAO(); //Dish
	 * dish=new Dish(); //dish.setName_dish("cá hấp");
	 * 
	 * boolean hh=jj.CheckExist_Dish_Name("   g  bò      luộc   ");
	 * System.out.println("keets qua "+hh);
	 * 
	 * }
	 */
}
