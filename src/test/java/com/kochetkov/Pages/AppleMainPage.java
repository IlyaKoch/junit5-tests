package com.kochetkov.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.kochetkov.Enums.MenuItem;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AppleMainPage {

    public static final String URL = "https://www.apple.com/ru/";

    private final SelenideElement iconForSearch = $x("//a[@id='ac-gn-link-search']");
    private final SelenideElement inputSearch = $x("//input[@id='ac-gn-searchform-input']");

    public AppleResultPage doSearch(String searchQuery) {
        iconForSearch.click();
        inputSearch.setValue(searchQuery)
                .pressEnter();
        return new AppleResultPage();
    }

    public AppleResultPage switchToMenuItem(MenuItem menuItem) {
        $$x("//ul[@class='ac-gn-list']//a").find(Condition.text(menuItem.getDesc())).click();
        return new AppleResultPage();
    }
}