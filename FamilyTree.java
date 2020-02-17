package project4;

/**
 * This is a family tree class that builds out a family tree from characters listed as members with allegiance to a House.
 * @author Chrissy Jeon (jj2174)
 *
 */
public class FamilyTree {

	/**
	 * This is an embedded CharacterNode class that implements Comparable interface.
	 * @author Chrissy Jeon (jj2174)
	 */
	private class CharacterNode implements Comparable<CharacterNode>{
		private Character character;
		private CharacterNode father;
		private CharacterNode mother;
		private boolean isSeen = false;  // This is for the extra credit.
		public sLinkedList<CharacterNode> children;

		/**
		 * A CharacterNode constructor that takes in a one parameter.
		 * Sets the character of CharacterNode to the character.
		 * Has references to father, mother, and children. 
		 * @param character (character element of a character)
		 */
		public CharacterNode (Character character) {
			this.character = character;
			this.father = father;
			this.mother = mother;
			this.children = new sLinkedList<CharacterNode>();
		}

		/**
		 * Returns CharacterNode father of the CharacterNode
		 * @return CharacterNode (father)
		 */
		public CharacterNode getFatherNode() {
			return this.father;
		}

		/**
		 * Returns CharacterNode mother of the CharacterNode
		 * @return CharacterNode (mother)
		 */
		public CharacterNode getMotherNode() {
			return this.mother;
		}
		
		/**
		 * Returns Character of the CharacterNode
		 * @return Character (character)
		 */
		public Character getCharacter() {
			return this.character;
		}
		
		/**
		 * Compares two nodes and returns int value.
		 * @param c (CharacterNode)
		 * @return int (if 0 : same, 1 : bigger, -1: smaller)
		 */
		public int compareTo(CharacterNode c) {
			return this.getCharacter().compareTo(c.getCharacter());
		}
	}
	private CharacterNode patriarch;
	private CharacterNode matriarch;
	
	//This is a singly linked list of unseen people for the extra credit.
	public sLinkedList<CharacterNode> unseen = new sLinkedList<CharacterNode>();

	/**
	 * FamilyTree constructor that takes in a house
	 * It automatically calls on buildFamilyTree function to create a family tree.
	 * @param h (house)
	 */
	public FamilyTree(House h) {
		this.buildFamilyTree(h);
	}

	/**
	 * Builds the tree structure by finding patriarch and matriarch.
	 * Uses the helper function by passing in the found patriarch and matriarch.
	 * @param h (House)
	 */
	public void buildFamilyTree(House h){
		sLinkedList<CharacterNode> listMembers = new sLinkedList<CharacterNode>();
		for (int i = 0; i < h.members.size(); i++) {
			if (h.members.get(i).isMatriarch()) {
				this.matriarch = new CharacterNode(h.members.get(i));
				this.matriarch.isSeen = true;
			}else if (h.members.get(i).isPatriarch()) {
				this.patriarch = new CharacterNode(h.members.get(i));
				this.patriarch.isSeen = true;
			}else {
				listMembers.addLast(new CharacterNode(h.members.get(i)));
			}
		}
		helpBuildFamTree(this.patriarch, listMembers);
		helpBuildFamTree(this.matriarch, listMembers);
	}

	/**
	 * This is a recursive helper function for building family tree that connects members to each other.
	 * This method takes in a CharacterNode and Singly Linked list of members.
	 * @param root (CharacterNode)
	 * @param listmembers (Singly Linked List of members of the house)
	 * @return CharacterNode (root)
	 */
	private CharacterNode helpBuildFamTree(CharacterNode root, sLinkedList<CharacterNode> listmembers) {	
		for(int i = 0; i < listmembers.size(); i++) {
			if (!listmembers.get(i).getCharacter().isMatriarch() && !listmembers.get(i).getCharacter().isPatriarch()) {
				if (listmembers.get(i).getCharacter().getfatherName().equals(root.getCharacter().getName())) {
					listmembers.get(i).father = root;
					listmembers.get(i).isSeen = true;
					if(!root.children.contains(listmembers.get(i)))
						root.children.addLast(listmembers.get(i)); 
				}if(listmembers.get(i).getCharacter().getMotherName().equals(root.getCharacter().getName())) {
					listmembers.get(i).mother = root;
					listmembers.get(i).isSeen = true;
					if(!root.children.contains(listmembers.get(i)))
						root.children.addLast(listmembers.get(i));
				}
			}
		}
		for (int c = 0; c < root.children.size(); c++) {
			helpBuildFamTree(root.children.get(c),listmembers);
		}
		
		//This is for the extra credit.
		//If the member in the house does not have any connections to it's father and mother, 
		//then they are added to the unseen list. 
		for (int x = 0; x < listmembers.size(); x++) {
			if (listmembers.get(x).isSeen == false) {
				unseen.addLast(listmembers.get(x));
			}
		}
		return root;
	}

	/**
	 * Method that prints the family tree by calling on the helper method by passing in patriarch and matriarch
	 */
	public void printTree(){
		System.out.print(help2PTree(this.patriarch));
		System.out.print(help2PTree(this.matriarch));
	}

