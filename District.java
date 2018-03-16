/**
 *  File: District.java 
 *  Author: Li Jingwei
 *  Date: October 12, 2015
 *  Purpose: CSCI 1110, Assignment 4 Solution
 *  
 *  Description: This class specifies the template for the District class.
 */

import java.util.ArrayList;

/**
   This is the District class for representing districts on the planet DohNat
   Each District object is responsible for keeping track of the spresso plants
   in the district, the TimBot (if one is present), and any incoming ion shots
   coming into and out of the district.  It is also responsible for gathering
   sense data from surrounding districts and passing it to the TimBot during the
   Sense phase.
*/
public class District
{ 
  // These are the district ID used to index into the spresso and bots
  // arrays passed to the senseDistrict method and also used to specify
  // where the TimBot wishes to fire the ion cannon.
  public static final int CURRENT = 0;
  public static final int NORTH = 1;
  public static final int EAST = 2;
  public static final int SOUTH = 3;
  public static final int WEST = 4;
  
  // Define the private variables to store data.
  private int x;
  private int y;
  private int jolts;
  private int growth;
  private DohNat planet;
  private TimBot bot;
  private int[] spresso = new int[5];
  private boolean[] bots = new boolean[5];
  private District curDistrict;
  private int direction;
  private ArrayList<TimBot> disBots = new ArrayList<TimBot>();
  private int[] fire;
  private int attack = 0;
  private int time;
  
  /** 
     This constructor initializes the district, its spresso plant counter, and
     saves planet, its coordinates, which it will need to query adjoining
     districts.  When a district is instantiated, the Spresso is ready for 
     harvest.

     parameters: planet: reference to DohNat so that adjacent districts can be
                         accessed.
                 x : x coordinate of the district
                 y : y coordinate of the district
                 jolts : number of jolts that the TimBot gets from a harvest.
                 growth : amount of time it takes to grow spresso
   */
  public District( DohNat planet, int x, int y, int jolts, int growth ) 
  {  
	  // Store all the data input into specific variables.
	  this.x = x;
	  this.y = y;
	  this.jolts = jolts;
	  this.growth = growth;  
	  this.planet = planet;
	  
	  // Initialize the time variable.
	  time = 0;
  }

  /** 
     This method adds a TimBot to the district.  Only one TimBot may occupy
     a district.  It is an error if a TimBot is added to a district that
     has one.  
     
     return: true on success, and false on error
   */
  public boolean setTimBot( TimBot bot ) 
  {
	  // Judge if the district has a bot already.
	  if ( this.bot == null )
	  {
		  // Assign the input to the inside parameter.
		  this.bot = bot;
		  disBots.add(bot);
		  
		  return true;
	  }
	  else 
	  {
		  return false;
	  }
  }


  /** 
     This method performs starts the next round for the TimBot in
     the district.  It should call the TimBot's startRound() method.
     This method need not do anything if no functional TimBot is present.
   */
  public void startNewRound() 
  {
	  // Judge if there is a bot.
	  if ( bot != null )
	  {
		  // Judge if the bot becomes null.
		  if ( !(bot.startRound()) )
		  {
			  bot = null;
		  }
	  }
  }


  /** 
     This method performs the Sense phase for the TimBot in the district.
     This method need not do anything if no functional TimBot is present.
   */
  public void doSensePhase() 
  {
	  // Judge if there is a TimBot in the district.
	  if ( bot != null )
	  {
		  bots[0] = true;
		  spresso[0] = this.time;
		  
		  // Set the variable to the specific district.
		  curDistrict = planet.getDistrict(x, y - 1);
		  
		  // Get the growth number in the north.
		  spresso[1] = curDistrict.hasSpresso();
		  
		  // Judge if there is a TimBot in the north.
		  if ( curDistrict.hasTimBot() )
		  {
			  bots[1] = true;
		  }
		  else 
		  {
			  bots[1] = false;
		  }
		  
		  // Set the variable to the specific district.
		  curDistrict = planet.getDistrict(x + 1, y);
		  
		  // Get the growth number in the east.
		  spresso[2] = curDistrict.hasSpresso();
		  
		  // Judge if there is a TimBot in the east.
		  if ( curDistrict.hasTimBot() )
		  {
			  bots[2] = true;
		  }
		  else 
		  {
			  bots[2] = false;
		  }
		  
		  // Set the variable to the specific district.
		  curDistrict = planet.getDistrict(x, y + 1);
		  
		  // Get the growth number in the south.
		  spresso[3] = curDistrict.hasSpresso();
		  
		  // Judge if there is a TimBot in the south.
		  if ( curDistrict.hasTimBot() )
		  {
			  bots[3] = true;
		  }
		  else
		  {
			  bots[3] = false;
		  }
		  
		  // Set the variable to the specific district.
		  curDistrict = planet.getDistrict(x - 1, y);
		  
		  // Get the growth number in the west.
		  spresso[4] = curDistrict.hasSpresso();
		  
		  // Judge if there is a TimBot in the west.
		  if ( curDistrict.hasTimBot() )
		  {
			  bots[4] = true;
		  }
		  else 
		  {
			  bots[4] = false;
		  }
		  
		  // Assign the data into the TimBot.
		  bot.senseDistricts(spresso, bots);
	  }
  }


