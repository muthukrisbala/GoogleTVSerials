package google.tv.serials.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SaveImageFromUrl {

	public static String getImagePath(String imgUrl,String imageName,String type) throws Exception {
		
		String orgFileName = "";
		
		if(type.equalsIgnoreCase("web")){
			orgFileName="C:/xampp/tomcat/webapps/OTP/image/Orginal/"+imageName;
		}else if(type.equalsIgnoreCase("mobile")){
			orgFileName="C:/xampp/tomcat/webapps/MOTP/image/Orginal/"+imageName;
		}

		saveImage(imgUrl, orgFileName);
		
		
		
		return orgFileName;
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

}