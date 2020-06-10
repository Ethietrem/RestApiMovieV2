package pl.wsb.students.mockito;

import org.junit.Test;
import org.mockito.Mock;
import pl.wsb.students.hibernatemodel.UserAccount;

import java.util.Iterator;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Mock
    Comparable<String> comparableString;

    @Mock
    Comparable<Integer> comparableInteger;

    @Mock
    Iterator<String> iteratorString;

    @Test
    public void mockitoTest_whenMockGetId_thenProperId(){
        UserAccount userAccount = mock(UserAccount.class);
        when(userAccount.getId()).thenReturn(9);
        assertEquals(userAccount.getId().intValue(), 9);
    }

    @Test
    public void mockitoTest_whenMockIterator_thenProperItems(){
        when(iteratorString.next()).thenReturn("Jeden").thenReturn("Dwa");
        String result = iteratorString.next() + " " + iteratorString.next();
        assertEquals("Jeden Dwa", result);
    }

    @Test
    public void mockitoTest_whenMockDependentMethodValues_thenProperValue(){
        when(comparableString.compareTo("Jeden")).thenReturn(1);
        when(comparableString.compareTo("Dwa")).thenReturn(2);
        assertEquals(1, comparableString.compareTo("Jeden"));
    }

    @Test
    public void mockitoTest_whenMockIndependentMethodValues_thenProperValue(){
        when(comparableInteger.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, comparableInteger.compareTo(9));
    }

    @Test
    public void mockitoTest_whenMockException_thenFail(){
        Properties properties = mock(Properties.class);
        when(properties.get("Dżawa")).thenThrow(new IllegalAccessException());
        try{
            properties.get("Dżawa");
            fail("Java");
        }catch(IllegalArgumentException ex){

        }
    }
}
