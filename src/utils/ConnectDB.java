package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.SQLException;

public class ConnectDB {
	private static String username;
    private static String pass;    
    // Thong tin duong dan toi CSDL trong MYSQL
    private static String url;
    
	public static Connection ConnectData() {	
		try {
			Properties p = new Properties();
			
			p.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("database_info.properties"));
			url=p.getProperty("URL");
			pass=p.getProperty("PASSWORD");
			username=p.getProperty("USER");
		
	     	Class.forName("com.mysql.jdbc.Driver");				
			Connection con=DriverManager.getConnection(url,username,pass);
			return con;		
		} 
		catch(SQLException ex) {			
			System.out.println("SQLException: " + ex.getMessage());			    
		    System.out.println(ConnectDB.class.getName()); 
		    ex.printStackTrace();		    
		} 
		catch(ClassNotFoundException ex) {
			System.out.println("Class.forNameException: " + ex.getMessage());
			System.out.println("Class.forNameException-State: " + ex.getStackTrace());    	
			System.out.println(ConnectDB.class.getName()); 
		}
		catch(IOException ie) {
			System.out.println("IOException: " +ie.getMessage());
			System.out.println(ConnectDB.class.getName()); 
        }
		catch(Exception ex) {
		    ex.printStackTrace();
		}
	 	
		return null;
	}
	
	//function stop the onnection,PreparedStatement,ResultSet	
	public static void closeConnection(Connection cn ,PreparedStatement pst,ResultSet rs) {
        try {
            if(rs !=null) {
                rs.close();
            }
            if(pst !=null) {
                pst.close();
            }
            if(cn !=null) {
                cn.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}  
	
	public static void main(String[] a) {
		ConnectDB.ConnectData();
	}
}
