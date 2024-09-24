# Inventory Management Project

This Inventory Management project utilizes Java technologies including JAX-RS, JPA, and Hibernate. It provides a set of Rest APIs for managing various entities such as categories, customer information, inventory orders, and products.

## Features

- **Rest API Endpoints**: 
  - **Category**: Manage product categories.
  - **CustomerInfo**: Handle customer-related information.
  - **InventoryOrder**: Process inventory orders.
  - **Product**: Manage product details.
 

|             End Points                  |     Method    |            Description           |
| --------------------------------------- | ------------- |  ------------------------------- |
| v1/category                             |    GET        | return all categories            |
| v1/category/id/{id}                     |    GET        | return category by id            |
| v1/category/name/{name}                 |    GET        | return category by name          |
| v1/category                             |    POST       | save all new category            |
| v1/category/id/{id}                     |    PUT        | Update category info             |
| v1/category/id/{id}                     |    DELETE     |    Delect category               |
| ----------------------                  | ------------- |  ------------------------------- |
| v1/product                              |    GET        | return all products              |
| v1/product/id/{id}                      |    GET        | return product by id             |
| v1/product/name/{name}                  |    GET        | return product by name           | 
| v1/product                              |    POST       | save all new product             |
| v1/product/id/{id}                      |    PUT        | Update product info              |
| v1/product/id/{id}                      |    DELETE     |    Delect product                |
| --------------------------------------- | ------------- |  ------------------------------- |
| v1/customer                             |    GET        | return all customers             |
| v1/customer/id/{id}                     |    GET        | return customer by id            |
| v1/customer/name/{name}                 |    GET        | return customer by name          | 
| v1/customer/company_name/{companyName}  |    GET        | return customer by company name  | 
| v1/customer                             |    POST       | save all new customer            |
| v1/customer/id/{id}                     |    PUT        | Update customer info             |
| v1/customer/id/{id}                     |    DELETE     |    Delect customer               |
| --------------------------------------- | ------------- |  ------------------------------- |
| v1/inventoryOrder                       |    GET        | return all inventoryOrders       |
| v1/inventoryOrder/id/{id}               |    GET        | return inventoryOrder by id      |
| v1/inventoryOrder/date                  |    GET        | return inventoryOrder by date    | 
| v1/inventoryOrder/type/{type}           |    GET        | return inventoryOrder by type    |
| v1/inventoryOrder/status/{status}       |    GET        | return inventoryOrder by status  |
| v1/inventoryOrder                       |    POST       | save all new inventoryOrder      |
| v1/inventoryOrder/id/{id}               |    PUT        | Update inventoryOrder info       |
| v1/inventoryOrder/id/{id}               |    DELETE     |    Delect inventoryOrder         |




- **Exception Handling**: Utilizes `ExceptionMapper` for centralized exception handling across APIs.

- **Validation**: Implements custom validators for input validation on different methods to ensure data integrity.

- **Containerization**: The project is packaged using Docker, with a `Dockerfile` and `docker-compose.yml` for easy deployment and management.

## Technologies Used

- **Java**: The core programming language for the project.
- **JAX-RS**: For building RESTful web services.
- **JPA (Java Persistence API)**: For database operations.
- **Hibernate**: As the ORM (Object-Relational Mapping) framework.
- **Docker**: For containerization of the application.

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.
- [Make](https://www.gnu.org/software/make/) (if you're using the Makefile).

### Running the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/SubriaIs/Inventory_management

2. **Build the Project (optional, if you're using the Makefile)**:
   ```bash
   make build
   
3. **Start the Application(Using Docker Compose)**:
   ```bash
   docker-compose up

4. **Access the APIs: The application will be accessible at**:
   - API Base URL: `http://localhost:8081`
     
## License
   This project is licensed under the MIT License.
