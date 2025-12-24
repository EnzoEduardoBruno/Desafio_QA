# 🧪 QA Automation - ComunidadFeliz Challenge

Proyecto de automatización QA usando:

- Java 21
- Selenium WebDriver
- Cucumber (BDD)
- Gradle
- GitHub Actions CI
- Allure Reports

## 🚀 CI Pipeline
El pipeline se ejecuta en cada push a `main` y:
- Corre los tests automatizados
- Genera reportes Cucumber y Allure
- Publica los resultados como artifacts

Podés ver los runs en:
👉 Actions tab del repositorio.

## 📊 Reportes
Los reportes se generan automáticamente y pueden descargarse desde los artifacts del pipeline.

## 🧪 Casos implementados
- TC001: Validación de campo Fecha de facturación deshabilitado
- TC002: Validación de campo visible con setting habilitado
