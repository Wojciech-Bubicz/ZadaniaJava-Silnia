package javahive.silnia.impl;


import java.math.BigDecimal;
import java.math.MathContext;

import javahive.silnia.KalkulatorSilni;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Marcin Grabowiecki
 ddddd* 
 * proszę zaimplementować algorytm analogiczny jak dla SzybkaSilnia
 * jednak należy się oprzeć na na typie BigDecimal
 - test na komentarzu * 
 */

public class KalkulatorSilniBigDecimal implements KalkulatorSilni {
    public BigDecimal liczBD(BigDecimal podstawa) {
    	if (podstawa.signum() == -1) {
        	throw new ArithmeticException();
        }
    	BigDecimal bd0 = new BigDecimal(0);
    	if (podstawa.compareTo(bd0) == 1) {
    		BigDecimal bd1 = new BigDecimal(1);
            return podstawa.multiply(liczBD(podstawa.subtract(bd1)));
        }
    	else {
    		BigDecimal bd = new BigDecimal(1);
            return bd;
        }
    }
    
    public String licz(int arg) {
        // Wynik działania rekrurencyjnej metody rzutowany jest na String,
        // bo string przechowa bez problemu duże liczby.
    	BigDecimal podstawa = new BigDecimal (arg);
        return ""+liczBD(podstawa); // To jest rzutowanie, przez ""+coś.
    }
}