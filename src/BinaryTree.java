import java.util.function.Function;

/**
 * Implementation issues:
 * * In some places this uses recursive algorithms which could be a problem for large trees - as java deos not have tail
 * call optimisation.
 * * The removal of a node with 2 children is solved by replacing it with its successor, a lot of removals around the
 * same region could unbalance the tree
 * * Doesn't correctly remove when there are equal keys in the tree
 * * Allows equal entities so it isn't a set - but it is very close
 * @param <T>
 */
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
            return root.remove(elem, newRoot -> {
                root = newRoot;
                return null;
            });
        } else {
            return null;
        }
    }

    @Override
    public T insert(T elem) {
        if (root == null) {
            root = new Node(elem);
        } else {
            root.insert(elem);
        }
        return null; // doesn't replace - not really set
    }

    public static <T extends Comparable<T>> Set<T> newWithJavaNaturalComparator() {
        return new BinaryTree<>(Comparable::compareTo);
    }

    public T findMin() {
        if (root == null) {
            return null;
        } else {
            return root.findMin();
        }
    }

    public T findMax() {
        if (root == null) {
            return null;
        } else {
            return root.findMax();
        }
    }

    private class Node {
        Node left;
        T key;
        Node right;

        Node(T key) {
            this.key = key;
        }

        void insert(T elem) {
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

        T lookup(T elem) {
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

        T remove(T elem, Function<Node, Void> replaceSelf) {
            T existingElem = key;
            int compare = comparator.compare(elem, existingElem);
            if (compare == 0) {
                if (right != null && left != null) {
                    // here the key could be replaced by either the successor or the predecessor,
                    // always doing the same thing could lead to an imbalanced tree
                    key = right.findMin();
                    right.remove(key, newRight -> {
                        right = newRight;
                        return null;
                    });
                } else if (right != null) {
                    replaceSelf.apply(right);
                } else if (left != null) {
                    replaceSelf.apply(left);
                } else {
                    replaceSelf.apply(null);
                }
                return existingElem;
            } else if (compare > 0 && right != null) {
                return right.remove(elem, newRight -> {
                    right = newRight;
                    return null;
                });
            } else if (left != null) {
                return left.remove(elem, newLeft -> {
                    left = newLeft;
                    return null;
                });
            } else {
                return null;
            }
        }

        T findMin() {
            if (left != null) {
                return left.findMin();
            } else {
                return key;
            }
        }

        T findMax() {
            if (right != null) {
                return right.findMax();
            } else {
                return key;
            }
        }
    }
}
