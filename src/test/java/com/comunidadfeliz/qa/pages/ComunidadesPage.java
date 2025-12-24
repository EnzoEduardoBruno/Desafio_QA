package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComunidadesPage {
    private final WebDriver driver;
    private final Waits waits;

    public ComunidadesPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/comunidades");
        // espera a que cargue algo típico del listado
        waits.visible(By.cssSelector("body"));
    }

    // Click en la tarjeta/link de la comunidad por nombre visible
    private By communityLinkByName(String name) {
        // Busca un <a> que tenga dentro algún elemento con el texto del nombre
        return By.xpath(
            "//a[contains(@href,'/comunidades/') and " +
            "(.//*[normalize-space()='" + name + "'] or contains(normalize-space(.),'" + name + "'))]"
        );
    }

    public void enterCommunityFromList(String name) {
        By link = communityLinkByName(name);

        // Click robusto
        waits.safeClick(link);

        // Opcional: aseguramos que salimos de /comunidades
        // (si querés, podés esperar algo del panel después)
        waits.visible(By.cssSelector("body"));
    }
}
