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
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Exception.jpg)

### Sequence Diagram

## Rotte
L'utente può effettuare le richieste da Postman tramite l'indirizzo:
```
localhost:8080
```
N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/events?stateCode=NY` | *restituisce un JSONArray contenente le informazioni relative agli eventi.*
[2](#2) | ` GET ` | `/promoters?stateCode=NY` | *restituisce un JSONArray contenente le informazioni relative ai promoter che sponsorizzano gli eventi.*
[3](#3) | ` POST ` | `/statsState` | *restituisce un JSONArray contenente le statistiche di ogni stato, in particolare il numero totale e il numero raggruppato per genere di promoter che sponsorizzano eventi.*
[4](#4) | ` POST ` | `/globalStats` | *restituisce un JSONArray contenente lo stato con il maggior/minor numero di eventi nel prossimo mese e lo stato con il maggior/minor numero di promoter di eventi.*
[5](#5) | ` POST ` | `/statsPromoter` | *restituisce un JSONArray contenente le statistiche in base a uno o più promoter, in particolare il numero di eventi totali, di eventi per genere e il numero di stati in cui il promoter sponsorizza l'evento .*
[6](#6) | ` POST ` | `/filteredStats` | *restituisce un JSONArray che contiene informazioni sulle statistiche, filtrate in base alla scelta dell'utente*

## 1.   /events
La prima rotta restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano le informazioni sugli eventi. Nel caso in cui non venga specificato lo stateCode dall'utente, l'applicazione restituisce i dati relativi a New York.
La rotta genera un'eccezione se non viene inserito uno degli state code seguenti: 
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/getEvents.jpeg)

## 2.   /promoters
La seconda rotta restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano Id e nome del promoter che sponsorizzano gli eventi. Nel caso in cui non venga specificato lo stateCode dall'utente, l'applicazione restituisce i dati relativi a New York.
La rotta genera un'eccezione se non viene inserito uno degli state code seguenti: 
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/getPromoters.jpeg)

## 3.   /statsState
La terza rotta è di tipo POST e restituisce le statistiche per ogni stato, in particolare restituisce il numero totale di promoter che sponsorizzano eventi nello stato e il numero di promoter raggruppati per il genere di eventi che sponsorizzano.
La risposta è un JSONArray di questo tipo:
```
[
    {
        "Tot promoter for genre": {
            "Other genre promoters": 0,
            "Sport promoters": 1,
            "Music promoters": 10,
            "Art&Theatre promoters": 5,
            "Miscellaneous promoters:" 1
         }
         "State": "CA",
         "Tot promoter": 11
    },
    ...
]
```

## 4.   /globalStats
La quarta rotta è di tipo POST e restituisce lo stato con il numero minimo e massimo di eventi nel prossimo mese e lo stato con il numero minimo e massimo di promoter di eventi.
La risposta è un JSONArray di questo tipo:
```
[
    {
        "Stato con il numero massimo di eventi: ": "California",
        "Stato con il numero minimo di eventi: ": "Florida"
    },
    {
        "Stato con il numero massimo di promoter: ": "Massachussets",
        "Stato con il numero minimo di promoter: ": "Florida"
    }
]
```

## 5.   /statsPromoter
La quinta rotta è di tipo POST e richiede un body del seguente tipo:
```
{
    "Promoter": [
        {
          "ID": "000"
        },
        {
          "ID": "494"
        }
     ]
}
```
* *Promoter* è un JSONArray contenente gli ID di uno o più promoter rispetto ai quali si vuole fare la statistica. 

Per ogni promoter vengono restituiti il numero di eventi totali, il numero di eventi per ogni genere e il numero di stati in cui il promoter sponsorizza l'evento.
Possono essere generate le seguenti eccezioni:
* Se uno o più ID del promoter non sono validi viene generata un'eccezione del tipo **WrongIdPromoterException** che restituisce un messaggio di questo tipo:
```
Uno o più ID inseriti non corrisponde a nessun promoter tra quelli che sponsorizzano eventi.
```

* Se uno o più campi del body sono vuoti viene generata un'eccezione del tipo **EmptyFieldException** che resituisce un messaggio di questo tipo:
```
Uno o più campi sono vuoti.
```

Se la richiesta ha successo l'utente riceverà un JSONArray di questo tipo:
```
[
    {
        "Promoter": "000",
        "Tot State": 4,
        "Tot event": 18,
        "Tot event for genre": {
            "Other genre events": 0,
            "Music events": 1,
            "Art&Theatre events": 3,
            "Sport events": 1,
            "Miscellaneous events:" 13
          }
    },
    ...
]
```

## 6.   /filteredStats
La sesta rotta è di tipo POST e richiede un body del seguente tipo:
```
{
    "Promoter": [
       {
         "ID": "000"
       },
       {
         "ID": "494"
       }
     ],
     
    "Genre": [
       {
         "name genre": "Music"
       },
       {
         "name genre": "Sports"
       }
     ],
    "State": [
       {
         "state code": "MA"
       }
     ],
     
    "param": "events",
    "period": 1
}
```

* *Promoter* è il JSONArray che contiene gli Id dei promoter
* *Genre* è il JSONArray che contiene i nomi dei generi degli eventi
* *State* è il JSONObject che contiene lo state code 
* *param* indica il parametro su cui si vuole fare la statistica
* *period* indica i mesi

La rotta permette di filtrare solo sulla base di alcune delle voci precedenti, è sufficiente inserire almeno un campo e specificare il param.
Possono essere generate le seguenti eccezioni:
* Se viene inserito uno state code diverso da "CA", "FL", "MA", "NY viene generata un'eccezione del tipo **WrongStateCodeException** che restituisce un messaggio di questo tipo:
```
Lo statecode inserito non è tra quelli disponibili
Gli statecode disponibili sono:
CA(California)
FL(Florida)
MA(Massachussets)
NY(New York)
```
* Se uno o più campi del body sono vuoti viene generata un'eccezione del tipo **EmptyFieldException** che resituisce un messaggio di questo tipo:
```
Uno o più campi sono vuoti.
```

* Se viene inserito un periodo non compreso tra 1 e 6 viene generata un'eccezione del tipo **WrongPeriodException** che restituisce un messaggio di questo tipo:
```
Il periodo specificato non è valido
E' consentito inserire un valore tra 1 e 6 che rappresenta il nuumero di mesi.
```

* Se viene inserito un parametro non valido viene generata un'eccezione del tipo **ParamException** che restituisce un messaggio di questo tipo:
```
Il parametro specificato non è valido.
```

Se la richiesta ha successo. l'utente riceverà un JSONArray di questo tipo:
```
 [
    {
      "data": "2021-04-09",
      "name": "Stephen Marley",
      "genre": {
          "name genre": "Music",
          "ID genre": "KZFzniwnSyZfZ7v7nJ"
      },
      "place": {
          "state": "Massachusetts",
          "state code": "MA"
      },
      "promoters": [
          {
              "ID promoter": "494",
              "name promoter": "PROMOTED BY VENUE"
          }
      ],
      "url": "https://www.ticketmaster.com/postmodern-jukebox-beverly-massachusetts-10-14-2021/event/0100578212B89B61"
    },
    ...
 ]
```

### Autori
Progetto realizzato da:
- Alice Moretti
- Arianna Ronci
