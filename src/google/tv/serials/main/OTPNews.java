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


 public class OTPNews{	 
	 
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
	           int count1=0;
				                
				for (Iterator iterator = feed.getEntries().iterator(); iterator.hasNext();) {	
					
					if(count1>5)
						break;
					count1++;
					SerialDatado serialDataObject=new SerialDatado();
					String videoId="";
					SyndEntry syndEntry = (SyndEntry) iterator.next();
					feedTitle=syndEntry.getTitle();					
					String link=syndEntry.getLink().toString();	
					link=link.replace("&feature=youtube_gdata", "");
					
					
					count++;
					//System.out.println(count+"--"+titleList);
					
					 serialName=feedTitle;
					 
					
						 
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
						
								 lang="tamil";
						
											
						 serialDate=new java.sql.Date(new java.util.Date().getTime());
						
					 
					 
					 String thumbImg1=thumbImg+"0.jpg\"/>";
					 
					// newURL="<a href=\"JavaScript:newPopup('"+link+"');\">"+thumbImg1+"</a>";
					 newURL="<a href=\"window.open('"+link+"');\">"+thumbImg1+"</a>";
							
					 
					
						//String refinedContent="<a onclick=\"window.open('https://www.youtube.com/tv?vq=medium#/watch?v="+videoId+"','width=620,height=400') \">"+serialTitle+"</a>";
						String refinedContent="<iframe width=\"854\" height=\"480\" src=\"https://www.youtube.com/embed/"+videoId+"\" frameborder=\"0\" allowfullscreen></iframe>";
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
						serialTitle=serialTitle.replace("-", " ");
						//System.out.println("refinedContent: "+refinedContent);
						
						if(channel.equalsIgnoreCase("thanti") || channel.equalsIgnoreCase("PTM") || channel.equalsIgnoreCase("nakkheeranwebtv")
								|| channel.equalsIgnoreCase("lankasri") || channel.equalsIgnoreCase("tamilhindu")){
							channel="news";
						}else if(channel.equalsIgnoreCase("behindwoodstv") || channel.equalsIgnoreCase("IGtamil") || channel.equalsIgnoreCase("cinemavikatan") 
								|| channel.equalsIgnoreCase("galattavideos") ){
							channel="cinema news";
						}
									boolean flagONP=false;
									flagONP=DatabaseManager.postlookup(serialName,"onlinetamilportal");
									if(!flagONP)
										//Wpost.main(serialTitle, thumbImg+"0.jpg\"/>"+"<BR><BR><BR>"+serialContent,channel,channel+","+serialTitle+","+serialDate,"onlinetamilportal");
									Wpost.main("",channel,serialName, thumbImg+"0.jpg\"/><BR><BR>"+refinedContent,channel,channel+","+serialName+","+serialDate,"onlinetamilportal","");
								
					
				}		
			
			
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
	
	
public static void main(String args[]) throws SQLException{	
		
		
		//System.out.println("START of MAIN");
		List<String> serialNameList=new ArrayList<String>();
		
		serialNameList.clear();
		System.out.println("START TIME: "+new Date());
		
		serialNameList.add("lankasri");
		serialNameList.add("thanti");
		serialNameList.add("tamilhindu");		
		//serialNameList.add("puthuyugam");		
		serialNameList.add("PTM");
		serialNameList.add("nakkheeranwebtv");	
		
		serialNameList.add("behindwoodstv");
		serialNameList.add("IGtamil");
		serialNameList.add("cinemavikatan");
		serialNameList.add("galattavideos");
		
		serialNameList.add("abntelugutv");
		serialNameList.add("6tvtelangana");
		serialNameList.add("V6NewsTelugu");
		serialNameList.add("6tv");
		serialNameList.add("ntvteluguhd");
		serialNameList.add("VanithaTvChannel");
		
		
		for(String serial: serialNameList){			
			String feedURL=util.getPropertyValue(serial);
			videoFeedReader(feedURL,serial);	
		}	
		
		System.out.println("End TIME: "+new Date());
		//System.out.println("END of MAIN");
	
	}	
	
	

	
	public static String currentDate(){
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate=dateFormat.format(date);
		return strDate;
	}
	
	
}