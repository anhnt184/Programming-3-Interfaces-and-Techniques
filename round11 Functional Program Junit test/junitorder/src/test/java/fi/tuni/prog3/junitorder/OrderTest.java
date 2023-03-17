package fi.tuni.prog3.junitorder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;

//    @BeforeEach
//    public void setUp() {
//        order = new Order();
//    }

    @Test
    public void testOrderConstructor() {
        Order order = new Order();
        assertNotNull(order);
        assertEquals(0, order.getEntryCount());
        assertTrue(order.getTotalPrice() == 0.0);
    }

    @Test
    public void testItemConstructor() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertNotNull(item);
        assertEquals("Test Item", item.getName());
        assertTrue(item.getPrice() == 10.0);
    }

    @Test
    public void testEntryConstructor() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertNotNull(entry);
        assertEquals("Test Item", entry.getItemName());
        assertTrue(entry.getUnitPrice() == 10.0);
        assertEquals(item, entry.getItem());
        assertEquals(5, entry.getCount());
    }

    @Test
    public void testAddItems() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        
        assertTrue(order.addItems(item, 3));
        assertEquals(1, order.getEntryCount());
        Order.Item item2 = new Order.Item("Test Item 2", 3.0);
        assertTrue(order.addItems(item2, 1));
        assertEquals(2, order.getEntryCount());
        
    }

    @Test
    public void testAddItemsNegativeCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertThrows(IllegalArgumentException.class, () -> order.addItems(item, -3));
    }

    @Test
    public void testAddItemsByName() {
        Order order2 = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order2.addItems(item, 3);
        assertTrue(order2.addItems("Test Item", 2));
        assertEquals(1, order2.getEntryCount());
    }

    @Test
    public void testAddItemsByNameNegativeCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertThrows(IllegalArgumentException.class, () -> order.addItems("Test Item", -2));
    }

    @Test
    public void testAddItemsByNameNonexistent() {
        Order order = new Order();
        assertThrows(NoSuchElementException.class, () -> order.addItems("Test Item", 2));
    }
//    Test adding the same item multiple times with different prices

    @Test
    public void testAddItemsSameNameDifferentPrice() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Test Item", 10.0);
        Order.Item item2 = new Order.Item("Test Item", 15.0);

        order.addItems(item1, 3);
        assertThrows(IllegalStateException.class, () -> order.addItems(item2, 2));
    }
//    Test adding a large number of item units

    @Test
    public void testAddItemsLargeCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);

        assertTrue(order.addItems(item, 1000));
        assertEquals(1, order.getEntries().size());
        assertEquals(1000, order.getEntries().get(0).getCount());
    }

//Test adding multiple distinct items
    @Test
    public void testAddItemsMultipleDistinctItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);

        assertTrue(order.addItems(item1, 3));
        assertTrue(order.addItems(item2, 2));

        assertEquals(2, order.getEntries().size());
    }
//Test adding item units to an existing entry

    @Test
    public void testAddItemsToExistingEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);

        assertTrue(order.addItems(item, 3));
        assertTrue(order.addItems(item, 2));

        assertEquals(1, order.getEntries().size());
        assertEquals(5, order.getEntries().get(0).getCount());
    }
//Test adding item units to an existing entry by name

    @Test
    public void testAddItemsByNameToExistingEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);

        assertTrue(order.addItems("Test Item", 2));
        assertEquals(1, order.getEntries().size());
        assertEquals(5, order.getEntries().get(0).getCount());
    }

//Test adding item units to a non-existent item by name
    @Test
    public void testAddItemsByNameNonExistentItem() {
        Order order = new Order();

        assertThrows(NoSuchElementException.class, () -> order.addItems("Non-existent Item", 2));
    }
//Test adding zero item units

    @Test
    public void testAddItemsByNameZeroCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);

        assertThrows(IllegalArgumentException.class, () -> order.addItems("Test Item", 0));
    }

//Test adding item units to multiple distinct items by name

    @Test
    public void testAddItemsByNameMultipleDistinctItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);
        order.addItems(item1, 3);
        order.addItems(item2, 2);

        assertTrue(order.addItems("Item 1", 2));
        assertTrue(order.addItems("Item 2", 3));

        assertEquals(2, order.getEntries().size());
        assertEquals(5, order.getEntries().get(0).getCount());
        assertEquals(5, order.getEntries().get(1).getCount());
    }


    @Test
    public void testGetEntries() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 5.0);
        Order.Item item2 = new Order.Item("Item 2", 10.0);
        order.addItems(item1, 2);
        order.addItems(item2, 3);

        List<Order.Entry> entries = order.getEntries();
        assertEquals(2, entries.size());
        assertEquals(item1, entries.get(0).getItem());
        assertEquals(item2, entries.get(1).getItem());
    }
//    Test getting entries from an empty order

    @Test
    public void testGetEntriesEmptyOrder() {
        Order order = new Order();

        List<Order.Entry> entries = order.getEntries();
        assertNotNull(entries);
        assertEquals(0, entries.size());
    }
