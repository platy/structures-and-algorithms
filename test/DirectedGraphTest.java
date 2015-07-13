import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DirectedGraphTest {
    private final DirectedGraph<String, String> structure;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {"AdjacencyList", (DirectedGraphFactory) DirectedGraphTest::newAdjacencyList}
        });
    }

    private static DirectedGraph<String, String> newAdjacencyList() {
        return new AdjacencyList<>(10, String::hashCode, String::equals);
    }

    @Test
    public void testAdjacencyOfConnectedElements() throws Exception {
        structure.add("a", "b");
        assertThat(structure.adjacent("a", "b"), is(true));
    }

    @Test
    public void unconnectedNodesAreNotAdjacent() throws Exception {
        assertThat(structure.adjacent("a", "b"), is(false));
    }

    @Test
    public void listOfNeighbours() throws Exception {
        structure.add("a", "b");
        structure.add("a", "c");
        Iterable<String> iterable = getJavaIterable(structure.neighbours("a"));

        assertThat(iterable, containsInAnyOrder("b", "c"));
    }

    @Test
    public void deleteEdge() throws Exception {
        structure.add("a", "b");
        assertThat(structure.adjacent("a", "b"), is(true));
        structure.delete("a", "b");
        assertThat(structure.adjacent("a", "b"), is(false));
    }

    private <T> Iterable<T> getJavaIterable(ForwardTraversable<T> traversable) {
        return () -> {
                ForwardTraverser<T> traverser = traversable.traverser();
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        return traverser.hasNext();
                    }

                    @Override
                    public T next() {
                        return traverser.next();
                    }
                };
            };
    }

    public DirectedGraphTest(String name, DirectedGraphFactory constructor) {
        structure = constructor.create();
    }

    private interface DirectedGraphFactory {
        DirectedGraph<String, String> create();
    }
}
