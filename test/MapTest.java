import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MapTest {
    private final Map<String, String> structure;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testClasses() {
        return Arrays.asList(new Object[][]{
                {"HashMap", (MapFactory) MapTest::newHashMap}
        });
    }

    private static Map<String, String> newHashMap() {
        return new HashMap<>(10, String::hashCode, String::equals);
    }

    @Test
    public void emptyGetReturnsNull() throws Exception {
        assertThat(structure.get("something"), is(nullValue()));
    }

    @Test
    public void putGetSomething() throws Exception {
        structure.put("k", "v");
        assertThat(structure.get("k"), is("v"));
    }

    @Test
    public void deleteSomething() throws Exception {
        structure.put("k", "v");
        assertThat(structure.remove("k"), is("v"));
        assertThat(structure.get("k"), is(nullValue()));
    }

    @Test
    public void overwrite() throws Exception {
        structure.put("k", "1");
        assertThat(structure.put("k", "2"), is("1"));
        assertThat(structure.get("k"), is("2"));
    }

    public MapTest(String name, MapFactory constructor) {
        structure = constructor.create();
    }

    private interface MapFactory {
        Map<String, String> create();
    }
}
