# Travel Management Solution – Cab Booking (Spring Boot Microservices)

George's travel company needs software to book cabs and quote fares. This project provides two
REST microservices (inside one Spring Boot app), HTML front-end pages, and JUnit 5 tests.

## Tech stack
Java 17 · Spring Boot 3.2.5 (Spring Web / MVC) · JUnit 5 + MockMvc + AssertJ · Maven

## Project layout
```
src/main/java/com/travel/george
├── SpringBootAssignmentSolutionApplication.java   (entry point)
├── controller/CabBookingController.java            (REST controller, Spring annotations)
├── service/CabBookingService.java                  (Microservice: book the cab)
├── service/FareCalculatorService.java              (Microservice: calculate the fare)
├── model/ (CabType, BookingResponse, FareResponse)
└── exception/GlobalExceptionHandler.java           (JSON error responses)
src/main/resources/static   index.html · book-cab.html · calculate-fare.html
src/test/java/...           context test, MockMvc controller tests, service unit tests
```

## API
| Method | URL         | Params                      | Success response |
|--------|-------------|-----------------------------|------------------|
| POST   | /cab/book   | from, to, typeOfCab         | `{"code":1,"message":"Luxury Cab Booked from Home to Work"}` |
| POST/GET | /cab/fare | distance (km), typeOfCab    | `{"code":1,"cabType":"Luxury","distanceKm":10.0,"fare":350.0,"message":"..."}` |

Cab types: `1` Economy (50 + 12/km), `2` Luxury (100 + 25/km), `3` SUV (70 + 18/km).
Errors return HTTP 400 with `{"code":0,"message":"..."}`.

## Run (Task 8 / 10)
```bash
./mvnw clean test              # run unit tests          (Windows: mvnw.cmd, or use mvn)
./mvnw clean package           # build executable jar -> target/cab-booking-system-1.0.0.jar
java -jar target/cab-booking-system-1.0.0.jar
```
If `./mvnw` gives trouble, install Maven and run `mvn wrapper:wrapper` once to generate the
official wrapper (including `mvnw.cmd`), or just use `mvn` directly.

In Eclipse EE: File ▸ Import ▸ Existing Maven Projects, then run
`SpringBootAssignmentSolutionApplication` as a Java Application.

## Test with Postman
1. `POST http://localhost:8080/cab/book`, Body ▸ form-data: `from=Home`, `to=Work`, `typeOfCab=2`
   → `200 OK` `{"code":1,"message":"Luxury Cab Booked from Home to Work"}`
2. `POST http://localhost:8080/cab/fare`, form-data: `distance=10`, `typeOfCab=2` → fare 350.0

## Test with front end
Open http://localhost:8080 → **Book Cab** / **Calculate Fare**.

## Submission checklist
- Source code zip (zip this folder, excluding `target/`)
- Database scripts: this project keeps no persistent data (bookings are returned as responses),
  so no DB scripts are required. See "Extending" if your course expects a database.
- Screenshots of outputs → put them in `screenshots/` (Postman, browser pages, test results, jar run)

## Extending
Add `spring-boot-starter-data-jpa` + H2/MySQL and a `Booking` entity to persist bookings; add the
matching `schema.sql` to the submission.
