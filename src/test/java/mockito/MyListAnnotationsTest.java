package mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

// @RunWith attaches a runner with the test class to initialize the test data -> makes the @Mock annotation work
@RunWith(MockitoJUnitRunner.class)
public class MyListAnnotationsTest {

    @Mock
    MyList listMock;

    @Before
    public void setup() {
        System.out.println("Setup executed before the test");
        when(listMock.add(anyString())).thenReturn(false);
    }

    @After
    public void clean() {
        System.out.println("Cleanup executed after the test");
    }

    @Test
    public void test() {
        System.out.println("Running the test");
        boolean added = listMock.add("test");
        assertThat(added, is(false));
    }

}
