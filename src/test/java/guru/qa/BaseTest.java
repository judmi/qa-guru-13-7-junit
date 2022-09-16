package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.$;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://music.yandex.com/radio";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    public void beforeEach() {
        Selenide.open("https://music.yandex.com/radio");
    }

    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }
}
