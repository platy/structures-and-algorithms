public interface Set<T> {
    T lookup(T elem);
    T remove(T elem);
    T insert(T elem);
}
