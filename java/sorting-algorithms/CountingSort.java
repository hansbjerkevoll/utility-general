package sorting_algs;

/**
 * @author Hans Bjerkevoll
 * https://github.com/hansbjerkevoll
 */

public class CountingSort {
	
	/**
	 * Algotihm sorting a given list of integers, using counting sort
	 * 
	 * @param input
	 * @param k
	 * @return sorted list of integers
	 */
	public static Integer[] counting_sort(Integer[] input, int k){
		
		Integer[] counting = new Integer[k];
		Integer[] output = new Integer[input.length];
		
		for(int i = 0; i < k; i++) {
			counting[i] = 0;
		}
		
		for(int i = 0; i < input.length; i++) {
			counting[input[i]] = counting[input[i]] + 1;
		}
		
		for(int i = 1; i < k; i++) {
			counting[i] = counting[i] + counting[i-1];
		}
		
		for(int i = input.length-1; i >= 0; i--) {
			output[counting[input[i]]-1] = input[i];
			counting[input[i]] = counting[input[i]] - 1;
		}
		
		return output;
		
	}

}
