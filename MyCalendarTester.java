

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * The intent of this class is to test the functionality of the Calendar program.
 * @author Ashraf Saber
 * Version: 1
 */
public class MyCalendarTester {

	/**
	 * Main function to test the Calendar Program
	 */
	public static void main(String[] args){

		// Creating a calendar object myCalendar		
		CalendarClass myCalendar = new CalendarClass();
		// Creating a scanner object inputScanner to read user input
		Scanner inputScanner = new Scanner(System.in);
		// Declaring object of type String for userInput
		String userInput; 
		// Creating a GregorianCalendar object calendar 
		GregorianCalendar calendar = new GregorianCalendar(); 

		// Printing the Calendar month view
		myCalendar.printMonth(calendar);

		// Creating a do-While loop to provide the user with the required navigation options
		do{
			System.out.println("Select one of the following options: ");
			System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");  
			userInput = inputScanner.nextLine().toUpperCase();

			switch(userInput){
			case "L":  	System.out.println(" ");
						myCalendar.load();
						break;
			case "V":  System.out.println("[D]ay view or [M]view ? ");
						String viewInput = inputScanner.nextLine();
						if(viewInput.equals("D")|| viewInput.equals("d")){
							String dNavigation;
								myCalendar.printDay(calendar);  
								do{   
								System.out.println("[P]revious or [N]ext or [M]ain menu ?");
								 dNavigation = inputScanner.nextLine();
								if(dNavigation.equals("P")||dNavigation.equals("p")){myCalendar.previousDay(calendar);}
								else if(dNavigation.equals("N")||dNavigation.equals("n")){myCalendar.nextDay(calendar);}
							} while(! (dNavigation.equals("M") ||  dNavigation.equals("m")));

						}else if(viewInput.equals("M") ||viewInput.equals("m")){
							myCalendar.printMonth(calendar); 
							String mNavigation;
							do{ 
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
							 mNavigation = inputScanner.nextLine();
							if(mNavigation.equals("P")||mNavigation.equals("p")){ myCalendar.previousMonth(calendar); }
							else if(mNavigation.equals("N")||mNavigation.equals("n")){myCalendar.nextMonth(calendar);}
							} while(! (mNavigation.equals("M") ||  mNavigation.equals("m")));
						}
						break;			
			case "C":  	
					   System.out.println("\nTitle: ");
					   String titleInput = inputScanner.nextLine();
					   System.out.println("\nDate: ");
					   String dateInput = inputScanner.nextLine();
					   System.out.println("\nStart Time (24:00 format):");
					   String startTInput = inputScanner.nextLine();
					   System.out.println("\nEnd Time (24:00 format): ");
					   String endTInput = inputScanner.nextLine();
					   if((endTInput.length()==0 || endTInput==null)){endTInput="23:59";}
						myCalendar.createEvent(titleInput, dateInput, startTInput, endTInput);
						break;
			case "G":  System.out.println("\nPlease enter date (MM/DD/YYYY): ");
					   String goToInput = inputScanner.nextLine();
					   System.out.print("\n");
					   myCalendar.printDay(myCalendar.stringToGC(goToInput));
					   break;
			case "E":  System.out.println(" ");
					   myCalendar.printAllEvents();
					   System.out.println(" ");
					   break;			
			case "D":  System.out.println("[S]elected or [A]ll ? ");
					   String deleteInput = inputScanner.nextLine();
					   if(deleteInput.equals("s") || deleteInput.equals("S") ){
						    System.out.println("\nPlease enter date (MM/DD/YYYY):");
					   		String dateDInput = inputScanner.nextLine();
							myCalendar.deleteSelected(dateDInput); 
					   }else if(deleteInput.equals("A")||deleteInput.equals("a"))
						    myCalendar.deleteAll();
					   break;
			}

		}while(!userInput.equals("Q"));
		myCalendar.quit();
	}
}
