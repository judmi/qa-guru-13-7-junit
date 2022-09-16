package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;

public class YaParamsTest extends BaseTest{

    @ValueSource(strings = {"JUnit5", "TestNG"})
    @ParameterizedTest(name = "Search query {0} in Yandex returns results with {0}")
    void yaTestCommon(String testData) {
        //Selenide.open("http://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(testData)).shouldBe(visible);

    }

    //@CsvFileSource(resources = "test_data/1.csv")
    @CsvSource(value = {
            "JUnit5, team's statement on the war in Ukraine",
            "TestNG, is a testing framework inspired from JUnit and NUnit"
    })
    @ParameterizedTest(name = "Search query {0} in Yandex returns results with {1}")
    void yaTestComplex(String searchData, String expectedResult) {
        //Selenide.open("http://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(expectedResult)).shouldBe(visible);

    }

    static Stream<Arguments> yaTestVeryComplexDataProvider() {
        return Stream.of(
                Arguments.of("JUnit5", asList("JUnit5", "framework")), //or List.Of instead of asList
                Arguments.of("TestNG", asList("TestNG", "framework"))
        );

    }


    @EnumSource()
    @MethodSource("yaTestVeryComplexDataProvider") //в скобках указываем метод только, если его название отличается от теста
    @ParameterizedTest(name = "Search query {0} in Yandex returns results with {1}")
    void yaTestVeryComplex(String searchData, List<String> expectedResult) {
        //Selenide.open("http://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(CollectionCondition.texts(expectedResult));
    }

    @EnumSource(Gender.class)
    @ParameterizedTest
    void enumTest (Gender gender) {
        //Selenide.open("http://ya.ru");
        $("#text").setValue(gender.desc);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(gender.desc)).shouldBe(visible);
    }
}
