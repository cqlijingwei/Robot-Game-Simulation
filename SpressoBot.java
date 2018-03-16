import java.util.ArrayList;

/**
 * File:SpressoBot.java
 * Author: Li Jingwei
 * Date: October 17,2015
 * Purpose: CSCI 1110, Assignment 4 Solution
 * 
 * Description: This class subclasses the TimBot class and implements
 * (Personality S) SpressoBot.
 */
public class SpressoBot extends TimBot
{	
	/**
	 *  The constructor sets the bot's ID and initial amount of energy.
	 */
	public SpressoBot( int id, int jolts)
	{
		super(id, jolts);
	}
	
	/**
	 * Move the SpressoBot by its preference.
	 */
	public int getNextMove()
	{
		// Judge the energy.
		if ( this.jolts >= 1 && !move )
		{
			// Define the ArrayList to determine which direction the bot prefers.
			ArrayList<Integer> direct = new ArrayList<Integer>();
			
			// Indicate that the bot has been moved.
			move = true;
			
			// Define the integer variables.
			int i = 0;
			int i1 = 1;
			int i2 = spresso[i];
			
			// Add the first direction into the arraylist.
			direct.add(i);
			
			// Find the direction which has the least rounds.
			while ( i1 < 5 )
			{
				// Get the minimal rounds number and the index.
				if ( spresso[i1] < i2 )
				{
					i2 = spresso[i1];
					i = i1;
					direct.clear();
					direct.add(i1);
				}
				// Set the equal numbers into arraylist.
				else if ( spresso[i1] == i2 )
				{
					direct.add(i1);
				}
				
				i1++;
			}
			
			// Initialize the loop number;
			i1 = 0;
			
			// Judge the size of the direction arraylist.
			if ( direct.size() == 1 )
			{
				if ( direct.get(i1) != 0 )
				{
					jolts--;
				}
				
				return direct.get(i1);
			}
			else
			{
				// Remove the direction which has other TimBots inside.
				while ( i1 < direct.size() )
				{
					// Judge if the direction is not current and if it has bots.
					if ( direct.get(i1) != 0 && this.bots[direct.get(i1)])
					{
						direct.remove(i1);
					}
					else 
					{
						i1++;
					}
					
				}
				
				// Return the preference direction.
				if ( direct.size() != 0 )
				{
					if ( direct.get(0) != 0 )
					{
						jolts--;
					}
					
					return direct.get(0);
				}
				else 
				{
					// judge if the bot moves.
					if ( i != 0 )
					{
						jolts--;
					}
					
					return i; 
				}
			}
		}
		else
		{
			return District.CURRENT;
		}
	}
	
	/**
	 * Return a string representing the state of the SpressoBot.
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
		str = "(S " + str1 + str2 + ")";
	  
		return str;
	}
}