  /** 
     This method returns true if there is a TimBot in the District and
     false otherwise.
     
     return: true if TimBot is present
   */
  public boolean hasTimBot() 
  {
	  // Judge if there is a TimBot.
	  if ( this.bot == null )
	  {
		  return false;
	  }
	  else
	  {
		  return true;
	  }
  }


  /** 
     This method returns number of rounds remaining until  spresso plants 
     are ready for harvest.
     
     return: number of rounds before spresso is ready.
   */
  public int hasSpresso() 
  {
    return this.time;
  }


  /** 
     This method performs the Move phase for the TimBot in the district.
     This method need not do anything if no functional TimBot is present.
   */
  public void doMovePhase() 
  {
	  // Judge if there is a TimBot.
	  if ( this.bot != null )
	  {
		  // Get the direction the TimBot wants to go in.
		  direction = bot.getNextMove();
		  
		  // Judge which district the bot wants to go to.
		  if ( direction == 1 )
		  {
			  planet.getDistrict(x, y - 1).moveTimBot(bot);
		  }
		  else if ( direction == 2 )
		  {
			  planet.getDistrict(x + 1, y).moveTimBot(bot);
		  }
		  else if ( direction == 3 )
		  {
			  planet.getDistrict(x, y + 1).moveTimBot(bot);
		  }
		  else if ( direction == 4 )
		  {
			  planet.getDistrict(x - 1, y).moveTimBot(bot);
		  }
		  
		  // Indicate that the bot has moved.
		  if ( direction != 0 )
		  {
			  disBots.remove(bot);
			  this.bot = null;
		  }
	  }
  }


  /** 
     This method moves the TimBot into the District during the Move phase.
     It is ok if there are multiple TimBots in the District, since they will
     be taken care of during the Battle phase.

     Parameter: bot: The TimBot moving into the District.
   */
  public void moveTimBot( TimBot bot ) 
  {
	  // Judge if there is a bot before.
	  if ( this.bot == null )
	  {
		  // Assign the input into the variable.
		  this.bot = bot;
	  }
	  
	  // Assign the bot into the arraylist.
	  disBots.add(bot);
  }


  /** 
     This method performs the Battle phase for the TimBot(s) in the district.
     This method need not do anything if less than two TimBots are present.
   */
  public void doBattlePhase() 
  {
	  
	  // Get the number of the bots in the district.
	  int i = disBots.size();
	  
	  // Judge if there is more than one bot.
	  while ( i > 1 )
	  {
		  // Execute useShield method for every bot all the time until less than two bots left.
		  for ( int i1 = 0; i1 < i; )
		  {
			  if ( !(disBots.get(i1).useShield()) )
			  {
				  disBots.remove(i1);
				  i--;
			  }
			  else 
			  {
				  i1++;
			  }
		  }
	  }
	  
	  // Clear the temporary bot variable.
	  this.bot = null;
	  
	  // Check if there is a bot functional.
	  for ( int i1 = 0; i1 < disBots.size(); i1++ )
	  {
		  if ( disBots.get(i1).isFunctional() )
		  {
			  this.bot = disBots.get(i1);
		  }
	  }
	  
	  // Remove the bots not functional.
	  disBots.clear();
	  
	  // Chech if there is one bot left.
	  if ( this.bot != null )
	  {
		  disBots.add(this.bot);
	  }
	  
  }


