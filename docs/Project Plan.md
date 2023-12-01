<p align="center">
  <img style="width: 200px;" src="resources/Logo.png">
   <img style="width: 550px;" src="resources/BATTAGLIA_NAVALE.png">
</p>

# Progetto di ingegneria del software
### Anno accademico 2023-2024

### Membri del team
- Buscaglia Giacomo, matricola 1078804
- Ciancio Luca, matricola 1079291
- Gervasoni Federica, matricola 1078966
- Signori Giulia, matricola 1078801

## Indice
- __1. Project plan__
  - 1.1 Introduzione
  - 1.2 Modello di processo
  - 1.3 Organizzazione del progetto
  - 1.4 Standards, linee guida e procedure
  - 1.5 Attività di gestione
  - 1.6 Rischi
  - 1.7 Membri
  - 1.8 Metodi e tecniche
  - 1.9 Garanzie di qualità
  - 1.10 Package di lavoro
  - 1.11 Risorse
  - 1.12 Budget e pianificazione
  - 1.13 Cambiamenti
  - 1.14 Consegna

## 1. Project plan
Il seguente project plan fa riferimento al progetto di Ingegneria del Software dei membri sopra citati, basato sullo sviluppo del gioco di Battaglia Navale in due differenti modalità: multiplayer e single player. 
Il gioco consiste nel posizionare le proprie navi nella griglia e successivamente cercare di colpire le navi dell'avversario. <br>
Ogni giocatore ha a disposizione un turno nel quale poter effettuare la propria mossa; se il colpo andasse a segno il turno non viene passato. <br>
Il giocatore che vince è colui che affonda per primo tutte le navi del suo avversario.

### 1.1 Background e Introduzione
Il nostro lavoro è iniziato con un'attenta analisi dei requisiti, che è stata il fulcro dei primi incontro. I requisiti non sono dettati da un finanziatore o da utenti esterni, ma sono stati identificati dai membri del team. <br>
Durante la prima riunione operativa si è deciso di sviluppare un software per un gioco, chiamato "Battaglia Navale", il quale verrà proposto agli utenti in duplice modalità: single player (contro la CPU) e multiplayer (con la connessione di più client tramite server). A seguito verrano illustrate nel dettaglio le funzionalità che verranno implementate e quelle che abbiamo considerato come possibili sviluppi futuri dell'app (1.2 modello MoSCoW) <br>
Ci siamo posti come deadline per il primo prototipo il 30/12/2023 mentre per la versione finale il 18/01/2024.<br>

### 1.2 Modello di processo
La fase iniziale dello sviluppo è stata dedicata alla definizione dei requisiti del software richiesti da parte del committente. Dal momento che la produzione del gioco non è stata commissionata bensì è stata un'idea spontanea, i requisiti sono stati definiti in base a quello che era ritenuto importante per il team e alla conoscenza del gioco dal quale il software ha origine.<br>
La fase di lavoro dedicata all'iongegneria dei requisiti ha occupato la maggior parte delle risorse in quanto su questo abbiamo basato tutto il lavoro eseguito successivamente. <br> 
In particolare ci siamo soffermati sulla *fattiblità*, valutata sia in termini di tempo, che di conoscenze, in quanto essendo un progetto potenzialmente aperto a molte implementazioni, abbiamo ritenuto importanti fissare da subito quali implementazioni potessero risultare infattibili (e quindi lasciate ad un possibile futuro, in caso chiedendo per il loro sviluppo una consulenza da parte di terzi). <br>
Abbiamo definito i requisiti sulla base del modello MoSCoW come segue:
<table>
    <tr>
        <th>MUST HAVE</th>
        <th>SHOULD HAVE</th>
        <th>COULD HAVE</th>
        <th>WON'T</th>
    </tr>
    <tr>
    <td>Gioco Single player vs CPU</td>
    <td>Classifica generale utenti</td>
    <td>GUI implementata con animazioni</td>
    <td>Applicazione Mobile</td>
    </tr>
     <tr>
    <td>Gioco Multiplayer</td>
    <td>Torneo Multiplayer</td>
    <td>Cambio della grafica delle Navi tramite un sistema 'shop'</td>
    <td>Web App</td>
    </tr>
    <tr>
    <td>Implementazione GUI</td>
    <td>Implementazione suono</td>
    <td>Livelli di difficoltà</td>
    <td>Versione alternativa del gioco con potenziamenti per i colpi</td>
    </tr>
    <tr>
    <td>Login e Registrazione Utente</td>
    <td>Gioco MultiLingua</td>
    <td></td>
    <td></td>
    </tr>
    <tr>
    <td>Utenti connessi Wireless</td>
    <td>Tutorial del gioco</td> 
        <td></td>
    <td></td>
    </tr>
</table>

#### Progettazione
La progettazione è stata fondamentale per noi nello sviluppo del software infatti è stata il fondamento sul quale ci siamo basati per la creazione degli issues, per la suddivisione del lavoro, per la pianificazione dei timebox e per la gestione delle varie problematiche affrontate nel corso della programmazione. <br>
La stesura dell'architettura ha quindi rappresentato il nostro vero punto di inizio ed il nostro punto di riferimento. 

#### Implementazione e Test 
Successivamente alla stesura del nostro progetto di base, siamo passati alla fase successiva che sarebbe sbagliato riconoscere come pura fase di implementazione, infatti i test sono stati eseguiti in parallelo, in modo costante, ritenendo necessario avere sempre un programma il quanto più corretto possibile. <br> 

Al termine di questa fase viene presentata una prima versione di gioco detta "alpha", dotata di tutte le funzionalità di base necessarie che erano state concordate.

#### Manutenzione 
Questa fase si occupa della correzione di eventuali bug che si sono presentati dopo la consegna del software. <br>
Spesso infatti è solo con l'uso effettivo del sistema che si verificano errori e che nascono richieste di miglioramenti.
Come piattaforma di Version Control System si è scelto di sfruttare GitHub che ci ha permesso di sfruttare le funzionalità di:
-project con issue tracking, funzionalità la quale è stata fondamentale per la comunicazione tra i membri del team; è attraverso la creazione della Kanban board sche abbiamo gestito i vari issues e le varie attività da portare a termine
-branch: attraverso la loro creazione abbiamo potuto gestire l'implementazione di diverse parti di codice in modo simultaneo e parallelo alla versione alpha del codice alla quale le modifiche sono state applicate solo su revisione di altri membri (anche questo è stato gestito con github)


### 1.3 Organizzazione del progetto
Le persone coinvolte nella progettazione del gioco sono quelle che compongono il team. In seguito alla pubblicazione, i giocatori potranno prendere parte allo sviluppo suggerendo nuove funzionalità o segnalando bug. <br>
Il team si incontrerà settimanalmente, o di persona o attraverso videoconferenza, per fare il punto sullo stato dei lavori e pianificare le attività da svolgere per la settimana successiva, nelle modalità spiegate al punto 1.2 . <br>
I lavori sono stati assegnati in base alle capacità personali, così da suddividerseli al meglio e risultare più rapidi nello sviluppo.


### 1.4 Standards, linee guida e procedure
Il gioco è composto da due applicativi separati:

Server: Gestisce l'andamento del gioco multiplayer effettuando la connesione di più client (non possiede un'interfaccia grafica) <br>
Client: Gestisce l'interfaccia grafica e l'interazione con l'utente, sia nelle fasi iniziali (registrazione, login, scelta della modalità di gioco) che nello svolgimento del gioco in versione single player <br>
Sia server che client sono scritti in Java, in un progetto maven, e per la comunicazione utilizzano H2Database per quanto riguarda la fase di registrazione e di login ed i socket per la gestione della connessione client e server nelle fasi del gioco. 
Per la grafica si è deciso di utilizzare la libreria JavaSwing ed è stata sviluppata interamente con linguaggio Java.

### 1.5 Attività di gestione
Lo sviluppo è stato organizzato seguendo i metodi agili, secondo la filosofia dell'*extreme programming* in quanto permette di migliorare la qualità del codice e approcciarsi al cambiamento dei requisiti in modo più responsivo.<br>
Il team ha lavorato sempre a stretto contatto con scambio continuo di idee e con confronti giornalieri, così da proseguire coesi e coerenti nonostante il lavoro sia stato indivuale in diverse fasi del progetto. <br>
Nonostante i primi incontri di stesura dei requisiti e di avvio del lavoro siano state delle sessioni piuttosto lunghe, gli incontri settimanali successivi sono stati di breve durata, durante i quali sono state effettuate delle sessioni di *brain storming* per trovare nuove idee o cercare soluzioni a problemi riscontrati. Nei meeting, organizzati solitamente nel momento in cui scadevano i timebox assegnati (tempi nei quali il team si prefissava degli obiettivi da raggiungere), ci si è occupati della verifica del lavoro eseguito. La fase di test infatti è stata costante e ci ha permesso di tener monitorati gli sviluppi e le modifiche che ogni membro ha apportato. <br>
Durante i meeting abbiamo, di volta in volta, deciso i passi successivi e identificato in quanto tempo sarebbero dovuti essere presentati. 

### 1.6 Rischi
I principali rischi che si possono incontrare durante lo sviluppo di questo gioco con le modalità sopra descritte sono principalmente 2:
1. La connessione e i problemi ad essa relativi potrebbero influenzare l'esperienza dei giocatori
2. Il gioco potrebbe non riscontrare l'interesse sperato da parte degli utenti o di potenziali investitori. In tal caso il tempo speso per lo sviluppo non potrà essere pagato. Inoltre gli utenti potrebbero perdere interesse a favore di altri giochi della concorrenza.

