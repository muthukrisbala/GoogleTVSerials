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

public class FeedReaderT {
	public static void main(String args[]) throws SQLException {

		getUrlList();
	}
	
	
	public static void getUrlList() throws SQLException{
		URL url;

		 //Connection connection=DatabaseManager.getConnection();
		

		try {
			// get URL content
			url = new URL("http://www.tamilserialtoday.org/page/2/");
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

				//if(inputLine.startsWith("<div class=\"cover\">")){
				 //if(inputLine.startsWith("<div class=\"post-thumb\">")){
					//System.out.println(inputLine);
					startIndex=inputLine.indexOf("<a href=\"");
					endIndex=inputLine.indexOf("\" title=");
					//System.out.println(startIndex+":"+endIndex);
					if(startIndex>0 && endIndex>0){
					urlStr=inputLine.substring(startIndex+9, endIndex);
					System.out.println("URL: "+urlStr);
					serialDataList.add(getExternalSource(urlStr));
					
					String titleToCheck=serialDataList.get(0).getSerial_title();
					System.out.println("TITLLTTT: "+serialDataList.get(0).getSerial_title());
					if(titleToCheck.contains("Kalyanam-Mudhal") || titleToCheck.contains("Super-Singer")
							|| titleToCheck.contains("Saravanan-Meenakshi") || titleToCheck.contains("Deivam-Thandha") || titleToCheck.contains("Andal-Azhagar") || titleToCheck.contains("Kalathu-Veedu")
							|| titleToCheck.contains("Bala-Ganapathy") || titleToCheck.contains("Chandralekha") || titleToCheck.contains("Kalyana-Kanavugal") || titleToCheck.contains("Urave-Uyire")
							|| titleToCheck.contains("En-Kanmani") || titleToCheck.contains("Iru-Malargal") || titleToCheck.contains("En-Kanavan") || titleToCheck.contains("Nandhavanam")
							|| titleToCheck.contains("En-Anbu") || titleToCheck.contains("Moondru-Mudichu") || titleToCheck.contains("Vani-Rani") || titleToCheck.contains("Priyamanaval")
							|| titleToCheck.contains("Vamsam") || titleToCheck.contains("Deivamagal") || titleToCheck.contains("Kula-Deivam") || titleToCheck.contains("Adhu-Idhu-Edhu")
							|| titleToCheck.contains("Valli") || titleToCheck.contains("Kalyana-Parisu") || titleToCheck.contains("Ponnoonjal") || titleToCheck.contains("Marakatha-Veenai")
							|| titleToCheck.contains("Devathai") || titleToCheck.contains("Apoorva-Raagangal") || titleToCheck.contains("Thamari") || titleToCheck.contains("Bommalattam")
							|| titleToCheck.contains("Samayal-Samayal") || titleToCheck.contains("Doctor-Doctor") || titleToCheck.contains("Mouna-Ragam") || titleToCheck.contains("Ullam-Kollai")){
						//System.out.println("Before Insert");
						//DatabaseManager.insertSerial_data(serialDataList,connection);
					}
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
	
	public static SerialDatado getExternalSource(String urlStr){
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
					startIndex=inputLine.indexOf("<title>");
					endIndex=inputLine.indexOf("- RAJTAMIL");

						title+=inputLine.substring(startIndex+7);
						
						if(title.endsWith("-")){
							title=title.replace("-", "") ;
					 }
						System.out.println("Test: "+title);
					
				}
				
				if(inputLine.contains("<iframe frameborder=\"0\"") ||( inputLine.contains("dailymotion") &&  inputLine.contains("<iframe"))  || (inputLine.contains("youtube") &&  inputLine.contains("<iframe")) ){
					//System.out.println("InputLine: "+inputLine);
					startIndex=inputLine.indexOf("<iframe");
					endIndex=inputLine.indexOf("</iframe>");
					
						frameStr+=inputLine.substring(startIndex)+"<BR>";
					
				}
				
				
			}
			
			startIndexVideoId=frameStr.indexOf("http://www.dailymotion.com/embed/video/");
			endIndexVideoId=frameStr.indexOf("?");
			
			if(startIndexVideoId>0){
				videoId=frameStr.substring(startIndexVideoId+39,endIndexVideoId);
				
				imageURL=fetchImage(videoId,title);
				//System.out.println("IMG: "+imageURL);
			}
			System.out.println("Title: "+title);
			java.sql.Date serialDate=new java.sql.Date(new java.util.Date().getTime());
			
			br.close();
			
			String channel="";
			if(title.contains("vijay") || title.contains("Vijay")){
				channel="vijaytv";
			}else if(title.contains("polimer") || title.contains("Polimer")){
				channel="polimertv";
			}else if(title.contains("sun") || title.contains("Sun")){
				channel="suntv";
			}else{
				channel="tamilserial";
			}

			title=title.replaceAll(" ", "-");
			if(title.endsWith("-"))
				title=title.substring(0, title.length()-1);
			
			title=title.replaceAll("---", "-");
			title=title.replaceAll("--", "-");
			
			title=title.replaceAll("–", "");
			serialDataObject.setSerial_video_url("NA");
			serialDataObject.setSerial_video_alink("NA");
			serialDataObject.setSerial_title(title);
			serialDataObject.setSerial_name(title);
			serialDataObject.setSerial_date(serialDate);
			serialDataObject.setSerial_pub_status(1);
			serialDataObject.setSerial_content(imageURL+"<BR><BR><BR>"+frameStr);
			serialDataObject.setSerial_lang("tamil");
			serialDataObject.setSerial_thumb_img_0(imageURL);
			serialDataObject.setSerial_thumb_img_1("NA");
			serialDataObject.setSerial_thumb_img_2("NA");
			serialDataObject.setSerial_chanel(channel);
			serialDataObject.setSerial_keyword(channel+title+","+serialDate+","+"tamil");
			serialDataObject.setSerial_videoid("NA");
			
			
			//System.out.println("Done");
			if(title.contains("Kalyanam-Mudhal") || title.contains("Super-Singer")
					|| title.contains("Saravanan-Meenakshi") || title.contains("Deivam-Thandha") || title.contains("Andal-Azhagar") || title.contains("Kalathu-Veedu")
					|| title.contains("Bala-Ganapathy") || title.contains("Chandralekha") || title.contains("Kalyana-Kanavugal") || title.contains("Urave-Uyire")
					|| title.contains("En-Kanmani") || title.contains("Iru-Malargal") || title.contains("En-Kanavan") || title.contains("Nandhavanam")
					|| title.contains("En-Anbu") || title.contains("Moondru-Mudichu") || title.contains("Vani-Rani") 
					|| title.contains("Vamsam")  ||  title.contains("Adhu-Idhu-Edhu")
					|| title.contains("Seethaiyin-Raaman")  || title.contains("Ponnoonjal") 
					 
					|| title.contains("Azhagi") || title.contains("Valli") || title.contains("kalyana Parisu") || title.contains("Ponnonjal") || title.contains("Marakatha Veenai")
					|| title.contains("Samayal-Samayal") || title.contains("Doctor-Doctor") || title.contains("Mouna-Ragam") || title.contains("Ullam-Kollai") 
					|| title.contains("Kings-of-Dance")  || title.contains("Neeya-Naana")){
				
				String categoryArray="\""+channel+"\",\""+title+"\"";
				boolean flag=DatabaseManager.postlookup(title,"webmuthu");
				if(!flag)
					Wpost.main(title, imageURL+"<BR><BR><BR>"+frameStr,categoryArray,channel+","+title+","+serialDate,"webmuthu");
					
				
				boolean flagOTP=DatabaseManager.postlookup(title,"onlinetamilportal");
				if(!flagOTP)
					Wpost.main(title, imageURL+"<BR><BR><BR>"+frameStr,categoryArray,channel+","+title+","+serialDate,"onlinetamilportal");
			
			}
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return serialDataObject;
	}
	
	public static String fetchImage(String videoId,String title) throws IOException{
		String imgtag="";
		URL jsonurl = new URL("https://api.dailymotion.com/video/"+videoId+"?fields=thumbnail_url");
		try{
		
		URLConnection conn = jsonurl.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(
                           new InputStreamReader(conn.getInputStream()));

		String inputLine;

		
		
		
			
		while ((inputLine = br.readLine()) != null) {
			inputLine=inputLine.replace("{\"thumbnail_url\":\"","");
			inputLine=inputLine.replace("\"}", "");
			//inputLine=inputLine.replace("\/\/", "");
			imgtag="<img alt=\""+title+"\" src=\""+inputLine+"\" />";
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//br.close();
		}
			
		return imgtag;
	}
}