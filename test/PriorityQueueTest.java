import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class PriorityQueueTest {
    private final PriorityQueue<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(PriorityQueueFactory) PriorityQueues::priorityQueueFromBinaryTree}
        });
    }

    @Test
    public void testTakeMinIsInOrder() throws Exception {
        structure.enqueue("a");
        structure.enqueue("b");

        assertThat(structure.takeMin(), is("a"));
        assertThat(structure.takeMin(), is("b"));
    }

    @Test
    public void testTakeMinOnEmpty() throws Exception {
        assertThat(structure.takeMin(), is(nullValue()));
    }

    @Test
    public void testTakeMaxIsInOrder() throws Exception {
        structure.enqueue("a");
        structure.enqueue("b");

        assertThat(structure.takeMax(), is("b"));
        assertThat(structure.takeMax(), is("a"));
    }

    @Test
    public void testTakeMaxOnEmpty() throws Exception {
        assertThat(structure.takeMax(), is(nullValue()));
    }

    public PriorityQueueTest(PriorityQueueFactory constructor) {
        structure = constructor.create(Comparable::compareTo);
    }

    private interface PriorityQueueFactory {
        <T> PriorityQueue<T> create(Comparator<T> comparator);
    }
}