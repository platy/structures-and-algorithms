public interface Set<T> extends ForwardTraversable<T> {
    T lookup(T elem);
    T remove(T elem);
    T insert(T elem);
}
