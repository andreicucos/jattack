package mockito;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MyListTest {

    @Test
    public void testMockReturn(){
        MyList listMock = Mockito.mock(MyList.class);

        when(listMock.addString(anyInt(), eq("element"))).thenReturn(false);
//        when(listMock.addString(anyInt(), eq("element3"))).thenReturn(true);

        boolean result = listMock.addString(0,"element");

        boolean result2 = listMock.addString(4,"element3");


        System.out.println(result);

        System.out.println(result2);






//        when(listMock.add(anyString())).thenReturn(false);

//        boolean added = listMock.add("test");
//        assertThat(added, is(false));
    }

    @Test
    public void testMockReturnAlternate(){
        MyList listMock = Mockito.mock(MyList.class);

        doReturn(false).when(listMock).add(anyString());

        when(listMock.add(anyString())).thenReturn(false);

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

        spy.addString(1,"test");

        doThrow(NullPointerException.class).when(spy).size();
        spy.size(); // will throw the exception
    }

    @Test
    public void testRealMethodCallOnMock(){
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.size()).thenCallRealMethod();

        assertThat(listMock.size(), equalTo(1));
    }


    @Ignore
    @Test
    public void testMockMetodCallWithCustomAnswer(){
        MyList listMock = Mockito.mock(MyList.class);
        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());

        String element = listMock.get(1);
        assertThat(element, is(equalTo("Always the same")));

        verify(listMock.addString(anyInt(),eq("test")), times(1));
    }

    @Test
    public void whenAddCalledVerfied() {
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class), isA(String.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }

    @Test
    public void whenAddCalledValueCaptured() {
        MyList myList = mock(MyList.class);
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(myList).add(any(Integer.class), valueCapture.capture());
        myList.add (0, "captured");

        assertEquals("captured", valueCapture.getValue());
    }
}
