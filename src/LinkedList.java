import java.util.NoSuchElementException;

public class LinkedList<T> implements StartResizable<T> {
    private Node list = null;

    /** Gets the first element in the list O(1) */
    @Override
    public T getFirst() throws NoSuchElementException{
        checkNotEmpty();
        return list.head;
    }

    @Override
    public void removeFirst() throws NoSuchElementException {
        checkNotEmpty();
        list = list.tail;
    }

    @Override
    public void addFirst(T element) {
        list = new Node(element, list);
    }

    private void checkNotEmpty() {
        if(list == null) throw new NoSuchElementException("List is empty");
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
