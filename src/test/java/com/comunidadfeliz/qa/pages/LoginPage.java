package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final Waits waits;

    private final By emailInput = By.id("email");
    private final By passInput  = By.id("password");
    private final By loginBtn   = By.cssSelector(".btn-green-cf");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String user, String pass) {
        waits.visible(emailInput).clear();
        waits.visible(emailInput).sendKeys(user);

        waits.visible(passInput).clear();
        waits.visible(passInput).sendKeys(pass);

        waits.safeClick(loginBtn);
    }

    /**
     * Login + espera REAL a que termine la autenticación (evita redirección a /ingresa)
     */
    public void loginAndWait(String user, String pass) {
        login(user, pass);

        // Espera a que el login “impacte”. Lo más estable: URL /comunidades
        // (si cambia, reemplazalo por un elemento post-login)
        waits.urlContains("/comunidades");
    }
}
