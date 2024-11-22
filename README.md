Sistem Prijave za Posao

Ovo je Spring Boot aplikacija koja omogućava upravljanje korisnicima i prijavama za posao. Sistem koristi modernu sigurnost (Spring Security), podršku za relacione baze podataka putem JPA/Hibernate, te omogućava autentifikaciju i autorizaciju korisnika.
Sadržaj

    Tehnologije
    Postavke
        Preduvjeti
        Pokretanje Aplikacije
    Ključne Funkcionalnosti
    Struktura Projekta
    Sigurnosne Postavke
    Problemi i Rješenja

Tehnologije

    Java 17 - Glavni programski jezik
    Spring Boot 3.3.6 - Okruženje za brzo razvijanje aplikacija
    Spring Security - Upravljanje autentifikacijom i autorizacijom
    JPA/Hibernate - Upravljanje relacionom bazom podataka
    H2 Database - Koristi se za testiranje (ili promjena na proizvodnu bazu poput PostgreSQL/MySQL)
    BCrypt - Sigurno enkodiranje lozinki

Postavke
Preduvjeti

    Instaliran Java Development Kit (JDK) verzije 17 ili novije.
    Maven za upravljanje projektom.
    Razvijeno i testirano na IDE IntelliJ IDEA (ili bilo kojem modernom IDE-u za Javu).
    Poželjno koristiti Postman za testiranje API ruta.

Pokretanje Aplikacije

    Klonirajte repozitorij:

git clone https://github.com/korisnik/sistem_prijave_za_poso.git
cd sistem_prijave_za_poso

Podesite aplikaciju:

    Provjerite u application.properties odgovarajuće postavke baze podataka. Zadano je H2 baza za testiranje:

    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

Pokrenite aplikaciju:

Koristite Maven za build i pokretanje:

    mvn clean install
    mvn spring-boot:run

    Pristupite aplikaciji:
        Backend API je dostupan na: http://localhost:8080/api.

    Kreirani Admin Korisnik (automatski):
        Email: admin@example.com
        Lozinka: securepassword

Ključne Funkcionalnosti

    Korisničke Uloge:
        Admin može dodavati, brisati i pregledati korisnike.
        Registracija korisnika s ulogama "Admin" ili "Korisnik".
    Sigurnosne Postavke:
        Svi API-ji su zaštićeni osim /api/**.
    Baza Podataka:
        Koristi H2 bazu za testiranje (može se promijeniti u produkcijsku bazu).
    Kreiranje Admin Korisnika:
        Automatsko kreiranje admin korisnika pri pokretanju aplikacije.

Struktura Projekta

src/main/java/com/example/sistem_prijave_za_poso/
├── Controllers/
│   └── KorisnikController.java   # API endpointi za korisnike
├── Models/
│   └── Korisnik.java             # Entitet korisnika
├── Repositories/
│   └── KorisnikRepository.java   # JPA repozitorij za korisnike
├── Services/
│   ├── KorisnikService.java      # Sloj servisa
│   └── Imp/KorisnikServiceImp.java # Implementacija servisa
├── Security/
│   └── SecurityConfig.java       # Sigurnosne postavke
└── SistemPrijaveZaPosoApplication.java # Main klasa

Sigurnosne Postavke

    BCrypt za enkodiranje lozinki.
    CORS konfiguracija omogućava frontend aplikaciji (default: http://localhost:5173) da komunicira s backendom.
    Sve rute osim /api/** zahtijevaju autentifikaciju.

Problemi i Rješenja

    Greška @Bean metoda ne smije biti void:
        Riješeno uklanjanjem @Bean i korištenjem @PostConstruct za inicijalizaciju admin korisnika.

    CORS problem između frontenda i backenda:
        Dodana konfiguracija CORS-a u SecurityConfig klasu:

    config.addAllowedOrigin("http://localhost:5173");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

H2 Baza ne radi na proizvodnom serveru:

    Zamijeniti H2 postavke s proizvodnom bazom, npr. PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/ime_baze
spring.datasource.username=korisnik
spring.datasource.password=lozinka
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
