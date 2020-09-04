//DON'T UNCOMMENT THE FOLLOWING THREE LINES.
//[Student ID]
//[Student name] (as on eStudent)
//[] Declaration from student that they haven't viewed another person's code for this assignment. 
//(Add a x between the brackets)

package shopping;

public class ShoppingCart {
	public CartItem[] items; // keeps the items in cart
	public int itemCount; // keeps a track of number of items added

	/**
	 * DO NOT MODIFY Constructor
	 */
	public ShoppingCart() {
		items = new CartItem[100]; // capacity 100
		itemCount = 0; // currently empty
	}

	/**
	 * DO NOT MODIFY
	 * 
	 * @return number of items currently in the cart
	 */
	public int size() {
		return itemCount;
	}

	/**
	 * 
	 * P/CR ATTEMPT TO add the item passed as parameter to the end of the cart. You
	 * may assume that an item with the same name DOES NOT exist in the cart.
	 * 
	 * @param itemToAdd
	 * @return true if there was space and the item is not null and hence added,
	 *         false if the array was already full.
	 */
	public boolean addItemBasic(CartItem itemToAdd) {

		if (this.itemCount >= 100)
			return false;

		if (itemToAdd == null)
			return false;

		this.items[this.itemCount++] = itemToAdd;

		return true;
	}

	/**
	 * 
	 * P/CR
	 * 
	 * @param idx
	 * @return null if the index is invalid (in that no valid item exists at that
	 *         index), otherwise return the item at given index
	 */
	public CartItem getItem(int idx) {

		if (idx < 0 || idx > 100)
			return null;

		return this.items[idx];
	}

	/**
	 * 
	 * P/CR
	 * 
	 * @return total savings based on discounts on each item in the shopping cart.
	 *         for example, if there are two items I1 ($24 each, quantity 3,
	 *         discounted by 10%) I2 ($15 each, quantity 5, discounted by 15%)
	 *         return 18.45
	 */
	public double getTotalSavings() {

		double totalSaving = 0;

		for (int i = 0; i < itemCount; i++) {
			totalSaving += items[i].getTotalDiscount();
		}

		return totalSaving;
	}

	/**
	 * 
	 * P/CR
	 * 
	 * @return the total for the shopping cart for example, if there are two items
	 *         I1 ($24 each, quantity 3, discounted by 10%) I2 ($15 each, quantity
	 *         5, discounted by 15%) return 128.55
	 */
	public double getTotal() {

		double total = 0;

		for (int i = 0; i < itemCount; i++) {
			total += items[i].getDiscountedUnitPrice() * items[i].quantity;
		}

		return total;
	}

	/**
	 * 
	 * P/CR replace the item at index idx, if any, with itemToReplaceWith, if not
	 * null
	 * 
	 * @param idx
	 * @param itemToReplaceWith
	 * @return true if index was valid and itemToReplaceWith was not null, and item
	 *         at index idx was replaced with itemToReplaceWith, otherwise false.
	 */
	public boolean replace(int idx, CartItem itemToReplaceWith) {

		if (idx < 0 || idx > 100)
			return false;

		if (itemToReplaceWith == null)
			return false;

		if (idx >= itemCount)
			return false;

		items[idx] = itemToReplaceWith;

		return true;
	}

	/**
	 * 
	 * P/CR if present, remove the item at index idx, and reduce the number of items
	 * in cart by 1.
	 * 
	 * @param idx
	 * @return false if index was invalid. true if index was valid and item removed
	 */
	public boolean removeItem(int idx) {

		if (idx < 0 || idx > 100)
			return false;

		if (idx >= itemCount)
			return false;

		for (int i = idx; i < itemCount; i++) {

			items[i] = items[i + 1];

		}

		itemCount--;

		return true;
	}

