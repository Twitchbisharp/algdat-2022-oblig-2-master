package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {}
        //Oppgave 1
    public DobbeltLenketListe(T[] a) { //Konstruktør
        this();

        int i = 0;

        for(; i < a.length && a[i] == null; i++){}

        Objects.requireNonNull(a, "Tabellen a er null!");

        if(i<a.length){
            Node<T> current = hode = new Node<>(a[i], null, null); // bare hode
            antall++; // dette fiksa alle problemene våre :D
            i++;
            for(; i<a.length; i++){
                if(a[i] != null){
                    current.neste = new Node<T>(a[i], current, null);
                    current.neste.forrige = current;
                    current = current.neste;
                    antall++;
                }
            }
            hale = current;
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(this.antall, fra, til);
        Liste<T> subliste = new DobbeltLenketListe<T>();
        Node<T> gihun = finnNode(fra); //gihun variabelnavn er det samme som squidGame xd
        for(int i = fra; i<til;i++){
            subliste.leggInn(gihun.verdi);
            gihun = gihun.neste;
        }
        return subliste;
    }

    private static void fratilKontroll(int antall, int fra, int til){
        if(fra < 0){
            throw new IndexOutOfBoundsException("Fra(" + fra + ") er negativ!");
        }
        if(til > antall){
            throw new IndexOutOfBoundsException("Til(" + til + ") > nodelengde(" + antall + ")");
        }
        if(fra > til){
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til +") - Illegalt intervall!");
        }
    }

    //Oppgave 1
    @Override
    public int antall() {
        return antall;
    }

    // Oppgave 1
    @Override
    public boolean tom() {
        return antall == 0 ? true : false;
    }

    //Oppgave 2b
    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Tabellen er null!");
        if(antall == 0){ // DobbeltLenketListe er tom
            hale = hode = new Node<>(verdi, null, null);
        }else{
            hale.neste = new Node<>(verdi, hale, null);
            hale = hale.neste;
        }
        antall++;
        endringer++;
        return true;
    }

    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi);
        indeksKontroll(indeks, true);
        if(tom()){ //Dersom listen er tom
            hale = hode = new Node<>(verdi, null, null);
        }
        else if(indeks == 0){ // Dersom verdien skal legges først
            hode = hode.forrige = new Node<>(verdi, null, hode);
        }
        else if(indeks == antall){ // verdien legges bakerst
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        else{ // verdien legges et sted i midten
            Node<T> gihun = finnNode(indeks); // eksisterende node i List
            gihun.forrige = gihun.forrige.neste = new Node<>(verdi, gihun.forrige, gihun);
        }
        antall++;
        endringer++;
    }


    //Oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) != -1){
            return true;
        } else{
            return false;
        }
    }

    // Oppgave 3
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    //Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        Node<T> gihun = hode;
        for(int i = 0; i < antall; i++){
            if(gihun.verdi.equals(verdi)){
                return i;
            }
            gihun = gihun.neste;
        }
        return -1; // hvis if-testen i for-løkken aldri blir true, så finnes ikke verdien i listen
    }

    // Oppgave 3
    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);
        Node<T> gihun = finnNode(indeks);
        T gammel = gihun.verdi;
        gihun.verdi = nyverdi;
        endringer++;
        return gammel; // returnerer det som lå der fra før;
    }

    //Oppgave 6
    @Override
    public boolean fjern(T verdi) { //skal fjerne node med oppgitt verdi
        if (verdi == null) return false;          // ingen nullverdier i listen

        Node<T> q = hode, p = null;               // hjelpepekere

        while (q != null)                         // q skal finne verdien
        {
            if (q.verdi.equals(verdi)) break;       // verdien funnet
            p = q;
            q = q.neste;                     // p er forgjengeren til q
        }

        if (q == null) return false;                                    // fant ikke verdi
        else if (q == hode) { // hvis vi skal fjerne første indeks
            if (antall == 1) { // Hvis tabellen bare er én Node
                hode = hale = null;
            } else { // Tabellen har 2 eller flere noder
                hode.neste.forrige = null;
                hode = hode.neste;
            }
        } else if (q != hale) { // hvis vi skal fjerne en node som verken er hale eller hode
            q.forrige.neste = q.neste;
            q.neste.forrige = q.forrige;
        }


        if (q == hale) {
            hale = p;
            hale.neste = null;
        }  // hvis vi skal fjerne hale

        q.verdi = null;                           // nuller verdien til q
        q.neste = null;                           // nuller nestepeker

        antall--;                                 // en node mindre i listen
        endringer++;
        return true;
    }

    //Oppgave 6
    @Override
    public T fjern(int indeks) { // skal returnere verdien som blir fjernet
        indeksKontroll(indeks, false);
        T navn;
        if(indeks == 0){ // skal fjerne første verdien i listen
            navn = hode.verdi;
            hode = hode.neste; // Denne er i kompendiet, men var ikke her da jeg så over
            if(antall == 1){
                hale = null;
            } else{ // Dersom antall > 1 og første indeks skal fjernes
                hode.forrige.neste = null; // fjerner peker fra første node sin .neste
                hode.forrige = null; // fjerner peker fra nåværende hode sin .forrige
            }
        }
        else{
            Node<T> gihun = finnNode(indeks-1); // gihun er verdien før den som skal fjernes
            Node<T> current = gihun.neste; // current er den som skal fjeres
            navn = current.verdi; // returverdi

                if(current == hale){ // hvis den som skal fjernes er siste verdi
                    hale = gihun; // if skal egt slutte her
                    gihun.neste = current.neste; // praktisk talt blir gihun.neste == null

                    // disse fjerner pekerene på noden som skal bli fjernet
                    current.neste = null;
                    current.forrige = null;
                } else{ // hvis current != hale
                    gihun.neste = current.neste; // hopper over fjernet verdi med .neste
                    gihun.neste.forrige = gihun; // hopper over fjernet verdi med .forrige

                    // fjerner pekere fra noden som skal bli fjernet
                    current.neste = null;
                    current.forrige = null;
            }
        }
        antall--;
        endringer++;
        return navn;

    }

    // Oppgave 7
    // Dette er metode 1 og den brukte 16ms som er den raskeste av de to metodene
    public void nullstill() {
        Node<T> peker = hode;

        while(peker != hale){
            peker.verdi = null;
            peker.forrige = null;
            peker = peker.neste; // itererer fremover
            peker.forrige.neste = null;
        }
        hode = null;
        hale = null;

        antall = 0;
        endringer++;
    }


    // Oppgave 7 2. metode
    // Denne metoden brukte 18 ms og var tregere enn metode 1
    public void nullstill2(){
        for(int i = 0; i < antall;){
            fjern(0);
        }
        antall = 0;
    }

    //Oppgave 2a
    @Override
    public String toString() {
        StringJoiner s = new StringJoiner(", " , "[", "]");
        Node<T> current = hode;
        while(current != null){
            s.add(current.verdi.toString());
            current = current.neste;
        }
        return s.toString();
    }

    // Oppgave 2a
    public String omvendtString() {

        StringJoiner s = new StringJoiner(", " , "[", "]");
        Node<T> current = hale;
        while(current != null){
            s.add(current.verdi.toString());
            current = current.forrige;
        }
        return s.toString();
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks){
        if(indeks < antall/2){
            //Starter på head og går til høyre ved hjelp av neste-pekere
            Node<T> current = hode;
            for(int i = 0; i < indeks; i++){
                current = current.neste;
            }
            return current;
        } else{
            //Starter på hale og går til venstre ved hjelp av forrige-pekere
            Node<T> current = hale;
            for(int i = antall-1; i > indeks; i--){
                current = current.forrige;
            }
            return current;
        }
    }

    //Oppgave 8
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        //Oppgave 8
        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(hasNext() != true){
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;



            /*if(iteratorendringer == endringer){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                fjernOK = true;
                T verdi = denne.verdi;
                denne = denne.neste;
                return verdi;
            } else{
                throw new ConcurrentModificationException();
            }*/
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


