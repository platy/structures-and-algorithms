import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements StartResizable<T>, EndResizable<T> {
    // list is linked circularly with a single sentinel node, this simplifies the operations as they don't need so many
    // special cases at the ends
    private final Node terminator;

    public DoublyLinkedList() {
        terminator = new Node();
        terminator.next = terminator;
        terminator.prev = terminator;
    }

    @Override
    public void addLast(T element) {
        Node oldLast = terminator.prev;
        Node newLast = new Node(oldLast, element, terminator);
        terminator.prev = newLast;
        oldLast.next = newLast;
    }

    @Override
    public void removeLast() {
        checkNotEmpty();
        Node secondNode = terminator.prev.prev;
        terminator.prev = secondNode;
        secondNode.next = terminator;
    }

    @Override
    public T getLast() {
        checkNotEmpty();
        return terminator.prev.elem;
    }

    /** Gets the first element in the list O(1) */
    @Override
    public T getFirst() throws NoSuchElementException{
        checkNotEmpty();
        return terminator.next.elem;
    }

    @Override
    public void removeFirst() throws NoSuchElementException {
        checkNotEmpty();
        Node secondNode = terminator.next.next;
        terminator.next = secondNode;
        secondNode.prev = terminator;
    }

    @Override
    public void addFirst(T element) {
        Node oldFirst = terminator.next;
        Node newFirst = new Node(terminator, element, oldFirst);
        terminator.next = newFirst;
        oldFirst.prev = newFirst;
    }

    private void checkNotEmpty() {
        if(terminator.next == terminator || terminator.prev == terminator) throw new NoSuchElementException("List is empty");
    }

    private class Node {
        Node prev;
        T elem;
        Node next;

        public Node() {}
        public Node(Node prev, T elem, Node next) {
            this.prev = prev;
            this.elem = elem;
            this.next = next;
        }
    }
}
