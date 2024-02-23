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
-1 portaerei da 4 caselle <br>
-2 incrociatori da 3 caselle <br>
-3 cacciatorpedinieri da 2 caselle <br>
-4 sottomarini da 1 casella. <br>
Le navi non si possono toccare e possono essere disposte solo in orizzontale ed in verticale. 

#### Svolgimento del gioco
I giocatori devono posizionare le proprie navi sulla propria griglia, composta da 10 righe e da 10 colonne. Una volta posizionate le navi, il gioco procede a turni.
Il giocatore di turno "spara un colpo" selezionando sulla griglia dell'opponent la casella che si vuole colpire (la quale ha una specifica coordinata).
Nel momento in cui il colpo va a segno, cioè il gioocatore ha colpito oppure affondato una nave, il turno non viene passato ed il giocatore può tentare un altro colpo. 
Altrimenti, se il colpo non va a segno, il turno viene passato al giocatore avversario. 
A vincere è il primo giocatore che affonda tutte le navi dell'avversario.

## 1. Project plan
https://github.com/buscst/BattagliaNavaleIngSoftware2324/blob/main/docs/Project%20Plan.md

## 2. Software lifecylce

Per il processo di sviluppo del gioco si è deciso di sfruttare un approccio di tipo *agile*, in particolare abbiamo deciso di seguire i principi del modello RAD, Rapid Application  Development. <br>
I principi sui quali ci siamo basati sono:
- L'organizzazione del team ha seguito i principi della SWAT: i 4 membri hanno avuto la stessa importanza e non ci sono state gerarchie all'interno del team, che ha lavorato in modo coeso e collaborando nelle diverse fasi dello sviluppo. <br>
- Le riunioni del gruppo sono state brevi sessioni, prevalentemente di brain storming, o nelle quali si cerca una soluzione ad un problema rilevato durante i test, che sono stati svolti ripetutamente e a seguito di ogni cambiamento. La documentazione di queste riunioni  è minima, formata principalmente da appunti o da schemi sui quali basarci per le future implementazioni e modifiche da attuare. <br>
Qualora non fosse stato possibile incontrarsi dal vivo, le riunioni sono state eseguite attraverso videochiamate da remoto, tramite applicazioni apposite quali *Google Meet* o *Discord*. 
-	Abbiamo usato cicli di sviluppo piccoli ed incrementali; lo sviluppo viene pianificato man mano senza una pianificazione eccessivamente anticipata, data l'assenza di esperienze pregresse sulle quali basarci. Ogni volta che un membro apportava modifiche al progetto lo comunicava agli altri membri, nel caso fosse necessario anche attraverso issues. <br>
- Il team ha preso anche spunto dai principi dell'*extreme programming*, basandosi sul concetto di voler creare un software funzionante e di qualità, dando importanza: prima al portare a termine i punti di must have del progetto, poi al migliorarli fino all'ottenimento di un progetto che fosse ritenuto di buona qualità e infine alle possibili implementazioni da attuare nel tempo rimanente.
- Durante lo sviluppo, sopratttutto nei momenti iniziali, si è sfruttato spesso il *pair programming* come metodo di verifica: abbiamo lavorato in coppia  sullo stesso frammento di codice in modo tale da avere una verifica in tempo reale sul lavoro svolto. Questa pratica ha inoltre favorito lo scambio di idee e la ricerca di soluzioni migliori, fondamentale nei primi incontri.
- Durante lo sviluppo abbiamo fruttato la tecnica del *timeboxing* tipica dello sviluppo RAD assegnando ad ogni compito una scadenza entro la quale doveva essere portato a termine.
- Sempre seguendo il principio di RAD abbiamo eseguito un triage dei requisiti secondo il metodo Moscow durante la fase iniziale. 

## 3. Configuration management
Tutto il lavoro svolto, che si tratti di documentazione o di codice, viene regolarmente salvato nel repository di GitHub.

### 3.1 Struttura del progetto
Il repository è strutturato nel seguente modo:
 - __branches__:
   - *main*: Contiene le versione alpha del codice
   - *beta*: Contiene la versione beta del codice (su questo branch sono state eseguite la maggior parte delle azioni)
 - __cartelle__:
    - *code*:
    - *docs*: Contiene la documentazione del progetto

Durante lo sviluppo del progetto sono stati creati diversi branch a partire dal master per implementare modifiche o per correggere errori quando riscontrati.<br>
Al termine dell'implementazione di tali modifiche, previa revisione di qualche altro componente delle modifiche eseguite attraverso una pull request, è stato effettuato il merge al branch master.

### 3.2 Issues e Kanban Board
Per la comunicazione e per l'aggiornamento sull'avanzamento dello sviluppo del lavoro il team ha utilizzato gli issues di GitHub. <br>
Come obiettivo del gruppo c'è stato l'impegno nell'utilizzo di questi come mezzo di comunicazione e di segnalazione degli avanzamenti o possibili problematiche; ogni membro ha la possibilità di creare nuovi issues nel caso lo trovasse necessario. <br>
Gli issues sono stati utilizzati anche all'interno della Kanban Board, utilizzata per monitorare le attività del team e come strumento di gestione. <br>
Per ogni issue della board è stata fatta una breve descrizione ed è stato designato un membro (o più) al quale assegnarlo.
Gli issues sono suddivise in 3 categorie principali:
- *task*: attività da svolgere
- *bug*: segnalazione di un bug
- *enhancement*: proposta di miglioramento
  
