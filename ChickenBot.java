import java.util.ArrayList;

/**
 * File: ChickenBot.java 
 * Author: Li Jingwei
 * Date: October 17, 2015
 * Purpose: CSCI 1110, Assignment 4 Solution
 * 
 * Description: This class subclasses the TimBot class and implements
 * (Personality C) ChickenBot.
 */
public class ChickenBot extends TimBot
{	
	/**
	 *  The constructor sets the bot's ID and initial amount of energy.
	 */
	public ChickenBot( int id, int jolts)
	{
		super(id, jolts);
	}
	
	/**
	 * Move the ChickenBot by its preference.
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
			
			// Add the current direction into the arraylist.
			direct.add(i);
			
			// Consider if there are other TimBots in the district.
			while ( i1 < 5 )
			{
				if ( !(this.bots[i1]) )
				{
					direct.add(i1);
				}
			
				i1++;
			}
			
			// Initialize the numbers.
			int i2 = spresso[i];
			i1 = 1;
		
			// Judge if there is the direction which does not have other TimBots.
			if ( direct.size() == 1 )
			{
				return District.CURRENT;
			}
			else 
			{
				// Find the direction which has the least rounds.
				while ( i1 < direct.size() )
				{
					if ( spresso[direct.get(i1)] < i2 )
					{
						i2 = spresso[direct.get(i1)];
						
						// Remove all the direction before which does not have the least rounds.
						for ( int i3 = i; i3 >= 0; i3--)
						{
							direct.remove(i3);
						}
						
						// Initialize the loop number.
						i1 = 1;
						i = 0;
					}
					else if ( spresso[direct.get(i1)] > i2 )
					{
						// Remove the direction which does not have the least rounds.
						direct.remove(i1);
					}
					else 
					{
						// Increase the loop numbers.
						i++;
						i1++;
					}
				}
				
				// Decrease the energy if the bot moves.
				if ( direct.get(0) != 0 )
				{
					jolts--;
				}
				
				return direct.get(0);
			}
		}
		else 
		{
			return District.CURRENT;
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
		str = "(C " + str1 + str2 + ")";
	  
		return str;
	}
}
