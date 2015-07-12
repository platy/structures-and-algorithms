/**
 * @param <T>
 */
public interface PriorityQueue<T> {
    /** Adds the new key to the queue
     *
     * @param s new key, must not be null
     */
    void enqueue(T s);

    /** Remove and return the lowest key, returns null if the queue is empty */
    T takeMax();

    /** Remove and return the greatest key, returns null if the queue is empty */
    T takeMin();
}
