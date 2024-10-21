# Microservices Architecture Documentation

## Architecture Overview

This project is built on a microservices architecture consisting of the following services:

1. **Config Server**
   - Manages configuration for all microservices.
   - All services connect to this server to download their configurations.

2. **Discovery Service**
   - Utilizes Eureka for service discovery.
   - All services connect to it to know which instances are currently up.

3. **API Gateway**
   - Acts as a single entry point for all clients.
   - Routes requests to the appropriate microservices based on their IP addresses.

4. **Customer Service**
   - Contains all customer information using a non-relational database.
   - Stores customer data in a collection that contains another collection.

5. **Notification Service**
   - Uses a Kafka topic to send notifications asynchronously.
   - Sends an email notification once an order is placed and payment is confirmed.
   - Stores notification details in a MongoDB database.

6. **Order Service**
   - Consumes services like Product, Customer, and Payment services.
   - Makes a request to the Notification Service via Kafka to send email notifications.
   - Stores order records in a PostgreSQL database.

7. **Payment Service**
   - Handles the payment process for orders.

8. **Product Service**
   - Contains all product data and their respective categories.

9. **Zipkin**
   - Used for tracing tasks performed by each service.

## Technology Stack

### Backend
- **Spring Boot**
- **Zipkin**
- **Docker Compose**
- **Kafka**
- **MongoDB**
- **PostgreSQL**

### Frontend
- **React**
- **Bootstrap**
- **SweetAlert**

## Architecture Diagram

```plaintext
+----------------+       +-------------------+
|  Config Server |<----->|  Discovery Service |
+----------------+       +-------------------+
                              |
                              |
                              v
                     +-------------------+
                     |     API Gateway    |
                     +-------------------+
                              |
        +---------------------+---------------------+
        |                     |                     |
        v                     v                     v
+----------------+   +----------------+   +----------------+
| Customer Service|   | Notification   |   | Order Service  |
|                |   | Service        |   |                |
+----------------+   +----------------+   +----------------+
        |                     |                     |
        v                     |                     |
+----------------+           |                     |
|   MongoDB      |           |                     |
+----------------+           |                     |
                              |                     |
                              v                     v
                     +----------------+   +----------------+
                     | Payment Service|   | Product Service |
                     +----------------+   +----------------+
                              |                     |
                              v                     v
                     +----------------+   +----------------+
                     |   PostgreSQL   |   |   PostgreSQL    |
                     |                |   |                 |
                     +----------------+   +----------------+