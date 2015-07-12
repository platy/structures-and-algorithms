public class BinaryTree<T> implements Set<T> {
    private Node root = null;
    private final Comparator<T> comparator;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public T lookup(T elem) {
        if (root == null) {
            return null;
        } else {
            return root.lookup(elem);
        }
    }

    @Override
    public T remove(T elem) {
        if (root != null) {
            T existingElem = root.key;
            int compare = comparator.compare(elem, existingElem);
            if (compare == 0) {
                if (root.right != null && root.left != null) {
                    throw new RuntimeException("this is the difficult case");
                } else if (root.right != null) {
                    root = root.right;
                } else if (root.left != null) {
                    root = root.left;
                } else {
                    root = null;
                }
                return elem;
            } else if (compare > 0 && root.right != null) {
                return root.removeRight(elem);
            } else if (root.left != null) {
                return root.removeLeft(elem);
            }
        }
        return null;
    }

    @Override
    public void insert(T elem) {
        if (root == null) {
            root = new Node(elem);
        } else {
            root.insert(elem);
        }
    }

    public static <T extends Comparable<T>> Set<T> newWithJavaNaturalComparator() {
        return new BinaryTree<>(Comparable::compareTo);
    }

    private class Node {
        Node left;
        T key;
        Node right;

        public Node(T key) {
            this.key = key;
        }

        public void insert(T elem) {
            int compare = comparator.compare(elem, key);
            if (compare < 0) { // this is slightly right biased as new keys which are equal to one already there will go to the right
                if (left == null) {
                    left = new Node(elem);
                } else {
                    left.insert(elem);
                }
            } else {
                if (right == null) {
                    right = new Node(elem);
                } else {
                    right.insert(elem);
                }
            }
        }

        public T lookup(T elem) {
            int compare = comparator.compare(elem, key);
            if (compare == 0) {
                return key;
            } else if (compare > 0 && right != null) {
                return right.lookup(elem);
            } else if (left != null) {
                return left.lookup(elem);
            } else {
                return null;
            }
        }

        private Node onlyChild() {
            if (left != null && right != null) {
                throw new IllegalStateException("this node is not an only child");
            } else if (left == null) {
                return right;
            } else {
                return left;
            }
        }

        public T removeRight(T elem) {
            T existingElem = right.key;
            int compare = comparator.compare(elem, existingElem);
            if (compare == 0) {
                if (right.right != null && right.left != null) {
                    throw new RuntimeException("this is the difficult case");
                } else if (right.right != null) {
                    right = right.right;
                } else if (right.left != null) {
                    right = right.left;
                } else {
                    right = null;
                }
                return existingElem;
            } else if (compare > 0) {
                return right.removeRight(elem);
            } else {
                return right.removeLeft(elem);
            }
        }

        public T removeLeft(T elem) {
            T existingElem = left.key;
            int compare = comparator.compare(elem, existingElem);
            if (compare == 0) {
                if (left.right != null && left.left != null) {
                    throw new RuntimeException("this is the difficult case");
                } else if (left.right != null) {
                    left = left.right;
                } else if (left.left != null) {
                    left = left.left;
                } else {
                    left = null;
                }
                return existingElem;
            } else if (compare > 0) {
                return left.removeRight(elem);
            } else {
                return left.removeLeft(elem);
            }
        }
    }
}
