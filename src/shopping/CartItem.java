//DON'T UNCOMMENT THE FOLLOWING THREE LINES.
//[Student ID]
//[Student name] (as on eStudent)
//[] Declaration from student that they haven't viewed another person's code for this assignment. 
//(Add a x between the brackets)

package shopping;

public class CartItem {
	public StockItem item;
	public int quantity;
	public int discountPercentage;
	
	/**
	 * 
	 * @param it for item
	 * @param q for quantity (set the higher of 0 and q to quantity)
	 * set discountPercentage to 0
	 */
	public CartItem(StockItem it, int q) {
	}
	
	/**
	 * 
	 * @param it for item, assume its not null
	 * @param q for quantity (set the higher of 0 and q to quantity)
	 * @param discount (constrain discount between 0 and 100 before copying into discountPercentage).
	 * for example, 
	 * if discount = -5, discountPercentage should become 0
	 * if discount = 12, discountPercentage should become 12
	 * if discount = 150, discountPercentage should become 100
	 */
	public CartItem(StockItem it, int q, int discount) {
	}

	/**
	 * 
	 * @return the total cost of the item (after discounting if so)
	 * for example,
	 * if the unitPrice of item is 1.5, quantity is 3, discountPercentage is 0, return 4.5
	 * if the unitPrice of item is 1.5, quantity is 3, discountPercentage is 20, return 3.6
	 */
	public double totalCost() {
		return 0;
	}
	
	/**
	 * 
	 * @return the discount value per unit
	 * for example, 
	 * if the unitPrice of item is 1.5 and discountPercentage is 20, return 0.3
	 */
	public double getDiscountPerUnit() {
		return 0;
	}
	
	/**
	 * 
	 * @return the discounted price for the calling object.
	 * for example, if unitPrice = 2.4, discountPercentage = 10, return 2.16
	 */
	public double getDiscountedUnitPrice() {
		return 0;
	}
	
	/**
	 * 
	 * @return total discount for this item
	 * for example, if unitPrice = 2.4, quantity = 4, discountPercentage = 10, return 0.96
	 */
	public double getTotalDiscount() {
		return 0;
	}
	
	/**
	 * @param other
	 * @return
	 * 1 if calling object is "more than" parameter object
	 * -1 if calling object is "less than" parameter object
	 * 0 if calling object is "equal to" parameter object
	 * 
	 * IMPORTANT!!! 
	 * Ordering criterion: 
	 * First: StockItem instance variable (item)
	 * Second: Discount percentage
	 */
	public int compareTo(CartItem other) {
		return 0;
	}
	
	/**
	 * return String version of the item
	 * for example,
	 * if item represents name = "BIBS", unitPrice = 5.9, quantity = 6, discountPercentage = 0,
	 * 		return "BIBS (6 @ $5.9)"
	 * if item represents name = "BIBS", unitPrice = 5.9, quantity = 6, discountPercentage = 10,
	 * 		return "BIBS (6 @ *$5.31)"
	 */
	public String toString() {
		return "";
	}
}
