# 🏨 Hotel Management Backend (Spring Boot Microservices)

This project is a **Hotel Management Backend System** built using **Spring Boot Microservices Architecture**.
It demonstrates how different services interact with each other in a distributed system using **Eureka Service Registry, API Gateway, and Feign Client**.

## 🚀 Features

* **User Service** → Manages user details and their interactions with hotels.
* **Hotel Service** → Handles hotel information (name, location, details, etc.).
* **Rating Service** → Stores and manages ratings given by users to hotels.

## 🔧 Tech Stack

* **Java** (Spring Boot)
* **Spring Cloud** (Microservices)
* **Eureka Server** (Service Registry)
* **Eureka Client** (Service Discovery)
* **Spring Cloud Gateway** (API Gateway for routing)
* **OpenFeign Client** (Inter-service communication)
* **REST APIs**

## 📌 Flow

1. Each microservice (**User**, **Hotel**, **Rating**) runs independently.
2. All services are registered with the **Eureka Server** (Service Registry).
3. The **API Gateway** routes incoming client requests to the appropriate service.
4. **Feign Client** is used for inter-service communication (e.g., User Service calling Rating/Hotel Service).

## 📂 Modules

* `user-service`
* `hotel-service`
* `rating-service`
* `service-registry` (Eureka Server)
* `api-gateway`

## ⚡ How It Works

* A **User** can view hotel details and ratings.
* A **Hotel** can be rated by multiple users.
* **Ratings** are fetched by combining data from multiple services via **Feign Clients** and exposed through the gateway.

---
## 🏛️ Architecture diagram

<img width="1024" height="1024" alt="hotel-management=architecture" src="https://github.com/user-attachments/assets/0f71766b-84e4-4a53-9305-3fe577c55b4c" />
