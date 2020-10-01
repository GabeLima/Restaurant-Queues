import java.util.ArrayList;

public class Restaurant{
	private int restNumber;
	private int size = 0;
	private static int capacity;
	private int profit = 0;
	private int customersLost = 0;
	private int customerTime = 0;
	private int customersServed = 0;
	private ArrayList<Customer> queue = new ArrayList<Customer>();
	/* Excess variable explanations:
	 * 
	 * @param restNumber
	 * Keeps track of the restaurant number
	 * 
	 * @param size
	 * Keeps track of the number of customers currently in the restaurant
	 * 
	 * @param capacity
	 * Keeps track of the user input of restaurant capacity
	 * 
	 * @param profit
	 * Keeps track of the profit this restaurant made
	 * 
	 * @param customersLost
	 * Keeps track of the number of customers lost
	 * 
	 * @param customerTime
	 * Keeps track of the total time customers spend in the restaurant
	 * @param customersServed
	 * Keeps track of the number of customers served
	 * 
	 * @param queue
	 * An ArrayList of type Customer used to queue and dequeue customers
	 * 
	 */
	public Restaurant() {
		
	}
	public Restaurant(int x) {
		restNumber = x;
	}

	/*
	 * This method adds customers the the ArrayList queue
	 * 
	 * 
	 * 
	 */
	public void enqueue(Customer c) {
		if (size != capacity) {
			queue.add(c);
			size++;
			queue.get(0).incrementTotalCustomers();
		}
		else {
			System.out.println("Customer #" + c.getOrderNumber() + 
			  " cannot be seated! They have left the restaurant!");
			customersLost++;
		}
	}
	/*
	 * This method removes customers from the ArrayList queue
	 * 
	 * 
	 */
	public Customer dequeue() {
		for (int i = 0; i < queue.size(); i ++) {
			if (queue.get(i).doneEating()) {
				System.out.println("Customer #" + queue.get(i).getOrderNumber() 
				  + " has enjoyed their food! $" + queue.get(i).getProfit() + 
				  " profit.");
				profit += queue.get(i).getProfit();
				queue.remove(i);
				customersServed++;
				size--;
			}
		}
		return new Customer();
	}
	public Customer peek() {
		return queue.get(queue.size()-1);
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	/*
	 * This method returns a string representation of the restaurant with all
	 * of its respective customers and their respective times, orders, etc.
	 * 
	 * 
	 */
	public String toString() { //prints rest.
		String tempString = "R" + (restNumber+1) +": {";
		for(int i = 0; i < queue.size(); i++) {
			tempString += queue.get(i).toString();
		}
		tempString += "}";
		return tempString;
	}
	/*
	 * This method sets the capacity (max size) of the ArrayList queue
	 */
	public void setCapacity(int x) {
		capacity = x;
		queue = new ArrayList<Customer>(capacity);
	}
	/*
	 * This method is used to update the customers time per simulation and is
	 * largely managed by DiningSimulator
	 * 
	 */
	public void updateCustomerTime() {
		for(int i = 0; i < queue.size(); i ++) {
			queue.get(i).updateTimeToServe();
			customerTime+=5;
		}
	}
	public int getProfit() {
		return profit;
	}
	public int getCustomersLost() {
		return customersLost;
	}
	public int getCustomerTime() {
		return customerTime;
	}
	/*
	 * This method prints the food customers select
	 * 
	 * 
	 */
	public void printCustomerFood() {
		for (int i = 0; i < queue.size(); i ++) {
			queue.get(i).printFood();
		}
	}
	
	public int getCustomersServed() {
		return customersServed;
	}
}
