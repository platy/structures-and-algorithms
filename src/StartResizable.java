import java.util.NoSuchElementException;

public interface StartResizable<T> {
    T getFirst() throws NoSuchElementException;

    void removeFirst() throws NoSuchElementException;

    void addFirst(T element);
}
