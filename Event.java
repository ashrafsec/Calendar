

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * The intent of the class in to capture the attributes of an Event as well as to provide 
 * the setters, getters and required methods that will be called in the CalendarClass.
 * @author Ashraf Saber
 * Version: 1
 */
public class Event implements Comparable<Event> {
	public String title;
	public String inputDate;
	public String startTime; 
	public String endTime;
	public Date userInputStringToDate;

	/**
	 * Constructor for Event Class
	 */
	public Event(String title,String inputDate,String startTime,String endTime)
	{
		this.title=title;
		this.inputDate=inputDate;	
		this.startTime=startTime;
		this.endTime=endTime;		 
		dateObjectConverter();
	}

	/**
	 * Setter for title
	 * @param title: title of the created event
	 */
	public void setTitle(String title){this.title=title;}
	/**
	 * Setter for date
	 * @param inputDate: date of the event
	 */
	public void setInputDate(String inputDate){this.inputDate=inputDate;}
	/**
	 * Setter for start time
	 * @param startTime: the start time of the event
	 */
	public void setStartTime(String startTime){this.startTime=startTime;}
	/**
	 * Setter for end time
	 * @param endTime the end time of the event
	 */
	public void setEndTime(String endTime){this.endTime=endTime;}
	/**
	 * Setter for the date object of the user input date
	 * @param date: date object for the date of the event
	 */
	public void setUserInputStringToDate(String date){inputDate=date;dateObjectConverter();}


	/**
	 * Getter for title
	 * @return title
	 */
	public String getTitle(){return title;}
	/**
	 * Getter for input date
	 * @return inputDate
	 */
	public String getInputDate(){return inputDate;}
	/**
	 * Getter for start time
	 * @return startTime
	 */
	public String getStartTime(){return startTime;}
	/**
	 * Getter for end time
	 * @return endTime
	 */
	public String getEndTime(){return endTime;}
	/**
	 * Getter for date object of event date value
	 * @return userInputStringToDate
	 */
	public Date getUserInputStringToDate(){return userInputStringToDate;}
	
	/**
	 * Function to return a string capturing the event and its attributes
	 * @return s
	 */
	public String toString()
	{
		String s = this.title + " " + this.inputDate + " " + this.startTime;
		if (!endTime.equals(null))
			s += " " + this.endTime;
		return s;
	}

	/**
	 * Function to convert String input date to date object
	 * and parse it into userInputStringToDate
	 */
	public void dateObjectConverter()
	{
		String concatenated = this.inputDate;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");  
		try {
			userInputStringToDate = dateFormatter.parse(concatenated);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Equals to function
	 */
	public boolean equals(Object other){
		Event that = (Event) other;  
		return this.compareTo(that) == 0; 
	}
	
	/**
	 * Compare to function for sorting the events
	 */
	public int compareTo(Event other){
		//comparing by date  
		int dateCmp = this.userInputStringToDate.compareTo(other.userInputStringToDate);
		if(dateCmp != 0)
			return dateCmp;
		
		//compare by Start Time
		// retrieve stringStartTime -> convert to int -> compare
		String thisTimeString="",otherTimeString="";
		String thisTime= this.getStartTime();
		String otherTime= other.getStartTime();	
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");   
		try{
			Date thisTimeHHmm= timeFormatter.parse(thisTime);
			Date otherTimeHHmm= timeFormatter.parse(otherTime);
			
			thisTimeString =timeFormatter.format(thisTimeHHmm);
			otherTimeString =timeFormatter.format(otherTimeHHmm);
			
		}catch(ParseException e){e.printStackTrace();}
		
		int timeCmp = thisTimeString.compareTo(otherTimeString);
		if(timeCmp!=0)
			return timeCmp;

		return 0;
	}
}	
