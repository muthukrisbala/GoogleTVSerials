package google.tv.serials.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SitemapUpdate {

	public static void main(String args,int count) {
		// TODO Auto-generated method stub
		
		String url="";
		String date="";
		String urlLink="";
		try{
		
		String fileStr="<?xml version=\"1.0\" encoding=\"UTF-8\"?><?xml-stylesheet type=\"text/xsl\" href=\"http://www.onlinetamilportal.com/css/sitemap.xsl\"?>"+
"<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"+	
"<url> <loc>http://www.onlinetamilportal.com</loc> <changefreq>always</changefreq> <priority>1.0</priority></url>";
		
		/*List<String> strList=new ArrayList<String>();
		strList=google.tv.serials.database.DatabaseManager.selectSerial_data_home("All");
	
		for(String str:strList){
			fileStr= fileStr+"<url> <loc>"+str+"</loc> <changefreq>hourly</changefreq> <priority>0.9</priority></url> ";
		}*/
		//fileStr=fileStr+"</urlset>";
		
		File f = new File("D:\\xampp_New\\tomcat\\webapps\\OTP\\sitemap-"+count+".xml");
		if (!f.exists()) {
            f.createNewFile();
         }
	    FileWriter fw =new FileWriter(f.getAbsoluteFile());
	    BufferedWriter bw =new BufferedWriter(fw);
	    bw.write(args);
	    bw.close();
	    fw.close();
	    
	    File f1 = new File("D:\\xampp_New\\tomcat-2\\webapps\\OTP\\sitemap-"+count+".xml");
		if (!f1.exists()) {
            f1.createNewFile();
         }
	    FileWriter fw1 =new FileWriter(f1.getAbsoluteFile());
	    BufferedWriter bw1 =new BufferedWriter(fw1);
	    bw1.write(args);
	    bw1.close();
	    fw.close();
		}catch(Exception e){e.printStackTrace();}
		
	}

}
