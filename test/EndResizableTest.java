import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class EndResizableTest {

    private final EndResizable<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(EndResizableFactory) DynamicArray::newDefault}
        });
    }

    @Test
    public void testPushPop() throws Exception {
        structure.addLast("first element");
        structure.addLast("last element");

        assertThat(structure.getLast(), is("last element"));
        structure.removeLast();
        assertThat(structure.getLast(), is("first element"));
        structure.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastOnEmpty() throws Exception {
        structure.removeLast();
    }

    public EndResizableTest(EndResizableFactory constructor) {
        structure = constructor.create(0);
    }

    private interface EndResizableFactory {
        <T> EndResizable<T> create(int length);
    }
}
