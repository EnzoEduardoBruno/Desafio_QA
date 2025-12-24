package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NuevoEgresoPage {

    private final WebDriver driver;
    private final Waits waits;

    public NuevoEgresoPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s
                .replace("á","a").replace("é","e").replace("í","i").replace("ó","o").replace("ú","u")
                .replace("Á","A").replace("É","E").replace("Í","I").replace("Ó","O").replace("Ú","U")
                .trim();
    }

    private By nuevoEgresoHeading() {
        return By.xpath("//*[self::h1 or self::h2][contains(translate(normalize-space(.),'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'Nuevo egreso')]");
    }

    public boolean isLabelVisible(String label) {
        String target = normalize(label);

        // Asegurar que estás en la pantalla "Nuevo egreso"
        waits.visible(nuevoEgresoHeading());

        // Scope: buscamos SOLO dentro del contenido principal/form, evitando sidebar/otros textos.
        // Tomamos el contenedor grande del contenido.
        By content = By.cssSelector("div#content, main, .container, .container-fluid");

        // Dentro del contenedor, buscamos label/placeholder/aria-label que contengan el texto.
        By scoped = By.xpath(
                "(.//*[self::label or self::span or self::div or self::p]" +
                        "[contains(translate(normalize-space(.),'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'" + target + "')]" +
                        " | .//*[@placeholder or @aria-label]" +
                        "[contains(translate(normalize-space(@placeholder),'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'" + target + "')" +
                        " or contains(translate(normalize-space(@aria-label),'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'" + target + "')])"
        );

        WebElement container = waits.present(content);
        List<WebElement> matches = container.findElements(scoped);

        // Visible REAL: alguno debe estar displayed (no solo existir en DOM)
        for (WebElement el : matches) {
            try {
                if (el.isDisplayed()) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }
}
