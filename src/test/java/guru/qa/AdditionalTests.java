package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdditionalTests {


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
}
