/**
 * This wrapper around java arrays serves only to provided type checking here so that I don't have to do it in types
 * which use arrays, also so that consistent interfaces can be added.
 * @param <T>
 */
public class Array<T> implements IndexedMutableStructure<T> {
    private Object[] underlying;

    public Array(int length) {
        underlying = new Object[length];
    }

    public T get(int index) {
        //noinspection unchecked
        return (T)underlying[index];
    }
    @Override
    public void set(int index, T item) {
        underlying[index] = item;
    }

    @Override
    public int length() {
        return underlying.length;
    }
}