A seconda del loro avanzamento, i task possono trovarsi in diversi stati.
Gli stati sono i seguenti:
- *Todo*: L'attività non è ancora stata iniziata
- *In progress*: L'attività è in fase di sviluppo
- *Review*: L'attività è stata completata e deve essere testata
- *Done*:L'attività è stata completata e funziona correttamente
- *To fix*: L'attività è stata testata ma non funziona correttamente

## 4. People management
Abbiamo deciso di basarci su una struttura organizzativa non gerarchica, così da garantire una maggiore flessibilità e un più facile adattamento alle esigenze del progetto. <br>
La suddivisione del lavoro non è stata rigida, il team ha deciso di comune accordo come dividere e assegnare i vari task e, in caso di difficoltà, più membri si sono affiancati per risolvere la problematica in gruppo. Il vantaggio di questa scelta è stato anche di avere un clima più sereno e informale, utile per il benessere dei membri del team.<br>

Anche se non esite una distinzione tra i ruoli scritta, le diverse fasi del progetto sono state affrontate con più o meno partecipazione. Con la seguente tabella abbiamo riassunto le varie aree di competenza e come i membri del gruppo vi hanno partecipato. 

È bene sottolineare che, essendo il team composto da pochi membri, ogni componente ha partecipato attivamente ad ogni attivita seppur con differente obiettivo.

<table>
    <tr>
        <th></th>
        <th>Documentazione</th>
        <th>UML</th>
        <th>Progettazione</th>
       <th>Server</th>
       <th>Client</th>
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
    <td>✅</td>
    <td>✅</td>
    <td>✅</td>
    </tr>
</table>

## 5. Software quality
Come precedentemente specificato, il team si è prefissato di sviluppare un'applicazione che rispetti i parametri e gli attributi di qualità, considerando fondamentale lo sviluppo di un software di qualità che garantisca: 

- Correttezza : il software consegnato soddisfa i requisiti (identificati come i must have del progetto)
- Affidabilità : abbiamo testato più e più volte la nostra applicazione presentandola col minor numero di bug possibili, il software svolge le funzioni richieste in modo corretto
- Efficienza : per il funzionamento del software non è necessario l'uso di particolari risorse 
- Integrità : il software permette l'accesso ai soli utenti che hanno effettuato la registrazione e sono inseriti nel database
- Usabilità : il prodotto è semplice da utilizzare infatti non sono richieste, oltre che a risorse avanzate, nemmeno competenze e conoscenze particolari. L'interpretazione dell'output e l'immissione dell'input non dovrebbero richiedere sforzo particolare grazie alla presenza di un Tutorial iniziale che è possibile consultare ogni volta che si apre il menu principale

Rispetto alla manutenibilità e all'interazione con l'ambiente, ci aspettiamo che rispetti le seguenti qualità:
- Testabilità: le funzionalità incluse sono testabili in qualunque momento tramite test manuali implementati attraverso l'utilizzo di JUnit
- Manutenibilità: il software è stato sviluppato con l'obbiettivo di essere chiaro, cosi da permettere l'individualizzazione e la risoluzione di eventuali errori. A questo fine è stato utilizzato JavaDoc
- Flessibilità: il software si presenza incline all'implementazione di nuove funzionalità
- Riusabilità: Sono state sfruttate e scritte librerie che possono essere riutilizzate in altri ambiti in quanto sono state sviluppate in modo indipendente dal contesto del gioco.
- Portabilità: Il gioco è eseguibile sui più diffusi sistemi operativi desktop (Windows, Linux, MacOS) dotati di una connessione internet.
<br>

## 6. Requirement Engineering
I requisiti sono stati decisi in fase di elicitazione, durante la quale abbiamo utilizzato linguaggio formali e semiformali, cioè la descrizione in linguaggio naturale e la descrizione degli scenari attarverso diagrammmi UML (ad esempio lo use case lo abbiamo utilizzato per comprendere come l'utente potesse interagire col sistema). Abbiamo quindi redatto, a partire dai requisiti elicitati un documento identificato come Specifica dei Requisiti, il quale è stato alla base dell'attività di Validazione, necessaria per capire se stessimo costruendo nel modo giusto il nostro sistema. <br> 

### 6.1 Specifica dei Requisiti
#### 6.1.1 Schermata Iniziale e Accesso
Nonostante non presente nei requisiti iniziali è stata inserita una schermata che precede tutte le altre la quale è necessaria a fini statici per l'avviamento del gioco (quest'ultimo a seconda delle modalità che verranno utilizzate deve di fatti contenere indirizzi diversi).<br>
La schermata iniziale è quella per l'accesso, che si distingue in due modalità: attraverso Login o Registrazione. <br>
Un cliente che non è loggato può registrarsi inserendo il suo nome, il cognome, un username (il quale vogliamo che sia univoco) ed una password. Queste informazioni vengono salvate nel database e vengono poi verificate ogni volta che un utente esegue il login (con username e password). <br>
Il login ha successo solo nel momento in cui l'username inserito ha un riscontro nel database. <br>
L'username è l'identificativo del client nel gioco multiplayer, si eseguono controlli durante il collegamento al server per verificare che il gioco avvenga sempre tra username diversi. <br>

