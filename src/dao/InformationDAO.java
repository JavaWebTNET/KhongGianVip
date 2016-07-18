package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Information;
import utils.ConnectDB;

public class InformationDAO {

	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;

	public InformationDAO() {
	}

	/* get all resource in table informatinon */
	public Information AllInformation() {
		Information info = new Information();
		try {
			connection = ConnectDB.ConnectData();
			String sql = "select name_res,add_res,tell,email,logo,gioithieu,tuyendung,lienhe from kgvinformation";
			pre = connection.prepareStatement(sql);
			rs = pre.executeQuery();
			if (rs.next()) {
				info.setName_res(rs.getString("name_res"));
				info.setAdd_res(rs.getString("add_res"));
				info.setEmail(rs.getString("email"));
				info.setLogo(rs.getString("logo"));
				info.setTell(rs.getString("tell"));
				info.setGioithieu(rs.getString("gioithieu"));
				info.setTuyendung(rs.getString("tuyendung"));
				info.setLienhe(rs.getString("lienhe"));
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(InformationDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return info;
	}
	/*update resource in table informatin*/
	public boolean UpdateInfo(Information info) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update kgvinformation set name_res=?,add_res=?,tell=?,logo=?,email=?,gioithieu=?,tuyendung=?,lienhe=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1,info.getName_res());
			pre.setString(2,info.getAdd_res());
			pre.setString(3,info.getTell());
			pre.setString(4,info.getLogo());
			pre.setString(5,info.getEmail());
			pre.setString(6,info.getGioithieu());
			pre.setString(7,info.getTuyendung());
			pre.setString(8, info.getLienhe());			
			return pre.executeUpdate()>0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(InformationDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}

		return false;
	}
	
	public boolean AddInformation(Information info){
		try{
			connection=ConnectDB.ConnectData();
			String sql="insert into kgvinformation(name_res,add_res,tell,logo,email,gioithieu,tuyendung) values(?,?,?,?,?,?,?))";
			pre = connection.prepareStatement(sql);
			pre.setString(1,info.getAdd_res());
			pre.setString(2,info.getAdd_res());
			pre.setString(3,info.getTell());
			pre.setString(4,info.getLogo());
			pre.setString(5,info.getEmail());
			pre.setString(6,info.getGioithieu());
			pre.setString(7,info.getTuyendung());

			return pre.executeUpdate()>0;
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(InformationDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}

		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
