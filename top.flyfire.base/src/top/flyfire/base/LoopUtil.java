package top.flyfire.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LoopUtil {
	public static final <T> void run(T[] arr,Task<T> t){
		for(int i = 0;i<arr.length;i++){
			t.exec(arr[i], i);
		}
	}
	
	public static final <T> void run(Collection<T> arr,Task<T> t){
		int j = 0;
		for(Iterator<T> i = arr.iterator();i.hasNext();j++){
			t.exec(i.next(), j);
		}
	}
	
	public static final <K,V> void run(Map<K,V> arr,Task<Entry<K,V>> t){
		int j = 0;
		Set<Entry<K,V>> set = arr.entrySet();
		for(Iterator<Entry<K,V>> i = set.iterator();i.hasNext();j++){
			t.exec(i.next(), j);
		}
	}
	
	public interface Task<T>{
		public abstract void exec(T t,int i);
	}

}
