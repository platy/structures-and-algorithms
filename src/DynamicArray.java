import java.util.NoSuchElementException;

public class DynamicArray<T> implements IndexedMutableStructure<T>, EndResizable<T> {
    private final GrowAlgorithm growAlgorithm;
    private Array<T> underlying;
    private int length = 0;

    public static <T> DynamicArray<T> newDefault(int initialSize) {
        return new DynamicArray<>(initialSize, initialSize * 2, GrowAlgorithm.Default);
    }

    DynamicArray(int initialSize, int capacity, GrowAlgorithm grow) {
        underlying = new Array<>(capacity);
        length = initialSize;
        this.growAlgorithm = grow;
    }

    /** Indexed access O(1) */
    public T get(int index) {
        checkIndex(index);
        return underlying.get(index);
    }

    /** Indexed mutation O(1) */
    public void set(int index, T item) {
        checkIndex(index);
        underlying.set(index, item);
    }

    @Override
    public int length() {
        return length;
    }

    /** addLast amortised to O(1), worst case O(n) (when array must be grown) */
    @Override
    public void addLast(T item) {
        if (length == underlying.length()) {
            final int newUnderlyingLength = growAlgorithm.grow(1, length);
            growUnderlyingTo(newUnderlyingLength);
        }
        underlying.set(length, item);
        length++;
    }

    /** removeLast O(1), this DynamicArray doesn't shrink */
    @Override
    public void removeLast() {
        if(length == 0)
            throw new NoSuchElementException("Array is empty");
        underlying.set(--length, null);
    }

    /** Retrieves the last element of the list O(1) */
    @Override
    public T getLast() throws NoSuchElementException {
        if(length == 0)
            throw new NoSuchElementException("Array is empty");
        return underlying.get(length - 1);
    }

    private void growUnderlyingTo(int newUnderlyingLength) {
        Array<T> newUnderlying = new Array<T>(newUnderlyingLength);
        for (int i = 0; i < underlying.length(); i++) {
            newUnderlying.set(i, underlying.get(i));
        }
        underlying = newUnderlying;
    }

    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= length)
            throw new ArrayIndexOutOfBoundsException("Index " + index + " greater than length " + length);
    }

    interface GrowAlgorithm {
        int grow(int minimum, int currentLength);

        GrowAlgorithm Default = (minimum, currentLength) -> {
            if (currentLength > 0) {
                return currentLength * 2;
            } else {
                return 16;
            }
        };
    }
}
