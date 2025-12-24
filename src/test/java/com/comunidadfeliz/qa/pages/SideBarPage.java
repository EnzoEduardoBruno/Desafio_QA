package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.*;

public class SideBarPage {

    private final Waits waits;

    private final By menuCobranzaRecaudacion =
            By.xpath("//a[contains(.,'Cobranza y recaudación')]");

    private final By menuEgresos =
            By.xpath("//a[contains(.,'Egresos')]");

    public SideBarPage(WebDriver driver) {
        this.waits = new Waits(driver);
    }

    public void irACobranzaYRecaudacion() {
        waits.safeClick(menuCobranzaRecaudacion);
    }

    public void irAEgresos() {
        waits.safeClick(menuEgresos);
    }
}
