package google.tv.serials.main;

import google.tv.serials.database.DatabaseManager;
import google.tv.serials.dataobject.SerialDatado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StarPlus {
	public static void main(String args[]) throws SQLException {
		
		//FeedReaderRajT.getUrlList();

		getUrlList("http://www.desiserials.tv/watch-online/star-plus/diya-aur-baati-hum-star-plus/");
		
		
				
		
	}
	
	
	public static void getUrlList(String urlString) throws SQLException{
		URL url;

		 //Connection connection=DatabaseManager.getConnection();
		int count=0;
		

		try {
			// get URL content
			String title="";
			
			String prefix="http://www.desiserials.tv/watch-online/star-plus/";
			String dataStr="";
			String imageURL="";
			
			url = new URL(urlString);
			URLConnection conn = url.openConnection();
			
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine;
			String channel="";

			

				int startIndex=0;
				int endIndex=0;
		
			
				String urlStr="";
				
				
				
				
				
				
			while ((inputLine = br.readLine()) != null) {
				
				

				//if(inputLine.startsWith("<div class=\"cover\">")){
				 //if(inputLine.startsWith("<div class=\"post-thumb\">")){
					//System.out.println("working");
					startIndex=inputLine.indexOf("<h2 class=\"title bottom-2 top-2\"><span class=\"underline\"><a");
					endIndex=inputLine.indexOf("</a>");
					
					//endIndex=inputLine.indexOf("class=\"clearfix\">");
					//System.out.println(startIndex+":"+endIndex);
					if(startIndex>0 && endIndex>0){
					urlStr=inputLine.substring(startIndex+2, endIndex-4);
					
					//if(urlStr.startsWith(serialName)){
						//title=urlStr.replace(serialName, "");
						//title=title.substring(0,title.length()-5);
						//title=title.replace("-", " ");
						urlStr=urlStr;
						//System.out.println("Title: "+title);
						
						dataStr="<a href=\""+urlStr+"\" target=\"_blank\">"+title+"</a>";
						System.out.println("URL: "+dataStr);
					//}
					
					boolean flagOTP=false;
					flagOTP=DatabaseManager.postlookup(title,"onlinetamilportal");
					if(!flagOTP)
						Wpost.main(title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlinetamilportal");
						
						
					}else if(channel.equalsIgnoreCase("zee hindi")){
						boolean flagOHP=DatabaseManager.postlookup(title,"onlinehindiportal");
						if(!flagOHP)
							Wpost.main(title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlinehindiportal");
					
					
				//}
					}else if(channel.equalsIgnoreCase("zee telugu")){
						boolean flagOHP=DatabaseManager.postlookup(title,"onlineteluguportal");
						if(!flagOHP)
							Wpost.main(title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlineteluguportal");
					
					
				//}
					}
					
			}

			
			br.close();
//connection.close();

			//System.out.println("Done");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
}