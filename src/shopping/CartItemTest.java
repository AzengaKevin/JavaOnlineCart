package shopping;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartItemTest {
	public static int score = 0;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();

	public StockItem item1, item2, item3;
	public CartItem cartItem1, cartItem2_1, cartItem2_2, cartItem2_3, cartItem3;

	@BeforeEach
	public void setup() {
		currentMethodName = null;

		item1 = new StockItem("soccer ball (size 5)", 39.9);
		item2 = new StockItem("sports bibs", 5.9);
		item3 = new StockItem("jersey", 29);

		cartItem1 = new CartItem(item1, 5);
		cartItem2_1 = new CartItem(item2, 4);
		cartItem2_2 = new CartItem(item2, 5, 20);
		cartItem2_3 = new CartItem(item2, 5, 20);
		cartItem3 = new CartItem(item3, 1);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:CartItem(StockItem,int)", marks=4)
	public void testCartItemStockItemInt() {
		assertEquals(item1, cartItem1.item);
		assertEquals(item2, cartItem2_1.item);
		
		assertEquals(0, cartItem1.discountPercentage);
		assertEquals(5, cartItem1.quantity);
		
		assertEquals(0, cartItem2_1.discountPercentage);
		assertEquals(4, cartItem2_1.quantity);
		
		cartItem1 = new CartItem(item1, -5);
		assertEquals(0, cartItem1.quantity);
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:CartItem(StockItem,int,int)", marks=4)
	public void testCartItemStockItemIntInt() {
		assertEquals(item2, cartItem2_2.item);
		assertEquals(20, cartItem2_2.discountPercentage);
		assertEquals(5, cartItem2_2.quantity);

		cartItem2_2 = new CartItem(item2, 7, -4);
		assertEquals(0, cartItem2_2.discountPercentage);
		assertEquals(7, cartItem2_2.quantity);

		cartItem2_2 = new CartItem(item2, 1, 103);
		assertEquals(100, cartItem2_2.discountPercentage);
		assertEquals(1, cartItem2_2.quantity);

		cartItem2_2 = new CartItem(item2, -5, 103);
		assertEquals(100, cartItem2_2.discountPercentage);
		assertEquals(0, cartItem2_2.quantity);
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:totalCost()", marks=4)
	public void testTotalCost() {
		assertEquals(39.9*5, cartItem1.totalCost(), 0.01);
		assertEquals(5.9*4, cartItem2_1.totalCost(), 0.01);
		assertEquals(5.9*5*0.8, cartItem2_2.totalCost(), 0.01);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:getDiscountPerUnit()", marks=4)
	public void testGetDiscountPerUnit() {
		assertEquals(0, cartItem1.getDiscountPerUnit(), 0.01);
		assertEquals(0, cartItem2_1.getDiscountPerUnit(), 0.01);
		assertEquals(5.9*0.2, cartItem2_2.getDiscountPerUnit(), 0.01);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:getDiscountedUnitPrice()", marks=4)
	public void testGetDiscountedUnitPrice() {
		assertEquals(39.9, cartItem1.getDiscountedUnitPrice(), 0.01);
		assertEquals(5.9, cartItem2_1.getDiscountedUnitPrice(), 0.01);
		assertEquals(5.9*0.8, cartItem2_2.getDiscountedUnitPrice(), 0.01);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:getTotalDiscount()", marks=4)
	public void testGetTotalDiscount() {
		assertEquals(0, cartItem1.getTotalDiscount(), 0.01);
		assertEquals(0, cartItem2_1.getTotalDiscount(), 0.01);
		assertEquals(5.9*5*0.2, cartItem2_2.getTotalDiscount(), 0.01);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:compareTo(CartItem)", marks=4)
	public void testCompareTo() {
		assertEquals(1, cartItem1.compareTo(cartItem2_1));
		assertEquals(-1, cartItem2_1.compareTo(cartItem1));
		assertEquals(1, cartItem2_2.compareTo(cartItem2_1));
		assertEquals(-1, cartItem2_1.compareTo(cartItem2_2));
		assertEquals(0, cartItem2_2.compareTo(cartItem2_3));
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="CartItem:toString()", marks=4)
	public void testToString() {
		assertEquals("SOCCER BALL (SIZE 5) (5 @ $39.9)", cartItem1.toString());
		assertEquals("SPORTS BIBS (4 @ $5.9)", cartItem2_1.toString());
		assertEquals("SPORTS BIBS (5 @ *$4.72)", cartItem2_2.toString());
		//note that a value like 4.720000000000001 should be changed to 4.72
		
		CartItem item = new CartItem(new StockItem("Dummy", 4.719999), 5);
		assertEquals("DUMMY (5 @ $4.71)", item.toString());
		
		CartItem item2 = new CartItem(new StockItem("Kit", 12.5), 5, 20);
		assertEquals("KIT (5 @ *$10)", item2.toString());
		
		assertEquals("JERSEY (1 @ $29)", cartItem3.toString());
		
		CartItem testItem = new CartItem(new StockItem("Test", 5.138), 1);
		assertEquals("TEST (1 @ $5.13)", testItem.toString());
		
		testItem = new CartItem(new StockItem("Test", 5.132), 1);
		assertEquals("TEST (1 @ $5.13)", testItem.toString());
		
		testItem = new CartItem(new StockItem("Test", 5.135), 1);
		assertEquals("TEST (1 @ $5.13)", testItem.toString());
		
		testItem = new CartItem(new StockItem("Test", 5), 1);
		assertEquals("TEST (1 @ $5)", testItem.toString());
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@AfterEach
	public void logSuccess() throws NoSuchMethodException, SecurityException {
		if(currentMethodName != null && !methodsPassed.contains(currentMethodName)) {
			methodsPassed.add(currentMethodName);
			Method method = getClass().getMethod(currentMethodName);
			Graded graded = method.getAnnotation(Graded.class);
			score+=graded.marks();
			result+=graded.description()+" passed. Marks awarded: "+graded.marks()+"\n";
		}
	}

	@AfterAll
	public static void wrapUp() throws IOException {
		System.out.println("\nShoppingCart\n");
		if(result.length() != 0) {
			result = result.substring(0, result.length()-1); //remove the last "\n"
		}
		System.out.println(result);
		System.out.println("Indicative mark: "+score);
		//		String name = "NAME_ME_HERE".replace("./","");
		//		String markMessage = name.substring(0, 8)+","+score;
		//		System.out.println(markMessage+",\""+result+"\"\n");
		//		File file = new File(name.substring(0, 8)+".txt");
		//		FileWriter writer = new FileWriter(file);
		//		writer.write(markMessage+",\""+result+"\"\n");
		//		writer.flush();
		//		writer.close();
	}


}