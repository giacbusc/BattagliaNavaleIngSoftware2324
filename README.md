# BattagliaNavaleIngSoftware2324
<p align="center">
  <img style="width: 200px;" src="docs/resources/Logo.png">
   <img style="width: 550px;" src="docs/resources/BATTAGLIA_NAVALE.png">
</p>

## Progetto di ingegneria del software
### Anno accademico 2023-2024

### Progetto a cura di:
[Buscaglia Giacomo, matricola 1078804](https://github.com/buscst)<br>
[Ciancio Luca, matricola 1079291](https://github.com/LucaCiancio)<br>
[Gervasoni Federica, matricola 1078966](https://github.com/fgervasoni7) <br>
[Signori Giulia, matricola 1078801](https://github.com/giessse)<br>

Il software che abbiamo sviluppato è un'implementazione del noto gioco "Battaglia Navale" attraverso interfacce grafiche (GUI). Il gioco sviluppato è un gioco distribuito quindi che implementa il meccanismo dei Socket (libreria ZeroMQ) per permettere la comunicazione tra diversi client. Il gioco permette di essere testato e di giocare anche in locale su un unico pc. <br>

### Project Plan
Il [project plan](https://github.com/buscst/BattagliaNavaleIngSoftware2324/blob/main/docs/Project%20Plan.md) è stato da noi redatto prima della realizzazione del software, lo abbiamo infatti utilizzato come linea guida nello sviluppo iniziale del progetto.Lo abbiamo successivamente modificato in quanto come era prevedibile nel corso della progettazione e dello sviluppo ci siamo imbattuti in nuove problematiche che ci hanno portato a rivedere i nostri piani iniziali.<br>
Il project Plan del progetto si trovva nella cartella docs della repository.<br>

### Documentazione
La documentazione del progetto è racchiusa in unico file chiamato [Documentazione](https://github.com/buscst/BattagliaNavaleIngSoftware2324/blob/main/docs/Documentazione.md).<br>
All'interno della documentazione si trova:<br>
<ol>
<li>la <a href="https://github.com/buscst/BattagliaNavaleIngSoftware2324/blob/main/docs/Documentazione.md#6-requirement-engineering"> specifica dei requisiti </a></li>
<li>la descrizionde del modello di sviluppo utilizzata</li>
<li>la specifica della gestione della configurazione</li>
<li>la specifica dell'organizzazione del team e delle varie fasi dello sviluppo</li>
<li>la descrizione della struttura e dei design</li>
<li>la rappresentazione tramite modelli del software e delle sue funzionalità</li>
<li>il piano di test</li>
<li>una specifica per la menutenzione del software</li>
</ol>

### UML
Per una documentazione migliore e maggiormente esplicativa delle diverse funzionalità del software abbiamo realizzato vari diagrammi UML, questi si trovano sia all'interno della [documentazione](https://github.com/buscst/BattagliaNavaleIngSoftware2324/blob/main/docs/Documentazione.md#7-modelling) stessa ma anche in una loro cartella all'interno della cartella UML nei documenti della repository.<br>

### Installazione e Avviamento
Come prima cosa vanno scaricati i due jar, essenziali per il funzionamento dell'applicazione. 
Il file "Server.jar" servirà per avviare il server di gioco. 
Il file "Player.jar" servirà per avviare il gioco lato client.

#### Avvio del gioco su un unico pc (server locale)
Come prima cosa avviare il  file "[Server.jar](https://github.com/giacbusc/BattagliaNavaleIngSoftware2324/blob/main/BattagliaNavaleServer/Server.jar)" (esso dovrà essere aperto una sola volta). All'apertura della schermata di scelta dell'indirizzo selezionare "Avviare server in locale". 
Fatto questo i player potranno connettersi. Avviare quindi per due volte il file "Player.jar" così da avere due giocatori. Una volta registrato/loggato verrà visualizzato il menù principale dove dovrà essere selezionata la modalità di gioco "Gioca su un solo pc". 
Fatto questo apparirà la griglia del primo player e il gioco avrà inizio.

#### Avvio del gioco su più pc (server tcp)
Come prima cosa avviare il file "[Server.jar](https://github.com/giacbusc/BattagliaNavaleIngSoftware2324/blob/main/BattagliaNavaleServer/Server.jar)". Esso dovrà essere avviato solo una volta e solamente su una macchina (questa macchina non dovrà essere necessariamente anche un player seppur possibile senza alcun tipo di problema).
L'indirizzo da mettere, per comodità, potrà essere l'IP privato del dispositivo che vogliamo funga da server (seguito anche dalla porta, per comodità utilizzare 5555).
Di conseguenza si ricorda che tutti i pc (sia server che player) dovranno essere connessi alla stessa rete internet!

Il file "[Player.jar](https://github.com/giacbusc/BattagliaNavaleIngSoftware2324/blob/main/BattagliaNavale/BattagliaNavale.jar)" invece dovrà essere aperto sui pc interessati a giocare alla battaglia navale. Una volta aperto il menù cliccare quindi su "Gioca su più pc" e immettere l'indirizzo del server (avviato in precedenza).
Una volta che due player saranno connessi al server il gioco potrà avere inizio.







