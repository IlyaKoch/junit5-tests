package com.kochetkov;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.kochetkov.Enums.MenuItem;
import com.kochetkov.Pages.AppleMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

public class AppleSearchGadgetTests {

    @BeforeEach
    void configurationBrowser() {
        Configuration.browserSize = "1920x1080";
    }

    private final AppleMainPage amp = new AppleMainPage();

    @DisplayName("iPhone SE should be present in search results")
    @Test
    void findOnSiteTest() {
        Selenide.open(AppleMainPage.URL);
        amp.doSearch("iPhone SE")
                .checkResults("iPhone SE");
    }

    @ValueSource(strings = {
            "iPhone 12",
            "iPhone 13",
            "iPhone SE"
    })
    @ParameterizedTest(name = "Check search results for {0}")
    void searchIphoneOnSite(String searchQuery) {
        Selenide.open(AppleMainPage.URL);
        amp.doSearch(searchQuery)
                .checkResults(searchQuery);
    }

    @CsvSource(value = {
            "12 ; Чип A14 Bionic ; Check iPhone 12 specs",
            "13 ; Сверхбыстрый чип A15 Bionic ; Check iPhone 13 specs"
    },
            delimiter = ';')
    @ParameterizedTest(name = "{2}")
    void checkSpecsIphoneTest(int iphoneModel, String chipModel, String testName) {
        Selenide.open(AppleMainPage.URL);
        amp.doSearch("Iphone" + iphoneModel)
                .checkResults(chipModel);
    }

    @EnumSource(MenuItem.class)
    @ParameterizedTest(name = "Click on tab {0}")
    void checkTabsOnSite(MenuItem menuItem) {
        Selenide.open(AppleMainPage.URL);
        amp.switchToMenuItem(menuItem);
    }

    static Stream<Arguments> productAvailabilityTest() {
        return Stream.of(
                Arguments.of(
                        "MAC", List.of("MacBook Air", "MacBook Pro", "iMac 24”", "Mac Pro", "Mac mini")
                ),
                Arguments.of(
                        "IPAD", List.of("iPad Pro", "iPad", "iPad mini")
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Check product availability - {0}")
    void productAvailabilityTest(String testName, List<String> gadgets) {
        Selenide.open(AppleMainPage.URL);
        amp.switchToMenuItem(MenuItem.valueOf(testName)).
                checkListOfGadgetsOnMenuItem(gadgets);
    }
}