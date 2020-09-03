//DON'T UNCOMMENT THE FOLLOWING THREE LINES.
//[Student ID]
//[Student name] (as on eStudent)
//[] Declaration from student that they haven't viewed another person's code for this assignment. 
//(Add a x between the brackets)

package shopping;

public class StockItem {
	public String name;
	public double unitPrice;
	private static final int GREATER = 1, LESSER = -1, EQUAL = 0;

	/**
	 * 
	 * @param str for name (assume not null)
	 * @param u   for unitPrice
	 * 
	 *            assign the upper case version of str to name for this method, the
	 *            only String functions allowed are length() and charAt(int). use of
	 *            any other function will result in an automatic zero for this
	 *            stage. assign the higher of 0 and u to unitPrice
	 * 
	 *            for example, if str = "Goal posts (1.5m)" and u = 259.9, name
	 *            should become "GOAL POSTS (1.5M)" and unitPrice should become
	 *            259.9
	 */
	public StockItem(String str, double u) {

		this.unitPrice = u < 0 ? 0 : u;

		String upperStr = "";

		for (int i = 0; i < str.length(); i++) {

			// Convert each character to ASCII
			int ascii = (int) str.charAt(i);

			// Check only for lowers characters
			if ((ascii >= 97) && (ascii <= 122)) {
				// Convert them and append to the new string
				upperStr += (char) (ascii - 32);
			} else {
				// Just append as it is
				upperStr += str.charAt(i);
			}
		}

		this.name = upperStr;
	}

	/**
	 * 
	 * @param percentageChange for example, if the unit price of the calling object
	 *                         is 1.2 before the method is called with parameter 10,
	 *                         it should become 1.32 after the method is called.
	 */
	public void updateRegularPrice(int percentageChange) {
		this.unitPrice += this.unitPrice * (percentageChange / 100.0);
	}

	/**
	 * @param other
	 * @return 1 if calling object is "more than" parameter object -1 if calling
	 *         object is "less than" parameter object 0 if calling object is "equal
	 *         to" parameter object
	 * 
	 *         IMPORTANT!!! Ordering criteria: first: unitPrice second: name (in
	 *         lexicographic order - this is the standard ordering criteria for
	 *         String compareTo)
	 * 
	 *         For example if calling object represents "BALL" with unit price 39.9
	 *         and parameter object represents "BIBS" with unit price 5.9, return 1
	 * 
	 *         if calling object represents "BIBS" with unit price 5.9 and parameter
	 *         object represents "BALL" with unit price 39.9, return -1
	 * 
	 *         if calling object represents "BALL (SIZE 5)" with unit price 5.9 and
	 *         parameter object represents "BIBS" with unit price 5.9, return -1
	 * 
	 *         if calling object represents "BIBS" with unit price 5.9 and parameter
	 *         object represents "BALL (SIZE 5)" with unit price 5.9, return 1
	 * 
	 *         if calling object represents "BALL" with unit price 5.9, and
	 *         parameter object represents "BIBS (XL)" with unit price 5.9 return -1
	 * 
	 *         if calling object represents "BIBS (XL)" with unit price 5.9 and
	 *         parameter object represents "BALL" with unit price 5.9, return 1
	 * 
	 *         if calling object represents "BIBS (XL)" with unit price 5.9 and
	 *         parameter object represents "BIBS" with unit price 5.9, return 1
	 * 
	 *         if calling object represents "BIBS" with unit price 5.9 and parameter
	 *         object represents "BIBS" with unit price 5.9, return 0
	 */
	public int compareTo(StockItem other) {

		// Compare the prices first
		if (this.unitPrice > other.unitPrice)
			return GREATER;
		else if (this.unitPrice < other.unitPrice)
			return LESSER;

		// Check the strings now
		int thisNameLength = this.name.length();
		int otherNameLength = other.name.length();

		// What will be the maximum loop count
		int maxLoopLength = thisNameLength < otherNameLength ? thisNameLength : otherNameLength;

		for (int i = 0; i < maxLoopLength; i++) {

			if (this.name.charAt(i) > other.name.charAt(i))
				return GREATER;
			if (this.name.charAt(i) < other.name.charAt(i))
				return LESSER;

		}

		//If the first characters until the minimum length are equal
		
		if (thisNameLength > otherNameLength)
			return GREATER;

		if (thisNameLength < otherNameLength)
			return LESSER;

		return EQUAL;
	}
}
