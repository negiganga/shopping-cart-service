# sample-springboot-service
Sample Springboot Service

MVP:
===
In Scope:
---------
1. As a user, I should be able to view the list of products with the product information.
2. The user should be able to specify the quantity for each product they wish to put into the shopping cart.
3. The user should be able to review their shopping cart to update the quantity or remove the product from the cart.
4. The user should be able to go back to view the list of products and add more to their shopping cart.
5. The user should be able to submit the order after which an order confirmation page should be displayed.

Out of Scope:
-------------
1. User's management
2. Supporting UI
3. I am mocking data for this MVP.
4. Payment
5. Personalization of products.
6. User's action tracking for analytics.
7. Home page which can be loaded on the basis of user's categorization or user's search/activity history.
8. Cache management ie eviction policy, etc.
9. DB indexing for better performance.


Components:
===========
Currently, all the major components are mentioned in a single repo, though we can extract them out to a separate
repositories so that we can maintain them separately. We can deploy them like separate microservices so that
we can allocate resources according to their respective needs and requirements, same goes with DB cluster design.

1. Search Service
2. Shopping Cart Service
3. Order Management Service


DB/Search Engine Choices:
=========================
PostgreSQL:
----------
This DB is going to help us in storing structured data for quick lookups and 
performing join queries and also getting the benefits of ACID properties to perform
transactional operational like making orders and keeping the counts of items consistent across nodes. 

ElasticSearch:
--------------
This search engine would help us in finding list of products matching the string
 entered by the user.

MongoDB:
--------
As different products have different fields and they are not having fixed structure
so NoSQL DBs can help us in storing them.


Header:
=======
In header we will always pass `userId` which will help us in personalizing the response and any Ads to the customer.


Objects:
========
Assumption: For simplicity and considering time constraint, I am using the same object as DTO, Domain, DAO.
Note: Currently there is very tight coupling between DTO/DAO, because of the time constraint I have to use it in that way. 

DTO:
----
The data transfer objects used to share the data with the client.

Domain/Business:
----------------
These objects would be carrying any domain related data.

DAO:
----
The data access objects used to carry DB/cache/any downstream objects.


Build:
======
mvn clean install

Run:
====
mvn spring-boot:run

URL:
====
Test URL:
---------
http://localhost:9178/shoppingservice/api/test

Shopping URL:
-------------
Product Metadata:
http://localhost:9178/shoppingservice/api/v1/products/metadata
Product Lookup:
http://localhost:9178/shoppingservice/api/v1/products/productID30

View Shopping Cart:
http://localhost:9178/shoppingservice/api/v1/shoppingcart/ShoppingCart2
Add item to Cart:
http://localhost:9178/shoppingservice/api/v1/shoppingcart/item?productId=productID101&shoppingCartId=shoppingCartID2&quantity-2&size=M&color=RED


