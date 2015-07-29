/**
 * @file Mints.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 10.26.12
 * @project CMSC 202 - Fall '12 - Project 3
 * @section Section 02
 */

package proj3;

import java.awt.Color;

/** This class represents a single package of mints
 *  Class Invariants: Mints must have a color of the object Color assigned
 *  */
public class Mints {
	
	private Color mintColor;
	
	/**
	 * @name Mints
	 * @description Constructor
	 * Precondition: color is a valid Java Color object 
	 * Postcondition: a new mint with the given color is created
	 * @param color
	 * @return none
	 */
	public Mints(Color color){
		this.mintColor = color;
	}
	
	/**
	 * @name Mints
	 * @description Copy Constructor
	 * Precondition: otherMint is a valid mint object
	 * Postcondition: a new mint is created with the same
	 * 			attributes as the given mint
	 * @param otherMint -- a mint object to be copied
	 * @return none
	 */
	public Mints(Mints otherMint){
		this.mintColor = otherMint.getColor();
	}
	
	/**
	 * @name getColor
	 * @description Returns the current mint's color
	 * Precondition: none
	 * Postcondition: a Color object is returned
	 * @param none
	 * @return mintColor -- a color object representing the
	 * 			current mint's color
	 */
	public Color getColor() {
		return mintColor;
	}

	/**
	 * @name main
	 * @description Unit testing 
	 * Precondition: none
	 * Postcondition: all methods will be successfully tested
	 * @param String[] args
	 * @return none
	 */
	public static void main(String[] args) {
		
		System.out.println("Creating a mint package with the color green...");
		Mints mint = new Mints(Color.green);
		System.out.println("Testing getColor method: "+mint.getColor());
		
		System.out.println("\nTesting copy constructor; giving green mint, expecting green mint...");
		Mints copyMint = new Mints(mint);
		System.out.println("Testing getColor method: "+copyMint.getColor());
	}

}
