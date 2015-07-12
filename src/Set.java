public interface Set<T> {
    T lookup(T elem);
    T remove(T elem);
    void insert(T elem);
}
