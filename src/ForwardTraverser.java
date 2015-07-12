public interface ForwardTraverser<T> {
    T next();

    boolean hasNext();
}
