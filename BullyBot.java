/**
 * File:BullyBot.java
 * Author: Li Jingwei
 * Date: October 18,2015
 * Purpose: CSCI 1110, Assignment 4 Solution
 * 
 * Description: This class subclasses the ChickenBot class and implements
 * (Personality B) BullyBot.
 */

public class BullyBot extends ChickenBot
{	
	/**
	 * The constructor sets the bot's ID and initial amount of energy.
	 */
	public BullyBot( int id, int jolts) 
	{
		super(id, jolts);
	}

	/**
	 * The BullyBot creates an array of integers, each representing where it
	 * wishes to fire the ion cannon, and decreases its energy reserves by 1
	 * Jolt for each shot. The integers can be one NORTH, EAST, SOUTH, or 
	 * WEST.
	 * 
	 * returns: array of integers representing shots from the cannon.
	 */
	public int[] fireCannon()
	{
		// Initialize the shots number and the array.
		int shots = 0;

		for ( int i = 1; i < 5; i++)
		{
			// Judge if the bot has enough energy.
			if ( this.jolts > 2 )
			{
				// Judge if there is a bot in the specific direction.
				if ( bots[i] )
				{
					shots++;
					jolts--;
				}
			}
			else 
			{
				break;
			}
		}
		
		// Create the array.
		int[] arr = new int[shots];
		
		// Define the loop number.
		int i = 0;
		int i1 = 1;
		
		// Get the shots array.
		while ( i < shots )
		{
			// Judge if there is a bot in the specific direction.
			if ( bots[i1] )
			{
				arr[i] = i1;
				i++;
			}
			
			i1++;
		}
		
		// Return the array.
		if ( shots != 0 )
		{
			return arr;
		}
		else 
		{
			return null;
		}
	}

	/**
	 * Return a string representing the state of the ChickenBot.
	 */
	public String toString() 
	{
		// Create the strings.
		String str = new String();
		String str1 = new String();
		String str2 = new String();
  
		// Judge if the number occupies one more space.
		if ( this.id < 10 )
		{
			str1 = " " + this.id;
		}
		else 
		{
			str1 = "" + this.id;
		}
  
		// Judge if the number occupies one more space.
		if ( this.jolts < 10 )
		{
			str2 = "  " + this.jolts;
		}
		else 
		{
			str2 = " " + this.jolts;
		}
  
		// Get the string which should be returned.
		str = "(B " + str1 + str2 + ")";
  
		return str;
	}
}