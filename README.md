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

### Commit changes
```shell script
git add .
git commit -m "<<Your commit message>>"
```