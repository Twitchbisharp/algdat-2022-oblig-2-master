# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Adarsh Singh Kular, s364590, s364590@oslomet.no
* Filip Mattias Nilssen, s364544, s364544@oslomet.no
* William Promnoi Evensen, s364581, s364581@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
Adarsh har hatt hovedansvar for oppgave ...
William har hatt hovedansvar for oppgave ...1
Filip har hatt hovedansvar for oppgave ...

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å først lage en for-løkke som gjør at pekeren hopper over alle 
null-verdier i starten av innkommende array. Så tester vi om arrayet  er null og sender 
feilmelding basert på det. Deretter itererer koden gjennom arrayet og lager noder av dem og oppdaterer 
antall for hver node som blir opprettet. Husker å sette hale til å være den siste noden.
De to andre metodene antall() og tom() var bare å returnere instansvariabelen antall og å sjekke om denne var lik 0 eller ikke.

I oppgave 2 så brukte vi en StringJoiner til å lage toString() metoden. 
I parentesen til new StringJoiner() skal vi først ha hva som skiller to verdier fra hverandre, deretter hva som skal 
stå først og hva som skal stå sist i utskriften. Deretter brukte vi metoden add() til å legge til verdier i strengen.
For å få til omvendtString() gjorde vi det samme bare at vi startet itereringen ved hale og ikke hode.
leggInn()-metoden skulle legge til en ny node bakerst i listen. Vi fikk to tilfeller: dersom tabellen allerede
var tom, og dersom tabellen hadde noder i seg. Dersom tabellen allerede var tom, opprettet vi en node med nullpekere 
som .neste og .forrige, samtidig som den er både hode og hale. Dersom tabellen hadde noder i seg, oppdaterer vi bare hale.

I oppgave 3 

I oppgave 4 så startet vi med å sette opp metoden "int indeksTil(T verdi)". 
Det var da bare å sette opp en for-løkke som sjekker verdi med equals og ikke "==" pga sammenligningsfeil.
Deretter trengte vi bare å sette en return statement med "-1" slik at ingen unntak ble kastet ved null.
Ved den andre metoden "boolean inneholder(T verdi)" trengte vi bare en return true dersom listen hadde noen
verdier i seg.

I oppgave 5 så gikk vi frem ved å først teste parameterverdiene indeks og verdi for å skjekke om de er lovlige verdier.
Det vil totalt være fire ulike tilfeller hvor man skal kunne legge inn en ny verdi i listen:
dersom listen er tom, dersom verdien skal legges først, dersom verdien skal ligge sist go dersom verdien skal legges et sted i midten.
I tilfellet hvor listen er tom, bare lager vi første node som både er hode og hale.
Dersom det skal legges først, plasserte vi verdien foran hodet og oppdaterte hva hodet skulle være.
Dersom det skal legges siste, plasserte vi verdien etter hale og oppdaterte hva hale skulle være.
Dersom verdien skulle et sted i modten av listen, så finner vi noden som står på indeksen til hva den nye verdien skulle være,
og så presser vi inn den nye verdien rett etter den eksisterende verdien.
Husker på å oppdatere antall og endringer til slutt.

I oppgave 6 så startet vi med å sette opp metodene og en sjekk av indeks. Ved første del av koden blir indeks 0
fjernet og for å sette pekerne opp korrekt var det enklest med en if/else-setning med if/else inni hver av dem. 
If/else-setningen går gang på gang gjennom hele oppgaven pga oppgavebeskrivelsen "hvis".
Ved den andre delen av koden startet vi med en return false statement dersom det er null-verdier.
Deretter satte vi opp noen hjelpepekere og en while-løkke som skal finne verdien og 
som stopper å kjøre etter å ha funnet verdien. Deretter var det samme konsept med if/else-setninger,
men med bruk av 'else if' for å sette opp en else for å slippe å skrive en "(statement)".
Til slutt var det bare å følge bulletpoints'a for hva som skulle gjøres og da var det lett å forstå
hvor 'antall--', 'endringer++' skulle komme inn. 

I oppgave 7 

I oppgave 8 så gikk vi frem ved å først lage hasNext-metoden. Den bestod av to sjekker som returnerer feilmeldinger hvis
de inntreffer. Så lager vi en ny variabel som har nåværende verdi og returnerer denne etter at vi har iterert oss til neste verdi.
Etter dette opprettet vi metodene iterator() som returnerer en DobbeltLenketListeiterator med og uten startindeks.
DobbeltLenketListeIterator oppdaterer instansvariabelen denne til å bli node med ønsket indeks.

I oppgave 9 startert

I oppgave 10 så gikk Filip frem ved å gjenbruke en av oppgavene han hadde gjort i en ukeoppgave
Deretter så endret han koden til å passe inn i med allerede eksisterende metoder for Node-strukturen.
Sorteringsalgoritmen som ble brukt er innsettingssortering.

