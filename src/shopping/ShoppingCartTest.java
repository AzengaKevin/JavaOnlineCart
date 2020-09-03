package shopping;


import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
	
	public static int score = 0;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();

	public StockItem item1_1, item1_2, item1_3;
	public CartItem cartItem1_1, cartItem1_2_1, cartItem1_2_2, cartItem1_2_3, cartItem1_3;
	public ShoppingCart cart1;

	public StockItem item2_1, item2_2, item2_3, item2_4;
	public CartItem cartItem2_1, cartItem2_2, cartItem2_3, cartItem2_4;
	public ShoppingCart cart2;

	public StockItem item3_1, item3_2, item3_3;
	public CartItem cartItem3_1, cartItem3_2, cartItem3_3;
	public ShoppingCart cart3;

	@BeforeEach
	public void setup() { 
		currentMethodName = null; //reset

		//CART 1:

		item1_1 = new StockItem("soccer ball (size 5)", 39.9);
		item1_2 = new StockItem("sports bibs", 5.9);
		item1_3 = new StockItem("jersey", 29);

		cartItem1_1 = new CartItem(item1_1, 5);
		cartItem1_2_1 = new CartItem(item1_2, 4);
		cartItem1_2_2 = new CartItem(item1_2, 5, 20);
		cartItem1_2_3 = new CartItem(item1_2, 5, 20);
		cartItem1_3 = new CartItem(item1_3, 1);

		cart1 = new ShoppingCart();

		cart1.addItemAdvanced(cartItem1_1);
		cart1.addItemAdvanced(cartItem1_2_1);
		cart1.addItemAdvanced(cartItem1_2_2);
		cart1.addItemAdvanced(cartItem1_2_3);
		cart1.addItemAdvanced(cartItem1_3);

		//CART 2:

		item2_1 = new StockItem("sports bibs", 5.9);
		item2_2 = new StockItem("jersey", 29);
		item2_3 = new StockItem("shin guard", 9.72);
		item2_4 = new StockItem("soccer ball (size 5)", 39.9);

		cartItem2_1 = new CartItem(item2_1, 5);
		cartItem2_2 = new CartItem(item2_2, 4);
		cartItem2_3 = new CartItem(item2_3, 5, 20);
		cartItem2_4 = new CartItem(item2_4, 1, 10);

		cart2 = new ShoppingCart();

		cart2.addItemBasic(cartItem2_1);
		cart2.addItemBasic(cartItem2_2);
		cart2.addItemBasic(cartItem2_3);
		cart2.addItemBasic(cartItem2_4);

		//CART 3:

		item3_1 = new StockItem("soccer ball (size 5)", 29.9);
		item3_2 = new StockItem("sports bibs", 6.49);
		item3_3 = new StockItem("jersey", 30);

		cartItem3_1 = new CartItem(item3_1, 5);
		cartItem3_2 = new CartItem(item3_2, 4, 10);
		cartItem3_3 = new CartItem(item3_3, 2, 2);

		cart3 = new ShoppingCart();

		cart3.addItemBasic(cartItem3_1);
		cart3.addItemBasic(cartItem3_2);
		cart3.addItemBasic(cartItem3_3);
	}

	@Test @Graded(description="ShoppingCart:addItemBasic(CartItem)", marks=4)
	public void testAddItemBasic() {
		assertEquals(4, cart2.itemCount);
		assertEquals(3, cart3.itemCount);

		boolean b = cart2.addItemBasic(null);
		assertFalse(b);
		assertEquals(4, cart2.itemCount);

		for(int i=0; i < 100; i++) {
			if(i < cart2.itemCount) {
				assertNotNull(cart2.items[i]);
			}
			else {
				assertNull(cart2.items[i]);
			}
			if(i < cart3.itemCount) {
				assertNotNull(cart3.items[i]);
			}
			else {
				assertNull(cart3.items[i]);
			}
		}

		assertEquals("SPORTS BIBS", cart2.items[0].item.name);
		assertEquals(5, cart2.items[0].quantity);
		assertEquals(5.9, cart2.items[0].item.unitPrice, 0.01);
		assertEquals(0, cart2.items[0].discountPercentage);

		assertEquals("JERSEY", cart2.items[1].item.name);
		assertEquals(4, cart2.items[1].quantity);
		assertEquals(29, cart2.items[1].item.unitPrice, 0.01);
		assertEquals(0, cart2.items[1].discountPercentage);

		assertEquals("SHIN GUARD", cart2.items[2].item.name);
		assertEquals(5, cart2.items[2].quantity);
		assertEquals(9.72, cart2.items[2].item.unitPrice, 0.01);
		assertEquals(20, cart2.items[2].discountPercentage);

		assertEquals("SOCCER BALL (SIZE 5)", cart2.items[3].item.name);
		assertEquals(1, cart2.items[3].quantity);
		assertEquals(39.9, cart2.items[3].item.unitPrice, 0.01);
		assertEquals(10, cart2.items[3].discountPercentage);

		int counter = 4;
		while(cart2.size()!=100) {
			b = cart2.addItemBasic(new CartItem(new StockItem("dummy", 1), 1));
			counter++;
			assertTrue(b);
			assertEquals(counter, cart2.itemCount);
		}

		b = cart2.addItemBasic(new CartItem(new StockItem("dummy", 1), 1));
		assertFalse(b); //cart2 is full
		assertEquals(100, cart2.itemCount);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();

	}

	@Test @Graded(description="ShoppingCart:getItem(int)", marks=4)
	public void testGetItem() {
		assertNull(cart2.getItem(-1));
		assertNull(cart2.getItem(4));
		assertNull(cart2.getItem(5));
		assertNull(cart2.getItem(6));
		assertNull(cart2.getItem(7));

		CartItem cartItem = cart2.getItem(0);

		assertNotNull(cartItem);
		assertEquals("SPORTS BIBS", cartItem.item.name);
		assertEquals(5, cartItem.quantity);
		assertEquals(5.9, cartItem.item.unitPrice, 0.01);
		assertEquals(0, cartItem.discountPercentage);

		cartItem = cart2.getItem(3);

		assertNotNull(cartItem);
		assertEquals("SOCCER BALL (SIZE 5)", cartItem.item.name);
		assertEquals(1, cartItem.quantity);
		assertEquals(39.9, cartItem.item.unitPrice, 0.01);
		assertEquals(10, cartItem.discountPercentage);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:getTotalSavings()", marks=4)
	public void testGetTotalSavings() {
		assertEquals(13.71, cart2.getTotalSavings(), 0.01);
		assertEquals(3.796, cart3.getTotalSavings(), 0.01);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:getTotal()", marks=4)
	public void testGetTotal() {
		assertEquals(220.29, cart2.getTotal(), 0.01);
		assertEquals(231.664, cart3.getTotal(), 0.01);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:replace(int,CartItem)", marks=4)
	public void testReplace() {
		boolean b = cart2.replace(-1, new CartItem(new StockItem("dummy", 1), 1));
		assertFalse(b);
		assertEquals(4,cart2.size());
		b = cart2.replace(0, null);
		assertFalse(b);
		assertEquals(4,cart2.size());
		b = cart2.replace(4, new CartItem(new StockItem("dummy", 1), 1));
		assertFalse(b);
		assertEquals(4,cart2.size());
		b = cart2.replace(4, null);
		assertFalse(b);
		assertEquals(4,cart2.size());
		b = cart2.replace(-1, null);
		assertFalse(b);
		assertEquals(4,cart2.size());

		CartItem d1 = new CartItem(new StockItem("dummy1", 1), 1);
		b = cart2.replace(0, d1);
		assertTrue(b);
		assertEquals(d1, cart2.items[0]);

		d1 = new CartItem(new StockItem("dummy2", 2), 1);
		b = cart2.replace(1, d1);
		assertTrue(b);
		assertEquals(d1, cart2.items[1]);

		d1 = new CartItem(new StockItem("dummy3", 3), 1);
		b = cart2.replace(2, d1);
		assertTrue(b);
		assertEquals(d1, cart2.items[2]);

		d1 = new CartItem(new StockItem("dummy4", 4), 1);
		b = cart2.replace(3, d1);
		assertTrue(b);
		assertEquals(d1, cart2.items[3]);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:removeItem(int)", marks=4)
	public void testRemoveItem() {
		boolean b = cart2.removeItem(-1);
		assertFalse(b);
		assertEquals(4,cart2.size());

		b = cart2.removeItem(4);
		assertFalse(b);
		assertEquals(4,cart2.size());

		b = cart2.removeItem(-1);
		assertFalse(b);
		assertEquals(4,cart2.size());

		b = cart2.removeItem(0);
		assertTrue(b);
		assertEquals(3,cart2.size());

		assertEquals("JERSEY", cart2.items[0].item.name);
		assertEquals(4, cart2.items[0].quantity);
		assertEquals(29, cart2.items[0].item.unitPrice, 0.01);
		assertEquals(0, cart2.items[0].discountPercentage);

		assertEquals("SHIN GUARD", cart2.items[1].item.name);
		assertEquals(5, cart2.items[1].quantity);
		assertEquals(9.72, cart2.items[1].item.unitPrice, 0.01);
		assertEquals(20, cart2.items[1].discountPercentage);

		assertEquals("SOCCER BALL (SIZE 5)", cart2.items[2].item.name);
		assertEquals(1, cart2.items[2].quantity);
		assertEquals(39.9, cart2.items[2].item.unitPrice, 0.01);
		assertEquals(10, cart2.items[2].discountPercentage);

		assertNull(cart2.items[3]);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:reduceItemBy(String,int)", marks=4)
	public void testReduceItemBy() {
		/*
		item2_1 = new StockItem("sports bibs", 5.9);
		item2_2 = new StockItem("jersey", 29);
		item2_3 = new StockItem("shin guard", 9.72);
		item2_4 = new StockItem("soccer ball (size 5)", 39.9);

		cartItem2_1 = new CartItem(item2_1, 5);
		cartItem2_2 = new CartItem(item2_2, 4);
		cartItem2_3 = new CartItem(item2_3, 5, 20);
		cartItem2_4 = new CartItem(item2_4, 1, 10);
		 */
		assertEquals(-1, cart2.reduceItemBy("JERSEYS", 1)); //not found (JERSEY, not JERSEYS)
		assertEquals(4, cart2.size());

		assertEquals(1, cart2.reduceItemBy("JERSEY", 1)); //1 out of 4 jerseys removed, 3 left
		assertEquals(4, cart2.size());
		assertEquals(3, cart2.items[1].quantity);

		assertEquals(1, cart2.reduceItemBy("jerSEY", 2)); //2 out of 3 jerseys removed, 1 left
		assertEquals(4, cart2.size());
		assertEquals(1, cart2.items[1].quantity);		

		assertEquals(0, cart2.reduceItemBy("JERSEY", 1)); //last one removed, item completely removed
		assertEquals(3, cart2.size());
		assertEquals(cartItem2_3, cart2.items[1]);
		assertEquals(cartItem2_4, cart2.items[2]);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="ShoppingCart:getGroupedByDiscount()", marks=5)
	public void testGetGroupedByDiscount() {
		ShoppingCart cart = new ShoppingCart();
		for(int i=1; i <= 100; i++) {
			if(i <= 21 || i >= 30) {
				if(i <= 82 || i >= 90) {
					//this test depends on successfully completing addItemBasic
					cart.addItemBasic(new CartItem(new StockItem("item"+i, i), i, i));
					System.out.println(cart.items[cart.itemCount-1].discountPercentage);
				}
			}
		}
		ShoppingCart[] buckets = cart.getGroupedByDiscount();
		assertEquals(11,buckets.length);
		for(int i=0; i < 11; i++) {
			assertNotNull(buckets[i]);
			if(i==0)
				assertEquals(9, buckets[i].size());
			else if(i==10)
				assertEquals(1, buckets[i].size());
			else if(i==2)
				assertEquals(2, buckets[i].size());
			else if(i==8)
				assertEquals(3, buckets[i].size());
			for(int k=0; k < buckets[i].size(); k++) {
				assertTrue(buckets[i].getItem(k).discountPercentage >= i*10);
				assertTrue(buckets[i].getItem(k).discountPercentage < (i+1)*10);
			}
		}
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

	//students should write their own tests for 
	//addItemAdvanced (10 marks), getGroupedByDiscount (5 marks), merge (10 marks)

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
