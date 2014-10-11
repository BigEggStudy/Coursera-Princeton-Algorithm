package Collections;

/**
 * Created by jianming.xiao on 10/11/14.
 */
public interface IBag<Item> extends Iterable<Item> {
    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    int size();

    /**
     * Adds the item to this bag.
     *
     * @param item the item to add to this bag
     * @throws NullPointerException if the <tt>item</tt> is null;
     */
    void add(Item item);
}
