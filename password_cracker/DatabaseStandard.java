import java.util.HashMap;

public class DatabaseStandard implements DatabaseInterface {
	private HashMap<String, String> database;
	public DatabaseStandard() {
	 // this constructor must create the initial hash map
		database = new HashMap<>();
		
	}
	public String save(String plainPassword, String encryptedPassword) {
		database.put(encryptedPassword, plainPassword);
		return null;
	}
	public String decrypt(String encryptedPassword) {
		if (database.containsKey(encryptedPassword)) {
		return database.get(encryptedPassword);
		}
		return "";
	}
	public int size() {
		return database.size();
	}
	public void printStatistics() {
		System.out.println("******DatabaseStandard Statistics******");
		System.out.println("Size is "+size()+" passwords.");
		System.out.println("Initial number of indexes when created = 16");
		System.out.println("******End of DatabaseStandard Statistics******");
	}
	
}
