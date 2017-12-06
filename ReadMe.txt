# Symantec Coding Assignment

This project is developed using below platform/libraries:
- Windows 10
- java 8
- Eclipse oxygen
- gradle 
- spring boot 1.5.4.RELEASE
- jetty server
- swagger 2.6.1 for APi doc

The "ContactManagementSystem" folder can be imported into Eclipse as eclipse project. 

Below are the directory structure of source code:
- All domain classes inside package com.symantec.cms.domain
- Spring boot and swagger helper class  inside com.symantec.cms package
- DAO classes inside com.symantec.cms.dao package
- error handling classes inside com.symantec.cms.exception package
- Rest APi classes inside com.symantec.cms.rest.service package
- service layer classes inside com.symantec.cms.service package
- utility classes inside com.symantec.cms.utils package
- test classes inside src/test/java directory

Assumption
--------------
- First name and Last Name is unique for any Contact
- the system is not defined in detail, so implemented it the way Google Contact works from different mobile phones.

Authentication
----------------
Implemented basic authentication using header "auth_token". The value of the header should be "auth_token".
Database
----------
I have not used any of persistence database. Instead I have used in-memory persistence using ConcurrentHashMap.

Features implemented
---------------------
1) Upload and download contact(s)
       - Could not understand what to implement for this
2) Look-up a contact
      - implemented
3) Delete/Update a contact
      - implemented
4) Sync interface 
      - implemented
5) Interface to import and export the contacts from/to a file
       - Rest interface to accept and return file is not implemented due to limited time, but the service API for accepting Contact list is implemented
6) Maintain history of the changes to a contact.
      - implemented using the "EntityUpdateHistory" entity class and "TableUpdateLogEnable" annotation
7) If look-up doesn’t return a match, it should be able to return a nearest match
       - not implemented due to limited time. Can be done by using any of the String distance algorithm

How to build and run:
1) Go to "ContactManagementSystem" folder from command prompt.
2) execute the command "gradlew build". It will build the application and run the unit test.
3) execute the command "gradlew test". It will run the unit test.
4) execute the command "java -jar build\libs\ContactManagementSystem-0.0.1-SNAPSHOT.jar". It will run the application inside the jetty server.


How to run the Rest APIs:
---------------------------

Every Rest request should have below 2 headers:
1) content-type: application/json
2) auth_token: symantec

API list:
---------
1) Add shop API
   URL: http://localhost:8080/contact
   
   Method: POST
   
   Request body:
      {
        "firstName": "abc",
        "lastName": "abc",
        "email": "email@abc.com",
        "phoneNumber": "1234"
      }
   Response code: CREATED(201)   
   
   Response body:
  
  2) Get Contact API:
  Method: GET
     URL(with sample value for path param): http://localhost:8080/contact?firstName=abc&lastName=abc
     
     Method: GET
     
     Response code : OK(200)
     
     Response body:
     {
        "id": 2,
        "isDeleted": false,
        "createdDate": 1512501807914,
        "lastUpdatedDate": 1512501807914,
        "firstName": "abc",
        "lastName": "abc",
        "email": "email@abc.com",
        "phoneNumber": "1234"
      }

   
   3) Update Contact API
   Method: PUT
   URL(URL param will have first name, last name and last update time from local): 
   http://localhost:8080/contact?firstName=abc&lastName=abc&lastUpdatedDate=1512502738610
   RequestBody(updated contact details:
         {
  "firstName": "abc",
  "lastName": "abdce",
  "email": "email@abc.com",
  "phoneNumber": "12347"
}

Response body:
{
    "id": 1,
    "isDeleted": false,
    "createdDate": 1512502977375,
    "lastUpdatedDate": 1512503039947,
    "firstName": "abc",
    "lastName": "abdce",
    "email": "email@abc.com",
    "phoneNumber": "12347"
    }

4) Delete API:
    Method: DELETE
   URL: http://localhost:8080/contact?firstName=abc&lastName=abdce

5) Sync API
Method: GET
Response:
[
    {
        "id": 2,
        "isDeleted": false,
        "createdDate": 1512503184227,
        "lastUpdatedDate": 1512503184227,
        "firstName": "abc",
        "lastName": "abc",
        "email": "email@abc.com",
        "phoneNumber": "1234"
    }
]
