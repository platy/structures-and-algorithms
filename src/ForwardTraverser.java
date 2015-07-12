public interface ForwardTraverser<T> {
    T next();

    boolean hasNext();

    /** Removes the last element returned by next() */
    void remove();
}
