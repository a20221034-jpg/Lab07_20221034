# Laboratorio 7 – Proyecto base (TEL131)

Proyecto base del Séptimo Laboratorio de Ingeniería Web para Telecomunicaciones.
Complete los `TODO` según el enunciado. **No cambie los nombres de paquetes, clases ni métodos.**

## Requisitos

- JDK 17 o superior
- Tomcat 10.1 o superior (Jakarta Servlet 6)
- MySQL 8

## Pasos iniciales

1. Ejecute `db/telecom_andina.sql` en MySQL.
2. En `BaseDao.java`, cambie la contraseña (`PASS`) por la de su MySQL.
3. Abra el proyecto en su IDE como proyecto Maven y despliéguelo en Tomcat.
4. Ingrese a `http://localhost:8080/lab7-iweb/equipos`.

El archivo `.gitignore` **no** está incluido: créelo usted (Ejercicio 1).

## Estructura

```
db/telecom_andina.sql                 script de la base de datos
src/main/java/pe/edu/pucp/iweb/lab7/
  beans/   Sede (completa), EquipoRed, EquipoSwitch, EquipoAP
  dao/     BaseDao, SedeDao, EquipoDao
  servlets/EquipoServlet               /equipos  (listar y pdf)
src/main/webapp/
  equipos.jsp                          vista con combobox y tabla
  css/estilos.css                      estilos
  index.jsp                            redirige a /equipos
```

| TODO | Ejercicio |
|------|-----------|
| 2a   | BaseDao abstracta y herencia de los DAO |
| 2b   | EquipoRed abstracta y subclases |
| 2c   | Consultas de SedeDao y EquipoDao |
| 3    | Combobox, listado y CSS |
| 4    | Reporte PDF |
