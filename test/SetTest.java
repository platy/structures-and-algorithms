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
public class SetTest {
    private final Set<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(SetFactory) BinaryTree::newWithJavaNaturalComparator}
        });
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

    public SetTest(SetFactory constructor) {
        structure = constructor.create();
    }

    private interface SetFactory {
        Set<String> create();
    }
}
