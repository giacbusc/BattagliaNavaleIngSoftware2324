<p align="center">
  <img style="width: 200px;" src="resources/Logo.jpeg">
   <img style="width: 550px;" src="resources/BATTAGLIA_NAVALE.jpeg">
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
-1 portaerei lunga 4 caselle
-2 incrociatori da 3 caselle
-3 cacciatorpedinieri da 2 caselle 
-4 sottomarini da 1 casella.
Le navi non si possono toccare e possono essere disposte solo in orizzontale ed in verticale. 

#### Svolgimento del gioco
I giocatori devono posizionare le proprie navi sulla propria griglia, composta da 10 righe e da 10 colonne. Una volta posizionate le navi, il gioco procede a turni.
Il giocatore di turno "spara un colpo" dichiarando una casella attraverso le sue coordinate, indicate da una lettera ed un numero.
Nel momento in cui il colpo va a segno, cioè il gioocatore ha colpito oppure affondato una nave, il turno non viene passato ed il giocatore può tentare un altro colpo. 
Altrimenti, se il colpo non va a segno, il turno viene passato al giocatore avversario. 
A vincere è il primo giocatore che affonda tutte le navi dell'avversario.

## 1. Project plan
### 1.1 Background e Introduzione
Il nostro lavoro è iniziato con un'attenta analisi dei requisiti, che è stata il fulcro del primo incontro. I requisiti non sono dettati da un finanziatore o da utenti esterni, ma sono stati identificati dai membri del team. <br>
Durante la prima riunione operativa si è deciso di sviluppare un softsware per un gioco, chiamato "Battaglia Navale", il quale verrà proposto agli utenti in duplice modalità: single player (contro la CPU) e multiplayer, con la connessione di più client tramite server. A seguito verrano illustrate nel dettaglio le funzionalità che verranno implementate e quelle che abbiamo considerato come possibili sviluppi futuri dell'app (1.2 modello MoSCoW) <br>
Ci siamo posti come deadline per il primo prototipo il 30/12/2023 mentre per la versione finale il 18/01/2024.<br>

### 1.2 Modello di processo
La fase iniziale dello sviluppo è stata dedicata alla definizione dei requisiti del software richiesti da parte del committente. Dal momento che la produzione del gioco non è stata commissionata bensì è stata un'idea spontanea, i requisiti sono stati definiti in base a quello che era ritenuto importante per il team e alla conoscenza del gioco di ruolo dal quale il software ha origine.<br>
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

Per il processo di sviluppo del gioco si è deciso di sfruttare un approccio di tipo *agile*, infatti, si è ritenuto che questo fosse più adatto per un progetto di questo tipo in cui la documentazione non è essenziale per l'utente finale, ossia i giocatori, di fatto anche il database associato non è molto rigoroso. <br>
Essendo un approccio agile: 
- L'organizzazione del team segue i principi della SWAT: i 4 membri hanno la stessa importanza e possono esprimere le loro opinioni. Le riunioni del gruppo sono brevi sessioni, prevalentemente di brain storming, o nelle quali si cerca una soluzione ad un problema rilevato durante i test, che sono stati svolti ripetutamente e a seguito di ogni cambiamento. Di queste riunioni la documentazione è minima, formata principalmente da appunti o da schemi sui quali basarci per le future implementazioni e modifiche da attuare. <br>
Qualora non fosse stato possibile incontrarsi dal vivo, le riunioni sono state eseguite attraverso videochiamate da remoto, tramite applicazioni apposite quali *Google Meet* o *Discord*. 
-	Abbiamo usato cicli di sviluppo piccoli ed incrementali; lo sviluppo viene pianificato man mano senza una pianificazione eccessivamente anticipata, data l'assenza di esperienze pregresse sulle quali basarci. Ogni volta che un membro apportava modifiche al progetto lo comunicava con gli altri, attraverso issues. 
- Il team ha seguito la filosofia dell'*extreme programming*, basandosi sul concetto di voler creare un software funzionante e di qualità, dando importanza prima al portare a termine i punti di must have del progetto, poi al migliorarli fino all'ottenimento di un progetto che ritenessimon di buona qualità e infine abbiamo pensato a cosa implementare nel tempo rimasto.
- Durante lo sviluppo, sopratttutto nei momenti iniziali, si è sfruttato spesso il *pair programming* come metodo di verifica: abbiamo lavorato in coppia  sullo stesso frammento di codice in modo tale da avere una verifica in tempo reale sul lavoro svolto. Questa pratica ha inoltre favorito lo scambio di idee e la ricerca di soluzioni migliori, fondamentale nei primi incontri.
- Durante lo sviluppo abbiamo fruttato la tecnica del *timeboxing* assegnando ad ogni compito una scadenza entro la quale doveva essere portato a termine. 

