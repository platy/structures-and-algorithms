public interface Queue<T> {
    /**
     * Add a new element to the queue
     * @param elem new element, must not be null
     */
    void enqueue(T elem);

    /** Removes and returns the next element from the queue, returns null if empty */
    T dequeue();
}
