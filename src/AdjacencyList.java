public class AdjacencyList<V, VV> implements DirectedGraph<V, VV> {
    private final EqualityFunction<V> vertexEqualityFunction;
    private final HashSet<Vertex> vertices;

    public AdjacencyList(int numberOfBuckets, HashFunction<V> hashFunction, EqualityFunction<V> equalityFunction) {
        vertexEqualityFunction = equalityFunction;
        this.vertices = new HashSet<>(
                numberOfBuckets,
                vertexHashFunction(hashFunction),
                () -> Sets.newSetFromLinkedList(vertexEqualityFunction(equalityFunction)));
    }

    @Override
    public void add(V source, V destination) {
        vertex(source).neighbours.insert(destination);
    }

    @Override
    public void delete(V source, V destination) {
        vertex(source).neighbours.remove(destination);
    }

    @Override
    public boolean adjacent(V source, V destination) {
        return vertex(source).neighbours.lookup(destination) != null;
    }

    @Override
    public ForwardTraversable<V> neighbours(V start) {
        return vertex(start).neighbours;
    }

    private Vertex vertex(V key) {
        Vertex referenceVertex = new Vertex(key);
        Vertex actualVertex = vertices.lookup(referenceVertex);
        if (actualVertex != null) {
            return actualVertex;
        } else {
            referenceVertex.neighbours = Sets.newSetFromLinkedList(vertexEqualityFunction);
            vertices.insert(referenceVertex);
            return referenceVertex;
        }
    }

    private EqualityFunction<Vertex> vertexEqualityFunction(EqualityFunction<V> recordEqualityFunction) {
        return (a, b) -> recordEqualityFunction.equal(a.record, b.record);
    }

    private HashFunction<Vertex> vertexHashFunction(HashFunction<V> recordHasher) {
        return object -> recordHasher.hashcode(object.record);
    }

    private class Vertex {
        V record;
        public Set<V> neighbours;

        public Vertex(V record) {
            this.record = record;
        }
    }
}
