import java.util.ArrayList;

/**
 * File:AngryBot.java
 * Author: Li Jingwei
 * Date: October 17,2015
 * Purpose: CSCI 1110, Assignment 4 Solution
 * 
 * Description: This class subclasses the SpressoBot class and implements
 * (Personality A) AngryBot.
 */
public class AngryBot extends SpressoBot
{
	/**
	 * The constructor sets the bot's ID and initial amount of energy.
	 */
	public AngryBot( int id, int jolts)
	{
		super(id, jolts);
	}
	
	/**
	 * Move the SpressoBot by its preference.
	 */
	public int getNextMove()
	{
		// Judge the energy.
		if ( this.jolts >= 1 && !move)
		{			
			// Judge the energy.
			if ( this.jolts < 3 )
			{
				return super.getNextMove();
			}
			else 
			{
				// Define the ArrayList to determine which direction the bot prefers.
				ArrayList<Integer> direct = new ArrayList<Integer>();
				
				// Indicate that the bot has been moved.
				move = true;
				
				// Define the integer variables.
				int i = 0;
				int i1 = 1;
				int i2;
				
				// Consider if there are other TimBots in the district.
				while ( i1 < 5 )
				{
					if ( bots[i1] )
					{
						direct.add(i1);
					}
					
					i1++;
				}
				
				// Initialize the loop number.
				i1 = 1;
				
				// Judge if the arraylist is empty.
				if ( direct.size() != 0 )
				{
					// Assign the first number in the arraylist to i.
					i = direct.get(0);
					i2 = spresso[i];
					
					// Judge the size of the arraylist.
					if ( direct.size() == 1 )
					{
						if ( i != 0 )
						{
							jolts--;
						}
						
						return i;
					}
					else 
					{
						// Get the direction which has the least rounds to harvest.
						while ( i1 < direct.size() )
						{
							if ( spresso[direct.get(i1)] < i2 )
							{
								i2 = spresso[direct.get(i1)];
								
								// Remove all the direction which does not have the least rounds.
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
								direct.remove(i1);	
							}
							else 
							{
								i = i1;
								i1++;
							}
						}
						
						// Decrease the energy if the bot moves.
						if ( direct.get(0) != 0)
						{
							jolts--;
						}
						
						return direct.get(0);
					}
				}
				else 
				{
					// Initialize the variables.
					i = 0;
					i1 = 1;
					i2 = spresso[i];
					
					// Add the first direction into the arraylist.
					direct.add(i);
					
					// Get the direction which has the least rounds to harvest.
					for ( i1 = 1; i1 < 5; i1++)
					{
						if ( spresso[i1] < i2 )
						{
							direct.clear();
							direct.add(i1);
							i2 = spresso[i1];
						}
						else if ( spresso[i1] == i2 )
						{
							direct.add(i1);
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
		str = "(A " + str1 + str2 + ")";
	  
		return str;
	}
}
