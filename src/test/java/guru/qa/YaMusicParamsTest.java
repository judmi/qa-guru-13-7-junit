package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;

public class YaMusicParamsTest extends BaseTest{

    @ValueSource(strings = {"Hinder", "Guns N' Roses"})
    @ParameterizedTest(name = "Searching band \"{0}\" in Yandex.Music returns results with \"{0}\"")
    void yaMusicBandSearchValueSourceTest(String testData) {
        //$(".pay-promo-popup popup deco-pane-popup popup_modal.pay-promo-close-btn js-close").click();
        $("button[type='submit']").click();
        $("input").setValue(testData).pressEnter();
        $$(".d-artists").find(exactText(testData)).shouldBe(visible);

    }

    @CsvSource(value = {
            "Def Leppard, Hysteria",
            "My Chemical Romance, The Black Parade"
    })
    @ParameterizedTest(name = "Search query \"{0}\" in Yandex.Music returns band's album {1}")
    void yaMusicBandAlbumSearchCsvSourceTest(String searchData, String expectedResult) {
        $("button[type='submit']").click();
        $("input").setValue(searchData).pressEnter();
        $(".serp-snippet__albums").
                $$("a").findBy(text(expectedResult)).should(exist);
    }

    @EnumSource(LinkinParkAlbum.class)
    @ParameterizedTest
    void yaMusicLpAlbumSearchEnumSourceTest (LinkinParkAlbum album) {
        $("button[type='submit']").click();
        $("input").setValue(album.name).pressEnter();
        $(".serp-snippet__albums").
                $$("a").findBy(text(album.name)).should(exist);
    }
}
