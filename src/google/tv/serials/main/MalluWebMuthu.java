package google.tv.serials.main;



import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


 
public class MalluWebMuthu implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException  {
 
		System.out.println("START:::");	
 
		try {
			//IpChecker.main();
			
			//FeedReader.main("dummy");
			//StemapGenerator.main("dummy");
			//FeedReaderRajT.getUrlList();
			//FeedReaderStar.main("dummy");
			FeedReaderMalay.main("dummy");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws SQLException{
		//FeedReader.main("dummy");
		//StemapGenerator.main("dummy");
		//FeedReaderRajT.getUrlList();
		//FeedReaderStar.main("dummy");
		FeedReaderMalay.main("dummy");
	}
 
}