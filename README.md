# ProgettoOOP

## Scaletta
1. [Introduzione](#intro)
2. [Diagrammi UML](#uml)
3. [Rotte](#rotte)
4. [Test](#test)
5. [Autori](#autori)

<a name="intro"></a>
## Introduzione
L'applicazione TicketMasterUS permette all'utente di ottenere informazioni relative agli eventi che avranno luogo negli USA, in particolare nelle città California, Florida, Massachusetts e New York. L'applicazione fornisce dati relativi agli eventi e ai promoter che li sponsorizzano, inoltre l'utente può visualizzare le statistiche su questi dati, con la possibilità di applicare dei filtri al momento della ricerca.

<a name="uml"></a>
## Diagrammi UML

### Use Case Diagram
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Use%20Case%20Diagram.jpg)

### Class Diagram
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Class%20Diagram.jpg)

* it.univpm.TicketMasterUS.Models
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Models.jpg)

* it.univpm.TicketMasterUS.Controller
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Controller.jpg)

* it.univpm.TicketMasterUS.Utils
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Utils.jpg)

* it.univpm.TicketMasterUS.Exception
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Exception.jpg)

### Sequence Diagram
![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/UML/Sequence%20Diagram.jpg)

<a name="rotte"></a>
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

<a name=1></a>
## 1.   /events
La prima rotta è di tipo GET e restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano le informazioni sugli eventi. Nel caso in cui non venga specificato lo state code dall'utente, l'applicazione restituisce i dati relativi a New York.
La rotta può generare un'eccezione **WrongStateCodeException** se non viene inserito uno degli state code tra quelli consentiti: 
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/getEvents.jpeg)

<a name=2></a>
## 2.   /promoters
La seconda rotta è di tipo GET e  restituisce un JSONArray di questo tipo, cioè contenente i JSONObject che riportano ID e nome del promoter che sponsorizzano gli eventi. Nel caso in cui non venga specificato lo state code dall'utente, l'applicazione restituisce i dati relativi a New York.
La rotta può generare un'eccezione **WrongStateCodeException** se non viene inserito uno degli state code tra quelli consentiti: 
1. CA per la California
2. FL per la Florida
3. MA per il Massachusetts
4. NY per New York

![alt text](https://github.com/AliceMoretti00/ProgettoOOP/blob/main/getPromoters.jpeg)

<a name=3></a>
## 3.   /statsState
La terza rotta è di tipo POST e restituisce le statistiche per ogni stato, in particolare restituisce il numero totale di promoter che sponsorizzano eventi in quello stato e il numero di promoter raggruppati per il genere di eventi che sponsorizzano.
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

<a name=4></a>
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

<a name=5></a>
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

Per ogni promoter inserito vengono restituiti il numero di eventi totali, il numero di eventi per ogni genere e il numero di stati in cui il promoter sponsorizza un evento.
Possono essere generate le seguenti eccezioni:
* Se uno o più ID del promoter non sono validi viene generata un'eccezione del tipo **WrongIdPromoterException** che restituisce un messaggio di questo tipo:
```
Uno o più ID inseriti non corrisponde a nessun promoter tra quelli che sponsorizzano eventi.
```

* Se il campo del body viene lasciato vuoto viene generata un'eccezione del tipo **EmptyFieldException** che resituisce un messaggio di questo tipo:
```
Devi inserire almeno un ID.
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

<a name=6></a>
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

* *Promoter* è il JSONArray che contiene gli ID dei promoter
* *Genre* è il JSONArray che contiene i nomi dei generi degli eventi
* *State* è il JSONObject che contiene gli state code 
* *param* rappresenta l'informazione che si vuole filtrare
* *period* rappresenta il numero di mesi 

La rotta permette di filtrare sulla base di una o tutte le voci precedenti; in ogni caso è necessario inserire almeno un campo e specificare il param.
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
* In particolare se il campo param non viene inserito viene generata un'eccezione del tipo **EmptyFieldException** che resituisce un messaggio di questo tipo:
```
Il parametro e' un campo obbligatorio.
```

* Se viene inserito un periodo non compreso tra 1 e 6 viene generata un'eccezione del tipo **WrongPeriodException** che restituisce un messaggio di questo tipo:
```
Il periodo specificato non è valido
E' consentito inserire un valore tra 1 e 6 che rappresenta il nuumero di mesi.
```

* Le informazioni che è possibile filtrare sono l'elenco degli eventi e le statistiche; nel primo caso va compilato il campo param con la stringa "event", nel secondo caso con una delle stringhe "stastatsState" o "statsPromoter".
Se il campo param viene compilato con una stringa diversa da queste viene generata un'eccezione del tipo **ParamException** che restituisce un messaggio di questo tipo:
```
Il parametro specificato non e' tra quelli disponibili.
```

Se la richiesta ha successo, l'utente riceverà un JSONArray che, in base al valore specificato come paramentro, restituirà l'elenco degli eventi filtrati o le statistiche filtrate.

<a name="test"></a>
## Test
Il programma prevede anche una sezione di testing:
* Test relativi al package Service:
    * Test 1: verifica la corretta generazione dell'eccezione WrongStateCodeException
    * Test 2: verifica che il metodo eventToJSONObject effettui correttamente il parsing
* Test relativi al package Statistic: 
    * Test 3: verifica il corretto funzionamento del metodo calcoloTot

<a name="autori"></a>
### Autori
Progetto realizzato da:
- Alice Moretti
- Arianna Ronci
