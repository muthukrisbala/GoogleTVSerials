package google.tv.serials.database;

import google.tv.serials.dataobject.*;
import google.tv.serials.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {
	
	
	
	
	
	public static String insertSerial_data(List<SerialDatado> serialDatadoList,Connection conn) throws SQLException{
		
		String sql = "INSERT IGNORE INTO SERIAL_DATA (SERIAL_TITLE, SERIAL_VIDEO_URL, SERIAL_VIDEO_ALINK,SERIAL_LANG,SERIAL_DATE,SERIAL_NAME,SERIAL_CONTENT,SERIAL_PUB_STATUS,"
				+ " serial_thumb_img_0,serial_thumb_img_1,serial_thumb_img_2,serial_chanel,serial_keyword,serial_videoid)" +
		        "VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		String status="";
		
		for(SerialDatado item: serialDatadoList){
			preparedStatement.setString(1, item.getSerial_title());
			preparedStatement.setString(2, item.getSerial_video_url());
			preparedStatement.setString(3, item.getSerial_video_alink());
			preparedStatement.setString(4, item.getSerial_lang());
			preparedStatement.setDate(5, item.getSerial_date());
			preparedStatement.setString(6, item.getSerial_name());
			preparedStatement.setString(7, item.getSerial_content());
			preparedStatement.setInt(8, item.getSerial_pub_status());
			preparedStatement.setString(9, item.getSerial_thumb_img_0());
			preparedStatement.setString(10, item.getSerial_thumb_img_1());
			preparedStatement.setString(11, item.getSerial_thumb_img_2());
			preparedStatement.setString(12, item.getSerial_chanel());
			preparedStatement.setString(13, item.getSerial_keyword());
			preparedStatement.setString(14, item.getSerial_videoid());
		/*	status+="Title: "+item.getSerial_title()+"Video URL: "+item.getSerial_video_url()+"Video A Link: "+item.getSerial_video_alink()
					+"Lang: "+item.getSerial_lang()+"Date: "+item.getSerial_date()+"Name: "+item.getSerial_name()+
					"Content: "+item.getSerial_content()+"PUB_Status: "+item.getSerial_pub_status()+
					"Thumb_0: "+item.getSerial_thumb_img_0()+"Thumb_1: "+item.getSerial_thumb_img_1()+"Thumb_2: "+item.getSerial_thumb_img_2()+
					"Channel: "+item.getSerial_chanel()+"Keyword: "+ item.getSerial_keyword()+"\n";
					*/
			status+="Title: "+item.getSerial_title()+"Channel: "+item.getSerial_chanel()+"\n";
		
		}
		int resultStatus=preparedStatement.executeUpdate(); 
		
		
		
		
		return status+"FINAL INSERT STATUS: "+resultStatus;
	}
	
	
public static boolean postlookup(String title,String siteName) throws SQLException{
		
	
	//System.out.println("Modified Title: "+title);
		String sql = "SELECT * FROM wp_posts WHERE post_title ='"+title+"' AND post_status='publish'";
		//String sql = "SELECT * FROM wp_posts WHERE post_title =CONVERT('"+title+"' USING ASCII) AND post_status='publish'";
		
		
		Connection conn=null;
		//System.out.println("POST LOOKUP: "+title);
	
		if(siteName.equalsIgnoreCase("webmuthu"))
			 conn=getConnection("dbwebmuthu");
		else if(siteName.equalsIgnoreCase("hindiwebmuthu"))
			conn=getConnection("dbhindiwebmuthu");
		else if(siteName.equalsIgnoreCase("malluwebmuthu"))
			conn=getConnection("dbmalluwebmuthu");
		else if(siteName.equalsIgnoreCase("teluguwebmuthu"))
			conn=getConnection("dbteluguwebmuthu");
		else if(siteName.equalsIgnoreCase("onlinetamilportal"))
			conn=getConnection("dbonlinetamilportal");
		else if(siteName.equalsIgnoreCase("onlinekanadaportal"))
			conn=getConnection("dbonlinekanadaportal");
		else if(siteName.equalsIgnoreCase("onlineteluguportal"))
			conn=getConnection("dbonlineteluguportal");
		else if(siteName.equalsIgnoreCase("onlinehindiportal"))
			conn=getConnection("dbonlinehindiportal");
		
		boolean flag=false;
		if(null!=conn){
			Statement statement = conn.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			
			
			//System.out.println("Flag: Before: "+flag+"--"+title);
			while(rs.next()){
				//System.out.println("TRUE");
				flag=true;
				break;
			}
			//System.out.println("Flag: After: "+flag+"--"+title);
			//System.out.println("END: "+new Date());
			
		}else{
			System.out.println("Connection Null: "+siteName);
			flag=true;
		}
		return flag;
	}


public static List<SerialDatado> selectSerial_data_home(String title) throws SQLException{
		
		String sql="";
		
		if(title.equalsIgnoreCase("All")){
			sql = "SELECT SERIAL_TITLE, SERIAL_VIDEO_URL, SERIAL_VIDEO_ALINK,SERIAL_LANG,SERIAL_DATE,SERIAL_NAME,SERIAL_CONTENT,SERIAL_PUB_STATUS,serial_thumb_img_0,serial_thumb_img_1,serial_thumb_img_2 " +
			        ",serial_timestamp FROM SERIAL_DATA ORDER BY SERIAL_DATE ASC";
		}else {
			sql = "SELECT SERIAL_TITLE, SERIAL_VIDEO_URL, SERIAL_VIDEO_ALINK,SERIAL_LANG,SERIAL_DATE,SERIAL_NAME,SERIAL_CONTENT,SERIAL_PUB_STATUS,serial_thumb_img_0,serial_thumb_img_1,serial_thumb_img_2 " +
			        " FROM SERIAL_DATA WHERE SERIAL_NAME LIKE '%"+title+"%'";
		}
		
		Connection conn=getConnection("db");
		Statement statement = conn.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String> itemList=new  ArrayList<String>();
		
		List<SerialDatado> itemListNew=new  ArrayList<SerialDatado>();
		while (rs.next()){
			SerialDatado item=new  SerialDatado();
		
		 //String titleStr="http://www.onlinetamilportal.com/showPost.jsp?serial="+rs.getString(1);
			String titleStr="http://www.onlinetamilportal.com/show/"+rs.getString(1);
			item.setSerial_video_url(titleStr);
			item.setTimestamp(rs.getTimestamp(12));
			itemListNew.add(item);
		
		 itemList.add(titleStr);
		}
		return itemListNew; 
		
	}


public static List<String> selectURLTest() throws SQLException{
	
	String sql="";
	
	
		sql = "SELECT SERIAL_TITLE FROM SERIAL_DATA";
	
	Connection conn=getConnection("db");
	Statement statement = conn.createStatement();
	ResultSet rs=statement.executeQuery(sql);
	List<String> itemList=new  ArrayList<String>();
	
	
	while (rs.next()){
		SerialDatado item=new  SerialDatado();
	
	 String titleStr="http://localhost/show/"+rs.getString(1);
	
	 itemList.add(titleStr);
	}
	statement.close();
	rs.close();
	conn.close();
	return itemList; 
	
}
	
	public static Connection getConnection(String dbname){
		Connection connection = null;
		dbname="dbonlinetamilportal";
		String db=util.getPropertyValue(dbname);
		String username ="";
		String password="";
		
		
		username=util.getPropertyValue("username");		
		password=util.getPropertyValue("password");
		/*if(dbname.equalsIgnoreCase("dbonlinekanadaportal")){
			username="muthuhqw_otp";		
			password="Saudia@77";
		}else if(dbname.equalsIgnoreCase("dbonlineteluguportal")){
			username="muthuhqw_otp";		
			password="Saudia@77";
		}else if(dbname.equalsIgnoreCase("dbonlinehindiportal")){
			username="muthuhqw_otp";		
			password="Saudia@77";
		}*/
		
		//System.out.println("-------- MySQL JDBC Connection Testing ------------");		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			
		} 	
	 
		try {
			connection = DriverManager.getConnection(db,username,password);
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();			
		}
	 
		if (connection != null) {
			//System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}
	
	
	
	
	public static void main(String args[]){
		//getConnection();
	}

}
