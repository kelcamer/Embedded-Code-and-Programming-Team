/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FacadePokemonCenter.java
 * Date: April 1, 2016
 */
import java.util.*;


public class HealAndPC 
{
	//This ArrayList will represent the Pokemon the trainer is currently carrying.
	//For the ease of coding, we will assume that the trainer has, indeed, caught them all.
	//We will also assume that the trainer has an infinite number of Pokemon in the PC.
	//This makes coding easier by assuming Pokemon are a commodity.
	//This also foregoes the process of keeping track of Pokemon stored on the PC.
	private ArrayList<Pokemon> pokemonTeam;
	
	//Sets up the trainer's team.
	public HealAndPC()
	{
		pokemonTeam = new ArrayList<Pokemon>();
		pokemonTeam.add(new Pokemon(25));
		pokemonTeam.add(new Pokemon(3));
		pokemonTeam.add(new Pokemon(6));
		pokemonTeam.add(new Pokemon(9));
		pokemonTeam.add(new Pokemon(131));
		pokemonTeam.add(new Pokemon(143));
	}
	
	public ArrayList getPokemonTeam()
	{
		return this.pokemonTeam;
		//This is pretty self-explanatory.
	}
	
	public void healPokemon()
	{
		for(Pokemon p: this.pokemonTeam){
			p.heal();
		}
		//Heals every Pokemon in the trainer's team.
	}

	public void depositPokemon(int dexNum)
	{
		//Finds the first Pokemon with the given Pokedex number in the team and removes it from the team.
		int x = 0;
		int num = 0;
		for(Pokemon p: this.pokemonTeam){
			if(p.getPokedexNumber() == dexNum){
				num = x;
			}
			x++;
		}
		this.pokemonTeam.remove(num);
	}
	
	
	public void withdrawPokemon(int dexNum)
	{
		//Retrieves a Pokemon from the PC (that is, constructs a new Pokemon) and adds it to the team.
		this.pokemonTeam.add(new Pokemon(dexNum));
	}
	
	public boolean containsPokemon(int dexNum)
	{
	
		for(Pokemon p: this.pokemonTeam){
			if(p.getPokedexNumber() == dexNum){
				return true;
			}
		}
		return false;
		//Returns true iff the given Pokemon is in the trainer's team.
	}
	
	public boolean isTeamEmpty()
	{
		if(this.pokemonTeam.size()==0)	return true;
		return false;
		//Returns true iff the team size is 0.
	}
	
	public boolean isTeamFull()
	{
		if(this.pokemonTeam.size()==6)	return true;
		return false;
		//Returns true iff the team size is 6.
	}
	
	public void printTeamAndHealth()
	{
		//Another freebie.
		//Please don't change this!
		
		System.out.println("The current team is as follows: ");
		
		for(int i = 0; i < pokemonTeam.size(); i++)
		{
			Pokemon cur = pokemonTeam.get(i);
			System.out.println("Pokedex number: " + cur.getPokedexNumber() + "\n\tName: " + cur.getPokemonName() + "\n\tHealth: " + cur.getPercentHealth());
		}
	}

}
