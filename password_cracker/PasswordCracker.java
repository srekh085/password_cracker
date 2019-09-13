import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PasswordCracker {
	private DatabaseInterface database;
	
	private void capsand2018 (String plain, boolean tocaps){
			try {
				database.save(plain, Sha1.hash(plain));
				if (tocaps) {
				String pass = plain;
				//System.out.println("saving from caps");
				database.save(pass+"2018", Sha1.hash(pass+"2018"));
				pass = pass.substring(0,1).toUpperCase() + pass.substring(1);
				//System.out.println("saving capsed");
				database.save(pass+"2018",Sha1.hash(pass+"2018"));
				changeAEI(pass);
				}
				else{
					database.save(plain+"2018", Sha1.hash(plain+"2018"));
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	private boolean needcaps(String plain) {
		if(Character.isLowerCase(plain.charAt(0))){
			return true;
		}
		return false;
	}
	private void changeAEI(String plain) {
		capsand2018(plain, needcaps(plain));
		ArrayList<String> letters = new ArrayList<>();
		ArrayList<String> replace = new ArrayList<>();
		if(plain.contains("a")) {letters.add("a"); replace.add("@");}
		if(plain.contains("e")) {letters.add("e"); replace.add("3");}
		if(plain.contains("i")) {letters.add("i"); replace.add("1");}
		int i = letters.size();
		//System.out.println(i);
		//System.out.println(replace);
		switch(i) {
		case 0:
			capsand2018(plain, needcaps(plain));
			break;
		case 1:
			//System.out.println("case1");
			//System.out.println(plain);
			String temp = plain;
			while(temp.contains(letters.get(0))) {
				temp = temp.replaceFirst(letters.get(0), replace.get(0));
				//System.out.println("calling caps");
				capsand2018(temp, needcaps(temp));
			}
			break;
		case 2:
			//System.out.println(letters);
			//System.out.println(replace);
			//System.out.println("case2");
			String temp1 = plain;
			while(temp1.contains(letters.get(0))) {
				temp1 = temp1.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp1, needcaps(temp1));
			}
			temp1 = plain;
			while(temp1.contains(letters.get(1))) {
				temp1 = temp1.replaceFirst(letters.get(1), replace.get(1));
				capsand2018(temp1, needcaps(temp1));
			}
			temp1 = plain;
			while(temp1.contains(letters.get(0))) {
				temp1 = temp1.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp1, needcaps(temp1));
				while(temp1.contains(letters.get(1))) {
					temp1 = temp1.replaceFirst(letters.get(1), replace.get(1));
					capsand2018(temp1, needcaps(temp1));
				}
			}
			break;
		case 3:
			//System.out.println("case3");
			String temp3 = plain;
			while(temp3.contains(letters.get(0))) {
				temp3 = temp3.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp3, needcaps(temp3));
			}
			temp3 = plain;
			while(temp3.contains(letters.get(1))) {
				temp3 = temp3.replaceFirst(letters.get(1), replace.get(1));
				capsand2018(temp3, needcaps(temp3));
			}
			temp3 = plain;
			while(temp3.contains(letters.get(2))) {
				temp3 = temp3.replaceFirst(letters.get(2), replace.get(2));
				capsand2018(temp3, needcaps(temp3));
			}
			temp3 = plain;
			while(temp3.contains(letters.get(0))) {
				temp3 = temp3.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp3, needcaps(temp3));
				while(temp3.contains(letters.get(1))) {
					temp3 = temp3.replaceFirst(letters.get(1), replace.get(1));
					capsand2018(temp3, needcaps(temp3));
				}
			}
			temp3 = plain;
			while(temp3.contains(letters.get(0))) {
				temp3 = temp3.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp3, needcaps(temp3));
				while(temp3.contains(letters.get(2))) {
					temp3 = temp3.replaceFirst(letters.get(2), replace.get(2));
					capsand2018(temp3, needcaps(temp3));
				}
			}
			temp3 = plain;
			while(temp3.contains(letters.get(1))) {
				temp3 = temp3.replaceFirst(letters.get(1), replace.get(1));
				capsand2018(temp3, needcaps(temp3));
				while(temp3.contains(letters.get(2))) {
					temp3 = temp3.replaceFirst(letters.get(2), replace.get(2));
					capsand2018(temp3, needcaps(temp3));
				}
			}
			temp3 = plain;
			while(temp3.contains(letters.get(0))) {
				temp3 = temp3.replaceFirst(letters.get(0), replace.get(0));
				capsand2018(temp3, needcaps(temp3));
				while(temp3.contains(letters.get(1))) {
					temp3 = temp3.replaceFirst(letters.get(1), replace.get(1));
					capsand2018(temp3, needcaps(temp3));
					while(temp3.contains(letters.get(2))) {
						temp3 = temp3.replaceFirst(letters.get(2), replace.get(2));
						capsand2018(temp3, needcaps(temp3));						
					}
				}
			}
		}
	}
	
	
	public void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database1) {
		//populating the hashmap
		database = database1;
		for(int i = 0; i < commonPasswords.size(); i++) {
			String plain = commonPasswords.get(i);
			
			//String updatedplain = plain;
			try {
				if (plain.matches("[0-9]+")) {
					database.save(plain, Sha1.hash(plain));
				}
				else {	
					changeAEI(plain);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String crackPassword(String encryptedPassword, DatabaseInterface database) {
		return database.decrypt(encryptedPassword);
	}
}
		
		
		
