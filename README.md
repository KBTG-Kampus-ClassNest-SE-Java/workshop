KBazaar - Online Shopping Application (Workshop)

Context:

KBazaar is a new online shopping platform aiming to provide a smooth and user-friendly experience for customers. This workshop will focus on implementing essential functionalities using REST APIs for the KBazaar server.

MVP Scope:
This workshop will focus on building the Minimum Viable Product (MVP) functionalities, prioritizing core features that deliver the most value to customers. This includes:

Products: Customers can browse and view product details which is associated with Shop
Carts: Customers can add and remove items to their carts.
Promotions: Customers can apply promotional codes to their carts.
						- Promotion could be a percentage discount or a fixed amount discount.
						- Promotion could be applied to the entire cart or specific products.
						- Promotion could be Buy 2 Get 1 Free or Buy 3 Get 1 Free
Kpoints: Customers can earn Kpoints for every purchase and redeem them for discounts.
						- Kpoints could be earned based on the amount spent.
						- Kpoints could be redeemed for a discount on the next purchase.
						- Kpoints could be used to get a free product.
						- Kpoints could be used to get a discount on the entire cart.

Requirements: Consist with user stories and acceptance criteria as well as technical requirements.
REST API Endpoints: could be /products, /carts, /promotions, /kpoints
assume we have fix user id for now, so we can use /carts/{user_id} to get the cart of the user


User Stories:
# Story 1:
	As a customer, I want to view a list of available products, so that I can browse and select the products I want to purchase.
## Acceptance Criteria:
	- The API retrieves a list of all products
	- Each product object includes essential details like id, name, description, price, sku and stock quantity.
## Technical Requirements:
	- REST API Endpoint: GET /products
	- Response: 200 OK with a list of products in JSON format
	- Each product object should include id, name, description, price, sku and stock quantity.
	- any error should be handled and return 404 Not Found with an appropriate error message if the product doesn't exist.
		for example: {"message": "Product not found"} with 404 status code

# Story 2:
	As a customer, I want to view details of a specific product by its ID, so that I can get more information about the product before making a purchase.
## Acceptance Criteria:
	- The API retrieves details of a specific product based on the provided ID.
	- Returns a 200 OK with the product details if found.
	- Returns a 404 Not Found with an appropriate error message if the product doesn't exist.
## Technical Requirements:
	- REST API Endpoint: GET /products/{id}
	- Path Variable: {id} - Represents the unique product identifier.
	- Response: 200 OK with the product details in JSON format if found
	- Response: 404 Not Found with an appropriate error message if the product doesn't exist
	- any error should be handled and return 404 Not Found with an appropriate error message if the product doesn't exist.
		for example: {"message": "Product not found"} with 404 status code

# Story 3:
	As a customer, I want to add a specific product to my cart, so that I can purchase the product later.
## Acceptance Criteria:
	- The API adds the specified product (identified by its ID) to the user's cart with the provided quantity.
	- Returns a 201 Created with a success message if the item is added successfully.
	- Returns a 400 Bad Request with an appropriate error message if the request body is invalid (e.g., missing fields or invalid data format).
## Technical Requirements:
	- REST API Endpoint: POST /carts/{user_id}/items
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Request Body: Content Type: application/json
	- Request Body Example: {"product_id": 1, "quantity": 2}
	- Response: 201 Created with a success message if the item is added successfully
	- Response: 400 Bad Request with an appropriate error message if the request body is invalid
	- any error should be handled and return 400 Bad Request with an appropriate error message if the request body is invalid.
		for example: {"message": "Invalid request body"} with 400 status code

# Story 4:
	As a customer, I want to apply a promotional code discount 30% to my cart, so that I can get a discount on my purchase.
## Acceptance Criteria:
	- The API applies the promotional code discount to the user's cart 30% off for the entire cart.
	- Returns a 200 OK with the updated cart details if the promotional code is applied successfully.
	- Returns a 404 Not Found with an appropriate error message if the promotional code is invalid or expired.
## Technical Requirements:
	- REST API Endpoint: POST /carts/{user_id}/promotions
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Request Body: Content Type: application/json
	- Request Body Example: {"code": "30OFF"}
	- Response: 200 OK with the updated cart details if the promotional code is applied successfully
	- Response: 404 Not Found with an appropriate error message if the promotional code is invalid or expired
	- any error should be handled and return 404 Not Found with an appropriate error message if the promotional code is invalid or expired.
		for example: {"message": "Promotional code is invalid or expired"} with 404 status code

# Story 5:
	As a customer, I want to apply a promotional buy 2 get 1 free to my cart, so that I can get a freebie on my purchase.
## Acceptance Criteria:
	- The API applies the promotional buy 2 get 1 free to the user's cart.
	- Returns a 200 OK with the updated cart details if the promotional code is applied successfully.
	- Returns a 404 Not Found with an appropriate error message if the promotional code is invalid or expired.
