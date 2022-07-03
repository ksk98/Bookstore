# BOOKSTORE BACKEND

## Funkcjonalności
Projekt realizuje backend księgarni obejmujący funkcjonalności dodawania 
i usuwania książek przez admina, przeglądania książek, dodawanie ich do 
koszyka przez usera oraz tworzenie i prowadzenie 
zamówień tworzonych z koszyka usera. W ramach Spring Security zostało 
zaimplementowane JWT.

## Narzędzia
Do stworzenia i testowania REST API księgarni został wykorzystany Swagger. 
Na bazie umieszczonej w src/main/resources specyfikacji api.yaml Swagger 
wygenerował klasy DTO przedstawiające zasoby podczas komunikacji HTTP 
w ramach REST API oraz interfejsy kontrolerów endpointów API.

## Testowanie
Po uruchomieniu projektu wystawiany zostaje endpoint /swagger-ui/index.html 
pozwalający na przetestowanie endpointów REST API. Endpointy wymagające 
autentykacji można sprawdzić logując się wcześniej na konto stworzonego 
wcześniej usera lub na istniejące konto administratora (u: admin, p: admin). 
Logowanie powinno zwrócić token, który należy wkleić w sekcji authorize 
(zielony przycisk w górnym prawym rogu listy endpointów).

## Uruchomienie
Przed uruchomieniem projektu należy wygenerować odpowiednie klasy na 
bazie specyfikacji API poleceniem:

> gradlew.bat clean openApiGenerate