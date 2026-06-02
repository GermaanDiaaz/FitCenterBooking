# FitCenterBooking

FitCenterBooking es una aplicación web desarrollada en Java con Spring Boot diseñada para gestionar las reservas de actividades en un centro deportivo o gimnasio. 

Permite a los clientes registrarse, consultar las actividades disponibles y reservar sus plazas mediante un sistema de carrito, mientras que los administradores tienen control total sobre el catálogo, los usuarios y sus reservas.

---

## Tecnologías Utilizadas

* **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA.
* **Frontend:** HTML5, Thymeleaf, CSS3, Bootstrap 5.
* **Base de Datos:** H2.

---

## Funcionalidades Principales

* **Gestión de Usuarios:** Sistema de registro y login con roles de seguridad (Administrador y Cliente).
* **Catálogo de Actividades:** Visualización de las clases disponibles con sus horarios, precios y aforo máximo.
* **Sistema de Reservas:** Carrito de la compra para añadir actividades y confirmar la reserva de plazas de forma segura.
* **Panel de Cliente:** Historial de reservas donde el usuario puede consultar o editar sus reservas.
* **Panel de Administrador:** Gestión completa (CRUD) de actividades, usuarios, reservas y control de aforos.
* **Control de Errores:** Página personalizada para errores y protección contra accesos no autorizados.

---

## Cómo ejecutar el proyecto

1. Clona este repositorio en tu equipo local.
2. Abre el proyecto en tu IDE.
3. Espera a que Maven descargue todas las dependencias necesarias.
4. Ejecuta la clase principal `FitcenterbookingApplication.java`.
5. Abre tu navegador web y accede a la dirección: `http://localhost:9000`.

---

## Usuarios de prueba

Para acceder rápidamente al sistema, puedes utilizar los siguientes usuarios:

* **Administrador:** `admin@admin.com` / Contraseña: `admin`
* **Cliente:** `user@user.com` / Contraseña: `user`
