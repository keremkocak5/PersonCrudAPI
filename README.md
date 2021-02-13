# Person CRUD API

A simple API to perform CRUD operations on Person objects. 

## Functional Background

This REST API can create, retrieve, update and delete Person objects from an in-memory database. Since the data is not persisted on hard drive, data will be lost every time the application stops.

A Person object has the following properties:
- First Name
- Last Name
- Age 
- Favourite Colour

All fields described above are mandatory. The API validates those fields. There are constraints on the database level against null values as well. 

For security reasons, "*" (asterisk) character is not allowed for firstName, lastName and favouriteColour fields. 

Basic authentication is required to access the services; please refer to the "Security" section of this document for a demo username and password. 




## REST API

### Retrieve Persons

Retrieves person objects from the database. A search can be conducted with any of the provided optional fields, which are described below. 

If no data is found, HTTP error code 404 is returned. 

##### API Access Information

HTTP Method: GET
The web service accepts JSON objects. 
Endpoint: http://localhost:8081/api/v1/persons

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
id | Optional | Integer | A search can be conducted on the ID field of persons, if this parameter is used.
firstName | Optional | String | A search can be conducted on the firstName field of persons, if this parameter is used. Min 3, max 250 characters. No asterisk allowed. 
lastName | Optional | String | A search can be conducted on the lastName field of persons, if this parameter is used. Min 3, max 250 characters. No asterisk allowed. 
favouriteColour | Optional | String | A search can be conducted on the favouriteColour field of persons, if this parameter is used. Min 3, max 250 characters. No asterisk allowed. 
age | Optional | Integer | A search can be conducted on the age field of persons, if this parameter is used. Min value 0, max value 140. 

Sample Request:

```
{    
	"id": 0,
	"firstName" : "kerem",
	"lastName" : "kocak",
	"age" : 38,
	"favouriteColour" : "azure"
}```

Sample Response: 
```
{
    "persons": [
        {
            "id": 0,
            "firstName": "kerem",
            "lastName": "kocak",
            "age": 38,
            "favouriteColour": "azure",
            "personStatus": "ACTIVE"
        }
    ]
}
```

### Create Person

Creates a person entity in the database. 

##### API Access Information

HTTP Method: POST
The web service accepts JSON objects. 
Endpoint: http://localhost:8081/api/v1/persons

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
firstName | Mandatory | String | No asterisk allowed. 
lastName | Mandatory | String | No asterisk allowed. 
favouriteColour | Mandatory | String | Min 3, max 250 characters. No asterisk allowed. 
age | Mandatory | Integer | Min value 0, max value 140. 

Sample Request:

```
{    	
	"firstName" : "kerem",
	"lastName" : "kocak",
	"age" : 38,
	"favouriteColour" : "azure"
}```

Sample Response: 
```
```


### Delete Person

Delete a person entity from the database. 

If the ID number is not found, or if the person is already deleted, then a HTTP 404 error code is returned. 


##### API Access Information

HTTP Method: DELETE
The web service accepts JSON objects. 
Endpoint: http://localhost:8081/api/v1/persons

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
id | Mandatory | Integer | Id number of the person to be deleted.


Sample Request:

```
{    	
	"id": 0
}```

Sample Response: 
```
```


### Update Person

Updates a person entity in the database. 

If the ID number is not found, or if the person is deleted, then a HTTP 404 error code is returned. 


##### API Access Information

HTTP Method: PUT
The web service accepts JSON objects. 
Endpoint: http://localhost:8081/api/v1/persons

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
id | Mandatory | Integer | Id number of the entity to be updated.
firstName | Mandatory | String | Min 3, max 250 characters. No asterisk allowed. 
lastName | Mandatory | String | Min 3, max 250 characters. No asterisk allowed. 
favouriteColour | Mandatory | String |  Min 3, max 250 characters. No asterisk allowed. 
age | Mandatory | Integer | Min value 0, max value 140. 

Sample Request:

```
{    	
	"id": 0,
	"firstName" : "mary",
	"lastName" : "smith",
	"age" : 38,
	"favouriteColour" : "red"
}```

Sample Response: 
```
```



## Tecnical Background

### Vagrant

This application is working with the https://github.com/joanmarcriera/vagrant-file-for-java-apps Vagrant template.

To strap the Person CRUD API Application with Vagrant;
1. Download Vagrant from https://www.vagrantup.com/ and install the application
2. Download https://github.com/joanmarcriera/vagrant-file-for-java-apps
3. Download XXXXXXXXXX and extract the files into a subfolder, where your Vagrant template resides 
4. Execute the following command where Vagrant template resides: **vagrant up**
5. Execute the following command where Vagrant template resides: **vagrant ssh**
6. Execute the following command : **cd /vagrant/demo**
7. Execute the following command : **ls**. The files of the demo application should be listed.
8. You are ready to run the Person CRUD API.
9. When done, execute the following command where Vagrant template resides: **vagrant halt**




### Running the service

This application uses port **8081**. The Maven command below can be used to run the application:
```
mvn spring-boot:run
```


### Api Documentation

Once the application is up and running on your local device, you may access [Swagger UI](http://localhost:8081/swagger-ui.html) to display the available API's. 

### Security

Spring Web Security ensures authentication for accessing web services. In this demo application, username and password are embedded into SecurityConfig class. Since this is only a demo, you can use **user1/1234** to gain access.


### Test Coverage

Test coverage is reported with Jacoco. Execute the following Maven command the generate Jacoco report:
```
mvn clean test
```

A Jacoco coverage file will be generated in your local directory ".../target/site/jacoco/index.html" 


### H2 Database

This application uses in-memory H2 database. The column properties of Person table are as follows:

```
PERSON

id INT AUTO_INCREMENT  PRIMARY KEY,
first_name VARCHAR(250) NOT NULL,
last_name VARCHAR(250) NOT NULL,
favourite_colour VARCHAR(250) NOT NULL,
age INT NOT NULL,
person_status INT NOT NULL,
create_time DATE NOT NULL,
last_update_time DATE,
delete_time DATE,
create_user VARCHAR(250)  NOT NULL,
last_update_user VARCHAR(250),
delete_user VARCHAR(250)```


A dummy record is inserted into the database by default for easing the testing progress:
id -> 0
firstName -> kerem
lastName -> kocak
age -> 38
favouriteColour -> azure

### Project Dependencies 
- Java 1.8.0_131 
- Spring Boot 2.3.8
- maven 3.6.3
- Swagger 2.4.0
- Junit 5
- Project Lombok
- Jacoco 0.8.6
- Spring Boot H2