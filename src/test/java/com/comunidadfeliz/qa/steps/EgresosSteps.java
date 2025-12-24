package com.comunidadfeliz.qa.steps;

import com.comunidadfeliz.qa.core.Hooks;
import com.comunidadfeliz.qa.pages.EgresosPage;
import com.comunidadfeliz.qa.pages.NuevoEgresoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class EgresosSteps {

    @When("voy a Cobranza y recaudación -> Egresos -> Nuevo Egreso")
    public void voyACobranzaEgresosNuevoEgreso() {
        EgresosPage egresos = new EgresosPage(Hooks.getDriver());
        egresos.irPorMenuAEgresos();
        egresos.clickNuevoEgreso();
    }

    @Then("debería visualizar el campo {string}")
    public void deberiaVerCampo(String label) {
        NuevoEgresoPage nuevo = new NuevoEgresoPage(Hooks.getDriver());
        assertTrue(nuevo.isLabelVisible(label), "Se esperaba ver el campo: " + label);
    }

    @Then("no debería visualizar el campo {string}")
    public void noDeberiaVerCampo(String label) {
        NuevoEgresoPage nuevo = new NuevoEgresoPage(Hooks.getDriver());
        assertFalse(nuevo.isLabelVisible(label), "NO se esperaba ver el campo: " + label);
    }
}
