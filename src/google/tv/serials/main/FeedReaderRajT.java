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


import com.amazonaws.services.lambda.runtime.Context; 


    

public class FeedReaderRajT {
	
	public  static String serialName;
	public static String imgPostId;
	public static void main(String args[]) throws SQLException {

		getUrlList();
	}
	
	
	
	
	public static void getUrlList() throws SQLException{
		URL url;

		 //Connection connection=DatabaseManager.getConnection();
		
		System.out.println("Start of Tamil Serial");
		try {
			// get URL content
			url = new URL("http://www.rajtamil.com/");
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
					//System.out.println("working");
					startIndex=inputLine.indexOf("<a href=\"");
					endIndex=inputLine.indexOf("\" title=");
					//System.out.println(startIndex+":"+endIndex);
					if(startIndex>0 && endIndex>0){
					urlStr=inputLine.substring(startIndex+9, endIndex);
					//System.out.println("URL: "+urlStr);
					
					
				//	String titleToCheck=serialDataList.get(0).getSerial_title();
					//titleToCheck=titleToCheck.replace("- RAJTAMIL","");
					serialDataList.add(getExternalSource(urlStr));
					//if(null!=titleToCheck){
					
					/*if(titleToCheck.contains("Kalyanam-Mudhal") || titleToCheck.contains("Super-Singer")
							|| titleToCheck.contains("Saravanan-Meenakshi") || titleToCheck.contains("Deivam-Thandha") || titleToCheck.contains("Andal-Azhagar") || titleToCheck.contains("Kalathu-Veedu")
							|| titleToCheck.contains("Neengalum-vellalam-oru-kodi") || titleToCheck.contains("Chandralekha") || titleToCheck.contains("Kalyana-Kanavugal") || titleToCheck.contains("Urave-Uyire")
							|| titleToCheck.contains("En-Kanmani") || titleToCheck.contains("Iru-Malargal") || titleToCheck.contains("En-Kanavan") || titleToCheck.contains("Nandhavanam")
							|| titleToCheck.contains("En-Anbu") || titleToCheck.contains("Moondru-Mudichu") || titleToCheck.contains("Vani-Rani") || titleToCheck.contains("Priyamanaval")
							|| titleToCheck.contains("Vamsam") || titleToCheck.contains("Deivamagal") || titleToCheck.contains("Kula-Deivam") || titleToCheck.contains("Adhu-Idhu-Edhu")
							|| titleToCheck.contains("Valli") || titleToCheck.contains("Kalyana-Parisu") || titleToCheck.contains("Ponnoonjal") || titleToCheck.contains("Marakatha-Veenai")
							|| titleToCheck.contains("Devathai") || titleToCheck.contains("Apoorva-Raagangal") || titleToCheck.contains("Thamari") || titleToCheck.contains("Bommalattam")
							|| titleToCheck.contains("Samayal-Samayal") || titleToCheck.contains("Doctor-Doctor") || titleToCheck.contains("Mouna-Ragam") || titleToCheck.contains("Ullam-Kollai")){
						//System.out.println("Before Insert");
						//DatabaseManager.insertSerial_data(serialDataList,connection);
					}*/
					//}
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
		System.out.println("End of Tamil Serial");

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

						title+=inputLine.substring(startIndex+7, endIndex);
						
						if(title.endsWith("-")){
							title=title.replace("-", "") ;
					 }
					
				}
				
				if(inputLine.contains("<iframe frameborder=\"0\"") ||( inputLine.contains("dailymotion") &&  inputLine.contains("<iframe"))  || (inputLine.contains("youtube") &&  inputLine.contains("<iframe"))
						|| (inputLine.contains("youtube") &&  inputLine.contains("<object")) || (inputLine.contains("youtube") &&  inputLine.contains("<a style=\"cursor:"))){
					//System.out.println("InputLine: "+inputLine);
					startIndex=inputLine.indexOf("<iframe");
					endIndex=inputLine.indexOf("</iframe>");
					
					if(inputLine.contains("<object")){
						
						//System.out.println("Beffore: "+startIndex);
						startIndex=inputLine.indexOf("<object");
						endIndex=inputLine.indexOf("</object>");
						//System.out.println("After:"+startIndex);
						
					}
					
					if(inputLine.contains("<a style=\"cursor:")){
						
						//System.out.println("Beffore: "+startIndex);
						startIndex=inputLine.indexOf("<a");
						endIndex=inputLine.indexOf("</a>");
						//System.out.println("After:"+startIndex);
						
					}
					
						frameStr+=inputLine.substring(startIndex)+"<BR>";
					
				}
				
				
			}
			imageURL=fetchStaticImage(title);
			
			startIndexVideoId=frameStr.indexOf("http://www.dailymotion.com/embed/video/");
			endIndexVideoId=frameStr.indexOf("?");
			
			if(startIndexVideoId>0){
				videoId=frameStr.substring(startIndexVideoId+39,endIndexVideoId);
				
				//imageURL=fetchStaticImage(videoId,title);
				//if(imageURL.contains("http://www.onlinetamilportal.com/wp-content/uploads/2016/")){
					//imageURL=fetchImage(videoId,title);
				//}
				//System.out.println("IMG: "+imageURL);
				if(videoId.length()>0){
				    imageURL=fetchImage(videoId,title);
				}
			}
			String orginalTitle="";
			orginalTitle=title;
			//System.out.println("imageURL: "+imageURL);
			java.sql.Date serialDate=new java.sql.Date(new java.util.Date().getTime());
			
			br.close();
			
			String channel="";
			if(title.contains("vijay") || title.contains("Vijay")){
				channel="vijaytv";
			}else if(title.contains("polimer") || title.contains("Polimer")){
				channel="polimertv";
			}else if(title.contains("sun") || title.contains("Sun")){
				channel="suntv";
			}else if(title.contains("jaya") || title.contains("Jaya")){
				channel="jayatv";
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
					|| title.contains("Saravanan-Meenakshi") || title.contains("Deivam-Thandha") || title.contains("Sonthangal") || title.contains("Kairasi-kudumbam")
					|| title.contains("Saravanan Meenakshi")
					
					|| title.contains("Kalyana-Kanavugal") || title.contains("Urave-Uyire") 
					|| title.contains("En-Kanmani") || title.contains("Iru-Malargal") || title.contains("En-Kanavan") || title.contains("Nandhavanam")
					|| title.contains("En-Anbu") || title.contains("Moondru-Mudichu")   ||  title.contains("Adhu-Idhu-Edhu") || title.contains("Seethaiyin-Raaman")  
					|| title.contains("Kings-of-Dance") || title.contains("Neeya-Naana") || title.contains("Naduvula-Konjam-Disturb-Pannuvom") 				
					|| title.contains("Oru-varthai-oru-Latcham") || title.contains("Kalakkapovadhu-Yaaru") || title.contains("Connexions")
					
					|| title.contains("Apoorva-Raagangal")  || title.contains("Inai-Kodugal") || title.contains("Valli") || title.contains("kalyana-Parisu") 
					|| title.contains("Ponnonjal") || title.contains("Marakatha-Veenai") || title.contains("Samayal-Samayal") || title.contains("Doctor-Doctor") 
					|| title.contains("Mouna-Ragam") || title.contains("Ullam-Kollai")  || title.contains("Ponnoonjal") 
					
					|| title.contains("Chandralekha") || title.contains("Vani-Rani") || title.contains("Vamsam") || title.contains("Deivamagal") 
					|| title.contains("Devathai") 	|| title.contains("Bommalattam") || title.contains("Kula-Deivam") || title.contains("EMI-Thavanai-Murai") 
					|| title.contains("Pagal-Nilavu")||title.contains("Neengalum-Vellalam-Oru-Kodi") || title.contains("Kutty-Chutties")
					
					|| title.contains("Naagini")|| title.contains("Maya-Mohini") || title.contains("Mahadev") || title.contains("Mangaiyin-Sabatham")
					|| title.contains("Kiranmala") || title.contains("Ennudaya-Thottathil") || title.contains("Amma") || title.contains("Endrum-Anbudan")
					|| title.contains("Nandhini")|| title.contains("Lakshmi Kalyaanam")|| title.contains("Lakshmi-Kalyaanam")
					|| title.contains("Mapillai")|| title.contains("Maapillai")|| title.contains("Neeli")					
					|| title.contains("Vidhi")|| title.contains("Mahalakshmi")|| title.contains("Sumangali") ){
				
				
				
				String serialName=google.tv.serials.util.util.getSerialName(title);
				
				String categoryArray="\""+channel+"\",\""+title+"\"";
				/*boolean flag=DatabaseManager.postlookup(title,"webmuthu");
				if(!flag){
					System.out.println("TITLE: "+title);
					Wpost.main(title, imageURL+"<BR><BR><BR>"+frameStr,categoryArray,channel+","+title+","+serialDate,"webmuthu");
				}
					*/
				title=title.replace("-", " ");
				System.out.println("serialName "+orginalTitle);
				boolean flagOTP=false;
				flagOTP=DatabaseManager.postlookup(orginalTitle,"onlinetamilportal");
				
				
				if(imgPostId.length()>0){
					imageURL="";
				}
				
				if(title.contains("Nandhini")){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Nandhini.jpg\" alt=\"Nandhini Sun Tv Serial\"\\>";
					
				}else if(title.contains("Lakshmi Kalyaanam")|| title.contains("Lakshmi-Kalyaanam")){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Lakshmi-Kalyanam.jpg\" alt=\"Lakshmi Kalyaanam Vijay Tv Serial\"\\>";
					
				}else if(title.contains("Mapillai") || title.contains("Maapillai")){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Maapillai.jpg\" alt=\"Mapillai Vijay Tv Serial\"\\>";
					
				}else if(title.contains("Neeli")){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Neeli.jpg\" alt=\"Neeli Vijay Tv Serial\"\\>";
					
				}else if(title.contains("Kalyanam Mudhal") ){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Kalyanam_Mudhal_Kadhal_Varai.png\" alt=\"Kalyanam Mudhal Kadhal Varai Vijay Tv Serial\"\\>";
					
				}else if(title.contains("Vamsam") ){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/201605/Vamsam.jpg\" alt=\"Vamsam Sun Tv Serial\" \\>";					
				
				
				}else if(title.contains("Saravanan Meenakshi") ){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Saravanan_Meenakshi.jpg\" alt=\"Saravanan Meenakshi Vijay Tv Serial\" \\>";
					
				}else if(title.contains("Vani Rani") ){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Vani-Rani.jpg\" alt=\"Vani Rani Sun Tv Serial\" \\>";
					
				}else if(title.contains("Vidhi") ){
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Vidhi.jpg\" alt=\"Vidhi Sun Tv Serial\" \\>";					
				
				
				}else if(title.contains("Mahalakshmi") ){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Mahalakshmi.jpg\" alt=\"Mahalakshmi Sun Tv Serial\" \\>";
					
				}else if(title.contains("Sumangali") ){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Sumangali.jpg\" alt=\"Sumangali Sun Tv Serial\" \\>";
					
				}
				
				
				if(!flagOTP)
					Wpost.main(serialName,channel,orginalTitle, imageURL+"<BR><BR><BR>"+frameStr,categoryArray,serialName+","+channel+","+title+","+serialDate,"onlinetamilportal",imgPostId);
					
				
				
					//Wpost.main(serialName,channel,title, frameStr,categoryArray,serialName+","+channel+","+title+","+serialDate,"onlinetamilportal");
			//BloggerMail.sendMail(orginalTitle,frameStr);
			}
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("URL Exception");
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//System.out.println("Finally");
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
			imgtag="<img src=\""+inputLine+"\" alt=\""+title+"\" />";
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//br.close();
		}
			//System.out.println("imgtag"+imgtag);
		return imgtag;
	}
	
	
	public static String fetchStaticImage(String title) throws IOException{
		String imgtag="";
		imgPostId="";
		//imgtag = "http://www.onlinetamilportal.com/wp-content/uploads/2016/";
		serialName="";
		//System.out.println("title new "+title);
		if(title.contains("Super Singer") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/super-singer-5-10-05-2016-vijay.jpg";
			serialName="Super Singer";
			imgPostId="11282";
		
		
		
		}else if(title.contains("Deivam Thandha") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Deivam-Thandha-Veedu.jpg";
			serialName="Deivam Thandha Veedu";
			imgPostId="112793";
		
		}else if(title.contains("Bommalattam") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Bommalattam.jpg";		
			serialName="Bommalattam";
			imgPostId="112792";
		
		}else if(title.contains("Urave Uyire") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/urave-uyire-serial.jpg";
			serialName=	"Urave Uyire";
			imgPostId="112822";
		}else if(title.contains("Adhu Idhu Edhu") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/adhu-idhu-edhu-07-05-2015-vijay-1.jpg";
			serialName="Adhu Idhu Edhu";
			imgPostId="112791";
		
		}else if(title.contains("Apoorva Raagangal") ){
			//System.out.println("Inside"+title);
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Apoorva_Ragangal_serial.jpg";
			serialName="Apoorva Raagangal";
			imgPostId="112790";
		
		}else if(title.contains("Valli") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Valli.jpg";
			serialName="Valli";
			imgPostId="112823";
							
		}else if(title.contains("Ponnonjal") ){
			//imgtag=imgtag+"";
			serialName="Ponnonjal";
			imgPostId="";
		}
		
		
		
		
		
		
		else if(title.contains("Chandralekha") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/chandralekha-07-05-2016-sun-tv-s.jpg";
			serialName="Chandralekha";
			imgPostId="";
		}
		
		else if(title.contains("Kula Deivam") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Kula-Deivam.jpg";
			serialName="Kula Deivam";
			imgPostId="112804";
		}
		
		else if(title.contains("Thamarai") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Thamarai.png";
			serialName="Thamarai";
			imgPostId="112821";
		}
		
		else if(title.contains("Deivamagal") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Deivamagal.jpg";
			serialName="Deivamagal";
			imgPostId="112794";
		}
		
		else if(title.contains("Priyamanaval") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Priyamanaval-Sun-Tv-Serial.jpg";
			serialName="Priyamanaval";
			imgPostId="112813";
		}
		
		else if(title.contains("En Kanmani") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/en-kanmani.jpg";
			serialName="En Kanmani";
			imgPostId="112796";
		}
		
		else if(title.contains("Iru-Malargal") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Iru-malargal-Polimer-Tv-Serial.jpg";
			serialName="Iru-Malargal";
			imgPostId="112799";
		}
		
		else if(title.contains("Moondru Mudichu") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Moondru-Mudichu-Polimer-tv-serial.jpg";
			serialName="Moondru Mudichu";
			imgPostId="112809";
		}
		
		else if(title.contains("Sonthangal") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Sonthangal.jpg";
			serialName="Sonthangal";
			imgPostId="112818";
		}
		
		else if(title.contains("Kairasi kudumbam") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Kairasi-Kudumbam.jpg";
			serialName="Kairasi kudumbam";
			imgPostId="112800";
		}
		
		else if(title.contains("Inai Kodugal") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Inai-Kodugal.jpg";
			serialName="Inai Kodugal";
			imgPostId="";
		}
		
		else if(title.contains("Pagal Nilavu") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/pagal-nilavu.jpg";
			serialName="Pagal Nilavu";
			imgPostId="112812";
					
		}
		
		
		else if(title.contains("Seethaiyin Raaman") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Seethaiyin Raaman";
			imgPostId="";
		}else if(title.contains("Ullam Kollai Poguthada") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Ullam Kollai Poguthada";
			imgPostId="";
		}else if(title.contains("Inai Kodugal") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Inai Kodugal";
			imgPostId="112797";
		}else if(title.contains("Urave Uyire") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Urave Uyire";
			imgPostId="";
		}else if(title.contains("Kutty Chutties") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Kutty-Chutties";
			imgPostId="112805";
		}else if(title.contains("Naagini") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Naagini";
			imgPostId="";
		}else if(title.contains("Maya Mohini") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Maya-Mohini";
			imgPostId="";
		}else if(title.contains("Neeya Naana") ){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Neeya-Naana";
			imgPostId="112811";
		}else if(title.contains("Marakatha Veenai")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Marakatha-Veenai";
			imgPostId="112807";
		}else if(title.contains("En Kanavan")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="En-Kanavan";
			imgPostId="112795";
		}else if(title.contains("Neengalum Vellalam Oru Kodi")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Neengalum-Vellalam-Oru-Kodi";
			imgPostId="112826";
		}else if(title.contains("Mahadev")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Mahadev";
			imgPostId="117369";
		}else if(title.contains("Mangaiyin Sabatham")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Mangaiyin-Sabatham";
			imgPostId="117368";
		}else if(title.contains("Kiranmala")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Kiranmala";
			imgPostId="117367";
		}else if(title.contains("Ennudaya Thottathil")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Ennudaya-Thottathil";
			imgPostId="117366";
		}else if(title.contains("Amma")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Amma";
			imgPostId="117365";
		}else if(title.contains("Endrum Anbudan")){
			//imgtag="http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Seethaiyin-Raman.jpg";
			serialName="Endrum-Anbudan";
			imgPostId="117364";
		}
		
		
		
		
		
		
		//imgtag="<img alt=\""+title+"\" src=\""+imgtag+"\" />";
		imgtag="";
			
		return imgtag;
	}
}