package shopping;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class StockItemTest {
	public static int score = 0;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();
	
	@Test @Graded(description="StockItem(String, double)", marks=5)
	public void testStockItem() {
		StockItem item = new StockItem("SamBa", 129.9);
		assertEquals("SAMBA", item.name);
		assertEquals(129.9, item.unitPrice, 0.001);

		item = new StockItem("Copa", -1.9);
		assertEquals("COPA", item.name);
		assertEquals(0, item.unitPrice, 0.001);

		item = new StockItem("SamBa", 79.1);
		assertEquals(79.1, item.unitPrice, 0.001);

		item = new StockItem("SamBa", -2313129.9);
		assertEquals(0, item.unitPrice, 0.001);
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="StockItem:updateRegularPrice(int)", marks=5)
	public void testUpdateRegularPrice() {
		
		StockItem item = new StockItem("SamBa", 129.9);
		item.updateRegularPrice(10); //10% increase
		assertEquals(142.89, item.unitPrice, 0.001);
		
		item.updateRegularPrice(-20); //20% decrease from the new price 142.89
		assertEquals(114.312, item.unitPrice, 0.001);
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test @Graded(description="StockItem:compareTo(StockItem)", marks=5)
	public void testCompareTo() {
		StockItem item1 = new StockItem("SamBa", 129.9);
		StockItem item2 = new StockItem("SamBa", 129.9);
		assertEquals(0, item1.compareTo(item2));

		item1 = new StockItem("SamBa", 9.9);
		item2 = new StockItem("SamBa", 12.9);

		assertEquals(-1, item1.compareTo(item2));
		assertEquals(1, item2.compareTo(item1));

		item1 = new StockItem("Copa", 9.9);
		item2 = new StockItem("Coda", 9.9);

		assertEquals(1, item1.compareTo(item2));
		assertEquals(-1, item2.compareTo(item1));
		
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
		System.out.println("\nStockItem\n");
		if(result.length() != 0) {
			result = result.substring(0, result.length()-1); //remove the last "\n"
		}
		System.out.println(result);
		System.out.println("Indicative mark: "+score);
	}
}
