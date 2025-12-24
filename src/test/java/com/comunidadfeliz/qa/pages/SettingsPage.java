package com.comunidadfeliz.qa.pages;

import com.comunidadfeliz.qa.core.Waits;
import org.openqa.selenium.*;

public class SettingsPage {

    private final WebDriver driver;
    private final Waits waits;

    // Menú izquierdo dentro de /comunidades/{slug}/editar
    // OJO: "Configuración" (NO "Configuración de correo")
    private final By tabConfiguracion = By.xpath("//a[normalize-space()='Configuración']");

    // Acordeón EXACTO "Egresos e ingresos" según DevTools:
    // div#heading_service_billings_conf.panel-heading  href="#collapse_service_billings_conf"
    private final By acordeonEgresosIngresosHeader =
            By.cssSelector("#heading_service_billings_conf.panel-heading");

    // Contenedor (fila) del setting por label (texto exacto del label)
    private final By rowMostrarFechaFacturacion =
            By.xpath("//label[contains(normalize-space(), 'Mostrar el campo fecha de facturación en los egresos')]" +
                     "/ancestor::div[contains(@class,'form-group')][1]");

    // Botón del selector (cf-selector) dentro de esa fila
    private final By btnSelectorMostrarFechaFacturacion =
            By.xpath("//label[contains(normalize-space(), 'Mostrar el campo fecha de facturación en los egresos')]" +
                     "/ancestor::div[contains(@class,'form-group')][1]" +
                     "//button[contains(@id,'cf-selector-button') or @id='cf-selector-button']");

    // Menú del selector (dropdown) por id real
    private final By dropdownMenu = By.cssSelector("#cf-selector-dropdown-menu");

    // Opción dentro del dropdown (Activado / Desactivado)
    private By opcionEstado(String estado) {
        return By.xpath("//*[@id='cf-selector-dropdown-menu']//*[normalize-space()='" + estado + "']");
    }

    // Guardar: en HTML es input type=submit value=Guardar (y a veces button)
    private final By btnGuardar =
            By.xpath("//input[@type='submit' and @value='Guardar'] | //button[normalize-space()='Guardar' or contains(.,'Guardar')]");

    // Flash de confirmación: “Configuración actualizada”
    private final By flashConfiguracionActualizada =
            By.xpath("//*[contains(normalize-space(), 'Configuración actualizada')]");

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public void openEditCommunity(String baseUrl, String communitySlug) {
        driver.get(baseUrl + "/comunidades/" + communitySlug + "/editar");
    }

    public void irAConfiguracion() {
        waits.safeClick(tabConfiguracion);
    }

    public void abrirAcordeonEgresosIngresos() {
        waits.scrollIntoView(acordeonEgresosIngresosHeader);

        // Si ya está abierto, aria-expanded="true" en el header
        try {
            WebElement header = waits.present(acordeonEgresosIngresosHeader);
            String expanded = header.getAttribute("aria-expanded");
            if ("true".equalsIgnoreCase(expanded)) return;
        } catch (Exception ignored) {}

        waits.safeClick(acordeonEgresosIngresosHeader);
    }

    public void setMostrarFechaFacturacion(String estado) {
        // estado esperado: "Activado" o "Desactivado"

        waits.scrollIntoView(rowMostrarFechaFacturacion);

        // Abrir selector
        waits.safeClick(btnSelectorMostrarFechaFacturacion);

        // Esperar menú visible
        waits.visible(dropdownMenu);

        // Click opción
        waits.safeClick(opcionEstado(estado));

        // Esperar que el menú desaparezca (evita flakiness)
        waits.invisible(dropdownMenu);
    }

    public void guardarYEsperarConfirmacion() {
        waits.scrollIntoView(btnGuardar);
        waits.safeClick(btnGuardar);

        // Espera el flash “Configuración actualizada”
        waits.visible(flashConfiguracionActualizada);
    }
}
