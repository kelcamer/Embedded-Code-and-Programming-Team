/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  SingletonDM.java
 * Date: April 11, 2016
 */
import java.util.*;

public class SingletonDM 
{
	//Here, put the specific private static variable that makes this class follow the singleton pattern.
	private static SingletonDM thisclass = null;
	static boolean firstThread = true;
	//The array of randomized character sheets.
	//Feel free to hardcode a few of these for your testing.
	private PlayerCharacter sheets[] = 
	{
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(),
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter()
	};
	
	//The sheets array remade as a list for convenience.
	private LinkedList<PlayerCharacter> sheetList = new LinkedList<PlayerCharacter>(Arrays.asList(sheets));
	
	//Here, put the specific kind of constructor that makes this class follow the singleton pattern.
	private SingletonDM() {};
	public static SingletonDM getInstance() throws InterruptedException
	{
		//This will act as the "true" constructor for this class.
		//Its details should include but not be limited to the following:
		//		- Check if this is the first thread.
		//		- Check the private static variable at the top of the class.
		//		- Have the "synchronized" key word in there somewhere.
		//		- Return some kind of SingletonDM
		if(thisclass == null){
			if(firstThread){
				firstThread = false;
				Thread.currentThread();
				Thread.sleep(1000);
			}
			thisclass = new SingletonDM();
		}
		return thisclass;
	}
	
	public String GetNameAndPID()
	{
		return( "Cameron, Kelsey, k3593775");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
	
	public LinkedList<PlayerCharacter> getSheetList()
	{
		//Pretty self-explanatory.
		
		return sheetList;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfLevel(int level)
	{
		LinkedList<PlayerCharacter> returnthis = new LinkedList<PlayerCharacter>();
		for(int x = 0; x < sheetList.size(); x++){
			if(sheetList.get(x).getLevel() == level){
				returnthis.add(sheetList.get(x));
			}
		}
		
		//This should find all characters of a certain level in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
		
		return returnthis;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfType(String type)
	{

		LinkedList<PlayerCharacter> returnthis = new LinkedList<PlayerCharacter>();
		for(int x = 0; x < sheetList.size(); x++){
			if(sheetList.get(x).gettype().equals(type)){
				returnthis.add(sheetList.get(x));
			}
		}
		return returnthis;
		//This should find all characters of a certain type in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
	}
}
