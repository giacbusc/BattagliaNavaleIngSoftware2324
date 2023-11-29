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
- __0. Il progetto__
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
- __2. Struttura__
- __3. Modelling__

  
## Il progetto
Realizzazione in Java del gioco Battaglia Navale, realizzato in due differenti modalità: multiplayer online e giocatore singolo contro la CPU. 

### Guida rapida al gioco
#### Scopo del gioco
Lo scopo del gioco è localizzare tutte le unità della flotta navale nemica composta da diverse navi, in tutto 10: 
-1 portaerei da 4 caselle
-2 incrociatori da 3 caselle
-3 cacciatorpedinieri da 2 caselle 
-4 sottomarini da 1 casella.
Le navi non si possono toccare e possono essere disposte solo in orizzontale ed in verticale. 

#### Svolgimento del gioco
I giocatori devono posizionare le proprie navi sulla propria griglia, composta da 10 righe e da 10 colonne. Una volta posizionate le navi, il gioco procede a turni.
Il giocatore di turno "spara un colpo" selezionando sulla griglia dell'opponent la casella che si vuole colpire (la quale ha una specifica coordinata).
Nel momento in cui il colpo va a segno, cioè il gioocatore ha colpito oppure affondato una nave, il turno non viene passato ed il giocatore può tentare un altro colpo. 
Altrimenti, se il colpo non va a segno, il turno viene passato al giocatore avversario. 
A vincere è il primo giocatore che affonda tutte le navi dell'avversario.

## 1. Project plan
### 1.1 Background e Introduzione
Il nostro lavoro è iniziato con un'attenta analisi dei requisiti, che è stata il fulcro dei primi incontro. I requisiti non sono dettati da un finanziatore o da utenti esterni, ma sono stati identificati dai membri del team. <br>
Durante la prima riunione operativa si è deciso di sviluppare un software per un gioco, chiamato "Battaglia Navale", il quale verrà proposto agli utenti in duplice modalità: single player (contro la CPU) e multiplayer (con la connessione di più client tramite server). A seguito verrano illustrate nel dettaglio le funzionalità che verranno implementate e quelle che abbiamo considerato come possibili sviluppi futuri dell'app (1.2 modello MoSCoW) <br>
Ci siamo posti come deadline per il primo prototipo il 30/12/2023 mentre per la versione finale il 18/01/2024.<br>

### 1.2 Modello di processo
La fase iniziale dello sviluppo è stata dedicata alla definizione dei requisiti del software richiesti da parte del committente. Dal momento che la produzione del gioco non è stata commissionata bensì è stata un'idea spontanea, i requisiti sono stati definiti in base a quello che era ritenuto importante per il team e alla conoscenza del gioco dal quale il software ha origine.<br>
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

Per il processo di sviluppo del gioco si è deciso di sfruttare un approccio di tipo *agile*, infatti si è ritenuto che questo fosse più adatto per un progetto di questo tipo in cui la documentazione non è essenziale per l'utente finale, ossia i giocatori; di fatto anche il database associato non è molto rigoroso. <br>
Essendo un approccio agile: 
- L'organizzazione del team segue i principi della SWAT: i 4 membri hanno la stessa importanza e possono esprimere le loro opinioni. Le riunioni del gruppo sono brevi sessioni, prevalentemente di brain storming, o nelle quali si cerca una soluzione ad un problema rilevato durante i test, che sono stati svolti ripetutamente e a seguito di ogni cambiamento. La documentazione di queste riunioni  è minima, formata principalmente da appunti o da schemi sui quali basarci per le future implementazioni e modifiche da attuare. <br>
Qualora non fosse stato possibile incontrarsi dal vivo, le riunioni sono state eseguite attraverso videochiamate da remoto, tramite applicazioni apposite quali *Google Meet* o *Discord*. 
-	Abbiamo usato cicli di sviluppo piccoli ed incrementali; lo sviluppo viene pianificato man mano senza una pianificazione eccessivamente anticipata, data l'assenza di esperienze pregresse sulle quali basarci. Ogni volta che un membro apportava modifiche al progetto lo comunicava agli altri attraverso issues. 
- Il team ha seguito la filosofia dell'*extreme programming*, basandosi sul concetto di voler creare un software funzionante e di qualità, dando importanza: prima al portare a termine i punti di must have del progetto, poi al migliorarli fino all'ottenimento di un progetto che fosse ritenuto di buona qualità e infine alle possibili implementazioni da attuare nel tempo rimanente.
- Durante lo sviluppo, sopratttutto nei momenti iniziali, si è sfruttato spesso il *pair programming* come metodo di verifica: abbiamo lavorato in coppia  sullo stesso frammento di codice in modo tale da avere una verifica in tempo reale sul lavoro svolto. Questa pratica ha inoltre favorito lo scambio di idee e la ricerca di soluzioni migliori, fondamentale nei primi incontri.
- Durante lo sviluppo abbiamo fruttato la tecnica del *timeboxing* assegnando ad ogni compito una scadenza entro la quale doveva essere portato a termine. 

