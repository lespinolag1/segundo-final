# Proyecto de Gestión de Estacionamiento - Spring Boot

API REST para gestionar estacionamientos con operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

## Tecnologías
- Java 21
- Spring Boot
- PostgreSQL
- Maven

## Instalación

### 1. Clonar el repositorio

Clona el repositorio y accede a la carpeta del proyecto:

bash
https://github.com/lespinolag1/segundo-final
cd segundo-final


2. Crear la base de datos en PostgreSQL

Accede a tu servidor PostgreSQL y crea la base de datos estacionamiento_db:

    Inicia sesión en PostgreSQL desde la terminal:
    
    Crea la base de datos estacionamiento_db: CREATE DATABASE estacionamiento_db;

3. Configuración de PostgreSQL en application.properties

Configura la conexión a PostgreSQL en el archivo src/main/resources/application.properties con los siguientes detalles:

