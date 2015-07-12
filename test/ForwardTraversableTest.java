import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ForwardTraversableTest {

    private final ForwardTraversable<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(ForwardTraversableFactory) ForwardTraversableTest::populatedLinkedList},
                {(ForwardTraversableFactory) ForwardTraversableTest::populatedDoublyLinkedList}
        });
    }

    private static LinkedList<String> populatedLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("c");
        list.addFirst("b");
        list.addFirst("a");
        return list;
    }

    private static DoublyLinkedList<String> populatedDoublyLinkedList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("c");
        list.addFirst("b");
        list.addFirst("a");
        return list;
    }

    @Test
    public void testTraversal() throws Exception {
        ForwardTraverser<String> traverser = structure.traverser();

        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("a"));
        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("b"));
        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("c"));
        assertThat(traverser.hasNext(), is(false));
    }

    public ForwardTraversableTest(ForwardTraversableFactory constructor) {
        structure = constructor.create();
    }

    private interface ForwardTraversableFactory {
        ForwardTraversable<String> create();
    }
}
