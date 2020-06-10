package pl.wsb.students.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import pl.wsb.students.hibernatemodel.UserAccount;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoVerifyTest {

    @Test
    public void mockitoTest_whenVerify_thenVerify(){
        UserAccount userAccount = mock(UserAccount.class);
        when(userAccount.getId()).thenReturn(9);
        userAccount.setEmail("test@test.pl");
        userAccount.getId();
        userAccount.getId();
        verify(userAccount).setEmail(ArgumentMatchers.eq("test@test.pl"));
        verify(userAccount, times(2)).getId();
        verify(userAccount, never()).getEmail();
        verify(userAccount, atLeastOnce()).getEmail();
        verify(userAccount, atLeast(2)).getEmail();
        verify(userAccount, times(5)).getEmail();
        verify(userAccount, atMost(3)).getEmail();
        verifyNoMoreInteractions(userAccount);
    }
}
