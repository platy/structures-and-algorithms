@FunctionalInterface
public interface HashFunction<T> {
    int hashcode(T object);
}
