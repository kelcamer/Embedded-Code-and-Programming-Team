
/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FacadePokemonCenter.java
 * Date: April 1, 2016
 */public class TrainerNameCheck {
	
	private String trainerName = "Ash Ketchum";
	//Please do not change this value.
	//I will be using this name to test your code. 
	
	public String getTrainerName()
	{
		return trainerName;
		//This seems self-explanatory.
	}
	
	public boolean trainerActive(String trainerNameToCheck)
	{
		if(this.trainerName.equals(trainerNameToCheck)){
			return true;
		}
		return false;
		//This function will check the given string against trainerName.
		//Return true if they are the same, false if they are not.
		
		//Make sure you use .equals() !
	}
	
		

}
