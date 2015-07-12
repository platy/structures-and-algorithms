public class Queues {

    public static <T> Queue<T> queueFromDoublyLinkedListForward() {
        return new Queue<T>() {
            private DoublyLinkedList<T> list = new DoublyLinkedList<>();
            @Override
            public void enqueue(T elem) {
                list.addFirst(elem);
            }

            @Override
            public T dequeue() {
                T last = list.getLast();
                if (last != null) {
                    list.removeLast();
                }
                return last;
            }
        };
    }

    public static <T> Queue<T> queueFromDoublyLinkedListBackward() {
        return new Queue<T>() {
            private DoublyLinkedList<T> list = new DoublyLinkedList<>();
            @Override
            public void enqueue(T elem) {
                list.addLast(elem);
            }

            @Override
            public T dequeue() {
                T first = list.getFirst();
                if (first != null) {
                    list.removeFirst();
                }
                return first;
            }
        };
    }

    private Queues() {}
}
