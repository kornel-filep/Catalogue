# Catalogue

Description
-----------
Catalogue is a simple Series and Episodes tracking application.  
This application is an assignment for **Programming Technologies** and **Programming Environments** class at the [University of Debrecen, Faculty of Informatics](http://www.inf.unideb.hu/).
To run the application you need at least java version 1.8 and to compile and package it yourself you need the JDK and maven installed and configured, so keep that in mind.

Expectations
------------
Structure:
- [x] Testable business logic
- [x] JavaFX user interface
- [x] Persistency using JPA

Maven-specific:
- [x] `pom.xml` must have a `description`, `developers` and `licenses` tag
- [x] Indicate source encoding
- [x] `maven-enforcer-plugin` must check for *JDK 1.8* (Later versions are also supported)
- [x] `maven-javadoc-plugin`
- [x] `maven-surefire-report-plugin` 
- [x] `maven-jxr-plugin`
- [x] `maven-checkstyle-plugin`

Documentation:
- [x] Checkstyle XML
- [x] ApiDoc (excluding GUI and JUnit tests)
- [x] Package informations (`package-info.java`)

Logging:
- [x] Logging

Unit testing:
- [x] JUnit tests

License:
- [x] Adding license 

Git-specific:
- [x] `.gitignore` file
- [x] Upload to *GitHub*
- [x] `README.md` file

How to run the application
--------------------------
You can build a standalone `.jar` application, and run it
- `$ mvn clean package`
- `$ cd target`
- `$ java -jar Catalogue-X.X.X.jar ` (where x.x.x is the current version number)
- *Keep in mind that the application.properties file is missing and should be created individually*
