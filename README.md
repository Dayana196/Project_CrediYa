# Sistema de Cobros de Cartera – CrediYa 💵

Sistema de consola desarrollado en Java para la gestion de empleados, clientes, prestamos y pagos de la empresa CrediYa S.A.S.
El proyecto permite registrar, consultar y persistir informacion usando archivos de texto y esta preparado para conexion a MySQL mediante JDBC.

---

## Funcionalidades

### Modulo de Empleados
- Registro de empleados
- Consulta y listado de empleados
- Persistencia en archivo empleados.txt
- Preparado para base de datos

### Modulo de Clientes
- Registro de clientes
- Listado de clientes
- Persistencia en archivo clientes.txt

### Modulo de Prestamos
- Creacion de prestamos asociando cliente y empleado
- Calculo automatico de interes y cuota
- Cambio de estado del prestamo
- Persistencia en archivo prestamos.txt

### Modulo de Pagos
- Registro de pagos
- Actualizacion del saldo pendiente
- Historial de pagos
- Persistencia en archivo pagos.txt

### Modulo de Reportes
- Prestamos activos
- Prestamos pagados
- Clientes con prestamos pendientes
- Uso de Stream API y lambda

---

## Persistencia

Los datos se almacenan en archivos de texto dentro de la carpeta data.
El sistema funciona aun cuando no hay conexion a MySQL.

---

## Ejecucion

Ejecutar la clase:

com.dayana.view.Main

El menu principal se mostrara en consola.

---

## Tecnologias

- Java
- POO
- Archivos
- Collections
- Stream API
- JDBC
- Maven

---

## Autor

Proyecto academico
Desarrollado por Dayana