//Test getting entries from an order with a single entry

    @Test
    public void testGetEntriesSingleEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);

        List<Order.Entry> entries = order.getEntries();
        assertEquals(1, entries.size());
        assertEquals(item, entries.get(0).getItem());
    }
//Test getting entries from an order with multiple entries

    @Test
    public void testGetEntriesMultipleEntries() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);
        order.addItems(item1, 3);
        order.addItems(item2, 2);

        List<Order.Entry> entries = order.getEntries();
        assertEquals(2, entries.size());
        assertEquals(item1, entries.get(0).getItem());
        assertEquals(item2, entries.get(1).getItem());
    }
//Test getting entries from an order with added and removed item units

    @Test
    public void testGetEntriesAfterAddingAndRemovingItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.removeItems("Item 1", 1);

        List<Order.Entry> entries = order.getEntries();
        assertEquals(2, entries.size());
        assertEquals(item1, entries.get(0).getItem());
        assertEquals(2, entries.get(0).getCount());
        assertEquals(item2, entries.get(1).getItem());
        assertEquals(2, entries.get(1).getCount());
    }


    @Test
    public void testGetEntryCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertEquals(1, order.getEntryCount());
    }

//    Test getting entry count from an empty order
    @Test
    public void testGetEntryCountEmptyOrder() {
        Order order = new Order();
        assertEquals(0, order.getEntryCount());
    }

    @Test
    public void testGetTotalPrice() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 5.0);
        Order.Item item2 = new Order.Item("Item 2", 10.0);
        order.addItems(item1, 2);
        order.addItems(item2, 3);
        assertTrue(order.getTotalPrice() == 40.0);
    }
//Test getting total price from an empty order

    @Test
    public void testGetTotalPriceEmptyOrder() {
        Order order = new Order();
        assertEquals(0.0, order.getTotalPrice(), 0.001);
    }
//Test getting total price after adding a single entry

    @Test
    public void testGetTotalPriceSingleEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertEquals(30.0, order.getTotalPrice(), 0.001);
    }
//Test getting total price after adding multiple entries

    @Test
    public void testGetTotalPriceMultipleEntries() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        assertEquals(60.0, order.getTotalPrice(), 0.001);
    }
    
//    ITEM
    
//    Test the creation of an item with valid name and price
    @Test
    public void testValidItemCreation() {
        Order.Item item = new Order.Item("Valid Item", 10.0);
        assertNotNull(item);
        assertEquals("Valid Item", item.getName());
        assertEquals(10.0, item.getPrice());
    }
//Test the creation of an item with a negative price
    @Test
    public void testNegativePriceItemCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Order.Item("Test Item", -10.0));
    }

//Test getting total price after adding and removing item units

    @Test
    public void testGetTotalPriceAfterAddingAndRemovingItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 10.0);
        Order.Item item2 = new Order.Item("Item 2", 15.0);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.removeItems("Item 1", 1);
        assertEquals(50.0, order.getTotalPrice(), 0.001);
    }

    @Test
    public void testRemoveItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 5.0);
        Order.Item item2 = new Order.Item("Item 2", 10.0);
        order.addItems(item1, 2);
        order.addItems(item2, 3);

        assertTrue(order.removeItems("Item 1", 1));
        assertEquals(2, order.getEntryCount());
        assertTrue(order.removeItems("Item 1", 1));
        assertEquals(1, order.getEntryCount());
    }

    @Test
    public void testRemoveItemsNegativeCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertThrows(IllegalArgumentException.class, () -> order.removeItems("Test Item", -2));
    }

    @Test
    public void testRemoveItemsNonexistent() {
        Order order = new Order();
        assertThrows(NoSuchElementException.class, () -> order.removeItems("Test Item", 1));
    }

    @Test
    public void testRemoveItemsLargerCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertThrows(IllegalArgumentException.class, () -> order.removeItems("Test Item", 5));
    }
//    Test removing all items of an entry
    @Test
    public void testRemoveAllItemsOfAnEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        order.addItems(item, 3);
        assertTrue(order.removeItems("Test Item", 3));
        assertEquals(0, order.getEntryCount());
    }


    @Test
    public void testItemGetName() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertEquals("Test Item", item.getName());
    }
//    Test getting the item name from an entry with a different item

    @Test
    public void testGetItemNameFromDifferentEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Another Item", 15.0);
        Order.Entry entry = new Order.Entry(item, 2);
        assertEquals("Another Item", entry.getItemName());
    }


    @Test
    public void testItemGetPrice() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertTrue(item.getPrice() == 10.0);
    }

    @Test
    public void testItemToString() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertEquals("Item(Test Item, 10.00)", item.toString());
    }

    @Test
    public void testItemEquals() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Test Item", 10.0);
        Order.Item item2 = new Order.Item("Test Item", 15.0);
        Order.Item item3 = new Order.Item("Other Item", 10.0);

        assertTrue(item1.equals(item2));
        assertFalse(item1.equals(item3));
    }

    @Test
    public void testEntryGetItemName() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals("Test Item", entry.getItemName());
    }

    @Test
    public void testEntryGetUnitPrice() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertTrue(entry.getUnitPrice() == 10.0);
    }
