
public class DatabaseMine implements DatabaseInterface {
	private HashTable database;
	public DatabaseMine() {
	 // this constructor must create the initial hash map
		database = new HashTable();
		
	}
	public DatabaseMine(int space) {
		database = new HashTable(space);
	}
	public String save(String plainPassword, String encryptedPassword) {
		database.put(encryptedPassword, plainPassword);
		return null;
	}
	public String decrypt(String encryptedPassword) {
		if(database.containsKey(encryptedPassword)) {
			return database.get(encryptedPassword);
		}
		return "";
	}
	public int size() {
		return database.getSize();
	}
	private double getAvgProbes() {
		return database.getAvgProbes();
	}
	private int getInitailSpace() {
		return database.getInitailSpace();
	}
	private double getLoadFactor() {
		return database.getLoadFactor();
	}
	private int getCollisions() {
		return database.getCollisions();
	}
	public void printStatistics() {
		System.out.println("******DatabaseStandard Statistics******");
		System.out.println("Size is "+size()+" passwords.");
		System.out.println("Initial number of indexes when created = "+getInitailSpace());
		System.out.println("Load Factor is "+getLoadFactor());
		System.out.println("Average Number of Probes is "+getAvgProbes());
		System.out.println("Number of displacements (due to collisions): "+getCollisions());
		System.out.println("******End of DatabaseStandard Statistics******");
	}

}
