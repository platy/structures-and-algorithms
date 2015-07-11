public interface Queue<T> {
    void enqueue(T elem);
    T dequeue();
}