  /** 
     This method performs the Fire phase for the TimBot in the district.
     This method need not do anything if less than two TimBots are present.
   */
  public void doFirePhase() 
  {
	  // Judge if there is a bot in the district.
	  if ( this.bot != null )
	  {
		  // Judge if there are less than two TimBots present.
		  if ( planet.getDistrict(x, y - 1).hasTimBot() || planet.getDistrict(x + 1, y).hasTimBot()
				  || planet.getDistrict(x, y + 1).hasTimBot() || planet.getDistrict(x - 1, y).hasTimBot() )
		  {
			  fire = bot.fireCannon();
		  }
	  
		  // Judge if fire is null.
		  if ( fire != null )
		  {
			  // Fire towards the specific district.
			  for ( int i1 = 0; i1 < fire.length; i1++ )
			  {
				  if ( fire[i1] == 1 )
				  {
					  planet.getDistrict(x, y - 1).fireAtDistrict();
				  }
			  
				  if ( fire[i1] == 2 )
				  {
					  planet.getDistrict(x + 1, y).fireAtDistrict();
				  }
			  
				  if ( fire[i1] == 3 )
				  {
					  planet.getDistrict(x, y + 1).fireAtDistrict();
				  }
			  
				  if ( fire[i1] == 4 )
				  {
					  planet.getDistrict(x - 1, y).fireAtDistrict();
				  }
			  }
		  }
	  }
  }


  /** 
     This method is called by adjacent Districts if a TimBot in
     those districts is firing at the TimBot in the present district.
     Every invocation of this method constitutes a single ion shot
     at the TimBot in this district.  This method need not do
     anything if is no TimBot are present.
   */
  public void fireAtDistrict() 
  {
	  // Calculate the number indicating the times this district is attacked.
	  this.attack++;
  }


  /** 
     This method performs the Defense phase for the TimBot in the district.
     This method need not do anything if is no TimBot are present.
   */
  public void doDefensePhase() 
  {
	  // Judge if there is a bot.
	  if ( this.bot != null )
	  {
		  // Judge if the district is attacked.
		  while ( this.attack != 0 )
		  {
			  // Call the useShield method for the bot to defend.
			  if ( !(this.bot.useShield()) )
			  {
				  this.bot = null;
				  this.attack = 0;
				  break;
			  }
			  
			  // Reduce the attack number.
			  this.attack--;
		  }
	  }
	  else 
	  {
		  // Initialize the attack number.
		  attack = 0;
	  }
  }


  /** 
     This method performs the Harvest phase for the TimBot in the district.
     This mehtod will always be called at the end of the round.  If the
     spresso is not ready to be harvested, then the method should decrement
     a counter that counts down to when the spresso will be ready for harvest.
     Otherwise, if the spresso is ready for harvest and a TimBot is present,
     the TimBot harvests the spresso and the counter is reset to count down.
     Otherwise, nothing is done.
   */
  public void doHarvestPhase() 
  {
	  // Judge if the spresso is available.
	  if ( this.time != 0 )
	  {
		  this.time--;		  
	  }
	  else 
	  {
		  // Harvest the spresso if there is a bot.
		  if ( this.bot != null )
		  {
			  this.bot.harvestSpresso(jolts);
			  
			  // Reset the growth time.
			  time = this.growth;
		  }
	  }
  }


  /** 
     This method returns a String describing state of the District using
     the format:
       |bot counter|
     where bot is either the string representing the TimBot in the District
     or 9 spaces if there is no TimBot; counter is the number of rounds left
     till the spress is ready for harvest.   The counter should have width 2.
     E.g.,
       |(N 42  7)  3|
     Shows a district with TimBot with personality N, ID 42, energy level 7,
     and there are three rounds left till the spresso is ready for harvest.
   */
  public String toString() 
  {
	  // Create the strings.
	  String str = new String();
	  String str1 = new String();
	  
	  // Get the bot string.
	  if ( this.bot == null )
	  {
		  str = "|         ";
	  }
	  else 
	  {
		  // Assign the string into the variable.
		  String str2 = this.bot.toString();
		  
		  str = "|" + str2;
	  }
	  
	  // Get the counter string.
	  if ( this.time < 10 )
	  {
		  str1 = " " + this.time;
	  }
	  else if ( this.time >= 10 && this.time < 100 )
	  {
		  str1 += this.time;
	  }
	  
	  // Get the total string.
	  str += " " + str1 + "|";
	  
	  return str;
  }
}
