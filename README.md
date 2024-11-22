# Sistem Prijave za Posao

Ovo je full-stack aplikacija koja omogućava upravljanje korisnicima, poslovima i recenzijama. Backend je razvijen u **Spring Boot-u**, dok je frontend izrađen korištenjem **React-a**. Ovaj dokument opisuje kako postaviti i pokrenuti aplikaciju.

---

## 🎯 Postavke i Pokretanje

### Preduvjeti

1. **Java Development Kit (JDK)** verzije 17 ili novije.
2. **Maven** za upravljanje projektom.
3. **Node.js i npm** za frontend aplikaciju.
4. **MySQL** server za bazu podataka.

---

### Backend: Postavljanje i Pokretanje

1. **Klonirajte repozitorij:**

   ```bash
   git clone https://github.com/korisnik/WEBZadace
   cd WEBZadace
    ```

2. **Podesite MySQL bazu podataka:**
   
   Kreirajte bazu podataka projekat_baza u MySQL-u:
 ```sql
   CREATE DATABASE projekat_baza;
 ```

3. **Provjerite konfiguraciju:**

   Otvorite fajl src/main/resources/application.properties i provjerite MySQL postavke:
     ```java
   spring.datasource.url=jdbc:mysql://localhost:3306/projekat_baza
   spring.datasource.username=root
   spring.datasource.password=hamza
      ```
     Zamijenite root i hamza svojim korisničkim imenom i lozinkom ako su drugačiji.
   
4. **Pokrenite backend aplikaciju:**
   
    U terminalu unutar direktorija projekta pokrenite:

   ```java
   cd sistem_prijave_za_poso
   mvn clean install
   mvn spring-boot:run
   ```
   Backend aplikacija će biti dostupna na http://localhost:8080.

5. **Admin korisnik:**
   
   Prilikom pokretanja backend aplikacije automatski se kreira admin korisnik sa sljedećim podacima:

    Email: hamza.gacic.22@size.ba
    Lozinka: hamza

### Frontend: Postavljanje i Pokretanje

1. **Instalirajte Node.js zavisnosti:**

   Idite u direktorij frontend aplikacije i instalirajte zavisnosti:

   ```java
   cd sistem_prijave_za_poso_frontend
   npm install
   ```
2. **Pokrenite frontend aplikaciju:**

   U istom direktoriju pokrenite razvojni server:
   
   ```javascript
   npm run dev
   ```
   Frontend aplikacija će biti dostupna na http://localhost:5173.


# Ključne Funkcionalnosti

## Korisničke Stranice

### Home: Pregled trenutnih poslova na koje je moguće se prijaviti. 
### Login/Register: Stranice za prijavu i registraciju korisnika. 
### Dashboard: Pregled svih korisnika, recenzija, poslova kao i statistika. 
### Dodavanje/Izmjena korisnika: Stranice za dodavanje novih ili izmjenu postojećih korisnika. 
### Dodavanje/Izmjena poslova: Stranice za dodavanje novih ili izmjenu postojećih poslova. 
### Dodavanje/Izmjena recenzija: Stranice za dodavanje novih ili izmjenu postojećih recenzija. 
### Recenzije: Pregled i uređivanje recenzija. 
### Posao: Pregled i uređivanje posla, kao i prijava na posao. 
### Profil: Pregled profila korisnika, izmjena lozinke, pregled napravljeni poslova i njihovo uređivanje, pregled svojih recenzija na drugim poslovima i njihovo uređivanje. 
   
   
## Backend Funkcionalnosti

### API endpointi za CRUD operacije nad korisnicima, poslovima i recenzijama.
### Sigurnosne funkcionalnosti (Spring Security) sa zaštitom endpointa.
### BCrypt enkodiranje lozinki za sigurnost.

## Frontend Funkcionalnosti

### Korištenje react-router-dom za navigaciju.
### Zaštićene rute za stranice koje zahtijevaju autentifikaciju.