	/**
	 * 
	 * D/HD
	 * 
	 * @param itemNameToReduce   (see JUnit tests for samples used regarding cases
	 *                           and all)
	 * @param quantityToReduceBy
	 * @return -1 if item not found or negative quantity to remove
	 * 
	 *         0 if the quantity for the item in the cart is less than or equal to
	 *         the quantity to reduce it by, and completely remove the item from the
	 *         cart
	 * 
	 *         1 if the quantity for the item in the cart is more than the quantity
	 *         to reduce it by, and the quantity in cart should be reduced
	 *         accordingly.
	 * 
	 *         NOTE: String comparison should be case insensitive
	 */
	public int reduceItemBy(String itemNameToReduce, int quantityToReduceBy) {

		if (quantityToReduceBy < 0)
			return -1;

		int cartItemIndex = -1;

		for (int i = 0; i < itemCount; i++) {

			if (items[i].item.name.equalsIgnoreCase(itemNameToReduce)) {
				cartItemIndex = i;
				break;
			}
		}

		if (cartItemIndex == -1)
			return -1;

		if ((items[cartItemIndex].quantity - quantityToReduceBy) <= 0) {
			this.removeItem(cartItemIndex);
			return 0;
		}

		this.items[cartItemIndex].quantity -= quantityToReduceBy;

		return 1;
	}

	/**
	 * 
	 * D
	 * 
	 * @return an array of ShoppingCart objects (say, result) such that: the first
	 *         item is a ShoppingCart object containing items from the calling
	 *         object that have a discount between 0 (inclusive) and 10 (exclusive),
	 *         the second item is a ShoppingCart object containing items from the
	 *         calling object that have a discount between 10 (inclusive) and 20
	 *         (exclusive). and so on ... the last item represents items that have a
	 *         discount of exactly 100 (FREE FREE FREE!)
	 */
	public ShoppingCart[] getGroupedByDiscount() {

		ShoppingCart[] shoppingCartArray = { new ShoppingCart(), new ShoppingCart(), new ShoppingCart(),
				new ShoppingCart(), new ShoppingCart(), new ShoppingCart(), new ShoppingCart(), new ShoppingCart(),
				new ShoppingCart(), new ShoppingCart(), new ShoppingCart() };

		for (int i = 0; i < itemCount; i++) {

			int discount = items[i].discountPercentage;

			if ((discount >= 0) && (discount < 10)) {
				shoppingCartArray[0].addItemBasic(items[i]);
			}

			else if ((discount >= 10) && (discount < 20)) {
				shoppingCartArray[1].addItemBasic(items[i]);
			}

			else if ((discount >= 20) && (discount < 30)) {
				shoppingCartArray[2].addItemBasic(items[i]);
			}

			else if ((discount >= 30) && (discount < 40)) {
				shoppingCartArray[3].addItemBasic(items[i]);
			}

			else if ((discount >= 40) && (discount < 50)) {
				shoppingCartArray[4].addItemBasic(items[i]);
			}

			else if ((discount >= 50) && (discount < 60)) {
				shoppingCartArray[5].addItemBasic(items[i]);
			}

			else if ((discount >= 60) && (discount < 70)) {
				shoppingCartArray[6].addItemBasic(items[i]);
			}

			else if ((discount >= 70) && (discount < 80)) {
				shoppingCartArray[7].addItemBasic(items[i]);
			}

			else if ((discount >= 80) && (discount < 90)) {
				shoppingCartArray[8].addItemBasic(items[i]);
			}

			else if ((discount >= 90) && (discount < 100)) {
				shoppingCartArray[9].addItemBasic(items[i]);
			}

			else {
				shoppingCartArray[10].addItemBasic(items[i]);
			}

		}

		return shoppingCartArray;
	}

