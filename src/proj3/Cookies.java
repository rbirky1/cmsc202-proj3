/**
 * @file Cookies.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 10.26.12
 * @project CMSC 202 - Fall '12 - Project 3
 * @section Section 02
 */

package proj3;
/** This class represents a single cookie package
 *  Class Invariants: Cookies must have a flavor of enum CookieFlavors and a String equivalent assigned
 *  */
public class Cookies {

	private CookieFlavors flavor;
	private String flavorString;
		
	/**
	 * @name Cookies
	 * @description Constructor
	 * Precondition: flavor is a valid CookiesFlavor object
	 * Postcondition: a new cookie with the given CookiesFlavor is created
	 * @param flavor -- a valid CookiesFlavor object
	 * @return none
	 */
	public Cookies(CookieFlavors flavor){
		this.flavor = flavor;
		
		//Assign string equivalent
		switch (this.flavor){
		case CHOCOLATE_CHIP:
			flavorString = "Chocolate Chip"; break;
		case OATMEAL:
			flavorString = "Oatmeal"; break;
		case LEMON:
			flavorString = "Lemon"; break;
		}
	}
	
	/**
	 * @name Cookies
	 * @description Copy Constructor
	 * Precondition: otherCookie is a valid cookie object
	 * Postcondition: a new cookie with the same attributes is created
	 * @param otherCookie -- a valid cookie object
	 * @return none
	 */
	public Cookies(Cookies otherCookie){
		this(otherCookie.getEnumFlavor());
	}
	
	/**
	 * @name getFlavor
	 * @description Returns the flavor of the cookie as a String object
	 * Precondition: none
	 * Postcondition: the current cookie's flavor is returned as a String
	 * @param none
	 * @return flavorString -- the cookie flavor in String form
	 */
	public String getFlavor() {
		return this.flavorString;
	}
	
	/**
	 * @name getEnumFlavor
	 * @description Returns the flavor of the cookie as a CookieFlavors enum
	 * Precondition: none
	 * Postcondition: the current cokie's flavor is returned as an enum
	 * @param none
	 * @return flavor -- the cookie flavor in its original enum form
	 */
	public CookieFlavors getEnumFlavor(){
		return this.flavor;
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
		System.out.println("Creating a cookie package with flavor CookieFlavors.LEMON...");
		Cookies cookie = new Cookies(CookieFlavors.LEMON);
		System.out.println("\nTesting getEnumFlavor; expecting 'LEMON'...\n"+cookie.getEnumFlavor());
		System.out.println("\nTesting getFlavor; expecting 'Lemon'...\n"+cookie.getFlavor());
		System.out.println("\nTesting copy constructor; giving lemon cookie, expecting lemon cookie...");
		System.out.println("Original cookie flavor: "+cookie.getFlavor());
		Cookies copyCookie = new Cookies(cookie);
		System.out.println("Copy cookie flavor: "+copyCookie.getFlavor());
	}

}
