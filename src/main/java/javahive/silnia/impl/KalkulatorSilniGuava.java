package javahive.silnia.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import javahive.silnia.KalkulatorSilni;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.google.common.math.BigIntegerMath;

/**
 *
 * @author mgr
 */

public class KalkulatorSilniGuava implements KalkulatorSilni {
	public BigInteger liczGuava(int podstawa) {
		if (podstawa < 0) {
        	throw new IllegalArgumentException();
        }
		return BigIntegerMath.factorial(podstawa);
	}
    
    public String licz(int arg) {
        return ""+liczGuava(arg);
    }
}