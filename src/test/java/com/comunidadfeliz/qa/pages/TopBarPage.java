package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarPage {
    private final Waits waits;

    private final By communityDropdown = By.id("admin-edit-community");

    public TopBarPage(WebDriver driver) {
        this.waits = new Waits(driver);
    }

    public void switchCommunity(String name) {
        waits.clickable(communityDropdown).click();
        By option = By.xpath("//*[self::li or self::div or self::button][contains(.,'" + name + "')]");
        waits.clickable(option).click();
    }
}
