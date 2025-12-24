package com.comunidadfeliz.qa.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void before() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @After
    public void after(Scenario scenario) {
        // Si falla, adjunta screenshot al reporte
        try {
            if (scenario.isFailed() && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot-failure");
            }
        } catch (Exception ignored) {}

        // ✅ Modo debug: si falla, NO cierres el navegador (así ves dónde quedó)
        boolean keepOpenOnFail = Boolean.parseBoolean(System.getProperty("KEEP_OPEN_ON_FAIL", "false"));

        if (scenario.isFailed() && keepOpenOnFail) {
            System.out.println("[HOOKS] Scenario falló -> dejo el navegador abierto para debug.");
            return;
        }

        // Si pasó o no querés debug, cerrá normal
        DriverFactory.quitDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
