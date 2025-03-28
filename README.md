📌 weatherDelivery 

Este proyecto es una API que consulta el clima utilizando un servicio externo y envía notificaciones en caso de condiciones climáticas que puedan afectar una entrega.

---

## 🚀 Ejecución en Local

### ✅ **Requisitos Previos**
- **Java 17**
- **Maven 3.8+**
- **Docker (Opcional, para base de datos u otros servicios)**
- **Credenciales de API externa (WeatherAPI)**

### 🔹 **Pasos para ejecutar**
1. **Clonar el repositorio**
   ```sh
   git clone https://github.com/tu-usuario/tu-repo.git
   cd tu-repo
   ```

2. **Configurar las variables de entorno**
   - Crea un archivo `application.properties` en `src/main/resources/` con el siguiente contenido:
     ```properties
     weatherapi.key=TU_API_KEY
     spring.mail.host=smtp.example.com
     spring.mail.port=587
     spring.mail.username=tu_correo@example.com
     spring.mail.password=tu_contraseña
     ```

3. **Compilar y ejecutar**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Probar la API**
   - Abre Postman o usa `curl` para probar:
     ```sh
     curl -X POST http://localhost:8080/weather/validate -H "Content-Type: application/json" -d '{"email":"prueba@example.com","latitude":4.6097,"longitude":-74.0817}'
     ```

---

## ☁️ Despliegue en AWS

### ✅ **Servicios Utilizados**
- **AWS ECS (Elastic Container Service)**
- **AWS ECR (Elastic Container Registry)**
- **AWS API Gateway**
- **AWS Cognito (Opcional, para autenticación)**

### 🔹 **Pasos para desplegar**
1. **Construir la imagen Docker**
   ```sh
   docker build -t clima-api .
   ```

2. **Subir la imagen a ECR**
   ```sh
   aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <tu-repo-ecr>
   docker tag clima-api:latest <tu-repo-ecr>:latest
   docker push <tu-repo-ecr>:latest
   ```

3. **Actualizar el servicio en ECS**
   ```sh
   aws ecs update-service --cluster mi-cluster --service clima-api-service --force-new-deployment
   ```

4. **Configurar API Gateway**
   - Crear un API en API Gateway
   - Configurar una integración con el servicio ECS (puede ser detrás de un Load Balancer)
   - Agregar autenticación con Cognito si es necesario

5. **Probar el servicio en AWS**
   ```sh
   curl -X POST https://tu-api.aws.com/weather/validate -H "Content-Type: application/json" -d '{"email":"test@example.com","latitude":4.6097,"longitude":-74.0817}'
   ```

---

## 🛠 **Solución de Problemas**
🔹 **Error de autenticación en AWS:** Asegúrate de que tus credenciales (`~/.aws/credentials`) estén correctamente configuradas.  
🔹 **El servicio no responde en AWS:** Revisa los logs en ECS con:
   ```sh
   aws logs tail /aws/ecs/tu-servicio --follow
   ```
🔹 **Error en la API externa:** Confirma que tu API Key de WeatherAPI es válida y no ha excedido su límite.

---

## 📌 **Notas Finales**
- El proyecto sigue una **arquitectura hexagonal**, separando bien las capas de dominio, infraestructura y aplicación.
- Se pueden agregar nuevas integraciones con otros servicios meteorológicos fácilmente.
- Cualquier mejora o corrección es bienvenida. 🚀
