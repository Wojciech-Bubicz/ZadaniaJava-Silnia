package javahive.silnia.impl;

import javahive.silnia.KalkulatorSilni;


/**
 *
 * @author Marcin Grabowiecki
 * 
 * Klasa licząca silnię, która wykorzystuje typy long.
 * nie jest to optymalne, ponieważ maksymalna wartość wynosi 3409530940953,
 * więc nie jesteśmy w stanie liczyć zbyt wielkich silni
 * używamy algorytmu rekurencyjnego
 */


public class SzybkiKalkulatorSilni implements KalkulatorSilni {
    //metoda licząca silnie w sposób rekurencyjny
    public long liczLong(long podstawa) {
        if (podstawa < 0) {
        	throw new ArithmeticException();
        }
    	if (podstawa > 0) {
            return podstawa*liczLong(podstawa-1);
        }
    	else {
            return 1;
        }
    }
    
    public String licz(int arg) {
        //wynik działania rekrurencyjnej metody rzutowany jest na String,
        //Bo string przechowa bez problemu duże liczby
        return ""+liczLong(arg);
    }

}
