# ProgettoOOP

## Introduzione
L'applicazione TicketMasterUS permette all'utente di ottenere informazioni relative agli eventi che avranno luogo negli USA, in particolare nelle città California, Florida, Massachusetts e New York. L'applicazione fornisce dati relativi agli eventi e ai promoter che li sponsorizzano, inoltre l'utente può visualizzare le statistiche su questi dati, con la possibilità di applicare dei filtri al momento della ricerca.

## Diagrammi UML
### Use Case Diagram
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/Use%20Case%20Diagram.jpg)

### Class Diagram
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/Class%20Diagram.jpg)

* it.univpm.TicketMasterUS.Models
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Models.jpg)

* it.univpm.TicketMasterUS.Controller
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Controller.jpg)

* it.univpm.TicketMasterUS.Utils
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Utils.jpg)

* it.univpm.TicketMasterUS.Exception
* ![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Exception.jpg)

### Sequence Diagram

## Rotte
L'utente può effettuare le richieste da Postman tramite l'indirizzo:
```
localhost:8080
```
N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/events?stateCode=NY` | *restituisce un JSONArray contenente i dati relativi agli eventi.*
[2](#2) | ` GET ` | `/promoters?stateCode=NY` | *restituisce un JSONArray contenente i dati relativi ai promoter che sponsorizzano gli eventi.*
[3](#3) | ` POST ` | `/statsState` | *restituisce un JSONArray contenente le statistiche di ogni stato, in particolare il numero totale e il numero raggruppato per genere di promoter che sponsorizzano eventi.*
[4](#4) | ` POST ` | `/globalStats` | *restituisce un JSONArray contenente lo stato con il maggior/minor numero di eventi e lo stato con il maggior/minor numero di promoter.*
[5](#5) | ` POST ` | `/statsPromoter` | *restituisce un JSONObject contenente le statistiche relative ai promoter, in particolare il numero di eventi totali, di eventi per genere e il numero di stati in cui il promoter sponsorizza l'evento .*
[6](#6) | ` POST ` | `/filteredStats` | *restituisce un JSONArray che contiene informazioni sulle statistiche, filtrate in base alla scelta dell'utente*

## 1.   /events
La prima rotta restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano le informazioni sugli eventi. Nel caso in cui non venga specificato lo stateCode dall'utente, l'applicazione restituisce i dati relativi a New York.
Gli stateCode utilizzabili sono i seguenti:
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

La rotta genera un'eccezione se non viene inserito uno di questi stateCode

## 2.   /promoters
La seconda rotta restituisce un JSONObject di questo tipo, cioè contenente Id e nome del promoter che sponsorizza l'evento. Nel caso in cui non venga specificato lo stateCode dall'utente, l'applicazione restituisce i dati relativi a New York.
Gli stateCode utilizzabili sono i seguenti:
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

La rotta genera un'eccezione se non viene inserito uno di questi stateCode

### Autori
Progetto realizzato da:
- Alice Moretti
- Arianna Ronci
