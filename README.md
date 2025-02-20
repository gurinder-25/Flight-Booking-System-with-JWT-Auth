# Flight Management System with JWT Authentication

## Overview
This Flight Management System provides user authentication using JWT (JSON Web Token) and allows users to book flights securely.

## API Routes

### User Authentication

#### **Register a User**
- **Endpoint:** `POST /register`
- **Request Body:**
  ```json
  {
      "id": 1,
      "username": "john",
      "password": "j@123"
  }
  ```
- **Response:**
  ```json
  {
      "id": 1,
      "username": "john",
      "password": "$2a$12$z2a/1pyL0EI2asgkPxBktOCZWmUihZMVi.w8Qwf9W3O2psw0t1is."
  }
  ```
  *(The password is stored in an encrypted format using Bcrypt.)*

#### **Login a User**
- **Endpoint:** `POST /login`
- **Request Body:**
  ```json
  {
      "username": "john",
      "password": "j@123"
  }
  ```
- **Response:**
  ```json
  {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNzQwMDQxODc1LCJleHAiOjE3NDAwNDE5ODN9.IoBB6x3Heodf4sGUwkRSRF76DEzscmn9kC4OT3MXjmU"
  }
  ```
  *(This is a JWT token that must be included in the Authorization header for further requests.)*

---

## **All Flight and Booking APIs Require JWT Authentication**

### Flight Management

#### **Get All Flights**
- **Endpoint:** `GET /flights`
- **Headers:**
  ```json
  {
      "Authorization": "Bearer <your_jwt_token>"
  }
  ```
- **Response:**
  ```json
  [
    {
      "id": 1,
      "origin": "NYC",
      "destination": "SFO",
      "seatAvailable": 150
    },
    {
      "id": 2,
      "origin": "NYC",
      "destination": "LAX",
      "seatAvailable": 200
    }
  ]
  ```

#### **Add a Flight**
- **Endpoint:** `POST /flights`
- **Headers:**
  ```json
  {
      "Authorization": "Bearer <your_jwt_token>"
  }
  ```
- **Request Body:**
  ```json
  {
      "origin": "NYC",
      "destination": "SFO"
  }
  ```
- **Response:**
  ```json
  {
      "id": 1,
      "origin": "NYC",
      "destination": "SFO",
      "seatAvailable": 150
  }
  ```

---

### Flight Booking

#### **Book a Flight**
- **Endpoint:** `POST /booking`
- **Headers:**
  ```json
  {
      "Authorization": "Bearer <your_jwt_token>"
  }
  ```
- **Request Body:**
  ```json
  {
      "username": "john",
      "origin": "NYC",
      "destination": "SFO"
  }
  ```
- **Response:**
  ```json
  {
      "message": "Flight Booked!"
  }
  ```

---

# Dockerized

This project is a Spring Boot application with PostgreSQL, fully containerized using Docker and Docker Compose.

## **Getting Started**


### **1. Dockerizing the Application**

The `Dockerfile` contains instructions to containerize the Spring Boot application:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/security1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### **Explanation:**
1. **Base Image:** Uses `openjdk:17-jdk-slim` for a lightweight Java 17 runtime.
2. **Working Directory:** Sets `/app` as the container's working directory.
3. **Copy Application JAR:** Copies the built JAR file into the container as `app.jar`.
4. **Expose Port:** The application runs on port `8080`.
5. **Entrypoint:** Starts the application with `java -jar app.jar`.

### **2. Dockerizing the Database**

The `docker-compose.yml` file defines a PostgreSQL database service:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: postgres-db11
    restart: always
    environment:
      POSTGRES_DB: flightdemo1
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: abc@123
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

  flight-app:
    build: .
    container_name: flight-app11
    depends_on:
      - postgres
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://host.docker.internal:5432/flightdemo1
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: abc@123

volumes:
  postgres-data:
```

#### **Explanation:**
1. **PostgreSQL Database Service:**
   - Uses the `postgres:15` image.
   - Sets environment variables for database credentials.
   - Exposes port `5432` for external access.
   - Uses a volume (`postgres-data`) to persist database data.

2. **Spring Boot Application Service:**
   - Builds the application from the `Dockerfile`.
   - Depends on the `postgres` service to ensure the database starts first.
   - Exposes port `8080`.
   - Configures database connection using environment variables.



## **Accessing the Application**
Once the containers are up, the application will be available at:

```
http://localhost:8080
```

## **Conclusion**
This setup allows a Spring Boot application to run inside a containerized environment, communicating with a PostgreSQL database service. 🚀
