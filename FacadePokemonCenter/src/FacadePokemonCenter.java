/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FacadePokemonCenter.java
 * Date: April 1, 2016
 */
public class FacadePokemonCenter 
{
	//Declare a private int and a private String up here for the trainer's ID and name.
	private int id;
	private String name;
	TrainerIDCheck checkTrainer;
	TrainerNameCheck checkName;
	HealAndPC heal;
	Welcome wel;
	//Also, be sure to declare variables of the following type:
	//		- TrainerIDCheck
	//		- TrainerNameCheck
	//		- HealAndPC
	//		- Welcome
	//Don't instantiate them yet - that can happen in the constructor.

	
	public FacadePokemonCenter(int newID, String newName)
	{
		//Here in the constructor, set the variables based on the input.
		this.name = newName;
		this.id = newID;
		//Also, instantiate the four classes here.
		this.wel = new Welcome();
		this.checkTrainer = new TrainerIDCheck();
		this.checkName = new TrainerNameCheck();
		this.heal = new HealAndPC();
		
	}
	
	public int getTrainerID()
	{
		return this.id;
		//Pretty self-explanatory.
	}
	
	public String getTrainerName()
	{
		return this.name;
		//Also self-explanatory.
	}
	
	public void healPokemon()
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//If every case is true, call the heal function in your instance of HealAndPC.
		//		In addition, print that the healing was successful.
		//Otherwise, do nothing, and say why the healing was not done.
		//Make sure you print out a statement on whether healing was successful or not!
		//This is crucial to how we will grade your assignment
		
		if(checkTrainer.trainerActive(this.id)){
			if(checkName.trainerActive(this.name)){
				if(heal.getPokemonTeam().size()>=1){
					heal.healPokemon();
					System.out.println("Healing was successful.");					
				}
				else{
					System.out.println("Sorry, you need at least one Pokemon in your party.");
				}
			}
			else{
				System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
			}
		}
		else{
			System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
		}
		
		
	}
	
	public void depositPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//		- Check if the team contains the given Pokemon.
		//If every case is true, call the deposit function in your instance of HealAndPC.
		//		In addition, print that the deposit was successful.
		//Otherwise, do nothing, and say why the deposit was not made.
		//Make sure you print out a statement on whether the deposit was successful or not!
		//This is crucial to how we will grade your assignment
		
		if(checkTrainer.trainerActive(this.id)){
			if(checkName.trainerActive(this.name)){
				if(heal.getPokemonTeam().size()>=1){
					if(dexNum < 722){
						if(heal.containsPokemon(dexNum)){
							heal.depositPokemon(dexNum);
							System.out.println("Successfully deposited your Pokemon!");
						}
						else{
							System.out.println("Sorry, this is not your Pokemon! You stole it from someone!");
						}
					}
					else{
						System.out.println("Sorry, this is not a valid Pokemon.");
					}
					
					
				}
				else{
					System.out.println("Sorry, you need at least one Pokemon in your party.");
				}
				
				}
			else{
				System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
			}
				}
		else{
			System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
		}
		
	}
	
	public void withdrawPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has space for another Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//If every case is true, call the withdrawal function in your instance of HealAndPC.
		//		In addition, print that the withdrawal was successful.
		//Otherwise, do nothing, and say why the withdrawal was not made.
		//Make sure you print out a statement on whether withdrawing was successful or not!
		//This is crucial to how we will grade your assignment
		if(checkTrainer.trainerActive(this.id)){
			if(checkName.trainerActive(this.name)){
				if(heal.getPokemonTeam().size()<6){
					if(dexNum < 722){
						heal.withdrawPokemon(dexNum);
						System.out.println("Yay, the withdrawal was successful!");
					}
					else{
						System.out.println("Sorry, this is not a valid Pokemon.");
					}
				}
				else{
					System.out.println("Sorry, but you do not have room for another Pokemon!");
				}
			}
			else{
				System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
			}
		}
		else{
			System.out.println("Sorry, you are not Ash Ketchum. Who are you, you crazy imposter?!");
		}
	
	
	}
	
	
	public void printTeamAndHealth()
	{
		//This should just call printTeamAndHealth() in your instance of HealAndPC.
		heal.printTeamAndHealth();
	}
	

}
