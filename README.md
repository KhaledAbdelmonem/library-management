# Library Management System
REST API for a simple car rental service.
 
## Description

A REST API that fulfil the requirements detailed below:

Register a book with its title, author and publisher date and ISBN

Register a patron with its name, address and email 

Register a patron (to borrow a book)

Borrow a book from date/time, to date/time, book id, patron id

## Running

1. Java 8 and Maven 4 should be installed.

2. Change directory to the root folder of the application.

3. Run the below maven command to generate the JAR file.

```bash
mvn clean package
```

4. Run the JAR file

```bash
java -jar target/library-management-system-0.0.1-SNAPSHOT.jar
```

5. Go to the below URL.

```bash
http://localhost:8080
```

## Endpoints

1. register book (api/books/) - HTTP POST

- Sample Request - body

```
{
    "author": "Marijn Haverbeke",
    "title" : "Eloquent JavaScript, Third Edition",
    "isbn" : "9781593279509",
    "publisher_date" : "2018-12-04"

}
```

- Sample Response

```
{
    "id": 1
    "title": "Eloquent JavaScript, Third Edition",
    "author": "Marijn Haverbeke",
    "isbn": "9781593279509",
    "publisher_date": "2018-12-04",
    "borrowed": false,
    "createdAt": "2024-07-30T21:13:01.468542007"
}
```

2. register patron (api/patrons/) - HTTP POST

- Sample Request - body

```
{
    "name": "John Smith",
    "email" : "example@test.com",
    "city" : "NewYork",
    "phone" : "212 555-1234",
    "address" : "21 2nd Street",
    "age" : 25

}
```

- Sample Response

```
{
    "id": 1,
    "name": "John Smith",
    "email": "example@test.com",
    "city": "NewYork",
    "phone": "212 555-1234",
    "address": "21 2nd Street",
    "age": 25,
    "createdAt": "2024-07-30T21:15:00.750945546"
}
```

4. search books availability (api/books/) - HTTP GET

- Sample Request - body

- Sample Response

```
[
    {
        "id": 1,
        "title": "Eloquent JavaScript, Third Edition",
        "author": "Marijn Haverbeke",
        "isbn": "9781593279509",
        "publisher_date": "2018-12-04",
        "borrowed": false,
        "createdAt": "2024-07-30T21:21:30.083446"
    },
    {
        "id": 2,
        "title": "Practical Modern JavaScript",
        "author": "Marijn Haverbeke",
        "isbn": "9781491943533",
        "publisher_date": "2019-07-16",
        "borrowed": false,
        "createdAt": "2024-07-30T21:22:43.453956"
    }
]
```

5. borrow book (api/borrow/bookId/patron/patronId) - HTTP POST

- Sample Request - body

```
{
  "bookId": 1,
  "patronId": 1,
 
}
```

- Sample Response

```
Book borrowed Successfully
```
6. borrow book (api/return/bookId/patron/patronId) - HTTP PUT

- Sample Request - body

```
{
  "bookId": 1,
  "patronId": 1,
  "returnDate" : "2024-08-08"
 
}
```

- Sample Response

```
Book Returned Successfully
```

## Technologies

1. Java 8

2. Spring boot with Spring data JPA and Hibernate.

3. Maven 4

4. H2 database

5. Junit 4

