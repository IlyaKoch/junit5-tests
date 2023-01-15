package com.kochetkov.Pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class AppleResultPage {
    private final ElementsCollection searchResults = $$x("//div[@id='featured']/div");
    private final ElementsCollection itemResults = $$x("//ul[@class='chapternav-items']//a");

    public void checkResults(String expected) {
        searchResults.shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(0)
                .shouldHave(Condition.text(expected));
    }

    public void checkListOfGadgetsOnMenuItem(List<String> list) {
        itemResults.shouldBe(CollectionCondition.sizeGreaterThan(0))
                .should(CollectionCondition.containExactTextsCaseSensitive(list));
    }
}