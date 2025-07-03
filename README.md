<!--
  Copyright © 2025 MaheshTambelabs. All rights reserved.
  This software is the confidential and proprietary information of MaheshTambelabs.
  Unauthorized copying of this file, via any medium is strictly prohibited.
-->

# Dairy Management System - Microservices Architecture

![Java](https://img.shields.io/badge/Java-21%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)
![Maven](https://img.shields.io/badge/Maven-3.8.1-red)
![Docker](https://img.shields.io/badge/Docker-✓-blue)

A modern, scalable microservices-based Dairy Management System built with Spring Boot and Docker.

## 💻 Features

- **Product Service**: Manage dairy products inventory and details
- **RESTful APIs**: Clean, well-documented REST endpoints
- **Docker Support**: Easy containerization and deployment
- **Database Integration**: PostgreSQL for data persistence
- **Microservices Architecture**: Independent, scalable services

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot 3.1.0
- **Build Tool**: Maven 3.8.1
- **Database**: PostgreSQL
- **Containerization**: Docker
- **API Documentation**: OpenAPI/Swagger

## 📦 Prerequisites

- Java 21 or higher
- Maven 3.8.1 or higher
- Docker and Docker Compose
- PostgreSQL 13+

## 🔧 Getting Started

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/backend-microservices-dairy-mgmt.git
   cd backend-microservices-dairy-mgmt
   ```

2. **Set up the database**
   - Ensure PostgreSQL is running
   - Create a new database for the application
   - Update the database configuration in `application.properties`

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### Docker Setup

1. **Build and run using Docker Compose**
   ```bash
   docker-compose up --build
   ```

2. **Access the application**
   - API Documentation: http://localhost:8080/swagger-ui.html
   - Application: http://localhost:8080

## 📋 Project Structure

```
backend-microservices-dairy-mgmt/
├── product-service/          # Product management microservice
│   ├── src/
│   │   └── main/
│   │       ├── java/com/dairy/product/
│   │       │   ├── config/      # Configuration classes
│   │       │   ├── controller/  # REST controllers
│   │       │   ├── model/       # Entity classes
│   │       │   ├── repository/  # Data access layer
│   │       │   ├── service/     # Business logic
│   │       │   └── ProductServiceApplication.java
│   │       └── resources/
│   │           ├── application.properties
│   │           └── data.sql
│   ├── Dockerfile
│   └── pom.xml
└── docker-compose.yml
```

## 🔍 API Documentation

Once the application is running, you can access the following:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Documentation**: http://localhost:8080/v3/api-docs

## 🛠️ Development

### Building the Project

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

### Code Style

This project follows the Google Java Style Guide. Please ensure your code adheres to these standards.

## 🛠️ Deployment

### Prerequisites

- Docker and Docker Compose installed
- Configured environment variables

### Using Docker Compose

1. Update the environment variables in `docker-compose.yml`
2. Run the following command:
   ```bash
   docker-compose up -d
   ```

## 📋 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 💬 Contact

For any queries or support, please contact:
- Email: maheshtambelab@gmail.com
- GitHub: [@maheshtambelab](https://github.com/maheshtambelab)

## ❤️ Acknowledgments

- Spring Boot Team
- Open Source Community
- All Contributors

---

© 2025 MaheshTambelabs. All rights reserved.
