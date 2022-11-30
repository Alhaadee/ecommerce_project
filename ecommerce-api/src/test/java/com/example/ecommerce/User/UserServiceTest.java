package com.example.ecommerce.User;

import com.example.ecommerce.CustomExceptions.InvalidEmailException;
import com.example.ecommerce.CustomExceptions.InvalidPasswordException;
import com.example.ecommerce.CustomExceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    private UserService underTest;

    @BeforeEach
    void setUp(){
        underTest = new UserService(userRepository, passwordEncoder);
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
    void addUser__willThrowWhenEmailIsTaken() throws InvalidPasswordException, InvalidEmailException {
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

    @Test
    void addUser__willThrowWhenInvalidPassword() throws InvalidPasswordException, InvalidEmailException {
        //given
        String password = "pasdgh";
        AppUser user = new AppUser(
                "sam",
                LocalDate.of(2011,12,12),
                password, "sam@hotmail.com");
        // I can put anystring() inside the argument because we are mocking it, this isnt actually happening
        given(userRepository.findAppUserByEmail(anyString()))
                .willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(()->underTest.addUser(user))
                .isInstanceOf(InvalidPasswordException.class);
        // verifies that the user repository never saves the user
        verify(userRepository, never()).save(user);

    }

    // todo: add cases for the empty email

    @Test
    void canDeleteUser() throws UserNotFoundException {
        //given
        long id = 10;
        given(userRepository.existsById(id)).willReturn(true);
        //when then
        underTest.deleteUser(id);

        verify(userRepository).deleteById(id);
    }

    @Test        // I think this test is wrong
    void canGetUserById() throws UserNotFoundException {
        String email = "sam@hotmail.com";
        AppUser user = new AppUser(
                "sam",
                LocalDate.of(2011,12,12),
                "Pass12", email);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        underTest.getUserById(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void canThrowErrorIfNoMatchingUserId() throws UserNotFoundException {

        ;
        given(userRepository.findById(12L)).willReturn(Optional.empty());
        assertThatThrownBy(()->underTest.getUserById(12L))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void canUpdateStudent() throws UserNotFoundException, InvalidPasswordException, InvalidEmailException {
        //given
        String email = "sam@hotmail.com";
        AppUser user = new AppUser(
                "sam",
                LocalDate.of(2011,12,12),
                "Pass12as", email);
        user.setId(1L);
        String name = "john";
//        given(underTest.getUserById(1L)).willReturn(user);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        underTest.updateStudent(1L,name,email,"newPass123",null);
        ArgumentCaptor<AppUser> appUserArgumentCaptor =
                ArgumentCaptor.forClass(AppUser.class);

        verify(userRepository)
                .save(appUserArgumentCaptor.capture());
        AppUser capturedUser = appUserArgumentCaptor.getValue();
        assertThat(capturedUser.getName()).isEqualTo("john");
        assertThat(capturedUser.getPassword()).isEqualTo("newPass123");
        assertThat(capturedUser.getDob()).isEqualTo("2011-12-12");



    }
}