import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * The large HashSet passes this test except that it fails to delete an entry.
 * So it both has a removal problem and may actually only be using a single bucket and functioning as a Linked List.
 */
@RunWith(Parameterized.class)
public class ForwardTraversableTest {

    private final ForwardTraversable<String> structure;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {"LinkedList", (ForwardTraversableFactory) ForwardTraversableTest::populatedLinkedList},
                {"DoublyLinkedList", (ForwardTraversableFactory) ForwardTraversableTest::populatedDoublyLinkedList},
//                {"BinaryTree", (ForwardTraversableFactory) BinaryTree::newWithJavaNaturalComparator},
                {"LinkedListSet", (ForwardTraversableFactory) ForwardTraversableTest::newSetFromLinkedList},
                {"Small HashSet", (ForwardTraversableFactory) ForwardTraversableTest::newSmallHashSet},
                {"Large HashSet", (ForwardTraversableFactory) ForwardTraversableTest::newLargeHashSet},
        });
    }

    private static Set<String> newSmallHashSet() {
        HashSet<String> set = new HashSet<>(1, String::hashCode, ForwardTraversableTest::newSetFromLinkedList);
        set.insert("c");
        set.insert("b");
        set.insert("a");
        return set;
    }

    private static Set<String> newLargeHashSet() {
        HashSet<String> set = new HashSet<>(60, String::hashCode, ForwardTraversableTest::newSetFromLinkedList);
        set.insert("c");
        set.insert("b");
        set.insert("a");
        return set;
    }

    private static Set<String> newSetFromLinkedList() {
        Set<String> set = Sets.newSetFromLinkedList(String::equals);
        set.insert("c");
        set.insert("b");
        set.insert("a");
        return set;
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

    @Test
    public void testRemoval() throws Exception {
        ForwardTraverser<String> traverser = structure.traverser();

        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("a"));
        traverser.remove();
        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("b"));
        traverser.remove();
        assertThat(traverser.hasNext(), is(true));
        assertThat(traverser.next(), is("c"));
        traverser.remove();
        assertThat(traverser.hasNext(), is(false));

        assertThat(structure.traverser().hasNext(), is(false));
    }

    public ForwardTraversableTest(String name, ForwardTraversableFactory constructor) {
        structure = constructor.create();
    }

    private interface ForwardTraversableFactory {
        ForwardTraversable<String> create();
    }
}
