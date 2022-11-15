package com.example.ecommerce.User;

import com.example.ecommerce.CustomExceptions.InvalidEmailException;
import com.example.ecommerce.CustomExceptions.InvalidPasswordException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    private UserService underTest;

    @BeforeEach
    void setUp(){
        underTest = new UserService(userRepository);
    }


    @Test
    void canGetUsers() {
        //when
        underTest.getUsers();
        //then
        // verifies if findall method is invoked from the user repository
        verify(userRepository).findAll();
    }

    @Test
    void canAddUser() throws InvalidPasswordException, InvalidEmailException {
        //given
        String email = "sam@hotmail.com";
        AppUser user = new AppUser(
                "sam",
                LocalDate.of(2011,12,12),
                "Pass12", email);
        //when
        underTest.addUser(user);

        //then
        ArgumentCaptor<AppUser> appUserArgumentCaptor =
                ArgumentCaptor.forClass(AppUser.class);

        verify(userRepository)
                .save(appUserArgumentCaptor.capture());
        AppUser capturedUser = appUserArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenEmailIsTaken() throws InvalidPasswordException, InvalidEmailException {
        //given
        String email = "sam@hotmail.com";
        AppUser user = new AppUser(
                "sam",
                LocalDate.of(2011,12,12),
                "Pass12", email);
        // I can put anystring() inside the argument because we are mocking it, this isnt actually happening
        given(userRepository.findAppUserByEmail(anyString()))
                .willReturn(Optional.of(user));
        //when
        //then
        assertThatThrownBy(()->underTest.addUser(user))
                .isInstanceOf(InvalidEmailException.class)
                .hasMessageContaining("This email is already in use.");
    // verifies that the user repository never saves the user
        verify(userRepository, never()).save(user);

    }

    // todo: add cases for the other two exceptions

    @Test
    @Disabled
    void deleteUser() {
    }

    @Test
    @Disabled
    void getUserById() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}