	/**
	 * 
	 * @param itemame
	 * @return index of the items if and Item with similar name exists else -1
	 */
	private int searchItem(String itemame) {

		for (int i = 0; i < itemCount; i++) {

			if (items[i].item.name.equalsIgnoreCase(itemame)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 
	 * HD
	 * 
	 * @param itemToAdd
	 * @return
	 * 
	 * @return -1 if passed item is null, or, if the item doesn't already exist and
	 *         the cart is full
	 * 
	 *         return 0 if an item with the same name already exists in cart. in
	 *         this case, you must choose which item has a lower price (after
	 *         considering discounts), and use that object. if the two object have
	 *         the same final lower price, keep the one already in the cart.
	 * 
	 *         for example, if a ShoppingCart object myCart already contains object
	 *         constructed as CartItem c1 = new CartItem(new StockItem("I1", 29.9),
	 *         6, 0); at index 5 and we call the function as: CartItem c2 = new
	 *         CartItem(new StockItem("I1", 35.9), 3, 30); myCart.addItem(c2);
	 *         object c2 should be placed instead of object c1 at index 5 (quantity
	 *         updated to 9) since it has a lower price 2(70% of 35.9 is less than
	 *         100% of 29.9) However, if the addItem is called as: CartItem c3 = new
	 *         CartItem(new StockItem("I1", 35.9), 7, 10); myCart.addItem(c3);
	 *         object c1 should be retained at index 5 but quantity updated to 13
	 *         (100% of 29.9 is less than 90% of 35.9) Similarly, if the addItem is
	 *         called as: CartItem c4 = new CartItem(new StockItem("I1", 32.89), 1,
	 *         10); myCart.addItem(c4); object c1 should be retained at index 5 but
	 *         quantity updated to 7 (100% of 29.9 is equal to 90% of 32.89) return
	 *         1 if item doesn't exist in the cart. in this case, an entry for the
	 *         item should be added at the end.
	 */
	public int addItemAdvanced(CartItem itemToAdd) {

		if (itemToAdd == null)
			return -1;

		int itemIndex = this.searchItem(itemToAdd.item.name);

		boolean itemExists = itemIndex != -1;

		if (!itemExists && itemCount >= 100)
			return -1;

		if (!itemExists && itemCount < 100) {
			this.addItemBasic(itemToAdd);
			return 1;
		}

		int quantity = items[itemIndex].quantity + itemToAdd.quantity;

		if (items[itemIndex].getDiscountedUnitPrice() > itemToAdd.getDiscountedUnitPrice())
			this.replace(itemIndex, itemToAdd);

		items[itemIndex].quantity = quantity;

		return 0;
	}

	/**
	 * 
	 * HD Say I make a shopping list and my partner makes another shopping list
	 * separately. And once we do that, we'd like to merge the two lists so if I
	 * added 3 liters of milk and my partner added 2 liters of milk, the sum of the
	 * two (5 liters) should be added to the resulting list.
	 * 
	 * note that if the prices (after all discounts are considered) are different,
	 * the item with the lower price (after any discounts) should be added.
	 * 
	 * so if the product in the first cart is "milk", unit price 1.8, quantity 3,
	 * discount 10% and the product in the second cart is "milk", unit price 2.0,
	 * quantity 2, discount 20% the product to be added in the merged list should be
	 * "milk", unit price 2.0, quantity 5, discount 20% (i'll let you work out the
	 * math)
	 * 
	 * in case of tie, unit price and discount from the item from calling object
	 * should be added
	 * 
	 * 
	 * @param other
	 * @return A shopping cart with the two carts merged. The order of items in the
	 *         resulting cart must be: all items in calling object cart (quantities
	 *         adjusted if they were present in the parameter object cart as well)
	 *         followed by all items in the parameter object cart (that weren't in
	 *         the calling object cart). for example, if there are two items in the
	 *         calling object cart: I1 ($24 each, quantity 3, discounted by 10%) I2
	 *         ($15 each, quantity 5, discounted by 15%) and two items in the
	 *         calling object cart: I1 ($19 each, quantity 5, discounted by 10%) I3
	 *         ($10 each, quantity 3) the resulting cart should have three items (in
	 *         that order): I1 ($19 each, quantity 8, discounted by 10%) I2 ($15
	 *         each, quantity 5, discounted by 15%) I3 ($10 each, quantity 3)
	 */
	public ShoppingCart merge(ShoppingCart other) {

		for (int i = 0; i < other.itemCount; i++) {
			
			this.addItemAdvanced(other.getItem(i));
			
		}

		return this;
	}

}
