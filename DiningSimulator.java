//import java.util.Collection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import HW1.GeneralLedger;
import HW1.Transaction;

public class DiningSimulator {
	private ArrayList<Restaurant> restaurants;
	private int chefs;//
	private int duration;//
	private double arrivalProb;//
	private int maxCustomerSize; //
	private int numRestaurants;//
	private int customersLost;
	private int totalServiceTime;
	private int customersServed;
	private int profit = 0;
	private int customerNumber = 1;
	/*
	 * Excess variable explanations:
	 * 
	 * @param customerNumber
	 * Keeps track of the number of customers
	 * 
	 * 
	 */
	
	
	public DiningSimulator() {
	}
	/*
	 * This method simulates the restaurant. I use a for loop to act as each
	 * simulation unit
	 * 
	 * 
	 * 
	 * 
	 */
	public double simulate() {
		for (int i = 0; i < duration; i++) { 
			System.out.println("Time: " + (i + 1));
			generateCustomers();
			for(int k = 0; k < restaurants.size(); k ++) {
				restaurants.get(k).updateCustomerTime();
				restaurants.get(k).dequeue();
			}
			for(int k = 0; k < restaurants.size(); k ++) {
				restaurants.get(k).printCustomerFood();
			}
			for(int k = 0; k < restaurants.size(); k ++) {
				System.out.println(restaurants.get(k).toString());
			}
		}
		for(int k = 0; k < restaurants.size(); k ++) {
			profit += restaurants.get(k).getProfit();
			customersLost += restaurants.get(k).getCustomersLost();
			totalServiceTime += restaurants.get(k).getCustomerTime();
			customersServed +=  restaurants.get(k).getCustomersServed();
		}
		
		return avgWait();
	}
	/*
	 * This constructor is essentially the brains of the operation. It obviously
	 * initially initializes all variables, but it also calls the simulate() method
	 * to get the program going.
	 * 
	 * 
	 * 
	 */
	public DiningSimulator(int numRest, int maxNumCust, double arrivalProb, int numChefs, int numSimulations) {
		numRestaurants = numRest;
		maxCustomerSize = maxNumCust;
		this.arrivalProb = arrivalProb;
		chefs = numChefs;
		duration = numSimulations;
		restaurants = new ArrayList<Restaurant>(numRestaurants);
		for(int i = 0; i < numRest; i ++) {
			restaurants.add(new Restaurant(i));
		}
		//restaurants.set(0).enqueue()
		restaurants.get(0).setCapacity(maxCustomerSize);
		simulate();
	}
	
	/*
	 * This method creates the customers that the restaurant class will eventually
	 * use
	 * 
	 */
	public void generateCustomers() { // need to make it so it chooses a restaurant at random
		for(int i = 0; i < restaurants.size(); i ++) {
			int tempInt = randInt(0, 100);
			for(int k = 0; k < 3; k ++) {
				if(arrivalProb * 100 > tempInt) {
					System.out.println("Customer # " + customerNumber + 
					  " has entered Restaurant " + (i + 1) + ".");
					restaurants.get(i).enqueue(new Customer(customerNumber++));
				}
				tempInt = randInt(0, 100);
			}
		}
	}
	public int getTotalServiceTime() {
		return totalServiceTime;
	}
	public int getProfit() {
		return profit;
	}
	public int totalCustomersServed() {
		return customersServed;
	}
	public double avgWait() {
		return (double)totalServiceTime/customersServed;
	}
	private int randInt(int minVal, int maxVal) {
		int rand = minVal + (int) (Math.random() * (maxVal + 1));
		return rand;
	}
	public int getCustomersLost() {
		return customersLost;
	}
	/*
	 * This is the main method, it handles user input.
	 * 
	 * @param CObj
	 * A customer Object used to interact with the Customer class
	 * @param DSObj
	 * A Dining Simulator Object used to interact with the DiningSimulator class
	 * 
	 * @throws Exception
	 * After each time customer input is asked for, their input is checked
	 * and an appropriate String wrapped within and Exception is thrown
	 * and printed to notify the user
	 * 
	 * 
	 */
	public static void main(String[] args) {
		Customer CObj = new Customer();
		Scanner stdin = new Scanner(System.in);
		boolean quitFlag = false;
		int maxNumCust = 0;
		double arrivalProb = 0;
		int numChefs = 0;
		int numSimulations = 0;
		int numRest = 0;
		while(!quitFlag) {
			System.out.println("Enter the number of restaurants: ");
			try {
				numRest = stdin.nextInt();
				if (numRest < 1) {
					throw new Exception("Number of restaurants must be greater than zero.");
				}
				System.out.println("Enter the maximum number of customers a restaurant can serve: ");
				maxNumCust = stdin.nextInt();
				if (maxNumCust < 1) {
					throw new Exception("Maximum number of customers must be greater than zero.");
				}
				System.out.println("Enter the arrival probability of a customer: ");
				arrivalProb = stdin.nextDouble();
				if (arrivalProb >1 || arrivalProb < 0) {
					throw new Exception("Arrival Probability must be between zero and one inclusive");
				}
				System.out.println("Enter the number of chefs:");
				numChefs = stdin.nextInt();
				if (numChefs < 1) {
					throw new Exception("Number of chefs must be greater than zero");
				}
				System.out.println("Enter the number of simulation units:");
				numSimulations = stdin.nextInt();
				if (numSimulations < 1) {
					throw new Exception("Number of simulations must be greater than zero");
				}
				CObj.setChefs(numChefs);
				DiningSimulator DSObj = new DiningSimulator(numRest, maxNumCust, arrivalProb, numChefs, numSimulations);
				System.out.println("Total customer time: " + 
				  DSObj.totalServiceTime);
				System.out.println("Total customers served: " + 
				  DSObj.totalCustomersServed());
				System.out.println("Average time customer waited per order: " + 
				  DSObj.avgWait());
				System.out.println("Total profit: " + DSObj.getProfit());
				System.out.println("Customers that left: " + 
				  DSObj.getCustomersLost());
				System.out.println("Would you like to try another simulation? ");
				String userInput = stdin.next();
				if(userInput.charAt(0) == 'n') {
					quitFlag = true;
				}
			}catch(InputMismatchException E) {
				System.out.println("Please enter input correctly");
				stdin.next();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
			stdin.close();
			System.out.println("Simulation ending...");

	}

}
