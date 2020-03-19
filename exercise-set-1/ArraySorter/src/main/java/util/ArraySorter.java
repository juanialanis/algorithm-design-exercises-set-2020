package util;

/**
 * Sorts arrays of comparable objects using a variety of options.
 * 
 * @author aguirre
 *
 * @param <E> is the type of the elements of the array.
 */
public class ArraySorter<E extends Comparable<E>> {

	/**
	 * The array to sort.
	 */
	private E[] array;
	
	/**
	 * The algorithm to use for sorting.
	 */
	private SortAlgorithm algorithm = SortAlgorithm.INSERTIONSORT;

	/**
	 * Default constructor. Sets the array to sort and sorting algorithm to INSERTION SORT.
	 * @param array is the array to sort.
	 */
	public ArraySorter(E[] array) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
	}

	/**
	 * Constructor that sets array and sorting algorithm.
	 * @param array is the array to sort.
	 * @param algorithm is the algorithm to use for sorting.
	 */
	public ArraySorter(E[] array, SortAlgorithm algorithm) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
		this.algorithm = algorithm;
	}

	/**
	 * Sets the algorithm to use for sorting.
	 * @param algorithm is the algorithm to set for sorting.
	 */
	public void setAlgorithm(SortAlgorithm algorithm) {
		if (algorithm == null) throw new IllegalArgumentException("algorithm can't be null");
		this.algorithm = algorithm;
	}
	
	/**
	 * Retrieves the (sorted or yet unsorted) array within the ArraySorter.
	 * @return the array stored within the ArraySorter object.
	 */
	public E[] getArray() {
		return this.array;
	}

	/**
	 * Sets the array to be sorted.
	 * @param array is the new array to sort.
	 */
	public void setArray(E[] array) {
		this.array = array;		
	}

	/**
	 * Sorts the array.
	 * The array can then be retrieved using getArray() method.
	 */
	public void sort() {
		switch (this.algorithm) {
		case INSERTIONSORT: 	
			insertionSort(array); 
			break;
		case BUBBLESORT:
			bubbleSort(array); 
			break;
		case MERGESORT:
			mergeSort(array,0,array.length-1); 
			break;
		case SELECTIONSORT:
			selectionSort(array); 
			break;
		default:
            throw new UnsupportedOperationException("sorting method not yet implemented"); 
		}	
	}

	/**
	 * Sorts an array. Implements the selection sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void selectionSort(T[] array) {
		if (array == null) throw new IllegalArgumentException("array is null, can't sort");
		int i = 0;
		while (i < array.length) {
			T min, aux;
			int minIndex, j;
			min = array[i];
			minIndex = i;
			j = i;
			while (j < array.length) {
				if (array[j].compareTo(min) < 0) {
					min = array[j];
					minIndex = j;
				}
				j++;
			}
			aux = array[i];
			array[i] = array[minIndex];
			array[minIndex] = aux;
			i++;
		}
	}

	/**
	 * Sorts an array. Implements the mergesort sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void merge(T[] array,int begin,int middle, int end){
		int lengthl = middle - begin + 1 ;
		int lengthr = end - middle;

		T[] l = (T[])new Comparable[lengthl];
		T[] r = (T[])new Comparable[lengthr];

		int i = 0;
		int j = 0;

		int k = begin;
		while(i<lengthl && j<lengthr){
			if (l[i].compareTo(r[j]) > 0) {
				array[k] = l[i];
				i++;
			}
			else{
				array[k] = r[j];
				j++;
			}
			k++;
		}

	}


	private static <T extends Comparable<T>> void mergeSort(T[] array,int begin, int end) {
		int middle = (begin+end) / 2;
		mergeSort(array, begin, middle);
		mergeSort(array, middle+1, end);
		merge(array,begin,middle,end);		
	}

	/**
	 * Sorts an array. Implements the bubblesort sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */

	private static <T extends Comparable<T>> void bubbleSort(T[] array) {
		Comparable aux ;
		for (int i = 0; i < array.length-1 ; i++) {
			if (isSorted(array) == false) {
				for (int j = i; j < array.length-1 ; j++) {
					if(array[j].compareTo(array[j+1]) == 1 ){
						aux = array[j];
						array[j] = array[j+1];
						array[j+1] = array[j];
					}											
				}
			}					
		}			
	}

	/**
	 * Sorts an array. Implements the insertion sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void insertionSort(T[] array) {
		int n = array.length; 
        T key;
        for (int i = 1; i < n; ++i) { 
         	key = array[i]; 
            int j = i - 1; 

            while (j >= 0 && array[j].compareTo(key) > 0) { 
                array[j + 1] = array[j]; 
                j = j - 1; 
            } 
            array[j + 1] = key; 
        } 		
	}

	/**
	 * Checks if a given array is sorted.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be checked for sortedness.
	 * @return true iff the array is sorted.
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] array) {
		boolean sorted = true;
		if (array.length == 1) {
			return sorted;
		}
		for (int i = 1; i < array.length ; i++ ) {
			if (array[i-1].compareTo(array[i]) > 0){
				sorted = false;
			}
		}
		return sorted;
	}

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
