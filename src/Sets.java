
public class Sets {

    public static <T> Set<T> newSetFromLinkedList(EqualityFunction<T> equalityFunction) {
        return new Set<T>(){
            LinkedList<T> list = new LinkedList<>();
            @Override
            public T lookup(T elem) {
                ForwardTraverser<T> traverser = list.traverser();
                T current;
                while (traverser.hasNext()) {
                    current = traverser.next();
                    if(equalityFunction.equal(current, elem)) {
                        return current;
                    }
                }
                return null;
            }

            @Override
            public T remove(T elem) {
                ForwardTraverser<T> traverser = list.traverser();
                T current;
                while (traverser.hasNext()) {
                    current = traverser.next();
                    if(equalityFunction.equal(current, elem)) {
                        traverser.remove();
                        return current;
                    }
                }
                return null;
            }

            @Override
            public T insert(T elem) {
                T previous = remove(elem);
                list.addFirst(elem);
                return previous;
            }
        };
    }

    private Sets() {
    }
}
