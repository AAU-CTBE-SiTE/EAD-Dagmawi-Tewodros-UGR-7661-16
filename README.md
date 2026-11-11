# Lab  6  AOP: Restaurant Menu CRUD with Spring Boot and AOP

**Student Name:** Dagmawi Tewodros  
**Student ID:** UGR/7661/16

---

## Overview

This project is a REST API for a restaurant menu, built with Spring Boot and Maven. It supports the four CRUD operations (create, read, update, delete) on menu items kept in memory in an `ArrayList`. It also uses **Aspect Oriented Programming** to log and time every menu operation without putting any logging code inside the controller.

## AOP Concepts Used

| Concept | Where it is in the code |
|---|---|
| **Aspect** | `LoggingAspect` (class annotated with `@Aspect`) |
| **Pointcut** | `menuMethods()` picks every method of `MenuItemController` |
| **@Before advice** | Logs the method name and arguments before it runs |
| **@AfterReturning advice** | Logs the value the method returned |
| **@AfterThrowing advice** | Logs the error when a method throws an exception (for example, menu item not found) |
| **@Around advice** | Measures how long each method takes |

## Project Files

* `SpringBootCrudApplication.java`: the main class.
* `MenuItem.java`: the model with `id`, `name`, `category` and `price`.
* `MenuItemController.java`: the CRUD endpoints under `/menu`.
* `LoggingAspect.java`: the aspect that logs every call.
* `pom.xml`: includes `spring-boot-starter-webmvc` and `spring-boot-starter-aspectj` (the Spring Boot 4 name for the AOP starter).

## Endpoints

| Method | URL | Action |
|---|---|---|
| GET | `/menu` | List the whole menu |
| GET | `/menu?category=Drink` | List items of one category |
| GET | `/menu/{id}` | Get one item (404 if missing) |
| POST | `/menu` | Add an item |
| PUT | `/menu/{id}` | Update an item (404 if missing) |
| DELETE | `/menu/{id}` | Delete an item (404 if missing) |

## How to Run

1. Open the project in IntelliJ IDEA (JDK 17 or newer).
2. Run `SpringBootCrudApplication`.
3. Open `http://localhost:8080/menu` in a browser, or use Postman or curl for POST, PUT and DELETE.
4. Watch the console: each request prints `[BEFORE]`, `[AFTER RETURNING]` and `[AROUND]` lines produced by the aspect.

## Example Requests

```
curl -X POST http://localhost:8080/menu -H "Content-Type: application/json" -d "{\"id\":5,\"name\":\"Tibs\",\"category\":\"Main\",\"price\":280}"
curl -X PUT http://localhost:8080/menu/5 -H "Content-Type: application/json" -d "{\"name\":\"Derek Tibs\",\"category\":\"Main\",\"price\":320}"
curl -X DELETE http://localhost:8080/menu/5
curl http://localhost:8080/menu/99
```

The last request returns 404 and the console shows an `[AFTER THROWING]` line.
