@tc002
Feature: TC002 - Visualizar Fecha de facturación con setting habilitado

  Background:
    Given inicio sesión en Comunidad Feliz
    And ingreso a la comunidad desde la lista "Comunidad prueba QA Delta"

  Scenario: TC002_VisualizarCampo_FechaFacturacion_SettingHabilitado
    When voy a Configurar comunidad -> Configuración -> Egresos e ingresos
    And configuro "Mostrar el campo fecha de facturación en los egresos" como "Activado" y guardo
    And voy a Cobranza y recaudación -> Egresos -> Nuevo Egreso
    Then debería visualizar el campo "Fecha de facturación"
