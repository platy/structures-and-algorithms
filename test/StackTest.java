import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class StackTest {

    private final Stack<String> structure;

    @Parameterized.Parameters
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {(StackFactory) StackTest::dynamicArrayStack},
                {(StackFactory) StackTest::linkedListStack}
        });
    }

    static <T> Stack<T> dynamicArrayStack() {
        return Stacks.fromEndResizable(DynamicArray.newDefault(0));
    }

    static <T> Stack<T> linkedListStack() {
        return Stacks.fromStartResizable(new LinkedList<>());
    }

    @Test
    public void testPushPop() throws Exception {
        structure.push("first element");
        structure.push("last element");

        assertThat(structure.pop(), is("last element"));
        assertThat(structure.pop(), is("first element"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopOnEmpty() throws Exception {
        structure.pop();
    }

    public StackTest(StackFactory constructor) {
        structure = constructor.create();
    }

    private interface StackFactory {
        <T> Stack<T> create();
    }
}
