
# Sample Spring Boot Project with Entity Management

This project is a Spring Boot application that demonstrates basic CRUD (Create, Read, Update, Delete) operations on a `Entity` object using Spring Data JPA. It provides endpoints for adding, fetching, and deleting entities. 

## Features
- **Create Entity**: Adds a new entity to the database.
- **Read Entity**: Retrieves entities either by ID or fetches all entities.
- **Delete Entity**: Deletes a specific entity by ID or removes all entities.
  
## Technologies Used
- Java 17
- Spring Boot 2.x
- Spring Data JPA
- H2 Database (In-memory database for testing purposes)
  
## Prerequisites
- Java 17 or above.
- Maven for dependency management.
- Postman or any REST client to test the API.

## Getting Started


### Build the Project
```bash
mvn clean install
```

### Run the Application
To start the application, run the following command:
```bash
mvn spring-boot:run
```

By default, the application will run on `http://localhost:8080`.

### Configuration
The database configuration for an in-memory H2 database is located in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.initialization-mode=always
```

### API Endpoints

#### 1. **Create an Entity**
- **URL**: `/entities`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "id": 12,
    "name": "Entity Name"
  }
  ```

- **Response**: Returns the created entity.

#### 2. **Get All Entities**
- **URL**: `/entities`
- **Method**: `GET`
- **Response**: Returns a list of all entities.

#### 3. **Get Entity by ID**
- **URL**: `/entities/{id}`
- **Method**: `GET`
- **Response**: Returns the entity with the given ID.

#### 4. **Delete an Entity**
- **URL**: `/entities`
- **Method**: `DELETE`
- **Request Body**:
  ```json
  {
    "id": 12,
    "name": "Entity Name"
  }
  ```

- **Response**: Deletes the specified entity.

#### 5. **Delete All Entities**
- **URL**: `/entities/all`
- **Method**: `DELETE`
- **Response**: Deletes all entities.

## Example Postman Requests

### 1. **Create Entity**

- **Method**: POST
- **URL**: `http://localhost:8080/entities`
- **Body**:
  ```json
  {
    "id": 12,
    "name": "Sample Entity"
  }
  ```

### 2. **Get All Entities**

- **Method**: GET
- **URL**: `http://localhost:8080/entities`

### 3. **Get Entity by ID**

- **Method**: GET
- **URL**: `http://localhost:8080/entities/{id}` (e.g., `http://localhost:8080/entities/12`)

### 4. **Delete Entity**

- **Method**: DELETE
- **URL**: `http://localhost:8080/entities`
- **Body**:
  ```json
  {
    "id": 12,
    "name": "Sample Entity"
  }
  ```

### 5. **Delete All Entities**

- **Method**: DELETE
- **URL**: `http://localhost:8080/entities/all`

## Test Cases

### Test Case 1: Create Entity
- **Test Steps**:
  1. Send a `POST` request to `/entities` with a valid `id` and `name`.
  2. Verify that the response status is `201 Created`.
  3. Verify that the response body contains the correct `id` and `name`.

- **Expected Result**: Entity is created and returned with correct attributes.

### Test Case 2: Get All Entities
- **Test Steps**:
  1. Send a `GET` request to `/entities`.
  2. Verify that the response status is `200 OK`.
  3. Verify that the response body is a list of entities.

- **Expected Result**: All entities are returned.

### Test Case 3: Get Entity by ID
- **Test Steps**:
  1. Send a `GET` request to `/entities/{id}` (e.g., `/entities/12`).
  2. Verify that the response status is `200 OK`.
  3. Verify that the response body contains the correct entity for the given `id`.

- **Expected Result**: Entity is returned based on the provided `id`.

### Test Case 4: Delete Entity
- **Test Steps**:
  1. Send a `DELETE` request to `/entities` with the entity's `id` and `name`.
  2. Verify that the response status is `204 No Content`.
  3. Verify that the entity is deleted by sending a `GET` request to `/entities/{id}` and receiving a `404 Not Found` or similar response.

- **Expected Result**: Entity is deleted successfully.

### Test Case 5: Delete All Entities
- **Test Steps**:
  1. Send a `DELETE` request to `/entities/all`.
  2. Verify that the response status is `204 No Content`.
  3. Verify that no entities remain by sending a `GET` request to `/entities`.

- **Expected Result**: All entities are deleted.

---

