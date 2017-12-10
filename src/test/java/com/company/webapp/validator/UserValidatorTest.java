package com.company.webapp.validator;

import com.company.webapp.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @Test
    public void testIsValidPassword() throws Exception {
        // too short
        assertEquals(false, userValidator.isValidPassword("123"));

        // no upper case letter
        assertEquals(false, userValidator.isValidPassword("1_abidpsvl"));

        // no number
        assertEquals(false, userValidator.isValidPassword("abZRYUpl"));

        // valid password
        assertEquals(true, userValidator.isValidPassword("12_zwRHIPKA"));
    }

    @Test
    public void testIsOnlyLettersDigitsPoints() throws Exception {
        // too short
        assertEquals(false, userValidator.isOnlyLettersDigitsPoints("1234"));

        // border conditions
        assertEquals(true, userValidator.isOnlyLettersDigitsPoints("User1."));

        // no upper case letter
        assertEquals(false, userValidator.isOnlyLettersDigitsPoints("1abidpsvl"));

        // no number
        assertEquals(false, userValidator.isOnlyLettersDigitsPoints("abZRYUpl"));

        // valid password
        assertEquals(true, userValidator.isOnlyLettersDigitsPoints("12zwRHIPKA."));
    }
}