## Technical Requirements:
	- REST API Endpoint: POST /carts/{user_id}/promotions
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Request Body: Content Type: application/json
	- Request Body Example: {"code": "BUY2GET1"}
	- Response: 200 OK with the updated cart details if the promotional code is applied successfully
	- Response: 404 Not Found with an appropriate error message if the promotional code is invalid or expired
	- any error should be handled and return 404 Not Found with an appropriate error message if the promotional code is invalid or expired.
		for example: {"message": "Promotional code is invalid or expired"} with 404 status code

# Story 6:
	As a customer, I want to earn Kpoints for every purchase, so that I can redeem them for discounts on my next purchase.
## Acceptance Criteria:
	- The API calculates and adds Kpoints to the user's account based on the amount spent.
	- Returns a 200 OK with the updated Kpoints balance if the Kpoints are added successfully.
	- Returns a 400 Bad Request with an appropriate error message if the request body is invalid (e.g., missing fields or invalid data format).
## Technical Requirements:
	- REST API Endpoint: POST /kpoints/{user_id}/earn
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Request Body: Content Type: application/json
	- Request Body Example: {"amount_spent": 100.00}
	- Response: 200 OK with the updated Kpoints balance if the Kpoints are added successfully
	- Response: 400 Bad Request with an appropriate error message if the request body is invalid
	- any error should be handled and return 400 Bad Request with an appropriate error message if the request body is invalid.
		for example: {"message": "Invalid request body"} with 400 status code

# Story 7:
	As a customer, I want to redeem Kpoints for a discount on my next purchase, so that I can save money on my order.
## Acceptance Criteria:
	- The API applies the Kpoints discount to the user's cart based on the number of Kpoints redeemed.
	- Returns a 200 OK with the updated cart details if the Kpoints are redeemed successfully.
	- Returns a 404 Not Found with an appropriate error message if the Kpoints are insufficient or invalid.
## Technical Requirements:
	- REST API Endpoint: POST /carts/{user_id}/kpoints
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Request Body: Content Type: application/json
	- Request Body Example: {"kpoints": 100}
	- Response: 200 OK with the updated cart details if the Kpoints are redeemed successfully
	- Response: 404 Not Found with an appropriate error message if the Kpoints are insufficient or invalid
	- any error should be handled and return 404 Not Found with an appropriate error message if the Kpoints are insufficient or invalid.
		for example: {"message": "Insufficient Kpoints"} with 404 status code

# Story 8:
	As a customer, I want to remove a specific product from my cart, so that I can update my purchase before checking out.
## Acceptance Criteria:
	- The API removes the specified product (identified by its ID) from the user's cart.
	- Any promotional discounts or Kpoints associated with the removed product should be recalculated and updated.
	- Returns a 200 OK with a success message if the item is removed successfully.
	- Returns a 404 Not Found with an appropriate error message if the product doesn't exist in the cart.
## Technical Requirements:
	- REST API Endpoint: DELETE /carts/{user_id}/items/{product_id}
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Path Variable: {product_id} - Represents the unique product identifier.
	- Response: 200 OK with a success message if the item is removed successfully
	- Response: 404 Not Found with an appropriate error message if the product doesn't exist in the cart
	- any error should be handled and return 404 Not Found with an appropriate error message if the product doesn't exist in the cart.
		for example: {"message": "Product not found in the cart"} with 404 status code

# Story 9:
	As a customer, I want to view my cart details, so that I can review the products and discounts before checking out.
## Acceptance Criteria:
	- The API retrieves the user's cart details including products, quantities, prices, discounts, and total amount.
	- Returns a 200 OK with the cart details in JSON format if the cart exists.
	- Returns a 404 Not Found with an appropriate error message if the cart doesn't exist.
## Technical Requirements:
	- REST API Endpoint: GET /carts/{user_id}
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Response: 200 OK with the cart details in JSON format if the cart exists
	- Response: 404 Not Found with an appropriate error message if the cart doesn't exist
	- any error should be handled and return 404 Not Found with an appropriate error message if the cart doesn't exist.
		for example: {"message": "Cart not found"} with 404 status code

# Story 10:
	As a customer, I want to place an order with the items in my cart, so that I can complete my purchase.
## Acceptance Criteria:
	- The API creates an order with the items in the user's cart and updates the stock quantity of the products.
	- Returns a 201 Created with the order details if the order is placed successfully.
	- Returns a 400 Bad Request with an appropriate error message if the cart is empty or the stock quantity is insufficient.
## Technical Requirements:
	- REST API Endpoint: POST /orders/{user_id}
	- Path Variable: {user_id} - Represents the unique user identifier.
	- Response: 201 Created with the order details if the order is placed successfully
	- Response: 400 Bad Request with an appropriate error message if the cart is empty or the stock quantity is insufficient
	- any error should be handled and return 400 Bad Request with an appropriate error message if the cart is empty or the stock quantity is insufficient.
		for example: {"message": "Cart is empty"} with 400 status code

<!-- Hints: collapse -->
## Some hints to help the participants get started with the workshop.
<details>
  <summary>Technical hints: อย่าพึ่งเปิดให้คิดเองก่อน</summary>
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
</details>
