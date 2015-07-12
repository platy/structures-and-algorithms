@FunctionalInterface
public interface EqualityFunction<T> {
    boolean equal(T a, T b);
}
