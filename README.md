Questo progetto permette di tener traccia delle gare podistiche di un'associazione italiana. Ad una gara podistica possono partecipare più atleti. Dal sistema inoltre sono tracciate le informazioni degli sponsor.
Con questo sistema è possibile inserire, modificare,eliminare e ricercare le informazioni relativa ad atleti,gare e sponsor. Il sistema permette inoltre la generazione di statistiche basate sulla frequenza degli atleti sulle diverse citta.
Il progetto è stato implementato in Java versione 17 e, in particolare, è stato usato il framework Spring-boot.
Le entita sono persistite su un database mySQL e in Java sono state modellate mediante l'utilizzo dell'Entity Manager. Sono state utilizzate le annotazioni Spring-Data-JPA; mentre la parte rest è stata sviluppata mediante l'utilizzo di Spring-boot-starter-web.
Infine è stata utilizzata l'estensione Lombok per evitare il codice statico dei Getter e Setter e dei Costruttori.
