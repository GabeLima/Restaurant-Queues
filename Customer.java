public class Customer extends Restaurant {
	private static int totalCustomers;
	private int orderNumber;
	private String food;
	private int priceOfFood;
	private int timeArrived;
	private int timeToServe;
	private String abbString;
	private static int chefs;
	private boolean justSelected = true;
	/* Excess variable explanations:
	 * 
	 * @param abbString
	 * An abbreviated string representing the food selected
	 * 
	 * @param chefs
	 * Represents the number of chefs, as cookingTime is calculated in this
	 * class based on the number of chefs
	 * 
	 * @param justSelected
	 * Keeps track of if the customer was just added in order to accurately
	 * print when the customer selected their food
	 * 
	 */

	public Customer() {

	}

	
	/* 
	 * This is a generic constructor that handles most of the customer class,
	 * takes the order number of the customer as an input
	 * 
	 * 
	 * 
	 */
	public Customer(int newOrderNum) {
		orderNumber = newOrderNum;
		food = selectFood();
		priceOfFood = foodCost(food);
		shortenString(food);
		timeToServe = cookingTime(food);
		priceOfFood = foodCost(food);
	}
	/* 
	 * Returns a string representation of the customer
	 * 
	 * 
	 */
	public String toString() {
		return "[# " + orderNumber + ", " + abbString + ", " + timeToServe + "]";
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public void printFood() {
		if(justSelected) {
			System.out.println("Customer #" + orderNumber + 
			  " has been seated with the order \"" + food + "\"");
		}
	}
	/* 
	 * This method shortens the String "food" and stores it in abbString
	 * 
	 */
	public void shortenString(String s) {
		if (s.equals("Cheeseburger")) {
			abbString = "C";
		}
		else if (s.equals("Steak")) {
			abbString = "S";
		}
		else if (s.equals("Grilled Cheese")) {
			abbString = "GC";
		}
		else if (s.equals("Chicken Tenders")) {
			abbString = "CT";
		}
		else if (s.equals("Chicken Wings")) {
			abbString = "CW";
		}
	}
	/*
	 * This method keeps track of the total number of customers that have arrived
	 * 
	 * 
	 * 
	 */
	public static void incrementTotalCustomers() {
		totalCustomers++;
	}
	
	/*
	 * This method keeps track of when the customer will get their food 
	 * by updating their timer
	 * 
	 * 
	 * 
	 */
	public void updateTimeToServe() {
		if(timeToServe == cookingTime(food)) {
			justSelected = true;
		}
		else
			justSelected = false;
		if (timeToServe != 0) {
			//justSelected = false;
			timeToServe -= 5;
		}
	}
	
	public boolean doneEating() {
		return (timeToServe == 0 && food != "");
	}
	private int randInt(int minVal, int maxVal) {
		int rand = minVal + (int) (Math.random() * (maxVal + 1));
		return rand;
	}
	public int getProfit() {
		return priceOfFood;
	}
	/*
	 * This method selects food at random for the customer
	 * 
	 * 
	 * 
	 */
	public String selectFood() {
		int value = randInt(0, 100);
		if (value < 20) {
			return "Cheeseburger";
		} else if (value < 40) {
			return "Steak";
		} else if (value < 60) {
			return "Grilled Cheese";
		} else if (value < 80) {
			return "Chicken Tenders";
		} else
			return "Chicken Wings";
	}
	/*
	 * This method sets the initial cooking time of the food the customer
	 * selected
	 * 
	 * @param timeAlteration
	 * Alters the avg time food is cooked based on the number of chefs
	 * 
	 * 
	 */
	public int cookingTime(String s) {
		int timeAlteration = 0;
		if (chefs == 3) {
		}
		else if (chefs > 3 && chefs < 6) {
			timeAlteration = (chefs - 3) * 5;
		}
		else if (chefs < 3) {
			timeAlteration = (chefs - 3) * 5;
		}
		else {
			timeAlteration = 10;
		}
		
		if (s.equals("Cheeseburger")) {
			return 25 + timeAlteration;
		}
		else if (s.equals("Steak")) {
			return 30 + timeAlteration;
		}
		else if (s.equals("Grilled Cheese")) {
			return 15 + timeAlteration;
		}
		else if (s.equals("Chicken Tenders")) {
			return 25 + timeAlteration;
		}
		else if (s.equals("Chicken Wings")) {
			return 30 + timeAlteration;
		}
		else
			return -1; // error
	}
	
	/*
	 * This method calculates the cost of the food the customer selected
	 * 
	 * 
	 */
	public int foodCost(String s) {
		if (s.equals("Cheeseburger")) {
			return 15;
		} else if (s.equals("Steak")) {
			return 25;
		} else if (s.equals("Grilled Cheese")) {
			return 10;
		} else if (s.equals("Chicken Tenders")) {
			return 10;
		} else if (s.equals("Chicken Wings")) {
			return 20;
		} else
			return -1;
	}
	public void setChefs(int numChefs) {
		chefs = numChefs;
	}
	public String getFood() {
		return food;
	}
}
