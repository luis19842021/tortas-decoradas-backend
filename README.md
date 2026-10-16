# Colores Tortas Decoradas · Backend

API REST del e-commerce de [Colores Tortas Decoradas](https://colorestortasdecoradas.com.ar), una pastelería de Almagro (CABA). El sitio está en producción y lo usa el negocio para recibir y cobrar pedidos.

Frontend: [tortas-decoradas-frontend](../tortas-decoradas-frontend) (Next.js + TypeScript).

## Qué hace

- Registro e inicio de sesión con JWT, verificación de email y recuperación de cuenta.
- Catálogo de productos con personalización (bizcochuelo, rellenos, pisos) y precio calculado según lo elegido.
- Carrito, pedidos con agenda de entrega, cupo máximo por día y días bloqueados.
- Cálculo de costo de envío por dirección (USIG del Gobierno de la Ciudad, con Mapbox como respaldo).
- Pagos con Mercado Pago Checkout Pro y confirmación por webhook; los pedidos sin pagar se cancelan solos.
- Notificaciones por email (Brevo) en cada cambio de estado del pedido.
- Panel de administración: productos, precios, carrusel, roles, facturas y notas de crédito por pedido.
- Imágenes alojadas en Cloudinary.

## Stack

| | |
| --- | --- |
| Lenguaje | Java 25 |
| Framework | Spring Boot 3.5 (Web, Data JPA, Security, Mail) |
| Base de datos | MySQL 8 |
| Seguridad | Spring Security + JWT (jjwt) |
| Integraciones | Mercado Pago, Cloudinary, Brevo (SMTP), USIG, Mapbox |
| Despliegue | Docker en servidor Linux (Ubuntu), DNS en Cloudflare |

## Arquitectura

Arquitectura hexagonal (puertos y adaptadores), organizada por módulo de negocio. Cada módulo separa tres capas:

```
pedido/
├── dominio/          Modelos y reglas del negocio, sin dependencias de Spring
│   ├── modelo/
│   └── puerto/
│       ├── in/       Casos de uso que ofrece el módulo
│       └── out/      Lo que el dominio necesita del exterior (repositorios, pasarela de pago, email)
├── aplicacion/       Implementación de los casos de uso
└── infraestructura/  Adaptadores: controladores REST, entidades JPA, clientes externos, configuración
```

Módulos: `Auth`, `usuario`, `rol`, `producto`, `carrusel`, `imagen`, `pedido`, `pago`, `notificacion`, `factura`, `notacredito` y `seguridad`.

Ejemplo: el dominio de pagos solo conoce el puerto `PuertoPasarelaPago`; `MercadoPagoAdaptador` lo implementa en infraestructura. Cambiar de pasarela no toca la lógica del pedido.

## Correr en local

Requisitos: JDK 25 y MySQL 8 (o Docker).

1. Crear la base `colores` en MySQL.
2. Copiar `.env.example` como `.env` y completar los valores, o bien copiar `src/main/resources/secretos.properties.ejemplo` como `secretos.properties`. Ninguno de los dos se sube al repositorio.
3. Levantar la API:

```bash
./mvnw spring-boot:run
```

Queda en `http://localhost:8080`.

## Con Docker

```bash
docker build -t tortas-backend .
docker run -p 8080:8080 --env-file .env tortas-backend
```

## Variables de entorno

| Variable | Uso |
| --- | --- |
| `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` | Base de datos |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` en el primer arranque, después `validate` |
| `JWT_SECRET` | Clave Base64 de al menos 32 bytes |
| `MAIL_HOST`, `MAIL_PORT`, `MAIL_SSL`, `MAIL_STARTTLS`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `MAIL_FROM` | Servidor SMTP (Brevo en producción) |
| `CLOUDINARY_CLOUD_NAME`, `CLOUDINARY_API_KEY`, `CLOUDINARY_API_SECRET` | Imágenes |
| `MP_ACCESS_TOKEN` | Access Token de Mercado Pago (`TEST-...` para pruebas) |
| `MP_NOTIFICATION_URL` | `https://dominio/api/pagos/webhook` |
| `APP_FRONTEND_URL` | URL pública del frontend, sin barra final |
| `APP_CORS_ORIGENES` | Orígenes permitidos, separados por coma |
| `ADMIN_BOOTSTRAP_EMAIL` | Email que recibe el rol ADMIN si todavía no hay ninguno |
| `MAPBOX_API_KEY` | Geocodificación de respaldo (opcional) |
| `PEDIDOS_MINUTOS_PARA_PAGAR` | Minutos para pagar antes de cancelar el pedido (20 por defecto) |
| `PORT` | Puerto HTTP (8080 por defecto) |

## Autor

Luis Alberto Cortes · [GitHub](https://github.com/luis19842021)
