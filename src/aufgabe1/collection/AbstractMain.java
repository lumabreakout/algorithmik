package aufgabe1.collection;

import java.util.TreeMap;

public class AbstractMain {

	private static TreeMap<String,Object> beans = new TreeMap<String, Object>();
	
	public static Object getBean(String objClass) {
		return beans.get(objClass);
	}
	
	public static boolean setBean(String objClass, Object obj) {
		if (beans.get(objClass) == null) {
			beans.put(objClass, obj);
			return true;
		}	
		return false;
	}
}
