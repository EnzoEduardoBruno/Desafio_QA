package com.comunidadfeliz.qa.steps;

import com.comunidadfeliz.qa.core.Config;
import com.comunidadfeliz.qa.core.Hooks;
import com.comunidadfeliz.qa.pages.ComunidadesPage;
import io.cucumber.java.en.Given;

public class ComunidadesSteps {

    @Given("ingreso a la comunidad desde la lista {string}")
    public void ingresoComunidadDesdeLista(String nombre) {
        String baseUrl = Config.get("BASE_URL", "https://app.comunidadfeliz.com");
        ComunidadesPage page = new ComunidadesPage(Hooks.getDriver());
        page.open(baseUrl);
        page.enterCommunityFromList(nombre);
    }
}
