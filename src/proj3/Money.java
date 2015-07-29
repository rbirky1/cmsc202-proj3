/**
 * @file Money.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 10.26.12
 * @project CMSC 202 - Fall '12 - Project 3
 * @section Section 02
 */
package proj3;

import java.text.DecimalFormat;

/** This class represents a money object
 *  Class Invariants: Must contain only quarters, nickels and dimes
 *  		these variables must be greater than or equal to zero
 *  		exact change is added and subtracted
 *  */
public class Money {
	
	private int nrNickels;
	private int nrDimes;
	private int nrQrts;
	
	private final int NICKEL_VALUE = 5;
	private final int DIME_VALUE = 10;
	private final int QRT_VALUE = 25;
	
	private double total;
	
	DecimalFormat df = new DecimalFormat("#0.00");
	
	/**
	 * @name Money
	 * @description Constructor 
	 * Precondition: nrNickels, nrDimes, nrQrts are positive integer values
	 * Postcondition: a new Money object is created with the given number of
	 * 			nickels, dimes and quarters
	 * @param nrNickels, nrDimes, nrQrts -- positive integers
	 * @return none
	 */
	public Money(int nrNickels, int nrDimes, int nrQrts) {
		if (nrNickels>=0 && nrDimes>=0 && nrQrts>=0 && nrNickels==Math.round(nrNickels)
				&& nrDimes==Math.round(nrDimes) && nrQrts == Math.round(nrQrts)){
			this.nrNickels = nrNickels;
			this.nrDimes = nrDimes;
			this.nrQrts = nrQrts;
		}
		else throw new RuntimeException("Invalid money value. Must be a positive integer");
	}

	/**
	 * @name Money
	 * @description Copy Constructor 
	 * Precondition: otherMoney is a valid money object
	 * Postcondition: a new Money object with the same attributes as the given
	 * 			Money object is created
	 * @param otherMoney -- a valid money object
	 * @return none
	 */
	public Money(Money otherMoney){
		this.nrNickels = otherMoney.getNrNickels();
		this.nrDimes = otherMoney.getNrDimes();
		this.nrQrts = otherMoney.getNrQrts();
	}
	
	/**
	 * @name getTotal
	 * @description Returns the total monetary value of the Money object
	 * 			in pennies (to avoid using doubles and floats which can be inaccurate)
	 * Precondition: none
	 * Postcondition: the total penny monetary value of the current Money object
	 * @param none
	 * @return total -- an integer sum of nrNickels, nrDimes, nrQrts multiplied
	 * 			by their respective monetary values
	 */
	public double getTotal(){
		total = (nrNickels*NICKEL_VALUE + nrDimes*DIME_VALUE + nrQrts*QRT_VALUE);
		return total;
	}

	/**
	 * @name withdrawal 
	 * @description Withdrawals the appropriate number of coins/change from the current Money object
	 * Precondition: cost is a positive integer value
	 * 			(determined by snackMachine -- no verification needed)
	 * Postcondition: the amount of money is withdrawn in exact change
	 * @param cost -- the amount of money to be withdrawn
	 * @return none
	 */
	public void withdrawal(int cost){
		//System.out.println("Original cost: "+cost);
		
		int qrtsInCost = cost / QRT_VALUE;
		//System.out.println("Number of quarters in cost: "+qrtsInCost);
		if (qrtsInCost<=nrQrts && cost>0) {cost-=qrtsInCost*QRT_VALUE; nrQrts-=qrtsInCost;}
		//System.out.println("New cost: "+cost);

		int dimesInCost = cost / DIME_VALUE;
		//System.out.println("Number of dimes in cost: "+dimesInCost);
		if (dimesInCost<=nrDimes && cost>0) {cost-=dimesInCost*DIME_VALUE; nrDimes-=dimesInCost;}
		//System.out.println("New cost: "+cost/100);
		
		int nickelsInCost = cost / NICKEL_VALUE;
		//System.out.println("Number of nickels in cost: "+nickelsInCost);
		if (nickelsInCost<=nrNickels && cost>0) {cost-=nickelsInCost*NICKEL_VALUE; nrNickels-=nickelsInCost;}
		//System.out.println("New cost: "+cost);
		
		//Error messages...?
	}
	
