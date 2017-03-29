package google.tv.serials.main;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class Scheduler {
	public static void main(String args[]) throws InterruptedException, SQLException {

		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
		time.schedule(st, 0, 3600000); // Create Repetitively task for every 1 hour
		
		
		
	}
	
}




 class ScheduledTask extends TimerTask {

	Date now; // to display current time

	// Add your task here
	public void run() {
		now = new Date(); // initialize date
		String output="";
		System.out.println("START TIME: "+new Date());
		try {
			//ZeeTamil.main("");
			//FeedReader.main("");
			output="Started Script by "+new Date()+"\n";
			FeedReaderRajT.getUrlList();
			output+="Completed FeedReadrRajT by "+new Date()+"\n";
			FeedReader.main("");
			output+="Completed FeedReadr by "+new Date()+"\n";
			ZeeTamil.main("");
			output+="Completed ZeeTamil by "+new Date()+"\n";
			SendMailTLS.main(output);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End TIME: "+new Date());
	}
}