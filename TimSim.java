/**
 * File:TimSim.java
 * Author: Li Jingwei
 * Date: October 18,2015
 * Purpose: CSCI 1110, Assignment 4 Solution
 * 
 * Description: This program reads in the simulation configuration and
 * then instantiate and run the simulation, printing out the state of
 * the simulation after each round. The simulation will continue until
 * the required number of rounds have transpired, or there is only one
 * TimBot remaining, whichever comes first.
 */
import java.util.Scanner;

/**
 *  This is the main class of the program and contains some program codes.
 *  The program starts running in the main() of this class.
 */
public class TimSim 
{

	/**
	 * This method is called when the program starts running.
	 * It reads in a configuration from the console, instantiate
	 * the simulation, run it for the required number of rounds
	 * or one function TimBot remains, and output the grid 
	 * representing the simulation after each round.
	 */
	public static void main(String[] args) 
	{
		// Instantiate new scanner to read from the console.
		Scanner keyboard = new Scanner(System.in);
		
		/**
		 *  Get the input from the console and assign data into the specific class.
		 */
		int R = keyboard.nextInt();
		int C = keyboard.nextInt();
		int J = keyboard.nextInt();
		int G = keyboard.nextInt();
		int N = keyboard.nextInt();
		int T = keyboard.nextInt();
		String P;
		int I;
		int X;
		int Y;
		int E;
		
		
		// Instantiate a DohNat.
		DohNat planet = new DohNat( R, C, J, G);
		
		// Create an array of size T of TimBot objects.
		TimBot[] tim = new TimBot[T];
		
		// Read in all date related to TimBots.
		for ( int i = 0; i < T; i++)
		{
			P = keyboard.next();
			I = keyboard.nextInt();
			X = keyboard.nextInt();
			Y = keyboard.nextInt();
			E = keyboard.nextInt();
			
			// Judge the type of the TimBot.
			if ( P.equals("chicken") )
			{
				tim[i] = new ChickenBot(I, E);
				planet.setTimBot(tim[i], X, Y);
			}
			
			if ( P.equals("spresso") )
			{
				tim[i] = new SpressoBot(I, E);
				planet.setTimBot(tim[i], X, Y);
			}
			
			if ( P.equals("angry") )
			{
				tim[i] = new AngryBot(I, E);
				planet.setTimBot(tim[i], X, Y);
			}
			
			if ( P.equals("bully") )
			{
				tim[i] = new BullyBot(I, E);
				planet.setTimBot(tim[i], X, Y);
			}
		}
		
		// Define the loop number.
		int i = 0; 
		int num = T;
		
		// Run the simulation for the required number of rounds.
		while ( i < N && num > 1 )
		{
			num = 0;
			
			// Run the round.
			planet.newRound();
			planet.doSensePhase();
			planet.doFirePhase();
			planet.doDefensePhase();
			planet.doMovePhase();
			planet.doBattlePhase();
			planet.doHarvestPhase();
			
			// Print out the round.
			System.out.println( "Round " + i );
			
			// Get the string which should be printed out.
			String str = planet.toString();
			
			// Print out the string.
			System.out.print( str );
			
			// Calculate the number of functional bots.
			for ( int i1 = 0; i1 < T; i1++)
			{
				if ( tim[i1].isFunctional() )
				{
					num++;
				}
			}
			i++;
		}
	}
}
