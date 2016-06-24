package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.News;
import utils.ConnectDB;

public class NewsDAO {
	private PreparedStatement pre;
	private Connection connection;
	private ResultSet rs;
	Date today=new Date(System.currentTimeMillis());
	SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
	public NewsDAO(){}
	
	public Vector<News> AllNews(){
		Vector<News> vtns=new Vector<News>();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id,title,content,time_news from tb_news order by id desc";
			pre=connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				News news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTime_news(rs.getString("time_news"));
				
				vtns.add(news);
			 }
		    }
			catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(NewsDAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
		return vtns;
	}
	
	public Vector<News> NewsLimit10(){
		Vector<News> vtns=new Vector<News>();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id,title,content,time_news from tb_news order by id desc limit 0,10";
			pre=connection.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				News news=new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTime_news(rs.getString("time_news"));
				
				vtns.add(news);
			 }
		    }
			catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(NewsDAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
		return vtns;
	}
	
	public News NewsLimit1(){
		News ns=new News();
		try{
			connection=ConnectDB.ConnectData();
			String sql="select id,title,content,time_news from tb_news order by id desc limit 1";
			pre=connection.prepareStatement(sql);
			rs=pre.executeQuery();
			if(rs.next()){				
				ns.setId(rs.getInt("id"));
				ns.setTitle(rs.getString("title"));
				ns.setContent(rs.getString("content"));
				ns.setTime_news(rs.getString("time_news"));	
				
			 }
		    }
			catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println(NewsDAO.class.getName());
				ex.printStackTrace();
			} finally {
				ConnectDB.closeConnection(connection, pre, rs);
			}
		return ns;
	}
	
	public boolean UpdateNews(News ns) {
	
		try {
			connection = ConnectDB.ConnectData();
			String sql = "update tb_news set content=?,title=?,time_news=? where id=?";
			pre = connection.prepareStatement(sql);
			pre.setString(1, ns.getContent());
			pre.setString(2, ns.getTitle());
			pre.setString(3,timeFormat.format(today.getTime()));
			pre.setInt(4,ns.getId());		
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(NewsDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean AddNews(News sl) {
		try {
			connection = ConnectDB.ConnectData();
			String sql = "insert into tb_news(title,content,time_news) values(?,?,?)";
			pre = connection.prepareStatement(sql);
			pre.setString(2, sl.getContent());
			pre.setString(1, sl.getTitle());
			pre.setString(3,timeFormat.format(today.getTime()));	
			
			return pre.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(NewsDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		return false;
	}
	
	public boolean DeleteNews(int id){
		try{
			connection=ConnectDB.ConnectData();
			String sql="delete from tb_news where id=?";
			pre=connection.prepareStatement(sql);
			pre.setInt(1,id);				
			return pre.executeUpdate()>0;
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(NewsDAO.class.getName());
			ex.printStackTrace();
		} finally {
			ConnectDB.closeConnection(connection, pre, rs);
		}
		
		return false;
	}

}