	/**
	 * @name addNickels
	 * @description Adds the given number of nickels to the current Money object
	 * Precondition: nrNickels must be a positive integer value
	 * Postcondition: the nrNickels given is added to the nrNickels in the current Money object
	 * @param nrNickels -- a positive integer value representing a number of nickels to be added
	 * @return none
	 */
	public void addNickels(int nrNickels){
		if (nrNickels>=0 && nrNickels==Math.round(nrNickels))
			this.nrNickels+=nrNickels;
		else throw new RuntimeException("Invalid money value. Must be a positive integer");
	}
	
	/**
	 * @name addDimes
	 * @description Adds the given number of dimes to the current Money object
	 * Precondition: nrDimes must be a positive integer value
	 * Postcondition: the nrDimes given is added to the nrDimes in the current Money object
	 * @param nrDimes -- a positive integer value representing a number of dimes to be added
	 * @return none
	 */
	public void addDimes(int nrDimes){
		if (nrDimes>=0 && nrDimes==Math.round(nrDimes))
			this.nrDimes+=nrDimes;
		else throw new RuntimeException("Invalid money value. Must be a positive integer");
	}
	
	/**
	 * @name addQrts
	 * @description Adds the given number of quarters to the current Money object
	 * Precondition: nrQrts must be a positive integer value
	 * Postcondition: the nrQrts given is added to the nrQrts in the current Money object
	 * @param nrQrts -- a positive integer value representing a number of quarters to be added
	 * @return none
	 */
	public void addQrts(int nrQrts){
		if (nrQrts>=0 && nrQrts==Math.round(nrQrts)) 
			this.nrQrts+=nrQrts;
		else throw new RuntimeException("Invalid money value. Must be a positive integer");
	}
	
	/**
	 * @name getNrNickels
	 * @description Returns the number of nickels in the current Money object
	 * Precondition: none
	 * Postcondition: the nrNickels in the current Money object is returned
	 * @param none
	 * @return nrNickels -- the nrNickels in the current Money object
	 */
	public int getNrNickels(){
		return nrNickels;
	}
	
	/**
	 * @name getNrDimes
	 * @description Returns the number of dimes in the current Money object
	 * Precondition: none
	 * Postcondition: the nrDimes in the current Money object is returned
	 * @param none
	 * @return nrDimes -- the nrDimes in the current Money object
	 */
	public int getNrDimes(){
		return nrDimes;
	}
	
	/**
	 * @name getNrQrts
	 * @description Returns the number of quarters in the current Money object
	 * Precondition: none
	 * Postcondition: the nrQrts in the current Money object is returned
	 * @param none
	 * @return nrQrts -- the nrQrts in the current Money object
	 */
	public int getNrQrts(){
		return nrQrts;
	}
	
	/**
	 * @name toString 
	 * @description Overrides the toString method
	 * 			returns a formatted String representing the number of
	 * 			nickels, dimes, quarters and the total monetary value
	 * 			in the current Money object
	 * Precondition: none
	 * Postcondition: a formatted String representing the number of
	 * 			nickels, dimes, quarters and the total monetary value
	 * 			in the current Money object is returned
	 * @param none
	 * @return a formatted String representing the number of
	 * 			nickels, dimes, quarters and the total monetary value
	 * 			in the current Money object
	 */
	public String toString(){
		return "Nickels: "+nrNickels
				+"\nDimes: "+nrDimes
				+"\nQuarters: "+nrQrts
				+"\nTotal: $"+df.format(getTotal()/100);
	}
	
	/**
	 * @name main
	 * @description Unit testing 
	 * Precondition: none
	 * Postcondition: Each Money method will be successfully tested
	 * @param String[] args
	 * @return none
	 */
	public static void main(String[] args) {
		System.out.println("Creating a new money object with no money...");
		Money testMoney = new Money(0,0,0);
		System.out.println(testMoney);
		System.out.println("\nTesting addNickels, addDimes, addQrts." +
				"+\nAdding 1 nickel, 4 dimes, 1 quarter. Expecting $0.70...");
		testMoney.addNickels(1);
		testMoney.addDimes(4);
		testMoney.addQrts(1);
		System.out.println(testMoney);
		System.out.println("\nTesting withdrawal. Withdrawaling $0.35. Expecting $0.35 left...");
		testMoney.withdrawal(35);
		System.out.println(testMoney);
		System.out.println("\nTesting withdrawal. Withdrawaling $0.35. Expecting $0.00 left...");
		testMoney.withdrawal(35);
		System.out.println(testMoney);

	}

}
