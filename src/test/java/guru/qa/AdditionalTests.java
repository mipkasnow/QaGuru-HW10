package guru.qa;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdditionalTests {

    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @Test
    void successTest(){
        assertTrue(true);
    }

    @Test
    void failedTest(){
        assertTrue(false);
    }

    @Disabled("I want to skip this!")
    @Test
    void skippedTest(){
        assertTrue(false);
    }

    @Test
    void ownerConfigTest(){
        String login = config.login();
        String password = config.password();
        String message = format("I get login %s ans password %s from Credentials", login, password);
        System.out.println(message);
    }
}
