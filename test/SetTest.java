import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SetTest {
    private final Set<String> structure;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {"BinaryTree", (SetFactory) BinaryTree::newWithJavaNaturalComparator},
                {"LinkedList", (SetFactory) SetTest::newSetFromLinkedList},
                {"Small HashSet", (SetFactory) SetTest::newSmallHashSet},
                {"Large HashSet", (SetFactory) SetTest::newLargeHashSet}
        });
    }

    private static Set<String> newSmallHashSet() {
        return new HashSet<>(1, String::hashCode, SetTest::newSetFromLinkedList);
    }

    private static Set<String> newLargeHashSet() {
        return new HashSet<>(60, String::hashCode, SetTest::newSetFromLinkedList);
    }

    private static Set<String> newSetFromLinkedList() {
        return Sets.newSetFromLinkedList(String::equals);
    }

    @Test
    public void test1Element() throws Exception {
        structure.insert("first element");

        assertThat(structure.lookup("first element"), is("first element"));
        assertThat(structure.remove("first element"), is("first element"));
        assertThat(structure.remove("first element"), is(nullValue()));
    }

    @Test
    public void testToRight() throws Exception {
        structure.insert("a");
        structure.insert("b");

        assertThat(structure.lookup("a"), is("a"));
        assertThat(structure.lookup("b"), is("b"));

        assertThat(structure.remove("a"), is("a"));
        assertThat(structure.remove("b"), is("b"));
    }

    @Test
    public void testInsertToRightLookupToLeft() throws Exception {
        structure.insert("a");
        structure.insert("b");

        assertThat(structure.lookup("b"), is("b"));
        assertThat(structure.lookup("a"), is("a"));

        assertThat(structure.remove("b"), is("b"));
        assertThat(structure.remove("a"), is("a"));
    }

    @Test
    public void testToLeft() throws Exception {
        structure.insert("b");
        structure.insert("a");

        assertThat(structure.lookup("b"), is("b"));
        assertThat(structure.lookup("a"), is("a"));

        assertThat(structure.remove("b"), is("b"));
        assertThat(structure.remove("a"), is("a"));
    }

    @Test
    public void testInsertToLeftLookupToRight() throws Exception {
        structure.insert("b");
        structure.insert("a");

        assertThat(structure.lookup("a"), is("a"));
        assertThat(structure.lookup("b"), is("b"));

        assertThat(structure.remove("a"), is("a"));
        assertThat(structure.remove("b"), is("b"));
    }

    @Test
    public void testLookupNonexistent() throws Exception {
        assertThat(structure.lookup("none"), is(nullValue()));
        structure.insert("something");
        assertThat(structure.lookup("nothing"), is(nullValue()));
    }

    @Test
    public void testRemoveNonexistent() throws Exception {
        assertThat(structure.remove("none"), is(nullValue()));
        structure.insert("something");
        assertThat(structure.remove("nothing"), is(nullValue()));
    }

    @Test
    public void removeANodeWith2Children() throws Exception {
        structure.insert("b");
        structure.insert("a");
        structure.insert("c");

        assertThat(structure.remove("b"), is("b"));
        assertThat(structure.remove("a"), is("a"));
        assertThat(structure.remove("c"), is("c"));
    }

    @SuppressWarnings("RedundantStringConstructorCall")
    @Test
    public void testDuplicatesOverwrite() throws Exception {
        String key1 = new String("a");
        String key2 = new String("a");
        assertThat(key1, is(not(sameInstance(key2))));

        structure.insert(key1);
        assertThat(structure.insert(key2), is(sameInstance(key1)));

        assertThat(structure.remove("a"), is(sameInstance(key2)));
        assertThat(structure.lookup("a"), is(nullValue()));
    }

    public SetTest(String name, SetFactory constructor) {
        structure = constructor.create();
    }

    private interface SetFactory {
        Set<String> create();
    }
}
