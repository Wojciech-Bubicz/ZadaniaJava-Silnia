/**
 * @author Marcin Grabowiecki
 */


package marcin.silnia;


import javahive.silnia.KalkulatorSilni;
import javahive.silnia.impl.KalkulatorSilniGuava;
import javahive.silnia.impl.KalkulatorSilniBigDecimal;
import javahive.silnia.impl.KalkulatorSilniNieRekursywny;
import javahive.silnia.impl.OptymalnyKalkulatorSilni;
import javahive.silnia.impl.SzybkiKalkulatorSilni;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author mgr
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SilniaTest {

    private KalkulatorSilni szybkaSilnia;
    private KalkulatorSilni optymalnaSilnia;
    private KalkulatorSilni duzaSilnia;
    private KalkulatorSilni nierekSilnia;
    private KalkulatorSilni guavaSilnia;
    private KalkulatorSilni[]wszytkieKalkulatory;
    
    
    
    @Before
    public void przypiszKalkulatory(){
        szybkaSilnia = new SzybkiKalkulatorSilni();
        optymalnaSilnia = new OptymalnyKalkulatorSilni();
        duzaSilnia = new KalkulatorSilniBigDecimal();
        nierekSilnia = new KalkulatorSilniNieRekursywny();
        guavaSilnia = new KalkulatorSilniGuava();
        wszytkieKalkulatory=new KalkulatorSilni[4];
        wszytkieKalkulatory[0]=szybkaSilnia;
        wszytkieKalkulatory[1]=optymalnaSilnia;
        wszytkieKalkulatory[2]=duzaSilnia;
        wszytkieKalkulatory[3]=nierekSilnia;    	
    }
    
    
    //to jest prosty test, który sprawdza implementację dostarczonego algorytmu, który liczy opierając się na typach long
    @Test
    public void t001PodstawowyTest(){
        //given
            int silnia=10;
        //when
            String wynik=szybkaSilnia.licz(silnia);
        //then
            Assert.assertEquals("3628800",wynik);
    }
    
    /*
     * test warunku brzegowego - liczby ujemne
     * 
     * pierwsze zdanie - uzupełnić algorytm w klasie SzybkiKalkulatorSilni o wykrywanie czy argument
     * nie jest ujemny. Jeśli jest - należy zwrócić wyjątek
     * Jak widać - test oczekuje że pojawi się wyjątek
     * 
     */
    
    @Test(expected = ArithmeticException.class)
    public void t002ArgumentyUjemne(){
        //given
            int silnia=-1;
        //when
            String wynik=szybkaSilnia.licz(silnia);
        //then
            Assert.fail();
    }    
    
    /*
     * 
     * drugie zadanie - zaimplementować metodę licz w KalkulatorzeSilnikBigDecimal
     * tak by do jej liczenia były używane BigDecimal-e
     * 
     */
    
    @Test
    public void t003DuzeArgumenty(){
        //given
            int silnia=50;
        //when
            String wynik=duzaSilnia.licz(silnia);
        //then
            //w teście sprawdzam tylko pierwsze 10 cyfr
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }

    
     /*
     * 
     * Zaimplementować algorytm w OptymalnymKalkulatorzeSilni tak, by algorytm automatycznie rozpoznawał
     * czy do liczenia użyć Szybkiej silni (małe argumenty) czy BigDecimalSilnia
     * (dla dużych argumentów)
     * 
     */
    
    @Test
    public void t004OptymalnaSilnia(){
        //given
            int silnia=50;
        //when
            String wynik=optymalnaSilnia.licz(silnia);
        //then
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }

    
     /*
     * 
     * Czasem zamiast odrywać Amerykę na nowo wystarczy poszukać dobrej biblioteki
     * zmodyfikuj pom.xml i zmodyfikuj GuavaSilnia tak by test kończył się sukcesem
     * 
     */
    
    @Test
    public void t005BibliotekaGuava(){
        //given
            int silnia=50;
        //when
            String wynik=optymalnaSilnia.licz(silnia);
        //then
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }
    
    
    
    //test wszystkich algorytmów

    @Test
    public void t007Wszystkie(){
        for(KalkulatorSilni kalkulator:wszytkieKalkulatory){
        //given
            int silnia=9;
        //when
            String wynik=kalkulator.licz(silnia);
        //then
            Assert.assertEquals("362880", wynik);
        }
    }            

}