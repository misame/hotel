# Hotel Alura

## Descripción

"Hotel Alura" es una aplicación en Java 11 que utiliza Hibernate, JPA y la base de datos H2. Este proyecto es parte del reto de Alura y Oracle y tiene como objetivo registrar huéspedes y reservas.

## Instrucciones de instalación

1. Clone el repositorio de GitHub en su computadora local.
2. Asegúrese de tener Java 11 y Maven instalados en su computadora.
3. Abra el proyecto en su IDE preferido.
4. Configure la conexión de la base de datos en el archivo ".properties".
5. Ejecute la aplicación utilizando el comando `java -jar target/hotel-alura-1.0.jar`.

## Estructura del proyecto

El proyecto sigue la estructura estándar de una aplicación MVC. Los diferentes componentes se organizan en paquetes separados como sigue:

- **Modelo**: El paquete "model" contiene las clases de modelo que representan los objetos del mundo real que se utilizan en la aplicación, como Huésped y Reserva. Las clases del modelo también incluyen anotaciones de JPA para especificar la estructura de la base de datos.

- **Vista**: El paquete "view" contiene las clases que se encargan de la presentación de la información al usuario, utilizando interfaces de usuario gráficas (GUI) o vistas HTML.

- **Controlador**: El paquete "controller" contiene las clases que se encargan de la lógica de negocio de la aplicación, es decir, la implementación de las operaciones de registro y consulta de huéspedes y reservas.

## Uso de la aplicación

La aplicación "Hotel Alura" permite registrar huéspedes y sus reservas. Los usuarios pueden realizar las siguientes acciones:

- Registrar un nuevo huésped.
- Consultar la información de un huésped existente.
- Registrar una nueva reserva para un huésped existente.
- Consultar las reservas de un huésped existente.

