package com.comunidadfeliz.qa.steps;

import com.comunidadfeliz.qa.core.Config;
import com.comunidadfeliz.qa.core.Hooks;
import com.comunidadfeliz.qa.pages.LoginPage;
import io.cucumber.java.en.Given;

public class AuthSteps {

    @Given("inicio sesión en Comunidad Feliz")
    public void login() {
        String baseUrl = Config.get("BASE_URL", "https://app.comunidadfeliz.com");
        String user = Config.get("CF_USER", "");
        String pass = Config.get("CF_PASS", "");

        System.out.println("[LOGIN] baseUrl=" + baseUrl);
        System.out.println("[LOGIN] user=" + user + " passLen=" + (pass == null ? "null" : pass.length()));

        if (user == null || user.isBlank() || pass == null || pass.isBlank()) {
            throw new RuntimeException("CF_USER/CF_PASS vacíos. Revisá qa.properties y Config.");
        }

        LoginPage page = new LoginPage(Hooks.getDriver());
        page.open(baseUrl);
        page.loginAndWait(user, pass); // <-- importante: esperar login real
    }
}
