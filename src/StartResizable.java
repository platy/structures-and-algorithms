import java.util.NoSuchElementException;

public interface StartResizable<T> {
    /** Adds a new element to the start of the structure
     *
     * @param element new element must not be null
     */
    void addFirst(T element);

    /** Removes the first element from the structure or throws NoSuchElementException */
    void removeFirst() throws NoSuchElementException;

    /** Gets the element from the start of the structure, or null if empty */
    T getFirst() throws NoSuchElementException;
}
