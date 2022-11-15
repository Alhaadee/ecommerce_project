package com.example.ecommerce.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void canFindAppUserByEmail() {
        //given
        String email = "sam@hotmail.com";
        AppUser appUser = new AppUser("sam", LocalDate.of(2011,12,12),"Pass12", email);
        underTest.save(appUser);
        //when
        Optional<AppUser> expectedUser = underTest.findAppUserByEmail(email);
        //then
        assertThat(expectedUser).isPresent();
    }

    @Test
    void canFindAppUserByEmail__When__UserDoesNotExist() {
        //given
        String email = "sam@hotmail.com";
        //when
        Optional<AppUser> expectedUser = underTest.findAppUserByEmail(email);
        //then
        assertThat(expectedUser).isEmpty();
    }
}