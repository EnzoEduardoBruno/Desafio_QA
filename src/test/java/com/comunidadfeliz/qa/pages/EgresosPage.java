package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EgresosPage {

    private final WebDriver driver;
    private final Waits waits;

    // Sidebar nuevo (sin hover puede estar "hidden-xs" y no expandido)
    private final By sidebarNav = By.cssSelector("nav#admin-sidebar.new-sidebar");

    // Toggle del módulo "Cobranza y recaudación" (click abre el acordeón)
    // En tu HTML: div.navlink-dropdown -> div.item-name "Cobranza y recaudación"
    private final By toggleCobranza = By.xpath(
            "//div[contains(@class,'navlink-dropdown')]//div[contains(@class,'item-name') and normalize-space()='Cobranza y recaudación']"
    );

    // Link REAL a /egresos (evita tocar la estrella)
    // En tu HTML: <a ... href="/egresos"><div class="item-name">Egresos</div></a>
    private final By linkEgresos = By.xpath(
            "//a[contains(@href,'/egresos') and .//div[contains(@class,'item-name') and normalize-space()='Egresos']]"
    );

    // Botón + Nuevo Egreso (ya dentro de /egresos)
    private final By btnNuevoEgreso = By.xpath("//a[contains(.,'Nuevo Egreso')] | //button[contains(.,'Nuevo Egreso')]");

    public EgresosPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    private void asegurarSidebarExpandidoPorHover() {
        // 1) Esperar sidebar presente
        waits.present(sidebarNav);

        // 2) Hover para que pase a "active" (igual que tu mouse)
        waits.hover(sidebarNav);

        // 3) Esperar que efectivamente quede activo
        waits.attributeContains(sidebarNav, "class", "active");

        // 4) Micro pausa para que termine la animación (evita clicks perdidos)
        waits.smallPause(150);
    }

    public void irPorMenuAEgresos() {
        asegurarSidebarExpandidoPorHover();

        // Abrir/expandir "Cobranza y recaudación"
        waits.safeClick(toggleCobranza);

        // Asegurar que el link real a /egresos esté visible y clickearlo
        waits.visible(linkEgresos);
        waits.safeClick(linkEgresos);

        waits.urlContains("/egresos");
    }

    public void clickNuevoEgreso() {
        waits.visible(btnNuevoEgreso);
        waits.scrollIntoView(btnNuevoEgreso);
        waits.safeClick(btnNuevoEgreso);
        waits.urlContains("/egresos/nuevo");
    }
}
