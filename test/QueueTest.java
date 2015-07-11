import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class QueueTest {
    private final Queue<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(QueueFactory) Queues::queueFromDoublyLinkedListForward},
                {(QueueFactory) Queues::queueFromDoublyLinkedListBackward}
        });
    }

    @Test
    public void testEnqueueDequeue() throws Exception {
        structure.enqueue("first element");
        structure.enqueue("last element");

        assertThat(structure.dequeue(), is("first element"));
        assertThat(structure.dequeue(), is("last element"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueOnEmpty() throws Exception {
        structure.dequeue();
    }

    public QueueTest(QueueFactory constructor) {
        structure = constructor.create();
    }

    private interface QueueFactory {
        <T> Queue<T> create();
    }

}