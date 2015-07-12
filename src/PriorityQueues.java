public class PriorityQueues {

    public static <T> PriorityQueue<T> priorityQueueFromBinaryTree(Comparator<T> comparator) {
        return new PriorityQueue<T>() {
            BinaryTree<T> tree = new BinaryTree<>(comparator);
            @Override
            public void enqueue(T s) {
                tree.insert(s);
            }

            @Override
            public T takeMin() {
                T min = tree.findMin();
                tree.remove(min);
                return min;
            }

            @Override
            public T takeMax() {
                T max = tree.findMax();
                tree.remove(max);
                return max;
            }
        };
    }

    private PriorityQueues() {}
}
