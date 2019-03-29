package mockito;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MyListTest {

    @Test
    public void testMockReturn(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);

        boolean added = listMock.add("test");
        assertThat(added, is(false));
    }

    @Test
    public void testMockReturnAlternate(){
        MyList listMock = Mockito.mock(MyList.class);
        doReturn(false).when(listMock).add(anyString());

        boolean added = listMock.add("test");
        assertThat(added, is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString())).thenThrow(IllegalStateException.class);

        listMock.add("test");
    }

    @Test(expected = NullPointerException.class)
    public void givenVoidMethod_whenCallingMethod_thenExceptionIsThrown(){
        MyList listMock = Mockito.mock(MyList.class);
        doThrow(NullPointerException.class).when(listMock).clear();

        listMock.clear();
    }

    @Test(expected = IllegalStateException.class)
    public void testConfigurationOfMultipleCalls(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString()))
                .thenReturn(false)
                .thenThrow(IllegalStateException.class);

        listMock.add("test1");
        listMock.add("test2"); // will throw the exception
    }

    @Test(expected = NullPointerException.class)
    public void testSpyBehavior(){
        //create real instance
        MyList instance = new MyList();
        //create spy using the real instance
        MyList spy = Mockito.spy(instance);

        //mock the call to a certain method
        doThrow(NullPointerException.class).when(spy).size();
        spy.size(); // will throw the exception
    }

    @Test
    public void testRealMethodCallOnMock(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.size()).thenCallRealMethod();

        assertThat(listMock.size(), equalTo(1));
    }

    @Test
    public void testMockMetodCallWithCustomAnswer(){
        MyList listMock = Mockito.mock(MyList.class);
        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());

        String element = listMock.get(1);
        assertThat(element, is(equalTo("Always the same")));

        verify(listMock,times(1));
    }
}
