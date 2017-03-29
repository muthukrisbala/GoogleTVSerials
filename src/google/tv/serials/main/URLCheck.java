package google.tv.serials.main;

import google.tv.serials.database.DatabaseManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class URLCheck {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		List<String> urlList=new ArrayList<String>();
		urlList=DatabaseManager.selectURLTest();
		System.out.println("Size:: "+urlList.size());
		String resultStr="";
		for(String url:urlList){
			resultStr+=checkURLStatus(url);
		}
		
		File f =new File("E:\\OnlineTamilPortal\\Testing\\URLStatus.txt");
		
		if (!f.exists()) {
            f.createNewFile();
         }
	    FileWriter fw =new FileWriter(f.getAbsoluteFile());
	    BufferedWriter bw =new BufferedWriter(fw);
	    bw.write(resultStr);
	    bw.close();
	    fw.close();
	}
	
	
	public static String checkURLStatus(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		String str="";
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		int statusCode = http.getResponseCode();
		str=urlStr+" EOD "+statusCode;
		//System.out.println(urlStr+"-"+statusCode);
		return str;
	}
}
