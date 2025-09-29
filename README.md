# Proyecto ALPESCAB - Sistema de Transporte

Sistema de gestión de servicios de transporte desarrollado con Spring Boot y Oracle Database.

---

## 📋 Tabla de Contenidos

- [Configuración Inicial](#configuración-inicial)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
- [Ejecución de la Aplicación](#ejecución-de-la-aplicación)
- [Ejecución de Tests](#ejecución-de-tests)
- [Orden de Ejecución de Requisitos Funcionales](#orden-de-ejecución-de-requisitos-funcionales)
- [Limpieza y Re-ejecución](#limpieza-y-re-ejecución)
- [Notas Importantes](#notas-importantes)


## ⚙️ Configuración Inicial

### 1. Clonar o descargar el proyecto

```bash
git clone <url-del-repositorio>
cd Proyecto1-SisTrans-main
```

### 2. Configurar conexión a la base de datos

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@<host>:<puerto>:<sid>
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

**Ejemplo:**
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:PROD
spring.datasource.username=ISIS2304B15202520
spring.datasource.password=tu_password
```

---

## 📁 Estructura del Proyecto

```
Proyecto1-SisTrans-main/
├── docs/
│   ├── tablas.sql    # Script de creación de tablas
│   ├── poblacion.sql            # Script para poblar la base de datos
│   ├── limpiar.sql              # Script para limpiar la base de datos
│   ├── RFC1.sql                 # Consulta RFC1
│   ├── RFC2.sql                 # Consulta RFC2
│   ├── RFC3.sql                 # Consulta RFC3
│   ├── RFC4.sql                 # Consulta RFC4
│   ├── SISTRANS-E_R.xlsx        # Archivo E/R corregido
│   ├── UML_FINAL.drawio.png     # Archivo UML corregido
│   └── InformeP1-Sistrans.pdf   # Documento de Informe del proyecto
├── collections/
│   ├── RF - Proyecto Sistrans.postman_collection.json
│   ├── RFC - Proyecto Sistrans.postman_collection.json
│   └── New Environment.postman_environment.json
├── src/
│   └── main/
│       ├── java/com/sistrans/
│       └── resources/
└── pom.xml
```

---

## 🗄️ Configuración de la Base de Datos

### Paso 1: Crear las tablas

Abre **SQL Developer** y ejecuta el script de creación de tablas:

1. Conecta a tu base de datos Oracle
2. Abre el archivo `docs/tablas.sql`
3. Ejecuta el script completo (F5 o botón "Run Script")

### Paso 2: Poblar la base de datos

Este script crea datos iniciales necesarios para las pruebas:

1. En SQL Developer, abre el archivo `docs/poblacion.sql`
2. Ejecuta el script completo (F5 o botón "Run Script")
3. Espera a que termine la ejecución (puede tardar unos segundos)

**Lo que crea el script de población:**
- 3 ciudades (Bogotá, Medellín, Cali)
- 20 puntos de trayecto (IDs 1-20)
- 100 conductores (cédulas 1001-1100)
- 100 vehículos (placas AUT001-AUT100)
- 200 pasajeros (cédulas 2001-2200)
- 200 tarjetas de crédito
- 200 servicios finalizados (IDs 1-200)
- 3 servicios abiertos para RF9 (IDs 201-203)

---

## 🚀 Ejecución de la Aplicación

1. Abre el proyecto en tu IDE
2. Busca la clase `SistransApplication.java`
3. Ejecuta como aplicación Java

La aplicación se ejecutará en: **http://localhost:8080**

---

## 🧪 Ejecución de Tests

### 1. Importar colección en Postman

1. Abre Postman
2. Click en **Import**
3. Selecciona `collections/RF - Proyecto Sistrans.postman_collection.json` y `collections/RFC - Proyecto Sistrans.postman_collection.json`
4. Importa también `collections/New Environment.postman_environment.json`

### 2. Configurar el entorno

1. En Postman, selecciona el entorno **"New Environment"**
2. Edita la variable `base` con el valor: `http://localhost:8080`
3. Guarda los cambios

### 3. Ejecutar los tests

**⚠️ IMPORTANTE: Debes ejecutar los RF en orden secuencial**

---

## 📝 Orden de Ejecución de Requisitos Funcionales

Ejecuta los tests en este orden exacto:

### RF1 - Registrar Ciudad
- ✅ Registrar Ciudad Nueva (exitoso)
- ✅ Registrar Ciudad - Barranquilla
- ❌ Fallo - Ciudad duplicada (debe fallar)

### RF2 - Registrar Usuario de Servicios
- ✅ Registrar Usuario Servicios - Nuevo (exitoso)
- ✅ Registrar Usuario Servicios - María
- ❌ Fallo - Datos incompletos (debe fallar)
- ❌ Fallo - Cédula duplicada (debe fallar)

### RF3 - Registrar Usuario Conductor
- ✅ Registrar Conductor - Nuevo (exitoso)
- ✅ Registrar Conductor - Ana
- ❌ Fallo - Conductor duplicado (debe fallar)
- ❌ Fallo - Correo duplicado (debe fallar)

### RF4 - Registrar Vehículo
- ✅ Registrar Vehículo Estándar (exitoso)
- ✅ Registrar Vehículo Confort
- ✅ Registrar Vehículo Large
- ✅ Registrar Vehículo - Motocicleta
- ❌ Fallo - Placa duplicada (debe fallar)
- ❌ Fallo - Conductor no existe (debe fallar)

### RF5 - Registrar Disponibilidad
- ✅ Crear Disponibilidad - Transporte Pasajeros (exitoso)
- ✅ Crear Disponibilidad - Tarde
- ✅ Crear Disponibilidad - Entrega Comida
- ✅ Crear Disponibilidad - Transporte Mercancías
- ❌ Fallo - Sin conductor (debe fallar)

### RF6 - Modificar Disponibilidad
- ✅ Modificar Disponibilidad (exitoso)
- ✅ Modificar Disponibilidad - Cambiar día
- ❌ Fallo - Modificación con superposición (debe fallar)

### RF7 - Registrar Punto Geográfico
- ✅ Crear Punto - Recogida (exitoso)
- ✅ Crear Punto - Destino
- ✅ Crear Punto - Con nombre establecimiento
- ✅ Crear Punto - Restaurante

### RF8 - Solicitar Servicio
- ✅ Solicitar Servicio Estándar (exitoso)
- ✅ Solicitar Servicio Confort
- ✅ Solicitar Servicio - Entrega Comida
- ✅ Solicitar con Usuario Poblado
- ❌ Fallo - Usuario no existe (debe fallar)

### RF9 - Registrar Viaje (Finalizar Servicio)
- ✅ Finalizar Servicio (exitoso) - Servicio 201
- ✅ Finalizar Servicio - Viaje Largo - Servicio 202
- ✅ Finalizar Servicio - Viaje Corto - Servicio 203
- ❌ Fallo - Servicio no existe (debe fallar)

### RF10 - Revisión Usuario a Conductor
- ✅ Crear Reseña Pasajero a Conductor (exitoso)
- ✅ Crear Reseña - Calificación Media
- ✅ Crear Reseña - Otro Servicio Poblado
- ❌ Fallo - Calificación inválida (>5) (debe fallar)

### RF11 - Revisión Conductor a Usuario
- ✅ Crear Reseña Conductor a Pasajero (exitoso)
- ✅ Crear Reseña - Pasajero Problemático
- ✅ Crear Reseña - Otro Servicio Poblado
- ❌ Fallo - Servicio no existe (debe fallar)

---

## 🔄 Limpieza y Re-ejecución

### ⚠️ IMPORTANTE: Antes de cada nueva ejecución completa de tests

Para ejecutar los tests desde cero, debes limpiar y repoblar la base de datos:

#### En SQL Developer:

**Paso 1: Limpiar la base de datos**
1. Abre el archivo `docs/limpiar.sql`
2. Ejecuta el script completo (F5 o botón "Run Script")

**Paso 2: Repoblar la base de datos**
1. Abre el archivo `docs/poblacion.sql`
2. Ejecuta el script completo (F5 o botón "Run Script")

**Paso 3: Ejecutar tests en Postman**
- Ahora puedes ejecutar todos los RF desde RF1 hasta RF11

## 📌 Notas Importantes

### Consultas RFC (Requerimientos Funcionales de Consulta)

Además de los RF, el sistema incluye consultas RFC que también se deben probar:

En la carpeta `docs/` encontrarás:

- **RFC1.sql**: Consulta de historial de servicios por conductor
- **RFC2.sql**: Consulta de top 10 conductores mejor calificados
- **RFC3.sql**: Consulta de ganancias por vehículo
- **RFC4.sql**: Consulta de uso del servicio por mes

Ejecuta estos scripts directamente en SQL Developer para ver los resultados.

La colección de Postman también incluye los RFC para probar a través de la API:

1. En Postman, busca la carpeta **"RFC - Proyecto Sistrans"**
2. Encontrarás los siguientes endpoints:
   - **RFC1** - Historial de servicios por conductor
   - **RFC2** - Top conductores mejor calificados
   - **RFC3** - Ganancias por vehículo
   - **RFC4** - Uso del servicio por mes
3. Ejecuta cada RFC después de haber completado todos los RF

**Nota:** Los RFC requieren que hayas ejecutado los RF previamente para tener datos suficientes para las consultas.

---

## 📄 Licencia

Este proyecto es parte del curso de Sistemas Transaccionales - Universidad de los Andes

---

**¡Listo para probar!** 🚀

Ejecuta `limpiar.sql`, luego `poblacion.sql`, inicia la aplicación y prueba los RF en Postman.