# Lab 5: CRUD REST API with Spring Boot and an ArrayList

**Student Name:** Dagmawi Tewodros
**Student ID:** UGR/7661/16

---

## Overview

This repository holds my solution for Lab 5. The goal is to build a REST API with Spring Boot, using Apache Maven and Spring Web, that supports the basic CRUD operations (create, read, update, delete). Data is kept in memory in an `ArrayList`, so no database is needed.

## What the Lab Requires

Following the lab manual, this project does the following:

* Sets up a Spring Boot project with web support.
* Defines an `Actor` model class with fields such as `id` and `name`.
* Provides an `ActorController` class, annotated with `@RestController` and `@RequestMapping("/actors")`, that exposes the API endpoints.
* Stores the actors in an `ArrayList` that the endpoints read from and modify.

## Requirements

Make sure your machine has the following before running the project:

* **JDK:** version 17 or newer.
* **IDE:** IntelliJ IDEA.
* **Build tool:** Apache Maven.
* **Testing tool:** a web browser, Postman, or curl, to try out the endpoints.

## Checking Your Setup

### Maven dependency (`pom.xml`)

The project uses the Spring Web starter, which takes care of handling HTTP requests and converting objects to JSON:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
```