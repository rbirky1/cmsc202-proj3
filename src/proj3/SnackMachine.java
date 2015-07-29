/**
 * @file SnackMachine.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 10.26.12
 * @project CMSC 202 - Fall '12 - Project 3
 * @section Section 02
 */
package proj3;

import java.awt.Color;

/** This class represents a snack (vending) machine
 *  Class Invariants: the number of cookie packages must not exceed 60
 *  		the number of mint packages must not exceed 75
 *  		cashOnHand must be a money object, thus contain exact change
 *  			of quarters, nickels and dimes
 *  		to add cookies or mints, a CookieFlavor or Color must be provided,
 *  			and an integer greater than or equal to zero
 *  */
public class SnackMachine {

	private final int COOKIES_CAPACITY = 60;
	private Cookies[] cookies = new Cookies[COOKIES_CAPACITY];
	private int COOKIE_COST = 65;
	
	private final int MINTS_CAPACITY = 75;
	private Mints[] mints = new Mints[MINTS_CAPACITY];
	private int MINTS_COST = 35;
	
	private Money cashOnHand;
	
	/**
	 * @name SnackMachine
	 * @description Constructor
	 * Precondition: none
	 * Postcondition: creates a new SnackMachine object with $0.00
	 * @param none
	 * @return none
	 */
	public SnackMachine(){
		cashOnHand = new Money(0,0,0);
	}
	
	/**
	 * @name buyMints
	 * @description returns the next mint object in the FIFO queue if enough money is available
	 * Precondition: there must be mints in the mints queue
	 * 			money must be a valid Money object and enough in combination with cashOnHand to buy a mints package
	 * Postcondition: the next mint object in the FIFO queue is returned, or null if the conditions are not met
	 * @param money -- a valid Money object
	 * @return the next mints object or null
	 */
	public Mints buyMints(Money money) {
		//add money to cashOnHand
		addCash(money);
		//check money for exact change; check queue for objects
		if (cashOnHand.getTotal()>=MINTS_COST && getNrMints()>0){
			//start at beginning of queue, increment index until non-null object found
			int mintsIndex=0;
			while(mints[mintsIndex]==null){
				mintsIndex++;
			}
			//copy object into temp object to prevent privacy leak
			Mints tempMint = new Mints(mints[mintsIndex]);
			//set original to null in queue
			mints[mintsIndex] = null;
			//subtract money from cashOnHand
			withdrawalMoney(MINTS_COST);
			//return copy object
			return tempMint;
			}
		//return null if not exact change or no objects in queue
		else return null;
	}

	/**
	 * @name buyCookies
	 * @description returns the next cookies object in the FIFO queue if enough money is available
	 * Precondition: there must be cookies in the cookies queue
	 * 			money must be a valid Money object and enough in combination with cashOnHand to buy a cookies package
	 * Postcondition: the next cookies object in the FIFO queue is returned, or null if the conditions are not met
	 * @param money -- a valid Money object
	 * @return the next cookies object or null
	 */
	public Cookies buyCookies(Money money) {
		//add money to cashOnHand
		addCash(money);
		//check money for exact change; check queue for objects
		if(cashOnHand.getTotal()>=COOKIE_COST && getNrCookies()>0){
			//start at beginning of queue, increment index until non-null object found
			int cookiesIndex=0;
			while (cookies[cookiesIndex]==null){
				cookiesIndex++;
			}
			//copy object into temp object to prevent privacy leak
			Cookies tempCookie = new Cookies(cookies[cookiesIndex]);
			//set original to null in queue
			cookies[cookiesIndex] = null;
			//subtract money from cashOnHand
			withdrawalMoney(COOKIE_COST);
			//return copy object
			return tempCookie;
		}
		//return null if not exact change or no objects in queue
		else return null;
	}

	/**
	 * @name addMints
	 * @description adds the specified number of mints of the specified color to the mints queue
	 * Precondition: number of mints must be non-negative
	 * Postcondition: the specified number of mints of the specified color is added to the mints queue
	 * @param mColor -- a java Color object
	 * 			nrMints -- the number of mint packages to be added -- must be a non-negative integer
	 * @return none
	 */
	public void addMints(Color mColor, int nrMints) {
		//verify adding positive number of mint packages
		if (nrMints>=0){
		//counter variable
		int j=0;
		//add as many mints as specified
			for (int i=0; i<nrMints; i++){
			//increment counter until a null spot is found in array
				while(j<MINTS_CAPACITY && mints[j] != null){
					j++;
				}
				//replace null spot with new mints of specified color
				mints[j] = new Mints(mColor);
			}
		}
		//error if trying to add negative or zero objects
		else throw new RuntimeException("Invalid package number. Must be non-negative.");
	}
	
	/**
	 * @name addCookies
	 * @description adds the specified number of cookies of the specified flavor to the cookies queue
	 * Precondition: number of cookies must be non-negative
	 * Postcondition: the specified number of cookies of the specified flavor is added to the cookies queue
	 * @param flavor -- a CookiesFlavor object
	 * 			nrcookies -- the number of mint packages to be added -- must be a non-negative integer
	 * @return none
	 */
	public void addCookies(CookieFlavors flavor, int nrCookies) {
		//verify adding positive number of cookie packages
		if (nrCookies>=0){
		//counter variable
		int j=0;
		//add as many cookies as specified
			for (int i=0; i<nrCookies; i++){
				//increment counter until a null spot is found in array
				while (j<COOKIES_CAPACITY && cookies[j] != null){
					j++;
				}
				//replace null spot with new cookies of specified flavor
				cookies[j] = new Cookies(flavor);
			}
		}
		//error if trying to add negative or zero objects
		else throw new RuntimeException("Invalid package number. Must be non-negative.");
	}

