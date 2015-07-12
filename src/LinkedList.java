import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements StartResizable<T>, ForwardTraversable<T> {
    private final Node terminator = new Node(null, null);

    /** Gets the first element in the list O(1) */
    @Override
    public T getFirst() throws NoSuchElementException{
        checkNotEmpty();
        return terminator.tail.head;
    }

    @Override
    public void removeFirst() throws NoSuchElementException {
        checkNotEmpty();
        terminator.tail = terminator.tail.tail;
    }

    @Override
    public void addFirst(T element) {
        terminator.tail = new Node(element, terminator.tail);
    }

    private void checkNotEmpty() {
        if(terminator.tail == null) throw new NoSuchElementException("List is empty");
    }

    @Override
    public ForwardTraverser<T> traverser() {
        return new ForwardTraverser<T>() {
            Node previous;
            Node current = terminator;
            @Override
            public T next() {
                previous = current;
                current = current.tail;
                return current.head;
            }

            @Override
            public boolean hasNext() {
                return current.tail != null;
            }

            @Override
            public void remove() {
                previous.tail = current.tail;
                current = previous;
            }
        };
    }

    private class Node {
        T head;
        Node tail;

        public Node(T element, Node list) {
            head = element;
            tail = list;
        }
    }
}
