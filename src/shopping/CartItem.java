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
	 * @param q  for quantity (set the higher of 0 and q to quantity) set
	 *           discountPercentage to 0
	 */
	public CartItem(StockItem it, int q) {

		this(it, q, 0);

	}

	/**
	 * 
	 * @param it       for item, assume its not null
	 * @param q        for quantity (set the higher of 0 and q to quantity)
	 * @param discount (constrain discount between 0 and 100 before copying into
	 *                 discountPercentage). for example, if discount = -5,
	 *                 discountPercentage should become 0 if discount = 12,
	 *                 discountPercentage should become 12 if discount = 150,
	 *                 discountPercentage should become 100
	 */
	public CartItem(StockItem it, int q, int discount) {
		this.discountPercentage = discount < 0 ? 0 : discount > 100 ? 100 : discount;
		this.item = it;
		this.quantity = q < 1 ? 0 : q;
	}

	/**
	 * 
	 * @return the total cost of the item (after discounting if so) for example, if
	 *         the unitPrice of item is 1.5, quantity is 3, discountPercentage is 0,
	 *         return 4.5 if the unitPrice of item is 1.5, quantity is 3,
	 *         discountPercentage is 20, return 3.6
	 */
	public double totalCost() {
		this.item.updateRegularPrice(-1 * this.discountPercentage);
		return this.item.unitPrice * this.quantity;
	}

	/**
	 * 
	 * @return the discount value per unit for example, if the unitPrice of item is
	 *         1.5 and discountPercentage is 20, return 0.3
	 */
	public double getDiscountPerUnit() {
		return this.item.unitPrice * (this.discountPercentage / 100d);
	}

	/**
	 * 
	 * @return the discounted price for the calling object. for example, if
	 *         unitPrice = 2.4, discountPercentage = 10, return 2.16
	 */
	public double getDiscountedUnitPrice() {

		this.item.updateRegularPrice(-1 * this.discountPercentage);

		return this.item.unitPrice;
	}

	/**
	 * 
	 * @return total discount for this item for example, if unitPrice = 2.4,
	 *         quantity = 4, discountPercentage = 10, return 0.96
	 */
	public double getTotalDiscount() {
		return this.item.unitPrice * (this.discountPercentage / 100d) * this.quantity;
	}

	/**
	 * @param other
	 * @return 1 if calling object is "more than" parameter object -1 if calling
	 *         object is "less than" parameter object 0 if calling object is "equal
	 *         to" parameter object
	 * 
	 *         IMPORTANT!!! Ordering criterion: First: StockItem instance variable
	 *         (item) Second: Discount percentage
	 */
	public int compareTo(CartItem other) {

		if (this.item.compareTo(other.item) == 0) {

			if (this.discountPercentage > other.discountPercentage)
				return 1;
			else if (this.discountPercentage < other.discountPercentage)
				return -1;

		} else {

			return this.item.compareTo(other.item);

		}
		return 0;
	}

	/**
	 * return String version of the item NOTE: an asterisk (*) should be added in
	 * front of discounted unit prices
	 * 
	 * If the final price is of the form: - 5.18 or 5.181 or 5.189 or 5.1899999:
	 * display 5.18 - 5.1: display 5.1 - 5 (or 5.0): display 5
	 * 
	 * for example, if item represents name = "BIBS", unitPrice = 5.9, quantity = 6,
	 * discountPercentage = 0, return "BIBS (6 @ $5.9)" if item represents name =
	 * "BIBS", unitPrice = 5.9, quantity = 6, discountPercentage = 10, return "BIBS
	 * (6 @ *$5.31)"
	 */
	public String toString() {

		String priceStr = "" + this.getDiscountedUnitPrice();

		String newPriceStr = "";
		String precisionStr = "";
		String price = "";

		int precisionCount = 0;
		boolean hasPrecision = false;

		for (int i = 0; i < priceStr.length(); i++) {

			int ascii = (int) priceStr.charAt(i);

			if (ascii == 46) {

				hasPrecision = true;

			} else if (ascii >= 48 && ascii <= 57) {

				if (hasPrecision) {
					if (precisionCount >= 2)
						break;
					precisionStr += priceStr.charAt(i);
					precisionCount++;

				} else {
					newPriceStr += priceStr.charAt(i);
				}
			}
		}

		if (!hasPrecision) {
			
			price = newPriceStr;
			
		} else {

			if (precisionStr.length() == 1) {
				if (precisionStr.charAt(0) == '0') {
					price = newPriceStr;
				} else {
					price = newPriceStr + '.' + precisionStr;
				}
			} else {
				if (precisionStr.charAt(0) == '0' && precisionStr.charAt(1) == '0') {
					price = newPriceStr;
				} else if (precisionStr.charAt(1) == '0') {
					price = newPriceStr + '.' + precisionStr.charAt(0);
				} else {
					price = newPriceStr + '.' + precisionStr;
				}
			}

		}
		
		if(discountPercentage == 0) {
			//BIBS (6 @ $5.9)
			return this.item.name +" (" + this.quantity + " @ $" + price + ")";
		}
		
		//BIBS (6 @ *$5.31)
		return this.item.name +" (" + this.quantity + " @ *$" + price + ")";
	}
}
