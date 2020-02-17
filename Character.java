package project1;

public class Character implements Comparable<Character> {
	String name;
	String allegiances;
	MyArrayList<Battle> battles;

	public Character(String name, String allegiances, MyArrayList<Battle> characterBattles) {
		this.name = name;
		this.allegiances = allegiances;
		this.battles = new MyArrayList<Battle>();

		//when creating a character object, automatically goes through the list of battles and attaches only the battles he/she participated in
		String targetName = name;
		for (int i = 0; i < characterBattles.size(); i++) {
			if (targetName.equals(characterBattles.get(i).getAttackerKing())) {
				this.battles.add(characterBattles.get(i));
			}
		}
		for (int i = 0; i < characterBattles.size(); i++) {
			if (targetName.equals(characterBattles.get(i).getDefenderKing())) {
				this.battles.add(characterBattles.get(i));
			}
		}
		battles.sort();
	}

	public MyArrayList<Battle> getBattles() {
		return battles;
	}

	public String getName() {
		return name;
	}

	public int compareTo(String name) {
		return this.name.compareTo(name);
	}

	public int compareTo(Character Other) {
		return this.name.compareTo(Other.getName());

	}

	public String toString() {
		return (name + " with allegiance to " + allegiances + '\n' + this.battles.toString());
	}
}
