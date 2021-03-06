#Uruchomienie projektu aplikacji schroniska

##


## Frontend:
### Wymagania:
Zainstalowany Node.js

### Uruchomienie aplikacji:
By uruchomic aplikacje nalezy przejsc do folderu frontend (znajduja sie tam pliki np package.json)

`npm install`

`npm start`


### Testy:
By uruchomic wszystkie przygotowane testy nalezy przejsc do folderu frontend (znajduja sie tam pliki np package.json) 
`npm test`

## Backend:
### Wymagania:
Zainstalowany JDK z ustawionym environment path JAVA_HOME 

### Uruchomienie aplikacji:
By uruchomic aplikacje nalezy przejsc do folderu backend (znajduja sie tam pliki pom oraz mvnw)

Windows: `mvnw spring-boot:run`

Linux:  `./mvnw spring-boot:run`

### Testy:
By uruchomic wszystkie przygotowane testy nalezy przejsc do folderu shelter (znajduja sie tam pliki pom oraz mvnw) 
,a nastepnie z poziomu terminala/cmd uruchomiec polecenie: 

Windows: `mvnw verify`

Linux:  `./mvnw verify` 

Chcac uruchomic pojedyncze testy nalezy
uruchomic polecenie 

`./mvnw -Dtest=sciezka.do.testu.klasaZtestem test` np. 

`mvn -Dtest=ztp.shelter.controller.AnimalControllerTest test`


## Baza danych:
Nalezy posiadac lokalna baze danych postgresql z uzytkownikiem postgres oraz haslem postgres
na porcie 5432. 
W celu stworzenia bazy danych nalezy wykonac nastepujace kroki:
UWAGA! Jesli windows: polecenia nalezy uruchomic z folderu bin postgresa.
1. W konsoli cmd/terminal uruchomic polecenie: `psql -U postgres`
2. Nastepnie stworzyc baze danych poleceniem: `create database shelter`;
3. Nastepnie zalezy przelaczyc sie na utworzona baze: `\c shelter`
4. Uruchomic kolejno skrypty sql: script, shelter_shelter_breeds, shelter_shelter_sizes, 
shelter_shelter_roles, shelter_shelter_animals, shelter_shelter_users

Windows: \i 'sciezka do skryptu' (np. \i 'C:\\plik.sql')

Linux: \i sciezka do skryptu

W razie problemow patrz:
https://stackoverflow.com/questions/13682739/postgresql-permission-denied-when-reading-from-file-with-i-command

Do aplikacji mozna zalogowac się za pomoca stworzonych juz wczesniej kont:

User: user@gmail.com, user

Pracownik: employee@gmail.com, employee

Admin: admin@gmail.com, admin


