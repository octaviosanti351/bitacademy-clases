# Bit academy - Proyecto Introductorio

## Proyecto hecho para la bit academy

Temas tratados en este proyecto:
- Uso de springboot
- Unit Testing
- Configuracion de una base de datos

Pasos para levantar el proyecto:

- clonarlo en una carpeta
- sincronizar las librerias y dependencias (ir al archivo build.gradle y darle a sync)
- generar la base de datos (explicado mas abajo)
- correr el proyecto y entrar a localhost:8080/swagger-ui.html 

Para generar la base de datos con dummy data:

```
docker run --name postgres-db -e POSTGRES_PASSWORD=docker -p 5432:5432 -d postgres 
```

Versión de java: 11 en adelante