Come piattaforma di Version Control System si è scelto di sfruttare GitHub, in quanto è la piattaforma più utilizzata per lo sviluppo software, inoltre permette di sfruttare le funzionalità di issue tracking, la quale è stata fondamentale per la comunicazione tra in membri del team.


### 1.3 Organizzazione del progetto
Le persone coinvolte nella progettazione del gioco sono quelle che compongono il team. In seguito alla pubblicazione, i giocatori potranno prendere parte allo sviluppo suggerendo nuove funzionalità o segnalando bug. <br>
Il team si incontrerà settimanalmente, o di persona o attraverso videoconferenza, per fare il punto sullo stato dei lavori e pianificare le attività da svolgere per la settimana successiva, nelle modalità spiegate al punto 1.2 . <br>
I lavori sono stati assegnati in base alle capacità personali, così da suddividersi al meglio il lavoro e risultare più rapidi nello sviluppo, i diversi compiti sono stati così suddivisi: 
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

### 1.4 Standards, linee guida e procedure

### 1.5 Attività di gestione
Si è deciso di organizzare lo sviluppo seguando i metodi agili, secondo la filosofia dell'*extreme programming* in quanto permette di migliorare la qualità del codice e approcciarsi al cambiamento dei requisiti in modo più responsivo.<br>
Il team ha lavorato sempre a stretto contatto con scambio continuo di idee e con confronti giornalieri, così da proseguire coesi e coerenti nonostante il lavoro sia stato indivuale in diverse fasi del progetto. <br>
Nonostante i primi incontri di stesura dei requisiti, e di avvio del lavoro siano state delle sessioni piuttosto lunghe, gli incontri settimanali successivi sono stati di breve durata, durante i quali possono essere effettuate delle sessioni di *brain storming* per trovare nuove idee o cercare soluzioni a problemi riscontrati. Nei meeting, organizzati solitamente nel momento in cui scadevano i timebox assegnati, ci si è occupati della verifica del lavoro eseguito. La fase di test infatti è stata costante e ci ha permesso di tener monitorati gli sviluppi e le modifiche che ogni membro ha apportato. <br>
Durante i meeting abbiamo, dsi volta in volta, deciso i passi successivi e identificato in quanto tempo sarebbero dovuti essere presentati. 

### 1.6 Rischi
I principali rischi che si possono incontrare durante lo sviluppo di questo gioco con le modalità sopra descritte sono principalmente 2:
1. La connessione e i problemi ad essa relativi potrebbero influenzare l'esperienza dei giocatori
2. Il gioco potrebbe non riscontrare l'interesse sperato da parte degli utenti o di potenziali investitori. In tal caso il tempo speso per lo sviluppo non potrà essere pagato. Inoltre gli utenti potrebbero perdere interesse a favore di altri giochi della concorrenza.

### 1.7 Membri
Non vi è stata una componente di aiuto esterno nella progettazione e nello sviluppo del software, i sopracitati componenti del gruppo sono stati gli unici membri.

### 1.8 Metodi e tecniche


### 1.9 Garanzie di qualità
Si punta a sviluppare un software che rispetti i parametri di qualità indicati dal modello di McCall:
- Correttezza
- Affidabilità
- Robustezza
- Integrità
- Usabilità


Il software lato client è predisposto per essere aggiornato in maniera automatica in fase di avvio (o attraverso le piattaforme di redistribuzione) in modo da garantire che l'utente abbia sempre la versione più recente del software nella quale solitamente sono corretti i bug e rimosse eventuali falle nella sicurezza.<br>
La separazione tra client e server permette di avere due sistemi aggiornabili separatamente, in modo da poter rendere più reattiva la fase di aggiornamento e miglioramento del software.
Oltre a rispettare i *quality assurance* indicati dal modello McCall, si vuole avere un software sicuro che non metta a rischio la sicurezza e la privacy degli utenti. Per questo motivo si è deciso di non raccogliere e registrare alcuna informazione personale degli utenti (fatta eccezione per l'indirizzo IP necessario per la comunicazione che comunque non verrà mai salvato).<br>

### 1.10 Package di lavoro


### 1.11 Risorse
Per l'utente non è richiesta una particolare dotazione se non un computer ed una connessione ad Internet funzionante.

### 1.12 Budget e pianificazione


### 1.13 Cambiamenti
I cambiamenti vengono discussi tra i membri del team e poi effettuati insieme, possono essere suggeriti anche dagli utenti finali attraverso i loro feedback, oltre che dai membri stessi. Ad ogni versione rilasciata verranno rilasciati i documenti relativi. 

### 1.14 Consegna
Una volta pronta la versione finale, prima della pubblicazione, verrà inviata una segnalazione tramite issue a tutti i membri del team in modo tale da poter effettuare un'ultima revisione prima della pubblicazione.
