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