### 1.7 Membri
Non vi è stata una componente di aiuto esterno nella progettazione e nello sviluppo del software, i sopracitati componenti del gruppo sono stati gli unici partecipanti.

### 1.8 Metodi e tecniche
La prima fase della progettazione è stata dedicata alla creazione di use case diagrams in modo da comprendere le funzionalità necessarie per la nostra applicazione.


//immagine del diagramma 



L'attore principale è l'utente che può interagire in vari modi con l'applicazione. Nel caso in cui voglia avviare una partita, per prima cosa deve fare richiesta al server (attore passivo) di creare la partita. Una volta effettuata la richiesta il client resta in attesa che al server si connetta un altro client, infatti affinché la partita possa essere avviata è necessaria la partecipazione di un altro giocatore. Il gioco è composto da due turni che si alternano tra loro: un giocatore fa la sua mossa, che il server trasmette all'avversario che era in attesa. A quel punto possono accadere due cose: o il turno passa all'avversario che quindi può eseguire la sua mossa, oppure il turno resta in mano al giocatore, se questo ha portato a termine una mossa efficace. Ciò che accade viene comunicato ai giocatori tramite una scritta a video. Al termine del gioco, dopo essere stata mostrato il vincitore, l'utente può decidere se giocare nuovamente oppure uscire dalla partita ed essere disconnesso dal server.

### 1.9 Garanzie di qualità
Per lo sviluppo di un software di qualità abbiamo puntato a rispettare i parametri indicati dal modello di McCall. <br>
Inoltre, per garantire la sicurezza degli utenti sono state usate librerie moderne e molto diffuse, aggiornate frequentemente e poco inclini ad avere vulnerabilità. <br>
Il fatto di avere due moduli separati per la parte client e server permette di avere due sistemi aggiornabili separatamente, in modo da poter rendere più reattiva la fase di aggiornamento e miglioramento del software. <br>
  

### 1.10 Package di lavoro
Il progetto del software è realizzato su maven e abbiamo gestito la sua struttura attraverso la configurazione di 3 moduli: client, server e common. L'avere questa suddivisione ci ha permesso di non avere un codice unico e comune a client e server, che sarebbe rimasto parzialmente inutilizzato (il codice del client risulta inutile al server e così il contrario). <br>
Il modulo del client è composto da quella parte di codice adibita alla visualizzazione del modello (ad esempio le classi *view* e *GUI*) e all'input/output da e verso l'utente. Tutta la parte del progetto che si occupa del gioco single player è implementata del modulo client in quanto si svolge in locale e non necessita collegamenti al server per funzionare. <br>
Il modulo del server invece ha la funzione di controller e gestisce il modello, il codice di questo modulo è quello che permette la connessione tra i client nel gioco

### 1.11 Risorse
Per quanto riguarda l'utente non è richiesta una particolare dotazione se non un computer ed una connessione ad Internet funzionante. <br>
Invece, parlando delle risorse utilizzate dal team si è utilizzato:
- Eclipse come ambiente di sviluppo Eclipse
- Github come sistema per il Configuration Management
- StarUML come software per la creazione dei diagrammi UML
- CodeTogether per la programmazione in parallelo
- H2Database per la creazione del database
- server
- Structure101 per la gestione della qualità della struttura packages

### 1.12 Budget e pianificazione
Il team non ha come obiettivo quello di ottenere un contributo per il lavoro svolto, infatti come già precemente detto non è un progetto nato su commissione. Nel caso in futuro si presentasse la possibilità di ottenere una retribuzione, il ricavato sarà equalmente distribuito tra i membri. <br>
Per quanto riguarda il budget il gruppo ha messo a disposizione il proprio tempo, inoltre, considerato che le riunioni sono state svolte a Dalmine, presso l'Università, ed essendo che i membri del gruppo hanno domicilio in località distanti a questo luogo abbiamo considerato come budget anche la benzina impiegata al raggiungimento del luogo di lavoro.

### 1.13 Cambiamenti
La manutenzione che ci proponiamo di effettuare in futuro comprenderà tutte e quattro le attività previste:
-correzione di errori
-adattamento ai cambiamenti dell'ambiente 
.perfezione del software sulla base ad esempio delle richieste fornite dagli utenti oppure per la volontà dei membri di aumentare le prestazioni del sistema
-prevenzione per l'aumento della manutentibilità del sistema (ad esempio con l'aggiornamento continuo della documentazione). <br>
I cambiamenti verrano discussi tra i membri del team e poi effettuati insieme.<br>
Ad ogni versione rilasciata verranno rilasciati i documenti relativi. <br>

### 1.14 Consegna
Una volta pronta la versione finale, prima della pubblicazione, verrà inviata una segnalazione tramite issue a tutti i membri del team in modo tale da poter effettuare un'ultima revisione prima della pubblicazione.