	/**
	 * @name getNrMints
	 * @description returns the number of mint packages in the SnackMachine
	 * Precondition: none
	 * Postcondition: the number of mint packages in the SnackMachine is returned
	 * @param none
	 * @return tempNumMints -- an integer representing the number of mint packages in the SnackMachine
	 */
	public int getNrMints() {
		int tempNumMints = MINTS_CAPACITY;
		//loop through array, subtracting from total possible for ecery null spot
		for (Mints mint : mints){
			if (mint == null)
				tempNumMints--;
		}
		return tempNumMints;
	}

	/**
	 * @name getNrCookies
	 * @description returns the number of cookie packages in the SnackMachine
	 * Precondition: none
	 * Postcondition: the number of cookie packages in the SnackMachine is returned
	 * @param none
	 * @return tempNumCookies -- an integer representing the number of cookie packages in the SnackMachine
	 */
	public int getNrCookies() {
		int tempNumCookies = COOKIES_CAPACITY;
		//loop through array, subtracting from total possible for ecery null spot
		for (Cookies cookie : cookies){
			if (cookie == null)
				tempNumCookies--;
		}
		return tempNumCookies;
	}

	/**
	 * @name addCash
	 * @description adds the given amount of money to cashOnHand
	 * Precondition: money must contain non negative integer values for nickels, dimes and qrts
	 * Postcondition: the given amount of money is added to cashOnHand
	 * @param money -- a money object containing non negative integer values for nickels, dimes and qrts
	 * @return none
	 */
	private void addCash(Money money){
		cashOnHand.addNickels(money.getNrNickels());
		cashOnHand.addDimes(money.getNrDimes());
		cashOnHand.addQrts(money.getNrQrts());
	}
	
	/**
	 * @name withdrawalMoney
	 * @description calls withdrawal from the Money object cashOnHand
	 * Precondition: cost is a non negative integer value (checked by Money)
	 * Postcondition: the cost is subtracted by the cashOnHand money object
	 * @param cost -- a non negative integer value representing the cost of the item 
	 * @return none
	 */
	private void withdrawalMoney(int cost){
		cashOnHand.withdrawal(cost);
	}
	
	/**
	 * @name getCashOnHand
	 * @description returns a money object representing the money in the SnackMachine
	 * Precondition: none
	 * Postcondition: a money object representing the money in the SnackMachine is returned
	 * @param none
	 * @return a copy of the cashOnHand object
	 */
	public Money getCashOnHand() {
		return new Money(cashOnHand);
	}
	
	/**
	 * @name main
	 * @description Unit testing 
	 * Precondition: none
	 * Postcondition: all methods are successfully tested
	 * @param String[] args
	 * @return none
	 */
	public static void main(String[] args) {
		System.out.println("Creating a new, empty SnackMachine object...");
		SnackMachine snackMachine = new SnackMachine();
		System.out.println("Testing getNrMints and getNrCookies methods; expecting 0 and 0");
		System.out.println("Number of mints: "+snackMachine.getNrMints()+"\nNumber of cookies: "+snackMachine.getNrCookies());
		
		System.out.println("\nAdding 4 mints (2 black, 2 red) to the SnackMachine...");
		
		//Adding four mints
		snackMachine.addMints(Color.black, 2);
		snackMachine.addMints(Color.red, 2);
		
		//Testing getNrMints(); Expecting 4
		System.out.println(snackMachine.getNrMints()+" mints total.");
		
		System.out.println("\nAdding $.70 with 1 nickel, 4 dimes and 1 quarter");
		Money testMoney = new Money(1,4,1);
		System.out.println("Testing toString method...");
		System.out.println("Original amount: \n"+testMoney);
		
		//Testing buyMints()
		Mints mints2 = snackMachine.buyMints(testMoney);
		
		//Expecting color black [r=0,g=0,b=0] and $.35
		System.out.println("\nBought 1 mint package." +
				"\nColor: " + mints2.getColor() +
				"\nTesting withdrawal method; expecting 1 nickel 3 dimes 0 quarters and $0.35" +
				"\nMoney left: \n"+snackMachine.getCashOnHand());
		
		//Not adding more money
		Money newTestMoney = new Money(0,0,0);
		
		//Testing buyMints()
		snackMachine.buyMints(newTestMoney);
		
		//Expecting color black [r=0,g=0,b=0] and $.00
		System.out.println("\nBought 1 mint package." +
				"\nColor: " + mints2.getColor() +
				"\nTesting withdrawal method; expecting 0 nickels 0 dimes and 0 quarters and $0.00" +
				"\nMoney left: \n"+snackMachine.getCashOnHand());
		
		//Testing buyMints(); Expecting null
		System.out.println("\nTesting buyMints(); Expecting null...\n"+snackMachine.buyMints(newTestMoney));
		
		//Expecting $.00
		System.out.println("\nTesting withdrawal method; expecting 0 nickels 0 dimes 0 quarters and $0.00" +
				"\nMoney left: \n"+snackMachine.getCashOnHand());
	}
}
