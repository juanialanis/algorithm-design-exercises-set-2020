package util.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Computeds greatest common divisor of two nonnegative, not-both-zero
 * integers using diferents algorithms.
 * 
 * @author scilingo
 */

public class GreatestCommonDivisor {

	/**
	* Computes greatest common divisor by Euclid's algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int euclidAlgorithm(int m, int n){
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		if(n == 0)
			return m;
		return euclidAlgorithm(n,m%n);
	}

	/**
	* Computes greatest common divisor by definition based algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int definitionBasedAlgorithm(int m, int n){
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		int r;
		if (m < n) {
			r = m;
		}
		else{
			r = n;
		}

		while(m % r != 0 || n % r !=0){
			r = r - 1;
		}

		return r;

	}

	/**
	* Computes greatest common divisor by middle school procedure
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/

	public static int[] factorizar(int m){
		int[] factorizacion= new int[10];
		int i = 0;
		int[] primos = sieve(m);
		while(m != 1){
			int j = 0;
			 while (m % primos[j] != 0 ) {
				 j++;
			 }
		 	m = m / primos[j];
		 	factorizacion[i] = primos[j];
		 	i++;
		}
		return factorizacion;
	}

	public static int middleSchoolAlgorithm(int m, int n){
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		int[] mfactors = factorizar(m);
		int[] nfactors = factorizar(n);
		int length;
		if (mfactors.length > nfactors.length) {
			length = mfactors.length;
		}
		else{
			length = nfactors.length;
		}
		int[] commonfactors = new int[length];
		
		int k = 0;
		for (int i = 0; i < mfactors.length ; i++ ) {
			for (int j = 0 ; j < nfactors.length  ; j++ ) {
				if (mfactors[i] == nfactors[j]) {
					commonfactors[k] = mfactors[i];
					k++;
				}		
			}
		}

		int mcd = 1;
		for (int i = 0; i < commonfactors.length ; i++ ) {
			mcd = mcd * commonfactors[i];
		}

		return mcd;
	}

 	/**
	* Implements the sieve of Eratosthenes
	* @param n is a number greater than 1
	* @return Array of all prime numbers less than or equal to n.
	*/
	private static int[] sieve(int n){
   
    boolean prime[] = new boolean[n + 1];
    
    Arrays.fill(prime, true);
    for (int p = 2; p * p <= n; p++) {
        if (prime[p]) {
            for (int i = p * 2; i <= n; i += p) {
                prime[i] = false;
            }
        }
    }
 	int[] primeNumbers = new int[n];
	for (int j = 0; j <= n ; j ++ ) {   	
	    for (int i = 2; i <= n; i++) {
	        if (prime[i]) {
	            primeNumbers[j] = i;
	        }
	    }
	 }    
    return primeNumbers;
	}
}
