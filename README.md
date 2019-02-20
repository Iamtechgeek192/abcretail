**abcretail**

**abcretail Restful service**

Technologies

JDK 1.8.0

Mongo 4.0.6

SpringBoot 1.5.3.RELEASE

Mongo DB database and collection

database=myRetail

mongodb host=localhost

mongodb port=27017

mongo collection name =itemprice

use myRetail

db.itemprice.insert({ "_id" : 13860428, "_class" : "com.abcretail.api.model.ProductPrice", "price" : "11.00", "currencyCode" : "USD" })

Build, Test and Run application

cd abcretail

Then run

mvn clean package

Then run the jar

java -jar target/abcretail-0.0.1-SNAPSHOT.jar
````````
Application will start running on port 8082

Calling abcretail api services
Performing GET request on http://localhost:8080/product/13860428 It will return json object with product information and pricing information.

GET http://localhost:8080/product/13860428

Response:-

{ "id": 13860428, "name": "The Big Lebowski (Blu-ray)", "productPrice": { "price": 11, "currencyCode": "USD" } }

To perform PUT operation, send JSON object with updated price in request body, it will return JSON object with updated pricing information.

PUT http://localhost:8080/product/13860428

Request Body:-

{ "id": 13860428, "productPrice": { "price": 50, "currencyCode": "USD" } }

Response :-

{ "id": 13860428, "name": "The Big Lebowski (Blu-ray)", "productPrice": { "price": 50.00, "currencyCode": "USD" } }