/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package PROG5121POE;
import PROG5121P0E.LogIn;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogInTest {

    @Test
    public void testLoginSuccessful() {
        LogIn login = new LogIn();
        login.setUsername("username");
        login.setPassword("password");
        assertTrue(login.loginUser("username", "password"));
    }

    @Test
    public void testLoginFailed() {
        LogIn login = new LogIn();
        login.setUsername("username");
        login.setPassword("password");
        assertFalse(login.loginUser("wrongUsername", "wrongPassword"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        LogIn login = new LogIn();
        login.setUsername("kyl_1");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        LogIn login = new LogIn();
        login.setUsername("kyle!!!!!!!");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        LogIn login = new LogIn();
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        LogIn login = new LogIn();
        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
    }
}
