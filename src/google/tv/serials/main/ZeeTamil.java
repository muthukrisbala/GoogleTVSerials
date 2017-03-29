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
import java.util.Date;
import java.util.List;

public class ZeeTamil {
	
	public static String introLV="<br>Lakshmi Vanthachu is an Tamil soap opera that airs on Zee Tamil. The show is launched on 2 February 2015 and airs Monday through Friday 9:30PM IST. The show stars Vani Bhojan in the lead female role and is directed by Suresh Krissna and V. Sathasivam"
			+ "<BR><h3>Plot</h3> Vetrivel, who lives in Pollachi, fallls in love with Lakshimi. Lakshimi too loves him. Vetri is from a rich family but has a bad past. 21 years before his sister committed suicide before his family members which made his mother to sit in wheelchair where she cannot able to speak, walk, or hear. His father who is Nattamai of his Village quits his job to look after his mother. He also has one elder brother who is still unmarried and two younger brothers where one is sent out of their house by his father as he was the main reason for his sister's suicide attempt. On the other hand, Lakshimi is a milk vendor who has three sisters and a mother. She is the only person who works hard for the family. At last, Vetri and Lakshimi decide to marry. On their marriage day, due to her mother's order, Lakshimi cannot come to the temple. After waiting for a long time for Lakshimi, Vetri decides to commit suicide as he cannot fetch a bad name to his family, who are eagerly waiting for the arrival of their house new daughter in law. Meanwhile in Chennai, Nandhini, a happy go lucky person, falls in love with Shakthi. Shakthi also loves her. They decide to marry. But Nandhini's greedy caretakers (her father's brother and his wife) fix her marriage with a rich guy for money. Nadhini decides to elope with Shakthi. She escapes from the marriage hall and waits for Shakthi. Shakthi comes there and slaps Nandhini saying bad things about her character and just now he came to know all the bad things about Nandhini. Actually this is done by Anushka, Shakthi's houseowner's daughter, who also loves Shakthi and wants him to separate him from Nandhini. Shakthi leaves Nandhini and goes in car. Depressed, Nandhini catches a bus and travels in that bus till its last stop. The bus is to Pollachi. She deboards at Pollachi. After thinking about Shakthi, she decides to commit suicide and goes to the top of a hill.There she meets Vetri who tries to commit to suicide. She stops him. Both of them narrates their tragic past to each other. After that Nandhini agrees to act like lakshimi and goes with Vetri to his house. She wins the heart of all people in the family."
			+ "<h3>Cast</h3> <ul><li>Vani Bhojan as Nandhini / Lakshmi Vetrivel and Jhansi </li>"+
			"<li> Sri Vidhya/ Shwetha Bandekar/as Lakshmi Santhosh</li>"+
			"<li> Saran Rajesh as Vetrivel</li>"+
			"<li> Vetrivel as Santhosh</li>"+
			"<li> Nathan Shyam as Shakthivel</li>"+
			"<li> Haripriya Vigneshkumar as Anushka Shaktivel</li>"+
			"<li> Krishna Kumar as Thangavel</li>"+
			"<li> Sri Kala as Thenmozhi Thangavel</li>"+
			"<li> Vijay Krishnaraj as Natamai Nachimuthu</li>"+
			"<li> Sulakshana as Valliammai</li>"+
			"<li> Devipriya as Kodeeshwari</li>"+
			"<li> Rajashekhar as Santhosh's father</li>"+
			"<li> Preethi Kumar as Chithra / Jennifer </li></ul>";
	
	public static String introMTK="<br>Mella Thiranthathu Kathavu is a Tamil Romance soap opera that airs on Zee Tamil. The show premiered on 2 November 2015 and airs Monday through Friday 8:00PM IST and 10:00PM IST. Starting from Monday 27 December 2016, the show was shifted to 7:00PM IST time Slot.[1][2][3] The Serial directed by Arul Raj Bramma G. Dev and producer by Divya Viswanathan"
			+ "<BR><h3>Cast:</h3> <ul><li>Ashwanth as Ashwanth (Santhosh & Maya Son)</li>"+
			"<li>Lisa as Anjali</li>"+
			"<li>Gayathri Yuvraaj as Manju/Selvi (Santhosh 1st Wife)</li>"+
			"<li>Venkat as Santhosh (Selvi & Maya Husband)</li>"+
			"<li>Anu as Maya (Santhosh 2nd Wife/Ashwanth Mother)</li></ol>";
	
