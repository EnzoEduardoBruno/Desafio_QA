package com.comunidadfeliz.qa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Waits(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement visible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement clickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement present(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean isPresent(By by) {
        try {
            present(by);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isVisible(By by) {
        try {
            return visible(by).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // URL waits
    public void urlContains(String fragment) {
        wait.until(ExpectedConditions.urlContains(fragment));
    }

    public void urlMatches(String regex) {
        wait.until(ExpectedConditions.urlMatches(regex));
    }

    // Invisibilidad (para dropdowns)
    public void invisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    // Scroll / JS / Actions
    public void scrollIntoView(By by) {
        WebElement el = present(by);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
    }

    public void jsClick(By by) {
        WebElement el = present(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void moveTo(By by) {
        WebElement el = present(by);
        new Actions(driver).moveToElement(el).pause(Duration.ofMillis(150)).perform();
    }

    // Hover (para “despegar” sidebar)
    public void hover(By by) {
        WebElement el = present(by);
        new Actions(driver).moveToElement(el).pause(Duration.ofMillis(200)).perform();
    }

    // Esperar que un atributo contenga un valor (ej: class contiene "active")
    public void attributeContains(By by, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(by, attribute, value));
    }

    // Check rápido sin wait largo
    public boolean attributeContainsNow(By by, String attribute, String value) {
        try {
            WebElement el = present(by);
            String attr = el.getAttribute(attribute);
            return attr != null && attr.contains(value);
        } catch (Exception e) {
            return false;
        }
    }

    // Pausa chica para animaciones/transiciones
    public void smallPause(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // Click robusto
    public void safeClick(By by) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                scrollIntoView(by);
                clickable(by).click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (ElementClickInterceptedException | TimeoutException e) {
                attempts++;
                try { moveTo(by); } catch (Exception ignored) {}
                try { jsClick(by); return; } catch (Exception ignored) {}
            }
        }
        jsClick(by);
    }

    // Select helpers (si algún día hay <select> real)
    public void selectByVisibleText(By selectBy, String visibleText) {
        WebElement el = visible(selectBy);
        new Select(el).selectByVisibleText(visibleText);
    }

    public void selectByValue(By selectBy, String value) {
        WebElement el = visible(selectBy);
        new Select(el).selectByValue(value);
    }
}
