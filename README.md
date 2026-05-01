# Plataformas Especiales (Backend)

Esta aplicación backend implementa dos microservicios para procesar datos de transacciones.  
El primer microservicio valida y procesa la información, y la envía al segundo microservicio.  
El segundo microservicio almacena la transacción en una base de datos en memoria H2.

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal. Para este proyecto se utilizó `JDK 17`.
- **Spring Boot**: Framework para construir aplicaciones Java. En este proyecto se usa la versión `3.4.0`.
  - **Hibernate / JPA**: Para la gestión de la base de datos relacional.
  - **Jakarta Validation**: Para la validación de datos de entrada.
  - **OpenFeign**: Dependencia utilizada para la comunicación entre microservicios.
  - **Spring Actuator**: Proporciona endpoints para monitoreo y métricas de la aplicación.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Swagger / OpenAPI**: Herramienta para documentar y probar los endpoints de la API de forma interactiva.
- **H2**: Gestor de base de datos en memoria para almacenar la información de las transacciones.
- **Postman**: Para simular peticiones HTTP y probar los endpoints.
- **Docker**: permite ejecutar esta aplicación en un entorno aislado, sin necesidad de configurar manualmente dependencias o versiones.

## Características

### Endpoints

Rutas organizadas para interactuar con las transacciones. Operaciones soportadas:

- **Transaction**:
  - Obtener la lista de todas las transacciones.
  - Obtener la información de una transacción específica por su valor de referencia.
  - Crear una nueva transacción.
  - Cambiar el estatus de una transacción a `cancel`.

### Gestor de base de datos

- Integración con H2 para la manipulación de datos.
- La base de datos SQL cuenta con una tabla que gestiona la información de las transacciones.

### Validaciones

- `Transaction`:
  - No se permite que el atributo **operacion** se reciba vacío, con solo espacios en blanco o con caracteres no alfabéticos. Debe contener únicamente letras.
  - No se permite que el atributo **importe** se reciba vacío o con un formato inválido. Debe tener un formato válido de tipo moneda (por ejemplo, `100.00`, `650.00`, etc.).
  - No se permite que el atributo **cliente** se reciba vacío o con solo espacios en blanco. Debe contener únicamente letras. La longitud es definida a criterio del desarrollador.
  - El atributo **secreto** se espera cifrado en formato AES de 256 bits. Se descifra al llegar al backend para ser procesado y almacenado en texto plano.

### Patrones de diseño

- Se emplea el patrón de diseño arquitectónico **MVC** (Modelo-Vista-Controlador), para separar el código en diferentes capas.
- Se emplean **DTOs** para separar los datos expuestos en las solicitudes y respuestas del modelo de dominio, mejorando la organización y el control de la información intercambiada.

### Docker

Este proyecto utiliza Docker para crear un entorno de ejecución aislado y reproducible, asegurando que la aplicación funcione igual en cualquier sistema.

Archivos relevantes:

- `Dockerfile`: define la imagen base y cómo se construye el entorno del proyecto.
- `docker-compose.yml`: orquesta los servicios (API y base de datos) para facilitar la ejecución local.
- `.env`: contiene variables de entorno usadas por Docker (no se incluye en el repositorio por seguridad).

## Estructura del proyecto

### Código fuente de la aplicación

- `controllers/`: Contiene las clases que manejan las solicitudes HTTP y definen los endpoints de la API.
- `services/`: Contiene las clases que implementan la lógica de negocio.
- `repositories/`: Contiene las interfaces que extienden de JPARepository para el manejo de datos.
- `entities/`: Contiene las clases que se mapean con sus respectivas tablas en la base de datos.
- `utils/`: Contiene clases con métodos utilitarios reutilizables en la aplicación.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Plataformas Especiales (Backend)](url).

# Futuras mejoras

- Desarrollar las test en el microservicio processing data.
- Implmentarle time limiter + retry + circuit breaker
- Despliegue de la aplicacion en AWS.
- Despligue automatico usando jenkins.

----

# Special Platforms (Backend)

This backend application implements two microservices to process transaction data.  
The first microservice validates and processes the information, then sends it to the second microservice.  
The second microservice stores the transaction in an in-memory H2 database.

## Technologies Used

- **Java**: Main programming language. This project uses `JDK 17`.
- **Spring Boot**: Framework for building Java applications. Version `3.4.0` is used in this project.
  - **Hibernate / JPA**: For relational database management.
  - **Jakarta Validation**: For input data validation.
  - **OpenFeign**: Dependency used for communication between microservices.
  - **Spring Actuator**: Provides endpoints for application monitoring and metrics.
- **Maven**: For dependency management and project build.
- **Swagger / OpenAPI**: Tool used to document and interactively test the API endpoints.
- **H2**: In-memory database manager to store transaction information.
- **Postman**: Used to simulate HTTP requests and test endpoints.
- **Docker**: Allows running the application in an isolated environment without manually configuring dependencies or versions.

## Features

### Endpoints

Organized routes to interact with transactions. Supported operations:

- **Transaction**:
  - Retrieve the list of all transactions.
  - Retrieve information for a specific transaction by its reference value.
  - Create a new transaction.
  - Change the status of a transaction to `cancel`.

### Database Manager

- Integration with H2 for data manipulation.
- The SQL database includes a table that manages transaction information.

### Validations

- `Transaction`:
  - The **operation** attribute must not be empty, contain only whitespace, or include non-alphabetic characters. It must contain letters only.
  - The **amount** attribute must not be empty or have an invalid format. It must follow a valid currency format (for example, `100.00`, `650.00`, etc.).
  - The **client** attribute must not be empty or contain only whitespace. It must contain letters only. The length is defined at the developer’s discretion.
  - The **secret** attribute is expected to be encrypted using AES 256-bit format. It is decrypted upon reaching the backend to be processed and stored in plain text.

### Design Patterns

- The **MVC** (Model-View-Controller) architectural design pattern is used to separate the code into different layers.
- **DTOs** are used to separate the data exposed in requests and responses from the domain model, improving organization and control of the exchanged information.

### Docker

This project uses Docker to create an isolated and reproducible runtime environment, ensuring the application behaves the same across different systems.

Relevant files:

- `Dockerfile`: Defines the base image and how the project environment is built.
- `docker-compose.yml`: Orchestrates the services (API and database) to facilitate local execution.
- `.env`: Contains environment variables used by Docker (not included in the repository for security reasons).

## Project Structure

### Application Source Code

- `controllers/`: Contains classes that handle HTTP requests and define the API endpoints.
- `services/`: Contains classes that implement business logic.
- `repositories/`: Contains interfaces that extend `JpaRepository` for data access.
- `entities/`: Contains classes mapped to their corresponding database tables.
- `utils/`: Contains reusable utility classes and methods used throughout the application.

## Demo

You can see a demo of the project at the following link:  
[Special Platforms (Backend)](url).

# Future Improvements

- Develop tests for the **processing data** microservice.
- Deploy the application on AWS.
- Implement automated deployment using Jenkins.
