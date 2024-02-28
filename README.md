KShop - Online Shopping Application (Workshop)

Context:

KShop is a new online shopping platform aiming to provide a smooth and user-friendly experience for customers. This workshop will focus on implementing essential functionalities using REST APIs for the KShop server.

MVP Scope:

This workshop will focus on building the Minimum Viable Product (MVP) functionalities, prioritizing core features that deliver the most value to customers. This includes:

Products: Customers can browse and view product details.
Carts: Customers can add and remove items to their carts.
REST API Requirements:

1. Get Products (GET /products):

Description: Retrieves a list of available products.
Response:
Status Code: 200 OK
Content Type: application/json
Example Payload:
JSON
[
  {
    "id": 1,
    "name": "T-Shirt",
    "description": "A comfortable and stylish T-shirt",
    "price": 19.99,
    "image_url": "https://example.com/tshirt.jpg"
  },
  {
    "id": 2,
    "name": "Mug",
    "description": "A beautiful mug for your morning coffee",
    "price": 9.99,
    "image_url": "https://example.com/mug.jpg"
  }
]
Use code with caution.

Acceptance Criteria:
The API retrieves a list of all products
Each product object includes essential details like id, name, description, price, and image URL.
2. Get Product by ID (GET /products/{id}):

Description: Retrieves details of a specific product by its ID.
Path Variable: {id} - Represents the unique product identifier.
Response:
Status Code:
200 OK - Product found
404 Not Found - Product not found
Content Type: application/json
Example Payload (200 OK):
JSON
{
  "id": 1,
  "name": "T-Shirt",
  "description": "A comfortable and stylish T-shirt",
  "price": 19.99,
  "image_url": "https://example.com/tshirt.jpg"
}
Use code with caution.

* **Example Payload (404 Not Found):**
JSON
{
  "message": "Product not found"
}
Use code with caution.

Acceptance Criteria:
The API retrieves details of a specific product based on the provided ID.
Returns a 200 OK with the product details if found.
Returns a 404 Not Found with an appropriate error message if the product doesn't exist.
3. Add Item to Cart (POST /carts/{user_id}/items):

Description: Adds a specific product to the user's cart.
Path Variable: {user_id} - Represents the unique user identifier.
Request Body:
Content Type: application/json
Example Payload:
JSON
{
  "product_id": 1,
  "quantity": 2
}
Use code with caution.

Response:
Status Code:
201 Created - Item added successfully
400 Bad Request - Invalid request body
Content Type: application/json
Example Payload (201 Created):
JSON
{
  "message": "Item added to cart successfully"
}
Use code with caution.

* **Example Payload (400 Bad Request):**
JSON
{
  "message": "Invalid request body"
}
Use code with caution.

Acceptance Criteria:
The API adds the specified product (identified by its ID) to the user's cart with the provided quantity.
Returns a 201 Created with a success message if the item is added successfully.
Returns a 400 Bad Request with an appropriate error message if the request body is invalid (e.g., missing fields or invalid data format).
Note:

This is a simplified example focusing on the core functionalities.
Error handling and additional features like authentication, user management, order processing, etc., can be further explored based on the workshop duration and complexity.
Implementing security and best practices like user authentication and authorization would be essential in a real-world application.
By implementing these basic REST APIs, the workshop participants will gain