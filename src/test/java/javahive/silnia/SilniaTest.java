package javahive.silnia;


import javahive.silnia.KalkulatorSilni;
import javahive.silnia.impl.KalkulatorSilniGuava;
import javahive.silnia.impl.KalkulatorSilniBigDecimal;
import javahive.silnia.impl.KalkulatorSilniNieRekurencyjny;
import javahive.silnia.impl.OptymalnyKalkulatorSilni;
import javahive.silnia.impl.SzybkiKalkulatorSilni;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Marcin Grabowiecki
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SilniaTest {
	
    // to jest prosty test, który sprawdza implementację dostarczonego algorytmu,
    // liczącego w oparciu o typy long
    @Test
    public void t001PodstawowaImplementacjaPowinnaPoprawnieLiczycSilnie(){
        //given
    		final KalkulatorSilni kalkulatorSilni=new SzybkiKalkulatorSilni(); 
            final int silnia=10;
        //when
            String wynik=kalkulatorSilni.licz(silnia);
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
    public void t002ArgumentyUjemnePowinnyWyrzucacWyjatek(){
        //given
    		final KalkulatorSilni kalkulatorSilni=new SzybkiKalkulatorSilni();
            final int silnia=-1;
        //when
            String wynik=kalkulatorSilni.licz(silnia);
        //then
            Assert.fail();
    }    
    
    /*
     * drugie zadanie - zaimplementować metodę licz w KalkulatorzeSilnikBigDecimal
     * tak by do jej liczenia były używane BigDecimal-e 
     */
    
    @Test
    public void t003KalkulatorPowinienLiczycPoprawnieDlaDuzychArgumentow(){
        //given
    		final KalkulatorSilni kalkulatorSilni=new KalkulatorSilniBigDecimal();
    		final int silnia=50;
        //when
            String wynik=kalkulatorSilni.licz(silnia);
        //then
            //w teście sprawdzam tylko pierwsze 10 cyfr
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }

    
     /*
     * Zaimplementować algorytm w OptymalnymKalkulatorzeSilni tak, by algorytm automatycznie rozpoznawał
     * czy do liczenia użyć Szybkiej silni (małe argumenty) czy BigDecimalSilnia
     * (dla dużych argumentów)
     */
    
    @Test
    public void t004KalkulatorOptymalnejSilniPowinienPoprawnieLiczyc(){
        //given
    		final KalkulatorSilni kalkulatorSilni=new OptymalnyKalkulatorSilni();
    		final int silnia=50;
        //when
            String wynik=kalkulatorSilni.licz(silnia);
        //then
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }

    
     /* 
     * Czasem zamiast odrywać Amerykę na nowo wystarczy poszukać dobrej biblioteki
     * zmodyfikuj pom.xml i zmodyfikuj GuavaSilnia tak by test kończył się sukcesem
     */
    
    @Test
    public void t005KalkulatorGuavaPowinienZwracacPoprawnyWynik(){
        //given
    		final KalkulatorSilni kalkulatorSilni=new KalkulatorSilniGuava();
            final int silnia=50;
        //when
            String wynik=kalkulatorSilni.licz(silnia);
        //then
            Assert.assertEquals(wynik.substring(0,10), "3041409320");
    }
    
    
    //test wszystkich algorytmów liczenia silni

    @Test
    public void t007WszystkieKalkulatorySilniPowinnyZwracacTenSamWynik(){
        //given
    		KalkulatorSilni[]wszytkieKalkulatory={
    				new SzybkiKalkulatorSilni(),
    				new OptymalnyKalkulatorSilni(),
    				new KalkulatorSilniGuava(),
    				new KalkulatorSilniBigDecimal(),
    				new KalkulatorSilniNieRekurencyjny()
    		};
            final int silnia=9;
        
        for(KalkulatorSilni kalkulator:wszytkieKalkulatory){
        //when
            String wynik=kalkulator.licz(silnia);
        //then
            Assert.assertEquals("362880", wynik);
        }
    }
    
    @Test
    public void t008WszystkieKalkulatorySilniPowinnyLiczycZero(){
    	//given
		KalkulatorSilni[]wszytkieKalkulatory={
				new SzybkiKalkulatorSilni(),
				new OptymalnyKalkulatorSilni(),
				new KalkulatorSilniGuava(),
				new KalkulatorSilniBigDecimal(),
				new KalkulatorSilniNieRekurencyjny()
		};
        final int silnia=0;
    
	    for(KalkulatorSilni kalkulator:wszytkieKalkulatory){
	    //when
	        String wynik=kalkulator.licz(silnia);
	    //then
	        Assert.assertEquals("1", wynik);
	    }
    }
    
    @Test
    public void t009WszystkieKalkulatorySilniPowinnyZwracacTeSamaLiczbeDlaWartosciUjemnych(){
    	//given
		KalkulatorSilni[]wszytkieKalkulatory={
				new SzybkiKalkulatorSilni(),
				new OptymalnyKalkulatorSilni(),
				new KalkulatorSilniGuava(),
				new KalkulatorSilniBigDecimal(),
				new KalkulatorSilniNieRekurencyjny()
		};
        final int silnia=-100;
    
	    for(KalkulatorSilni kalkulator:wszytkieKalkulatory) {
		    //when
	    	try {
	    		String wynik=kalkulator.licz(silnia);
	    	}
	        catch (IllegalArgumentException e) {
	        	continue;
	        }
	    	catch (ArithmeticException e) {
	    		continue;
	    	}
		    //then
	        Assert.fail();
	    }
    }
}
