package project4;

/**
 * House class holds all the information about the house.
 * @author Chrissy Jeon (jj2174)
 */
public class House implements Comparable<House>{
	private String name;
	protected sLinkedList<Character> members; 
	private Character patriarch;
	private Character matriarch;
	public FamilyTree familyTree;
	
	/**
	 * House constructor that takes in the name of allegiance
	 * When creating a house object, automatically finds the patriarch and matriarch from the members of the house. 
	 * @param name (of the allegiance/house)
	 */
	public House (String name) {
		this.name = name;
		this.members = new sLinkedList<Character>();
		this.patriarch = null;
		this.matriarch = null;
		
		for (int n = 0; n < this.members.size(); n++) {
			if (this.members.get(n).isMatriarch()) {
				this.matriarch = this.members.get(n);
			}if (this.members.get(n).isPatriarch()) {
				this.patriarch = this.members.get(n);
			}
		}
	}

	/**
	 * Returns name
	 * @return name (of the house)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Creates familyTree of the House
	 */
	public void makeFamTree() {
		this.familyTree = new FamilyTree(this);
	}
	
	/**
	 * Returns familyTree
	 * @return familyTree (of the house)
	 */
	public FamilyTree getFamilyTree() {
		return familyTree;
	}
	
	/**
	 * Method that takes in a character and add to the singly linked list of members of the house
	 * @param members (of the house)
	 */
	public void addMembers(Character members){
		this.members.addLast(members);
	}
	
	/**
	 * Compares two house names and returns integer
	 * @param name (name of the houses)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(String name) {
		return this.name.toLowerCase().compareTo(name.toLowerCase());
	}
	
	/**
	 * Compares two houses and returns integer
	 * @param Other (House object you want to compare with)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(House Other) {
		return this.name.toLowerCase().compareTo(Other.getName().toLowerCase());
	}	
}
