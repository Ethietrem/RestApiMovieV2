___=== DESCRIPTION===___
    *1st commit*
Introduction
Preliminary work
First start
API data model - Swagger
API endpoints - Swagger
Generating documentation and preliminary code and API models (editor.swagger.io)
Use of parts received from the file generator
Finishing the implementation of API mocks in the project

    *2nd commit*
Preliminary work - xamp, MySQL, phpMyAdmin
Creating a table, filling with values
Object-relational table mapping (Hibernate Reverse Engineering Tool; NetBeans)
Verification?

    *3rd commit*
Data access layers - EntityManager
General authentication architecture
Implementation of registration
Verification
Bug fix

    *4th commit*
Introduction to unit tests
Performing the AuthenticateResource sample test
Introduction to Java FX technology (not available here)
Add mockito tests
Jersey tests?
Bug fix

    *5th commit*
Adding new data models and endpoints for the API
Swagger generation
Implementation
Tests
Database edition
Generating hibernate from the database
Tests


***********************************************************************************************************************
Języki obiektowe 2 (Java)

Aplikacja Baza filmów – REST API

Intelli IDEA JAVA MAVEN

Swagger - narzedzie do modelowania API

<project><dependency> - zaleznosci projektu, takie pomocnicze biblioteki
         <plugin> - dodatkowe wtyczki np wtyczka do usługi sieciowej servera org.eclipse.jetty

Kod odpowiedzi HTTP – informujący klienta o stanie zakończenia operacji, w ogólności można przyjąć następującą konwencję:
    a. kod 2xx – powodzenie operacji, np. 200 dla GET, 204 dla PUT,
    b. kod 4xx – błąd u klienta, np. 401 – brak uwierzytelnienia, 403 – brak uprawnień, 404 – zasób nie został znaleziony,
    c. kod 5xx – błąd serwera, np. wystąpienie nieoczekiwanego wyjątku aplikacji.
