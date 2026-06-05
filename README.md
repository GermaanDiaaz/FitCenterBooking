<a name="top"></a>
<div align="center">
  <h1>🏋️‍♂️ FitCenterBooking</h1>
  <p><b>Motor de reservas modular e inteligente para centros deportivos y gimnasios profesionales</b></p>
</div>

[![Java](https://img.shields.io/badge/Java-17%20%2F%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%20-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)

---

## 📋 Índice
- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Arquitectura y Seguridad Avanzada](#-arquitectura-y-seguridad-avanzada)
- [Garantía de Calidad y Pruebas](#-garantía-de-calidad-y-pruebas)
- [Cómo Compilar y Ejecutar](#-cómo-compilar-y-ejecutar)
- [Cuentas de Acceso Preconfiguradas](#-cuentas-de-acceso-preconfiguradas)
- [Licencia](#-licencia)
- [Contacto](#-contacto)
---

## 🚀 Acerca del Proyecto

**FitCenterBooking** es una solución robusta para la gestión del flujo comercial y operativo de complejos deportivos. Diseñado bajo exigentes patrones de diseño de software, el ecosistema web desacopla la lógica de negocio mediante una arquitectura por capas orientada a servicios genéricos (`BaseServiceImpl`), facilitando la mantenibilidad, escalabilidad y una tolerancia a fallos de nivel empresarial.

### Beneficios Arquitectónicos Principales:
* **Estructura Genérica DRY**: Implementación de servicios base parametrizados para la reutilización de operaciones CRUD críticas.
* **Alta Testabilidad**: Desacoplamiento total entre controladores de presentación (Thymeleaf UI) y la capa de acceso a datos (Spring Data JPA).
* **Gestión de Sesión Fluida**: Control milimétrico de carritos virtuales y persistencia transitoria de reservas por usuario.

---

## 🔒 Arquitectura y Seguridad Avanzada

El sistema cuenta con una configuración perimetral restrictiva implementada mediante **Spring Security**, asegurando el principio de menor privilegio en base a roles unívocos (`ROLE_ADMIN`, `ROLE_CLIENTE`).

* **Control Perimetral Obligatorio**: Rutas administrativas bloqueadas de forma nativa a nivel de infraestructura HTTP Request Matchers.
* **Manejo Dinámico de Cache**: Mitigación de fugas de sesión en flujos de login gracias al control explícito de caché mediante `HttpSessionRequestCache`.

---

## 🎓 Garantía de Calidad y Pruebas

Siguiendo una estricta planificación de pruebas funcionales y de seguridad no automatizadas, la aplicación ha superado con éxito el 100% de la batería de pruebas diseñada, certificando su resiliencia operativa.

Todos los **14** casos de prueba planificados han resultado ![Passed].

### Resumen de Cobertura Funcional

| Área Funcional | Casos Ejecutados | Descripción de la Verificación | Estado |
| :--- | :---: | :--- | :---: |
| **Control de Accesos & Login** | 3 | Flujos de autenticación válidos, denegación perimetral a usuarios no autorizados e intentos de registros duplicados. | **PASSED** |
| **Operaciones de Carrito** | 3 | Adición de ítems, borrado atómico y validación de idempotencia en reenvíos (bloqueo del botón "Atrás" del navegador). | **PASSED** |
| **Integridad de Datos (CRUD)** | 4 | Comprobación de bloqueos de eliminación en cascada para usuarios y actividades con reservas activas. | **PASSED** |
| **Navegación y Fronteras** | 4 | Edición segura de entidades por propietarios, control de asignación por administradores y resolución de errores 404. | **PASSED** |

> Para consultar el desglose pormenorizado de pasos y datos de entrada, acuda al documento anexo de **Plan de pruebas.pdf**.

---

## 📝 Cómo Compilar y Ejecutar

### Prerrequisitos
* Java JDK 17 o superior.
* Maven 3.x (o el wrapper `./mvnw` incluido).

Siga estos pasos para clonar, construir e iniciar la suite localmente:

```bash
# 1. Clonar el repositorio oficial
git clone https://github.com/GermaanDiaaz/FitCenterBooking.git

# 2. Abrir el proyecto un tu IDE de confianza

# 3. Esperar a que Maven descargue todas las dependencias necesarias o entrar a maven y pulsar 'Update Project'.

# 4. Ejecutar la clase principal
'FitcenterbookingApplication.java'

# 5. Abrir el navegador web y acceder a la dirección:
 'http://localhost:9000'
```

---

## 👥 Cuentas de Acceso Preconfiguradas

El proyecto incluye un mecanismo de persistencia inicial automatizado (a través de un componente `DataSeed`) que pobla la base de datos en memoria H2 al arrancar el servidor. Esto permite evaluar los flujos de control perimetral y los permisos de la interfaz sin necesidad de registros previos.

Utiliza las siguientes credenciales para realizar el testeo manual de la aplicación:

| Rol de Usuario | Cuenta de Correo (Username) | Contraseña (Password) | Permisos y Alcance Operativo |
| :--- | :--- | :--- | :--- |
| **Administrador** | `admin@admin.com` | `admin` | Acceso completo al panel de control. Gestión integral (CRUD) de actividades, visualización de la lista global de usuarios y reservas y acceso exclusivo al módulo de analíticas y estadísticas. |
| **Cliente / Usuario** | `user@user.com` | `user` | Acceso al catálogo de actividades disponibles, flujo transaccional del carrito de la compra y acceso exclusivo a su panel privado de gestión de reservas propias. |

---

## 📃 Licencia

Este proyecto educativo está distribuido bajo licencia de Código Abierto (Open Source). Libre para su uso, modificación y distribución académica.

---

## 📝 Contacto

Si deseas conocer más detalles sobre el diseño técnico del proyecto o necesitas soporte para la instalación del entorno, no dudes en contactar al equipo de desarrollo:

Soporte Técnico: diaz.cager25@triana.salesianos.edu

Repositorio Principal: 'https://github.com/GermaanDiaaz/FitCenterBookin'

---
