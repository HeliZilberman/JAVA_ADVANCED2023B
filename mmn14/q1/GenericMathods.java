import java.util.Iterator;
//contains the generic method reduce
public class GenericMathods {
	
	//returns a sorted group of t elements that are bigger than x
	public static <T extends Comparable<T>> SortedGroup<T> reduce(SortedGroup<T> sGroup,T x){
		SortedGroup<T> retG = new SortedGroup<T>();
		Iterator<T> iterator = sGroup.iterator();
		while(iterator.hasNext()) {
			T curr = iterator.next();
			if(curr.compareTo(x) > 0) {
				retG.add(curr);
				break;
			}
		}
		iterator.forEachRemaining(element -> retG.add(element));
		return retG;
	}
}
