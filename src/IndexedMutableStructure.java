public interface IndexedMutableStructure<T> {
    T get(int index);

    void set(int index, T item);

    int length();
}