	public static String introDD="<BR>Darling Darling is an Tamil language Comedy-Romance soap opera starring Ramji, V. J. Chitra, Nalini,Sri Vidhya Shankar, Monkey Ravi,Vasanth Gopinath and Nandhini. It will air on Zee Tamil every Monday and Friday at 22:00 IST starting from 12 December 2016. The show is an adaptation of &TV serial Bhabi Ji Ghar Par Hai!."
			+ "<BR> <h3> CAST</h3> <ol><li>Ramji as Venkatesh (Vicky)</li>"+
			"<li> V. J. Chitra as Anitha (Honey;Vicky's wife)</li>"+
			"<li> Vasanth Gopinath as Natarajan (Nattu)</li>"+
			"<li> Nandhini as Rukkumani (Rukku;Nattu's wife)</li>"+
			"<li> Sri Vidya Shankar as Vicky's mother</li>"+
			"<li> Nalini as Nattu's mother</li>"+
			"<li> Monkey Ravi as Gopi</li></ol>";
	
	public static String introLC="<BR>Sa Re Ga Ma Pa Lil Champs is an Indian-Tamil language musical reality TV game show, which premiered on 24 December 2016 and on every Saturday and Sunday at 7:00PM IST on Zee Tamil. It is based on the Hindi Language show Sa Re Ga Ma Pa L'il Champs The series is meant for young children between the ages of 5-14 years, which judges the prodigious children on the basis of their voice quality, singing talent and versatility in performance"
			+ "<BR><h3>Host:</h3> Archana <BR> <h3>Main Judges</h3><ol><li>Karthik</li> <li>Vijay Prakash</li><li> Sujatha Mohan</li></ol>";
	public static void main(String args[]) throws SQLException {
		
		//FeedReaderRajT.getUrlList();
		
	
		
		System.out.println("Start of Zee: "+new Date());
		
		

		
		

		getUrlList("iniya-iru-malargal/video/","http://www.ozee.com/shows/iniya-iru-malargal");
		getUrlList("annakodiyum-ainthupengalum/video/","http://www.ozee.com/shows/annakodiyum-ainthupengalum");
		getUrlList("mella-thiranthathu-kathavu/video/","http://www.ozee.com/shows/mella-thiranthathu-kathavu");
		getUrlList("lakshmi-vanthachu/video/","http://www.ozee.com/shows/lakshmi-vanthachu");
		
		
		
		getUrlList("junior-superstars/video/","http://www.ozee.com/shows/junior-superstars");
		getUrlList("athirshta-lakshmi/video/","http://www.ozee.com/shows/athirshta-lakshmi");
		getUrlList("genes-2/video/","http://www.ozee.com/shows/genes-2");
		getUrlList("cid/video/","http://www.ozee.com/shows/cid");
		getUrlList("thalayanai-pookal/video/","http://www.ozee.com/shows/thalayanai-pookal");
		getUrlList("naga-rani/video/","http://www.ozee.com/shows/naga-rani");
		getUrlList("mr-and-mrs-khiladis/video/","http://www.ozee.com/shows/mr-and-mrs-khiladis");		
		getUrlList("dance-jodi-dance/video/","http://www.ozee.com/shows/dance-jodi-dance");
		getUrlList("solvathellam-unmai-season-2/video/","http://www.ozee.com/shows/solvathellam-unmai-season-2");
		
		getUrlList("priyasakhi/video/","http://www.ozee.com/shows/priyasakhi");
		getUrlList("mahamayi/video/","http://www.ozee.com/shows/mahamayi");
		getUrlList("anjarai-petti/video/","http://www.ozee.com/shows/anjarai-petti");
		getUrlList("darling-darling/video/","http://www.ozee.com/shows/darling-darling");
		getUrlList("dancing-khilladis/video/","http://www.ozee.com/shows/dancing-khilladis");
		getUrlList("sa-re-ga-ma-pa-lil-champs-tamil/video/","http://www.ozee.com/shows/sa-re-ga-ma-pa-lil-champs-tamil");
		getUrlList("zeetamil-promos/video/","http://www.ozee.com/shows/zeetamil-promos");
		getUrlList("junior-senior/video/","http://www.ozee.com/shows/junior-senior");
		getUrlList("simply-khushbu/video/","http://www.ozee.com/shows/simply-khushbu");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		getUrlList("tashan-e-ishq/video/","http://www.ozee.com/shows/tashan-e-ishq");
		getUrlList("jamai-raja/video/","http://www.ozee.com/shows/jamai-raja");
		getUrlList("ek-tha-raja-ek-thi-rani/video/","http://www.ozee.com/shows/jamai-raja");
		getUrlList("kumkum-bhagya/video/","http://www.ozee.com/shows/kumkum-bhagya");
		
		getUrlList("kaala-teeka/video/","http://www.ozee.com/shows/kaala-teeka");
		getUrlList("yeh-vaada-raha/video/","http://www.ozee.com/shows/yeh-vaada-raha");
		getUrlList("vishkanya/video/","http://www.ozee.com/shows/vishkanya");
		getUrlList("sarojini/video/","http://www.ozee.com/shows/sarojini");
		getUrlList("meri-saasu-maa/video/","http://www.ozee.com/shows/meri-saasu-maa");
		
		
		//http://www.ozee.com/shows/gangaa
			
		
		//Kannada Serials
		getUrlList("punar-vivaha/video/","http://www.ozee.com/shows/punar-vivaha");
		getUrlList("shrirasthu-shubhamasthu/video/","http://www.ozee.com/shows/shrirasthu-shubhamasthu");
		getUrlList("shrimaan-shrimathi/video/","http://www.ozee.com/shows/shrimaan-shrimathi");
		getUrlList("mahadevi/video/","http://www.ozee.com/shows/mahadevi");
		
		
		
		System.out.println("End of Zee: "+new Date());
			
		
	}
	
	
	public static void getUrlList(String serialName,String urlString) throws SQLException{
		URL url;

		 //Connection connection=DatabaseManager.getConnection();
		int count=0;
		int countHindi=0;
		int countTelugu=0;
		int countKanada=0;
		String sName=google.tv.serials.util.util.getSerialName(serialName);
		
		String intro="";

		try {
			// get URL content
			String title="";
			
			String prefix="http://www.ozee.com/shows/";
			String dataStr="";
			String imageURL="";
			
			url = new URL(urlString);
			URLConnection conn = url.openConnection();
			
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine;
			String channel="";

			

				int startIndex=0;
				int endIndex=0;
		
			
				String urlStr="";
				String showName="";
				
				if(serialName.equals("iniya-iru-malargal/video/") ||serialName.equals("annakodiyum-ainthupengalum/video/") || 
						serialName.equals("mella-thiranthathu-kathavu/video/") || serialName.equals("lakshmi-vanthachu/video/")
						||serialName.equals("junior-superstars/video/") || serialName.equals("athirshta-lakshmi/video/") ||  serialName.equals("genes-2/video/") || 
						serialName.equals("cid/video/") || serialName.equals("thalayanai-pookal/video/") || serialName.equals("naga-rani/video/") || serialName.equals("mr-and-mrs-khiladis/video/") || 
						serialName.equals("dance-jodi-dance/video/") || serialName.equals("solvathellam-unmai-season-2/video/") || serialName.equals("priyasakhi/video/") ||
						serialName.equals("mahamayi/video/") || serialName.equals("anjarai-petti/video/")|| serialName.equals("darling-darling/video/") ||
						serialName.equals("dancing-khilladis/video/")|| serialName.equals("sa-re-ga-ma-pa-lil-champs-tamil/video/")||
						serialName.equals("zeetamil-promos/video/")|| serialName.equals("junior-senior/video/") || serialName.equals("simply-khushbu/video/")){
					 channel="zee tamil";
					
				}else if ((serialName.equals("tashan-e-ishq/video/") || serialName.equals("jamai-raja/video/") ||
						serialName.equals("kumkum-bhagya/video/") || serialName.equals("kaala-teeka/video/") || serialName.equals("yeh-vaada-raha/video/")
						||serialName.equals("vishkanya/video/")  || serialName.equals("sarojini/video/") || serialName.equals("meri-saasu-maa/video/"))){
					channel="zee hindi";
				}else if (serialName.equals("punar-vivaha/video/") || serialName.equals("shrimaan-shrimathi/video/") || serialName.equals("shrirasthu-shubhamasthu/video/")
						|| serialName.equals("mahadevi/video/")){
					channel="zee kannada";
				}
				
				if(serialName.equals("iniya-iru-malargal/video/")){				
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Iniya-Iru-Malargal-Zee-Tamil.jpg\"\\>";
					showName="Iniya iru malargal";
				}else if (serialName.equals("annakodiyum-ainthupengalum/video/")){				
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Annakodiyum-5-Pengalum.jpg\"\\>";
					showName="Annakodiyum ainthupengalum";
				}else if (serialName.equals("mella-thiranthathu-kathavu/video/")){				
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Mella-Thiranthathu-Kathavu-Zee-Tamil.jpg\"\\>";
					showName="Mella thiranthathu kathavu";
					intro=introMTK;
				}else if (serialName.equals("lakshmi-vanthachu/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Lakshmi_Vanthachu.jpg\"\\>";
					showName="lakshmi vanthachu";
					intro=introLV;
					
				}else if (serialName.equals("solvathellam-unmai-season-2/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Solvathellam-Unmai-2016-New.jpg\"\\>";
					showName="solvathellam unmai";
					
				}else if (serialName.equals("anjarai-petti/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Anjarai-petti.jpg\"\\>";
					showName="anjarai petti";
					
				}else if (serialName.equals("athirshta-lakshmi/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/Athirshta_Lakshmi.jpg\"\\>";
					showName="athirshta-lakshmi";	
					
				}else if (serialName.equals("darling-darling/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/darling-darling.jpg\"\\>";
					showName="darling-darling";
					intro=introDD;
					
				}else if (serialName.equals("dancing-khilladis/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/dancing-khilladis.jpg\"\\>";
					showName="dancing-khilladis";
					
				}else if (serialName.equals("sa-re-ga-ma-pa-lil-champs-tamil/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/sa-re-ga-ma-pa-lil-champs-tamil.jpg\"\\>";
					showName="sa-re-ga-ma-pa-lil-champs-tamil";
					intro=introLC;
					
				}else if (serialName.equals("zeetamil-promos/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/zeetamil-promos.jpg\"\\>";
					showName="zeetamil-promos";
					
				}else if (serialName.equals("junior-senior/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/junior-senior.jpg\"\\>";
					showName="junior-senior";
					
				}else if (serialName.equals("simply-khushbu/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/09/simply-khushbu.jpg\"\\>";
					showName="simply-khushbu";
					
					
					
					
				}else if (serialName.equals("thalayanai-pookal/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Thalayanai_Pookal.jpg\"\\>";
					showName="thalayanai pookal";
					
				}else if (serialName.equals("naga-rani/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Nagarani.jpg\"\\>";
					showName="naga rani";
					
				}else if (serialName.equals("mahamayi/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/11/Mahamayi.jpg\"\\>";
					showName="mahamayi";
				
				}else if (serialName.equals("tashan-e-ishq/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/tashan-e-ishq.jpg\"\\>";
					showName="tashan e ishq";
				}else if (serialName.equals("jamai-raja/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Jamai_Raja.jpg\"\\>";
					showName="jamai raja";
				
				}else if (serialName.equals("kumkum-bhagya/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/kumkum-bhagya.jpg\"\\>";
					showName="kumkum bhagya";
				}else if (serialName.equals("kaala-teeka/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Kaala_Teeka.jpg\"\\>";
					showName="kaala teeka";
				}else if (serialName.equals("yeh-vaada-raha/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Yeh_Vaada_Raha.jpg\"\\>";
					showName="yeh vaada raha";
				}else if (serialName.equals("vishkanya/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Vishkanya.jpg\"\\>";
					showName="vishkanya";
				}else if (serialName.equals("sarojini/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Sarojini.jpg\"\\>";
					showName="sarojini";
				}else if (serialName.equals("meri-saasu-maa/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/meri-saasu-maa.jpg\"\\>";
					showName="meri saasu maa";
				}
				
				
				
				
				//Kannada Serials
				else if (serialName.equals("punar-vivaha/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/punarvivaha.jpg\"\\>";
					showName="punar vivaha";
				}else if (serialName.equals("shrimaan-shrimathi/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/shrimaan-shrimathi.jpg\"\\>";
					showName="shrimaan shrimathi";
				}else if (serialName.equals("shrirasthu-shubhamasthu/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/shrirasthu-shubhamasthu.jpg\"\\>";
					showName="shrirasthu shubhamasthu";
				}else if (serialName.equals("mahadevi/video/")){					
					imageURL="<img src=\"http://www.onlinetamilportal.com/wp-content/uploads/2016/05/Mahadevi.jpg\"\\>";
					showName="mahadevi";
				}
				
				
				
				
				
				
				
			while ((inputLine = br.readLine()) != null) {
				
				
				

				//if(inputLine.startsWith("<div class=\"cover\">")){
				 //if(inputLine.startsWith("<div class=\"post-thumb\">")){
					//System.out.println("working");
					startIndex=inputLine.indexOf("<a href=\"");
					endIndex=inputLine.indexOf("\" title=");
					
					endIndex=inputLine.indexOf("class=\"clearfix\">");
					//System.out.println(startIndex+":"+endIndex);
					if(startIndex>0 && endIndex>0){
					urlStr=inputLine.substring(startIndex+9, endIndex-2);
					
					if(urlStr.startsWith(serialName)){
						count++;
						countHindi++;
						countTelugu++;
						countKanada++;
						title=urlStr.replace(serialName, "");
						title=title.substring(0,title.length()-5);
						title=title.replace("-", " ");
						urlStr=prefix+urlStr;
						//System.out.println("Title: "+title);
						
						dataStr="<a href=\""+urlStr+"\" target=\"_blank\">"+title+"</a>";
						//System.out.println("URL: "+dataStr);
						//System.out.println(count);
					}
					
					if(channel.equalsIgnoreCase("zee tamil")){ 
						
					boolean flagOTP=false;
					if(count<=2){
						System.out.println("title: "+title);
					
					flagOTP=DatabaseManager.postlookup(title,"onlinetamilportal");
					if(!flagOTP)
						Wpost.main(sName,channel,title, imageURL+intro+"<BR><BR><b>Click below link to view "+showName+" </b><BR><h3>"+dataStr+"</h3>","",showName+","+channel+","+title+"","onlinetamilportal","");
					}
						}
					 if(channel.equalsIgnoreCase("zee hindi")){ 
						 boolean flagOHP=false;
							if(countHindi<=2){
								System.out.println("title: "+title);
						 flagOHP=DatabaseManager.postlookup(title,"onlinehindiportal");
						 serialName=serialName.replace("/video/", "");
						 serialName=serialName.replace("-"," ");
						if(!flagOHP)
							Wpost.main(serialName,channel,title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlinetamilportal","");
					
							}
				//}
					}else if(channel.equalsIgnoreCase("zee telugu")){
						boolean flagOTEP=false;
						if(countTelugu<=2){
							 flagOTEP=DatabaseManager.postlookup(title,"onlineteluguportal");
							 serialName=serialName.replace("/video/", "");
							 serialName=serialName.replace("-"," ");
							if(!flagOTEP)
								Wpost.main(serialName,channel,title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlinetamilportal","");
					
						}
				//}
					}else if(channel.equalsIgnoreCase("zee kanada")){
						boolean flagOKAP=false;
						if(countKanada<=2){
					
							flagOKAP=DatabaseManager.postlookup(title,"onlinekanadaportal");
							 serialName=serialName.replace("/video/", "");
							 serialName=serialName.replace("-"," ");
							if(!flagOKAP)
								Wpost.main(serialName,channel,title, imageURL+"<BR><BR><b>Click below link to view the serial</b><BR>"+dataStr,"",channel+","+title+"","onlinetamilportal","");
					
						}
				//}
					}
					
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
}