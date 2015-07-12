import java.util.NoSuchElementException;

public interface EndResizable<T> {
    /** Adds a new element to the end of the structure
     *
     * @param element new element must not be null
     */
    void addLast(T element);

    /** Removes the last element from the structure or throws NoSuchElementException */
    void removeLast() throws NoSuchElementException;

    /** Gets the element from the end of the structure, or null if empty */
    T getLast();
}