	/**
	 * Helper method that calls on another helper method that deals with indentations
	 * @param c (CharacterNode)
	 * @return String (of the family tree)
	 */
	private String help2PTree(CharacterNode c) {
		return helpPTree(c, 0);
	}

	/**
	 * Second recursive helper method that takes in a CharacterNode and a integer value to print the family tree with indentation in front of children.
	 * @param c (CharacterNode of the start of the family tree)
	 * @param count (indentation)
	 * @return String (of the family tree)
	 */
	private String helpPTree(CharacterNode c, int count) {
		if(c==null)
			return "";

		String parents = "";
		if (c.getCharacter().isMatriarch()) {
			parents = "\nMatriarch: " + c.getCharacter().getName();
		}else if (c.getCharacter().isPatriarch()) {
			parents = "\nPatriarch: " + c.getCharacter().getName();
		}else {
			parents = "\t"+c.getCharacter().getName() + "-father: " + c.getCharacter().getfatherName() + "-mother: "+ c.getCharacter().getMotherName();

		}for (int i = 0; i < c.children.size(); i++) {
			parents += "\n";
			for (int k =0; k <count; k++) {
				parents += "\t";
			}
			parents += helpPTree(c.children.get(i), count+1);
		}
		return parents;
	}
	
	/**
	 * Extra credit find Character function that takes in a character element.
	 * This method converts the character element into a characterNode
	 * and calls on the helper function with that characterNode.
	 * @param find (Character of what you want to find)
	 * @return CharacterNode (of what you found)
	 */
	public CharacterNode findCharacterNodeExtraCredit(Character find) {
		CharacterNode findToCN = new CharacterNode(find);
		return helpfindCharacterNodeExtraCredit(findToCN);
	}
	
	/**
	 * Extra credit find character function helper that takes in a characterNode.
	 * When the characterNode is not reachable with the normal findCharacterNode function then,
	 * goes through the unseen list and finds the characterNode
	 * @param find (CharacterNode of what you want to find)
	 * @return CharacterNode (of what you found)
	 */
	private CharacterNode helpfindCharacterNodeExtraCredit(CharacterNode find) {
		if (findCharacterNode(find) == null) {
			for (int s = 0; s < unseen.size(); s++) {
				if (unseen.get(s).compareTo(find) == 0) {
					return find;
				}
			}
		}else {
			return findCharacterNode(find);
		}
		return null;
	}

	/**
	 * Method to find the CharacterNode that calls on the helper function
	 * @param find (CharacterNode that needs to be found)
	 * @return CharacterNode (of the CharacterNode that needs to be found)
	 */
	public CharacterNode findCharacterNode(CharacterNode find) {
		if (helpfindCharacterNode(this.patriarch,find) == null && helpfindCharacterNode(this.matriarch,find) == null) {
			return null;
		}else if (helpfindCharacterNode(this.matriarch,find) != null) {
			return helpfindCharacterNode(this.matriarch,find);
		}else if (helpfindCharacterNode(this.patriarch,find) != null) {
			return helpfindCharacterNode(this.patriarch,find);
		}
		return null;
	}

	/**
	 * Recursive helper method to search the CharacterNode in the tree by starting from the CharacterNode passed in as the starting point, and going down the tree, iterating through the children and each node below them. 
	 * @param startingPoint (Start of where you want to find 
	 * @param find (CharacterNode you want to find)
	 * @return CharacterNode (of what you found)
	 */
	private CharacterNode helpfindCharacterNode(CharacterNode startingPoint, CharacterNode find){
		if (startingPoint.compareTo(find) == 0) {
			return startingPoint;
		}else {
			for (int i = 0; i < startingPoint.children.size(); i++) {
				CharacterNode temp = helpfindCharacterNode(startingPoint.children.get(i), find);
				if (temp != null) {
					return temp;
				}
			}return null;
		}
	}

	/**
	 * Method that prints lineage of a character passed in climbing up the tree by calling on the helper function
	 * By passing in a character, it creates a characterNode of the character element passed in and finds the characterNode in the tree using the find fuction. 
	 * @param c (Character)
	 */
	public void LinToString (Character c) {
		CharacterNode cToCN = new CharacterNode(c);
		if (findCharacterNode(cToCN) != null) {
			CharacterNode temp = findCharacterNode(cToCN);
			String up = temp.getCharacter().getName() + " -father: " + temp.getCharacter().getfatherName() + " -mother: "+ temp.getCharacter().getMotherName();
			System.out.println(up + "\n");
			System.out.println("Father's side: ");
			System.out.println(helpLinToString(temp.father));
			System.out.println("Mother's side: ");
			System.out.println(helpLinToString(temp.mother));
			}
		}

	/**
	 * This is a recursive method that takes in a characterNode and climbs up to the Matriarch and Patriarch of the tree. 
	 * @param c (CharacterNode)
	 * @return String (of the lineage going up to the Patriarch and Matriarch)
	 */
	private String helpLinToString(CharacterNode c) {
		if(c==null)
			return "";
		String up = c.getCharacter().getName() + " -father: " + c.getCharacter().getfatherName() + " -mother: "+ c.getCharacter().getMotherName()+ "\n";
		
		if (c.father != null) {
			up += helpLinToString(c.father);

		}
		if (c.mother != null) {
			up += helpLinToString(c.mother);
		}
		return up;
	}
}

