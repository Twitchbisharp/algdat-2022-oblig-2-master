package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;


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
        Objects.requireNonNull(indeks);
        indeksKontroll(indeks, true);
        
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

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


