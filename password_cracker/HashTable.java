import java.util.Arrays;

public class HashTable {
	private String keys[];
	private String values[];
	private int space;
	private int initialSpace;
	private int size;
	private int collisions;
	
	public HashTable(int space) {
		values = new String[space];
		keys = new String[space];
		size = 0;
		this.space = space;
		initialSpace = space;
		collisions = 0;
	}
	public HashTable() {
		values = new String[176551];
		keys = new String[176551];
		space = 176551;
		initialSpace = 176551;
		size = 0;
		collisions = 0;
	}
	public int getSize() {
		return size;
	}
	public int getSpace() {
		return space;
	}
	public int getInitailSpace() {
		return initialSpace;
	}
	public int getCollisions() {
		return collisions;
	}
	public double getLoadFactor() {
		return size/space;
	}
	public double getAvgProbes() {
		return size/collisions;
	}
	private int getIndex(int tempIndex, int hashcode) {
		if(values[tempIndex] != null) {
			collisions = collisions +1;
			//System.out.println(tempIndex+" at collision");
			int newtemp = tempIndex+(149 - hashcode%149);
			//System.out.println(newtemp+" aftr collision resolution");
			if(newtemp>=space) {
				//System.out.println(newtemp+" when bigger bef4");
				newtemp = newtemp-space;
				//System.out.println(newtemp+" when bigger aftr");
			}
			getIndex(newtemp, hashcode);
		}
		return tempIndex;
	}
	private String[] extendArray(String[] toExtend) {
		space = space*2;
		String[] extendedArray = new String[space];
		for (int i = 0; (i<toExtend.length); i++) {
			extendedArray[i] = toExtend[i];
		}
		return extendedArray;
	}
	public boolean containsKey(String key) {
		return (Arrays.asList(keys).indexOf(key))>0;
	}
	public String get(String key) {
		return values[Arrays.asList(keys).indexOf(key)];
	}
	private void incSize() {
		size = size+1;
	}
	public void put(String encryptedPassword, String plainPassword) {
		if(getLoadFactor()>0.6) {
			keys = extendArray(keys);
			values = extendArray(values);
		}
			int hash = encryptedPassword.hashCode();
			int tempIndex = Math.abs(hash%90863); 
			//System.out.println(tempIndex+"   "+hash+"  before method");
			int index = getIndex(tempIndex, hash);
			values[index] = plainPassword;
			keys[index] = encryptedPassword;
			incSize();
			}

}