//    Test getting the unit price from an entry with a different item
    @Test
    public void testGetUnitPriceFromDifferentEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Another Item", 15.0);
        Order.Entry entry = new Order.Entry(item, 2);
        assertEquals(15.0, entry.getUnitPrice(), 0.001);
    }


    @Test
    public void testEntryGetItem() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals(item, entry.getItem());
    }
//    Test getting the item from an entry with a different item
    @Test
    public void testGetItemFromDifferentEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Another Item", 15.0);
        Order.Entry entry = new Order.Entry(item, 2);
        assertEquals(item, entry.getItem());
    }


    @Test
    public void testEntryGetCount() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals(5, entry.getCount());
    }
//    Test getting the count from an entry with a different count
    @Test
    public void testGetCountFromDifferentEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Another Item", 15.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals(5, entry.getCount());
    }
//    Test the string representation of an entry with a different item and count
    @Test
    public void testToStringOfDifferentEntry() {
        Order order = new Order();
        Order.Item item = new Order.Item("Another Item", 15.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals("5 units of Item(Another Item, 15.00)", entry.toString());
    }

    @Test
    public void testEntryToString() {
        Order order = new Order();
        Order.Item item = new Order.Item("Test Item", 10.0);
        Order.Entry entry = new Order.Entry(item, 5);
        assertEquals("5 units of Item(Test Item, 10.00)", entry.toString());
    }

    @Test
    public void testItemGetPricePositive() {
        Order.Item item = new Order.Item("Test Item", 20.0);
        assertEquals(20.0, item.getPrice(), 0.001);
    }

    @Test
    public void testItemGetPriceZero() {
        Order.Item item = new Order.Item("Test Item", 0.0);
        assertEquals(0.0, item.getPrice(), 0.001);
    }
//Test the equality of two items with the same name but different prices

    @Test
    public void testItemEqualityDifferentPrice() {
        Order.Item item1 = new Order.Item("Test Item", 10.0);
        Order.Item item2 = new Order.Item("Test Item", 15.0);
        assertTrue(item1.equals(item2));
    }

//Test the equality of two items with different names and prices
    @Test
    public void testItemEqualityDifferentNameAndPrice() {
        Order.Item item1 = new Order.Item("Test Item", 10.0);
        Order.Item item2 = new Order.Item("Another Item", 20.0);
        assertFalse(item1.equals(item2));
    }
//Test toString() with a price that has more than two decimal places

    @Test
    public void testItemToStringRoundedPrice() {
        Order.Item item = new Order.Item("Test Item", 10.123);
        String expectedStringRepresentation = "Item(Test Item, 10.12)";
        assertEquals(expectedStringRepresentation, item.toString());
    }
//Test the equality of an item with itself

    @Test
    public void testItemEqualityWithItself() {
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertTrue(item.equals(item));
    }
//Test the equality of an item with a null reference

    @Test
    public void testItemEqualityWithNull() {
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertFalse(item.equals(null));
    }
//Entry constructor

    @Test
    public void testEntryConstructorWithZeroCount() {
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item, 0));
    }

    @Test
    public void testEntryConstructorWithNegativeCount() {
        Order.Item item = new Order.Item("Test Item", 10.0);
        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item, -1));
    }

    @Test
    public void testEntryConstructorWithDifferentItems() {
        Order.Item item1 = new Order.Item("Test Item 1", 10.0);
        Order.Item item2 = new Order.Item("Test Item 2", 15.0);
        Order.Entry entry1 = new Order.Entry(item1, 3);
        Order.Entry entry2 = new Order.Entry(item2, 4);

        assertEquals("Test Item 1", entry1.getItemName());
        assertEquals("Test Item 2", entry2.getItemName());
        assertTrue(entry1.getUnitPrice() == 10.0);
        assertTrue(entry2.getUnitPrice() == 15.0);
        assertEquals(item1, entry1.getItem());
        assertEquals(item2, entry2.getItem());
        assertEquals(3, entry1.getCount());
        assertEquals(4, entry2.getCount());
    }
//Order constructor

    @Test
    public void testOrderConstructorWithMultipleEntries() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Item 1", 5.0);
        Order.Item item2 = new Order.Item("Item 2", 10.0);

        order.addItems(item1, 3);
        order.addItems(item2, 2);

        assertEquals(2, order.getEntries().size());
        assertEquals(2, order.getEntryCount());
        assertTrue(order.getTotalPrice() == 35.0);
    }

    @Test
    public void testOrderConstructorWithNoEntries() {
        Order order = new Order();

        assertEquals(0, order.getEntries().size());
        assertEquals(0, order.getEntryCount());
        assertTrue(order.getTotalPrice() == 0.0);
    }

    @Test
    public void testOrderConstructorWithNewItem() {
        Order order = new Order();
        Order.Item item = new Order.Item("New Item", 7.5);

        order.addItems(item, 4);

        assertEquals(1, order.getEntries().size());
        assertEquals(1, order.getEntryCount());
        assertTrue(order.getTotalPrice() == 30.0);
    }

}
