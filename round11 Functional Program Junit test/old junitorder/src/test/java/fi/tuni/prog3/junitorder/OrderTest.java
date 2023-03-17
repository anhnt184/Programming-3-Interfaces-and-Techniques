package fi.tuni.prog3.junitorder;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderConstructor() {
        Order order = new Order();
        assertNotNull(order);
        assertEquals(0, order.getEntries().size());
    }

    @Test
    void testAddItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Apple", 1.5);
        Order.Item item2 = new Order.Item("Banana", 0.5);

        // ... (rest of the testAddItems method) ...
    }

    @Test
    void testRemoveItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Apple", 1.5);
        Order.Item item2 = new Order.Item("Banana", 0.5);

        // ... (rest of the testRemoveItems method) ...
    }

    @Nested
    class ItemTest {

        @Test
        void testItemConstructor() {
            Order.Item item = new Order.Item("Apple", 1.5);
            assertNotNull(item);
            assertEquals("Apple", item.getName());
            assertEquals(1.5, item.getPrice());
        }

        @Test
        void testItemToString() {
            Order.Item item = new Order.Item("Apple", 1.5);
            assertEquals("Item(Apple, 1.50)", item.toString());
        }

        @Test
        void testItemEquals() {
            Order.Item item1 = new Order.Item("Apple", 1.5);
            Order.Item item2 = new Order.Item("Apple", 1.5);
            Order.Item item3 = new Order.Item("Banana", 0.5);

            assertTrue(item1.equals(item2));
            assertFalse(item1.equals(item3));
        }
    }

    @Nested
    class EntryTest {

        @Test
        void testEntryConstructor() {
            Order.Item item = new Order.Item("Apple", 1.5);
            Order.Entry entry = new Order.Entry(item, 2);
            assertNotNull(entry);
            assertEquals(item, entry.getItem());
            assertEquals(2, entry.getCount());
        }

        @Test
        void testEntryToString() {
            Order.Item item = new Order.Item("Apple", 1.5);
            Order.Entry entry = new Order.Entry(item, 2);
            assertEquals("2 units of Item(Apple, 1.50)", entry.toString());
        }
    }
}

