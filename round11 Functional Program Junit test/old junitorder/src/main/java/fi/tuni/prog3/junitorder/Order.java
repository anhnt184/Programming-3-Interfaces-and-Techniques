
package fi.tuni.prog3.junitorder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * @author Tuan Anh Nguyen anh.5.nguyen@tuni.fi
 * Represents an order that contains zero or more entries.
 */
public class Order {
    private List<Entry> entries;

    /**
     * Constructs an initially empty order.
     */
    public Order() {
        entries = new ArrayList<>();
    }

    /**
     * Adds count units of an item to the order.
     *
     * @param item  The item to be added to the order.
     * @param count The number of units of the item to be added.
     * @return true if the items were added without errors.
     * @throws IllegalArgumentException if the item unit count to add is not positive.
     * @throws IllegalStateException    if an existing entry has the same item name but a different price than the added item.
     */
    public boolean addItems(Item item, int count) throws IllegalArgumentException, IllegalStateException {
        if (count <= 0) {
            throw new IllegalArgumentException("Item count must be positive.");
        }

        for (Entry entry : entries) {
            if (entry.getItem().getName().equals(item.getName())) {
                if (entry.getUnitPrice() != item.getPrice()) {
                    throw new IllegalStateException("Item with the same name but a different price already exists.");
                }
                entry.setCount(entry.getCount() + count);
                return true;
            }
        }

        entries.add(new Entry(item, count));
        return true;
    }

    /**
     * Adds count units of an item to the order.
     *
     * @param name  The name of the item to be added to the order.
     * @param count The number of units of the item to be added.
     * @return true if the items were added without errors.
     * @throws IllegalArgumentException if the item unit count to add is not positive.
     * @throws NoSuchElementException   if the order does not contain an entry with the specified item name.
     */
    public boolean addItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        for (Entry entry : entries) {
            if (entry.getItemName().equals(name)) {
                entry.setCount(entry.getCount() + count);
                return true;
            }
        }
        throw new NoSuchElementException("No entry with the specified item name found.");
    }

    /**
     * Returns the order entries in their original adding order.
     *
     * @return A list of order entries.
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Returns the total number of entries in this order (sum of all entries' counts).
     *
     * @return The total number of entries in this order.
     */
    public int getEntryCount() {
        int count = 0;
        for (Entry entry : entries) {
            count += entry.getCount();
        }
        return count;
    }

    /**
     * Returns the total price of all items in the order.
     *
     * @return The total price of all items in the order.
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Entry entry : entries) {
            totalPrice += entry.getUnitPrice() * entry.getCount();
        }
        return totalPrice;
    }

    /**
     * Removes count units of an item from the order.
     *
     * @param name  The name of the item to be removed from the order.
     * @param count The number of units of the item to be removed.
     * @return true if the items were removed without errors.
     * @throws IllegalArgumentException if the item unit count to remove is not positive or is larger than the corresponding
 * existing entry's item unit count.
 * @throws NoSuchElementException   if the order does not contain an entry with the specified item name.
 */
public boolean removeItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
    for (Entry entry : entries) {
        if (entry.getItemName().equals(name)) {
            if (count <= 0 || count > entry.getCount()) {
                throw new IllegalArgumentException("Invalid item count to remove.");
            }
            entry.setCount(entry.getCount() - count);
            return true;
        }
    }
    throw new NoSuchElementException("No entry with the specified item name found.");
}

/**
 * Represents an order entry that consists of an item and an item unit count.
 */
public static class Entry {
    private Item item;
    private int count;

    /**
     * Constructs an entry with the given item and item unit count.
     *
     * @param item  The item for this entry.
     * @param count The item unit count for this entry.
     * @throws IllegalArgumentException if the item count is not positive.
     */
    public Entry(Item item, int count) throws IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("Item count must be positive.");
        }
        this.item = item;
        this.count = count;
    }

    /**
     * Returns the name of this entry's item.
     *
     * @return The name of the item.
     */
    public String getItemName() {
        return item.getName();
    }

    /**
     * Returns the unit price of this entry's item.
     *
     * @return The unit price of the item.
     */
    public double getUnitPrice() {
        return item.getPrice();
    }

    /**
     * Returns the item of this entry.
     *
     * @return The item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the item unit count of this entry.
     *
     * @return The entry's item unit count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the item unit count of this entry.
     *
     * @param count The new item unit count.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns a string representation of this entry. The presentation format is "x units of item", where x is the item unit count and item is the string representation of this entry's item.
     *
     * @return A string representation of this entry.
     */
    public String toString() {
        return count + " units of " + item;
    }
}

/**
 * Represents an item that has a name and a unit price.
 */
public static class Item {
    private String name;
    private double price;

    /**
     * Constructs an item with the given name and unit price.
     *
     * @param name  The name of the item.
     * @param price The unit price of the item.
     * @throws IllegalArgumentException if the price is negative.
     */
    public Item(String name, double price) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unit price of the item.
     *
     * @return The unit price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
    
     * Returns a string representation of this item. The presentation format is "Item(name, price)", where the price is expressed with two decimals of precision.
     *
     * @return A string representation of this item.
     */
    public String toString() {
        return "Item(" + name + ", " + String.format("%.2f", price) + ")";
    }

    /**
     * Compares this item with another item based on item name.
     *
     * @param other The other item to compare.
     * @return true if the name of this item equals the name of the other item, otherwise false.
     */
    public boolean equals(Item other) {
        return this.name.equals(other.getName());
    }
}
}
