/**
   File: DohNat.java
   Author: Li Jingwei
   Date: October 13, 2015
   Purpose: CSCI 1110, Assignment 4 Solution

   Description: This class specifies the template for the DohNat class.
*/

import java.util.*;

/**
   This is the DohNat base class for representing the torroidal planet
   DohNat.  It represents a grid division of the planet where each element
   of the grid is a District and where opposite edges of the grid are
   considered adjacent.  
*/
public class DohNat { 
	
	// Define the variables.
	private int rows;
	private int columns;
	private int jolts;
	private int growth;
	private District[][] grid;
	
  /** This constructor creates a planet divided into a grid of
      rows X columns districts.

      parameters:  rows:    number of rows in the grid
                   columns: number of columns in the grid
                   jolts:   number of jolts that a spresso harvest delivers
                   growth:  number of rounds it takes to grow spresso plants
   */
  public DohNat( int rows, int columns, int jolts, int growth ) 
  {
	  // Assign the values into corresponding variables.
	  this.rows = rows;
	  this.columns = columns;
	  this.jolts = jolts;
	  this.growth = growth;
	  
	  // Define the district 2D array.
	  grid = new District[columns][rows];
	  
	  // Define each district in DohNat planet.
	  for ( int i1 = 0; i1 < rows; i1++)
		  for ( int i2 = 0; i2 < columns; i2++)
		  {
			  grid[i2][i1] = new District( this, i2, i1, this.jolts, this.growth);
		  }
  }


  /** This method adds a TimBot to the District in grid element (x,y).
      where 0 <= x < width and 0 <= y < height.  This method will 
      return false if there is already a TimBot in the District, or
      the coordinates are invalid.

      parameters:  bot: The TimBot object to be added
                   x  : x coordinate of the destination District 
                   y  : y coordinate of the destination District 

      returns: true on success or false otherwise.
   */
  public boolean setTimBot( TimBot bot, int x, int y ) 
  {
	  // Judge if the coordinates are valid.
	  if ( x < 0 || x >= columns || y < 0 || y >= rows )
	  {
		  return false;
	  }
	  // Judge if there is a bot in the district.
	  else if ( grid[x][y].hasTimBot() )
	  {
		  return false;
	  }
	  else 
	  {
		  // Assign the value into corresponding variable.
		  grid[x][y].setTimBot( bot );
		  
		  return true;
	  }
  }


  /** This method starts the next round of the simulation
   */
  public void newRound() 
  {
	  // Execute the new round method in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].startNewRound();
		  }
  }


  /** This method executes the Sense phase of the round.
   */
  public void doSensePhase() 
  {
	  // Execute doSensePhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doSensePhase();
		  }
  }


  /** This method exectutes the Move phase of the round.
   */
  public void doMovePhase() 
  {
	  // Execute doMovePhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doMovePhase();
		  }
  }


  /** This method exectutes the Battle phase of the round.
   */
  public void doBattlePhase() 
  {
	  // Execute doBattlePhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doBattlePhase();
		  }
  }


  /** This method exectutes the Fire phase of the round.
   */
  public void doFirePhase() 
  {
	  // Execute doFirePhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doFirePhase();
		  }
  }


  /** This method executes the Defense phase of the round.
   */
  public void doDefensePhase() 
  {
	  // Execute doDefensePhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doDefensePhase();
		  }
  }


  /** This method executes the Harvest phase of the round.
   */
  public void doHarvestPhase()
  {
	  // Execute doHarvestPhase in every district.
	  for ( int i1 = 0; i1 < rows; i1++ )
		  for ( int i2 = 0; i2 < columns; i2++ )
		  {
			  grid[i2][i1].doHarvestPhase();
		  }
  }


  /** This method returns the District object representing district 
      (x,y) on DohNat.  Coordinate may in the range -1 <= x <= width,
      and -1 <= y <= height.  Note since the antipodal edges of the 
      grid are adjacent, 
        if x == -1, then x = width - 1
        if y == -1, then y = height - 1
        if x == width, then x = 0
        if y == height, then y = 0

      parameters: x: x coordinate of the District
                  y: y coordinate of the District

      returns the District object
   */
  public District getDistrict( int x, int y ) 
  {
	  // Judge if the coordinates are out of bound. 
	  if ( x == -1 )
	  {
		  x = columns - 1;
	  }
	  
	  if ( y == -1 )
	  {
		  y = rows - 1;
	  }
	  
	  if ( x == columns )
	  {
		  x = 0;
	  }
	  
	  if ( y == rows )
	  {
		  y = 0;
	  }
	  
	  return grid[x][y];
  }

  /**
    This method returns a String describing state of the DohNat using
    the grid format:
      DDDD..D
      DDDD..D
      ::::  :
      DDDD..D
    where D denotes a string representing the corresponding district in
    the grid.   There should be no spaces spearating the districts, and
    each row of disticts should be terminated by a newline.
  */
  public String toString() 
  {
	  // Create the string.
	  String str = new String();
	  
	  // Add the district strings onto the total string.
	  for ( int i1 = 0; i1 < rows; i1++)
	  {
		  for ( int i2 = 0; i2 < columns; i2++)
		  {
			  str += grid[i2][i1].toString();
		  }
		  
		  str += "\n";
	  }
	  
	  return str;
  }
}
