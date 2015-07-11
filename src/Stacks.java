public class Stacks {

    static <T> Stack<T> fromEndResizable(EndResizable<T> endResizable) {
        return new StackFromEndResizable<>(endResizable);
    }

    public static <T> Stack<T> fromStartResizable(StartResizable<T> startResizable) {
        return new StackFromStartResizable<>(startResizable);
    }

    private static class StackFromEndResizable<T> implements Stack<T> {
        private final EndResizable<T> endResizable;

        private StackFromEndResizable(EndResizable<T> endResizable) {
            this.endResizable = endResizable;
        }

        @Override
        public void push(T item) {
            endResizable.addLast(item);
        }

        @Override
        public T pop() {
            final T last = endResizable.getLast();
            endResizable.removeLast();
            return last;
        }

        @Override
        public T peek() {
            return endResizable.getLast();
        }
    }

    private Stacks() {
    }

    private static class StackFromStartResizable<T> implements Stack<T> {
        private final StartResizable<T> startResizable;

        public StackFromStartResizable(StartResizable<T> startResizable) {
            this.startResizable = startResizable;
        }

        @Override
        public void push(T item) {
            startResizable.addFirst(item);
        }

        @Override
        public T pop() {
            final T first = peek();
            startResizable.removeFirst();
            return first;
        }

        @Override
        public T peek() {
            return startResizable.getFirst();
        }
    }
}
