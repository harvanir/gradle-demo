# Getting Started

### Libraries & Tech
* Gradle 5.6
* Spring Boot 2.1.8
* Spring Web MVC
* Spring Data JPA
* QueryDsl 4.2.1
* MapStruct 1.3.0
* GoogleJavaFormat 1.7.0
* Lombok
* Flyway DB
* PostgreSQL
* Git hooks: pre-commit
* Jacoco
* SonarQube


### Gradle command
#### Clean
```shell script
./gradlew clean
```
#### Build
```shell script
./gradlew build
```
#### Format Java Code
```shell script
./gradlew goJF
```
#### Verify Java Code Format
```shell script
./gradlew clean verifyFormat build
```
#### Build & run code coverage (Jacoco)
```shell script
./gradlew clean build jacocoTestReport
```
#### Build, run Jacoco & Publish to SonarQube
```shell script
./gradlew clean sonarqube -Dsonar.login=<<sonarqube user token>>
```

### Commit changes
```shell script
git add .
git commit -m "<<Your commit message>>"
```