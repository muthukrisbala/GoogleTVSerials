package google.tv.serials.main;

import google.tv.serials.dataobject.SerialDatado;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StemapGenerator {

	public static void main(String args) {
		// TODO Auto-generated method stub
		
		String url="";
		String date="";
		String urlLink="";
		int count=0;
		String content="<?xml version=\"1.0\" encoding=\"UTF-8\"?><?xml-stylesheet type=\"text/xsl\" href=\"http://www.onlinetamilportal.com/css/sitemap.xsl\"?>"+
"<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"+	
"<url> <loc>http://www.onlinetamilportal.com</loc> <changefreq>always</changefreq> <priority>1.0</priority></url><url> <loc>http://www.onlinetamilportal.com/tamil.jsp</loc> <changefreq>always</changefreq> <priority>1.0</priority></url>"
+ "<url> <loc>http://www.onlinetamilportal.com/categoryPost.jsp?lang=tamil&amp;postType=serialAll</loc> <changefreq>always</changefreq> <priority>1.0</priority></url> "
+ "<url> <loc>http://www.onlinetamilportal.com/categoryPost.jsp?lang=tamil&amp;postType=newsAll</loc> <changefreq>always</changefreq> <priority>1.0</priority></url>"
+ "<url> <loc>http://www.onlinetamilportal.com/categoryPost.jsp?lang=tamil&amp;postType=cineAll</loc> <changefreq>always</changefreq> <priority>1.0</priority></url>";
		try{
		
		String fileStr="<?xml version=\"1.0\" encoding=\"UTF-8\"?><?xml-stylesheet type=\"text/xsl\" href=\"http://www.onlinetamilportal.com/css/sitemap.xsl\"?>"+
" <sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";	

		
		//List<String> strList=new ArrayList<String>();
		List<SerialDatado> list=new ArrayList<SerialDatado>();
		list=google.tv.serials.database.DatabaseManager.selectSerial_data_home("All");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
		Date date1 = new Date();
		String dateStr=dateFormat.format(date1);
		System.out.println(dateFormat.format(date1)); //2013/10/15 16:16:39
	
		for(SerialDatado item:list){
			content=content+"<url> <loc>"+item.getSerial_video_url()+"</loc><lastmod>"+item.getTimestamp()+"</lastmod> <changefreq>hourly</changefreq> <priority>0.9</priority></url> ";
			if(count % 200==0){
				fileStr= fileStr+"<sitemap> <loc> http://www.onlinetamilportal.com/sitemap-"+count+".xml</loc> </sitemap> ";
				content=content+"</urlset>";
				SitemapUpdate.main(content,count);
				content="<?xml version=\"1.0\" encoding=\"UTF-8\"?><?xml-stylesheet type=\"text/xsl\" href=\"http://www.onlinetamilportal.com/css/sitemap.xsl\"?>"+
"<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"+	
"<url> <loc>http://www.onlinetamilportal.com</loc> <lastmod>"+dateStr+"</lastmod><changefreq>always</changefreq> <priority>1.0</priority></url>";
			}
			
			count++;
		}
		fileStr=fileStr+"</sitemapindex>";
		
		File f = new File("D:\\xampp_New\\tomcat\\webapps\\OTP\\sitemap.xml");
		if (!f.exists()) {
            f.createNewFile();
         }
	    FileWriter fw =new FileWriter(f.getAbsoluteFile());
	    BufferedWriter bw =new BufferedWriter(fw);
	    bw.write(fileStr);
	    bw.close();
	    fw.close();
	    
	    File f1 = new File("D:\\xampp_New\\tomcat-2\\webapps\\OTP\\sitemap.xml");
		if (!f1.exists()) {
            f1.createNewFile();
         }
	    FileWriter fw1 =new FileWriter(f1.getAbsoluteFile());
	    BufferedWriter bw1 =new BufferedWriter(fw1);
	    bw1.write(fileStr);
	    bw1.close();
	    fw1.close();
		}catch(Exception e){e.printStackTrace();}
		
	}

}
