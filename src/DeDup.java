import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The DeDup provides various implementations of removing duplicates in an array.
 * 
 * @author kavitha.lingareddy
 *
 */
public class DeDup {

	private int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3, 13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

	private int length;

	/**
	 * Accessor method
	 * @return int[] This returns the member array.
	 */
	public int[] getRandomIntegers(){
		return this.randomIntegers;
	}

	/**
	 * Class constructor specifying number of objects to create
	 * @param randomIntegers The integer input array.
	 */
	DeDup(int[] randomIntegers){
		this.randomIntegers=randomIntegers;
		if(randomIntegers!=null)
		  this.length=randomIntegers.length;
		else 
			this.length=0;
	}

	public static void main(String [] args) {


		int[] testArray={1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3, 13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};
		int[] testArray1=null;
		DeDup de=new DeDup(testArray1);

		int[] res=null;
		
		System.out.println("\nOriginal Array");
		de.print();
		
		//Test Approach 1 -Start
		res=de.removeDuplicatesUsingSorting();
		//Test Approach 1 - End
				
		//Test Approach 2 - Start
		//res=de.removeDupsByPreservingOrder();
		//Test Approach 2 - End

		//Tet Approach 3 - Start
		//res=de.removeDupsUsingSet();
		//Test Approach 3 - End
		System.out.println("\nAfter removing duplicates");
		de.printArray(res);
	}

	/**
	 * Approach 1 
	 * This method sorts the array in place and then removes the duplicates from the sorted array.
	 * Best Use Case: Works well in cases of huge data as you don't allocate additional memory 
	 * The complexity of quick sort in the average case is Θ(n log(n)) and in the worst case is Θ(n2).
	 * And removing the duplicates from the sorted array will be o(n)
	 * @return int[] This returns an array with non-duplicate elements.
	 */
	public int[] removeDuplicatesUsingSorting() {
		if(this.randomIntegers==null)return new int[0];
		quicksort(0, length - 1);
		int[] result=removeDupsInSortedArray();
		return result;
	}

	/**
	 * Approach 2 
	 * Remove duplicates by preserving the order
	 * This approach works well for small data sets 
	 * complexity is O(n*2)
	 * @return int[] This returns an array with non-duplicate elements by preserving order of elements as
	 * in original array.
	 */
	public int[]  removeDupsByPreservingOrder(){
		if(this.randomIntegers==null)return new int[0];
		
		int standardVal=randomIntegers[0];

		List<Integer> result= new ArrayList<Integer>();

		result.add(standardVal);

		for(int i=1;i<length-1; i++){


			if(randomIntegers[i]!= standardVal){


				for(int j=i+1; j<length; j++){

					if(randomIntegers[i]==randomIntegers[j])

						randomIntegers[j]=standardVal;

				}
				result.add(randomIntegers[i]);
			}
		}
		int[] resultArray = new int[result.size()];
		for (int k = 0; k < resultArray.length; k++) {
			resultArray[k] = result.get(k);
		}
		return resultArray;
	}

	/**
	 * Approach 3 - Uses a set to remove the duplicates.
	 * Complexity of this solution is O(n) because we are only going through array one time, 
	 * but it also has space complexity of O(n) because of HashSet data structure, which contains your unique elements.
	 * UseCase - Best works in case the order needs to be preserved and huge data set is involved. 
	 * @return int[] Returns an array with non-duplicates
	 */
	public int[] removeDupsUsingSet(){
		if(this.randomIntegers==null)return new int[0];
		Set<Integer> set = new HashSet<Integer>();
		int[] resultArray = new int[randomIntegers.length];
		int i = 0;
		for (int element : randomIntegers) {
			if (set.add(element)) {
				resultArray[i++] = element;
			}
		}

		return Arrays.copyOf(resultArray, i);
	}


/**
 * This method is used for performing quicksort  and put the elements sorted in place. 
 * @param lowerIndex  lower index of the input array.
 * @param higherIndex higher index of the input array.
 */
public void quicksort(int lowerIndex,int higherIndex){
	if(this.randomIntegers==null)return;
	int i = lowerIndex, j = higherIndex;
	//Consider middle element as pivot element
	int pivot = randomIntegers[lowerIndex + (higherIndex-lowerIndex)/2];
	while (i <= j) {
		while (randomIntegers[i] < pivot) {
			i++;
		}
		while (randomIntegers[j] > pivot) {
			j--;
		}
		if (i <= j) {
			swapElements(i, j);
			i++;
			j--;
		}
	}

	// Recursively call the quicksort for all the subsets
	if (lowerIndex < j)
		quicksort(lowerIndex, j);
	if (i < higherIndex)
		quicksort(i, higherIndex);
}

/**
 * This method assumes the input array is sorted and removes duplicates from the array.
 * @return int[] returns an array with non-duplicates
 */
public int[] removeDupsInSortedArray(){
	int prev= randomIntegers[0];
	int curr;
	List<Integer> resultList=new ArrayList<Integer>();
	resultList.add(prev);
	for(int i=1;i<length;i++){
		curr=randomIntegers[i];
		if(prev!=curr){
			resultList.add(randomIntegers[i]);
		}
		prev=randomIntegers[i];
	} 
	int[] resultArray = new int[resultList.size()];
	for (int i = 0; i < resultArray.length; i++) {
		resultArray[i] = resultList.get(i);
	}

	return resultArray;


}

/**
 * This method is used to swap the elements of the array given the position of the elements that needs to be swapped.
 * @param i  The first index that needs to be swapped.
 * @param j  Second Index that needs to be swapped.
 */
private void swapElements(int i, int j) {

	int temp = randomIntegers[i];

	randomIntegers[i] = randomIntegers[j];

	randomIntegers[j] = temp;

}

/**
 * This method is used to print the array.
 */
public void print() {

	System.out.println("\n");
	for (int i = 0; i < this.length; i++) {

		System.out.print(this.randomIntegers[i]+" ");

	}

}

public void printArray(int[] arr) {

	System.out.println("\n");
	for (int i = 0; i < arr.length; i++) {
		System.out.print(arr[i]+ " ");

	}

}


}
