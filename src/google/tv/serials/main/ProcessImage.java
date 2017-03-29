package google.tv.serials.main;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProcessImage {

	public static  String process(String imageUrl,String fName,String type) throws Exception {
		// TODO Auto-generated method stub
		
		StringTokenizer st=new StringTokenizer(imageUrl, "/");
		List<String> imageStrList=new ArrayList<String>();
		
		
		while(st.hasMoreTokens()){
			imageStrList.add(st.nextToken());
		}
		String imageName=imageStrList.get(3)+".jpg";
		//System.out.println(imageStrList.get(3));
		
		
		String orginalFile=SaveImageFromUrl.getImagePath(imageUrl,fName,type);
		String destFile="";
		if(type.equalsIgnoreCase("web")){
			 destFile=ImageTest.scaleImg("C:/xampp/tomcat/webapps/OTP/image/Orginal/",fName,"web");
		}else if(type.equalsIgnoreCase("mobile")){
			 destFile=ImageTest.scaleImg("C:/xampp/tomcat/webapps/MOTP/image/Orginal/",fName,"mobile");
		}
		return destFile;
		
		

	}

}
