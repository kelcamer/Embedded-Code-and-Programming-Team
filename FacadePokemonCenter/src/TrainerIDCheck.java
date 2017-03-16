/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FacadePokemonCenter.java
 * Date: April 1, 2016
 */
public class TrainerIDCheck {
	
	private int trainerID = 01301;
	//Please do not change this value.
	//I will be using this ID to test your code. 
	
	public int getTrainerID()
	{
		return trainerID;
		//This seems self-explanatory.
	}
	
	public boolean trainerActive(int trainerIDToCheck)
	{
		if(trainerIDToCheck == this.trainerID){
			return true;
		}
		return false;
		//This function will check the given value against trainerID.
		//Return true if they are the same, false if they are not.
	}
	
		

}