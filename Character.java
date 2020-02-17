package project3;

/**
 * Character class holds all the information for a given character.
 * @author Chrissy Jeon (jj2174)
 */
public class Character implements Comparable<Character> {
	String name;
	String allegiances;
	sLinkedList<Battle> battles;
	private String fatherName; 
	private String motherName; 
	private boolean isPatriarch; 
	private boolean isMatriarch;

	/**
	 * A character constructor that takes in three parameters.
	 * when creating a character object, automatically goes through the list of battles 
	 * and attaches only the battles he/she participated in (sorted).
	 * @param name (name of the character)
	 * @param allegiances (allegiances of the character)
	 * @param characterBattles (battles related to character)
	 */
	public Character(String name, String fatherName, String motherName, String allegiances, sLinkedList<Battle> characterBattles) {
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.allegiances = allegiances;
		this.battles = new sLinkedList<Battle>();

		String targetName = name;
		if (characterBattles != null && characterBattles.size() > 0) {
			for (int i = 0; i < characterBattles.size(); i++) {
				if (targetName.equals(characterBattles.get(i).getAttackerKing())) {
					this.battles.addLast(characterBattles.get(i));
				}
			}
			for (int i = 0; i < characterBattles.size(); i++) {
				if (targetName.equals(characterBattles.get(i).getDefenderKing())) {
					this.battles.addLast(characterBattles.get(i));
				}
			}battles.sort();
		}
	}
	
	/**
	 * Returns battles
	 * @return battles (battles relating to character)
	 */
	public sLinkedList<Battle> getBattles() {
		return battles;
	}

	/**
	 * Returns the name
	 * @return name (name of the character)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the allegiances
	 * @return allegiances (name of the house)
	 */
	public String getAllegiances() {
		return allegiances;
	}
	
	/**
	 * Returns the father name
	 * @return fatherName (name of the father)
	 */
	public String getfatherName() {
		return fatherName;
	}
	
	/**
	 * Returns the mother name
	 * @return motherName (name of the mother)
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * Compares two character names and returns integer
	 * @param name (name of the character)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(String name) {
		return this.name.toLowerCase().compareTo(name.toLowerCase());
	}

	/**
	 * Compares two characters and returns integer
	 * @param Other (Character object you want to compare with)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(Character Other) {
		return this.name.toLowerCase().compareTo(Other.getName().toLowerCase());
	}

	/**
	 * Returns the information about the character as a string
	 * @return String (string of character information)
	 */
	public String toString() {
		return (name + " with allegiance to " + allegiances + '\n' + this.battles.toString());
	}
	
	/**
	 * checks whether the character is patriarch and returns boolean value
	 * @return boolean (true : patriarch, false : not patriarch)
	 */
	public boolean isPatriarch() {
		if (this.fatherName.equals("PATRIARCH")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * checks whether the character is matriarch and returns boolean value
	 * @return boolean (true : matriarch, false : not matriarch)
	 */
	public boolean isMatriarch() {
		if (this.motherName.equals("MATRIARCH")) {
			return true;
		}else {
			return false;
		}
	}
}
