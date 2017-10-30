

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * The intent of this class is to hold all the methods used to manipulate and edit the Calendar and its Events.
 * @author Ashraf Saber
 * Version: 1
 */
public class CalendarClass extends GregorianCalendar{

	//instance variables
	static  TreeMap<GregorianCalendar, TreeSet<Event>> eventsMap;
	public static GregorianCalendar calendarN = new GregorianCalendar();
	private static Scanner fileScanner;
	String[] monthNames = {"January","February","March","April","May","June","July","August",
			"September","October","November","December"};
	String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

	/**
	 * Constructor for the Calendar Class
	 */
	public CalendarClass()
	{
		eventsMap = new TreeMap<>();
	}

	/**
	 * Returns the value of the following Month
	 * @return nextMonth
	 */  
	public String nextMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, 1);
		String nextMonth= printMonth(calendar);
		return nextMonth;
	}

	/**
	 * Returns the value of the previous Month
	 * @return previousMonth
	 */  
	public String previousMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, -1);
		String previousMonth= printMonth(calendar);
		return previousMonth;
	}	

	/**
	 * Prints the Month Display
	 * 
	 */      
	public String printMonth(GregorianCalendar calendar){

		int monthIndex= calendar.get(Calendar.MONTH); // retrieves the index of the month from 0-11
		int yearValue= calendar.get(Calendar.YEAR);   // retrieves the year value from the calendar parameter

		GregorianCalendar  calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1); 
		int dayIndex = calMonthStart.get(Calendar.DAY_OF_WEEK)-1; // 1st day of month index
		String d = dayNames[dayIndex]; // 1st day of month name
		int daysInMonth=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// getting the days in a month
		
		// Printing the Month value and the Year value
		System.out.println(monthNames[monthIndex]+"  "+ yearValue);
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");   

		for(int j=0; j<dayIndex;j++)  // j< first day of week
		{
			System.out.print("    ");  
		}
		for(int i=1; i<=daysInMonth; i++)  
		{	
			String dayS;
			if(i<10){dayS="0"+i;}else{dayS=String.valueOf(i);}
			int monthIndx= calendar.get(Calendar.MONTH)+1;
			String monthS;
			if( monthIndx<10){monthS="0"+monthIndx ;}else{monthS=String.valueOf( monthIndx);}
			String yearS= String.valueOf(calendar.get(Calendar.YEAR));
			String dateOfi= monthS+"/"+dayS+"/"+yearS;
			GregorianCalendar key=new GregorianCalendar();
			key= stringToGC(dateOfi);
			
			if(i == calendar.get(Calendar.DAY_OF_MONTH) &&  calendarN.get(Calendar.MONTH)==calendar.get(Calendar.MONTH))
				System.out.print("["+i+"] "); 
			
			
			else if(eventsMap.containsKey(key))
				System.out.print("{"+i+"} "); 
			else if(i<10)
				System.out.print(i+"   "); 
			else
				System.out.print(i+"  ");
			if( ((dayIndex+ i)%7==0) || (i==daysInMonth) ){System.out.println("\n");}
		}
		String x="";
		return x;
	} 

	/**
	 * Returns the value of the following Day
	 * @return nextDay
	 */  
	public String nextDay(GregorianCalendar calendar) {     	
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		String nextDay= printDay(calendar);
		return nextDay;
	}
	/**
	 * Returns the value of the previous Day
	 * @return previousDay
	 */  
	public String previousDay(GregorianCalendar calendar) {    
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		String previousDay= printDay(calendar);
		return previousDay;
	}    

	/**
	 * Prints the Day Display (TODAY)
	 * 
	 */      
	public String printDay(GregorianCalendar calendar){
		String today  = dayNames[calendar.get(Calendar.DAY_OF_WEEK)-1];
		String monthShort  = monthNames[calendar.get(Calendar.MONTH)];
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		System.out.println(today+", "+monthShort+" "+ dayOfMonth+", "+year);

		String calendarString = gcToString(calendar);
		getEventsOnThisDay(calendarString);
		
		String x="";
		return x;    	
	}

	/**
	 * Function to convert a String to a GregorianCalendar object
	 * @param string
	 * @return c
	 */
	public GregorianCalendar stringToGC(String string){
		
		Date dummyDate = null;
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try{
			dummyDate = dateFormatter.parse(string);
			c.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	/**
	 * Function to convert a GregorianCalendar to a String object
	 * @param calendar
	 * @return x
	 */
	public String gcToString(GregorianCalendar calendar){
		
		String dayS;
		int dayI=calendar.get(Calendar.DAY_OF_MONTH);
		if(dayI<10){dayS="0"+dayI;}
		else{dayS=String.valueOf(dayI);}

		String monthS;
		int monthI=calendar.get(Calendar.MONTH)+1;
		if(monthI<10){monthS="0"+monthI;}
		else{monthS=String.valueOf(monthI);}		
		
		String yearS;
		int yearI=calendar.get(Calendar.YEAR);
		yearS=String.valueOf(yearI);
		
		  String x=monthS+"/"+dayS+"/"+yearS;
		  return x;
	}

	/**
	 * Checks the eventsTree map and prints the treeSet associated with the key ()
	 * @return s
	 */
	public String getEventsOnThisDay(String date){ 
		String s = "";

		String title="",startT="",endT="";
		Date dummyDate = null;
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

		try{
			dummyDate = dateFormatter.parse(date);
			c.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		//  check the events map if that key (GC Object from string conversion) exists.	
		TreeSet<Event> events = new TreeSet<>();
		
		//printDay(c);
		if (eventsMap.containsKey(c))
		{
			events = eventsMap.get(c);
			if (!events.equals(null))
				for (Event e : events){
					//	s += e.toString() + "\n";
					title=e.getTitle();
					startT=e.getStartTime();
					endT=e.getEndTime();		
					System.out.println(title+" "+startT+" - "+endT+"\n");
				}
		}
		else 
			System.out.println("No Events Scheduled");

		return s;
	}

	/**
	 * Prints all events 
	 * @return s
	 */	
	public String printAllEvents(){
		String s = "";
		String nameOfDay="", monthLong="", startT="",endT="",title="";
		int dayOfMonth;
		for(GregorianCalendar key : eventsMap.keySet()){

			for (Event e : eventsMap.get(key)){
				nameOfDay=dayNames[key.get(Calendar.DAY_OF_WEEK)-1];
				monthLong=monthNames[key.get(Calendar.MONTH)];
				dayOfMonth=key.get(Calendar.DAY_OF_MONTH);
				startT = e.getStartTime().toString();
				endT = e.getEndTime().toString(); 
				title=e.getTitle();
				System.out.println(nameOfDay+" "+monthLong+" "+dayOfMonth+" "+startT+" - "+endT+" "+title);
			}
		}
		return s;
	}

	/**
	 * Deletes all events 
	 */	
	public void deleteAll(){
		eventsMap.clear();;
	}

	/**
	 * Deletes selected events 
	 */	
	public void deleteSelected(String dateStringToDelete){ 

		Date dummyDate = null;
		GregorianCalendar key = new GregorianCalendar() ;

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try{
			dummyDate= dateFormatter.parse(dateStringToDelete);
			key.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}

		eventsMap.remove(key);
	}

	/**
	 * Quits and saves events 
	 * @throws IOException
	 */	
	public  void quit(){ 
		String title="",date="",startT="",endT="";
		try{
			PrintWriter printWriter = new PrintWriter("events.txt");
			for(GregorianCalendar key : eventsMap.keySet()){

				for (Event e : eventsMap.get(key)){
					title=e.getTitle();
					date=e.getInputDate();
					startT=e.getStartTime();
					endT=e.getEndTime();
					printWriter.println(title+" "+date+" "+startT+" "+endT);
				}
			}
			printWriter.close();
			System.exit(0);
		}
		catch(IOException e){System.out.println("IOException");}
	}

	/**
	 * Loads events
	 */		
	public void load(){
	try{
			fileScanner = new Scanner(new File("events.txt"));

		}
		catch(Exception e){
			System.out.println("could not find file");
		}

		if(fileScanner.hasNext() != true)
		{
			System.out.println("This is the first run");	
		}else
		{     
			
			while(fileScanner.hasNext())
			{
				String titleReader = fileScanner.next(); 
				String dateReader = fileScanner.next(); // reading Date
				String startTimeReader = fileScanner.next(); // reading Start Time
				String endTimeReader = fileScanner.next(); // reading End Time
				createEvent(titleReader,dateReader,startTimeReader,endTimeReader);
			}
		}
		fileScanner.close();	
	}

	/**
	 * Creates an Event
	 * @param title
	 * @param inputDate
	 * @param startTime
	 * @param endTime
	 */
	public void createEvent(String title,String inputDate,String startTime,String endTime){
		// create an event object
		Event newEvent = new Event(title, inputDate, startTime, endTime);  
		// add event object to EventsMap 
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(newEvent.getUserInputStringToDate());

		if(!checkEventCollision(newEvent)){

			if(eventsMap.containsKey(c)){
				eventsMap.get(c).add(newEvent);  //accessing the tree set inside the tree map
			} else{
				TreeSet<Event> events = new TreeSet<>(); 
				events.add(newEvent);
				eventsMap.put(c, events);
			}

		} 
	}

	/**
	 * Checks event Collision
	 * @param event
	 * @return collision
	 */
	public  boolean checkEventCollision(Event event){  
		boolean collision=false;
		for(GregorianCalendar key : eventsMap.keySet()){
			
			if(key.getTime().equals(event.userInputStringToDate))
			{
				for(Event e : eventsMap.get(key)){
					if(e.startTime.equals(event.startTime) )
					{
						System.out.println("Collision!!");
						collision=true;
					}
				}
			}
		}
		return collision;
	}

}
