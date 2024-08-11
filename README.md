# User Account Service

## Opis
Ovaj projekt je REST API servis koji omogućava CRUD operacije nad korisnicima i njihovim tekućim računima.

## Tehnologije
- Spring Boot
- Hibernate
- Microsoft SQL Server
- Maven
- JDK 17+

## Pokretanje aplikacije
1. Klonirajte repozitorijum: `git clone <URL_REPOZITORIJUMA>`
2. Uđite u direktorijum projekta: `cd user-account-service`
3. Konfigurišite `application.properties` fajl sa vašim MSSQL kredencijalima.
4. Pokrenite aplikaciju: `mvn spring-boot:run`

## API Endpointovi
- **POST /users**: Kreira novog korisnika
- **GET /users/{id}**: Dohvata korisnika po ID-u
- **PUT /users/{id}**: Ažurira informacije o korisniku
- **DELETE /users/{id}**: Briše korisnika po ID-u
- **POST /accounts/users/{userId}**: Kreira tekući račun za korisnika
- **GET /accounts/{id}**: Dohvata tekući račun po ID-u
- **GET /accounts/users/{userId}**: Dohvata sve tekuće račune za određenog korisnika
- **DELETE /accounts/{id}**: Briše tekući račun po ID-u

## SQL Skripte
Pronađite SQL skripte u priloženim fajlovima sql/sql_upit.
