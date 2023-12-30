import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//associative sorted (ascending) list.
public class SortedGroup <T extends Comparable<T>> {
	private ArrayList<T> arr;
	
	//Construct empty array list
	public SortedGroup() {
		arr = new ArrayList<T>();
	}
	//adds element to arr in order 
	public void add(T element) {
		int index = Collections.binarySearch(arr,element);
		//If key(element) is not present, the binary search returns (-(insertion point) - 1)
		if(index < 0)
			index = -index - 1;
		arr.add(index,element);
	}
	
	//if element exists in sorted arr it is removed and returns the number of times it appears 
	public int remove(T element) {
		int count = 0;
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().equals(element)) {
				count++;
				iterator.remove();
			} else if(count > 0) { // iterator goes by order - arr is sorted
				break;
			}
		}
		return count;
	}
	//returns iterator to iterate sorted arr
	public Iterator<T> iterator(){
		return arr.iterator();
	}
}