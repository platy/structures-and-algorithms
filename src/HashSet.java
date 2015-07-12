import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class HashSet<T> implements Set<T> {
    private final ToIntFunction<T> hasher;
    private final Array<Set<T>> table;

    /**
     * @param numberOfBuckets Number of buckets used in the hashtable
     * @param hasher function to generate hashes of our elements
     * @param collisionSet constructor of Sets placed in the hash table to handle collisions, these should only take a
     *                     few entries
     */
    HashSet(int numberOfBuckets, ToIntFunction<T> hasher, Supplier<Set<T>> collisionSet) {
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
    public void insert(T elem) {
        getCollisionSetForElem(elem).insert(elem);
    }

    private Set<T> getCollisionSetForElem(T elem) {
        return table.get(Math.abs(hasher.applyAsInt(elem)) % table.length());
    }
}
