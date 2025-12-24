package com.comunidadfeliz.qa.steps;

import com.comunidadfeliz.qa.core.Config;
import com.comunidadfeliz.qa.core.Hooks;
import com.comunidadfeliz.qa.pages.SettingsPage;
import io.cucumber.java.en.When;

public class SettingsSteps {

    @When("voy a Configurar comunidad -> Configuración -> Egresos e ingresos")
    public void voyAConfiguracionEgresos() {
        String baseUrl = Config.get("BASE_URL", "https://app.comunidadfeliz.com");
        String slug = Config.get("COMMUNITY_SLUG", "prueba-molina-chile");

        SettingsPage settings = new SettingsPage(Hooks.getDriver());
        settings.openEditCommunity(baseUrl, slug);
        settings.irAConfiguracion();
        settings.abrirAcordeonEgresosIngresos();
    }

    // MATCH EXACTO con tu feature:
    // And configuro "Mostrar ..." como "Desactivado" y guardo
    @When("configuro {string} como {string} y guardo")
    public void configuroComoYGuardo(String labelIgnorado, String estado) {
        SettingsPage settings = new SettingsPage(Hooks.getDriver());
        settings.setMostrarFechaFacturacion(estado);
        settings.guardarYEsperarConfirmacion();
    }
}
