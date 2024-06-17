/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package PROG5121POE;
import PROG5121P0E.SignUp;
import org.junit.Test;
import static org.junit.Assert.*;

public class SignUpTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        SignUp signUp = new SignUp();
        signUp.setUsername("kyl_1");
        assertTrue(signUp.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        SignUp signUp = new SignUp();
        signUp.setUsername("kyle!!!!!!!");
        assertFalse(signUp.checkUserName());
    }

    @Test
    public void testUsernameWithoutUnderscore() {
        SignUp signUp = new SignUp();
        signUp.setUsername("abcde");
        assertFalse(signUp.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        SignUp signUp = new SignUp();
        signUp.setPassword("Ch&&sec@ke99!");
        assertTrue(signUp.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        SignUp signUp = new SignUp();
        signUp.setPassword("password");
        assertFalse(signUp.checkPasswordComplexity());
    }
}

