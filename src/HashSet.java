import java.util.function.Supplier;

public class HashSet<T> implements Set<T> {
    private final HashFunction<T> hasher;
    private final Array<Set<T>> table;

    /**
     * @param numberOfBuckets Number of buckets used in the hashtable
     * @param hasher          function to generate hashes of our elements
     * @param collisionSet    constructor of Sets placed in the hash table to handle collisions, these should only take a
     *                        few entries
     */
    HashSet(int numberOfBuckets, HashFunction<T> hasher, Supplier<Set<T>> collisionSet) {
        this.hasher = hasher;
        table = new Array<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            table.set(i, collisionSet.get());
        }
    }

    @Override
    public T lookup(T elem) {
        return getCollisionSetForElem(elem).lookup(elem);
    }

    @Override
    public T remove(T elem) {
        return getCollisionSetForElem(elem).remove(elem);
    }

    @Override
    public T insert(T elem) {
        return getCollisionSetForElem(elem).insert(elem);
    }

    private Set<T> getCollisionSetForElem(T elem) {
        return table.get(Math.abs(hasher.hashcode(elem)) % table.length());
    }

    @Override
    public ForwardTraverser<T> traverser() {
        return new ForwardTraverser<T>() {
            int nextBucketIndex = 0;
            ForwardTraverser<T> currentSubTraverser;

            @Override
            public T next() {
                if (hasNext()) {
                    return currentSubTraverser.next();
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                if (currentSubTraverser != null && currentSubTraverser.hasNext()) {
                    return true;
                } else if (nextBucketIndex < table.length()) {
                    currentSubTraverser = table.get(nextBucketIndex++).traverser();
                    return hasNext();
                } else {
                    return false;
                }
            }

            @Override
            public void remove() {
                currentSubTraverser.remove();
            }
        };
    }
}
