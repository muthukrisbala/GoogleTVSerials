package google.tv.serials.main;

import google.tv.serials.dataobject.*;
import google.tv.serials.database.*;
import google.tv.serials.util.*;

import java.net.URL;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.*;


 public class FeedReaderKanada{	 
	 
	 static int count=0;
	 
	 public static  String videoFeedReader(String url,String channel) {				
		String feedTitle="";
		String newURL="";    
		String serialTitle="";
		String serialName="";
		java.sql.Date serialDate = null;
		int pubStatus=0;
		String serialContent="";
		List<String> titleList=new ArrayList<String>();		
	    List<SerialDatado> serialDataList=new ArrayList<SerialDatado>();
	    String thumbImg="";
	    String lang="";
	    String serial_chanel="";
	    String thumbImgNew="";
	    String logStr="";
	    
	    
	    logStr+="Channel: "+channel+"\n";
	     try {
	    	 System.out.println(channel);
	    		//System.out.println("Start: "+new Date());
	    	 //System.out.println("Start of Session: "+channel +": "+new java.util.Date());
			   URL feedUrl = new URL(url);
	           SyndFeedInput input = new SyndFeedInput();
	           SyndFeed feed = input.build(new XmlReader(feedUrl));
				                
				for (Iterator iterator = feed.getEntries().iterator(); iterator.hasNext();) {	
					SerialDatado serialDataObject=new SerialDatado();
					String videoId="";
					SyndEntry syndEntry = (SyndEntry) iterator.next();
					feedTitle=syndEntry.getTitle();					
					String link=syndEntry.getLink().toString();	
					link=link.replace("&feature=youtube_gdata", "");
					
					//System.out.println(count+"--"titleList);
					//titleList=getTitle(feedTitle);
					//titleList=feedTitle;
					//System.out.println(titleList.size());
					count++;
					System.out.println(count+"--"+titleList);
					
					 serialName=feedTitle;
					 
					 if(channel.equals("chithi") || channel.equals("vikatan") 
							 ||  channel.equals("radan") || channel.equals("suntv")|| channel.equals("set")|| channel.equals("colors")
							 || channel.equals("gemini") || channel.equals("surya") || channel.equals("chutti") || channel.equals("thanti")
							 || channel.equals("karai") || channel.equals("rajtv") || channel.equals("vision") || channel.equals("thiru")
							 || channel.equals("cinetimes") || channel.equals("homemovie") || channel.equals("polimer") || channel.equals("setindiatamil")
							 || channel.equals("vendar") || channel.equals("puthuyugam") || channel.equals("vasanth") || channel.equals("captain")
							 || channel.equals("jaya") || channel.equals("kalaignar") || channel.equals("etvtelugu") || channel.equals("maatv")
							 || channel.equals("maamusic") || channel.equals("teluguserials") || channel.equals("udayatv") || channel.equals("etvkannada")
							 || channel.equals("etvmarathi") || channel.equals("etvbangla") || channel.equals("etvgujarati") || channel.equals("etvodia")
							 || channel.equals("6tvtelangana") || channel.equals("abntelugutv") || channel.equals("V6NewsTelugu") || channel.equals("6tv")
							 || channel.equals("ntvteluguhd") || channel.equals("VanithaTvChannel") || channel.equals("asianetnews") || channel.equals("amritatv")
							 || channel.equals("behindwoodstv") || channel.equals("IGtamil") || channel.equals("IGtelugu") || channel.equals("igbollywood")
							 || channel.equals("igmalayalam") || channel.equals("igkannada") || channel.equals("cinemavikatan") || channel.equals("nakkheeranwebtv")
							 || channel.equals("galattavideos") || channel.equals("GalattaHindi") || channel.equals("GalattaMalayalam") || channel.equals("GalattaKannada")
							 || channel.equals("aajtaktv")|| channel.equals("PTM") || channel.equals("sarthaktv") || channel.equals("apoorva") || channel.equals("SANMEDIATV")	
						     || channel.equals("abinaya")){
						 
						 
						 
						 
						/* if(channel.equals("colors")){
							 serialName=titleList.get(0)+"-"+titleList.get(1);
						 }else{
							 serialName=titleList.get(0);
						 }*/
						 
						 
						 serialTitle=setSerialTitle(serialName);
						 pubStatus=1;
						
						 videoId=link.substring(31,link.length());
						 String urlStr="http://img.youtube.com/vi/"+videoId+"/0.jpg";
						 String newImg="";
						 //newImg=ProcessImage.process(urlStr,videoId+".jpg","web");
						 //newImg=ProcessImage.process(urlStr,videoId+".jpg","mobile");
						 
						 thumbImg="<img alt=\""+serialTitle+"\" src=\"http://img.youtube.com/vi/"+videoId+"/" ;
						 // thumbImgNew="<img width=\"180\" height=\"180\" src=\""+newImg+"\" alt=\""+serialTitle+"\"/>";
						 //System.out.println("thumbImg: "+thumbImg);
						 
						 serialContent=serialTitle+"<BR><BR><iframe frameborder=\"0\" width=\"560\" height=\"343\" src=\""+link+"\"></iframe>";
						 if(channel.equalsIgnoreCase("gemini") || channel.equalsIgnoreCase("etvtelugu") || channel.equals("maatv")
								 || channel.equals("maamusic") || channel.equals("teluguserials") || channel.equals("6tvtelangana") 
								 || channel.equals("abntelugutv") || channel.equals("V6NewsTelugu") || channel.equals("6tv")
								 || channel.equals("ntvteluguhd") || channel.equals("VanithaTvChannel") || channel.equals("IGtelugu")
								 ){
							 lang="telugu";
						 }else if(channel.equalsIgnoreCase("colors") || channel.equalsIgnoreCase("set")  || channel.equals("aajtaktv")
								 || channel.equals("igbollywood") || channel.equals("GalattaHindi") ){
							 lang="hindi";
						 
						 }else if(channel.equalsIgnoreCase("surya") || channel.equalsIgnoreCase("karai") || channel.equals("asianetnews") || channel.equals("amritatv")
								 || channel.equals("igmalayalam") || channel.equals("GalattaMalayalam") ){
							 lang="malayalam";
						 }else if(channel.equalsIgnoreCase("udayatv") || channel.equalsIgnoreCase("etvkannada")
								 || channel.equalsIgnoreCase("igkannada") || channel.equalsIgnoreCase("GalattaKannada")){
							 lang="kannada";
						 }else if(channel.equalsIgnoreCase("etvmarathi") ){
							 lang="marathi";
						 }
						 else if(channel.equalsIgnoreCase("etvbangla") ){
							 lang="bangla";
						 }else if(channel.equalsIgnoreCase("etvgujarati") ){
							 lang="gujarati";
						 }else if(channel.equalsIgnoreCase("etvodia") || channel.equalsIgnoreCase("sarthaktv")){
							 lang="odia";
						 }else {
								 lang="tamil";
						}
											
						 serialDate=new java.sql.Date(new java.util.Date().getTime());
						
					 }
					 
					 String thumbImg1=thumbImg+"0.jpg\"/>";
					 
					// newURL="<a href=\"JavaScript:newPopup('"+link+"');\">"+thumbImg1+"</a>";
					 newURL="<a href=\"window.open('"+link+"');\">"+thumbImg1+"</a>";
							
					 
					 if(channel.equalsIgnoreCase("thiru") || channel.equalsIgnoreCase("vision") || channel.equalsIgnoreCase("cinetimes")
							 || channel.equalsIgnoreCase("homemovies") || channel.equalsIgnoreCase("radan")){
						 channel="suntv";
					 }
						String refinedContent="<a onclick=\"window.open('https://www.youtube.com/tv?vq=medium#/watch?v="+videoId+"','width=620,height=400') \">"+thumbImg+"0.jpg\"/>"+"</a>";
						serialDataObject.setSerial_video_url(newURL);
						serialDataObject.setSerial_video_alink(link);
						serialDataObject.setSerial_title(serialTitle);
						serialDataObject.setSerial_name(serialName);
						serialDataObject.setSerial_date(serialDate);
						serialDataObject.setSerial_pub_status(pubStatus);
						serialDataObject.setSerial_content(serialContent);
						serialDataObject.setSerial_lang(lang);
						serialDataObject.setSerial_thumb_img_0(thumbImg+"0.jpg\"/>");
						serialDataObject.setSerial_thumb_img_1(thumbImg+"1.jpg\"/>");
						serialDataObject.setSerial_thumb_img_2(thumbImg+"2.jpg\"/>");
						serialDataObject.setSerial_chanel(channel);
						serialDataObject.setSerial_keyword(channel+","+serialTitle+","+serialDate+","+lang);
						serialDataObject.setSerial_videoid(videoId);
						serialDataList.add(serialDataObject);		
						//System.out.println("serialTitle: "+serialTitle);
						boolean flagOKP=DatabaseManager.postlookup(serialTitle,"onlinekanadaportal");
						if(!flagOKP)
							//Wpost.main(serialTitle, thumbImg+"0.jpg\"/>"+"<BR><BR><BR>"+serialContent,channel,channel+","+serialTitle+","+serialDate,"onlinetamilportal");
							Wpost.main(serialTitle, thumbImg+"0.jpg\"/>"+"<BR><BR><h3>Click on the below image to view the Video</h3><BR><BR>"+refinedContent,channel,channel+","+serialTitle+","+serialDate,"onlinekanadaportal");
					
					
					
				}		
				//logStr+=DatabaseManager.insertSerial_data(serialDataList,conn)+"\n";
				//System.out.println("End of Session: "+new java.util.Date());
			
	          }catch (Exception ex) {
	               ex.printStackTrace();
	               //System.out.println("ERROR: "+ex.getMessage());
	           }	
	     return logStr;
	   }
	 
	 private static String setSerialTitle(String serialName) {
		 String serialTitle="";
		 serialTitle=serialName.replace("(", "") ;
		 
		 serialTitle=serialName.replace(";", "-") ;
		 serialTitle=serialTitle.replace(".", "-") ;
		 serialTitle=serialTitle.replace(".-", "-") ;
		 serialTitle=serialTitle.replace("+", "-") ;
		 serialTitle=serialTitle.replace("=", "-") ;
		 serialTitle=serialTitle.replace("?", "") ;
		 serialTitle=serialTitle.replace("..", "") ;
		 serialTitle=serialTitle.replace(")", "-") ;
		 serialTitle=serialTitle.replace("(", "-") ;
		 serialTitle=serialTitle.replace(":", "-") ;
		 serialTitle=serialTitle.replace("\"", "") ;
		 serialTitle=serialTitle.replace("|", "") ;
		 serialTitle=serialTitle.replace("'", "") ;
		 serialTitle=serialTitle.replace("'", "") ;
		 serialTitle=serialTitle.replace("’", "") ;
		 serialTitle=serialTitle.replace("‘", "") ;	 
		 
		 serialTitle=serialTitle.replace("@", "") ;
		 serialTitle=serialTitle.replace("&", "") ;
		 serialTitle=serialTitle.replace("   ", "-") ;
		 serialTitle=serialTitle.replace("  ", "-") ;
		 serialTitle=serialTitle.replace(" ", "-") ;
		 serialTitle=serialTitle.replace("/", "-") ;
		 serialTitle=serialTitle.replace(" ", "-") ;
		 
		 serialTitle=serialTitle.replace(",", "-") ;		 
		 serialTitle=serialTitle.replace("“", "") ;
		 serialTitle=serialTitle.replace("”", "") ;
		 serialTitle=serialTitle.replace("#", "") ;
		 serialTitle=serialTitle.replace("[", "") ;
		 serialTitle=serialTitle.replace("]", "") ;
		 serialTitle=serialTitle.replace("!", "") ;
		 serialTitle=serialTitle.replace("--", "-") ;
		 serialTitle=serialTitle.replace("---", "-") ;
		 serialTitle=serialTitle.replace("----", "-") ;
		 
		 
		 if(serialTitle.endsWith("-"))
			 serialTitle=serialTitle.substring(0, serialTitle.length()-1);
		 
		 
		/* if (serialTitle.equals("VaaniRani") || serialTitle.equals("Thamarai") || serialTitle.equals("KuladheivamSUNTVEpisode") || serialTitle.equals("AzhagiyaLaila")
				 || serialTitle.equals("EnIniyaThozhiye") || serialTitle.equals("Kanchana") || serialTitle.equals("MannVasanai") || serialTitle.equals("SindhuBhairavi")
				 || serialTitle.equals("EnThangai") || serialTitle.equals("Koppiyam") || serialTitle.equals("PoovizhiVaasalile") || serialTitle.equals("Poomagal")
				 || serialTitle.equals("PriyamanaThozhi") || serialTitle.equals("Ramayanam") || serialTitle.equals("JaiVeeraHanuman") || serialTitle.equals("KairasiKudumbam")
				 || serialTitle.equals("SravanaSameeralu") || serialTitle.equals("AgniPoolu") || serialTitle.equals("ApoorvaRaagangal") || serialTitle.equals("BOMMALAATAM")){*/
			 //serialTitle=serialTitle+"-"+currentDate();
			 //System.out.println("serialName"+serialTitle);
		// }
		// System.out.println("serialTitle -END "+serialTitle);
		// TODO Auto-generated method stub
		return serialTitle;
	}

	public static List<String> getTitle(String feedTitle){		 
		
		 StringTokenizer st=new StringTokenizer(feedTitle,"-");
		 List<String> strList=new ArrayList<String>();
		 
		 while(st.hasMoreTokens()){
			 strList.add(st.nextToken());			 
		 }
		 return strList;
	 }
	
	
	
	public static void main(String args) throws SQLException{	
		
		
		System.out.println("START of MAIN");
		List<String> serialNameList=new ArrayList<String>();
		//Connection conn=DatabaseManager.getConnection("onlinetamilportal");
		serialNameList.clear();
		
		serialNameList.add("udayatv");
		serialNameList.add("etvkannada");
		
		
		serialNameList.add("cinemavikatan");
		
		
		serialNameList.add("GalattaKannada");
		
		
		
		String fileContent="Start Time: "+new java.util.Date()+"\n";
		for(String serial: serialNameList){
			
			String feedURL=util.getPropertyValue(serial);
			//System.out.println("New Serial Name: "+serial);
			videoFeedReader(feedURL,serial);	
		}	
		
		fileContent+="Start Second Time: "+new java.util.Date();
		FeedReaderRajT.main("dummy");
		
		fileContent+="End Time: "+new java.util.Date();
		fileWrite(fileContent);
		System.out.println("START TIME: "+new Date());
		System.out.println("END of MAIN");
	
	}	  
	
	
	public static void fileWrite(String fileContent){
		
		try {
			
			
			String strDate=currentDate();
            File file = new File("E:\\OnlineTamilPortal\\Dataload\\logs\\"+strDate+"-log.txt");
               // if file doesnt exists, then create it
                if (!file.exists()) {
                   file.createNewFile();
                }
           FileWriter fw =new FileWriter(file.getAbsoluteFile(),true);
           BufferedWriter bw =new BufferedWriter(fw);
           bw.write(fileContent);
           bw.close();
           fw.close();
       }catch (IOException e) {
             e.printStackTrace();
       }
	}
	
	public static String currentDate(){
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate=dateFormat.format(date);
		return strDate;
	}
	
	
}