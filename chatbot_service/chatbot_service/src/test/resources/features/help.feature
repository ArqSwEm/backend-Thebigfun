Feature: Contacto con soporte técnico

  Como visitante,
  deseo poder contactarme con soporte técnico
  para recibir ayuda ante alguna duda de un evento.

  Scenario: Visitante tiene dudas sobre un evento en nuestra app

    Given un visitante se encuentra en la página web
    When tiene dudas de algún evento
    Then el visitante podrá utilizar un chatbot que le ayudará a proporcionarle la información deseada
    And el chatbot enviará la solicitud al endpoint "http://localhost:8080/api/chatbot/send"
    And el visitante recibirá una respuesta del chatbot

      | inputQuery                              | expectedResponse                                 |
      | "Tengo una duda"                        | "¿Cual es tu consulta?"                          |
      | "Necesito informacion sobre Ejemplo2"   | "N.°:2
      |                                         |  Título: Ejemplo2"
      |                                         |  Descripción: Descripción del evento de ejemplo
      |                                         |  Día inicio: 2024-07-01 11:00:00
      |                                         |  Día fin: 2024-07-01 13:00:00f
      |                                         |  Dirección: Lugar del evento
      |                                         |  Estado: activo" |
