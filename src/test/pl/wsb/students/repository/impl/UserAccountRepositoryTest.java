package pl.wsb.students.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.wsb.students.exceptions.ApiException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.model.User;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountRepositoryTest {

    //obiekt dostępu do bazy danych
    @Mock
    private UserAccountRepository mockedUserAccountRepository;

    //test sprawdzania czy jak zapisze do BD to będzie wszystko oke
    @Test
    public void saveUserAccount_whenProperSave_thenProperDeserialization() throws ValidationException, ApiException{
        UserAccount mockUserAccount = new UserAccount();
        mockUserAccount.setEmail("test@test.pl");
        mockUserAccount.setPassSalt("pass_salt");
        mockUserAccount.setPassHash(
                mockUserAccount.generatePassHash(
                        "haslo", mockUserAccount.getPassSalt()
                )
        );
        //to nie działa na BD tylko symuluje działanie
        when(mockedUserAccountRepository.merge(Mockito.any(UserAccount.class))).thenReturn(mockUserAccount);
        User returnedUser = User.createFromUserAccount(mockUserAccount);
        assertEquals(returnedUser.getEmail(), mockUserAccount.getEmail());
    }
}
