package google.tv.serials.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class util {
	
	 public static String generateKeyword(String serialName){
		 String keyword="";
		 Date date=new Date();
		 SimpleDateFormat ft1 = new SimpleDateFormat ("dd-MM-yyyy");
		 SimpleDateFormat ft2 = new SimpleDateFormat ("MM-dd-yyyy");
		 SimpleDateFormat ft3 = new SimpleDateFormat ("dd-MM-yy");
		 SimpleDateFormat ft4 = new SimpleDateFormat ("MM-dd-yy");

		String date1= ft1.format(date);
		String date2= ft2.format(date);
		String date3= ft3.format(date);
		String date4= ft4.format(date);
		 keyword+=serialName+" "+date1+",";
		 keyword+=serialName+" "+date2+",";
		 keyword+=serialName+" "+date3+",";
		 keyword+=serialName+" "+date4+",";
		 
		 keyword+=date1+" "+serialName+",";
		 keyword+=date2+" "+serialName+",";
		 keyword+=date3+" "+serialName+",";
		 keyword+=date4+" "+serialName+",";
		 return keyword;
	 }
	 
	 public static String modifyVideoURLContent(String videoURL){
			videoURL=videoURL.replaceAll("width=\"480\"","width=\"640\"");
			videoURL=videoURL.replaceAll("height=\"360\"","height=\"480\"");
			return videoURL;
		
		}
	 
	 public static String getPropertyValue(String key) {
			
		 Properties prop = new Properties();
			InputStream input = null;
			String value="";

			try {
				//input = util.class.getResource("/config.properties");
				input = new FileInputStream("/home/muthukris82/scripts/res/config.properties");

				// load a properties file
				prop.load(input);
				
				value=prop.getProperty(key);

				// get the property value and print it out
				//System.out.println(value);
				

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return value;
		  }
	 
	 
	 public static java.sql.Date dateFomartForColor(String StringValu) throws ParseException{
		 //String StringValu="8th September 2015";
		 Date dateValue=null;
		 
		 List<String> monthValue=new ArrayList<String>();
		 java.sql.Date dateSQL=null;
		 
		 try{
		 
		 monthValue.add(0,"January");
		 monthValue.add(1,"Febuary");
		 monthValue.add(2,"March");
		 monthValue.add(3,"April");
		 monthValue.add(4,"May");
		 monthValue.add(5,"June");
		 monthValue.add(6,"July");
		 monthValue.add(7,"August");
		 monthValue.add(8,"September");
		 monthValue.add(9,"Octomber");
		 monthValue.add(10,"November");
		 monthValue.add(11,"December");
		 
		 for(int i=0;i<monthValue.size();i++){
			 if (StringValu.contains(monthValue.get(i))){
				 StringValu= StringValu.replace(monthValue.get(i), "-"+(i+1)+"-");
				 StringValu=StringValu.replace("th ", "");
				 StringValu=StringValu.trim();
			 }
		 }
		 
		 
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 Date date = dateFormat.parse(StringValu);
		 //date=dateFormat.format(date);
		 System.out.println(date);
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
	      dateSQL=new java.sql.Date(date.getTime());
	     System.out.println(dateSQL);
		 }catch(Exception e){}
		 
		 return dateSQL;
		 
	 }
	 
	 public static void main(String args[]) throws ParseException{
		 dateFomartForColor("abcd");
	 }
	 
	 
	 
	 public static String getSerialName(String title){
		 String serialName="";
		 
		 if(title.contains("Kalyanam-Mudhal") )
				 serialName="Kalyanam Mudhal Kadhal Varai";
		 else if (title.contains("Saravanan-Meenakshi") )
			 	serialName="Saravanan Meenakshi";
		 else if(title.contains("Deivam-Thandha"))
			 serialName="Deivam Thandha Veedu";
		 else if (title.contains("Sonthangal"))
			 serialName="Sonthangal";
		 
		 else if ( title.contains("Kairasi-kudumbam"))
			 serialName="Kairasi kudumbam";	
		 else if (title.contains("Kalyana-Kanavugal"))
			 serialName="Kalyana Kanavugal";
		else if (title.contains("Urave-Uyire")) 
			serialName="Urave Uyire";
		else if (title.contains("En-Kanmani"))
			 serialName="En Kanmani";
		else if (title.contains("Iru-Malargal"))
			serialName="Iru Malargal";
		else if (title.contains("En-Kanavan") )
			serialName="En Kanavan En Thozhan";
		else if (title.contains("Nandhavanam"))
			serialName="Nandhavanam";
		else if (title.contains("En-Anbu") )
			serialName="En Anbu Thangaiku";
		else if (title.contains("Moondru-Mudichu"))
			serialName="Moondru Mudichu";
		else if (title.contains("Adhu-Idhu-Edhu") )
			serialName="Adhu Idhu Edhu";
		else if (title.contains("Seethaiyin-Raaman"))
			serialName="Seethaiyin Raaman";
		else if (title.contains("Kings-of-Dance") )
			serialName="Kings of Dance";
		else if (title.contains("Neeya-Naana") )
			serialName="Neeya Naana";
		 				
		else if (title.contains("Oru-varthai-oru-Latcham"))
			serialName="Oru varthai oru Latcham";
		else if (title.contains("Kalakkapovadhu-Yaaru") )
			serialName="Kalakkapovadhu Yaaru";
		else if (title.contains("Connexions"))
			serialName="Connexions";
					
		else if (title.contains("Apoorva-Raagangal"))
			serialName="Apoorva Raagangal";
		else if (title.contains("Inai-Kodugal") )
			serialName="Inai Kodugal";
		else if (title.contains("Valli") )
			serialName="Valli";
		else if (title.contains("kalyana-Parisu"))
			serialName="kalyana Parisu";
		else if (title.contains("Ponnonjal") )
			serialName="Ponnonjal";
		else if (title.contains("Marakatha-Veenai"))
			serialName="Marakatha Veenai";
		else if (title.contains("Samayal-Samayal") )
			serialName="Samayal Samayal";
		else if (title.contains("Doctor-Doctor"))
			serialName="Doctor Doctor";
		else if (title.contains("Mouna-Ragam") )
			serialName="Mouna Ragam";
		else if (title.contains("Ullam-Kollai"))
			serialName="Ullam Kollai Poguthada";
							
		else if (title.contains("Chandralekha"))
			serialName="Chandralekha";
		else if (title.contains("Vani-Rani") )
			serialName="Vani Rani";
		else if (title.contains("Vamsam") )
			serialName="Vamsam";
		else if (title.contains("Deivamagal"))
			serialName="Deivamagal";
		else if (title.contains("Devathai"))
			serialName="Devathai";
		else if (title.contains("Bommalattam"))
			serialName="Bommalattam";
		else if (title.contains("Kula-Deivam"))
			serialName="Kula Deivam";
		else if (title.contains("EMI-Thavanai-Murai"))
			serialName="EMI Thavanai Murai Vazhkai";
		else if (title.contains("Pagal-Nilavu"))
			serialName="Pagal Nilavu";
		else if (title.contains("Neengalum-vellalam-oru-kodi"))
			serialName="Neengalum vellalam oru kodi";
		 
		else if (title.contains("iniya-iru-malargal/video/"))
			serialName="Iniya Iru Malargal";
		else if (title.contains("annakodiyum-ainthupengalum/video/"))
			serialName="Annakodiyum Ainthupengalum";
		else if (title.contains("mella-thiranthathu-kathavu/video/"))
			serialName="Mella Thiranthathu Kathavu";
		else if (title.contains("lakshmi-vanthachu/video/"))
			serialName="Lakshmi Vanthachu";
		 
		 return serialName;
		 
		 
	 }

}
