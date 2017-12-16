package com.company.webapp.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

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