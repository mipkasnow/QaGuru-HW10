package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {
    @BeforeAll
    static void before(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        clearBrowserCookies();
        Configuration.browserSize = "1590x850";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void checkForm() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Mikhail");
        $("#lastName").setValue("Loginov");
        $("#userEmail").setValue("random@mail.ru");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $("[class*='month-select']").selectOptionByValue("2");
        $("[class*='year-select']").selectOptionByValue("1993");
        $("[class*='datepicker__day--016']").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();

        ElementsCollection hobbies = $$("[for*='hobbies-checkbox']");
        int count = hobbies.size();
        for (int i = 0; i < count; i++) {
            hobbies.get(i).click();
        }

        //$("input[id='uploadPicture']").uploadFromClasspath("qa-guru.txt");

        $("[placeholder='Current Address']").setValue("Saint-Pee");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        checkResult();
    }

    void checkResult(){
        $(byText("Thanks for submitting the form")).should(appear);
        $(byText("Mikhail Loginov")).should(appear);
        $(byText("random@mail.ru")).should(appear);
        $(byText("1234567890")).should(appear);
        $(byText("16 March,1993")).should(appear);
        $(byText("Maths")).should(appear);
        $(byText("Sports, Reading, Music")).should(appear);
        //$(byText("qa-guru.txt")).should(appear);
        $(byText("Saint-Pee")).should(appear);
        $(byText("NCR Delhi")).should(appear);
    }

    @AfterEach
    void after(){
        Tools.addVideo();
        Tools.screenshotAs("Last screen");
        Tools.pageSource();
        Tools.browserConsoleLogs();
        closeWebDriver();
    }
}
