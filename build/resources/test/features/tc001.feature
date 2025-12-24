@tc001
Feature: TC001 - No visualizar Fecha de facturación con setting deshabilitado

  Background:
    Given inicio sesión en Comunidad Feliz
    And ingreso a la comunidad desde la lista "Comunidad prueba QA Delta"

  Scenario: TC001_NoVisualizarCampo_FechaFacturacion_SettingDeshabilitado
    When voy a Configurar comunidad -> Configuración -> Egresos e ingresos
    And configuro "Mostrar el campo fecha de facturación en los egresos" como "Desactivado" y guardo
    And voy a Cobranza y recaudación -> Egresos -> Nuevo Egreso
    Then no debería visualizar el campo "Fecha de facturación"
