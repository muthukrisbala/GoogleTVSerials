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

public class FeedReaderStar {
	public static void main(String args) throws SQLException {

		getUrlList("http://clicknclap.com/indian-dramas/star-plus/","starplus","hindi");
		getUrlList("http://clicknclap.com/indian-dramas/zee-tv/","zeetv","hindi");
		//getUrlList("http://telugustop.com/telugu/telugu-tv-serials-online-all-episodes-zeetv-maatv-etv/","maatv","telugu");
		
		
	}
	
	
	public static void getUrlList(String contentUrl,String channel,String lang) throws SQLException{
		URL url;

		 Connection connection=DatabaseManager.getConnection("hindiwebmuthu");

		try {
			// get URL content
			url = new URL(contentUrl);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine;

			

				int startIndex=0;
				int endIndex=0;
		
			
				String urlStr="";
			while ((inputLine = br.readLine()) != null) {
				 List<SerialDatado> serialDataList=new ArrayList<SerialDatado>();

				if(inputLine.startsWith("<h2 class=\"entry-title\">")){
					startIndex=inputLine.indexOf("<a href=\"");
					endIndex=inputLine.indexOf("\" title=");
					urlStr=inputLine.substring(startIndex+9, endIndex);
					System.out.println("URL: "+urlStr);
					serialDataList.add(getExternalSource(urlStr,channel,lang));
					
					System.out.println("TITLLTTT: "+serialDataList.get(0).getSerial_title());
					
						System.out.println("Before Insert");
						//DatabaseManager.insertSerial_data(serialDataList,connection);
					
				}
			}

			
			br.close();
connection.close();

			//System.out.println("Done");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static SerialDatado getExternalSource(String urlStr, String channel,String lang) throws SQLException{
		URL url;
		SerialDatado serialDataObject=new SerialDatado();

		try {
			// get URL content
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine;

			

				int startIndex=0;
				int endIndex=0;
				int startIndexVideoId=0;
				int endIndexVideoId=0;
		
			
				String frameStr="";
				String title="";
				String videoId="";
				String imageURL="";
			while ((inputLine = br.readLine()) != null) {
				
				if(inputLine.startsWith("<title")){	
						title=inputLine.substring(7,inputLine.length()-8);					
						
					 }
				
				if(inputLine.contains("<p><img")){
					imageURL=inputLine;
				}
				
				if(inputLine.contains("<iframe")){
					startIndex=inputLine.indexOf("<iframe");
					endIndex=inputLine.indexOf("</iframe>");
					frameStr+=inputLine.substring(startIndex)+"<BR>";
					
				}
				
			}
			
			
			
			System.out.println("Title: "+title+" \nIFrame: "+frameStr);
			java.sql.Date serialDate=new java.sql.Date(new java.util.Date().getTime());
			
			br.close();
			
			//String channel="starplus";
			

			title=title.replaceAll(" ", "-");
			if(title.endsWith("-"))
				title=title.substring(0, title.length()-1);
			serialDataObject.setSerial_video_url("NA");
			serialDataObject.setSerial_video_alink("NA");
			serialDataObject.setSerial_title(title);
			serialDataObject.setSerial_name(title);
			serialDataObject.setSerial_date(serialDate);
			serialDataObject.setSerial_pub_status(1);
			serialDataObject.setSerial_content(imageURL+"<BR<BR>>"+frameStr);
			serialDataObject.setSerial_lang(lang);
			serialDataObject.setSerial_thumb_img_0(imageURL);
			serialDataObject.setSerial_thumb_img_1("NA");
			serialDataObject.setSerial_thumb_img_2("NA");
			serialDataObject.setSerial_chanel(channel);
			serialDataObject.setSerial_keyword(channel+","+title+","+serialDate+","+"hindi");
			serialDataObject.setSerial_videoid("NA");
			;
			
			//System.out.println("Done");
			
			String categoryArray="\""+channel+"\",\""+title+"\"";
			boolean flag=DatabaseManager.postlookup(title,"hindiwebmuthu");
			if(!flag)
				hindiwebmuthuWpost.main(title, imageURL+"<BR><BR><BR>"+frameStr,categoryArray,channel+","+title+","+serialDate);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return serialDataObject;
	}
	
	public static String fetchImage(String videoId,String title) throws IOException{
		URL jsonurl = new URL("https://api.dailymotion.com/video/"+videoId+"?fields=thumbnail_url");
		URLConnection conn = jsonurl.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(
                           new InputStreamReader(conn.getInputStream()));

		String inputLine;

		
		String imgtag="";
		
			
		while ((inputLine = br.readLine()) != null) {
			inputLine=inputLine.replace("{\"thumbnail_url\":\"","");
			inputLine=inputLine.replace("\"}", "");
			//inputLine=inputLine.replace("\/\/", "");
			imgtag="<img alt=\""+title+"\" src=\""+inputLine+"\" />";
		}
			
		return imgtag;
	}
}