#### 6.1.2 Scelta Metodologia di Gioco
Una volta eseguito l'accesso si apre la schermata del menu principale, questa offre diverse opzioni: deve essere presente l'opzione di visualizzazione del Tutorial, così che l'utente inesperto possa apprendere ad utilizzare il gioco. <br>
Il gioco è solo orientato alla versione multiplayer, che può essere eseguita in due modi diversi:
- in locale su uno stesso pc
- su pc diversi connessi alla stessa rete locale
<br>
Questa scelta è anticipata da una schermata resa necessaria in quanto non è possibile a questo punto del gioco modificare gli indirizzi di avviamento.
<br>

#### 6.1.3 Posizionamento
Una volta eseguito l'accesso e decisa la modalità di gioco, si apre una schermata di attesa. Questa rimane visibile fino al momento in cui non si connette al server un secondo client (il quale deve avere un username diverso dal primo).<br>
Una volta connessi due client al primo dei due si apre la griglia di gioco, mentre il secondo rimane in attesa che il primo effettui il posizionamento delle barche nella griglia. <br>
Il posizionamento delle barche inizia con la scelta della barca, una volta selezionata questa viene tolta dal pannello sottostante alla griglia e va posizionata in un punto. <br> 
Non deve essere possibile selezionare due barche di fila in questo momento. <br>
Supponiamo che il primo click che si effettua sulla griglia inidichi un estremo della barca.<br>
Una volta iniziato il posizionamento della barca, se questa non è una barca da uno, viene calcolato dal server se la posizione scelta è corretta e viene mostrato sulla griglia come poter terminare il posizionamento. <br>
In questo momento sono stati gestite le varie eccezioni, così che non si possa cliccare qualcosa che non sia suggerito. <br>
In caso di errore di posizionamento la barca deve tornare visibile nella zona sottostante. <br>
Una volta terminato il posizionamento del primo client può iniziare il secondo, il quale viene risvegliato dal server. <br>

#### 6.1.4 Fase di Gioco
Terminati i posizionamenti delle barche, il turno deve passare a colui che si era connesso per primo. Questo client ora deve cercare di colpire una barca dell'avversario cliccando sulla griglia alla sua destra. <br>

## 7. Modelling

## 8. Software Architecture
Il software abbiamo deciso di basarlo sullo stile architetturale MVC (Model-View-Control):
-VIEW: la view è l'insieme delle gui che gestiscono l'interfaccia utente e attraverso le quali vengono inseriti gli input. Attraverso l'interfaccia l'utente durante il gioco manipola i dati. Tale manipolazione è realizzata del Control. (Ad esempio attraverso i click sulla caselle della griglia cambio lo stato della griglia.)
-MODEL: rappresenta i dati e gestisce le loro modifiche. Il model gestisce poi la visualizzazione dell'interfaccia utente attraverso il controller.
<br>
Esempio di utilizzo di MVC nel progetto:<br>
durante il gioco un utente prova a colpire una barca dell'avversario sulla griglia a destra. L'utente cliccando da un input al programma che viene elaborato attraverso le classi di logica del control. Queste comunicano con il socket e verificano lo stato della casella. Nel momento in cui la casella viene controllata possiamo dire che lo stato della cella cambia. Può essere parte di una barca e quindi identificare che la barca è stata colpita, oppure che la barca è stata affondata. Lo stato della casella è all'interno del model e viene modificato dal control. Una modifica del model porta ad una modifica della View infatti la casella a seconda del suo stato cambia colore.<br>
## 9. Software Design

## 10. Software Testing

## 11. Software Maintenance
Durante lo sviluppo il corretto funzionamento del codice è stato testato costantemente con test pratici di esecuzione del programma e delle sue funzionalità. Questo è stato effettuato inserendo controlli di stampa,debug con break point e attraverso test di JUnit. <br>
Nel momento in cui ci si è trovavi davanti un errore si è proceduto alla sua correzione, nel caso chiedendo anche agli altri membri un aiuto.<br>
In seguito alla revisione del bug e del codice errato si è poi ripetuto il test per verificarne l'esito. <br>
In alcuni casi, insieme alla correzione del bug è stato necessario fare anche delle modifiche più sostanziali al programma con un conseguente refactoring del codice per adattarlo alle nuove funzionalità. Queste modifiche essendo più invasive hanno spesso richiesto la creazione di un branch sul quale effettuare il lavoro. <br>
