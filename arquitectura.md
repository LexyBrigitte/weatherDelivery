Arquitectura del Proyecto

1. Introducción

Este documento describe la arquitectura del proyecto, basado en el patrón hexagonal, explica la organización de los componentes y su interacción.

2. Arquitectura Hexagonal

La arquitectura sigue el patrón hexagonal para separar la lógica de negocio de las dependencias externas, promoviendo la mantenibilidad y extensibilidad.

2.1. Capas y Responsabilidades

Domain (Dominio):

Contiene las entidades principales y las interfaces.

Clases relevantes:

IWeatherService

WeatherResponseDto

Application (Casos de uso):

Contiene la lógica de negocio.

Clase relevante:

ValidateWeatherCase

EntryPoints (Puntos de entrada):

Controladores REST que exponen la API.

Clase relevante:

WeatherController

Infrastructure (Infraestructura externa):

Comunicación con servicios externos y componentes auxiliares.

Clases relevantes:

WeatherApiExternal (Consumo de API externa del clima)

WeatherServiceImp (Implementación del servicio de clima)

EmailService (Envío de notificaciones)

3. Flujos Principales

3.1. Consulta de Clima

El usuario hace una petición a WeatherController.

Se delega la consulta a ValidateWeatherCase, que aplica la lógica de negocio.

Se obtiene la información desde WeatherApiExternal.

Se devuelve la respuesta estructurada al usuario.

3.2. Envío de Notificaciones

ValidateWeatherCase determina si es necesario enviar una notificación.

Si aplica, EmailService se encarga del envío de correo.

4. Configuración y Dependencias

Spring Boot: Marco de trabajo principal.

WebClient: Para llamadas a la API de clima.

Spring Mail: Para el envío de correos.

Mockito y JUnit: Para pruebas.

Maven: Para la gestión de dependencias.

5. Consideraciones para Producción

Manejo adecuado de credenciales en application.properties.

Logs detallados para monitoreo.

Implementación de mecanismos de reintento en caso de fallos en API externa.