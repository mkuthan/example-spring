# Enterprise Spring Showcase

Collects best practices of the JEE applications based on Spring Framework and AngularJS. 
The showcase borrows also many concepts from another platforms and languages. 
Focuses on developer productivity, even if Java and JEE are not well suited for rapid development.

[![Build Status](https://mkuthan.ci.cloudbees.com/job/Example%20Spring/badge/icon)](https://mkuthan.ci.cloudbees.com/job/Example%20Spring/)

# Key Acronyms

## DDD

Project structure and setup aligned with Domain Driven Design methodology. Package structure reflects domain concepts not technical details. This is only a top of iceberg in DDD, for more details see: [Domain-Driven Design: Tackling Complexity in the Heart of Software](http://www.goodreads.com/book/show/179133.Domain_Driven_Design).

## BDD

The technical infrastructure and configuration for Behavior Driven Development with JBehave. Please remember that the most important part of BDD is communication not tools and automation, for more details see: [Specification by Example](http://www.goodreads.com/book/show/10288718-specification-by-example).

## TDD

The best of the beast tools for testing: TestNG, Mockito and FestAssert are the key players. They make the tests first class citizen in your projects. For more details see: [Practical Unit Testing With TestNG and Mockito](http://www.goodreads.com/book/show/15737558-practical-unit-testing-with-testng-and-mockito).

# Key Frameworks, Libraries and Runtimes

* Spring, Spring MVC, Spring Security, Spring Data are the backbone of the showcase.
* Hibernate as a persistence. I have plans to integrate NoSQL storages as well.
* Guava as a swiss army knife, if you like functional programming, you are impatiently waiting for closures in Java, you will love this library.
* JodaTime where SDK sucks.
* SLF4J + Logback where SDK sucks.
* JBehave for doing BDD not test scripts.
* TestNG for testing on steroids, no more JUnit. 
* Mockito the best library for mocking, developed by Polish guy!
* FestAssert to address a little bit not so flexible Java syntax.
* TwitterBootstrap give a good support for developer with strong server side background (me).
* AngularJS as JS MVC platform.
* ActiveMQ for asynchronous processing, integration with WebSockets is comming ...
* CloudBees as a awesome runtime platform and continuous delivery automation tool (Jenkins).
* MySql as a default database, but in memory H2 for integration tests. 
* Hexagonal architecture
* And last but not least: old good Maven. I will migrate project to Gradle eventually ...
