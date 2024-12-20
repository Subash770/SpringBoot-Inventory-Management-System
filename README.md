# Spring Boot: Inventory Management System

## Project Overview

**Type:** Backend System  
**Objective:** Develop an inventory management system to monitor the gadgets assigned to employees for Stackroute.

## Entity Details

### Laptop Entity
The `Laptop` entity includes the following attributes:

- **id**: Unique ID of the Laptop (Integer)
- **laptopBrand**: Brand of the Laptop (String)
- **modelName**: Model name of the Laptop (String)
- **laptopTag**: Tag of the Laptop (String)

#### Example JSON Representation:
```json
{
   "id": 1,
   "laptopBrand": "HP",
   "modelName": "Pavilion x360",
   "laptopTag": "Convertible Laptop"
}
```

## API Details

The system includes REST services that allow for adding and deleting laptops in the inventory.

| API Route               | API Type | Success Response Code  | Validation Error Code  |
|-------------------------|----------|------------------------|------------------------|
| `/laptops`              | POST     | 201                    | 400                    |
| `/laptops/{laptopId}`   | DELETE   | 200                    | 404                    |

### Task 1: Service Layer Implementation

#### Methods in `LaptopService`:

1. **`saveLaptop()`**: Implements the POST method to save a laptop's data.
2. **`deleteLaptopById()`**: Implements the DELETE method to remove laptop data based on its ID.

### Task 2: REST API Endpoints

#### POST `/laptops`
- Saves a laptop to the database.
- **HTTP Status Codes:**
  - `201`: Successful response.
  - `400`: Parameters are null or empty.

#### DELETE `/laptops/{laptopId}`
- Deletes a laptop based on the provided ID.
- **HTTP Status Codes:**
  - `200`: ID found and deleted.
  - `404`: ID not found.

## Example Requests and Responses

### POST `/laptops`

#### Request Body:
```json
{
  "laptopBrand": "Lenovo",
  "modelName": "ThinkpadG12",
  "laptopTag": "LenovoThinkpadE14GN2"
}
```

#### Response:
- **Code:** `201`
- **Body:**
```json
{
  "id": 6,
  "laptopBrand": "Lenovo",
  "modelName": "ThinkpadG12",
  "laptopTag": "LenovoThinkpadE14GN2"
}
```

### DELETE `/laptops/{laptopId}`

#### Response:
- **Code:** `200`



