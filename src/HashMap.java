public class HashMap<K, V> implements Map<K, V> {
    private HashSet<Entry> set;

    public HashMap(int numberOfBuckets, HashFunction<K> keyHash, EqualityFunction<K> keyEquality) {
        set = new HashSet<>(numberOfBuckets, entryHashFunction(keyHash), () -> Sets.newSetFromLinkedList(entryEqualityFunction(keyEquality)));
    }

    @Override
    public V get(K key) {
        Entry entry = set.lookup(new Entry(key, null));
        return valueOrNull(entry);
    }

    @Override
    public V remove(K key) {
        Entry entry = set.remove(new Entry(key, null));
        return valueOrNull(entry);
    }

    @Override
    public V put(K key, V value) {
        Entry entry = set.insert(new Entry(key, value));
        return valueOrNull(entry);
    }

    private V valueOrNull(Entry entry) {
        if (entry != null) {
            return entry.value;
        } else {
            return null;
        }
    }

    private EqualityFunction<Entry> entryEqualityFunction(EqualityFunction<K> keyEqualityFunction) {
        return (a, b) -> keyEqualityFunction.equal(a.key, b.key);
    }

    private HashFunction<Entry> entryHashFunction(HashFunction<K> keyHasher) {
        return object -> keyHasher.hashcode(object.key);
    }

    private class Entry {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