Come piattaforma di Version Control System si è scelto di sfruttare GitHub, in quanto è la piattaforma più utilizzata per lo sviluppo software, inoltre permette di sfruttare le funzionalità di issue tracking, la quale è stata fondamentale per la comunicazione tra i membri del team.


### 1.3 Organizzazione del progetto
Le persone coinvolte nella progettazione del gioco sono quelle che compongono il team. In seguito alla pubblicazione, i giocatori potranno prendere parte allo sviluppo suggerendo nuove funzionalità o segnalando bug. <br>
Il team si incontrerà settimanalmente, o di persona o attraverso videoconferenza, per fare il punto sullo stato dei lavori e pianificare le attività da svolgere per la settimana successiva, nelle modalità spiegate al punto 1.2 . <br>
I lavori sono stati assegnati in base alle capacità personali, così da suddividerseli al meglio e risultare più rapidi nello sviluppo; i diversi compiti sono stati così suddivisi: 
<table>
    <tr>
        <th></th>
        <th>Documentazione</th>
        <th>Progettazione</th>
        <th>Sviluppo back-end</th>
        <th>Sviluppo front-end</th>
        <th>Test</th>
    </tr>
    <tr>
    <th>Giacomo Buscaglia</th>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    </tr>
    <tr>
    <th>Luca Ciancio</th>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    </tr>
    <tr>
    <th>Federica Gervasoni</th>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    </tr>
    <tr>
    <th>Giulia Signori</th>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    </tr>
</table>

È bene sottolineare che, essendo il team composto da pochi membri, ogni componente ha partecipato attivamente ad ogni attivita seppur con differente obiettivo.

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
Si punta a sviluppare un software che rispetti i parametri di qualità indicati dal modello di McCall:
- Efficienza : per il funzionamento del software non è necessario l'uso di particolari risorse
- Correttezza : il software consegnato soddisfa i requisiti (identificati come i must have del progetto)
- Affidabilità : abbiamo testato più e più volte la nostra applicazione presentandola col minor numero di bug possibili
- Integrità : il software è reso quanto più sicuro possibile in quanto è prevalentemente autonomo 
- Usabilità : il prodotto è semplice da utilizzare infatti non sono richieste, oltre che a risorse avanzate, nemmeno competenze e conoscenze particolari
- Testabilità: Tutte le funzionalità incluse sono testabili in qualunque momento tramite test manuali 
- Flessibilità: il software si presenza incline all'implementazione di nuove funzionalità rendendolo molto flessibile.
- Portabilità: Il gioco è eseguibile sui più diffusi sistemi operativi desktop (Windows, Linux, MacOS) dotati di una connessione internet.
- Riusabilità: Sono state sfruttate e scritte librerie che possono essere riutilizzate in altri ambiti in quanto sono state sviluppate in modo indipendente dal contesto del gioco.

### 1.10 Package di lavoro
Il progetto del software è realizzato su maven e abbiamo gestito la sua struttura attraverso la configurazione di 3 moduli: client, server e common. L'avere questa suddivisione ci ha permesso di non avere un codice unico e comune a client e server, che sarebbe rimasto parzialmente inutilizzato (il codice del client risulta inutile al server e così il contrario). <br>
Il modulo del client è composto da quella parte di codice adibita alla visualizzazione del modello (ad esempio le classi *view* e *GUI*) e all'input/output da e verso l'utente. Tutta la parte del progetto che si occupa del gioco single player è implementata del modulo client in quanto si svolge in locale e non necessita collegamenti al server per funzionare. <br>
Il modulo del server invece ha la funzione di controller e gestisce il modello, il codice di questo modulo è quello che permette la connessione tra i client nel gioco

### 1.11 Risorse
Per l'utente non è richiesta una particolare dotazione se non un computer ed una connessione ad Internet funzionante.

### 1.12 Budget e pianificazione
Il team non ha come obiettivo quello di ottenere un contributo per il lavoro svolto, infatti come già precemente detto non è un progetto nato su commissione. Nel caso in futuro si presentasse la possibilità di ottenere una retribuzione, il ricavato sarà equalmente distribuito tra i membri. <br>
Per quanto riguarda il budget il gruppo ha messo a disposizione il proprio tempo, inoltre, considerato che le riunioni sono state svolte a Dalmine, presso l'Università, ed essendo che i membri del gruppo hanno domicilio in località distanti a questo luogo abbiamo considerato come budget anche la benzina impiegata al raggiungimento del luogo di lavoro.

### 1.13 Cambiamenti
I cambiamenti vengono discussi tra i membri del team e poi effettuati insieme, possono essere suggeriti anche dagli utenti finali attraverso i loro feedback.<br> Ad ogni versione rilasciata verranno rilasciati i documenti relativi. 

### 1.14 Consegna
Una volta pronta la versione finale, prima della pubblicazione, verrà inviata una segnalazione tramite issue a tutti i membri del team in modo tale da poter effettuare un'ultima revisione prima della pubblicazione.
