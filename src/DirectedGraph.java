public interface DirectedGraph<V, VV> {

    void add(V source, V destination);
    void delete(V source, V destination);
    boolean adjacent(V source, V destination);
    ForwardTraversable<V> neighbours(V node);

//    VV getNodeValue(V node);
//    VV setNodeValue(V node, VV value);
//
//    E getEdgeValue(V source, V destination);
//    E setEdgeValue(V source, V destination, E value);
}
