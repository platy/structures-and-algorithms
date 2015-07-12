import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IndexedTest {

    private final Indexed<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(IndexedMutableStructureFactory) Array::new},
                {(IndexedMutableStructureFactory) DynamicArray::newDefault}
        });
    }

    @Test
    public void testIndexedAccess() throws Exception {
        structure.set(0, "first element");
        structure.set(3, "last element");

        assertThat(structure.get(0), is("first element"));
        assertThat(structure.get(3), is("last element"));
    }

    @Test
    public void testSizeReporting() throws Exception {
        assertThat(structure.length(), is(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNegativeIndex() throws Exception {
        structure.get(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOutOfBoundsIndex() throws Exception {
        structure.get(4);
    }

    public IndexedTest(IndexedMutableStructureFactory constructor) {
        structure = constructor.create(4);
    }

    private interface IndexedMutableStructureFactory {
        <T> Indexed<T> create(int length);
    }
}
