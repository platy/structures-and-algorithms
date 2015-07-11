import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class StartResizableTest {

    private final StartResizable<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(StartResizableFactory) LinkedList::new},
                {(StartResizableFactory) DoublyLinkedList::new}
        });
    }

    @Test
    public void testPushPop() throws Exception {
        structure.addFirst("first element");
        structure.addFirst("last element");

        assertThat(structure.getFirst(), is("last element"));
        structure.removeFirst();
        assertThat(structure.getFirst(), is("first element"));
        structure.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastOnEmpty() throws Exception {
        structure.removeFirst();
    }

    public StartResizableTest(StartResizableFactory constructor) {
        structure = constructor.create();
    }

    private interface StartResizableFactory {
        <T> StartResizable<T> create();
    }
}
